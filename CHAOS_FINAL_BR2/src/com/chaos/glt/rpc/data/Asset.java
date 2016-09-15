package com.chaos.glt.rpc.data;

public class Asset 
{
	private String Name="";
	private double quantity=0.0;
	private String assetRef = "";
	

	
	
	public Asset(String name) {
		super();
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getAssetRef() {
		return assetRef;
	}
	public void setAssetRef(String assetRef) {
		this.assetRef = assetRef;
	}
	
}
