package elucent.eidolon.world.stuctures;

import com.google.common.collect.Sets;
import elucent.eidolon.Config;
import elucent.eidolon.Eidolon;
import elucent.eidolon.world.WorldGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraftforge.common.Tags;

import java.util.Random;
import java.util.Set;

public class StrayTowerStructure extends StructureFeature<NoneFeatureConfiguration> {
    static Random rand = new Random();
    private static final ResourceLocation PART = new ResourceLocation(Eidolon.MODID,"stray_tower");
    public StrayTowerStructure() {
        super(NoneFeatureConfiguration.CODEC, PieceGeneratorSupplier.simple(StrayTowerStructure::isFeatureChunk, new StrayTowerPieceGenerator()));
    }

    @Override
    public GenerationStep.Decoration step(){
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    static boolean isFeatureChunk(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> ctx) {
        if (!ctx.validBiomeOnTop(Types.WORLD_SURFACE)) return false;
        int i = ctx.chunkPos().x >> 4;
        int j = ctx.chunkPos().z >> 4;
        rand.setSeed((long) (i ^ j << 4) * 1556190469);
        double prob = rand.nextInt(10000) / 10000.0f;
        return prob < (1 / Config.STRAY_TOWER_RARITY.get());
    }

    public static class StrayTowerPieceGenerator implements PieceGenerator<NoneFeatureConfiguration> {
        @Override
        public void generatePieces(StructurePiecesBuilder pieces, Context<NoneFeatureConfiguration> ctx) {
            int i = ctx.chunkPos().x * 16;
            int j = ctx.chunkPos().z * 16;
            int minH = 999;
            for (int xx = -3; xx < 4; xx ++) for (int yy = -3; yy < 4; yy ++) {
                minH = Math.min(minH, ctx.chunkGenerator().getBaseHeight(i + xx, j + yy, Types.WORLD_SURFACE_WG, ctx.heightAccessor()));
            }
            pieces.addPiece(new RandomlyRotatedPiece(WorldGen.STRAY_TOWER_PIECE.get(), PART, ctx.structureManager(), new BlockPos(i, minH - 9, j), ctx.random()));
        }
    }

    public static class Piece extends BasicPiece {
        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {
            super(WorldGen.STRAY_TOWER_PIECE.get(),PART, nbt, context.structureManager());
        }

        public Piece(StructureManager templateManager, BlockPos pos, Rotation rot, Random random) {
            super(WorldGen.STRAY_TOWER_PIECE.get(), PART, templateManager, pos, rot, Mirror.NONE, random);
        }
    }
}
