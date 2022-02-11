package games.moegirl.sinocraft.sinocore.api.data.gen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * BlockStateProviderBase
 *
 * @author skyinr
 */
public class BlockStateProviderBase extends BlockStateProvider {
    private final String modID;
    public BlockStateProviderBase(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
        this.modID = modId;
    }

    @Override
    protected void registerStatesAndModels() {
        // skyinr: Register models and state for blocks
        Set<Block> blocks = Registry.BLOCK.stream()
                .filter(b -> modID.equals(Registry.BLOCK.getKey(b).getNamespace()))// skyinr: Filter block in mod
                .collect(Collectors.toSet());

        registerBlock(blocks);
    }

    private void registerBlock(Set<Block> blocks) {
        blocks.forEach(this::simpleBlock);
    }

}
