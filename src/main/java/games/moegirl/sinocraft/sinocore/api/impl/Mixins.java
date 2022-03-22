package games.moegirl.sinocraft.sinocore.api.impl;

import games.moegirl.sinocraft.sinocore.api.impl.mixin.MBoat;
import games.moegirl.sinocraft.sinocore.api.impl.mixin.MBoatItems;
import games.moegirl.sinocraft.sinocore.api.mixin.IBoatItems;
import games.moegirl.sinocraft.sinocore.api.mixin.IBoats;
import games.moegirl.sinocraft.sinocore.api.mixin.IMixins;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;

public enum Mixins implements IMixins {
    INSTANCE;

    @Override
    public IBoatItems getBoatItem(BoatItem item) {
        return new MBoatItems(item);
    }

    @Override
    public IBoats getBoat(Boat boat) {
        return new MBoat(boat);
    }
}
