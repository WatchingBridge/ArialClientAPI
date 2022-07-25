package lukas.arialclient.api;

import lukas.arialclient.api.command.ClientCommand;
import lukas.arialclient.api.handler.PlayerHandler;
import lukas.arialclient.api.listener.ConnectionListener;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;

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
        this.getCommand("arialapi").setExecutor((CommandExecutor)new ClientCommand());
    }
    
    public void onDisable() {
        PlayerHandler.getInstance().disable();
    }
    
    public static API getAPI() {
        return ArialAPI.api;
    }
}
