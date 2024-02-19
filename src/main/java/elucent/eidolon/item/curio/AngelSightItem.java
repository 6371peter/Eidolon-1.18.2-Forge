package elucent.eidolon.item.curio;

import java.util.Random;

import elucent.eidolon.Registry;
import elucent.eidolon.entity.AngelArrowEntity;
import elucent.eidolon.item.ItemBase;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class AngelSightItem extends ItemBase {

    public AngelSightItem(Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(AngelSightItem::onShoot);
    }

    @SubscribeEvent
    public static void onShoot(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof AbstractArrow arrow && !(arrow instanceof AngelArrowEntity)) {
            if (arrow.getOwner() instanceof Player player) {
                if (CuriosApi.getCuriosHelper().findFirstCurio(player, Registry.ANGELS_SIGHT.get()).isEmpty()) return;
                Level world = event.getWorld();
                float velocity = (float) Math.sqrt(arrow.getDeltaMovement().lengthSqr());
                if (velocity < 0.1) return;
                if (!world.isClientSide) {
                    AngelArrowEntity angelArrow = new AngelArrowEntity(world, player);
                    angelArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, velocity, 1.0F);
                    angelArrow.setCritArrow(arrow.isCritArrow());
                    angelArrow.setArrow(arrow);
                    angelArrow.pickup = arrow.pickup;
                    world.addFreshEntity(angelArrow);
                }
                event.setCanceled(true);
            }
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused) {
        return new EidolonCurio(stack) {
            @Override
            public boolean canRightClickEquip() {
                return true;
            }
        };
    }
}
