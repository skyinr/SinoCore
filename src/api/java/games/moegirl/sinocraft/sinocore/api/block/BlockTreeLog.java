package games.moegirl.sinocraft.sinocore.api.block;

import games.moegirl.sinocraft.sinocore.api.tree.ITreeBlock;
import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import games.moegirl.sinocraft.sinocore.api.tree.TreeProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * A class for log and stripped log with tree
 */
public class BlockTreeLog extends RotatedPillarBlock implements ITreeBlock, IStrippable {

    private final Tree tree;
    private final boolean isStripped;

    public BlockTreeLog(Tree tree, boolean isStripped, Properties properties) {
        super(properties);
        this.tree = tree;
        this.isStripped = isStripped;
    }

    public BlockTreeLog(Tree tree, boolean isStripped) {
        this(tree, isStripped, Properties.of(Material.WOOD, state -> color(tree, state, isStripped))
                .strength(2.0F)
                .sound(SoundType.WOOD));
    }

    @Override
    public Tree getTree() {
        return tree;
    }

    private static MaterialColor color(Tree tree, BlockState state, boolean isStripped) {
        TreeProperties prop = tree.getProperties();
        if (isStripped) {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? prop.topStrippedLogColor : prop.barkStrippedLogColor;
        } else {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? prop.topLogColor : prop.barkLogColor;
        }
    }

    @Override
    public boolean canStripped() {
        return !isStripped;
    }

    @Override
    public BlockState getStrippedBlock() {
        return tree.getBlocks().strippedLog.get().defaultBlockState();
    }
}
