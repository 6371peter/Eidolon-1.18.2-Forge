package elucent.eidolon.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class SpellRegistry {
    static Map<ResourceLocation, SpellRecipe> recipes = new HashMap<>();

    public static SpellRecipe register(SpellRecipe recipe) {
        ResourceLocation loc = recipe.getRegistryName();
        assert loc != null;
        recipes.put(loc, recipe);
        return recipe;
    }

    public static SpellRecipe find(ResourceLocation loc) {
        return recipes.get(loc);
    }
}
