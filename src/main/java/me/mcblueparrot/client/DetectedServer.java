package me.mcblueparrot.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import net.minecraft.client.multiplayer.ServerData;
import me.mcblueparrot.client.mod.Mod;

public enum DetectedServer {
	GOMMEHD("gommehd\\.net(:[0-9]+)?", "https://www.gommehd.net/forum/threads/rules-minecraft.941059/",
			"freelook", "fullbright"),
	MINEPLEX("([A-z]+\\.)?mineplex\\.com(:[0-9]+)?", "https://www.mineplex.com/rules/", "freelook");

	private Pattern pattern;
	private URI blockedModPage;
	private List<String> blockedMods;

	private DetectedServer(String regex, String blockModPage, String... blockedMods) {
		pattern = Pattern.compile(regex);
		try {
			this.blockedModPage = new URI(blockModPage);
		} catch (URISyntaxException error) {
			throw new IllegalStateException(error);
		}
		this.blockedMods = Arrays.asList(blockedMods);
	}

	public URI getBlockedModPage() {
		return blockedModPage;
	}

	public boolean shouldBlockMod(Mod mod) {
		return blockedMods.contains(mod.getId());
	}

	public boolean matches(ServerData data) {
		return pattern.matcher(data.serverIP).matches();
	}

}
