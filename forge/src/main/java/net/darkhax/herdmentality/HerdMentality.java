package net.darkhax.herdmentality;

import net.darkhax.bookshelf.api.util.EntityHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(Constants.MOD_ID)
public class HerdMentality {
    
    private final Configuration configuration = new Configuration();
    public final TagKey<EntityType<?>> IGNORED_MOBS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(Constants.MOD_ID, "ignored_mobs"));
    
    public HerdMentality() {
        
        MinecraftForge.EVENT_BUS.addListener(this::onEntityDamaged);
        ModLoadingContext.get().registerConfig(Type.COMMON, this.configuration.getSpec());
    }
    
    private void onEntityDamaged (LivingDamageEvent event) {

        if (event.getEntity() instanceof Mob target && !target.getType().is(IGNORED_MOBS)) {

            final Entity attacker = event.getSource().getEntity();
            
            if (this.configuration.shouldIgnoreNeutralMobs() && !(target instanceof Enemy)) {
                
                return;
            }
            
            if (attacker instanceof FakePlayer) {
                
                return;
            }
            
            if (attacker instanceof Player player && player.isCreative()) {
                
                return;
            }
            
            if (attacker instanceof LivingEntity livingAttacker) {

                for (final Mob nearby : EntityHelper.getEntitiesInArea(target.getClass(), target.level(), target.blockPosition(), this.configuration.getRange())) {

                    if (!nearby.isAlliedTo(attacker)) {
                        nearby.setLastHurtByMob(livingAttacker);
                    }
                }
            }
        }
    }
}