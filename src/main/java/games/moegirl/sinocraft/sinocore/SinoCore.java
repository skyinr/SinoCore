package games.moegirl.sinocraft.sinocore;

import games.moegirl.sinocraft.sinocore.common.crafting.IngredientRegister;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SinoCore.MODID)
public class SinoCore {
    public static final String MODID = "sinocore";

    public SinoCore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::onSetup);
    }

    private void onSetup(FMLCommonSetupEvent event) {
        IngredientRegister.register();
    }
}
