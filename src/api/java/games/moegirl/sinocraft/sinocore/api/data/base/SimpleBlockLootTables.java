package games.moegirl.sinocraft.sinocore.api.data.base;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import games.moegirl.sinocraft.sinocore.api.block.ILootableBlock;
import games.moegirl.sinocraft.sinocore.api.utility.BlockLootables;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author skyinr
 */
public class SimpleBlockLootTables extends BlockLoot {
    private final Set<Block> blocks;

    public SimpleBlockLootTables(Set<Block> blocks) {
        this.blocks = new HashSet<>(blocks);
    }

    /**
     * Add a block to the list of blocks
     *
     * @param block add this block to the list
     * @param table the loot table to use for this block
     */
    @Override
    protected void add(Block block, LootTable.Builder table) {
        super.add(block, table);
        blocks.remove(block);
    }

    /**
     * Get the list of blocks
     *
     * @return known blocks
     */
    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return blocks;
    }

    /**
     * Add a block to the list of blocks to skip
     */
    protected void skip(Block block) {
        blocks.remove(block);
    }

    /**
     * Add blocks to the list of blocks to skip
     *
     * @param blocks blocks to skip
     */
    protected void skip(Block... blocks) {
        for (Block block : blocks) {
            this.blocks.remove(block);
        }
    }

    protected void skip(Collection<Block> blocks) {
        this.blocks.removeAll(blocks);
    }

    /**
     * Build a loot table for a block
     *
     */
    protected void dropSelfWithContents() {
        for (Block block : blocks) {
            if (block instanceof ILootableBlock block1) {
                if (!block.getLootTable().equals(new ResourceLocation(block.getRegistryName().getNamespace(), "blocks/" + block.getRegistryName().getPath()))) {
                    SinoCoreAPI.LOGGER.atWarn().log("Do not use BlockBehaviour.Properties#lootFrom when ILootableBlock is implemented - {}", block1);
                }
                add(block, block1.createLootBuilder(BlockLootables.INSTANCE));
            } else {
                dropSelf(block);
            }
        }
    }

    @Override
    protected void addTables() {
        dropSelfWithContents();
    }
}
