package elucent.eidolon.world.gen;

import elucent.eidolon.Config;
import elucent.eidolon.world.feature.PlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class OreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> undergound_ore = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        if (Config.LEAD_ENABLED.get()){
            undergound_ore.add(PlacedFeatures.LEAD_ORE_PLACED);
        }
        if (Config.SILVER_ENABLED.get()) {
            undergound_ore.add(PlacedFeatures.SILVER_ORE_PLACED);
        }
    }
}
