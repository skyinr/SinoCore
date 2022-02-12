package games.moegirl.sinocraft.sinocore.api.crafting;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * Base on {@link Container}, and can get fluid and heat.
 *
 * <p>Warning: The Container is read-only!!!</p>
 */
public interface IRecipeContainer extends Container {

    /**
     * Get input item by index.
     * Use for the item handler that contains inputs and outputs
     */
    ItemStack getInputItem(int index);

    /**
     * Get the input items count.
     */
    int getItemCount();

    /**
     * If the recipe is a shapeless recipe, use this to record the relationship between item input index and
     * recipe index.
     */
    default void setItemSlotMap(int input, int recipe) {

    }

    /**
     * Get input fluid by index.
     * Use for the item handler that contains inputs and outputs
     */
    default FluidStack getInputFluid(int index) {
        return FluidStack.EMPTY;
    }

    /**
     * Get the input fluids count.
     */
    default int getFluidCount() {
        return 0;
    }

    /**
     * If the recipe is a shapeless recipe, use this to record the relationship between fluid input index and
     * recipe index.
     */
    default void setFluidSlotMap(int input, int recipe) {

    }

    /**
     * Get the heat in this time.
     */
    default int getCurrentHeat() {
        return 0;
    }

    /**
     * Clear all relationships.
     */
    default void removeAllSlotMap() {

    }

    /**
     * Get the max heat during the crafting.
     */
    default int getMaxHeat() {
        return 0;
    }
}
