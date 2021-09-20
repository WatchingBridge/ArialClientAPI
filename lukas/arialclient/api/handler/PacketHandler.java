package lukas.arialclient.api.handler;

import lukas.arialclient.api.player.*;
import org.bukkit.entity.*;
import net.minecraft.server.v1_8_R3.*;
import io.netty.channel.*;

public class PacketHandler
{
    private final ArialPlayer arialPlayer;
    
    public void inject(final Player player) {
        final ChannelDuplexHandler duplexHandler = new ChannelDuplexHandler() {
            public void channelRead(final ChannelHandlerContext context, final Object packet) throws Exception {
                PacketHandler.this.arialPlayer.handle((Packet)packet);
                super.channelRead(context, packet);
            }
            
            public void write(final ChannelHandlerContext context, final Object packet, final ChannelPromise promise) throws Exception {
                super.write(context, packet, promise);
            }
        };
        final ChannelPipeline pipeline = PlayerHandler.getInstance().getData(player).getEntityPlayer().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), (ChannelHandler)duplexHandler);
    }
    
    public void uninject(final Player player) {
        final Channel channel = PlayerHandler.getInstance().getData(player).getEntityPlayer().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> channel.pipeline().remove("packet_handler"));
    }
    
    public PacketHandler(final ArialPlayer arialPlayer) {
        this.arialPlayer = arialPlayer;
    }
}
