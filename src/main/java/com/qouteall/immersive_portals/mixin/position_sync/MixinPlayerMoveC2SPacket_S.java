package com.qouteall.immersive_portals.mixin.position_sync;

import com.qouteall.immersive_portals.ducks.IEPlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerMoveC2SPacket.class)
public class MixinPlayerMoveC2SPacket_S implements IEPlayerMoveC2SPacket {
    private DimensionType playerDimension;
    
    @Inject(
        method = "read",
        at = @At("HEAD")
    )
    private void onRead(PacketByteBuf packetByteBuf_1, CallbackInfo ci) {
        playerDimension = DimensionType.byRawId(packetByteBuf_1.readInt());
    }
    
    @Inject(
        method = "write",
        at = @At("HEAD")
    )
    private void onWrite(PacketByteBuf packetByteBuf_1, CallbackInfo ci) {
        packetByteBuf_1.writeInt(playerDimension.getRawId());
    }
    
    @Override
    public DimensionType getPlayerDimension() {
        return playerDimension;
    }
    
    @Override
    public void setPlayerDimension(DimensionType dim) {
        playerDimension = dim;
    }
    
    
}
