package net.soulsweaponry.networking.C2S;

import com.google.common.collect.Iterables;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.soulsweaponry.config.ConfigConstructor;
import net.soulsweaponry.entity.projectile.ReturningProjectile;
import net.soulsweaponry.entitydata.ReturningProjectileData;

import java.util.UUID;

public class ReturnThrownWeaponC2S {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        server.execute(() -> {
            ServerWorld serverWorld = Iterables.tryFind(server.getWorlds(), (element) -> element == player.getWorld()).orNull();
            if (serverWorld != null) {
                UUID uuid = ReturningProjectileData.getReturningProjectileUuid(player);
                Text text = Text.translatable("soulsweapons.weapon.no_soulbound_weapon");
                if (uuid != null) {
                    Entity entity = serverWorld.getEntity(uuid);
                    if (entity instanceof ReturningProjectile projectile) {
                        projectile.setShouldReturn(true);
                    } else if (ConfigConstructor.inform_player_about_no_soulbound_thrown_weapon) {
                        player.sendMessage(text, true);
                    }
                } else if (ConfigConstructor.inform_player_about_no_soulbound_thrown_weapon) {
                    player.sendMessage(text, true);
                }
            }
        });
    }
}