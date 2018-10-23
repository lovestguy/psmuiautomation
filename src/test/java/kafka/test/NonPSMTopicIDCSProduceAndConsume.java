package kafka.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.commons.net.util.Base64;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class NonPSMTopicIDCSProduceAndConsume {
	
	OkHttpClient client = new OkHttpClient();
	
	String bearerToken;	
	Response response ;
	String resposebody;
	
	private static String createEncodedText(final String username, final String password) {
		final String pair = username + ":" + password;
		final byte[] encodedBytes = Base64.encodeBase64(pair.getBytes());
		return new String(encodedBytes);
	}
	
	public String getidcsClinetID(String smURL, String identityDomain, String clusterName) throws IOException {

		String clienID = "";
		Request request = new Request.Builder()
				.url("http://"+smURL+".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHPCS/instances/" + clusterName)
				.get().addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		Response response = client.newCall(request).execute();

		String value = response.body().string();
		value = value.substring(1, value.length() - 1);
		String[] keyValuePairs = value.split(",");

		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("serviceLogicalUuid")) {
				clienID = keyValuePairs[i].split(":")[1];

			}

		}
		System.out.println("IDCS Application clientID is......" + clienID.substring(1, clienID.length() - 1));

		return clienID.substring(1, clienID.length() - 1);
	}
	
	public String getPublicToken() throws IOException{
		


		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "scope=urn%3Aopc%3Aidm%3A__myscopes__&grant_type=client_credentials");
		
		Request request = new Request.Builder()
		  .url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/oauth2/v1/token")
		  .post(body)
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .addHeader("authorization", "Basic T0NMT1VEOV9TTV9QTEFURk9STV9BUFBJRDo2YWU4NWJhNi1mY2E3LTQ4MWItYjZmNy1mZWViZDRhYTA2M2M=")
		  .addHeader("accept", "application/json")
		  .build();
		Response response = client.newCall(request).execute();
		String responsestring = response.body().string();
		String bearerToken = responsestring.split(",")[0];
		//System.out.println("public bearer token........"+bearerToken.substring(17, bearerToken.length()-1));

		return bearerToken.substring(17, bearerToken.length()-1);
	}
	
	
	public String[] getclientDetails(String smURL, String identityDomain, String clusterName) throws IOException {

		String clientsecret = "";
		String clientID = getidcsClinetID(smURL, identityDomain, clusterName);
		String publicBearerToken = getPublicToken();

		Request request = new Request.Builder()
				.url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/sm/v1/AppServices/"
						+ identityDomain + "/" + clientID
						+ "_APPID?grant_type=client_credentials&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer&client_id=opcInfra&client_assertion=eyJ4NXQjUzI1NiI6IlFfenVsM01kbWJHX0c5WkdjSjl4Yl85S1hYTHF2MnVZZldnbDNJbmRXSmMiLCJ4NXQiOiJIMUY4VU02LW43Zll6aFFVVnRhYXQ2Z0t3WlkiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczpcL1wvaWRlbnRpdHkub3JhY2xlY2xvdWQuY29tXC8iLCJzdWIiOiJvcGNJbmZyYSIsImlzcyI6Imh0dHBzOlwvXC9pZGVudGl0eS5vcmFjbGVjbG91ZC5jb21cLyIsInRva190eXBlIjoiQ0EiLCJleHAiOjQ2MzM4MzA3MTQsImlhdCI6MTQ3ODE1NzExNCwiY2xpZW50X25hbWUiOiJvcGNJbmZyYSIsImp0aSI6Ijc3NGY0ZGFkLTY0YmQtNDA5Ni1iOTU3LWZkYmI4ZDYxYjQ4NSIsInRlbmFudCI6ImlkY3Mtb3JhY2xlIiwiY2xpZW50X2lkIjoib3BjSW5mcmEifQ.ZEa6Cb9Bwtv1-L0NdTKoUll47MiRaMVGkPbt_8UDeh7k6HUC2M1NnLfJfLmHTgQDaX3aiW6Gfo38ZQ2Z_icj33vv5qxltqLXj0bxEaVGF8hRcXaf1bwxVIAbSPHtoElNdbK_fj7k5Fct2U8P3YtY2QKlWk-_oExmfukEtLRIuyR0VZc_Dg0R6vaneWmm6M8ZHmEstdesbEAPCcVWDbDdyagaDpHF2ts2eJlT7wNMjdKZX04jcJp1MgcdEqkRPqkD_bwkVuZ3JLsgZBti8LFsRqZjM9K1T4kWfy61CPjAGSsfr3zLJkp6XakICB0i5kU1vBVz9B1W5-1n5tnEvZhWaw&scope=urn%3Aopc%3Aidm%3A__myscopes__")
				.get().addHeader("authorization", "Bearer " + publicBearerToken).build();

		Response response = client.newCall(request).execute();
		String responsestring = response.body().string();

		String[] keyValuePairs = responsestring.split(",");

		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("clientSecret")) {
				clientsecret = keyValuePairs[i].split(":")[1];

			}

		}
		clientsecret = clientsecret.substring(1, clientsecret.length() - 1);
		System.out.println("client secret is ........." + clientsecret);
		String scope = "https://" + clientID + ".uscom-central-1.c9dev1.oc9qadev.com:443/dedicatedehcs";
		System.out.println("client scpoe is .........." + scope);

		String[] clientDetails = new String[] { clientID, clientsecret, scope };

		return clientDetails;

	}

	public String genBearerToken(String smURL, String identityDomain, String clusterName, String username,
			String password) throws IOException {

		String[] idcsAppDetails = getclientDetails(smURL, identityDomain, clusterName);

		String basicAuth = createEncodedText(idcsAppDetails[0] + "_APPID", idcsAppDetails[1]);
		//String bearertoken = "";
		String encodeURL = "grant_type=password&username=" + URLEncoder.encode(username, "UTF-8") + "&password="
				+ URLEncoder.encode(password, "UTF-8") + "&scope=" + URLEncoder.encode(idcsAppDetails[2], "UTF-8");

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, encodeURL);
		Request request = new Request.Builder()
				.url("https://" + identityDomain + ".identity.c9dev1.oc9qadev.com:443/oauth2/v1/token").post(body)
				.addHeader("content-type", "application/x-www-form-urlencoded")
				.addHeader("authorization", "Basic " + basicAuth).build();

		Response response = client.newCall(request).execute();

		bearerToken = response.body().string().split(":")[1].split(",")[0];
		bearerToken = bearerToken.substring(1, bearerToken.length() - 1);
		System.out.println("Bearer token is .................  " + bearerToken);

		return bearerToken;
	}
	
	
	public void produceJsonMessages(String smURL, String identityDomain, String clusterName, String topicName,
			String siteName, boolean isPsmTopic, String username, String password) throws IOException {

		String clusterfullname = clusterName + "-" + siteName;
		if (isPsmTopic == true) {
			topicName = identityDomain + "-" + topicName;
		}
		bearerToken = genBearerToken(smURL, identityDomain, clusterName, username, password);
         System.out.println("endpoint is.... ");
         System.out.println("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com/restproxy/topics/"
						+ topicName);
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		RequestBody body = RequestBody.create(mediaType,"{  \"records\": [    {      \"key\": \"name\",      \"value\": \"siva\"    },    {       \"key\": \"company\",      \"value\": \"oracle\"    }  ]}");
	
		Request request = new Request.Builder().url("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com/restproxy/topics/"
						+ topicName)
				.post(body).addHeader("authorization", "Bearer " + bearerToken)
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

        final Buffer buffer = new Buffer();
		
		System.out.println("request URI is "+request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is "+buffer.readUtf8());
		System.out.println("request Headers are "+request.headers());

		
		response = client.newCall(request).execute();
		resposebody = response.body().string();
		System.out.println("Response..."+resposebody);
		System.out.println("Response code..."+response.code());
		System.out.println("Response message..."+response.message());
		
	}

	
	public void creteConsumerInstance(String bearertoken, String clusterName, String siteName,String topicname,boolean isPsmTopic,String identityDomain) throws IOException {
		if (isPsmTopic == true) {
			topicname = identityDomain + "-" + topicname;
		}
		
		// Create consumer instance 
		System.out.println(" Create consumer instance");
		String clusterfullname = clusterName + "-" + siteName;
		String consumergroup = "consumergroup" + ranGen();
		String consumername = "consumername" + ranGen();
		String newconsumername = "\""+consumername+"\"";
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		
		RequestBody body = RequestBody.create(mediaType,
				"{    \"name\":" +newconsumername+ ",    \"format\":\"json\",    \"auto.offset.reset\":\"earliest\",    \"auto.commit.enable\":\"true\"}");
		
		Request request = new Request.Builder()
				.url("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com:443/restproxy/consumers/"
						+ consumergroup)
				.post(body).addHeader("authorization", "Bearer " + bearertoken)
				.addHeader("accept", "application/vnd.kafka.json.v2+json").addHeader("content-type", "application/vnd.kafka.json.v2+json").build();
		
		final Buffer buffer = new Buffer();
		
		System.out.println("request URI is "+request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is "+buffer.readUtf8());
		System.out.println("request Headers are "+request.headers());

		
		Response response = client.newCall(request).execute();
		System.out.println("Response..."+response.body().string());
		System.out.println("Response code..."+response.code());
		System.out.println("Response message..."+response.message());
		
     // Subscribe consumer instance to topic
		System.out.println("Subscribe consumer instance to topic");
		  topicname = "\""+topicname+"\"";

		 body = RequestBody.create(mediaType, "{\"topics\":[\r\n "+topicname+"]}");
		 request = new Request.Builder()
		  .url("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com:443/restproxy/consumers/idcs-15245d97c94445888192e634a85e23a2-"+consumergroup+"/instances/"+consumername+"/subscription")
		  .post(body)
		  .addHeader("authorization", "Bearer " + bearertoken)
		  .addHeader("content-type", "application/vnd.kafka.json.v2+json").addHeader("accept", "application/vnd.kafka.json.v2+json").build();
         // Buffer buffer = new Buffer();

		   System.out.println("request URI is "+request.url());
			request.body().writeTo(buffer);
			System.out.println("request body is "+buffer.readUtf8());
			System.out.println("request Headers are "+request.headers());
		
		 response = client.newCall(request).execute();
		    System.out.println("Response..."+response.body().string());
			System.out.println("Response code..."+response.code());
			System.out.println("Response message..."+response.message());
			

			
			// consume messages
			System.out.println("consume messages");
			 request = new Request.Builder()
					  .url("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com:443/restproxy/consumers/idcs-15245d97c94445888192e634a85e23a2-"+consumergroup+"/instances/"+consumername+"/records")
					  .get()
					  .addHeader("authorization","Bearer " + bearertoken)
					  .addHeader("accept", "application/vnd.kafka.json.v2+json")
					  .build();
			        // final Buffer buffer = new Buffer();

			 System.out.println("request URI is "+request.url());
				
				System.out.println("request Headers are "+request.headers());
					 response = client.newCall(request).execute();
					 System.out.println("Response..."+response.body().string());
						System.out.println("Response code..."+response.code());
						System.out.println("Response message..."+response.message());
						
						response = client.newCall(request).execute();
						 System.out.println("Response..."+response.body().string());
							System.out.println("Response code..."+response.code());
							System.out.println("Response message..."+response.message());
							
						
						
						
	}
	
	
	@Test
	public void testProduceJsonMessagesOnPSMtopic() throws IOException {

		produceJsonMessages("adc00lsp", "idcs-15245d97c94445888192e634a85e23a2", "testpod1842", "Test123",
				"kafkaden13", true, "manoj.sankar@oracle.com", "Welc0me1234#");
		creteConsumerInstance(bearerToken, "testpod1842", "kafkaden13","Test123",true,"idcs-15245d97c94445888192e634a85e23a2");		

	}
	@Test
	public void testProduceJsonMessagesOnNonPSMtopic() throws IOException{
		produceJsonMessages("adc00lsp", "idcs-15245d97c94445888192e634a85e23a2", "testpod1842", "testtopic",
				"kafkaden13", false, "manoj.sankar@oracle.com", "Welc0me1234#");
		creteConsumerInstance(bearerToken, "testpod1842", "kafkaden13","testtopic",false,"idcs-15245d97c94445888192e634a85e23a2");		
		}
	@Test
	public void testProduceJsonMessageswithNoAccess() throws IOException{
		produceJsonMessages("adc00lsp","idcs-15245d97c94445888192e634a85e23a2","testpod1842","Test123","kafkaden13",true,"narayana.balusu@oracle.com","Welc0me1234#");
		Assert.assertEquals(resposebody.contains("Not authorized to access topics"), true);
		}
	@Test
	public void testProduceJsonMessagesForNonPSMwithNoAccess() throws IOException{
		produceJsonMessages("adc00lsp","idcs-15245d97c94445888192e634a85e23a2","testpod1842","testtopic","kafkaden13",false,"narayana.balusu@oracle.com","Welc0me1234#");
		Assert.assertEquals(resposebody.contains("Not authorized to access topics"), true);

		}
	
	public static String ranGen(){
		Random rand = new Random();
		return String.valueOf(rand.nextInt(10000));
	
	
	}
	public static void main(String[] args) throws IOException {
		
		System.out.println(ranGen());
		
	}
	}


