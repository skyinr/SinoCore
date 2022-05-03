package games.moegirl.sinocraft.sinocore.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

/**
 * All capability
 */
public class SCCapabilities {

    public static final Capability<IHeat> HEAT = CapabilityManager.get(new CapabilityToken<>() {
    });
}
