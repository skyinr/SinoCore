package games.moegirl.sinocraft.sinocore.api.tree;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Optional;

public enum Trees {

    INSTANCE;

    /**
     * Return a tree builder to build a tree
     *
     * @param modid modid
     * @param name  tree name
     * @return builder
     */
    public TreeBuilder treeBuilder(String modid, String name) {
        return Tree.builder(modid, name);
    }

    /**
     * Return a tree builder to build a tree
     *
     * @param name tree name
     * @return builder
     */
    public TreeBuilder treeBuilder(ResourceLocation name) {
        return Tree.builder(name);
    }

    /**
     * Get a tree by name
     *
     * @param name name
     * @return tree
     */
    public Optional<Tree> get(ResourceLocation name) {
        return Optional.ofNullable(Tree.get(name));
    }

    /**
     * Get a tree by wood type
     *
     * @param type wood type
     * @return tree
     */
    public Optional<Tree> get(WoodType type) {
        return Optional.ofNullable(Tree.get(type));
    }
}
