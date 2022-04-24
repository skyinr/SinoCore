package games.moegirl.sinocraft.sinocore.api.data;

import games.moegirl.sinocraft.sinocore.api.data.base.ExtendedBlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagManager;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

/**
 * Register block tags
 *
 * @author skyinr
 */
public abstract class BlockTagsProviderBase extends ExtendedBlockTagsProvider {
    protected String mainModId;

    public BlockTagsProviderBase(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    public BlockTagsProviderBase(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper, String mainModIdIn) {
        super(pGenerator, modId, existingFileHelper);
        mainModId = mainModIdIn;
    }

    @Override
    public String getName() {
        return "Mod " + modId + " Block Tags";
    }

    @Override
    protected Path getPath(ResourceLocation loc) {
        var key = this.registry.key();
        return this.generator.getOutputFolder().resolve("data/" + getMainModId() + "/" + TagManager.getTagDir(key) + "/" + loc.getPath() + ".json");
    }

    public String getMainModId() {
        return mainModId != null ? mainModId : modId;
    }
}
