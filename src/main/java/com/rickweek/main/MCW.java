package com.rickweek.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.rickweek.init.MCItems;
import com.rickweek.init.MCSoundEvents;
import com.rickweek.main.proxies.CommonProxy;
import com.rickweek.mobs.MobRegistry;

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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MCItems.init();
		MCItems.register();
		MCSoundEvents.registerSounds();
		
		proxy.registerRenders();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		MobRegistry.register();
		proxy.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
