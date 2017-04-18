package com.rickweek.mobs;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.rickweek.entities.CREEPSEntityBigBaby;
import com.rickweek.entities.CREEPSEntityBlorp;
import com.rickweek.entities.CREEPSEntityInvisibleMan;
import com.rickweek.entities.CREEPSEntityLolliman;
import com.rickweek.entities.CREEPSEntityMummy;
import com.rickweek.entities.CREEPSEntityRockMonster;
import com.rickweek.main.MCW;
import com.rickweek.mob.renders.CREEPSRenderBabyMummy;
import com.rickweek.mob.renders.CREEPSRenderBigBaby;
import com.rickweek.mob.renders.CREEPSRenderBlorp;
import com.rickweek.mob.renders.CREEPSRenderInvisibleMan;
import com.rickweek.mob.renders.CREEPSRenderLolliman;
import com.rickweek.mob.renders.CREEPSRenderRockMonster;
import com.rickweek.models.CREEPSModelBigBaby;
import com.rickweek.models.CREEPSModelBlorp;
import com.rickweek.models.CREEPSModelLolliman;
import com.rickweek.models.CREEPSModelMummy;
import com.rickweek.models.CREEPSModelRockMonster;

public class MobRegistry {

	public MobRegistry() {}
	  
	  public static void register() {
	    registerRender();
	    registerEntity();
	  }
	  
	  public static void registerRender() {
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityMummy.class, new CREEPSRenderBabyMummy(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  // TODO RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBabyMummy.class, new CREEPSRenderBabyMummy(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityLolliman.class, new CREEPSRenderLolliman(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelLolliman(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBlorp.class, new CREEPSRenderBlorp(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelBlorp(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityBigBaby.class, new CREEPSRenderBigBaby(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelBigBaby(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityRockMonster.class, new CREEPSRenderRockMonster(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelRockMonster(0.5F), 0.5F));
		  RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityInvisibleMan.class, new CREEPSRenderInvisibleMan(Minecraft.getMinecraft().getRenderManager(), new CREEPSModelMummy(0.5F), 0.5F));
	  }
	  
	  public static void registerEntity() {
		  EntityRegistry.registerModEntity(CREEPSEntityMummy.class, "mummy", 300, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityMummy.class, 11, 1, 1, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.DRY));
		    
		  EntityRegistry.registerModEntity(CREEPSEntityLolliman.class, "lolliman", 301, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityLolliman.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		    
		  EntityRegistry.registerModEntity(CREEPSEntityBlorp.class, "blorp", 302, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityBlorp.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		    
		  EntityRegistry.registerModEntity(CREEPSEntityBigBaby.class, "bigbaby", 303, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityBigBaby.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		  
		  EntityRegistry.registerModEntity(CREEPSEntityRockMonster.class, "rockmonster", 304, MCW.instance, 64, 1, true, 16250871, 2106401);
		    EntityRegistry.addSpawn(CREEPSEntityRockMonster.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
		    
		  EntityRegistry.registerModEntity(CREEPSEntityInvisibleMan.class, "invisibleman", 305, MCW.instance, 64, 1, true, 16250871, 2106401);
			EntityRegistry.addSpawn(CREEPSEntityInvisibleMan.class, 11, 1, 1, EnumCreatureType.MONSTER, new Biome[0]);
	  }
	
}
