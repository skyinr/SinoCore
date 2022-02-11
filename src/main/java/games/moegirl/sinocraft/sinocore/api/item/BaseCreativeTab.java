package games.moegirl.sinocraft.sinocore.api.item;

import games.moegirl.sinocraft.sinocore.api.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;

/**
 * A create tab impl
 */
public abstract class BaseCreativeTab extends CreativeModeTab {

    private ItemStack icon = null;

    protected BaseCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        if (icon == null) {
            if (getIcon() == null) {
                return ItemStack.EMPTY;
            }
            icon = new ItemStack(ModBlocks.pot);
        }
        return icon;
    }

    @Nullable
    public abstract ItemLike getIcon();
}
