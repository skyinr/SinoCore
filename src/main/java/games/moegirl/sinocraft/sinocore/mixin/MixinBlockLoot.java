package games.moegirl.sinocraft.sinocore.mixin;

import games.moegirl.sinocraft.sinocore.api.mixin.IBlockLoot;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockLoot.class)
public abstract class MixinBlockLoot implements IBlockLoot {

    @Shadow(remap = false) protected abstract Iterable<Block> getKnownBlocks();

    @Override
    public Iterable<Block> scGetKnownBlocks() {
        return getKnownBlocks();
    }
}
