package me.mcblueparrot.client.mod.impl.replay.fix;

import net.minecraft.client.settings.KeyBinding;
import me.mcblueparrot.client.Client;
import me.mcblueparrot.client.annotation.ForgeCompat;

@Deprecated
@ForgeCompat
public class SCClientRegistry {

	public static void registerKeyBinding(KeyBinding keyBinding) {
		Client.INSTANCE.registerKeyBinding(keyBinding);
	}

}
