package kafka.test;

import java.io.IOException;

import org.testng.annotations.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BMCDedicatedTopicProduceAndConsume {
	
	
	
	 BMCDedicatedTopicProduceAndConsume(){
		
		//System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
      //  System.setProperty("http.proxyPort", "80");
		//System.setProperty("https.proxyHost", "www-proxy.us.oracle.com");
     //  System.setProperty("https.proxyPort", "80");
	}
	
	String topicname ="nonpsmtopictest";
	String clustername="idcspsmtest-kafkaden13";
	String identityDomain="idcs-15245d97c94445888192e634a85e23a2";
	String restproxy = "";
	String topicURL="https://10.90.91.119:1080/restproxy/topics/idcs-15245d97c94445888192e634a85e23a2-psmtopic";
	@Test
	public void produceMessages() throws IOException{
		
		
		//System.setProperty("http.proxyHost", "www-proxy.us.oracle.com");
       // System.setProperty("http.proxyPort", "80");
		//System.setProperty("https.proxyHost", "www-proxy.us.oracle.com");
      // System.setProperty("https.proxyPort", "80");

       String certificatesTrustStorePath = "C:/Program Files/Java/jdk1.8.0_131/jre/lib/security/cacerts";
       System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
		
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/vnd.kafka.binary.v2+json");
		RequestBody body = RequestBody.create(mediaType, " {\r\n       \"records\":[\r\n         {\r\n               \"value\":\"rO0ABXQADkhlbGxvIFdvcmxkICkz\",\r\n               \"key\":\"rO0ABXQABktleS05Mw==\"\r\n           }\r\n     ]\r\n  }");
		Request request = new Request.Builder()
		  .url(topicURL)
		  .post(body)
		  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
		  .addHeader("content-type", "application/vnd.kafka.binary.v2+json")
		  .build();

		Response response = client.newCall(request).execute();
		
		
		
		
	}
		
	@Test
	public void getTopic() throws IOException{
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://129.213.106.201:1080/restproxy/topics/bmcnonpsmtopic1")
		  .get()
		  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
		  .build();

		Response response = client.newCall(request).execute();
		
	}
	}


