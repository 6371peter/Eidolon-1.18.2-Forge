package elucent.eidolon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;

import elucent.eidolon.block.BlockBase;
import elucent.eidolon.block.BrazierBlock;
import elucent.eidolon.block.CandleBlock;
import elucent.eidolon.block.CandlestickBlock;
import elucent.eidolon.block.CisternBlock;
import elucent.eidolon.block.CrucibleBlock;
import elucent.eidolon.block.EffigyBlock;
import elucent.eidolon.block.EnchantedAshBlock;
import elucent.eidolon.block.GobletBlock;
import elucent.eidolon.block.HandBlock;
import elucent.eidolon.block.HerbBlockBase;
import elucent.eidolon.block.NecroticFocusBlock;
import elucent.eidolon.block.PillarBlockBase;
import elucent.eidolon.block.PipeBlock;
import elucent.eidolon.block.ResearchTableBlock;
import elucent.eidolon.block.SoulEnchanterBlock;
import elucent.eidolon.block.TableBlockBase;
import elucent.eidolon.block.TwoHighBlockBase;
import elucent.eidolon.block.WoodenStandBlock;
import elucent.eidolon.block.WorktableBlock;
import elucent.eidolon.capability.IKnowledge;
import elucent.eidolon.capability.IPlayerData;
import elucent.eidolon.capability.IReputation;
import elucent.eidolon.capability.ISoul;
import elucent.eidolon.capability.KnowledgeCommand;
import elucent.eidolon.entity.AngelArrowEntity;
import elucent.eidolon.entity.AngelArrowRenderer;
import elucent.eidolon.entity.BonechillProjectileEntity;
import elucent.eidolon.entity.ChantCasterEntity;
import elucent.eidolon.entity.NecromancerEntity;
import elucent.eidolon.entity.NecromancerModel;
import elucent.eidolon.entity.NecromancerRenderer;
import elucent.eidolon.entity.NecromancerSpellEntity;
import elucent.eidolon.entity.RavenEntity;
import elucent.eidolon.entity.RavenModel;
import elucent.eidolon.entity.RavenRenderer;
import elucent.eidolon.entity.SlimySlugEntity;
import elucent.eidolon.entity.SlimySlugModel;
import elucent.eidolon.entity.SlimySlugRenderer;
import elucent.eidolon.entity.SoulfireProjectileEntity;
import elucent.eidolon.entity.WraithEntity;
import elucent.eidolon.entity.WraithModel;
import elucent.eidolon.entity.WraithRenderer;
import elucent.eidolon.entity.ZombieBruteEntity;
import elucent.eidolon.entity.ZombieBruteModel;
import elucent.eidolon.entity.ZombieBruteRenderer;
import elucent.eidolon.gui.ResearchTableContainer;
import elucent.eidolon.gui.SoulEnchanterContainer;
import elucent.eidolon.gui.WoodenBrewingStandContainer;
import elucent.eidolon.gui.WorktableContainer;
import elucent.eidolon.item.AthameItem;
import elucent.eidolon.item.BonechillWandItem;
import elucent.eidolon.item.BonelordArmorItem;
import elucent.eidolon.item.CleavingAxeItem;
import elucent.eidolon.item.CodexItem;
import elucent.eidolon.item.CompletedResearchItem;
import elucent.eidolon.item.DeathbringerScytheItem;
import elucent.eidolon.item.ItemBase;
import elucent.eidolon.item.NotetakingToolsItem;
import elucent.eidolon.item.ReaperScytheItem;
import elucent.eidolon.item.ResearchNotesItem;
import elucent.eidolon.item.ReversalPickItem;
import elucent.eidolon.item.SappingSwordItem;
import elucent.eidolon.item.SilverArmorItem;
import elucent.eidolon.item.SoulfireWandItem;
import elucent.eidolon.item.SummoningStaffItem;
import elucent.eidolon.item.Tiers;
import elucent.eidolon.item.TongsItem;
import elucent.eidolon.item.TopHatItem;
import elucent.eidolon.item.UnholySymbolItem;
import elucent.eidolon.item.WarlockRobesItem;
import elucent.eidolon.item.curio.AngelSightItem;
import elucent.eidolon.item.curio.BasicAmuletItem;
import elucent.eidolon.item.curio.BasicBeltItem;
import elucent.eidolon.item.curio.BasicRingItem;
import elucent.eidolon.item.curio.EnervatingRingItem;
import elucent.eidolon.item.curio.GlassHandItem;
import elucent.eidolon.item.curio.GravityBeltItem;
import elucent.eidolon.item.curio.MindShieldingPlateItem;
import elucent.eidolon.item.curio.PrestigiousPalmItem;
import elucent.eidolon.item.curio.RavenCloakItem;
import elucent.eidolon.item.curio.ResoluteBeltItem;
import elucent.eidolon.item.curio.SanguineAmuletItem;
import elucent.eidolon.item.curio.SoulboneAmuletItem;
import elucent.eidolon.item.curio.TerminusMirrorItem;
import elucent.eidolon.item.curio.VoidAmuletItem;
import elucent.eidolon.item.curio.WardedMailItem;
import elucent.eidolon.item.model.SilverArmorModel;
import elucent.eidolon.item.model.TopHatModel;
import elucent.eidolon.item.model.WarlockArmorModel;
import elucent.eidolon.mixin.PotionBrewingMixin;
import elucent.eidolon.particle.BubbleParticleType;
import elucent.eidolon.particle.FlameParticleType;
import elucent.eidolon.particle.GlowingSlashParticleType;
import elucent.eidolon.particle.LineWispParticleType;
import elucent.eidolon.particle.RuneParticleType;
import elucent.eidolon.particle.SignParticleType;
import elucent.eidolon.particle.SlashParticleType;
import elucent.eidolon.particle.SmokeParticleType;
import elucent.eidolon.particle.SparkleParticleType;
import elucent.eidolon.particle.SteamParticleType;
import elucent.eidolon.particle.WispParticleType;
import elucent.eidolon.potion.AnchoredEffect;
import elucent.eidolon.potion.ChilledEffect;
import elucent.eidolon.potion.ReinforcedEffect;
import elucent.eidolon.potion.UndeathEffect;
import elucent.eidolon.potion.VulnerableEffect;
import elucent.eidolon.reagent.Reagent;
import elucent.eidolon.reagent.ReagentRegistry;
import elucent.eidolon.recipe.CrucibleRecipe;
import elucent.eidolon.recipe.WorktableRecipe;
import elucent.eidolon.research.Research;
import elucent.eidolon.ritual.AllureRitual;
import elucent.eidolon.ritual.CrystalRitual;
import elucent.eidolon.ritual.DaylightRitual;
import elucent.eidolon.ritual.DeceitRitual;
import elucent.eidolon.ritual.MoonlightRitual;
import elucent.eidolon.ritual.PurifyRitual;
import elucent.eidolon.ritual.RepellingRitual;
import elucent.eidolon.ritual.SanguineRitual;
import elucent.eidolon.ritual.SummonRitual;
import elucent.eidolon.spell.Sign;
import elucent.eidolon.spell.Signs;
import elucent.eidolon.tile.BrazierTileEntity;
import elucent.eidolon.tile.CrucibleTileEntity;
import elucent.eidolon.tile.CrucibleTileRenderer;
import elucent.eidolon.tile.EffigyTileEntity;
import elucent.eidolon.tile.GobletTileEntity;
import elucent.eidolon.tile.HandTileEntity;
import elucent.eidolon.tile.NecroticFocusTileEntity;
import elucent.eidolon.tile.ResearchTableTileEntity;
import elucent.eidolon.tile.SoulEnchanterTileEntity;
import elucent.eidolon.tile.SoulEnchanterTileRenderer;
import elucent.eidolon.tile.WoodenStandTileEntity;
import elucent.eidolon.tile.reagent.CisternTileEntity;
import elucent.eidolon.tile.reagent.PipeTileEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class Registry {
    public static final TagKey<Item> INGOTS_LEAD = TagKey.create(net.minecraft.core.Registry.ITEM_REGISTRY,
            new ResourceLocation("forge", "ingots/lead"));
    public static final TagKey<Item> INGOTS_PEWTER = TagKey.create(net.minecraft.core.Registry.ITEM_REGISTRY,
            new ResourceLocation("forge", "ingots/pewter"));
    public static final TagKey<Item> INGOTS_ARCANE_GOLD = TagKey.create(net.minecraft.core.Registry.ITEM_REGISTRY,
            new ResourceLocation("forge", "ingots/arcane_gold"));
    public static final TagKey<Item> INGOTS_SILVER = TagKey.create(net.minecraft.core.Registry.ITEM_REGISTRY,
            new ResourceLocation("forge", "ingots/silver"));
    public static final TagKey<Item> GEMS_SHADOW = TagKey.create(net.minecraft.core.Registry.ITEM_REGISTRY,
            new ResourceLocation("forge", "gems/shadow_gem"));

    static Map<String, Block> BLOCK_MAP = new HashMap<>();
    static Map<String, Item> ITEM_MAP = new HashMap<>();
    static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eidolon.MODID);
    static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eidolon.MODID);
    static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Eidolon.MODID);
    static DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
            Eidolon.MODID);
    static DeferredRegister<MobEffect> POTIONS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Eidolon.MODID);
    static DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTIONS, Eidolon.MODID);
    static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            Eidolon.MODID);
    static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
            Eidolon.MODID);
    static DeferredRegister<RecipeSerializer<?>> RECIPE_TYPES = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, Eidolon.MODID);
    static DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Eidolon.MODID);

    public static final TagKey<Item> ZOMBIE_FOOD_TAG = TagKey.create(net.minecraft.core.Registry.ITEM_REGISTRY,
            new ResourceLocation(Eidolon.MODID, "zombie_food"));

    public static Item.Properties itemProps() {
        return new Item.Properties().tab(Eidolon.TAB);
    }
    public static BlockBehaviour.Properties blockProps(Material mat, MaterialColor color) {
        return BlockBehaviour.Properties.of(mat, color);
    }

    /*
    public static class DecoBlockPack {
        DeferredRegister<Block> registry;
        String basename;
        BlockBehaviour.Properties props;
        RegistryObject<Block> full = null, slab = null, stair = null, wall = null, fence = null,
                fence_gate = null;

        public DecoBlockPack(DeferredRegister<Block> blocks, String basename, BlockBehaviour.Properties props) {
            this.registry = blocks;
            this.basename = basename;
            this.props = props;
            full = BLOCKS.register(basename, () -> new Block(props));
            slab = BLOCKS.register(basename + "_slab", () -> new SlabBlock(props));
            stair = BLOCKS.register(basename + "_stairs",
                    () -> new StairBlock(() -> full.get().defaultBlockState(), props));
        }

        public DecoBlockPack addWall() {
            wall = BLOCKS.register(basename + "_wall", () -> new WallBlock(props));
            return this;
        }

        public DecoBlockPack addFence() {
            fence = BLOCKS.register(basename + "_fence", () -> new FenceBlock(props));
            fence = BLOCKS.register(basename + "_fence_gate", () -> new FenceGateBlock(props));
            return this;
        }

        public Block getBlock() {
            return full.get();
        }

        public Block getSlab() {
            return slab.get();
        }

        public Block getStairs() {
            return stair.get();
        }

        public Block getWall() {
            return wall.get();
        }

        public Block getFence() {
            return fence.get();
        }
    }*/

    static <T extends Entity> RegistryObject<EntityType<T>> addEntity(String name, float width, float height, EntityType.EntityFactory<T> factory, MobCategory kind) {
        return ENTITIES.register(name, () -> EntityType.Builder.<T>of(factory, kind)
                .setTrackingRange(64)
                .setUpdateInterval(1)
                .sized(width, height)
                .build(Eidolon.MODID + ":" + name));
    }

    static <T extends Mob> RegistryObject<EntityType<T>> addEntity(String name, int color1, int color2, float width, float height, EntityType.EntityFactory<T> factory, MobCategory kind) {
        var type = ENTITIES.register(name, () ->
                EntityType.Builder.<T>of(factory, kind)
                        .setTrackingRange(64)
                        .setUpdateInterval(1)
                        .sized(width, height)
                        .build(Eidolon.MODID + ":" + name));
        ITEMS.register("spawn_" + name, () -> new ForgeSpawnEggItem(type, color1, color2, itemProps().tab(CreativeModeTab.TAB_MISC)));
        return type;
    }

    public static RegistryObject<SoundEvent> addSound(String name) {
        SoundEvent event = new SoundEvent(new ResourceLocation(Eidolon.MODID, name));
        return SOUND_EVENTS.register(name, () -> event);
    }

    static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> addContainer(String name,
                                                                                      MenuType.MenuSupplier<T> factory) {
        return CONTAINERS.register(name, () -> new MenuType<T>(factory));
    }

    static <T extends BlockEntity> BlockEntityType<T> addTileEntity(IForgeRegistry<BlockEntityType<?>> registry,
                                                                    String name, BlockEntitySupplier<T> factory, Block... blocks) {
        BlockEntityType<T> type = BlockEntityType.Builder.<T>of(factory, blocks).build(null);
        type.setRegistryName(Eidolon.MODID, name);
        registry.register(type);
        return type;
    }

    public static RegistryObject<SoundEvent> CAST_SOULFIRE_EVENT = addSound("cast_soulfire"),
            CAST_BONECHILL_EVENT = addSound("cast_bonechill"),
            SPLASH_SOULFIRE_EVENT = addSound("splash_soulfire"),
            SPLASH_BONECHILL_EVENT = addSound("splash_bonechill"),
            SELECT_RUNE = addSound("select_rune"),
            CHANT_WORD = addSound("chant_word"),
            PAROUSIA = addSound("parousia");

    public static RegistryObject<MobEffect> CHILLED_EFFECT = POTIONS.register("chilled", () -> new ChilledEffect()),
            ANCHORED_EFFECT = POTIONS.register("anchored", () -> new AnchoredEffect()),
            REINFORCED_EFFECT = POTIONS.register("reinforced", () -> new ReinforcedEffect()
                    .addAttributeModifier(Attributes.ARMOR, "483b6415-421e-45d1-ab28-d85d11a19c70", 0.25,
                            Operation.MULTIPLY_TOTAL)),
            VULNERABLE_EFFECT = POTIONS.register("vulnerable", () -> new VulnerableEffect()
                    .addAttributeModifier(Attributes.ARMOR, "e5bae4de-2019-4316-b8cc-b4d879d676f9", -0.25,
                            Operation.MULTIPLY_TOTAL)),
            UNDEATH_EFFECT = POTIONS.register("undeath", () -> new UndeathEffect());

    public static RegistryObject<Potion> CHILLED_POTION = POTION_TYPES.register("chilled",
            () -> new Potion(new MobEffectInstance(CHILLED_EFFECT.get(), 3600))),
            LONG_CHILLED_POTION = POTION_TYPES.register("long_chilled",
                    () -> new Potion(new MobEffectInstance(CHILLED_EFFECT.get(), 9600))),
            ANCHORED_POTION = POTION_TYPES.register("anchored",
                    () -> new Potion(new MobEffectInstance(ANCHORED_EFFECT.get(), 3600))),
            LONG_ANCHORED_POTION = POTION_TYPES.register("long_anchored",
                    () -> new Potion(new MobEffectInstance(ANCHORED_EFFECT.get(), 9600))),
            REINFORCED_POTION = POTION_TYPES.register("reinforced",
                    () -> new Potion(new MobEffectInstance(REINFORCED_EFFECT.get(), 3600))),
            LONG_REINFORCED_POTION = POTION_TYPES.register("long_reinforced",
                    () -> new Potion(new MobEffectInstance(REINFORCED_EFFECT.get(), 9600))),
            STRONG_REINFORCED_POTION = POTION_TYPES.register("strong_reinforced",
                    () -> new Potion(new MobEffectInstance(REINFORCED_EFFECT.get(), 1800, 1))),
            VULNERABLE_POTION = POTION_TYPES.register("vulnerable",
                    () -> new Potion(new MobEffectInstance(VULNERABLE_EFFECT.get(), 3600))),
            LONG_VULNERABLE_POTION = POTION_TYPES.register("long_vulnerable",
                    () -> new Potion(new MobEffectInstance(VULNERABLE_EFFECT.get(), 9600))),
            STRONG_VULNERABLE_POTION = POTION_TYPES.register("strong_vulnerable",
                    () -> new Potion(new MobEffectInstance(VULNERABLE_EFFECT.get(), 1800, 1))),
            UNDEATH_POTION = POTION_TYPES.register("undeath",
                    () -> new Potion(new MobEffectInstance(UNDEATH_EFFECT.get(), 3600))),
            LONG_UNDEATH_POTION = POTION_TYPES.register("long_undeath",
                    () -> new Potion(new MobEffectInstance(UNDEATH_EFFECT.get(), 9600))),
            DECAY_POTION = POTION_TYPES.register("decay",
                    () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 900))),
            LONG_DECAY_POTION = POTION_TYPES.register("long_decay",
                    () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 1800))),
            STRONG_DECAY_POTION = POTION_TYPES.register("strong_decay",
                    () -> new Potion(new MobEffectInstance(MobEffects.WITHER, 450, 1)));

    public static RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(itemProps())),
            RAW_LEAD = ITEMS.register("raw_lead", () -> new Item(itemProps())),
            LEAD_NUGGET = ITEMS.register("lead_nugget", () -> new Item(itemProps())),
            SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(itemProps())),
            RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(itemProps())),
            SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(itemProps())),
            PEWTER_BLEND = ITEMS.register("pewter_blend", () -> new Item(itemProps())),
            PEWTER_INGOT = ITEMS.register("pewter_ingot", () -> new Item(itemProps())),
            PEWTER_NUGGET = ITEMS.register("pewter_nugget", () -> new Item(itemProps())),
            PEWTER_INLAY = ITEMS.register("pewter_inlay", () -> new Item(itemProps())),
            ARCANE_GOLD_INGOT = ITEMS.register("arcane_gold_ingot", () -> new Item(itemProps())),
            ARCANE_GOLD_NUGGET = ITEMS.register("arcane_gold_nugget", () -> new Item(itemProps())),
            ELDER_BRICK = ITEMS.register("elder_brick", () -> new Item(itemProps())),
            SULFUR = ITEMS.register("sulfur", () -> new Item(itemProps())),
            GOLD_INLAY = ITEMS.register("gold_inlay", () -> new Item(itemProps())),
            ZOMBIE_HEART = ITEMS.register("zombie_heart", () -> new ItemBase(itemProps().rarity(Rarity.UNCOMMON).food(
                    new FoodProperties.Builder()
                            .nutrition(2).saturationMod(1.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 1800), 0.875f)
                            .effect(() -> new MobEffectInstance(MobEffects.POISON, 900, 1), 1.0f)
                            .build()))
                    .setLore("lore.eidolon.zombie_heart")),
            TATTERED_CLOTH = ITEMS.register("tattered_cloth", () -> new Item(itemProps())),
            WRAITH_HEART = ITEMS.register("wraith_heart", () -> new ItemBase(itemProps()
                    .rarity(Rarity.UNCOMMON)).setLore("lore.eidolon.wraith_heart")),
            TOP_HAT = ITEMS.register("top_hat", () ->
                    new TopHatItem(itemProps().stacksTo(1).rarity(Rarity.EPIC)).setLore("lore.eidolon.top_hat")),
            BASIC_RING = ITEMS.register("basic_ring", () -> new BasicRingItem(itemProps().stacksTo(1))),
            BASIC_AMULET = ITEMS.register("basic_amulet", () -> new BasicAmuletItem(itemProps().stacksTo(1))),
            BASIC_BELT = ITEMS.register("basic_belt", () -> new BasicBeltItem(itemProps().stacksTo(1))),
            CODEX = ITEMS.register("codex", () ->
                    new CodexItem(itemProps().stacksTo(1).rarity(Rarity.UNCOMMON)).setLore("lore.eidolon.codex")),
            SOUL_SHARD = ITEMS.register("soul_shard", () -> new Item(itemProps())),
            DEATH_ESSENCE = ITEMS.register("death_essence", () -> new Item(itemProps())),
            CRIMSON_ESSENCE = ITEMS.register("crimson_essence", () -> new Item(itemProps())),
            FUNGUS_SPROUTS = ITEMS.register("fungus_sprouts",() -> new Item(
                    itemProps().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1f).build()))),
            WARPED_SPROUTS = ITEMS.register("warped_sprouts",() -> new Item(
                    itemProps().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6f)
                            .effect(() -> new MobEffectInstance(ANCHORED_EFFECT.get(), 900), 1).build()))),
            ENDER_CALX = ITEMS.register("ender_calx", () -> new Item(itemProps())),
            TALLOW = ITEMS.register("tallow", () -> new Item(itemProps())),
            LESSER_SOUL_GEM = ITEMS.register("lesser_soul_gem", () -> new Item(itemProps())),
            UNHOLY_SYMBOL = ITEMS.register("unholy_symbol", () ->
                    new UnholySymbolItem(itemProps().rarity(Rarity.UNCOMMON).stacksTo(1))),
            REAPER_SCYTHE = ITEMS.register("reaper_scythe", () -> new ReaperScytheItem(itemProps().rarity(Rarity.UNCOMMON))
                    .setLore("lore.eidolon.reaper_scythe")),
            CLEAVING_AXE = ITEMS.register("cleaving_axe", () -> new CleavingAxeItem(itemProps().rarity(Rarity.UNCOMMON))
                    .setLore("lore.eidolon.cleaving_axe")),
            SHADOW_GEM = ITEMS.register("shadow_gem", () -> new Item(itemProps())),
            WICKED_WEAVE = ITEMS.register("wicked_weave", () -> new Item(itemProps())),
            WARLOCK_HAT = ITEMS.register("warlock_hat", () -> new WarlockRobesItem(EquipmentSlot.HEAD, itemProps())),
            WARLOCK_CLOAK = ITEMS.register("warlock_cloak", () -> new WarlockRobesItem(EquipmentSlot.CHEST, itemProps())),
            WARLOCK_BOOTS = ITEMS.register("warlock_boots", () -> new WarlockRobesItem(EquipmentSlot.FEET, itemProps())),
            SILVER_HELMET = ITEMS.register("silver_helmet", () -> new SilverArmorItem(EquipmentSlot.HEAD, itemProps())),
            SILVER_CHESTPLATE = ITEMS.register("silver_chestplate", () ->
                    new SilverArmorItem(EquipmentSlot.CHEST, itemProps())),
            SILVER_LEGGINGS = ITEMS.register("silver_leggings", () -> new SilverArmorItem(EquipmentSlot.LEGS, itemProps())),
            SILVER_BOOTS = ITEMS.register("silver_boots", () -> new SilverArmorItem(EquipmentSlot.FEET, itemProps())),
            SILVER_SWORD = ITEMS.register("silver_sword", () ->
                    new SwordItem(Tiers.SilverTier.INSTANCE, 3, -2.4f, itemProps())),
            SILVER_PICKAXE = ITEMS.register("silver_pickaxe", () ->
                    new PickaxeItem(Tiers.SilverTier.INSTANCE, 1, -2.4f, itemProps())),
            SILVER_AXE = ITEMS.register("silver_axe", () -> new AxeItem(Tiers.SilverTier.INSTANCE, 6, -2.4f, itemProps())),
            SILVER_SHOVEL = ITEMS.register("silver_shovel", () ->
                    new ShovelItem(Tiers.SilverTier.INSTANCE, 1.5f, -2.4f, itemProps())),
            SILVER_HOE = ITEMS.register("silver_hoe", () -> new HoeItem(Tiers.SilverTier.INSTANCE, 0, -2.4f, itemProps())),
            ATHAME = ITEMS.register("athame", () -> new AthameItem(itemProps().stacksTo(1))),
            REVERSAL_PICK = ITEMS.register("reversal_pick", () -> new ReversalPickItem(itemProps()
                    .rarity(Rarity.UNCOMMON))),
            VOID_AMULET = ITEMS.register("void_amulet", () -> new VoidAmuletItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.void_amulet")),
            WARDED_MAIL = ITEMS.register("warded_mail", () -> new WardedMailItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.warded_mail")),
            SAPPING_SWORD = ITEMS.register("sapping_sword", () -> new SappingSwordItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.sapping_sword")),
            SANGUINE_AMULET = ITEMS.register("sanguine_amulet", () -> new SanguineAmuletItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.sanguine_amulet")),
            ENERVATING_RING = ITEMS.register("enervating_ring", () -> new EnervatingRingItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.enervating_ring")),
            SOULFIRE_WAND = ITEMS.register("soulfire_wand", () -> new SoulfireWandItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1).durability(253).setNoRepair())
                    .setLore("lore.eidolon.soulfire_wand")),
            BONECHILL_WAND = ITEMS.register("bonechill_wand", () -> new BonechillWandItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1).durability(253).setNoRepair())
                    .setLore("lore.eidolon.bonechill_wand")),
            GRAVITY_BELT = ITEMS.register("gravity_belt", () -> new GravityBeltItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.gravity_belt")),
            RESOLUTE_BELT = ITEMS.register("resolute_belt", () -> new ResoluteBeltItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.resolute_belt")),
            PRESTIGIOUS_PALM = ITEMS.register("prestigious_palm", () -> new PrestigiousPalmItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.prestigious_palm")),
            MIND_SHIELDING_PLATE = ITEMS.register("mind_shielding_plate", () -> new MindShieldingPlateItem(itemProps()
                    .rarity(Rarity.UNCOMMON).stacksTo(1)).setLore("lore.eidolon.mind_shielding_plate")),
            GLASS_HAND = ITEMS.register("glass_hand", () -> new GlassHandItem(itemProps()
                    .rarity(Rarity.RARE).stacksTo(1)).setLore("lore.eidolon.glass_hand")),
            TERMINUS_MIRROR = ITEMS.register("terminus_mirror", () -> new TerminusMirrorItem(itemProps()
                    .rarity(Rarity.RARE).stacksTo(1)).setLore("lore.eidolon.terminus_mirror")),
            ANGELS_SIGHT = ITEMS.register("angels_sight", () -> new AngelSightItem(itemProps()
                    .rarity(Rarity.RARE).stacksTo(1)).setLore("lore.eidolon.angels_sight")),
            WITHERED_HEART = ITEMS.register("withered_heart", () -> new ItemBase(itemProps().rarity(Rarity.RARE).food(
                    new FoodProperties.Builder()
                            .nutrition(2).saturationMod(1.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 1800), 0.875f)
                            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 900, 1), 1.0f)
                            .build()))
                    .setLore("lore.eidolon.withered_heart")),
            IMBUED_BONES = ITEMS.register("imbued_bones", () -> new Item(itemProps().rarity(Rarity.UNCOMMON))),
            SUMMONING_STAFF = ITEMS.register("summoning_staff", () ->
                    new SummoningStaffItem(itemProps().rarity(Rarity.RARE))),
            DEATHBRINGER_SCYTHE = ITEMS.register("deathbringer_scythe", () ->
                    new DeathbringerScytheItem(itemProps().rarity(Rarity.RARE))
                            .setLore("lore.eidolon.deathbringer_scythe")),
            SOULBONE_AMULET = ITEMS.register("soulbone_amulet", () -> new SoulboneAmuletItem(itemProps()
                    .rarity(Rarity.RARE).stacksTo(1)).setLore("lore.eidolon.soulbone_amulet")),
            BONELORD_HELM = ITEMS.register("bonelord_helm", () ->
                    new BonelordArmorItem(EquipmentSlot.HEAD, itemProps().rarity(Rarity.RARE))),
            BONELORD_CHESTPLATE = ITEMS.register("bonelord_chestplate", () ->
                    new BonelordArmorItem(EquipmentSlot.CHEST, itemProps().rarity(Rarity.RARE))),
            BONELORD_GREAVES = ITEMS.register("bonelord_greaves", () ->
                    new BonelordArmorItem(EquipmentSlot.LEGS, itemProps().rarity(Rarity.RARE))),
            PAROUSIA_DISC = ITEMS.register("music_disc_parousia", () -> new RecordItem(9, () -> PAROUSIA.get(),
                    itemProps().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE))),
            RAVEN_FEATHER = ITEMS.register("raven_feather", () -> new Item(itemProps())),
            RAVEN_CLOAK = ITEMS.register("raven_cloak", () -> new RavenCloakItem(itemProps().stacksTo(1).rarity(Rarity.RARE))),
            ALCHEMISTS_TONGS = ITEMS.register("alchemists_tongs", () -> new TongsItem(itemProps().stacksTo(1))),
            MERAMMER_RESIN = ITEMS.register("merammer_resin", () -> new Item(itemProps())),
            MAGIC_INK = ITEMS.register("magic_ink", () -> new Item(itemProps())),
            MAGICIANS_WAX = ITEMS.register("magicians_wax", () -> new Item(itemProps())),
            ARCANE_SEAL = ITEMS.register("arcane_seal", () -> new Item(itemProps())),
            PARCHMENT = ITEMS.register("parchment", () -> new Item(itemProps())),
            NOTETAKING_TOOLS = ITEMS.register("notetaking_tools", () -> new NotetakingToolsItem(itemProps().stacksTo(16))),
            RESEARCH_NOTES = ITEMS.register("research_notes", () ->
                    new ResearchNotesItem(itemProps().rarity(Rarity.UNCOMMON).stacksTo(1))),
            COMPLETED_RESEARCH = ITEMS.register("completed_research", () ->
                    new CompletedResearchItem(itemProps().rarity(Rarity.UNCOMMON).stacksTo(1))),
            RED_CANDY = ITEMS.register("red_candy", () -> new ItemBase(itemProps().rarity(Rarity.COMMON).food(
                    new FoodProperties.Builder()
                            .nutrition(2).saturationMod(2)
                            .build()))
                    .setLore(ChatFormatting.RED, "lore.eidolon.red_candy")),
            GRAPE_CANDY = ITEMS.register("grape_candy", () -> new ItemBase(itemProps().rarity(Rarity.COMMON).food(
                    new FoodProperties.Builder()
                            .nutrition(2).saturationMod(2)
                            .build()))
                    .setLore(ChatFormatting.LIGHT_PURPLE, "lore.eidolon.grape_candy"));

    public static RegistryObject<Block> LEAD_ORE = BLOCKS.register("lead_ore",
            () -> new Block(blockProps(Material.STONE, MaterialColor.STONE)
                    .sound(SoundType.STONE).strength(2.8f, 3.0f).requiresCorrectToolForDrops())),
            DEEP_LEAD_ORE = BLOCKS.register("deep_lead_ore",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.DEEPSLATE)
                            .sound(SoundType.DEEPSLATE).strength(3.2f, 3.0f).requiresCorrectToolForDrops())),
            LEAD_BLOCK = BLOCKS.register("lead_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.TERRACOTTA_PURPLE)
                            .sound(SoundType.METAL).strength(3.0f, 3.0f).requiresCorrectToolForDrops())),
            RAW_LEAD_BLOCK = BLOCKS.register("raw_lead_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.TERRACOTTA_PURPLE)
                            .sound(SoundType.DEEPSLATE).strength(2.4f, 3.0f).requiresCorrectToolForDrops())),
            SILVER_ORE = BLOCKS.register("silver_ore", () -> new Block(blockProps(Material.STONE, MaterialColor.STONE)
                    .sound(SoundType.STONE).strength(3.2f, 3.0f).requiresCorrectToolForDrops())),
            DEEP_SILVER_ORE = BLOCKS.register("deep_silver_ore",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.DEEPSLATE)
                            .sound(SoundType.DEEPSLATE).strength(3.6f, 3.0f).requiresCorrectToolForDrops())),
            SILVER_BLOCK = BLOCKS.register("silver_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE)
                            .sound(SoundType.METAL).strength(3.0f, 3.0f).requiresCorrectToolForDrops())),
            RAW_SILVER_BLOCK = BLOCKS.register("raw_silver_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE)
                            .sound(SoundType.STONE).strength(2.4f, 3.0f).requiresCorrectToolForDrops())),
            PEWTER_BLOCK = BLOCKS.register("pewter_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY)
                            .sound(SoundType.METAL).strength(4.0f, 4.0f).requiresCorrectToolForDrops())),
            ARCANE_GOLD_BLOCK = BLOCKS.register("arcane_gold_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.GOLD)
                            .sound(SoundType.METAL).strength(3.0f, 4.0f).requiresCorrectToolForDrops())),
            SHADOW_GEM_BLOCK = BLOCKS.register("shadow_gem_block",
                    () -> new Block(blockProps(Material.STONE, MaterialColor.COLOR_PURPLE)
                            .sound(SoundType.METAL).strength(3.0f, 4.0f).requiresCorrectToolForDrops())),
            WOODEN_ALTAR = BLOCKS.register("wooden_altar",
                    () -> new TableBlockBase(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.6f, 3.0f))),
            STONE_ALTAR = BLOCKS.register("stone_altar",
                    () -> new TableBlockBase(blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.8f, 3.0f)
                            .requiresCorrectToolForDrops().noOcclusion())
                            .setMainShape(Shapes.or(
                                    Shapes.box(0, 0.375, 0, 1, 1, 1),
                                    Shapes.box(0.0625, 0.125, 0.0625, 0.9375, 0.375, 0.9375)))),
            CANDLE = BLOCKS.register("candle",
                    () -> new CandleBlock(blockProps(Material.DECORATION, MaterialColor.TERRACOTTA_WHITE)
                            .sound(SoundType.STONE).lightLevel((state) -> 15).strength(0.6f, 0.8f).noOcclusion())),
            CANDLESTICK = BLOCKS.register("candlestick",
                    () -> new CandlestickBlock(blockProps(Material.METAL, MaterialColor.GOLD)
                            .sound(SoundType.STONE).lightLevel((state) -> 15).strength(1.2f, 2.0f).noOcclusion())),
            MAGIC_CANDLE = BLOCKS.register("magic_candle",
                    () -> new CandleBlock(blockProps(Material.DECORATION, MaterialColor.TERRACOTTA_RED)
                            .sound(SoundType.STONE).lightLevel((state) -> 15).strength(0.6f, 0.8f).noOcclusion())),
            MAGIC_CANDLESTICK = BLOCKS.register("magic_candlestick",
                    () -> new CandlestickBlock(blockProps(Material.DECORATION, MaterialColor.GOLD)
                            .sound(SoundType.STONE).lightLevel((state) -> 15).strength(1.2f, 2.0f).noOcclusion())),
            STRAW_EFFIGY = BLOCKS.register("straw_effigy",
                    () -> new EffigyBlock(blockProps(Material.PLANT, MaterialColor.COLOR_YELLOW)
                            .sound(SoundType.WOOD).strength(1.4f, 2.0f)
                            .noOcclusion()).setShape(
                            Shapes.box(0.28125, 0, 0.28125, 0.71875, 1, 0.71875))),
            GOBLET = BLOCKS.register("goblet", () -> new GobletBlock(blockProps(Material.METAL, MaterialColor.GOLD)
                    .sound(SoundType.METAL).strength(1.4f, 2.0f).requiresCorrectToolForDrops()
                    .noOcclusion()).setShape(Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.5, 0.6875))),
            UNHOLY_EFFIGY = BLOCKS.register("unholy_effigy",
                    () -> new EffigyBlock(blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.8f, 3.0f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()).setShape(
                            Shapes.box(0.25, 0, 0.25, 0.75, 1, 0.75))),
            WORKTABLE = BLOCKS.register("worktable",
                    () -> new WorktableBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.6f, 3.0f)
                            .noOcclusion()).setShape(Shapes.or(
                            Shapes.box(0, 0, 0, 1, 0.25, 1),
                            Shapes.box(0.125, 0.25, 0.125, 0.875, 0.625, 0.875),
                            Shapes.box(0, 0.625, 0, 1, 1, 1)))),
            RESEARCH_TABLE = BLOCKS.register("research_table",
                    () -> new ResearchTableBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.6f, 3.0f)
                            .noOcclusion()).setShape(Shapes.or(
                            Shapes.box(0, 0, 0, 1, 0.25, 1),
                            Shapes.box(0.125, 0.25, 0.125, 0.875, 0.625, 0.875),
                            Shapes.box(0, 0.625, 0, 1, 1, 1)))),
            PLINTH = BLOCKS.register("plinth", () -> new PillarBlockBase(blockProps(Material.STONE, MaterialColor.STONE)
                    .sound(SoundType.STONE).strength(2.0f, 3.0f)
                    .requiresCorrectToolForDrops().noOcclusion())
                    .setShape(Shapes.box(0.25, 0, 0.25, 0.75, 1, 0.75))),
            OBELISK = BLOCKS.register("obelisk",
                    () -> new PillarBlockBase(blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.0f, 3.0f)
                            .requiresCorrectToolForDrops().noOcclusion())
                            .setShape(Shapes.box(0.125, 0, 0.125, 0.875, 1, 0.875))),
            BRAZIER = BLOCKS.register("brazier", () -> new BrazierBlock(blockProps(Material.WOOD, MaterialColor.METAL)
                    .sound(SoundType.METAL).strength(2.5f, 3.0f)
                    .noOcclusion())
                    .setShape(Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.75, 0.8125))),
            CRUCIBLE = BLOCKS.register("crucible",
                    () -> new CrucibleBlock(blockProps(Material.METAL, MaterialColor.METAL)
                            .sound(SoundType.METAL).strength(4.0f, 3.0f)
                            .requiresCorrectToolForDrops().noOcclusion())
                            .setShape(Shapes.or(
                                    Shapes.box(0.0625, 0.875, 0.0625, 0.1875, 1, 0.9375),
                                    Shapes.box(0.8125, 0.875, 0.0625, 0.9375, 1, 0.9375),
                                    Shapes.box(0.0625, 0.875, 0.0625, 0.9375, 1, 0.1875),
                                    Shapes.box(0.0625, 0.875, 0.8125, 0.9375, 1, 0.9375),
                                    Shapes.box(0, 0.125, 0, 0.125, 0.875, 1),
                                    Shapes.box(0.875, 0.125, 0, 1, 0.875, 1),
                                    Shapes.box(0, 0.125, 0, 1, 0.875, 0.125),
                                    Shapes.box(0, 0.125, 0.875, 1, 0.875, 1),
                                    Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.125, 0.9375)))),
            STONE_HAND = BLOCKS.register("stone_hand",
                    () -> new HandBlock(blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.0f, 3.0f)
                            .requiresCorrectToolForDrops().noOcclusion())
                            .setShape(Shapes.box(0.25, 0, 0.25, 0.75, 0.75, 0.75))),
            ENCHANTED_ASH = BLOCKS.register("enchanted_ash",
                    () -> new EnchantedAshBlock(blockProps(Material.DECORATION, MaterialColor.TERRACOTTA_WHITE)
                            .sound(SoundType.STONE).strength(0.0f, 0.75f).noOcclusion())
                            .setShape(Shapes.empty())),
            NECROTIC_FOCUS = BLOCKS.register("necrotic_focus",
                    () -> new NecroticFocusBlock(blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.8f, 3.0f)
                            .requiresCorrectToolForDrops().noOcclusion())
                            .setShape(Shapes.box(0.25, 0, 0.25, 0.75, 0.75, 0.75))),
            PLANTER = BLOCKS.register("planter", () -> new BlockBase(blockProps(Material.WOOD, MaterialColor.WOOD)
                    .sound(SoundType.WOOD).strength(2.0f, 3.0f)
                    .noOcclusion())
                    .setShape(Shapes.or(
                            Shapes.box(0, 0.25, 0, 1, 1, 1),
                            Shapes.box(0.25, 0, 0.25, 0.75, 0.25, 0.75)))),
            MERAMMER_ROOT = BLOCKS.register("merammer_root",
                    () -> new HerbBlockBase(blockProps(Material.PLANT, MaterialColor.GRASS)
                            .sound(SoundType.GRASS).noOcclusion())),
            AVENNIAN_SPRIG = BLOCKS.register("avennian_sprig",
                    () -> new HerbBlockBase(blockProps(Material.PLANT, MaterialColor.GRASS)
                            .sound(SoundType.GRASS).noOcclusion())),
            OANNA_BLOOM = BLOCKS.register("oanna_bloom",
                    () -> new HerbBlockBase(blockProps(Material.PLANT, MaterialColor.GRASS)
                            .sound(SoundType.GRASS).noOcclusion())),
            SILDRIAN_SEED = BLOCKS.register("sildrian_seed",
                    () -> new HerbBlockBase(blockProps(Material.PLANT, MaterialColor.GRASS)
                            .sound(SoundType.GRASS).noOcclusion())),
            ILLWOOD_SAPLING = BLOCKS.register("illwood_sapling",
                    () -> new BushBlock(blockProps(Material.PLANT, MaterialColor.GRASS)
                            .sound(SoundType.GRASS).noOcclusion().noCollission())),
            ILLWOOD_LEAVES = BLOCKS.register("illwood_leaves",
                    () -> new BlockBase(blockProps(Material.PLANT, MaterialColor.GRASS)
                            .sound(SoundType.GRASS).noOcclusion())),
            ILLWOOD_LOG = BLOCKS.register("illwood_log",
                    () -> new RotatedPillarBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.6f, 3.0f))),
            ILLWOOD_BARK = BLOCKS.register("illwood_bark",
                    () -> new RotatedPillarBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.6f, 3.0f))),
            STRIPPED_ILLWOOD_LOG = BLOCKS.register("stripped_illwood_log",
                    () -> new RotatedPillarBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.4f, 3.0f))),
            STRIPPED_ILLWOOD_BARK = BLOCKS.register("stripped_illwood_bark",
                    () -> new RotatedPillarBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                            .sound(SoundType.WOOD).strength(1.4f, 3.0f))),
            SOUL_ENCHANTER = BLOCKS.register("soul_enchanter",
                    () -> new SoulEnchanterBlock(blockProps(Material.STONE, MaterialColor.PODZOL)
                            .sound(SoundType.STONE).strength(5.0f, 1200.0f)
                            .requiresCorrectToolForDrops().noOcclusion())
                            .setShape(Shapes.box(0, 0, 0, 1, 0.75, 1))),
            WOODEN_STAND = BLOCKS.register("wooden_brewing_stand",
                    () -> new WoodenStandBlock(blockProps(Material.METAL, MaterialColor.WOOD)
                            .sound(SoundType.STONE).strength(2.0f, 3.0f)
                            .noOcclusion())),
            INCUBATOR = BLOCKS.register("incubator",
                    () -> new TwoHighBlockBase(blockProps(Material.METAL, MaterialColor.METAL)
                            .sound(SoundType.GLASS).strength(2.0f, 3.0f)
                            .noOcclusion()).setShape(Shapes.box(0.0625, 0, 0.0625, 0.9375, 1, 0.9375))),
            GLASS_TUBE = BLOCKS.register("glass_tube",
                    () -> new PipeBlock(blockProps(Material.GLASS, MaterialColor.COLOR_LIGHT_BLUE)
                            .sound(SoundType.GLASS).strength(1.0f, 1.5f).noOcclusion())),
            CISTERN = BLOCKS.register("cistern",
                    () -> new CisternBlock(blockProps(Material.GLASS, MaterialColor.COLOR_LIGHT_BLUE)
                            .sound(SoundType.GLASS).strength(1.5f, 1.5f).noOcclusion())
                            .setShape(Shapes.box(0.0625, 0, 0.0625, 0.9375, 1, 0.9375)));
    /*
    public static DecoBlockPack SMOOTH_STONE_BRICK = new DecoBlockPack(BLOCKS, "smooth_stone_bricks",
            blockProps(Material.STONE, MaterialColor.STONE)
                    .sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f, 3.0f))
            .addWall(),
            SMOOTH_STONE_TILES = new DecoBlockPack(BLOCKS, "smooth_stone_tiles",
                    blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.0f, 3.0f)),
            SMOOTH_STONE_MASONRY = new DecoBlockPack(BLOCKS, "smooth_stone_masonry",
                    blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.6f, 3.0f)),
            POLISHED_PLANKS = new DecoBlockPack(BLOCKS, "polished_planks", blockProps(Material.WOOD, MaterialColor.WOOD)
                    .sound(SoundType.WOOD).strength(1.6f, 3.0f))
                    .addFence(),
            ILLWOOD_PLANKS = new DecoBlockPack(BLOCKS, "illwood_planks", blockProps(Material.WOOD, MaterialColor.WOOD)
                    .sound(SoundType.WOOD).strength(1.6f, 3.0f))
                    .addFence(),
            ELDER_BRICKS = new DecoBlockPack(BLOCKS, "elder_bricks",
                    blockProps(Material.STONE, MaterialColor.TERRACOTTA_ORANGE)
                            .sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.0f, 3.0f))
                    .addWall(),
            ELDER_MASONRY = new DecoBlockPack(BLOCKS, "elder_masonry",
                    blockProps(Material.STONE, MaterialColor.TERRACOTTA_ORANGE)
                            .sound(SoundType.STONE).requiresCorrectToolForDrops().strength(2.4f, 3.0f)),
            BONE_PILE = new DecoBlockPack(BLOCKS, "bone_pile", blockProps(Material.STONE, MaterialColor.QUARTZ)
                    .sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().strength(1.6f, 3.0f));
                    */
    public static RegistryObject<Block> POLISHED_WOOD_PILLAR = BLOCKS.register("polished_wood_pillar",
            () -> new RotatedPillarBlock(blockProps(Material.WOOD, MaterialColor.WOOD)
                    .strength(1.6f, 3.0f))),
            SMOOTH_STONE_ARCH = BLOCKS.register("smooth_stone_arch",
                    () -> new PillarBlockBase(blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.0f, 3.0f).requiresCorrectToolForDrops())),
            MOSSY_SMOOTH_STONE_BRICKS = BLOCKS.register("mossy_smooth_stone_bricks", () -> new Block(
                    blockProps(Material.STONE, MaterialColor.STONE)
                            .sound(SoundType.STONE).strength(2.0f, 3.0f).requiresCorrectToolForDrops())),
            ELDER_BRICKS_EYE = BLOCKS.register("elder_bricks_eye", () -> new Block(
                    blockProps(Material.STONE, MaterialColor.TERRACOTTA_ORANGE)
                            .sound(SoundType.STONE).strength(3.0f, 3.0f).requiresCorrectToolForDrops())),
            ELDER_PILLAR = BLOCKS.register("elder_pillar",
                    () -> new PillarBlockBase(blockProps(Material.STONE, MaterialColor.TERRACOTTA_ORANGE)
                            .sound(SoundType.STONE).strength(3.0f, 3.0f)
                            .requiresCorrectToolForDrops()));
    public static RegistryObject<Item> LEAD_ORE_ITEM = fromBlock(LEAD_ORE),
            DEEP_LEAD_ORE_ITEM = fromBlock(DEEP_LEAD_ORE),
            LEAD_BLOCK_ITEM = fromBlock(LEAD_BLOCK),
            RAW_LEAD_BLOCK_ITEM = fromBlock(RAW_LEAD_BLOCK),
            SILVER_ORE_ITEM = fromBlock(SILVER_ORE),
            DEEP_SILVER_ORE_ITEM = fromBlock(DEEP_SILVER_ORE),
            SILVER_BLOCK_ITEM = fromBlock(SILVER_BLOCK),
            RAW_SILVER_BLOCK_ITEM = fromBlock(RAW_SILVER_BLOCK),
            PEWTER_BLOCK_ITEM = fromBlock(PEWTER_BLOCK),
            ARCANE_GOLD_BLOCK_ITEM = fromBlock(ARCANE_GOLD_BLOCK),
            SHADOW_GEM_BLOCK_ITEM = fromBlock(SHADOW_GEM_BLOCK),
            WOODEN_ALTAR_ITEM = fromBlock(WOODEN_ALTAR),
            STONE_ALTAR_ITEM = fromBlock(STONE_ALTAR),
            CANDLE_ITEM = fromBlock(CANDLE),
            CANDLESTICK_ITEM = fromBlock(CANDLESTICK),
            MAGIC_CANDLE_ITEM = fromBlock(MAGIC_CANDLE),
            MAGIC_CANDLESTICK_ITEM = fromBlock(MAGIC_CANDLESTICK),
            STRAW_EFFIGY_ITEM = fromBlock(STRAW_EFFIGY),
            GOBLET_ITEM = fromBlock(GOBLET),
            UNHOLY_EFFIGY_ITEM = fromBlock(UNHOLY_EFFIGY),
            WORKTABLE_ITEM = fromBlock(WORKTABLE),
            RESEARCH_TABLE_ITEM = fromBlock(RESEARCH_TABLE),
            PLINTH_ITEM = fromBlock(PLINTH),
            OBELISK_ITEM = fromBlock(OBELISK),
            BRAZIER_ITEM = fromBlock(BRAZIER),
            CRUCIBLE_ITEM = fromBlock(CRUCIBLE),
            STONE_HAND_ITEM = fromBlock(STONE_HAND),
            ENCHANTED_ASH_ITEM = fromBlock(ENCHANTED_ASH),
            NECROTIC_FOCUS_ITEM = fromBlock(NECROTIC_FOCUS),
            PLANTER_ITEM = fromBlock(PLANTER),
            MERAMMER_ROOT_ITEM = fromBlock(MERAMMER_ROOT),
            AVENNIAN_SPRIG_ITEM = fromBlock(AVENNIAN_SPRIG),
            OANNA_BLOOM_ITEM = fromBlock(OANNA_BLOOM),
            SILDRIAN_SEED_ITEM = fromBlock(SILDRIAN_SEED),
            ILLWOOD_SAPLING_ITEM = fromBlock(ILLWOOD_SAPLING),
            ILLWOOD_LEAVES_ITEM = fromBlock(ILLWOOD_LEAVES),
            ILLWOOD_LOG_ITEM = fromBlock(ILLWOOD_LOG),
            ILLWOOD_BARK_ITEM = fromBlock(ILLWOOD_BARK),
            STRIPPED_ILLWOOD_LOG_ITEM = fromBlock(STRIPPED_ILLWOOD_LOG),
            STRIPPED_ILLWOOD_BARK_ITEM = fromBlock(STRIPPED_ILLWOOD_BARK),
            SOUL_ENCHANTER_ITEM = fromBlock(SOUL_ENCHANTER),
            WOODEN_STAND_ITEM = fromBlock(WOODEN_STAND),
            INCUBATOR_ITEM = fromBlock(INCUBATOR),
            GLASS_TUBE_ITEM = fromBlock(GLASS_TUBE),
            CISTERN_ITEM = fromBlock(CISTERN),
            POLISHED_WOOD_PILLAR_ITEM = fromBlock(POLISHED_WOOD_PILLAR),
            SMOOTH_STONE_ARCH_ITEM = fromBlock(SMOOTH_STONE_ARCH),
            MOSSY_SMOOTH_STONE_BRICKS_ITEM = fromBlock(MOSSY_SMOOTH_STONE_BRICKS),
            ELDER_BRICKS_EYE_ITEM = fromBlock(ELDER_BRICKS_EYE),
            ELDER_PILLAR_ITEM = fromBlock(ELDER_PILLAR);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), itemProps()));
    }

    public static RegistryObject<EntityType<ZombieBruteEntity>> ZOMBIE_BRUTE = addEntity("zombie_brute", 7969893, 44975,
            1.2f, 2.5f, ZombieBruteEntity::new, MobCategory.MONSTER);
    public static RegistryObject<EntityType<WraithEntity>> WRAITH = addEntity("wraith", 0x706e6b, 0xadacbd, 0.6f, 1.9f,
            WraithEntity::new, MobCategory.MONSTER);
    public static RegistryObject<EntityType<SoulfireProjectileEntity>> SOULFIRE_PROJECTILE = addEntity(
            "soulfire_projectile", 0.4f, 0.4f, SoulfireProjectileEntity::new, MobCategory.MISC);
    public static RegistryObject<EntityType<BonechillProjectileEntity>> BONECHILL_PROJECTILE = addEntity(
            "bonechill_projectile", 0.4f, 0.4f, BonechillProjectileEntity::new, MobCategory.MISC);
    public static RegistryObject<EntityType<NecromancerSpellEntity>> NECROMANCER_SPELL = addEntity("necromancer_spell",
            0.4f, 0.4f, NecromancerSpellEntity::new, MobCategory.MISC);
    public static RegistryObject<EntityType<ChantCasterEntity>> CHANT_CASTER = addEntity("chant_caster", 0.1f, 0.1f,
            ChantCasterEntity::new, MobCategory.MISC);
    public static RegistryObject<EntityType<AngelArrowEntity>> ANGEL_ARROW = addEntity("angel_arrow", 0.5f, 0.5f,
            AngelArrowEntity::new, MobCategory.MISC);
    public static RegistryObject<EntityType<NecromancerEntity>> NECROMANCER = addEntity("necromancer", 0x69255e,
            0x9ce8ff, 0.6f, 1.9f, NecromancerEntity::new, MobCategory.MONSTER);
    public static RegistryObject<EntityType<RavenEntity>> RAVEN = addEntity("raven", 0x1e1f24, 0x404f66, 0.375f, 0.5f,
            RavenEntity::new, MobCategory.CREATURE);
    public static RegistryObject<EntityType<SlimySlugEntity>> SLIMY_SLUG = addEntity("slimy_slug", 0xdbe388, 0x5f9e42,
            0.5f, 0.25f, SlimySlugEntity::new, MobCategory.CREATURE);

    public static RegistryObject<MenuType<WorktableContainer>> WORKTABLE_CONTAINER = addContainer("worktable",
            WorktableContainer::new);
    public static RegistryObject<MenuType<SoulEnchanterContainer>> SOUL_ENCHANTER_CONTAINER = addContainer(
            "soul_enchanter", SoulEnchanterContainer::new);
    public static RegistryObject<MenuType<WoodenBrewingStandContainer>> WOODEN_STAND_CONTAINER = addContainer(
            "wooden_brewing_stand", WoodenBrewingStandContainer::new);
    public static RegistryObject<MenuType<ResearchTableContainer>> RESEARCH_TABLE_CONTAINER = addContainer(
            "research_table", ResearchTableContainer::new);

    public static RegistryObject<RecipeSerializer<WorktableRecipe>> WORKTABLE_RECIPE = RECIPE_TYPES
            .register("worktable", () -> new WorktableRecipe.Serializer());
    public static RegistryObject<RecipeSerializer<CrucibleRecipe>> CRUCIBLE_RECIPE = RECIPE_TYPES.register("crucible",
            () -> new CrucibleRecipe.Serializer());

    public static RegistryObject<Attribute> MAX_SOUL_HEARTS = ATTRIBUTES.register("max_soul_hearts",
            () -> new RangedAttribute(Eidolon.MODID + ".max_soul_hearts", Config.MAX_ETHEREAL_HEALTH.get(), 0, 2000)
                    .setSyncable(true)),
            PERSISTENT_SOUL_HEARTS = ATTRIBUTES.register("persistent_soul_hearts",
                    () -> new RangedAttribute(Eidolon.MODID + ".persistent_soul_hearts", 0, 0, 2000).setSyncable(true));

    @SubscribeEvent
    public void addCustomAttributes(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> t : event.getTypes()) {
            if (event.has(t, Attributes.MAX_HEALTH)) {
                event.add(t, Registry.PERSISTENT_SOUL_HEARTS.get());
                event.add(t, Registry.MAX_SOUL_HEARTS.get());
            }
        }
    }

    public static void init() {
        ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        POTION_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void addBrewingRecipes() {
        PotionBrewingMixin.callAddMix(Potions.WATER, Registry.FUNGUS_SPROUTS.get(), Potions.AWKWARD);
        PotionBrewingMixin.callAddMix(Potions.AWKWARD, Registry.WRAITH_HEART.get(), Registry.CHILLED_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.CHILLED_POTION.get(), Items.REDSTONE,
                Registry.LONG_CHILLED_POTION.get());
        PotionBrewingMixin.callAddMix(Potions.AWKWARD, Registry.WARPED_SPROUTS.get(), Registry.ANCHORED_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.ANCHORED_POTION.get(), Items.REDSTONE,
                Registry.LONG_ANCHORED_POTION.get());
        PotionBrewingMixin.callAddMix(Potions.AWKWARD, Items.NAUTILUS_SHELL, Registry.REINFORCED_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.REINFORCED_POTION.get(), Items.REDSTONE,
                Registry.LONG_REINFORCED_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.REINFORCED_POTION.get(), Items.GLOWSTONE,
                Registry.STRONG_REINFORCED_POTION.get());
        PotionBrewingMixin.callAddMix(Potions.AWKWARD, Registry.TATTERED_CLOTH.get(), Registry.VULNERABLE_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.VULNERABLE_POTION.get(), Items.REDSTONE,
                Registry.LONG_VULNERABLE_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.VULNERABLE_POTION.get(), Items.GLOWSTONE,
                Registry.STRONG_VULNERABLE_POTION.get());
        PotionBrewingMixin.callAddMix(Potions.AWKWARD, Registry.DEATH_ESSENCE.get(), Registry.UNDEATH_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.UNDEATH_POTION.get(), Items.REDSTONE,
                Registry.LONG_UNDEATH_POTION.get());
        PotionBrewingMixin.callAddMix(Potions.AWKWARD, Registry.WITHERED_HEART.get(), Registry.DECAY_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.DECAY_POTION.get(), Items.REDSTONE, Registry.LONG_DECAY_POTION.get());
        PotionBrewingMixin.callAddMix(Registry.DECAY_POTION.get(), Items.GLOWSTONE, Registry.STRONG_DECAY_POTION.get());
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientInit() {
    }

    public static BlockEntityType<HandTileEntity> HAND_TILE_ENTITY;
    public static BlockEntityType<BrazierTileEntity> BRAZIER_TILE_ENTITY;
    public static BlockEntityType<NecroticFocusTileEntity> NECROTIC_FOCUS_TILE_ENTITY;
    public static BlockEntityType<CrucibleTileEntity> CRUCIBLE_TILE_ENTITY;
    public static BlockEntityType<EffigyTileEntity> EFFIGY_TILE_ENTITY;
    public static BlockEntityType<SoulEnchanterTileEntity> SOUL_ENCHANTER_TILE_ENTITY;
    public static BlockEntityType<WoodenStandTileEntity> WOODEN_STAND_TILE_ENTITY;
    public static BlockEntityType<GobletTileEntity> GOBLET_TILE_ENTITY;
    public static BlockEntityType<CisternTileEntity> CISTERN_TILE_ENTITY;
    public static BlockEntityType<PipeTileEntity> PIPE_TILE_ENTITY;
    public static BlockEntityType<ResearchTableTileEntity> RESEARCH_TABLE_TILE_ENTITY;

    @SubscribeEvent
    public void registerTiles(RegistryEvent.Register<BlockEntityType<?>> evt) {
        HAND_TILE_ENTITY = addTileEntity(evt.getRegistry(), "hand_tile", HandTileEntity::new, STONE_HAND.get());
        BRAZIER_TILE_ENTITY = addTileEntity(evt.getRegistry(), "brazier_tile", BrazierTileEntity::new, BRAZIER.get());
        NECROTIC_FOCUS_TILE_ENTITY = addTileEntity(evt.getRegistry(), "necrotic_focus", NecroticFocusTileEntity::new,
                NECROTIC_FOCUS.get());
        CRUCIBLE_TILE_ENTITY = addTileEntity(evt.getRegistry(), "crucible", CrucibleTileEntity::new, CRUCIBLE.get());
        EFFIGY_TILE_ENTITY = addTileEntity(evt.getRegistry(), "effigy", EffigyTileEntity::new, STRAW_EFFIGY.get(),
                UNHOLY_EFFIGY.get());
        SOUL_ENCHANTER_TILE_ENTITY = addTileEntity(evt.getRegistry(), "soul_enchanter", SoulEnchanterTileEntity::new,
                SOUL_ENCHANTER.get());
        WOODEN_STAND_TILE_ENTITY = addTileEntity(evt.getRegistry(), "wooden_brewing_stand", WoodenStandTileEntity::new,
                WOODEN_STAND.get());
        GOBLET_TILE_ENTITY = addTileEntity(evt.getRegistry(), "goblet", GobletTileEntity::new, GOBLET.get());
        CISTERN_TILE_ENTITY = addTileEntity(evt.getRegistry(), "cistern", CisternTileEntity::new, CISTERN.get());
        PIPE_TILE_ENTITY = addTileEntity(evt.getRegistry(), "pipe", PipeTileEntity::new, GLASS_TUBE.get());
        RESEARCH_TABLE_TILE_ENTITY = addTileEntity(evt.getRegistry(), "research_table", ResearchTableTileEntity::new,
                RESEARCH_TABLE.get());
    }

    public static DamageSource RITUAL_DAMAGE = new DamageSource("ritual").bypassArmor().bypassMagic();
    public static DamageSource FROST_DAMAGE = new DamageSource("frost");

    public void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(IReputation.class);
        event.register(IKnowledge.class);
        event.register(ISoul.class);
        event.register(IPlayerData.class);
    }

    @SubscribeEvent
    public void defineAttributes(EntityAttributeCreationEvent event) {
        event.put(Registry.ZOMBIE_BRUTE.get(), ZombieBruteEntity.createAttributes());
        event.put(Registry.WRAITH.get(), WraithEntity.createAttributes());
        event.put(Registry.NECROMANCER.get(), NecromancerEntity.createAttributes());
        event.put(Registry.RAVEN.get(), RavenEntity.createAttributes());
        event.put(Registry.SLIMY_SLUG.get(), SlimySlugEntity.createAttributes());
    }

    static DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES,
            Eidolon.MODID);

    public static RegistryObject<FlameParticleType> FLAME_PARTICLE = PARTICLES.register("flame_particle",
            FlameParticleType::new);
    public static RegistryObject<SmokeParticleType> SMOKE_PARTICLE = PARTICLES.register("smoke_particle",
            SmokeParticleType::new);
    public static RegistryObject<SparkleParticleType> SPARKLE_PARTICLE = PARTICLES.register("sparkle_particle",
            SparkleParticleType::new);
    public static RegistryObject<WispParticleType> WISP_PARTICLE = PARTICLES.register("wisp_particle",
            WispParticleType::new);
    public static RegistryObject<BubbleParticleType> BUBBLE_PARTICLE = PARTICLES.register("bubble_particle",
            BubbleParticleType::new);
    public static RegistryObject<LineWispParticleType> LINE_WISP_PARTICLE = PARTICLES.register("line_wisp_particle",
            LineWispParticleType::new);
    public static RegistryObject<SteamParticleType> STEAM_PARTICLE = PARTICLES.register("steam_particle",
            SteamParticleType::new);
    public static RegistryObject<SignParticleType> SIGN_PARTICLE = PARTICLES.register("sign_particle",
            SignParticleType::new);
    public static RegistryObject<SlashParticleType> SLASH_PARTICLE = PARTICLES.register("slash_particle",
            SlashParticleType::new);
    public static RegistryObject<GlowingSlashParticleType> GLOWING_SLASH_PARTICLE = PARTICLES
            .register("glowing_slash_particle", GlowingSlashParticleType::new);
    public static RegistryObject<RuneParticleType> RUNE_PARTICLE = PARTICLES.register("rune_particle",
            RuneParticleType::new);

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void registerFactories(ParticleFactoryRegisterEvent evt) {
        Minecraft.getInstance().particleEngine.register(FLAME_PARTICLE.get(), FlameParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(SMOKE_PARTICLE.get(), SmokeParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(SPARKLE_PARTICLE.get(), SparkleParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(WISP_PARTICLE.get(), WispParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(BUBBLE_PARTICLE.get(), BubbleParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(STEAM_PARTICLE.get(), SteamParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(LINE_WISP_PARTICLE.get(), LineWispParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(SIGN_PARTICLE.get(),
                (sprite) -> new SignParticleType.Factory());
        Minecraft.getInstance().particleEngine.register(SLASH_PARTICLE.get(), SlashParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(GLOWING_SLASH_PARTICLE.get(),
                GlowingSlashParticleType.Factory::new);
        Minecraft.getInstance().particleEngine.register(RUNE_PARTICLE.get(),
                (sprite) -> new RuneParticleType.Factory());
    }
}
