package games.moegirl.sinocraft.sinocore.api.mixin;

import net.minecraft.world.level.block.Block;

public interface IBlockEntityTypes {

    /**
     * Add block to validBlocks
     *
     * @param block block
     */
    void addBlockToEntity(Block block);
}
