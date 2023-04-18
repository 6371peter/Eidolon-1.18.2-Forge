package elucent.eidolon.world.feature;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class OrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placement) {
        return List.of(placementModifier, InSquarePlacement.spread(), placement, BiomeFilter.biome());
    }
}
