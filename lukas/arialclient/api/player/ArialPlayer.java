package lukas.arialclient.api.player;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import net.minecraft.server.v1_8_R3.*;
import lukas.arialclient.api.*;

public class ArialPlayer
{
    private final Player player;
    private final EntityPlayer entityPlayer;
    private boolean arial;
    
    public ArialPlayer(final UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
        this.entityPlayer = ((CraftPlayer)this.player).getHandle();
    }
    
    public void handle(final Packet packet) {
        if (packet instanceof PacketPlayInCustomPayload) {
            final PacketPlayInCustomPayload payloadPacket = (PacketPlayInCustomPayload)packet;
            final String payload = payloadPacket.a();
            if (payload.equalsIgnoreCase("MC|ARIAL")) {
                this.arial = true;
                ArialAPI.getAPI().arialPlayers.add(this.player);
            }
            else {
                this.arial = false;
            }
        }
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public EntityPlayer getEntityPlayer() {
        return this.entityPlayer;
    }
    
    public boolean isArial() {
        return this.arial;
    }
    
    public void setArial(final boolean arial) {
        this.arial = arial;
    }
}
