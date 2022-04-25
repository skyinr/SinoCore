package games.moegirl.sinocraft.sinocore.api.block;

import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

/**
 * A class for wood with tree
 */
public class BlockTreeWood extends RotatedPillarBlock implements ITreeBlock, IStrippable {

    private final Tree tree;
    private final boolean isStripped;

    public BlockTreeWood(Tree tree, boolean isStripped, Properties properties) {
        super(properties);
        this.tree = tree;
        this.isStripped = isStripped;
    }

    public BlockTreeWood(Tree tree, boolean isStripped) {
        this(tree, isStripped, Properties.of(Material.WOOD,
                        isStripped ? tree.properties().strippedWoodColor() : tree.properties().woodColor())
                .strength(tree.properties().strengthModifier().apply(2), 2.0f)
                .sound(SoundType.WOOD));
    }

    @Override
    public Tree getTree() {
        return tree;
    }

    @Override
    public boolean canStripped() {
        return !isStripped;
    }

    @Override
    public BlockState getStrippedBlock() {
        return tree.strippedWoods().defaultBlockState();
    }
}
