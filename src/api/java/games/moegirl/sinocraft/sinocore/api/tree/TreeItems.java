package games.moegirl.sinocraft.sinocore.api.tree;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TreeItems {

    public final Tree tree;
    public final CreativeModeTab tab;

    public final RegistryObject<BoatItem> boat;
    @Nullable
    public final RegistryObject<Item> stick;

    public TreeItems(Tree tree, DeferredRegister<Item> register, CreativeModeTab tab) {
        this.tree = tree;
        this.tab = tab;

        // blocks
        TreeBlocks blocks = tree.getBlocks();
        registerBlock(register, blocks.planks);
        registerBlock(register, blocks.sapling);
        registerBlock(register, blocks.log);
        registerBlock(register, blocks.strippedLog);
        registerBlock(register, blocks.strippedWoods);
        registerBlock(register, blocks.wood);
        registerBlock(register, blocks.leaves);
        registerBlock(register, blocks.slab);
        registerBlock(register, blocks.fence);
        registerBlock(register, blocks.stairs);
        registerBlock(register, blocks.button);
        registerBlock(register, blocks.pressurePlate);
        registerBlock(register, blocks.door,
                () -> new DoubleHighBlockItem(blocks.door(), new Item.Properties().tab(tab)));
        registerBlock(register, blocks.trapdoor);
        registerBlock(register, blocks.fenceGate);
        registerBlock(register, blocks.sign,
                () -> new SignItem(new Item.Properties().stacksTo(16).tab(tab), blocks.sign(), blocks.wallSign()));

        boat = register.register(tree.getName().getPath() + "_boat", () -> {
            BoatItem item = SinoCoreAPI.getMixins().newBoatItem(tree, new Item.Properties().stacksTo(1).tab(tab));
            DispenserBlock.registerBehavior(item, new TreeBoatDisposeItemBehavior(tree));
            return item;
        });

        if (tree.getProperties().hasStick()) {
            stick = register.register(tree.getName().getPath() + "_stick", () -> new Item(new Item.Properties().tab(tab)));
        } else {
            stick = null;
        }
    }

    public BoatItem boat() {
        return boat.get();
    }

    @Nullable
    public Item stick() {
        return stick == null ? null : stick.get();
    }

    public CreativeModeTab tab() {
        return tab;
    }

    public Tree tree() {
        return tree;
    }

    private <T extends Block> void registerBlock(DeferredRegister<Item> register, RegistryObject<T> block) {
        register.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private void registerBlock(DeferredRegister<Item> register, RegistryObject<?> block, Supplier<? extends BlockItem> supplier) {
        register.register(block.getId().getPath(), supplier);
    }
}
