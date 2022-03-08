package games.moegirl.sinocraft.sinocore.api.tree;

import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

/**
 * A boat dispose for tree
 */
public class TreeBoatDisposeItemBehavior extends DefaultDispenseItemBehavior {

    private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
    private final Tree tree;

    public TreeBoatDisposeItemBehavior(Tree tree) {
        this.tree = tree;
    }

    @Override
    protected ItemStack execute(BlockSource pSource, ItemStack pStack) {
        Direction direction = pSource.getBlockState().getValue(DispenserBlock.FACING);
        Level level = pSource.getLevel();
        double d0 = pSource.x() + direction.getStepX() * 1.125F;
        double d1 = pSource.y() + direction.getStepY() * 1.125F;
        double d2 = pSource.z() + direction.getStepZ() * 1.125F;
        BlockPos blockpos = pSource.getPos().relative(direction);
        double d3;
        if (level.getFluidState(blockpos).is(FluidTags.WATER)) {
            d3 = 1.0D;
        } else {
            if (!level.getBlockState(blockpos).isAir() || !level.getFluidState(blockpos.below()).is(FluidTags.WATER)) {
                return this.defaultDispenseItemBehavior.dispense(pSource, pStack);
            }

            d3 = 0.0D;
        }

        Boat boat = new Boat(level, d0, d1 + d3, d2);
        boat.setType(Boat.Type.OAK);
        SinoCoreAPI.getMixins().getBoat(boat).setTreeType(tree);
        boat.setYRot(direction.toYRot());
        level.addFreshEntity(boat);
        pStack.shrink(1);
        return pStack;
    }
}
