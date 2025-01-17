package games.moegirl.sinocraft.sinocore.api.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import games.moegirl.sinocraft.sinocore.api.data.base.WarnItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Base item model provider.
 *
 * @author skyinr
 */
public class ItemModelProviderBase extends WarnItemModelProvider {  // qyl: Use FooBase for our base data providers.
    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    protected Set<Item> skipItems = new HashSet<>();

    private final DeferredRegister<? extends Item> deferredRegister;

    public ItemModelProviderBase(DataGenerator generator, String modId, ExistingFileHelper exHelper, DeferredRegister<? extends Item> deferredRegister) {
        super(generator, modId, exHelper);
        this.deferredRegister = deferredRegister;
        // qyl: We need not an additional modId variable. MC already have.
    }

    /**
     * Register item model.
     */
    @Override
    protected void registerModels() {
        Set<Item> items = getItems();
        items.removeAll(skipItems);

        registerItemBlock(items.stream()
                .filter(i -> i instanceof BlockItem)
                .map(i -> (BlockItem) i)
                .collect(Collectors.toSet()));
        registerItem(items);
    }

    protected Set<Item> getItems() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    /**
     * @param items Set of items.
     * @return
     */
    protected void skipItems(Item... items) {
        skipItems.addAll(Arrays.asList(items));
    }

    /**
     * @param items Set of items.
     * @return
     */
    protected void skipItems(RegistryObject<? extends Item>... items) {
        skipItems.addAll(Arrays.stream(items).map(RegistryObject::get).toList());
    }

    /**
     * Register item block model.
     *
     * @param itemBlocks Set of item block.
     */
    protected void registerItemBlock(@NotNull Set<BlockItem> itemBlocks) {
        itemBlocks.forEach(i -> withExistingParent(name(i),
                modLoc("block/" + name(i))));   // qyl: Change prefix to mc builtin "modLoc".
    }

    /**
     * Add handheld item model.
     *
     * @param name Name of items.
     * @return Builders.
     */
    protected ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", modLoc("item/" + name));    // qyl: Change prefix to mc builtin "modLoc".
    }

    /**
     * @see ItemModelProviderBase#handheldItem(String)
     */
    protected ItemModelBuilder handheldItem(Item i) {
        return handheldItem(name(i));
    }

    /**
     * Add generated item model.
     *
     * @param name Name of items.
     * @return item model builder
     */
    protected ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", modLoc("item/" + name));    // qyl: Change prefix to mc builtin "modLoc".
    }

    /**
     * @see ItemModelProviderBase#generatedItem(String)
     */
    protected ItemModelBuilder generatedItem(Item i) {
        return generatedItem(name(i));
    }

    /**
     * get item name
     *
     * @param item item
     * @return item name
     */
    protected static String name(Item item) { // Todo: qyl: Avoid direct use string Path, use ResourceLocation instead.
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private void registerItem(Set<Item> items) {
        items.removeAll(items.stream()
                .filter(item -> item instanceof BlockItem || item instanceof CrossbowItem)
                .collect(Collectors.toSet()));  // skyinr: Block and crossbow filter.
        items.forEach(this::generatedItem);
        items.stream()
                .filter(item -> item instanceof TieredItem)
                .forEach(this::handheldItem);  // skyinr: Tiered items filter.
    }

    /**
     * qyl27: Generate all, use modid of DeferredRegister to save to.
     */
    @Override
    protected void generateAll(HashCache cache) {
        for (ItemModelBuilder model : generatedModels.values()) {
            Path target = getPath(model);
            try {
                DataProvider.save(GSON, cache, model.toJson(), target);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * qyl27: Generate all, use modid of DeferredRegister to save to.
     */
    private Path getPath(ItemModelBuilder model) {
        ResourceLocation loc = model.getLocation();
        return generator.getOutputFolder().resolve("assets/"
                + deferredRegister.createTagKey("test").location().getNamespace()
                + "/models/" + loc.getPath() + ".json");    // qyl: Did it work?
    }
}
