package lukas.arialclient.api.handler;

import lukas.arialclient.api.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;

public class PlayerHandler
{
    private static PlayerHandler instance;
    private Map<UUID, ArialPlayer> players;
    
    public PlayerHandler() {
        this.players = new HashMap<UUID, ArialPlayer>();
    }
    
    public void enable() {
        PlayerHandler.instance = new PlayerHandler();
        for (final Player players : Bukkit.getOnlinePlayers()) {
            PlayerHandler.instance.inject(players);
        }
    }
    
    public void disable() {
        for (final Player players : Bukkit.getOnlinePlayers()) {
            PlayerHandler.instance.uninject(players);
        }
        PlayerHandler.instance = null;
    }
    
    public void inject(final Player player) {
        final ArialPlayer arialPlayer = new ArialPlayer(player.getUniqueId());
        this.players.put(player.getUniqueId(), arialPlayer);
        final PacketHandler packetHandler = new PacketHandler(arialPlayer);
        packetHandler.inject(player);
    }
    
    public void uninject(final Player player) {
        final ArialPlayer arialPlayer = this.players.remove(player.getUniqueId());
        final PacketHandler packetHandler = new PacketHandler(arialPlayer);
        packetHandler.uninject(player);
    }
    
    public ArialPlayer getData(final Player player) {
        return this.players.get(player.getUniqueId());
    }
    
    public static PlayerHandler getInstance() {
        return (PlayerHandler.instance == null) ? (PlayerHandler.instance = new PlayerHandler()) : PlayerHandler.instance;
    }
}
