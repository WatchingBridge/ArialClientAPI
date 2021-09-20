package lukas.arialclient.api;

import java.util.*;
import org.bukkit.entity.*;
import lukas.arialclient.api.player.*;
import lukas.arialclient.api.handler.*;

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
