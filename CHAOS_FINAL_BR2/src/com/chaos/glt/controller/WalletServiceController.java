package com.chaos.glt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chaos.glt.rpc.RPCClient;
import com.chaos.glt.rpc.data.Address;
import com.chaos.glt.rpc.data.Response;
import com.chaos.glt.rpc.data.TransferAlert;

@RestController
public class WalletServiceController 
{

	RPCClient rpc = new RPCClient();
	
    //@RequestMapping(method=RequestMethod.GET)
    @RequestMapping("/getWalletDetails")
    public @ResponseBody Address getWalletDetails(@RequestParam(value="walletId", required=true) String walletId) 
    {
    	Address add = rpc.getAddressBalance(walletId);
    	return add;
    }
    
    
    //@RequestMapping(method=RequestMethod.GET)
    @RequestMapping("/transferAmmount")
    public @ResponseBody Response transferAmmount(@RequestParam(value="fromwalletId", required=true) String fromWalletId,
    		@RequestParam(value="towalletId", required=true) String toWalletId, @RequestParam(value="amount", required=true) Double amount) 
    {
    	rpc.transferCashFunds(fromWalletId, toWalletId, amount);
    	
    	Response resp = new Response();
    	resp.setResponseStr("SUCCESS");
    	
    	return resp;
    }

  
    @RequestMapping("/transferAlert")
    public @ResponseBody TransferAlert getLastTransaction() 
    {
    	TransferAlert ta  = rpc.getLastTransaction();
    	
    	return ta;
    }

}