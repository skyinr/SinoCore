package games.moegirl.sinocraft.sinocore.api;

import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;
import games.moegirl.sinocraft.sinocore.api.mixin.IMixins;

public interface ApiLoader {
    void setCrafting(ICrafting api);

    void setMixins(IMixins api);
}
