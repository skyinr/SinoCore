package games.moegirl.sinocraft.sinocore.api.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
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
public abstract class BlockTagsProviderBase extends BlockTagsProvider {
    protected String mainModId;

    public BlockTagsProviderBase(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    public BlockTagsProviderBase(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper, String mainModIdIn) {
        super(pGenerator, modId, existingFileHelper);
        mainModId = mainModIdIn;
    }

    @Override
    protected void addTags() {
        addPickaxe();
        addAxe();
        addShovel();
        addHoe();

        addStoneTool();
        addIronTool();
        addDiamondTool();
    }

    public abstract void addPickaxe();
    public abstract void addAxe();
    public abstract void addShovel();
    public abstract void addHoe();

    public abstract void addStoneTool();
    public abstract void addIronTool();
    public abstract void addDiamondTool();

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
