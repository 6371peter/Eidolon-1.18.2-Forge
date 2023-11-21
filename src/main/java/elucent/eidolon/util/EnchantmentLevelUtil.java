package elucent.eidolon.util;

import elucent.eidolon.Eidolon;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

// Code from Create Enchantment Industry
public class EnchantmentLevelUtil {
    private static final MethodHandle getMaxLevel;

    static {
        Method method;
        try {
            Class<?> EnchHooks = Class.forName("shadows.apotheosis.ench.asm.EnchHooks");
            method = EnchHooks.getMethod("getMaxLevel", Enchantment.class);
        } catch (Throwable exception) {
            Eidolon.LOGGER.debug("Failed to load EnchHooks from Apotheosis, fall back to vanilla method...");
            method = ObfuscationReflectionHelper.findMethod(Enchantment.class, "m_6586_");
        }
        try {
            method.setAccessible(true);
            getMaxLevel = MethodHandles.lookup().unreflect(method);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access Enchantment#getMaxLevel!");
        }
    }

    public static int getMaxLevel(Enchantment enchantment) {
        Integer maxLevel;
        try {
            maxLevel = (Integer) getMaxLevel.invoke(enchantment);
        } catch (Throwable e) {
            Eidolon.LOGGER.warn("Fail to invoke getMaxLevel", e);
            maxLevel = enchantment.getMaxLevel();
        }
        return maxLevel;
    }
}
