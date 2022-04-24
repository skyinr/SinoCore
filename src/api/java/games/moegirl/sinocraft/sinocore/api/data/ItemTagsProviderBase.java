package games.moegirl.sinocraft.sinocore.api.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagManager;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

/**
 * Register item tags.
 *
 * @author qyl27
 */
public abstract class ItemTagsProviderBase extends ItemTagsProvider {
    protected String mainModId;

    public ItemTagsProviderBase(DataGenerator pGenerator, BlockTagsProviderBase blockTags, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, blockTags, modId, existingFileHelper);

        mainModId = blockTags.getMainModId();
    }

    @Override
    protected Path getPath(ResourceLocation loc) {
        var key = this.registry.key();
        return this.generator.getOutputFolder().resolve("data/" + mainModId + "/" + TagManager.getTagDir(key) + "/" + loc.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Mod " + modId + " Item Tags";
    }
}
