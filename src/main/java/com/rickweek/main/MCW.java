package com.rickweek.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.rickweek.entities.CREEPSEntityMoney;
import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.proxies.CommonProxy;
import com.rickweek.mobs.MobRegistry;
import com.rickweek.world.WorldGenStructures;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class MCW {

	@Mod.Instance(Reference.MODID)
	public static MCW instance;
	
	@SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
	public static CommonProxy proxy;

	private int count;
    
    public int spittime = 500;
    
    public int currentJailX;
    public int currentJailY;
    public int currentJailZ;
    public boolean jailBuilt;
    
    public int currentfine;
    
    public int creepsTimer;
    
    public static int prisonercount = 0;
    public static int colacount = 0;
    public static int rocketcount = 0;
    public static int floobcount = 0;
    public static int goatcount = 0;
    public static int preachercount = 0;
    public static int cavemancount = 0;
    public static boolean cavemanbuilding = false;
    
    public static Item partBubble, partWhite, partRed, partBlack, partYellow, partBlue, partShrink, partBarf;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MCItems.init();
		MCItems.register();
		MCSoundEvents.registerSounds();
		GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
		
		partBarf = new Item().setUnlocalizedName("partBarf");
		GameRegistry.registerItem(partBarf, "partBarf");
		
		proxy.render();
		proxy.registerRenders();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		MobRegistry.register();
		// proxy.render();
		
		EntityRegistry.registerModEntity(CREEPSEntityMoney.class, "MoneyEnt", 500, this, 40, 1, true);
		
		RenderingRegistry.registerEntityRenderingHandler(CREEPSEntityMoney.class, new RenderSnowball(Minecraft.func_71410_x().func_175598_ae(), MCItems.Money, Minecraft.func_71410_x().func_175599_af()));
		
		proxy.renderModelItem();
		proxy.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
