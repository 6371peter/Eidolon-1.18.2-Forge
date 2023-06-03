package elucent.eidolon.entity.ai;

import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;
import java.util.function.Predicate;

public class ZombieBarterGoal extends GenericBarterGoal<Zombie>{

    public ZombieBarterGoal(Zombie entity, Predicate<ItemStack> valid, Function<ItemStack, ItemStack> result) {
        super(entity, valid, result);
    }
    
    @Override
    public void tick() {
        super.tick();
    }
}
