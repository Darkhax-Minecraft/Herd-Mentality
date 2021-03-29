package net.darkhax.herdmentality;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class Configuration {
    
    private final ForgeConfigSpec spec;
    
    private final BooleanValue ignoreNeutralMobs;
    
    public Configuration() {
        
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        // General Configs
        builder.comment("General settings for the mod.");
        builder.push("general");
        
        builder.comment("Should neutral mobs like pigs not be affected?");
        this.ignoreNeutralMobs = builder.define("ignoreNeutralMobs", false);
        
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec () {
        
        return this.spec;
    }
    
    public boolean shouldIgnoreNeutralMobs () {
        
        return this.ignoreNeutralMobs.get();
    }
}