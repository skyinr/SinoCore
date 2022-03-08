package games.moegirl.sinocraft.sinocore.api.block;

import games.moegirl.sinocraft.sinocore.api.tree.ITreeBlock;
import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.Material;

/**
 * A class for sapling with tree
 */
public class BlockTreeSapling extends SaplingBlock implements ITreeBlock {

    private final Tree tree;

    public BlockTreeSapling(AbstractTreeGrower pTreeGrower, Properties pProperties, Tree tree) {
        super(pTreeGrower, pProperties);
        this.tree = tree;
    }

    public BlockTreeSapling(Tree tree) {
        this(tree.getProperties().grower, Properties.of(Material.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS), tree);
    }

    @Override
    public Tree getTree() {
        return tree;
    }
}
