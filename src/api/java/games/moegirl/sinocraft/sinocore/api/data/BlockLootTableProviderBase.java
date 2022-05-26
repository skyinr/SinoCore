package games.moegirl.sinocraft.sinocore.api.data;

import com.mojang.datafixers.util.Pair;
import games.moegirl.sinocraft.sinocore.api.data.base.SimpleBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author skyinr
 */
public abstract class BlockLootTableProviderBase extends LootTableProvider {
    protected final String modID;
    protected final DeferredRegister<Block> register;
    protected final Set<Block> removed = new HashSet<>();

    public BlockLootTableProviderBase(DataGenerator pGenerator, String modID, DeferredRegister<Block> register) {
        super(pGenerator);
        this.modID = modID;
        this.register = register;
    }

    @Nonnull
    @Override
    public String getName() {
        return super.getName() + ":" + modID;
    }

    /**
     * Return the block loot table
     */
    public abstract void getBlockLootTable(List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> list);

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>> defaultTable = () -> {
            Set<Block> blocks = register.getEntries()
                    .stream()
                    .map(Supplier::get)
                    .filter(b -> !removed.contains(b))
                    .collect(Collectors.toSet());
            return new SimpleBlockLootTables(blocks);
        };
        List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> list = new ArrayList<>();
        list.add(Pair.of(defaultTable, LootContextParamSets.BLOCK));
        getBlockLootTable(list);
        return list;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker) {
    }

    /**
     * Add a block to the list of blocks to skip
     */
    protected void skip(Block block) {
        removed.add(block);
    }

    /**
     * Add a block to the list of blocks to skip
     */
    protected void skip(Supplier<? extends Block> block) {
        removed.add(block.get());
    }

    /**
     * Add blocks to the list of blocks to skip
     */
    protected void skipAll(Collection<? extends Supplier<? extends Block>> blocks) {
        for (Supplier<? extends Block> block : blocks) {
            skip(block.get());
        }
    }

    /**
     * Add blocks to the list of blocks to skip
     */
    protected void skipBlocks(Collection<? extends Block> blocks) {
        removed.addAll(blocks);
    }

}
