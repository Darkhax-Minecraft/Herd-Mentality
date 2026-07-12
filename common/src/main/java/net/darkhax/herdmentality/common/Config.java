package net.darkhax.herdmentality.common;

import net.darkhax.pricklemc.common.api.annotations.RangedDouble;
import net.darkhax.pricklemc.common.api.annotations.Value;

public class Config {

    @Value(comment = "Should the mod also apply to neutral mobs?")
    public boolean affects_neutral_mobs = true;

    @Value(comment = "How many blocks away should similar mobs be alerted?")
    @RangedDouble(min = 0, max = 512)
    public double range = 8d;
}