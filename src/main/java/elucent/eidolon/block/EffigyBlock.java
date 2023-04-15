package elucent.eidolon.block;

import elucent.eidolon.tile.EffigyTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EffigyBlock extends HorizontalWaterloggableBlock implements EntityBlock {
    public EffigyBlock(Properties properties) {
        super(properties);
    }

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EffigyTileEntity(pos, state);
	}
}
