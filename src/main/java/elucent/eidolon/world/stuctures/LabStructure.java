package elucent.eidolon.world.stuctures;

import java.util.Random;

import com.mojang.serialization.Codec;

import elucent.eidolon.Config;
import elucent.eidolon.Eidolon;
import elucent.eidolon.world.WorldGen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class LabStructure extends StructureFeature<NoneFeatureConfiguration> {

    public LabStructure(Codec<NoneFeatureConfiguration> codec) {
        super(codec, PieceGeneratorSupplier.simple(LabStructure::isFeatureChunk, new LabPieceGenerator()));
    }

    private static final ResourceLocation PART = new ResourceLocation(Eidolon.MODID,"lab");

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    static Random rand = new Random();

    static boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> ctx) {
        int i = ctx.chunkPos().x >> 4;
        int j = ctx.chunkPos().z >> 4;
        rand.setSeed((long) (i ^ j << 4) * 1086585193);
        double prob = rand.nextInt(10000) / 10000.0f;
        return prob < (1 / Config.LAB_RARITY.get());
    }

    public static class LabPieceGenerator implements PieceGenerator<NoneFeatureConfiguration> {
        @Override
        public void generatePieces(StructurePiecesBuilder pieces, Context<NoneFeatureConfiguration> ctx) {
            int i = ctx.chunkPos().x * 16;
            int j = ctx.chunkPos().z * 16;
            int k = Math.min(ctx.chunkGenerator().getSeaLevel(), ctx.chunkGenerator().getFirstFreeHeight(i, j, Heightmap.Types.OCEAN_FLOOR_WG, ctx.heightAccessor()));
            if (k < 33) k = 33;
            BlockPos blockpos = new BlockPos(i + ctx.random().nextInt(16), ctx.random().nextInt(k - 32) + 8, j + ctx.random().nextInt(16));

            pieces.addPiece(new RandomlyRotatedPiece(WorldGen.LAB_PIECE.get(), PART, ctx.structureManager(), blockpos, ctx.random()));
        }
    }

    public static class Piece extends BasicPiece {
        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {
            super(WorldGen.LAB_PIECE.get(), PART, nbt, context.structureManager());
            mirror = Mirror.NONE;
        }

        public Piece(StructureManager templateManager, BlockPos pos, Rotation rot, Random random) {
            super(WorldGen.LAB_PIECE.get(), PART, templateManager, pos, rot, Mirror.NONE, random);
        }
    }
}
