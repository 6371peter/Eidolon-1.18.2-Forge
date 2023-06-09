package elucent.eidolon.spell;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import elucent.eidolon.Eidolon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellReloadListener extends SimpleJsonResourceReloadListener {
    public static final List<Map<String, ResourceLocation>> SPELL = new ArrayList<>();

    private static final Gson GSON =(new GsonBuilder()).create();
    public SpellReloadListener() {
        super(GSON, "spell");
    }
    public static void registry(AddReloadListenerEvent event) {
        event.addListener(new SpellReloadListener());
    }


    // TODO:增加输入nbt支持
    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        SPELL.clear();
        for (JsonElement spells : pObject.values()) {
            Map<String, ResourceLocation> map = new HashMap<>();

            JsonObject object = spells.getAsJsonObject();
            String spell = object.getAsJsonPrimitive("spell").getAsString();
            String output = object.getAsJsonPrimitive("output").getAsString();
            JsonObject inputObject = object.getAsJsonObject("input");
            String input = inputObject.getAsJsonPrimitive("item").getAsString();

            if (spell != null && input != null && output != null) {
                ResourceLocation inputLoc = new ResourceLocation(input);
                ResourceLocation outputLoc = new ResourceLocation(output);
                ResourceLocation spellLoc = new ResourceLocation(spell);

                map.put("input", inputLoc);
                map.put("output", outputLoc);
                map.put("spell", spellLoc);

                SPELL.add(map);
            }
            else Eidolon.LOGGER.info("Spell has some error,please recheck.");
        }
    }
}
