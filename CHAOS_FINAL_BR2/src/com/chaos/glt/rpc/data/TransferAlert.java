package com.chaos.glt.rpc.data;

public class TransferAlert 
{
	private String tranId="";
	private Double amount=0.0;
	
	
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	@Override
	public String toString() {
		return "TransferAlert [tranId=" + tranId + ", amount=" + amount + "]";
	}
	
	
	
	
}
