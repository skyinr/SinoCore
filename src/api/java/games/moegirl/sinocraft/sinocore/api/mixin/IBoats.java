package games.moegirl.sinocraft.sinocore.api.mixin;

import games.moegirl.sinocraft.sinocore.api.tree.Tree;

import javax.annotation.Nullable;

public interface IBoats {

    /**
     * Get tree type from the boat, null if not exist
     *
     * @return tree type
     */
    @Nullable
    Tree getTreeType();

    /**
     * Set tree type to the boat
     *
     * @param tree tree
     */
    void setTreeType(Tree tree);
}
