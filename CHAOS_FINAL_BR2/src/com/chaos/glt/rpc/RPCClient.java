package com.chaos.glt.rpc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.chaos.glt.rpc.data.Address;
import com.chaos.glt.rpc.data.Asset;
import com.chaos.glt.rpc.data.TransferAlert;

public class RPCClient {

	private static final String COMMAND_GET_BALANCE = "getbalance";
	private static final String COMMAND_GET_INFO = "getinfo";
	private static final String COMMAND_GET_NEW_ADDRESS = "getnewaddress";
	private static final String COMMAND_GET_ADDRESS_BALANCE = "getaddressbalances";
	private static final String COMMAND_TRANSFER_FUNDS = "sendassetfrom";
	private static final String COMMAND_GET_TRANSFER_ALERT = "listaddresstransactions";
	
	private static final String ip = "localhost";
	private static final int port = 7336;
	
	public static void main(String arg[])
	{
//		new RPCClient().getAddressBalance("17wpXGDa8NTurnNXSTpDVfGE5caAynq8bHSVWs");
//		new RPCClient().transferCashFunds("17wpXGDa8NTurnNXSTpDVfGE5caAynq8bHSVWs", "16cEeADxJc6tJujyXgBQo83q2vhQnHDymxtrTS", 10.0);
		TransferAlert ta = new RPCClient().getLastTransaction();
		System.out.println("FINAL RESULT " + ta);
		
		
	}

	private JSONObject invokeRPC(String id, String method, List<String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("method", method);
		if (null != params) {
			JSONArray array = new JSONArray();
			array.addAll(params);
			json.put("params", params);
		}
		JSONObject responseJsonObj = null;
		try {
			httpclient.getCredentialsProvider().setCredentials(new AuthScope(ip, port),
					new UsernamePasswordCredentials("multichainrpc", "FxPtkuuw5fNqSUyY9hf9JjD6U6AXhwEcAD7qtrfRgcEi"));
			StringEntity myEntity = new StringEntity(json.toJSONString());
			System.out.println(json.toString());
			HttpPost httppost = new HttpPost("http://"+ip+":" + port);
			httppost.setEntity(myEntity);

			System.out.println("executing request" + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
				// System.out.println(EntityUtils.toString(entity));
			}
			JSONParser parser = new JSONParser();
			responseJsonObj = (JSONObject) parser.parse(EntityUtils.toString(entity));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return responseJsonObj;
	}
	
	
	
	
	private JSONObject invokeRPCWithParams(String id, String method, List<Object> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("method", method);
		if (null != params) {
			JSONArray array = new JSONArray();
			array.addAll(params);
			json.put("params", params);
		}
		JSONObject responseJsonObj = null;
		try {
			httpclient.getCredentialsProvider().setCredentials(new AuthScope(ip, port),
					new UsernamePasswordCredentials("multichainrpc", "FxPtkuuw5fNqSUyY9hf9JjD6U6AXhwEcAD7qtrfRgcEi"));
			StringEntity myEntity = new StringEntity(json.toJSONString());
			System.out.println(json.toString());
			HttpPost httppost = new HttpPost("http://"+ip+":" + port);
			httppost.setEntity(myEntity);

			System.out.println("executing request" + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: " + entity.getContentLength());
				// System.out.println(EntityUtils.toString(entity));
			}
			JSONParser parser = new JSONParser();
			responseJsonObj = (JSONObject) parser.parse(EntityUtils.toString(entity));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return responseJsonObj;
	}
	
	

	public Address getAddressBalance(String account) 
	{
		Address add = new Address(account);
		String[] params = { account };
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_ADDRESS_BALANCE, Arrays.asList(params));
 
		try 
		{
			JSONArray jsonMainArr = (JSONArray) json.get("result");
			for (int i = 0; i < jsonMainArr.size(); i++) {  // **line 2**
			     JSONObject childJSONObject = (JSONObject) jsonMainArr.get(i);
			     String name = (String) childJSONObject.get("name");
			     Double qty     = (Double) childJSONObject.get("qty");
			     System.out.println("Got " +name + "::"+ qty );
			     Asset a = new Asset(name);
			     a.setQuantity(qty);
			     add.addAsset(name, a);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return add;
		
	}
	
	
	//For transfer of funds.
	public Boolean transferCashFunds(String fromAccount, String toAccount, Double amount) 
	{
		try
		{
			Object[] params = { fromAccount, toAccount, "CASH", amount };
			System.out.println("Transfering amount : " + amount);
			JSONObject json = invokeRPCWithParams(UUID.randomUUID().toString(), COMMAND_TRANSFER_FUNDS, Arrays.asList(params));			
			
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	
	//For transfer of funds.
	public TransferAlert getLastTransaction() 
	{
		TransferAlert ta = new TransferAlert();
		try
		{
			Object[] params = { "1ZhKBPuX96gPkiPSCVfJYDa6wgA1vux3w3hVxC", 1};
			JSONObject json = invokeRPCWithParams(UUID.randomUUID().toString(), COMMAND_GET_TRANSFER_ALERT, Arrays.asList(params));			
			JSONArray arr = ((JSONArray)json.get("result"));
			JSONObject resJson = (JSONObject) arr.get(0);
			ta.setTranId(""+resJson.get("txid"));
			ta.setAmount((Double) ((JSONObject)((JSONArray)((JSONObject)(resJson.get("balance"))).get("assets")).get(0)).get("qty"));
		} catch (Exception e) {
			e.printStackTrace();
//			return Boolean.FALSE;
		}
		return ta;
	}

	public String getNewAddress(String account) {
		String[] params = { account };
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_NEW_ADDRESS, Arrays.asList(params));
		return (String)json.get("result");
	}

	public JSONObject getInfo() {
		JSONObject json = invokeRPC(UUID.randomUUID().toString(), COMMAND_GET_INFO, null);
		return (JSONObject)json.get("result");
	}
}