package games.moegirl.sinocraft.sinocore.api.tree;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Function;

public class TreeProperties {

    public final Tree tree;

    public final ResourceLocation name;
    public final SoundType sound;
    public final MaterialColor topLogColor, topStrippedLogColor;
    public final MaterialColor barkLogColor, barkStrippedLogColor;
    public final AbstractTreeGrower grower;
    public final MaterialColor plankColor;
    public final MaterialColor woodColor, strippedWoodColor;
    public final WoodType type;
    public final boolean hasStick;

    final Function<Tree, Block> planks;
    final Function<Tree, SaplingBlock> sapling;
    final Function<Tree, RotatedPillarBlock> log;
    final Function<Tree, RotatedPillarBlock> strippedLog;
    final Function<Tree, RotatedPillarBlock> wood;
    final Function<Tree, RotatedPillarBlock> strippedWoods;
    final Function<Tree, LeavesBlock> leaves;
    final Function<Tree, StandingSignBlock> sign;
    final Function<Tree, WallSignBlock> wallSign;
    final Function<Tree, PressurePlateBlock> pressurePlate;
    final Function<Tree, TrapDoorBlock> trapdoor;
    final Function<Tree, StairBlock> stairs;
    final Function<Tree, FlowerPotBlock> pottedSapling;
    final Function<Tree, ButtonBlock> button;
    final Function<Tree, SlabBlock> slab;
    final Function<Tree, FenceGateBlock> fenceGate;
    final Function<Tree, FenceBlock> fence;
    final Function<Tree, DoorBlock> door;

    public TreeProperties(Tree tree, TreeBuilder builder) {
        this.tree = tree;

        this.name = builder.name;
        this.type = WoodType.register(WoodType.create(builder.name.getPath()));
        this.sound = builder.sound;
        this.topStrippedLogColor = builder.topStrippedLogColor;
        this.barkStrippedLogColor = builder.barkStrippedLogColor;
        this.topLogColor = builder.topLogColor;
        this.barkLogColor = builder.barkLogColor;
        this.plankColor = builder.plankColor;
        this.woodColor = builder.woodColor;
        this.strippedWoodColor = builder.strippedWoodColor;
        this.grower = builder.grower;
        this.hasStick = builder.hasStick;

        this.planks = builder.planks;
        this.sapling = builder.sapling;
        this.log = builder.log;
        this.strippedLog = builder.strippedLog;
        this.wood = builder.wood;
        this.strippedWoods = builder.strippedWoods;
        this.leaves = builder.leaves;
        this.sign = builder.sign;
        this.wallSign = builder.wallSign;
        this.pressurePlate = builder.pressurePlate;
        this.trapdoor = builder.trapdoor;
        this.stairs = builder.stairs;
        this.pottedSapling = builder.pottedSapling;
        this.button = builder.button;
        this.slab = builder.slab;
        this.fenceGate = builder.fenceGate;
        this.fence = builder.fence;
        this.door = builder.door;
    }

    public ResourceLocation name() {
        return name;
    }

    public WoodType type() {
        return type;
    }

    public boolean hasStick() {
        return hasStick;
    }

}
