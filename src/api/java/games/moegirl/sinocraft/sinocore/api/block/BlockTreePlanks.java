package games.moegirl.sinocraft.sinocore.api.block;

import games.moegirl.sinocraft.sinocore.api.tree.ITreeBlock;
import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

/**
 * A class for plank with tree
 */
public class BlockTreePlanks extends Block implements ITreeBlock {

    private final Tree tree;

    public BlockTreePlanks(Tree tree, Properties properties) {
        super(properties);
        this.tree = tree;
    }

    public BlockTreePlanks(Tree tree) {
        this(tree, Properties.of(Material.WOOD, tree.getProperties().plankColor)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD));
    }

    @Override
    public Tree getTree() {
        return tree;
    }
}
