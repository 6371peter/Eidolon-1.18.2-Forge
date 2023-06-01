package elucent.eidolon.ritual;

import java.util.List;

import elucent.eidolon.Config;
import elucent.eidolon.Eidolon;
import elucent.eidolon.item.CodexItem;
import elucent.eidolon.mixin.ZombieVillagerMixin;
import elucent.eidolon.spell.Runes;
import elucent.eidolon.util.ColorUtil;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

public class PurifyRitual extends Ritual {
    public static final ResourceLocation SYMBOL = new ResourceLocation(Eidolon.MODID, "particle/purify_ritual");

    public PurifyRitual() {
        super(SYMBOL, ColorUtil.packColor(255, 163, 252, 255));
    }

    @Override
    public RitualResult start(Level world, BlockPos pos) {
        // Ritual Item Focus
        List<IRitualItemFocus> tiles = Ritual.getTilesWithinAABB(IRitualItemFocus.class, world, getSearchBounds(pos));
        // If Focus has Codex,then get Focus block pos
        // TODO:在对应位置显示粒子效果?
        BlockPos blockPos = null;
        if (!tiles.isEmpty()) for (int i = 0; i< tiles.size(); i++) {
            ItemStack stack = tiles.get(i).provide();
            if (stack.getItem() instanceof CodexItem) {
                blockPos = ((BlockEntity)tiles.get(i)).getBlockPos();
                break;
            }
        }

        List<PathfinderMob> purifiable = world.getEntitiesOfClass(PathfinderMob.class, Ritual.getDefaultBounds(pos), (entity) -> entity instanceof ZombieVillager || entity instanceof ZombifiedPiglin || entity instanceof Zoglin);

        if (purifiable.size() > 0 && !world.isClientSide) world.playSound(null, pos, SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.PLAYERS, 1.0f, 1.0f);
        if (!world.isClientSide) for (PathfinderMob entity : purifiable) {
            if (entity instanceof ZombieVillager) {
                if (!tiles.isEmpty() && Config.HOLY_RUNE.get()) for (int i = 0; i< tiles.size(); i++) {
                    ItemStack stack = tiles.get(i).provide();
                    if (stack.getItem() instanceof CodexItem) {
                        if (!stack.hasTag()) {
                            tiles.get(i).replace(CodexItem.withRune(stack.copy(), Runes.find(new ResourceLocation(Eidolon.MODID, "holy"))));
                            /*
                            ItemStack newStack = stack.copy();
                            newStack.getOrCreateTag().putString("rune", "holy");
                            tiles.get(i).replace(newStack);*/
                            break;
                        }
                    }
                }
                ((ZombieVillagerMixin)entity).callFinishConversion((ServerLevel)world);
            }
            if (entity instanceof ZombifiedPiglin) {
                entity.remove(RemovalReason.KILLED);
                Piglin piglin = new Piglin(EntityType.PIGLIN, world);
                piglin.copyPosition(entity);
                piglin.finalizeSpawn((ServerLevel)world, world.getCurrentDifficultyAt(pos), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(piglin);
            }
            if (entity instanceof Zoglin) {
                entity.remove(RemovalReason.KILLED);
                Hoglin hoglin = new Hoglin(EntityType.HOGLIN, world);
                hoglin.copyPosition(entity);
                hoglin.finalizeSpawn((ServerLevel)world, world.getCurrentDifficultyAt(pos), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(hoglin);
            }
        }
        return RitualResult.TERMINATE;
    }
}
