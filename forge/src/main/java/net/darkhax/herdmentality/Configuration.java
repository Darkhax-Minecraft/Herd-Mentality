package net.darkhax.herdmentality;

import net.darkhax.herdmentality.config.IConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class Configuration implements IConfig {
    
    private final ForgeConfigSpec spec;
    
    private final BooleanValue ignoreNeutralMobs;
    private final ForgeConfigSpec.IntValue range;
    
    public Configuration() {
        
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        // General Configs
        builder.comment("General settings for the mod.");
        builder.push("general");
        
        builder.comment("Should neutral mobs like pigs not be affected?");
        this.ignoreNeutralMobs = builder.define("ignoreNeutralMobs", false);
        
        builder.comment("The range to notify other mobs.");
        this.range = builder.defineInRange("range", 8, 0, 512);
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec () {
        
        return this.spec;
    }

    @Override
    public boolean shouldIgnoreNeutralMobs () {
        
        return this.ignoreNeutralMobs.get();
    }

    @Override
    public int getRange () {
        
        return this.range.get().intValue();
    }
}