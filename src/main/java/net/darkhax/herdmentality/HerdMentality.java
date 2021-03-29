package net.darkhax.herdmentality;

import net.darkhax.bookshelf.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(HerdMentality.MOD_ID)
public class HerdMentality {
    
	public static final String MOD_ID = "herdmentality";
	
    public static final INamedTag<EntityType<?>> IGNORED_MOBS = EntityTypeTags.createOptional(new ResourceLocation(HerdMentality.MOD_ID, "ignored_mobs"));
    
    public HerdMentality() {
        
        MinecraftForge.EVENT_BUS.addListener(this::onEntityDamaged);
    }
    
    private void onEntityDamaged (LivingDamageEvent event) {
        
        if (event.getEntityLiving() instanceof MobEntity) {
            
        	if (!IGNORED_MOBS.contains(event.getEntityLiving().getType())) {
        		
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
}