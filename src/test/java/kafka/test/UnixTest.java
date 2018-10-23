package kafka.test;

import java.io.IOException;

import org.testng.annotations.Test;

import kafka.utils.KafkaTestUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UnixTest extends KafkaTestUtils{
	
	
	public static void main(String[] args) {
		String host="kkm00dzy.in.oracle.com";
	    String user="nusivaku";
	    String password="Goaa@6223";
	}
	
	//@Test
	public void test124() throws IOException{
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/SparkContainer/topics/bdcsceden13-default/partition-3/bdcsceden13-default_3_0000000000_0000000000")
		  .delete(null)
		  .addHeader("authorization", "Basic U3RvcmFnZWFkbWluOldlbGNvbWUx")
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "7880545c-dc5d-e4e0-d59d-01b3220aabd1")
		  .build();

		Response response = client.newCall(request).execute();
		
		
	}
	
	@Test
	public void cleanStoragecontainer() throws IOException{
		OkHttpClient client = new OkHttpClient();
		
		

		

		Request request = new Request.Builder()
		  .url("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/SparkContainer")
		  .get()
		  .addHeader("authorization", "Basic U3RvcmFnZWFkbWluOldlbGNvbWUx")
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "fb90af7d-9c82-ffa2-42fc-e79af8e55105")
		  .build();
		Response response = client.newCall(request).execute();
	    String[] str = response.body().string().split("\\n");
	    int i;
	    for(i=0; i<str.length;i++){
	    	System.out.println(i+" line is "+str[i]);
	    	if(str[i].contains("topics")){
	    		
	    	
	    	request = new Request.Builder()
					  .url("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/SparkContainer/"+str[i])
					  .delete(null)
					  .addHeader("authorization", "Basic U3RvcmFnZWFkbWluOldlbGNvbWUx")
					  .addHeader("content-type", "application/json")
					  .addHeader("cache-control", "no-cache")
					  .addHeader("postman-token", "7880545c-dc5d-e4e0-d59d-01b3220aabd1")
					  .build();
		     response = client.newCall(request).execute();
		     System.out.println(response.body());
		     System.out.println(response.isSuccessful());

	    }
	    	
	    }
	    
	   
	}

	@Test
	public void cleanOCIStoragecontainer() throws IOException{
		
		//OkHttpClient client = new OkHttpClient();
		
		System.setProperty("https.proxyHost", "www-proxy.us.oracle.com");
		System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
		System.setProperty("https.proxyPort", "80");
		System.setProperty("http.proxyPort", "80");

		Request request = new Request.Builder()
				  .url("https://swiftobjectstorage.us-phoenix-1.oraclecloud.com/v1/psmsvc1/ociconnector-test")
				  .get()
				  .addHeader("authorization", "Basic c2F0eWEuZG9kZGFAb3JhY2xlLmNvbTpHdkV7MGtdI3dBISYxM3tnbFtRWQ==")

				 // .addHeader("cache-control", "no-cache")
				 // .addHeader("postman-token", "e388be23-eed1-9a8a-29b8-788c951d8b7b")
				  .build();

		
		
		Response response = null;
		try {
			response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    String[] str = response.body().string().split("\\n");
	    int i;
	    for(i=0; i<str.length;i++){
	    	System.out.println(i+" line is "+str[i]);
	    	if(str[i].contains("topics")){
	    		
	    	
	    	request = new Request.Builder()
					  .url("https://swiftobjectstorage.us-phoenix-1.oraclecloud.com/v1/psmsvc1/ociconnector-test/"+str[i])
					  .delete(null)
					  .addHeader("authorization", "Basic c2F0eWEuZG9kZGFAb3JhY2xlLmNvbTpHdkV7MGtdI3dBISYxM3tnbFtRWQ==")
					  .addHeader("content-type", "application/json")
					  .build();
		     response = client.newCall(request).execute();
		     System.out.println(response.body());
		     System.out.println(response.isSuccessful());

	    }
	    	
	    }
	    
	   
	}

	}
	
 