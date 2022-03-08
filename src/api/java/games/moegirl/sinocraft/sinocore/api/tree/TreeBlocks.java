package games.moegirl.sinocraft.sinocore.api.tree;

import games.moegirl.sinocraft.sinocore.api.util.Suppliers;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * A class for all tree blocks
 */
public class TreeBlocks {

    public final Tree tree;

    public final RegistryObject<Block> planks;
    public final RegistryObject<SaplingBlock> sapling;
    public final RegistryObject<RotatedPillarBlock> log;
    public final RegistryObject<RotatedPillarBlock> strippedLog;
    public final RegistryObject<RotatedPillarBlock> wood;
    public final RegistryObject<RotatedPillarBlock> strippedWoods;
    public final RegistryObject<LeavesBlock> leaves;
    public final RegistryObject<StandingSignBlock> sign;
    public final RegistryObject<WallSignBlock> wallSign;
    public final RegistryObject<PressurePlateBlock> pressurePlate;
    public final RegistryObject<TrapDoorBlock> trapdoor;
    public final RegistryObject<StairBlock> stairs;
    public final RegistryObject<FlowerPotBlock> pottedSapling;
    public final RegistryObject<ButtonBlock> button;
    public final RegistryObject<SlabBlock> slab;
    public final RegistryObject<FenceGateBlock> fenceGate;
    public final RegistryObject<FenceBlock> fence;
    public final RegistryObject<DoorBlock> door;

    public Block planks() {
        return planks.get();
    }

    public SaplingBlock sapling() {
        return sapling.get();
    }

    public RotatedPillarBlock log() {
        return log.get();
    }

    public RotatedPillarBlock strippedLog() {
        return strippedLog.get();
    }

    public RotatedPillarBlock wood() {
        return wood.get();
    }

    public RotatedPillarBlock strippedWoods() {
        return strippedWoods.get();
    }

    public LeavesBlock leaves() {
        return leaves.get();
    }

    public StandingSignBlock sign() {
        return sign.get();
    }

    public WallSignBlock wallSign() {
        return wallSign.get();
    }

    public PressurePlateBlock pressurePlate() {
        return pressurePlate.get();
    }

    public TrapDoorBlock trapdoor() {
        return trapdoor.get();
    }

    public StairBlock stairs() {
        return stairs.get();
    }

    public FlowerPotBlock pottedSapling() {
        return pottedSapling.get();
    }

    public ButtonBlock button() {
        return button.get();
    }

    public SlabBlock slab() {
        return slab.get();
    }

    public FenceGateBlock fenceGate() {
        return fenceGate.get();
    }

    public FenceBlock fence() {
        return fence.get();
    }

    public DoorBlock door() {
        return door.get();
    }

    TreeBlocks(Tree tree, DeferredRegister<Block> register) {
        this.tree = tree;

        TreeProperties properties = tree.getProperties();
        planks = register(register, "planks", Suppliers.curry(properties.planks, tree));
        sapling = register(register, "sapling", Suppliers.curry(properties.sapling, tree));
        log = register(register, "log", Suppliers.curry(properties.log, tree));
        strippedLog = register(register, "stripped", "log", Suppliers.curry(properties.strippedLog, tree));
        wood = register(register, "wood", Suppliers.curry(properties.wood, tree));
        strippedWoods = register(register, "stripped", "wood", Suppliers.curry(properties.strippedWoods, tree));
        leaves = register(register, "leaves", Suppliers.curry(properties.leaves, tree));
        sign = register(register, "sign", Suppliers.curry(properties.sign, tree));
        wallSign = register(register, "wall_sign", Suppliers.curry(properties.wallSign, tree));
        pressurePlate = register(register, "pressure_plate", Suppliers.curry(properties.pressurePlate, tree));
        trapdoor = register(register, "trapdoor", Suppliers.curry(properties.trapdoor, tree));
        stairs = register(register, "stairs", Suppliers.curry(properties.stairs, tree));
        pottedSapling = register(register, "potted", "sapling", Suppliers.curry(properties.pottedSapling, tree));
        button = register(register, "button", Suppliers.curry(properties.button, tree));
        slab = register(register, "slab", Suppliers.curry(properties.slab, tree));
        fenceGate = register(register, "fence_gate", Suppliers.curry(properties.fenceGate, tree));
        fence = register(register, "fence", Suppliers.curry(properties.fence, tree));
        door = register(register, "door", Suppliers.curry(properties.door, tree));
    }

    private <T extends Block> RegistryObject<T> register(DeferredRegister<Block> register, String prefix, String postfix, Supplier<T> supplier) {
        return register.register(prefix + "_" + tree.getName().getPath() + "_" + postfix, supplier);
    }

    private <T extends Block> RegistryObject<T> register(DeferredRegister<Block> register, String postfix, Supplier<T> supplier) {
        return register.register(tree.getName().getPath() + "_" + postfix, supplier);
    }
}
