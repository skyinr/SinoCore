package games.moegirl.sinocraft.sinocore.api.block;

import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * A class for log and stripped log with tree
 */
public class BlockTreeLog extends RotatedPillarBlock implements IStrippable, ITreeBlock {

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

    private static MaterialColor color(Tree tree, BlockState state, boolean isStripped) {
        Tree.BuilderProperties prop = tree.properties();
        if (isStripped) {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? prop.topStrippedLogColor() : prop.barkStrippedLogColor();
        } else {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? prop.topLogColor() : prop.barkLogColor();
        }
    }

    @Override
    public boolean canStripped() {
        return !isStripped;
    }

    @Override
    public BlockState getStrippedBlock() {
        return tree.strippedLog().defaultBlockState();
    }

    @Override
    public Tree getTree() {
        return tree;
    }
}
