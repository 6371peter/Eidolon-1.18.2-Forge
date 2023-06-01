package elucent.eidolon.spell;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import elucent.eidolon.Config;
import elucent.eidolon.Eidolon;
import net.minecraft.resources.ResourceLocation;

public class Runes {
	static Map<ResourceLocation, Rune> runes = new HashMap<>();
	
	public static void register(Rune rune) {
		runes.put(rune.getRegistryName(), rune);
	}
	
	public static Rune find(ResourceLocation rl) {
		return runes.getOrDefault(rl, null);
	}
	
	public static Collection<Rune> getRunes() {
		return runes.values();
	}
	
	public static void init() {
		register(new Rune(new ResourceLocation(Eidolon.MODID, "sin")) {
			@Override
			public RuneResult doEffect(SignSequence seq) {
				seq.addRight(Signs.WICKED_SIGN);
				return RuneResult.PASS;
			}
		});
		register(new Rune(new ResourceLocation(Eidolon.MODID, "crimson_rose")) {
			@Override
			public RuneResult doEffect(SignSequence seq) {
				if (seq.removeRightmostN(Signs.WICKED_SIGN, 2)) {
					seq.addRight(Signs.BLOOD_SIGN);
					return RuneResult.PASS;
				}
				return RuneResult.FAIL;
			}
		});
		// Soul Rune
		// TODO:更改名字
		register(new Rune(new ResourceLocation(Eidolon.MODID, "soul")) {
			@Override
			public RuneResult doEffect(SignSequence seq) {
				if (seq.removeRightmostN(Signs.WICKED_SIGN, 2)) {
					seq.addRight(Signs.SOUL_SIGN);
					return RuneResult.PASS;
				}
				return RuneResult.FAIL;
			}
		});

		// Holy Rune
		// TODO:更改名字
		if (Config.HOLY_RUNE.get()) register(new Rune(new ResourceLocation(Eidolon.MODID, "holy")) {
			@Override
			public RuneResult doEffect(SignSequence seq) {
				seq.addRight(Signs.SACRED_SIGN);
				return RuneResult.PASS;
			}
		});
	}
}
