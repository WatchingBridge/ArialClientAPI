package lukas.arialclient.api;

import lukas.arialclient.api.handler.PlayerHandler;
import lukas.arialclient.api.player.ArialPlayer;
import org.bukkit.entity.*;

import java.util.ArrayList;

public class API
{
    private final ArialAPI plugin;
    public ArrayList<Player> arialPlayers;
    
    public API(final ArialAPI plugin) {
        this.arialPlayers = new ArrayList<Player>();
        this.plugin = plugin;
    }
    
    public ArialAPI getPlugin() {
        return this.plugin;
    }
    
    public ArialPlayer getData(final Player player) {
        return PlayerHandler.getInstance().getData(player);
    }
}
