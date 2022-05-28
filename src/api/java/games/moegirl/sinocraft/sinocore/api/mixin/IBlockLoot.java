package games.moegirl.sinocraft.sinocore.api.mixin;

import net.minecraft.world.level.block.Block;

public interface IBlockLoot {

    Iterable<Block> scGetKnownBlocks();
}
