package games.moegirl.sinocraft.sinocore.api.data.base;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import games.moegirl.sinocraft.sinocore.api.block.ILootableBlock;
import games.moegirl.sinocraft.sinocore.api.utility.BlockLootables;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

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

    @Override
    protected void add(Block block, LootTable.Builder table) {
        super.add(block, table);
    }

    protected void addBlock(Block block, LootTable.Builder table) {
        add(block, table);
        blocks.remove(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return blocks;
    }

    @Override
    protected void addTables() {
        for (Block block : blocks) {
            if (block instanceof ILootableBlock block1) {
                if (!block.getLootTable().equals(new ResourceLocation(block.getRegistryName().getNamespace(), "blocks/" + block.getRegistryName().getPath()))) {
                    SinoCoreAPI.LOGGER.atWarn().log("Do not use BlockBehaviour.Properties#lootFrom when ILootableBlock is implemented - {} will be skipped", block1);
                } else {
                    add(block, block1.createLootBuilder(BlockLootables.INSTANCE));
                }
            } else {
                dropSelf(block);
            }
        }
    }
}
