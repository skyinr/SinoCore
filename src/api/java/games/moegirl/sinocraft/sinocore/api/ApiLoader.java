package games.moegirl.sinocraft.sinocore.api;

import games.moegirl.sinocraft.sinocore.api.crafting.ICrafting;
import games.moegirl.sinocraft.sinocore.api.network.INetwork;

public interface ApiLoader {

    void loadAll(String id, ICrafting craftingApi, INetwork networkApi);
}
