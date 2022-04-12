package games.moegirl.sinocraft.sinocore.api.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
            var icon = getIcon();
            if (icon == null) {
                return new ItemStack(Items.POTATO);  // qyl27: For a meme.
            }
        }
        return icon;
    }

    @Nullable
    public abstract ItemLike getIcon();
}
