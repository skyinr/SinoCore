package games.moegirl.sinocraft.sinocore;

import games.moegirl.sinocraft.sinocore.api.ApiLoader;
import games.moegirl.sinocraft.sinocore.api.SinoCoreAPI;
import games.moegirl.sinocraft.sinocore.api.impl.Crafting;
import games.moegirl.sinocraft.sinocore.api.impl.Network;
import games.moegirl.sinocraft.sinocore.block.SCBlockItems;
import games.moegirl.sinocraft.sinocore.block.SCBlocks;
import games.moegirl.sinocraft.sinocore.block.blockentity.SCBlockEntities;
import games.moegirl.sinocraft.sinocore.crafting.IngredientRegister;
import games.moegirl.sinocraft.sinocore.gui.SCMenus;
import games.moegirl.sinocraft.sinocore.item.SCItems;
import games.moegirl.sinocraft.sinocore.network.SCNetworks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SinoCore.MODID)
public class SinoCore {
    public static final String MODID = "sinocore";

    public SinoCore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SCNetworks.register();
        SCItems.register(bus);
        SCBlocks.register(bus);
        SCBlockItems.register(bus);
        SCBlockEntities.register(bus);

        SCMenus.register(bus);

        bus.addListener(this::onSetup);
        SinoCoreAPI._loadCoreApi(this::registerApi);
    }

    private void onSetup(FMLCommonSetupEvent event) {
        IngredientRegister.register();
    }

    private void registerApi(ApiLoader loader) {
        loader.loadAll(MODID, Crafting.INSTANCE, Network.INSTANCE);
    }
}
