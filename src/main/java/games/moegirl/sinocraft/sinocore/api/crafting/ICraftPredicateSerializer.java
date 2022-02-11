package games.moegirl.sinocraft.sinocore.api.crafting;

import com.google.gson.JsonElement;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

/**
 * A serializer for {@link Predicate} to json/network.
 * @param <T> predicate type
 */
public interface ICraftPredicateSerializer<T extends ICraftPredicateSerializer.Predicate<T>> {

    /**
     * Get the predicate/serializer id
     * @return id
     */
    ResourceLocation id();

    /**
     * Create predicate from json
     * @param json json
     * @return predicate
     */
    T fromJson(JsonElement json);

    /**
     * Create predicate from network
     * @param buffer network packet
     * @return predicate
     */
    T fromNetwork(FriendlyByteBuf buffer);

    /**
     * Save predicate to json
     * @param predicate predicate
     * @return json element
     */
    JsonElement toJson(T predicate);

    /**
     * Write predicate to network packet
     * @param buffer network packet
     * @param predicate predicate
     */
    void toNetwork(FriendlyByteBuf buffer, T predicate);

    /**
     * A predicate to add custom filter rule to existed ingredient
     * @param <T> predicate type
     */
    interface Predicate<T extends Predicate<T>> {

        /**
         * Test item
         * @param stack item
         * @return true if allowed
         */
        boolean test(@Nullable ItemStack stack);

        /**
         * Get all items depends on input stacks
         * @param input input stacks
         * @return all allowed items
         */
        List<ItemStack> getAllStack(List<ItemStack> input);

        /**
         * Get serializer
         * @return serializer
         */
        ICraftPredicateSerializer<T> serializer();
    }
}
