package elucent.eidolon.world.stuctures;

import java.util.Random;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class RandomlyRotatedPiece extends BasicPiece {

    public RandomlyRotatedPiece(StructurePieceType type, ResourceLocation key, StructureManager StructureManager, CompoundTag nbt) {
        super(type, key, nbt, StructureManager);
    }

    public RandomlyRotatedPiece(StructurePieceType type, ResourceLocation key, StructureManager templateManager, BlockPos pos, Random random) {
        super(type, key, templateManager, pos, Rotation.NONE, Mirror.NONE, random);
    }
}
