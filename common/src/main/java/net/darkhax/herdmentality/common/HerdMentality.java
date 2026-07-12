package net.darkhax.herdmentality.common;

import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HerdMentality {

    public static final String MOD_ID = "herdmentality";
    public static final String MOD_NAME = "Herd Mentality";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final TagKey<EntityType<?>> IGNORED_MOB = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "ignored_mobs"));
    public static final Config CONFIG = ConfigManager.load(MOD_ID, new Config());

    public static void init() {
        LOG.debug("Initializing {}. Neutral={} Range={}", MOD_NAME, CONFIG.affects_neutral_mobs, CONFIG.range);
    }

    public static void onMobHurt(LivingEntity victim, Player attacker) {
        if (!victim.is(IGNORED_MOB) && (CONFIG.affects_neutral_mobs || victim instanceof Enemy)) {
            for (LivingEntity nearby : victim.level().getEntitiesOfClass(victim.getClass(), victim.getBoundingBox().inflate(CONFIG.range))) {
                if (!nearby.isAlliedTo(attacker) && !nearby.is(IGNORED_MOB)) {
                    nearby.setLastHurtByPlayer(attacker, 100);
                    if (nearby instanceof Mob mob) {
                        mob.setTarget(attacker);
                    }
                    if (nearby instanceof IPanic panic) {
                        panic.herdmentality$setPanic(true);
                    }
                }
            }
        }
    }
}