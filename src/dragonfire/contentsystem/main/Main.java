package dragonfire.contentsystem.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("ContentSystem Aktiveirt");
		getServer().getPluginManager().registerEvents(new Events(), this);
		getCommand("erstellewelt").setExecutor(new CreateworldCommand());
		getCommand("entfernewelt").setExecutor(new DelworldCommand());
	}
	
	public void onDisable() {
		System.out.println("Das Plugin ContentSystem wurde nicht Aktiviert!");
	}
}

