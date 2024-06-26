package elucent.eidolon.spell;

import java.util.List;

import elucent.eidolon.Eidolon;
import elucent.eidolon.Registry;
import elucent.eidolon.capability.IReputation;
import elucent.eidolon.deity.Deities;
import elucent.eidolon.network.MagicBurstEffectPacket;
import elucent.eidolon.network.Networking;
import elucent.eidolon.recipe.SpellRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;

public class DarkTouchSpell extends StaticSpell {
    public static final String NECROTIC_KEY = new ResourceLocation(Eidolon.MODID, "necrotic").toString();

    public DarkTouchSpell(ResourceLocation name, Sign... signs) {
        super(name, signs);

        MinecraftForge.EVENT_BUS.addListener(DarkTouchSpell::onHurt);
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.addListener(DarkTouchSpell::tooltip);
            return new Object();
        });
    }

      @SubscribeEvent
      public static void onHurt(LivingHurtEvent event) {
        if (!event.getSource().equals(DamageSource.WITHER)
                && event.getSource().getEntity() instanceof LivingEntity
                && ((LivingEntity)event.getSource().getEntity()).getMainHandItem().hasTag()
                && ((LivingEntity)event.getSource().getEntity()).getMainHandItem().getTag().contains(NECROTIC_KEY)) {
            float amount = Math.min(1, event.getAmount());
            event.setAmount(event.getAmount() - amount);
            if (event.getAmount() <= 0) event.setCanceled(true);
            int prevHurtResist = event.getEntityLiving().hurtTime;
              /*if (event.getEntityLiving().attackEntityFrom(new EntityDamageSource(DamageSource.WITHER.toString(), event.getSource().getEntity()), amount)) {
                  if (event.getEntityLiving().getHealth() <= 0) event.setCanceled(true);
                  else event.getEntityLiving().hurtTime = prevHurtResist;
              }*/
            DamageSource damageSource = new EntityDamageSource(DamageSource.WITHER.toString(), event.getSource().getEntity());
            if (event.getEntityLiving().hurt(damageSource, amount)) {
                if (event.getEntityLiving().getHealth() <= 0) event.setCanceled(true);
                else event.getEntityLiving().hurtTime = prevHurtResist;
            }
        }
    }

      @OnlyIn(Dist.CLIENT)
      @SubscribeEvent
      public static void tooltip(ItemTooltipEvent event) {
         if (event.getItemStack().hasTag() && event.getItemStack().getTag().contains(NECROTIC_KEY)) {
              event.getToolTip().add(new TranslatableComponent("eidolon.tooltip.necrotic").withStyle(ChatFormatting.DARK_BLUE));
          }
      }

    @Override
    public boolean canCast(Level world, BlockPos pos, Player player) {
        if (!world.getCapability(IReputation.INSTANCE).isPresent()) return false;
        if (world.getCapability(IReputation.INSTANCE).resolve().get().getReputation(player, Deities.DARK_DEITY.getId()) < 4.0) return false;

        HitResult ray = world.clip(new ClipContext(player.getEyePosition(0), player.getEyePosition(0).add(player.getLookAngle().scale(4)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        Vec3 v = ray.getType() == HitResult.Type.BLOCK ? ray.getLocation() : player.getEyePosition(0).add(player.getLookAngle().scale(4));
        List<ItemEntity> items = world.getEntitiesOfClass(ItemEntity.class, new AABB(v.x - 1.5, v.y - 1.5, v.z - 1.5, v.x + 1.5, v.y + 1.5, v.z + 1.5));
        if (items.size() != 1) return false;
        ItemStack stack = items.get(0).getItem();
        return stack.getCount() == 1 && canTouch(stack);
    }

    boolean canTouch(ItemStack stack) {
        // Recipe
        for (SpellRecipe recipe : Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(SpellRecipe.Type.INSTANCE)) {
            if (recipe.getSpell().equals(Spells.DARK_TOUCH) && recipe.matches(stack)) return true;
        }
        // DISC
        if (stack.getItem() instanceof RecordItem && stack.getItem() != Registry.PAROUSIA_DISC.get()) return true;
        // Tool
        if (stack.isDamageableItem() && stack.getMaxStackSize() == 1) return true;
        return false;
    }

    ItemStack touchResult(ItemStack stack) {
        // Recipe
        for (SpellRecipe recipe : Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(SpellRecipe.Type.INSTANCE)) {
            if (recipe.getSpell().equals(Spells.DARK_TOUCH) && recipe.getInput().test(stack) && recipe.getOutput() != null) {
                return new ItemStack(recipe.getResultItem().getItem());
            }
        }
        // DISC
        if (stack.getItem() instanceof RecordItem && stack.getItem() != Registry.PAROUSIA_DISC.get()) return new ItemStack(Registry.PAROUSIA_DISC.get());
        // Tool create Necrotic tag
        else if (stack.isDamageableItem() && stack.getMaxStackSize() == 1) {
            stack.getOrCreateTag().putBoolean(NECROTIC_KEY, true);
            return stack;
        }
        return stack;
    }

    @Override
    public void cast(Level world, BlockPos pos, Player player) {
        HitResult ray = world.clip(new ClipContext(player.getEyePosition(0), player.getEyePosition(0).add(player.getLookAngle().scale(4)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        Vec3 v = ray.getType() == HitResult.Type.BLOCK ? ray.getLocation() : player.getEyePosition(0).add(player.getLookAngle().scale(4));
        List<ItemEntity> items = world.getEntitiesOfClass(ItemEntity.class, new AABB(v.x - 1.5, v.y - 1.5, v.z - 1.5, v.x + 1.5, v.y + 1.5, v.z + 1.5));
        if (items.size() == 1) {
            if (!world.isClientSide) {
                ItemStack stack = items.get(0).getItem();
                if (canTouch(stack)) {
                    items.get(0).setItem(touchResult(stack));
                    Vec3 p = items.get(0).position();
                    items.get(0).setDefaultPickUpDelay();
                    Networking.sendToTracking(world, items.get(0).blockPosition(), new MagicBurstEffectPacket(p.x, p.y, p.z, Signs.WICKED_SIGN.getColor(), Signs.BLOOD_SIGN.getColor()));
                }
            } else {
                world.playSound(player, player.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.NEUTRAL, 1.0F, 0.6F + world.random.nextFloat() * 0.2F);
            }
        }
    }
}
