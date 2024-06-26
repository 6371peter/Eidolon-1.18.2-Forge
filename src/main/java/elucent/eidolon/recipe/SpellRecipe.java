package elucent.eidolon.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import elucent.eidolon.Eidolon;
import elucent.eidolon.Registry;
import elucent.eidolon.spell.Spell;
import elucent.eidolon.spell.Spells;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class SpellRecipe implements Recipe<RecipeWrapper> {
    public Ingredient input;
    ItemStack output;
    ResourceLocation registryName;
    Spell spell;

    public SpellRecipe(Ingredient input, ItemStack output, ResourceLocation registryName, Spell spell) {
        this.input = input;
        this.output = output;
        this.registryName = registryName;
        this.spell = spell;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ResourceLocation getRegistryName() {
        return registryName;
    }

    public Spell getSpell() {
        return spell;
    }

    public boolean matches(ItemStack stack) {
        if (input.test(stack)) return true;
        return false;
    }

    @Override
    public boolean matches(RecipeWrapper recipeWrapper, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper recipeWrapper) {
        return getResultItem();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return registryName;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Registry.SPELL_RECIPE.get();
    }

    public static class Type implements RecipeType<SpellRecipe> {
        @Override
        public String toString() {
            return Eidolon.MODID + ":spell";
        }
        public static final SpellRecipe.Type INSTANCE = new SpellRecipe.Type();
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<SpellRecipe> {

        @Override
        public SpellRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
            Ingredient input = CraftingHelper.getIngredient(jsonObject.get("input"));
            ItemStack output = CraftingHelper.getItemStack(jsonObject.getAsJsonObject("output"), true);
            ResourceLocation spellLoc = new ResourceLocation(jsonObject.get("spell").getAsString());
            Spell spell = Spells.find(spellLoc);

            if (spell == null) throw new JsonSyntaxException(resourceLocation + "has ERROR.Failed to find spell with registry name:" + spellLoc);
            return new SpellRecipe(input, output, resourceLocation, spell);
        }

        @Nullable
        @Override
        public SpellRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            Ingredient input = Ingredient.fromNetwork(friendlyByteBuf);
            ItemStack output = friendlyByteBuf.readItem();
            ResourceLocation spellLoc = friendlyByteBuf.readResourceLocation();
            Spell spell = Spells.find(spellLoc);
            return new SpellRecipe(input, output, resourceLocation, spell);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, SpellRecipe spellRecipe) {
            spellRecipe.input.toNetwork(friendlyByteBuf);
            friendlyByteBuf.writeItem(spellRecipe.output);
            friendlyByteBuf.writeResourceLocation(spellRecipe.getSpell().getRegistryName());
        }
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public boolean isSpecial() {
        return true; // needed to prevent errors loading modded recipes in the recipe book
    }
}
