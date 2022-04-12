package games.moegirl.sinocraft.sinocore.utility;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

/**
 * Helper for adding slot to menu.
 * @author qyl27
 */
public class SlotHelper {
    public static final int SLOT_SIZE = 18;

    /**
     *
     * @param menu Menu to add slot.
     * @param inventory Inventory source.
     * @param startX Start X location in screen.
     * @param startY Start Y location in screen.
     * @param deltaX Delta X to startX.
     * @param deltaY Delta Y to startY.
     */
    public static void addPlayerInventory(AbstractContainerMenu menu, Inventory inventory,
                                          int startX, int startY, int deltaX, int deltaY) {
        addSlotLine(menu, inventory, 0, 9, startX, startY, deltaX, deltaY);
        addInventorySquareByRow(menu, inventory, 9, 27, 9, startX, startY, deltaX, deltaY);
    }

    /**
     * Add slots as a square by column.
     * Left to right.
     *
     * @param menu Menu to add slot.
     * @param inventory Inventory source.
     * @param startIndex Start index of inv.
     * @param amount Amount of a line.
     * @param height Row count(height).
     * @param startX Start X location in screen.
     * @param startY Start Y location in screen.
     * @param deltaX Delta X to startX.
     * @param deltaY Delta Y to startY.
     */
    public static void addInventorySquareByColumn(AbstractContainerMenu menu, Container inventory,
                                               int startIndex, int amount, int height,
                                               int startX, int startY, int deltaX, int deltaY) {
        int width = amount / height;
        int remainder = amount % height;
        int index = startIndex;
        int x = startX;
        int y = startY;

        for (int i = 0; i < width - 1; i++) {
            addSlotLine(menu, inventory, index, amount, x, y, 0, deltaY);
            index += height;
            x += deltaX;
        }

        addSlotLine(menu, inventory, index, remainder, x, y, 0, deltaY);
    }


    /**
     * Add slots as a square by row.
     * Up to bottom.
     *
     * @param menu Menu to add slot.
     * @param inventory Inventory source.
     * @param startIndex Start index of inv.
     * @param amount Amount of a line.
     * @param width Column count(width).
     * @param startX Start X location in screen.
     * @param startY Start Y location in screen.
     * @param deltaX Delta X to startX.
     * @param deltaY Delta Y to startY.
     */
    public static void addInventorySquareByRow(AbstractContainerMenu menu, Container inventory,
                                               int startIndex, int amount, int width,
                                               int startX, int startY, int deltaX, int deltaY) {
        int height = amount / width;
        int remainder = amount % width;
        int index = startIndex;
        int x = startX;
        int y = startY;

        for (int i = 0; i < height - 1; i++) {
            addSlotLine(menu, inventory, index, amount, x, y, deltaX, 0);
            index += width;
            y += deltaY;
        }

        addSlotLine(menu, inventory, index, remainder, x, y, deltaX, 0);
    }

    /**
     * Add slots as a line.
     *
     * @param menu Menu to add slot.
     * @param inventory Inventory source.
     * @param startIndex Start index of inv.
     * @param amount Amount of a line.
     * @param startX Start X location in screen.
     * @param startY Start Y location in screen.
     * @param deltaX Delta X to startX.
     * @param deltaY Delta Y to startY.
     */
    public static void addSlotLine(AbstractContainerMenu menu, Container inventory, int startIndex, int amount,
                                   int startX, int startY, int deltaX, int deltaY) {
        int x = startX;
        int y = startY;
        for (int i = 0; i < amount; i++) {
            addSlotToContainer(menu, new Slot(inventory, startIndex + i, x, y));
            x += deltaX;
            y += deltaY;
        }
    }

    public static void addSlotToContainer(AbstractContainerMenu menu, Slot slot) {
        slot.index = menu.slots.size();
        menu.slots.add(slot);
    }
}
