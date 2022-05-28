package games.moegirl.sinocraft.sinocore.api.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * A create tab impl
 * @deprecated remove and extends CreativeModeTab directly. makeIcon() method will be invoked only once in CreativeModeTab.
 */
@Deprecated(forRemoval = true)
public abstract class BaseCreativeTab extends CreativeModeTab {

    private ItemStack icon = null;

    protected BaseCreativeTab(String label) {
        super(label);
    }

    /**
     * Set the icon of this tab
     * @param label The label of the tab.
     * @param iconItem The icon of the tab.
     */
    protected BaseCreativeTab(String label, ItemLike iconItem) {
        super(label);

        icon = iconItem.asItem().getDefaultInstance();
    }

    @Override
    public ItemStack makeIcon() {
        // qyl27: Potato is a meme.

        // Todo: remove it in ver 1.2.0.
        if (getIcon() != null) {
            return getIcon().asItem().getDefaultInstance();
        }

        return Objects.requireNonNullElseGet(icon, () -> new ItemStack(Items.POTATO));
    }

    @Deprecated(forRemoval = true, since = "1.1.2")
    @Nullable
    public abstract ItemLike getIcon();
}
