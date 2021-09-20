package lukas.arialclient.api.command;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import lukas.arialclient.api.*;
import lukas.arialclient.api.handler.*;
import lukas.arialclient.api.player.*;
import org.bukkit.*;

public class ClientCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String arg, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    if (ArialAPI.getAPI().arialPlayers.size() == 0) {
                        player.sendMessage(this.translate(ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.list.no-players")));
                        return true;
                    }
                    player.sendMessage(this.translate(ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.list.message")));
                    for (int i = 0; i < ArialAPI.getAPI().arialPlayers.size(); ++i) {
                        player.sendMessage(this.translate(ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.list.list-format").replace("%player%", ArialAPI.getAPI().arialPlayers.get(i).getName())));
                    }
                    return true;
                }
                else {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(this.translate(ArialAPI.getAPI().getPlugin().getConfig().getString("player-not-online")));
                        return true;
                    }
                    final ArialPlayer arialPlayer = PlayerHandler.getInstance().getData(target);
                    final String format = ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.message").replace("%target%", target.getName()).replace("%format%", arialPlayer.isArial() ? ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.format-on") : ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.format-off"));
                    player.sendMessage(this.translate(format));
                }
            }
            else {
                final ArialPlayer arialPlayer2 = PlayerHandler.getInstance().getData(player);
                final String format2 = ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.message").replace("%target%", "You").replace("%format%", arialPlayer2.isArial() ? ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.format-on") : ArialAPI.getAPI().getPlugin().getConfig().getString("arial-api.format-off"));
                player.sendMessage(this.translate(format2));
            }
        }
        return false;
    }
    
    private String translate(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
