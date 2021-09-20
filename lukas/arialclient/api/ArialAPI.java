package lukas.arialclient.api;

import org.bukkit.plugin.java.*;
import lukas.arialclient.api.handler.*;
import org.bukkit.*;
import lukas.arialclient.api.listener.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import lukas.arialclient.api.command.*;
import org.bukkit.command.*;

public class ArialAPI extends JavaPlugin
{
    private static API api;
    
    public void onEnable() {
        ArialAPI.api = new API(this);
        PlayerHandler.getInstance().enable();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getPluginManager().registerEvents((Listener)new ConnectionListener(), (Plugin)this);
        this.getCommand("arial").setExecutor((CommandExecutor)new ClientCommand());
    }
    
    public void onDisable() {
        PlayerHandler.getInstance().disable();
    }
    
    public static API getAPI() {
        return ArialAPI.api;
    }
}
