package com.rickweek.init;

import com.rickweek.items.Blorpcola;
import com.rickweek.items.CREEPSItemBabyJarEmpty;
import com.rickweek.items.CREEPSItemBabyJarFull;
import com.rickweek.items.CREEPSItemBandAid;
import com.rickweek.items.CREEPSItemBattery;
import com.rickweek.items.CREEPSItemEvilEgg;
import com.rickweek.items.CREEPSItemRam16K;
import com.rickweek.main.Reference;
import com.rickweek.main.utils.Utils;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MCItems {
	
	public static Item Blorpcola;
	public static Item Bandaid;
	public static Item EmpyJar;
	public static Item FullJar;
	public static Item Ram16K;
	public static Item Battery;
	public static Item EvilEgg;
	
	public static void init() {
		Blorpcola = new Blorpcola("Blorpcola", "Blorpcola");
		Bandaid = new CREEPSItemBandAid("Bandaid", "Bandaid");
		EmpyJar = new CREEPSItemBabyJarEmpty("EmpyJar", "EmpyJar");
		FullJar = new CREEPSItemBabyJarFull("FullJar", "FullJar");
		Ram16K = new CREEPSItemRam16K("Ram16K", "Ram16K");
		Battery = new CREEPSItemBattery("Battery", "Battery");
		EvilEgg = new CREEPSItemEvilEgg("EvilEgg", "EvilEgg");
	}
	
	public static void register() {
		registerItem(Blorpcola);
		registerItem(Bandaid);
		registerItem(EmpyJar);
		registerItem(FullJar);
		registerItem(Ram16K);
		registerItem(Battery);
		registerItem(EvilEgg);
	}
	
	public static void registerRenders() {
		registerRender(Blorpcola);
		registerRender(Bandaid);
		registerRender(EmpyJar);
		registerRender(FullJar);
		registerRender(Ram16K);
		registerRender(Battery);
		registerRender(EvilEgg);
	}
	
	public static void registerItem(Item item) {
		GameRegistry.register(item);
		
	}
	
	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}

}
