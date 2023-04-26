package elucent.eidolon.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class IllWoodBlock extends LogBlock{
    public IllWoodBlock(Properties properties, Supplier<Block> stripped) {
        super(properties, stripped);
    }

    @Nullable
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction) {
        if (toolAction.equals(ToolActions.AXE_STRIP)) {
            return stripped.get().defaultBlockState();
        }
        return null;
    }
}
