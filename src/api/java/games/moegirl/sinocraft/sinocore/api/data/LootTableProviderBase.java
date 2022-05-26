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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author skyinr
 */
public abstract class LootTableProviderBase extends LootTableProvider {
    protected final String modID;
    protected final DeferredRegister<Block> register;

    public LootTableProviderBase(DataGenerator pGenerator, String modID, DeferredRegister<Block> register) {
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
     *
     * @return Block loot table
     */
    public abstract void getBlockLootTable(SimpleBlockLootTables table);

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>> defaultTable = () -> {
            Set<Block> blocks = register.getEntries()
                    .stream()
                    .map(Supplier::get)
                    .collect(Collectors.toSet());
            SimpleBlockLootTables tables = new SimpleBlockLootTables(blocks);
            getBlockLootTable(tables);
            return tables;
        };
        return List.of(Pair.of(defaultTable, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker) {
    }
}
