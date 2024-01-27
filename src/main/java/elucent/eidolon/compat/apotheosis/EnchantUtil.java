package elucent.eidolon.compat.apotheosis;

import it.unimi.dsi.fastutil.floats.Float2FloatMap;
import it.unimi.dsi.fastutil.floats.Float2FloatOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import shadows.apotheosis.ench.EnchModule;
import shadows.apotheosis.ench.EnchantmentInfo;
import shadows.apotheosis.ench.table.EnchantingStatManager;
import shadows.apotheosis.ench.table.IEnchantableItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EnchantUtil {
    public static float getEthereal(Level world, BlockPos pos) {
        Float2FloatMap eternaMap = new Float2FloatOpenHashMap();
        float[] stats = { 15F, 0 };
        float etherealPower = 0;
        for(int k = -1; k <= 1; ++k) {
            for(int l = -1; l <= 1; ++l) {
                if ((k != 0 || l != 0) && world.isEmptyBlock(pos.offset(l, 0, k)) && world.isEmptyBlock(pos.offset(l, 1, k))) {
                    gatherStats(eternaMap, stats, world, pos.offset(l * 2, 0, k * 2));
                    gatherStats(eternaMap, stats, world, pos.offset(l * 2, 1, k * 2));
                    if (l != 0 && k != 0) {
                        gatherStats(eternaMap, stats, world, pos.offset(l * 2, 0, k));
                        gatherStats(eternaMap, stats, world, pos.offset(l * 2, 1, k));
                        gatherStats(eternaMap, stats, world, pos.offset(l, 0, k * 2));
                        gatherStats(eternaMap, stats, world, pos.offset(l, 1, k * 2));
                    }
                }
            }
        }
        List<Float2FloatMap.Entry> entries = new ArrayList<>(eternaMap.float2FloatEntrySet());
        Collections.sort(entries, Comparator.comparing(Float2FloatMap.Entry::getFloatKey));
        for (Float2FloatMap.Entry entry : entries) {
            if (entry.getFloatKey() > 0) etherealPower = Math.min(entry.getFloatKey(), etherealPower + entry.getFloatValue());
            else etherealPower +=entry.getFloatValue();
        }

        if (stats[0] >= 0 && stats[1] >= 0) {
            if (stats[0] >= 100) stats[0] = 100;
            if (stats[1] >= 100) stats[1] = 100;
            etherealPower = etherealPower + etherealPower * stats[0] / 100 * stats[1] / 100;
        }

        return etherealPower;
    }

    public static void gatherStats(Float2FloatMap eternaMap, float[] stats, Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if(state.isAir()) return;
        float max = EnchantingStatManager.getMaxEterna(state, level, pos);
        float eterna = EnchantingStatManager.getEterna(state, level, pos);
        eternaMap.put(max, eternaMap.getOrDefault(max, 0) + eterna);
        float quanta = EnchantingStatManager.getQuanta(state, level, pos);
        stats[0] += quanta;
        float quantaRec = EnchantingStatManager.getQuantaRectification(state, level, pos);
        stats[1] += quantaRec;
    }

    public static int enchantmentLevelToPower(Enchantment enchantment,float power, ItemStack stack) {
        IEnchantableItem enchi = (IEnchantableItem) stack.getItem();
        EnchantmentInfo info = EnchModule.getEnchInfo(enchantment);
        if (!info.isDiscoverable()) return 0;
        for (int level = info.getMaxLevel(); level > enchantment.getMinLevel() - 1; --level) {
            if (power >= info.getMinPower(level) && power <= info.getMaxPower(level)) {
                return level;
            }
        }
        return 0;
    }
}
