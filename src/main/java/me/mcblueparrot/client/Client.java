package me.mcblueparrot.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.logisticscraft.occlusionculling.DataProvider;
import com.logisticscraft.occlusionculling.OcclusionCullingInstance;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import me.mcblueparrot.client.config.ConfigVersion;
import me.mcblueparrot.client.culling.CullTask;
import me.mcblueparrot.client.event.EventBus;
import me.mcblueparrot.client.event.EventHandler;
import me.mcblueparrot.client.event.impl.PostGameStartEvent;
import me.mcblueparrot.client.event.impl.PreTickEvent;
import me.mcblueparrot.client.event.impl.SendChatMessageEvent;
import me.mcblueparrot.client.event.impl.ServerConnectEvent;
import me.mcblueparrot.client.mod.Mod;
import me.mcblueparrot.client.mod.hud.HudElement;
import me.mcblueparrot.client.mod.impl.ArabicNumeralsMod;
import me.mcblueparrot.client.mod.impl.BetterTooltipsMod;
import me.mcblueparrot.client.mod.impl.BlockSelectionMod;
import me.mcblueparrot.client.mod.impl.ChunkAnimatorMod;
import me.mcblueparrot.client.mod.impl.ColourSaturationMod;
import me.mcblueparrot.client.mod.impl.FreelookMod;
import me.mcblueparrot.client.mod.impl.FullbrightMod;
import me.mcblueparrot.client.mod.impl.HitColourMod;
import me.mcblueparrot.client.mod.impl.ItemPhysicsMod;
import me.mcblueparrot.client.mod.impl.MenuBlurMod;
import me.mcblueparrot.client.mod.impl.MotionBlurMod;
import me.mcblueparrot.client.mod.impl.NumeralPingMod;
import me.mcblueparrot.client.mod.impl.ParticlesMod;
import me.mcblueparrot.client.mod.impl.ScoreboardMod;
import me.mcblueparrot.client.mod.impl.ShowOwnTagMod;
import me.mcblueparrot.client.mod.impl.SolClientMod;
import me.mcblueparrot.client.mod.impl.TimeChangerMod;
import me.mcblueparrot.client.mod.impl.V1_7VisualsMod;
import me.mcblueparrot.client.mod.impl.ZoomMod;
import me.mcblueparrot.client.mod.impl.hud.ArmourMod;
import me.mcblueparrot.client.mod.impl.hud.ComboCounterMod;
import me.mcblueparrot.client.mod.impl.hud.CpsMod;
import me.mcblueparrot.client.mod.impl.hud.CrosshairMod;
import me.mcblueparrot.client.mod.impl.hud.FpsMod;
import me.mcblueparrot.client.mod.impl.hud.KeystrokesMod;
import me.mcblueparrot.client.mod.impl.hud.PingMod;
import me.mcblueparrot.client.mod.impl.hud.PositionMod;
import me.mcblueparrot.client.mod.impl.hud.PotionEffectsMod;
import me.mcblueparrot.client.mod.impl.hud.ReachDisplayMod;
import me.mcblueparrot.client.mod.impl.hud.SpeedometerMod;
import me.mcblueparrot.client.mod.impl.hud.TimersMod;
import me.mcblueparrot.client.mod.impl.hud.ToggleSprintMod;
import me.mcblueparrot.client.mod.impl.hud.chat.ChatMod;
import me.mcblueparrot.client.mod.impl.replay.SCReplayMod;
import me.mcblueparrot.client.ui.element.ChatButton;
import me.mcblueparrot.client.ui.screen.mods.ModsScreen;

/**
 * Main class for Sol Client.
 */
public class Client {

	private Minecraft mc = Minecraft.getMinecraft();
	public static final Client INSTANCE = new Client();
	private JsonObject data;
	
	private List<Mod> mods = new ArrayList<Mod>();
	
	private List<HudElement> huds = new ArrayList<HudElement>();
	private static final Logger LOGGER = LogManager.getLogger();

	private final File DATA_FILE = new File(Minecraft.getMinecraft().mcDataDir, "sol_client_mods.json");
	private final File LEGACY_DATA_FILE = new File(Minecraft.getMinecraft().mcDataDir, "parrot_client_mods.json" /* This was the old name. */ );

	public DetectedServer detectedServer;

	public EventBus bus = new EventBus();

	private Map<ResourceLocation, IResource> resources = new HashMap<>();
	private Map<String, CommandBase> commands = new HashMap<>();
	private List<ChatButton> chatButtons = new ArrayList<>();

	private ChatChannelSystem chatChannelSystem;

	public KeyBinding modsKey = new KeyBinding("Mods", Keyboard.KEY_RSHIFT, "Sol Client");

	public static final String VERSION = System.getProperty("me.mcblueparrot.client.version", "DEVELOPMENT TEST");
	public static final String NAME = "Sol Client " + VERSION;

	public void init() {
		System.setProperty("http.agent", "Sol Client/" + VERSION);

		LOGGER.info("Initialising...");
		bus.register(this);

		CpsMonitor.forceInit();

		LOGGER.info("Loading settings...");

		if(!DATA_FILE.exists() && LEGACY_DATA_FILE.exists()) {
			LEGACY_DATA_FILE.renameTo(DATA_FILE);
		}

		load();

		LOGGER.info("Loading mods...");

		register(SolClientMod::new);
		register(FpsMod::new);
		register(PositionMod::new);
		register(KeystrokesMod::new);
		register(CpsMod::new);
		register(PingMod::new);
		register(SpeedometerMod::new);
		register(ReachDisplayMod::new);
		register(ComboCounterMod::new);
		register(PotionEffectsMod::new);
		register(ArmourMod::new);
		register(TimersMod::new);
		register(ChatMod::new);
		register(CrosshairMod::new);
		register(ScoreboardMod::new);
		register(FullbrightMod::new);
		register(MotionBlurMod::new);
		register(MenuBlurMod::new);
		register(ColourSaturationMod::new);
		register(ChunkAnimatorMod::new);
		register(FreelookMod::new);
		register(ToggleSprintMod::new);
		register(V1_7VisualsMod::new);
		register(ItemPhysicsMod::new);
		register(ZoomMod::new);
		register(ParticlesMod::new);
		register(TimeChangerMod::new);
		register(ArabicNumeralsMod::new);
		register(NumeralPingMod::new);
		register(ShowOwnTagMod::new);
		register(BetterTooltipsMod::new);
		register(BlockSelectionMod::new);
		register(HitColourMod::new);
		register(SCReplayMod::new);
//		register(QuickPlayMod::new);

		registerKeyBinding(modsKey);

		try {
			unregisterKeyBinding((KeyBinding) GameSettings.class.getField("ofKeyBindZoom").get(mc.gameSettings));
		}
		catch(NoSuchFieldException | IllegalAccessException | ClassCastException ignored) {
			// OptiFine is not enabled.
		}

		cacheHudList();

		LOGGER.info("Loaded {} mods", mods.size());

		LOGGER.info("Saving settings...");
		save();

		LOGGER.info("Starting culling thread...");
		Thread cullThread = new Thread(new CullTask(new OcclusionCullingInstance(128, new DataProvider() {

			private WorldClient world;

			@Override
			public boolean prepareChunk(int x, int z) {
				return (world = mc.theWorld) != null;
			}

			@Override
			public boolean isOpaqueFullCube(int x, int y, int z) {
				return world.isBlockNormalCube(new BlockPos(x, y, z), false);
			}

		})), "Culling Thread");
		cullThread.setUncaughtExceptionHandler((thread, error) -> {
			LOGGER.error("Culling Thread has crashed:", error);
		});
		cullThread.start();
	}

	public void registerKeyBinding(KeyBinding keyBinding) {
		mc.gameSettings.keyBindings = ArrayUtils.add(mc.gameSettings.keyBindings, keyBinding);
	}

	public void unregisterKeyBinding(KeyBinding keyBinding) {
		mc.gameSettings.keyBindings = ArrayUtils.removeElement(mc.gameSettings.keyBindings, keyBinding);
		keyBinding.setKeyCode(0);
	}

	private Gson getGson(Mod mod) {
		GsonBuilder builder = new GsonBuilder();
		if(mod != null) {
			builder.registerTypeAdapter(mod.getClass(), (InstanceCreator<Mod>) (type) -> mod);
		}
		return builder.excludeFieldsWithoutExposeAnnotation().create();
	}

	private void cacheHudList() {
		huds.clear();
		for(Mod mod : mods) {
			huds.addAll(mod.getHudElements());
		}
	}

	public boolean load() {
		try {
			if(DATA_FILE.exists()) {
				// 1.8 uses old libraries, so this warning cannot be easily fixed.
				data = new JsonParser().parse(FileUtils.readFileToString(DATA_FILE)).getAsJsonObject();
				data = ConfigVersion.migrate(data);
			}
			else {
				data = new JsonObject();
				data.addProperty("version", ConfigVersion.values()[ConfigVersion.values().length - 1].name());
			}
			return true;
		}
		catch(IOException error) {
			LOGGER.error("Could not load data", error);
			data = new JsonObject();
			return false;
		}
	}

	public boolean save() {
		Gson gson = getGson(null);

		for(Mod mod : mods) {
			data.add(mod.getId(), gson.toJsonTree(mod));
		}

		try {
			FileUtils.writeStringToFile(DATA_FILE, gson.toJson(data));
			return true;
		}
		catch(IOException error) {
			LOGGER.error("Could not save data", error);
			return false;
		}
	}

	private void register(Supplier<Mod> modInitialiser) {
		try {
			Mod mod = modInitialiser.get();

			if(data.has(mod.getId())) {
				mods.add(getGson(mod).fromJson(data.get(mod.getId()), mod.getClass()));
			}
			else {
				mods.add(mod);
			}

			mod.onRegister();
		}
		catch(Throwable error) {
			LOGGER.error("Could not register mod", error);
		}
	}

	public void addResource(ResourceLocation location, IResource resource) {
		resources.put(location, resource);
	}

	public IResource getResource(ResourceLocation location) {
		return resources.get(location);
	}

	public CommandBase getCommand(String name) {
		return commands.get(name);
	}

	public Set<String> getCommandNames() {
		return commands.keySet();
	}

	public CommandBase registerCommand(String name, CommandBase command) {
		commands.put(name, command);
		for(String alias : command.getCommandAliases()) {
			commands.put(alias, command);
		}

		return command;
	}

	public CommandBase unregisterCommand(String name) {
		CommandBase command;

		for(String alias : (command = commands.remove(name)).getCommandAliases()) {
			commands.remove(alias);
		}

		return command;
	}

	public void setChatChannelSystem(ChatChannelSystem chatChannelSystem) {
		this.chatChannelSystem = chatChannelSystem;

		if(chatChannelSystem != null) {
			registerChatButton(ChatChannelSystem.ChatChannelButton.INSTANCE);
		}
		else {
			unregisterChatButton(ChatChannelSystem.ChatChannelButton.INSTANCE);
		}
	}

	public ChatChannelSystem getChatChannelSystem() {
		return chatChannelSystem;
	}

	@EventHandler
	public void onPostStart(PostGameStartEvent event) {
		mods.forEach(Mod::postStart);
	}

	@EventHandler
	public void onSendMessage(SendChatMessageEvent event) {
		// TODO tab completion

		if(event.message.startsWith("/")) {
			List<String> args = new ArrayList<>(Arrays.asList(event.message.split(" ")));
			String commandKey = args.get(0).substring(1);
			if(commands.containsKey(commandKey)) {
				event.cancelled = true;

				try {
					args.remove(0);

					commands.get(commandKey).processCommand(mc.thePlayer, args.toArray(new String[0]));
				}
				catch(CommandException error) {
					mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.RED + error.getMessage()));
				}
				catch(Exception error) {
					mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Could " +
							"not execute client-sided command, see log for details"));
					LOGGER.info("Could not execute client-sided command: " + event.message + ", error: ", error);
				}
			}
		}
		else if(getChatChannelSystem() != null) {
			event.cancelled = true;
			getChatChannelSystem().getChannel().sendMessage(mc.thePlayer, event.message);
		}
	}

	@EventHandler
	public void onTick(PreTickEvent event) {
		if(modsKey.isPressed()) {
			mc.displayGuiScreen(new ModsScreen(null));
		}
	}

	public void registerChatButton(ChatButton button) {
		chatButtons.add(button);
		chatButtons.sort(Comparator.comparingInt(ChatButton::getPriority));
	}

	public void unregisterChatButton(ChatButton button) {
		chatButtons.remove(button);
	}

	public void onServerChange(ServerData data) {
		setChatChannelSystem(null);

		if(data == null) {
			detectedServer = null;
			mods.forEach(Mod::unblock);
		}

		if(data != null) {
			for(DetectedServer server : DetectedServer.values()) {
				if(server.matches(data)) {
					detectedServer = server;
					mods.stream().filter(server::shouldBlockMod).forEach(Mod::block);
					break;
				}
			}
		}

		bus.post(new ServerConnectEvent(data, detectedServer));
	}

	public List<ChatButton> getChatButtons() {
		return chatButtons;
	}

	public List<Mod> getMods() {
		return this.mods;
	}

	public List<HudElement> getHuds() {
		return this.huds;
	}

}
