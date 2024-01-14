package elucent.eidolon.world.stuctures;

import java.util.Random;

import elucent.eidolon.Eidolon;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraftforge.registries.RegistryObject;

public class CatacombPieces {
    public static final ResourceLocation
            CORRIDOR_CENTER_ID = new ResourceLocation(Eidolon.MODID, "catacomb_corridor_center"),
            CORRIDOR_DOOR_ID = new ResourceLocation(Eidolon.MODID, "catacomb_corridor_door"),
            SMALL_ROOM_ID = new ResourceLocation(Eidolon.MODID, "catacomb_room_small"),
            TRAP_ID = new ResourceLocation(Eidolon.MODID, "catacomb_trap"),
            SHRINE_ID = new ResourceLocation(Eidolon.MODID, "catacomb_shrine"),
            SKULL_ID = new ResourceLocation(Eidolon.MODID, "catacomb_skull"),
            SPAWNER_ID = new ResourceLocation(Eidolon.MODID, "catacomb_spawner"),
            COFFIN_ID = new ResourceLocation(Eidolon.MODID, "catacomb_coffin"),
            MEDIUM_ROOM_ID = new ResourceLocation(Eidolon.MODID, "catacomb_room_medium"),
            GRAVEYARD_ID = new ResourceLocation(Eidolon.MODID, "catacomb_graveyard"),
            TURNAROUND_ID = new ResourceLocation(Eidolon.MODID, "catacomb_turnaround"),
            LAB_ID = new ResourceLocation(Eidolon.MODID, "catacomb_lab");
    public static RegistryObject<StructurePieceType>
            CORRIDOR_CENTER = null, CORRIDOR_DOOR = null,
            SMALL_ROOM = null, TRAP = null, SHRINE = null, SKULL = null, SPAWNER = null,
            COFFIN = null, MEDIUM_ROOM = null, GRAVEYARD = null, TURNAROUND = null, LAB = null;

    public static class CorridorCenter extends RandomlyRotatedPiece {
        public CorridorCenter(StructurePieceSerializationContext manager, CompoundTag nbt) { super(CORRIDOR_CENTER.get(), CORRIDOR_CENTER_ID, manager.structureManager(), nbt); }
        public CorridorCenter(StructureManager StructureManager, BlockPos pos, Random random) { super(CORRIDOR_CENTER.get(), CORRIDOR_CENTER_ID, StructureManager, pos, random); }
    }

    public static class CorridorDoor extends BasicPiece {
        public CorridorDoor(StructurePieceSerializationContext manager, CompoundTag nbt) { super(CORRIDOR_DOOR.get(), CORRIDOR_DOOR_ID, nbt, manager.structureManager()); }
        public CorridorDoor(StructureManager templateManager, BlockPos pos, Rotation rot, Random random) {
            super(CORRIDOR_DOOR.get(), CORRIDOR_DOOR_ID, templateManager, pos, rot, Mirror.NONE, random);
        }
    }

    public static class SmallRoom extends RandomlyRotatedPiece {
        public SmallRoom(StructurePieceSerializationContext manager, CompoundTag nbt) { super(SMALL_ROOM.get(), SMALL_ROOM_ID, manager.structureManager(), nbt); }
        public SmallRoom(StructureManager templateManager, BlockPos pos, Random random) { super(SMALL_ROOM.get(), SMALL_ROOM_ID, templateManager, pos, random); }
    }

    public static class Trap extends RandomlyRotatedPiece {
        public Trap(StructurePieceSerializationContext manager, CompoundTag nbt) { super(TRAP.get(), TRAP_ID, manager.structureManager(), nbt); }
        public Trap(StructureManager templateManager, BlockPos pos, Random random) { super(TRAP.get(), TRAP_ID, templateManager, pos, random); }
    }

    public static class Shrine extends RandomlyRotatedPiece {
        public Shrine(StructurePieceSerializationContext manager, CompoundTag nbt) { super(SHRINE.get(), SHRINE_ID, manager.structureManager(), nbt); }
        public Shrine(StructureManager templateManager, BlockPos pos, Random random) { super(SHRINE.get(), SHRINE_ID, templateManager, pos, random); }
    }

    public static class Skull extends RandomlyRotatedPiece {
        public Skull(StructurePieceSerializationContext manager, CompoundTag nbt) { super(SKULL.get(), SKULL_ID, manager.structureManager(), nbt); }
        public Skull(StructureManager templateManager, BlockPos pos, Random random) { super(SKULL.get(), SKULL_ID, templateManager, pos, random); }
    }

    public static class Spawner extends RandomlyRotatedPiece {
        public Spawner(StructurePieceSerializationContext manager, CompoundTag nbt) { super(SPAWNER.get(), SPAWNER_ID, manager.structureManager(), nbt); }
        public Spawner(StructureManager templateManager, BlockPos pos, Random random) { super(SPAWNER.get(), SPAWNER_ID, templateManager, pos, random); }
    }

    public static class Coffin extends RandomlyRotatedPiece {
        public Coffin(StructurePieceSerializationContext manager, CompoundTag nbt) { super(COFFIN.get(), COFFIN_ID, manager.structureManager(), nbt); }
        public Coffin(StructureManager templateManager, BlockPos pos, Random random) { super(COFFIN.get(), COFFIN_ID, templateManager, pos, random); }
    }

    public static class MediumRoom extends RandomlyRotatedPiece {
        public MediumRoom(StructurePieceSerializationContext manager, CompoundTag nbt) { super(MEDIUM_ROOM.get(), MEDIUM_ROOM_ID, manager.structureManager(), nbt); }
        public MediumRoom(StructureManager templateManager, BlockPos pos, Random random) { super(MEDIUM_ROOM.get(), MEDIUM_ROOM_ID, templateManager, pos, random); }
    }

    public static class Graveyard extends RandomlyRotatedPiece {
        public Graveyard(StructurePieceSerializationContext manager, CompoundTag nbt) { super(GRAVEYARD.get(), GRAVEYARD_ID, manager.structureManager(), nbt); }
        public Graveyard(StructureManager templateManager, BlockPos pos, Random random) { super(GRAVEYARD.get(), GRAVEYARD_ID, templateManager, pos, random); }
    }

    public static class Turnaround extends RandomlyRotatedPiece {
        public Turnaround(StructurePieceSerializationContext manager, CompoundTag nbt) { super(TURNAROUND.get(), TURNAROUND_ID, manager.structureManager(), nbt); }
        public Turnaround(StructureManager templateManager, BlockPos pos, Random random) { super(TURNAROUND.get(), TURNAROUND_ID, templateManager, pos, random); }
    }

    public static class Lab extends RandomlyRotatedPiece {
        public Lab(StructurePieceSerializationContext manager, CompoundTag nbt) { super(LAB.get(), LAB_ID, manager.structureManager(), nbt); }
        public Lab(StructureManager templateManager, BlockPos pos, Random random) { super(LAB.get(), LAB_ID, templateManager, pos, random); }
    }
}

