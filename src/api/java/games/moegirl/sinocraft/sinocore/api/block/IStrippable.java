package games.moegirl.sinocraft.sinocore.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

/**
 * An interface for block.
 * <p>A block implement this interface, the axe can make it stripped</p>
 *
 * @deprecated override {@link net.minecraft.world.level.block.Block#getToolModifiedState(BlockState, Level, BlockPos, Player, ItemStack, ToolAction)}
 */
@Deprecated(forRemoval = true)
public interface IStrippable {

    /**
     * Check if the block can be stripped
     *
     * @return true if stripped
     */
    boolean canStripped();

    /**
     * Get stripped block
     *
     * @return stripped block
     */
    BlockState getStrippedBlock();
}
