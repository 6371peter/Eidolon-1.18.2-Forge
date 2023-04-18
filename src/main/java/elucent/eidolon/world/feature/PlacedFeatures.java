package elucent.eidolon.world.feature;

import elucent.eidolon.Config;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final Holder<PlacedFeature> LEAD_ORE_PLACED = PlacementUtils.register("lead_ore_placed",
            ConfiguredFeatures.LEAD_ORE,
            OrePlacement.orePlacement(
                    CountPlacement.of(Config.LEAD_VEIN_COUNT.get()),
                    HeightRangePlacement.uniform(
                            VerticalAnchor.absolute(Config.LEAD_MIN_Y.get()),
                            VerticalAnchor.absolute(Config.LEAD_MAX_Y.get())
                    )
            ));

    public static final Holder<PlacedFeature> SILVER_ORE_PLACED = PlacementUtils.register("silver_ore_placed",
            ConfiguredFeatures.SILVER_ORE,
            OrePlacement.orePlacement(
                    CountPlacement.of(Config.SILVER_VEIN_COUNT.get()),
                    HeightRangePlacement.uniform(
                            VerticalAnchor.absolute(Config.SILVER_MIN_Y.get()),
                            VerticalAnchor.absolute(Config.SILVER_MAX_Y.get())
                    )
            ));
}