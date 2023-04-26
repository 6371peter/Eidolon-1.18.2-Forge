package elucent.eidolon.world.feature;

import elucent.eidolon.Config;
import elucent.eidolon.Registry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> LEAD_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Registry.LEAD_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registry.DEEP_LEAD_ORE.get().defaultBlockState())
    );

    public static final List<OreConfiguration.TargetBlockState> SILVER_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Registry.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registry.DEEP_SILVER_ORE.get().defaultBlockState())
    );
    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> LEAD_ORE = FeatureUtils.register("lead_ore",
            Feature.ORE, new OreConfiguration(LEAD_ORES, Config.LEAD_VEIN_SIZE.get()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SILVER_ORE = FeatureUtils.register("silver_ore",
            Feature.ORE, new OreConfiguration(SILVER_ORES, Config.SILVER_VEIN_SIZE.get()));
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> ILLWOOD_TREE =
            FeatureUtils.register("illwood", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Registry.ILLWOOD_LOG.get()),
                    new StraightTrunkPlacer(5,6,3),
                    BlockStateProvider.simple(Registry.ILLWOOD_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1,0,2)).build());
}
