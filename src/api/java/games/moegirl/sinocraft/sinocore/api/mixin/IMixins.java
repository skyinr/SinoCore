package games.moegirl.sinocraft.sinocore.api.mixin;

import games.moegirl.sinocraft.sinocore.api.tree.Tree;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;

/**
 * Get interface to in
 */
public interface IMixins {

    /**
     * Create mixed block entity type method
     *
     * @param item boat item
     * @return methods
     */
    IBoatItems getBoatItem(BoatItem item);

    /**
     * Create mixed block entity type method
     *
     * @param boat boat
     * @return methods
     */
    IBoats getBoat(Boat boat);

    default BoatItem newBoatItem(Tree tree, Item.Properties properties) {
        return getBoatItem(new BoatItem(Boat.Type.OAK, properties)).setTreeType(tree);
    }
}
