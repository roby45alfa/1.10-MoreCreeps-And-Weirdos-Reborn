package com.rickweek.main.proxies;

import com.rickweek.init.MCItems;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {
		MCItems.registerRenders();
	}
}
