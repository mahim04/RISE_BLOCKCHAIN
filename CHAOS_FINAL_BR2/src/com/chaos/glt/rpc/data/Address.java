package com.chaos.glt.rpc.data;

import java.util.HashMap;
import java.util.Map;

public class Address 
{
	private String addressId="";
	
	Map<String, Asset> assetsMap = new HashMap<>();

	public Address(String addressId) {
		super();
		this.addressId = addressId;
	}
	
	
	public void addAsset(String name, Asset asset)
	{
		assetsMap.put(name, asset);
	}
	
	public Asset getAsset(String name)
	{
		return assetsMap.get(name);
	}


	public String getAddressId() {
		return addressId;
	}


	public Map<String, Asset> getAssetsMap() {
		return assetsMap;
	}

	
}
