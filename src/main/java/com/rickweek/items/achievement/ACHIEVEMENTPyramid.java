package com.rickweek.items.achievement;

import net.minecraft.item.Item;

public class ACHIEVEMENTPyramid extends Item
{

    public ACHIEVEMENTPyramid(String unlocalizedName, String registryName) {
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
        maxStackSize = 1;
    }
}