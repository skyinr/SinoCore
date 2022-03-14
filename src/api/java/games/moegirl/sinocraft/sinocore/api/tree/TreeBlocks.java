package games.moegirl.sinocraft.sinocore.api.tree;

import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
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
    @Nullable
    public final RegistryObject<ChestBlock> chest;

    private final Set<Block> allBlocks = new HashSet<>();

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

    public ChestBlock chest() {
        return Objects.requireNonNull(chest).get();
    }

    public boolean hasChest() {
        return chest != null;
    }

    public Set<Block> allBlocks() {
        return Set.copyOf(allBlocks);
    }

    TreeBlocks(Tree tree, DeferredRegister<Block> register) {
        this.tree = tree;

        TreeProperties properties = tree.getProperties();
        planks = register(register, "planks", asSupplier(properties.planks));
        sapling = register(register, "sapling", asSupplier(properties.sapling));
        log = register(register, "log", asSupplier(properties.log));
        strippedLog = register(register, "stripped", "log", asSupplier(properties.strippedLog));
        wood = register(register, "wood", asSupplier(properties.wood));
        strippedWoods = register(register, "stripped", "wood", asSupplier(properties.strippedWoods));
        leaves = register(register, "leaves", asSupplier(properties.leaves));
        sign = register(register, "sign", asSupplier(properties.sign));
        wallSign = register(register, "wall_sign", asSupplier(properties.wallSign));
        pressurePlate = register(register, "pressure_plate", asSupplier(properties.pressurePlate));
        trapdoor = register(register, "trapdoor", asSupplier(properties.trapdoor));
        stairs = register(register, "stairs", asSupplier(properties.stairs));
        pottedSapling = register(register, "potted", "sapling", asSupplier(properties.pottedSapling));
        button = register(register, "button", asSupplier(properties.button));
        slab = register(register, "slab", asSupplier(properties.slab));
        fenceGate = register(register, "fence_gate", asSupplier(properties.fenceGate));
        fence = register(register, "fence", asSupplier(properties.fence));
        door = register(register, "door", asSupplier(properties.door));
        chest = properties.chest == null ? null : register(register, "chest", asSupplier(properties.chest));
    }

    private <T extends Block> Supplier<T> asSupplier(Function<Tree, T> factory) {
        return () -> {
            T block = factory.apply(tree);
            allBlocks.add(block);
            return block;
        };
    }

    private <T extends Block> RegistryObject<T> register(DeferredRegister<Block> register, String prefix, String postfix, Supplier<T> supplier) {
        return register.register(prefix + "_" + tree.getName().getPath() + "_" + postfix, supplier);
    }

    private <T extends Block> RegistryObject<T> register(DeferredRegister<Block> register, String postfix, Supplier<T> supplier) {
        return register.register(tree.getName().getPath() + "_" + postfix, supplier);
    }
}
