package games.moegirl.sinocraft.sinocore.api.data.gen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * default item model provider
 *
 * @author Sinocraft
 */
public class DefaultItemModelProvider extends ItemModelProvider {
    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    private final String modID;

    public DefaultItemModelProvider(DataGenerator generator, String modID, ExistingFileHelper existingFileHelper) {
        super(generator, modID, existingFileHelper);
        this.modID = modID;
    }

    /**
     * register item model
     */
    @Override
    protected void registerModels() {
        Set<Item> items = ForgeRegistries.ITEMS.getValues().stream()
                .filter(i -> modID.equals(ForgeRegistries.ITEMS.getRegistryName().getNamespace()))//filter item in mod
                .collect(Collectors.toSet());

        registerItemBlock(items.stream().filter(i -> i instanceof BlockItem).map(i -> (BlockItem) i).collect(Collectors.toSet()));
        registerItem(items);
    }

    /**
     * register item block model
     *
     * @param itemBlocks item block
     */
    private void registerItemBlock(@NotNull Set<BlockItem> itemBlocks) {
        itemBlocks.forEach(i -> withExistingParent(name(i), prefix("block/" + name(i))));
    }

    /**
     * add handheld item model
     *
     * @param name item name
     * @return item model builder
     */
    private ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", prefix("items/" + name));
    }

    /**
     * @see DefaultItemModelProvider#handheldItem(String)
     */
    private ItemModelBuilder handheldItem(Item i) {
        return handheldItem(name(i));
    }

    /**
     * add generated item model
     *
     * @param name item name
     * @return item model builder
     */
    private ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", prefix("items/" + name));
    }

    /**
     * @see DefaultItemModelProvider#generatedItem(String)
     */
    private ItemModelBuilder generatedItem(Item i) {
        return generatedItem(name(i));
    }

    /**
     * get item name
     *
     * @param i item
     * @return item name
     */
    private static String name(Item i) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(i)).getPath();
    }

    private void registerItem(Set<Item> items) {
        items.removeAll(items.stream().filter(i -> i instanceof BlockItem || i instanceof CrossbowItem).collect(Collectors.toSet()));//filter block and crossbow
        items.forEach(this::generatedItem);
        items.stream().filter(i -> i instanceof TieredItem).forEach(this::handheldItem);//filter tiered item
    }

    /**
     * additional method
     *
     * @param path model path
     * @return model path with prefix
     */
    public ResourceLocation prefix(String path) {
        return new ResourceLocation(modID, path);
    }
}
