package games.moegirl.sinocraft.sinocore;

import games.moegirl.sinocraft.sinocore.api.impl.APIRegister;
import games.moegirl.sinocraft.sinocore.common.block.ModBlockItemRegister;
import games.moegirl.sinocraft.sinocore.common.block.ModBlockRegister;
import games.moegirl.sinocraft.sinocore.common.blockentity.ModBlockEntityRegister;
import games.moegirl.sinocraft.sinocore.common.crafting.IngredientRegister;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SinoCore.MODID)
public class SinoCore {
    public static final String MODID = "sinocore";

    public SinoCore() {
        APIRegister.register();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlockRegister.register(bus);
        ModBlockItemRegister.register(bus);
        ModBlockEntityRegister.register(bus);

        bus.addListener(this::onSetup);
    }

    private void onSetup(FMLCommonSetupEvent event) {
        IngredientRegister.register();
    }
}
