package games.moegirl.sinocraft.sinocore.api.crafting;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Serializer fluid ingredient to json or network buffer
 */
public interface IFluidIngredientSerializer {

    /**
     * Read from network buffer
     * @param buffer network buffer
     * @return fluid ingredient
     */
    IFluidIngredient fromNetwork(FriendlyByteBuf buffer);

    /**
     * Read from json
     * @param json json
     * @return fluid ingredient
     */
    IFluidIngredient fromJson(JsonObject json);

    /**
     * Write ingredient to network buffer
     * @param buffer network buffer
     * @param ingredient fluid ingredient
     */
    void write(FriendlyByteBuf buffer, IFluidIngredient ingredient);

    /**
     * Write ingredient to json
     * @param object json
     * @param ingredient fluid ingredient
     * @return the json object
     */
    JsonObject write(JsonObject object, IFluidIngredient ingredient);
}
