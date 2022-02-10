package games.moegirl.sinocraft.sinocore.api.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.stream.Collectors;

public class DefaultBlockStateProvider extends BlockStateProvider {
    private final String modID;
    public DefaultBlockStateProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
        this.modID = modId;
    }

    @Override
    protected void registerStatesAndModels() {
        // register models and state for blocks
        Set<Block> blocks = Registry.BLOCK.stream()
                .filter(b -> modID.equals(Registry.BLOCK.getKey(b).getNamespace()))//filter block in mod
                .collect(Collectors.toSet());

        registerBlock(blocks);
    }

    private void registerBlock(Set<Block> blocks) {
        blocks.forEach(this::simpleBlock);
    }

}
