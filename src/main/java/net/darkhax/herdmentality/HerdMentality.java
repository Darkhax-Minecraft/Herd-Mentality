package net.darkhax.herdmentality;

import net.darkhax.bookshelf.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("herdmentality")
public class HerdMentality {
    
    public HerdMentality() {
        
        MinecraftForge.EVENT_BUS.addListener(this::onEntityDamaged);
    }
    
    private void onEntityDamaged (LivingDamageEvent event) {
        
        if (event.getEntityLiving() instanceof MobEntity) {
            
            final MobEntity entity = (MobEntity) event.getEntityLiving();
            
            final Entity attacker = event.getSource().getTrueSource();
            
            if (attacker instanceof LivingEntity) {
                
                for (final MobEntity nearby : EntityUtils.getEntitiesInArea(entity.getClass(), entity.world, entity.getPosition(), 8f)) {
                    
                    nearby.setRevengeTarget((LivingEntity) attacker);
                }
            }
        }
    }
}