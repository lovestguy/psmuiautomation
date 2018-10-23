package kafka.test;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class NonPSMTopicProduceandConsume {
	
   String restproxy = "10.90.90.194:1080";
   String topic = "resttopic4";
   String groupname ="sivagroup"+ranGen();
   String domain = "idcs-15245d97c94445888192e634a85e23a2";
   String consumer = "consumer"+ranGen();
   String 	consumername = "\"" + consumer + "\"";

public void produceMessages(String restproxy,String topic) throws IOException{
		
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n    \"records\":[\r\n        {\r\n            \"value\":{\r\n                \"ID\":2,\r\n                \"NAME\":\"Test User-2\"\r\n            }\r\n        }\r\n    ]\r\n}");
		Request request = new Request.Builder()
		  .url("https://"+restproxy+"/restproxy/topics/"+topic)
		  .post(body)
		  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
		  .addHeader("content-type", "application/vnd.kafka.json.v2+json")
		  .addHeader("accept", "application/vnd.kafka.v2+json")
		  .build();
		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}

public void createConsumerInstance(String restproxy) throws IOException{
	
	OkHttpClient client = new OkHttpClient();
	
	MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
	RequestBody body = RequestBody.create(mediaType,
			"{\r\n    \"name\":" +consumername+ ",\r\n    \"format\":\"json\",\r\n    \"auto.offset.reset\":\"earliest\",\r\n    \"auto.commit.enable\":\"true\"\r\n}");
	Request request = new Request.Builder()
	  .url("https://"+restproxy+"/restproxy/consumers/"+groupname )
	  .post(body)
	  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
	  .addHeader("content-type", "application/vnd.kafka.json.v2+json")
	  .addHeader("accept", "application/vnd.kafka.json.v2+json")
	  .build();

	Response response = client.newCall(request).execute();
	System.out.println(response.body().string());

}

public void subscribeTopic(String restproxy,String topic) throws IOException{
	String newtopic =  "\"" + topic + "\"";
	OkHttpClient client = new OkHttpClient();
	MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n   \"topics\":[\r\n   "+newtopic+ "\r\n   ]\r\n}");
	Request request = new Request.Builder()
	  .url("https://"+restproxy+"/restproxy/consumers/"+domain+groupname+"1/instances/"+consumer+"/subscription")
	  .post(body)
	  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
	  .addHeader("content-type", "application/vnd.kafka.json.v2+json")
	  .build();

	Response response = client.newCall(request).execute();
	
	System.out.println(response.body().string());

	
}

@Test
public void  validateJsonProduceConsume() throws IOException{
	produceMessages(restproxy,topic);
	createConsumerInstance("hyderabad");
	subscribeTopic(restproxy,topic);
}


public void consumeMessages() throws IOException{
	
	
	
	OkHttpClient client = new OkHttpClient();

	MediaType mediaType = MediaType.parse("application/vnd.kafka.binary.v2+json");
	RequestBody body = RequestBody.create(mediaType, "{\r\n   \"topics\":[\r\n   \"idcs-b4f1c69c3c064e509560dbfa989d7d2f-sivatopic0821\"\r\n   ]\r\n}");
	Request request = new Request.Builder()
	  .url("https://10.90.90.194:1080/restproxy/consumers/idcs-15245d97c94445888192e634a85e23a2-sivagroup11/instances/siva61911444/records")
	  .get()
	  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
	  .addHeader("content-type", "application/vnd.kafka.json.v2+json")
	  .addHeader("accept", "application/vnd.kafka.json.v2+json")
	  .build();

	Response response = client.newCall(request).execute();
	System.out.println(response.body().string());

}

public static String ranGen(){
	Random rand = new Random();
	return String.valueOf(rand.nextInt(10000));


}

}
