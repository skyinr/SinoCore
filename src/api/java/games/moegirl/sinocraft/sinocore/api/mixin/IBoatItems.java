package games.moegirl.sinocraft.sinocore.api.mixin;

import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.world.item.BoatItem;

public interface IBoatItems {

    /**
     * Set tree to boat item
     *
     * @param tree tree
     * @return this boat item
     */
    BoatItem setTreeType(Tree tree);
}
