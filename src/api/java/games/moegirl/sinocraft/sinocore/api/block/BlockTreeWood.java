package games.moegirl.sinocraft.sinocore.api.block;

import games.moegirl.sinocraft.sinocore.api.tree.ITreeBlock;
import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

/**
 * A class for wood with tree
 */
public class BlockTreeWood extends RotatedPillarBlock implements ITreeBlock {

    private final Tree tree;

    public BlockTreeWood(Tree tree, Properties properties) {
        super(properties);
        this.tree = tree;
    }

    public BlockTreeWood(Tree tree, boolean isStripped) {
        this(tree, Properties.of(Material.WOOD,
                        isStripped ? tree.getProperties().strippedWoodColor : tree.getProperties().woodColor)
                .strength(2.0F)
                .sound(SoundType.WOOD));
    }

    @Override
    public Tree getTree() {
        return tree;
    }
}
