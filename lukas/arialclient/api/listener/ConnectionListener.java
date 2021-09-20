package lukas.arialclient.api.listener;

import lukas.arialclient.api.handler.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import lukas.arialclient.api.*;
import org.bukkit.event.player.*;
import lukas.arialclient.api.player.*;
import org.bukkit.*;

public class ConnectionListener implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        PlayerHandler.getInstance().inject(player);
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        PlayerHandler.getInstance().uninject(player);
        ArialAPI.getAPI().arialPlayers.remove(player);
    }
    
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        if (ArialAPI.getAPI().getPlugin().getConfig().getBoolean("chat.enabled")) {
            final Player player = event.getPlayer();
            final ArialPlayer arialPlayer = PlayerHandler.getInstance().getData(player);
            final String format = event.getFormat();
            event.setFormat(format.replace("%arial%", arialPlayer.isArial() ? this.translate(ArialAPI.getAPI().getPlugin().getConfig().getString("chat.prefix")) : ""));
        }
    }
    
    private String translate(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
