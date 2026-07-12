package net.darkhax.herdmentality.fabric;

import net.darkhax.herdmentality.common.HerdMentality;
import net.fabricmc.api.ModInitializer;

public class HerdMentalityFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        HerdMentality.init();
    }
}