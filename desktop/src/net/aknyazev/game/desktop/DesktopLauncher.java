package net.aknyazev.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.aknyazev.game.PlatformerMapEditor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Map Editor";

		config.width = 1600;
		config.height = 1000;
		config.vSyncEnabled = false;
		config.resizable = true;
		//config.fullscreen = true;
		new LwjglApplication(new PlatformerMapEditor(), config);
	}
}
