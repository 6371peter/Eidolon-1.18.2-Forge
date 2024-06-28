package elucent.eidolon.mixin;

import elucent.eidolon.event.StuckInBlockEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    protected Vec3 stuckSpeedMultiplier;

    @Inject(method = "makeStuckInBlock", at = @At("HEAD"), cancellable = true)
    public void makeStuckInBlock(BlockState pState, Vec3 pMotionMultiplier, CallbackInfo ci) {
        StuckInBlockEvent event = new StuckInBlockEvent((Entity) (Object) this, pState, pMotionMultiplier);
        MinecraftForge.EVENT_BUS.post(event);
        ((Entity) (Object)this).resetFallDistance();
        this.stuckSpeedMultiplier = event.getStuckMultiplier();
        ci.cancel();
    }
}
