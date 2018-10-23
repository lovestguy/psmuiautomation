package kafka.idcs;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.util.Base64;
import org.testng.annotations.BeforeTest;

import kafka.bean.BearerTokenGen;
import kafka.bean.OAuthClient;
import kafka.bean.TopicDetails;
import kafka.bean.TopicIdcsDetails;
import kafka.utils.KafkaTestUtils;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class IDCSKafkaTestUtils extends KafkaTestUtils {
	
	public String bearerToken;	
	Response response ;
	String resposebody;
	boolean isSearchUser= false;
	String clientappid;
	String clientappname;
	String clientsecret;
	String idcstopicurl;
	String scope;
	IdcsTopicDetails itd =null;
	private String responsebody;
	public IdcsTopicDetails getIdcsTopicDetails(String topic,String idcsuser,String password) throws IOException{

		itd = new IdcsTopicDetails();
		TopicDetails td = getTopicInstanceDetails(topic);
		itd.setClient_appid(td.getServiceLogicalUuid());
		itd.setClientid(td.getIdcs().getCustomer().getAppID());
		itd.setTopicurl(td.getRestProxyUri());
		
		request = new Request.Builder()
				.url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/sm/v1/AppServices/"
						+ identityDomain + "/" +td.getServiceLogicalUuid()
						+ "_APPID?grant_type=client_credentials&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer&client_id=opcInfra&client_assertion=eyJ4NXQjUzI1NiI6IlFfenVsM01kbWJHX0c5WkdjSjl4Yl85S1hYTHF2MnVZZldnbDNJbmRXSmMiLCJ4NXQiOiJIMUY4VU02LW43Zll6aFFVVnRhYXQ2Z0t3WlkiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczpcL1wvaWRlbnRpdHkub3JhY2xlY2xvdWQuY29tXC8iLCJzdWIiOiJvcGNJbmZyYSIsImlzcyI6Imh0dHBzOlwvXC9pZGVudGl0eS5vcmFjbGVjbG91ZC5jb21cLyIsInRva190eXBlIjoiQ0EiLCJleHAiOjQ2MzM4MzA3MTQsImlhdCI6MTQ3ODE1NzExNCwiY2xpZW50X25hbWUiOiJvcGNJbmZyYSIsImp0aSI6Ijc3NGY0ZGFkLTY0YmQtNDA5Ni1iOTU3LWZkYmI4ZDYxYjQ4NSIsInRlbmFudCI6ImlkY3Mtb3JhY2xlIiwiY2xpZW50X2lkIjoib3BjSW5mcmEifQ.ZEa6Cb9Bwtv1-L0NdTKoUll47MiRaMVGkPbt_8UDeh7k6HUC2M1NnLfJfLmHTgQDaX3aiW6Gfo38ZQ2Z_icj33vv5qxltqLXj0bxEaVGF8hRcXaf1bwxVIAbSPHtoElNdbK_fj7k5Fct2U8P3YtY2QKlWk-_oExmfukEtLRIuyR0VZc_Dg0R6vaneWmm6M8ZHmEstdesbEAPCcVWDbDdyagaDpHF2ts2eJlT7wNMjdKZX04jcJp1MgcdEqkRPqkD_bwkVuZ3JLsgZBti8LFsRqZjM9K1T4kWfy61CPjAGSsfr3zLJkp6XakICB0i5kU1vBVz9B1W5-1n5tnEvZhWaw&scope=urn%3Aopc%3Aidm%3A__myscopes__")
				.get().addHeader("authorization",getPublicToken()).build();
		
		 response = client.newCall(request).execute();
		String[] keyValuePairs = response.body().string().split(",");
		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("clientSecret")) {
				clientsecret = keyValuePairs[i].split(":")[1];
			}
		}
		itd.setClient_secret(clientsecret.substring(1, clientsecret.length() - 1));
		itd.setBasicauth("Basic "+createBasicAuth(itd.getClient_appid()+ "_APPID", itd.getClient_secret()));
        itd.setScope("https://"+itd.getClient_appid() + ".uscom-central-1.c9dev1.oc9qadev.com:443/"+identityDomain+"-"+topic); 
		
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=password&username=" + URLEncoder.encode(idcsuser, "UTF-8") + "&password="
				+ URLEncoder.encode(password, "UTF-8") + "&scope=" + URLEncoder.encode(itd.getScope(), "UTF-8"));
		
		 request = new Request.Builder()
				.url("https://" + identityDomain + ".identity.c9dev1.oc9qadev.com:443/oauth2/v1/token").post(body)
				.addHeader("content-type", "application/x-www-form-urlencoded")
				.addHeader("authorization", itd.getBasicauth()).build();
		 
		 response = client.newCall(request).execute();
		 String responsebody = response.body().string();
			BearerTokenGen btg = gson.fromJson(responsebody, BearerTokenGen.class);
			
			itd.setBearertoken("Bearer "+btg.getAccess_token());
			
			
			 body = RequestBody.create(mediaType, "grant_type=password&username=" + URLEncoder.encode(idcsuser, "UTF-8") + "&password="
						+ URLEncoder.encode(password, "UTF-8") + "&scope=urn%3Aopc%3Aidm%3A__myscopes__");
			Request request = new Request.Builder()
					.url("https://" + identityDomain + ".identity.c9dev1.oc9qadev.com:443/oauth2/v1/token").post(body)
					.addHeader("content-type", "application/x-www-form-urlencoded")
					.addHeader("authorization", itd.getBasicauth()).build();
			 response = client.newCall(request).execute();
			  responsebody = response.body().string();
				 btg = gson.fromJson(responsebody, BearerTokenGen.class);
			
				 itd.setSearchbearertoken("Bearer "+btg.getAccess_token());

		return itd;
	}

	public String getUsergrantId(String username) throws IOException{
		    username =   "\"userName eq \\\""+username+"\\\"\"";

		MediaType mediaType = MediaType.parse("application/scim+json");
		RequestBody body = RequestBody.create(mediaType, "{\"filter\":" + username
				+ ",\"schemas\":[\"urn:ietf:params:scim:api:messages:2.0:SearchRequest\"]}");
		Request request = new Request.Builder()
		  .url("https://"+identityDomain+".identity.c9dev1.oc9qadev.com:443/admin/v1/Users/.search")
		  .post(body)
		  .addHeader("accept", "application/scim+json")
		  .addHeader("authorization", itd.getSearchbearertoken())
		  .addHeader("content-type", "application/scim+json")
		  .build();

			 response = client.newCall(request).execute();
			  String responsebody = response.body().string();
		 String grantvalue = null;
	        String[] ress = responsebody.split(",");
	        for(String st : ress){
	        	if (st.contains("\"id\"")){ 
	        		 grantvalue =st.split(":")[1].substring(1, st.split(":")[1].length()-1);
	        		break;
	        	}
	        }
		     System.out.println("grant value...."+grantvalue);
		     grantvalue = "\""+ grantvalue+ "\"";

		
		return grantvalue;
		
	}
	public void grantProducerRole(String topic,String username) throws IOException{
		
		String grantid=getUsergrantId(username);
		String entitlementid =getUserRoleEntitlementId(topic,"Producer");
	     grantRole(grantid, entitlementid);
	}
    public void grantConsumerRole(String topic,String username) throws IOException{
		
		String grantid=getUsergrantId(username);
		String entitlementid =getUserRoleEntitlementId(topic,"Consumer");
	     grantRole(grantid, entitlementid);
	}
	private void grantRole(String grantid, String entitlementid) throws IOException {
		String clientid = "\""+itd.getClientid()+ "\"";

			MediaType mediaType = MediaType.parse("application/scim+json");

		 RequestBody body = RequestBody.create(mediaType, "{    \"app\":{\"value\":"+clientid

				+ "},    \"entitlement\":{\"attributeName\":\"appRoles\",\"attributeValue\":" + entitlementid
				+ "},    \"grantMechanism\":\"ADMINISTRATOR_TO_USER\",    \"grantee\":{\"value\":" + grantid
				+ ",\"type\":\"User\"    },    \"schemas\":[\"urn:ietf:params:scim:schemas:oracle:idcs:Grant\"    ]}");
			  request = new Request.Builder()
					  .url("https://"+identityDomain+".identity.c9dev1.oc9qadev.com:443/admin/v1/Grants/")
					  .post(body)
					  .addHeader("accept", "application/scim+json")
					  .addHeader("authorization", itd.getSearchbearertoken())
					  .addHeader("content-type", "application/scim+json")
					  .build();

				 response = client.newCall(request).execute();
				  responsebody = response.body().string();
	}
	public String getUserRoleEntitlementId(String topic,String role) throws IOException{
		String topicname=null;
		if(role.equalsIgnoreCase("producer")){
		   topicname =   "\"displayName eq \\\""+identityDomain+"-"+topic+"-Producer\\\"\"";
		  }
		if(role.equalsIgnoreCase("consumer")){
			   topicname =   "\"displayName eq \\\""+identityDomain+"-"+topic+"-Consumer\\\"\"";
			  }
		
			MediaType mediaType = MediaType.parse("application/scim+json");

			RequestBody body = RequestBody.create(mediaType,
					"{\"filter\":" + topicname + ",\"schemas\":[\"urn:ietf:params:scim:api:messages:2.0:SearchRequest\"]}");
			     request = new Request.Builder()
					  .url("https://"+identityDomain+".identity.c9dev1.oc9qadev.com:443/admin/v1/AppRoles/.search")
					  .post(body)
					  .addHeader("accept", "application/scim+json")
					  .addHeader("authorization", itd.getSearchbearertoken())
					  .addHeader("content-type", "application/scim+json")
					  .build();
			
				 response = client.newCall(request).execute();
				  responsebody = response.body().string();
				 String	 entitlementvalue = null;
			       String[] ress = responsebody.split(",");
			        for(String st : ress){
			        	if (st.contains("\"id\"")){ 
			        		entitlementvalue =st.split(":")[1].substring(1, st.split(":")[1].length()-1);
			        		break;
			        	}
			        }
			     System.out.println("entitlement value...."+entitlementvalue);
			     
			     entitlementvalue = "\""+ entitlementvalue+ "\"";
			     
			     return entitlementvalue;
			     		
	}
	
	public void AssignAppRole( String topic, String username,String topicowner,String password) throws IOException{
		
	getIdcsTopicDetails(topic,topicowner, password);	
	grantProducerRole(topic,username);
	grantConsumerRole(topic,username);
	}

	
	private static String createBasicAuth(final String username, final String password) {
		final String pair = username + ":" + password;
		final byte[] encodedBytes = Base64.encodeBase64(pair.getBytes());
		return new String(encodedBytes);
	}

	public String getclusteridcsClinetID(String smURL, String identityDomain, String clusterName) throws IOException {

		String clienID = "";
		Request request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
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
	public String gettopicidcsClinetID(String smURL, String identityDomain, String topic) throws IOException {

		String clienID = "";
		Request request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHCS/instances/" + topic)
				.get().addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();
		
		
		
		final Buffer buffer = new Buffer();
		System.out.println("request URI is " + request.url());
		//request.body().writeTo(buffer);
		//System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());
		

		Response response = client.newCall(request).execute();

		String value = response.body().string();
		System.out.println(value);
		value = value.substring(1, value.length() - 1);
		String[] keyValuePairs = value.split(",");

		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("serviceLogicalUuid")) {
				clienID = keyValuePairs[i].split(":")[1];

			}

		}
		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("appID")) {
				clientappid = keyValuePairs[i].split(":")[3];
			}
		}
		System.out.println("IDCS Application clientAPPID is......" + clienID.substring(1, clienID.length() - 1));
		System.out.println("IDCS Application clientID is......" + clientappid.substring(1, clienID.length() - 1));
		return clienID.substring(1, clienID.length() - 1);
	}
	
	public Map<String,String> getIdcsTopicInfo(String smURL, String identityDomain, String topic) throws IOException{
		
		Map<String,String> topicDetails = new HashMap<String,String>();
		TopicDetails td = getTopicInstanceDetails(smURL, identityDomain, topic);
	
		topicDetails.put("clientappanme", td.getServiceLogicalUuid());
		topicDetails.put("clientid", td.getIdcs().getCustomer().getAppID());
		topicDetails.put("topicurl", td.getRestProxyUri());
		topicDetails.put("scope", "https://" +td.getServiceLogicalUuid() + ".uscom-central-1.c9dev1.oc9qadev.com:443/"+identityDomain+"-"+topic);

		Request request = new Request.Builder()
				.url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/sm/v1/AppServices/"
						+ identityDomain + "/" +td.getServiceLogicalUuid()
						+ "_APPID?grant_type=client_credentials&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer&client_id=opcInfra&client_assertion=eyJ4NXQjUzI1NiI6IlFfenVsM01kbWJHX0c5WkdjSjl4Yl85S1hYTHF2MnVZZldnbDNJbmRXSmMiLCJ4NXQiOiJIMUY4VU02LW43Zll6aFFVVnRhYXQ2Z0t3WlkiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczpcL1wvaWRlbnRpdHkub3JhY2xlY2xvdWQuY29tXC8iLCJzdWIiOiJvcGNJbmZyYSIsImlzcyI6Imh0dHBzOlwvXC9pZGVudGl0eS5vcmFjbGVjbG91ZC5jb21cLyIsInRva190eXBlIjoiQ0EiLCJleHAiOjQ2MzM4MzA3MTQsImlhdCI6MTQ3ODE1NzExNCwiY2xpZW50X25hbWUiOiJvcGNJbmZyYSIsImp0aSI6Ijc3NGY0ZGFkLTY0YmQtNDA5Ni1iOTU3LWZkYmI4ZDYxYjQ4NSIsInRlbmFudCI6ImlkY3Mtb3JhY2xlIiwiY2xpZW50X2lkIjoib3BjSW5mcmEifQ.ZEa6Cb9Bwtv1-L0NdTKoUll47MiRaMVGkPbt_8UDeh7k6HUC2M1NnLfJfLmHTgQDaX3aiW6Gfo38ZQ2Z_icj33vv5qxltqLXj0bxEaVGF8hRcXaf1bwxVIAbSPHtoElNdbK_fj7k5Fct2U8P3YtY2QKlWk-_oExmfukEtLRIuyR0VZc_Dg0R6vaneWmm6M8ZHmEstdesbEAPCcVWDbDdyagaDpHF2ts2eJlT7wNMjdKZX04jcJp1MgcdEqkRPqkD_bwkVuZ3JLsgZBti8LFsRqZjM9K1T4kWfy61CPjAGSsfr3zLJkp6XakICB0i5kU1vBVz9B1W5-1n5tnEvZhWaw&scope=urn%3Aopc%3Aidm%3A__myscopes__")
				.get().addHeader("authorization",getPublicToken()).build();
		
		

		Response response = client.newCall(request).execute();
		String responsestring = response.body().string();
		//System.out.println("siva..."+responsestring);
		//TopicIdcsDetails tid = gson.fromJson(responsestring, TopicIdcsDetails.class);
		//System.out.println(tid.toString());
		//System.out.println("from bean secret is ......"+tid.getIdcsArtifacts().getOAuthClient().getClientSecret());

		String[] keyValuePairs = responsestring.split(",");

		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("clientSecret")) {
				clientsecret = keyValuePairs[i].split(":")[1];

			}

		}
		clientsecret = clientsecret.substring(1, clientsecret.length() - 1);
		//System.out.println("client secret is ........." + clientsecret);
		//String scope = "https://" + clientappname + ".uscom-central-1.c9dev1.oc9qadev.com:443/"+identityDomain+"-"+topic;
		//System.out.println("client scpoe is .........." + scope);
		topicDetails.put("secret", clientsecret);
		System.out.println(topicDetails);

		return topicDetails;
		
		//return new String[]{clientappname,clientappid,
};
	
	public String getPublicToken() throws IOException {

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType,
				"scope=urn%3Aopc%3Aidm%3A__myscopes__&grant_type=client_credentials");

		 request = new Request.Builder()
				.url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/oauth2/v1/token")
				.post(body).addHeader("content-type", "application/x-www-form-urlencoded")
				.addHeader("authorization",
						"Basic T0NMT1VEOV9TTV9QTEFURk9STV9BUFBJRDo2YWU4NWJhNi1mY2E3LTQ4MWItYjZmNy1mZWViZDRhYTA2M2M=")
				.addHeader("accept", "application/json").build();
		 response = client.newCall(request).execute();
		String responsestring = response.body().string();
		String bearerToken = responsestring.split(",")[0];
		return "bearer "+bearerToken.substring(17, bearerToken.length() - 1);
	}

	public String[] getclientDetails(String smURL, String identityDomain, String clusterName) throws IOException {

		String clientsecret = "";
		String clientID = getclusteridcsClinetID(smURL, identityDomain, clusterName);

		Request request = new Request.Builder()
				.url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/sm/v1/AppServices/"
						+ identityDomain + "/" + clientID
						+ "_APPID?grant_type=client_credentials&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer&client_id=opcInfra&client_assertion=eyJ4NXQjUzI1NiI6IlFfenVsM01kbWJHX0c5WkdjSjl4Yl85S1hYTHF2MnVZZldnbDNJbmRXSmMiLCJ4NXQiOiJIMUY4VU02LW43Zll6aFFVVnRhYXQ2Z0t3WlkiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczpcL1wvaWRlbnRpdHkub3JhY2xlY2xvdWQuY29tXC8iLCJzdWIiOiJvcGNJbmZyYSIsImlzcyI6Imh0dHBzOlwvXC9pZGVudGl0eS5vcmFjbGVjbG91ZC5jb21cLyIsInRva190eXBlIjoiQ0EiLCJleHAiOjQ2MzM4MzA3MTQsImlhdCI6MTQ3ODE1NzExNCwiY2xpZW50X25hbWUiOiJvcGNJbmZyYSIsImp0aSI6Ijc3NGY0ZGFkLTY0YmQtNDA5Ni1iOTU3LWZkYmI4ZDYxYjQ4NSIsInRlbmFudCI6ImlkY3Mtb3JhY2xlIiwiY2xpZW50X2lkIjoib3BjSW5mcmEifQ.ZEa6Cb9Bwtv1-L0NdTKoUll47MiRaMVGkPbt_8UDeh7k6HUC2M1NnLfJfLmHTgQDaX3aiW6Gfo38ZQ2Z_icj33vv5qxltqLXj0bxEaVGF8hRcXaf1bwxVIAbSPHtoElNdbK_fj7k5Fct2U8P3YtY2QKlWk-_oExmfukEtLRIuyR0VZc_Dg0R6vaneWmm6M8ZHmEstdesbEAPCcVWDbDdyagaDpHF2ts2eJlT7wNMjdKZX04jcJp1MgcdEqkRPqkD_bwkVuZ3JLsgZBti8LFsRqZjM9K1T4kWfy61CPjAGSsfr3zLJkp6XakICB0i5kU1vBVz9B1W5-1n5tnEvZhWaw&scope=urn%3Aopc%3Aidm%3A__myscopes__")
				.get().addHeader("authorization", "Bearer eyJ4NXQjUzI1NiI6Ijg1a3E1MFVBVmNSRDJOUTR6WVZMVDZXbndUZmVidjBhNGV2YUJGMjFqbU0iLCJ4NXQiOiJNMm1hRm0zVllsTUJPbjNHZXRWV0dYa3JLcmsiLCJraWQiOiJTSUdOSU5HX0tFWSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJPQ0xPVUQ5X1NNX1BMQVRGT1JNX0FQUElEIiwidXNlci50ZW5hbnQubmFtZSI6ImlkY3MtY2xvdWRpbmZyYS11c2NvbS1jZW50cmFsLTEiLCJhbHNvVGVuYW50cyI6WyIqIl0sInN1Yl9tYXBwaW5nYXR0ciI6InVzZXJOYW1lIiwiaXNzIjoiaHR0cHM6XC9cL2lkZW50aXR5Lm9yYWNsZWNsb3VkLmNvbVwvIiwidG9rX3R5cGUiOiJBVCIsImNsaWVudF9pZCI6Ik9DTE9VRDlfU01fUExBVEZPUk1fQVBQSUQiLCJhdWQiOlsiaHR0cHM6XC9cL2lkY3MtY2xvdWRpbmZyYS11c2NvbS1jZW50cmFsLTEuaWRlbnRpdHkuYzlkZXYxLm9jOXFhZGV2LmNvbSIsInVybjpvcGM6bGJhYXM6bG9naWNhbGd1aWQ9aWRjcy1jbG91ZGluZnJhLXVzY29tLWNlbnRyYWwtMSJdLCJzdWJfdHlwZSI6ImNsaWVudCIsImNsaWVudEFwcFJvbGVzIjpbIk9QQyBJbmZyYSIsIlVzZXIgVmlld2VyIiwiQXV0aGVudGljYXRlZCBDbGllbnQiLCJDQSBDb25zdW1lciIsIkNyb3NzIFRlbmFudCJdLCJzY29wZSI6InVybjpvcGM6aWRtOnQuYXVkaXQgdXJuOm9wYzppZG06dC5zZWN1cml0eS5jbGllbnQgdXJuOm9wYzppZG06dC5hcHBzZXJ2aWNlcyB1cm46b3BjOmlkbTp0LmdyYW50Y3Jvc3N0ZW5hbnQgdXJuOm9wYzppZG06dC51c2Vydmlld2VyIHVybjpvcGM6aWRtOmcubWV0ZXJpbmcgdXJuOm9wYzppZG06eC5jYS50ZW5hbnQuYWNjZXNzIHVybjpvcGM6aWRtOnQuY2EuY29uc3VtZXIgdXJuOm9wYzppZG06dC5uYW1lZGFwcGFkbWluIHVybjpvcGM6aWRtOnguc2VydmljZW1hbmFnZXIiLCJjbGllbnRfdGVuYW50bmFtZSI6ImlkY3MtY2xvdWRpbmZyYS11c2NvbS1jZW50cmFsLTEiLCJleHAiOjE1Mzg3NDkxODksImlhdCI6MTUzODc0NTU4OSwiY2xpZW50X2d1aWQiOiJkZTExMmMzMjUyODQ0YjJlODAwYzVlNjEwNjA5OWY2NiIsImNsaWVudF9uYW1lIjoiT0NMT1VEOV9TTV9QTEFURk9STSIsInRlbmFudCI6ImlkY3MtY2xvdWRpbmZyYS11c2NvbS1jZW50cmFsLTEiLCJqdGkiOiI1OWQxM2MyMS00MDI2LTQ1NDAtYTI5My02YzE1ZGU0ZjAyZmYifQ.ZgW24KjVA0QZCwhIOGCg0yGgjil34TkjunvIMBIpY1dUU69CmAlz5zGFao34ZpV9QOgC38w5-r8rJtgWGkN3E3fbWk99v1HSyd7XG6fR91Ln5Z_3LDq3QGpE7aLumEaZH8wGjfE0gkHxswM8r1RQaB-oaGG2_Ygh9RtTZUMpSL8").build();

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

	public String[] gettopicclientDetails(String smURL, String identityDomain, String topic) throws IOException {

		String clientsecret = "";
		String clientID = gettopicidcsClinetID(smURL, identityDomain, topic);
		//String publicBearerToken = getPublicToken();

		Request request = new Request.Builder()
				.url("https://idcs-cloudinfra-uscom-central-1.identity.c9dev1.oc9qadev.com:443/sm/v1/AppServices/"
						+ identityDomain + "/" + clientID
						+ "_APPID?grant_type=client_credentials&client_assertion_type=urn%3Aietf%3Aparams%3Aoauth%3Aclient-assertion-type%3Ajwt-bearer&client_id=opcInfra&client_assertion=eyJ4NXQjUzI1NiI6IlFfenVsM01kbWJHX0c5WkdjSjl4Yl85S1hYTHF2MnVZZldnbDNJbmRXSmMiLCJ4NXQiOiJIMUY4VU02LW43Zll6aFFVVnRhYXQ2Z0t3WlkiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczpcL1wvaWRlbnRpdHkub3JhY2xlY2xvdWQuY29tXC8iLCJzdWIiOiJvcGNJbmZyYSIsImlzcyI6Imh0dHBzOlwvXC9pZGVudGl0eS5vcmFjbGVjbG91ZC5jb21cLyIsInRva190eXBlIjoiQ0EiLCJleHAiOjQ2MzM4MzA3MTQsImlhdCI6MTQ3ODE1NzExNCwiY2xpZW50X25hbWUiOiJvcGNJbmZyYSIsImp0aSI6Ijc3NGY0ZGFkLTY0YmQtNDA5Ni1iOTU3LWZkYmI4ZDYxYjQ4NSIsInRlbmFudCI6ImlkY3Mtb3JhY2xlIiwiY2xpZW50X2lkIjoib3BjSW5mcmEifQ.ZEa6Cb9Bwtv1-L0NdTKoUll47MiRaMVGkPbt_8UDeh7k6HUC2M1NnLfJfLmHTgQDaX3aiW6Gfo38ZQ2Z_icj33vv5qxltqLXj0bxEaVGF8hRcXaf1bwxVIAbSPHtoElNdbK_fj7k5Fct2U8P3YtY2QKlWk-_oExmfukEtLRIuyR0VZc_Dg0R6vaneWmm6M8ZHmEstdesbEAPCcVWDbDdyagaDpHF2ts2eJlT7wNMjdKZX04jcJp1MgcdEqkRPqkD_bwkVuZ3JLsgZBti8LFsRqZjM9K1T4kWfy61CPjAGSsfr3zLJkp6XakICB0i5kU1vBVz9B1W5-1n5tnEvZhWaw&scope=urn%3Aopc%3Aidm%3A__myscopes__")
				.get().addHeader("authorization",  getPublicToken()).build();

		Response response = client.newCall(request).execute();
		String responsestring = response.body().string();
		System.out.println("siva..."+responsestring);

		String[] keyValuePairs = responsestring.split(",");

		for (int i = 0; i < keyValuePairs.length - 1; i++) {

			if (keyValuePairs[i].contains("clientSecret")) {
				clientsecret = keyValuePairs[i].split(":")[1];

			}

		}
		clientsecret = clientsecret.substring(1, clientsecret.length() - 1);
		System.out.println("client secret is ........." + clientsecret);
		String scope = "https://" + clientID + ".uscom-central-1.c9dev1.oc9qadev.com:443/"+identityDomain+"-"+topic;
		System.out.println("client scpoe is .........." + scope);

		String[] clientDetails = new String[] { clientID, clientsecret, scope };

		return clientDetails;

	}

	
	public String genclusterBearerToken(String smURL, String identityDomain, String clusterName, String username,
			String password) throws IOException {

		String[] idcsAppDetails = getclientDetails(smURL, identityDomain, clusterName);

		String basicAuth = createBasicAuth(idcsAppDetails[0] + "_APPID", idcsAppDetails[1]);
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
	
	public String gentopicBearerToken(String smURL, String identityDomain, String topic, String username,
			String password) throws IOException {
		
		Map<String,String> idcstopicdetails = getIdcsTopicInfo(smURL, identityDomain, topic);
		String basicAuth = createBasicAuth(idcstopicdetails.get("clientappanme")+ "_APPID", idcstopicdetails.get("secret"));
		
		String encodeURL = "grant_type=password&username=" + URLEncoder.encode(username, "UTF-8") + "&password="
				+ URLEncoder.encode(password, "UTF-8") + "&scope=" + URLEncoder.encode(idcstopicdetails.get("scope"), "UTF-8");
      
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
    
	public String gentopicsearchBearerToken(String smURL, String identityDomain, String topic, String username,
			String password) throws IOException {

		//String[] idcsAppDetails = gettopicclientDetails(smURL, identityDomain, topic);
            
	Map<String,String>	idcstopicdetails = getIdcsTopicInfo(smURL, identityDomain, topic);
		
		String basicAuth = createBasicAuth(idcstopicdetails.get("clientappanme")+ "_APPID", idcstopicdetails.get("secret"));
		String encodeURL = "grant_type=password&username=" + URLEncoder.encode(username, "UTF-8") + "&password="
				+ URLEncoder.encode(password, "UTF-8") + "&scope=urn%3Aopc%3Aidm%3A__myscopes__";
        System.out.println("encodeURL...."+encodeURL);
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

	
	
	public String searchusergenBearerToken(String smURL, String identityDomain, String clusterName, String username,
			String password) throws IOException {

		String[] idcsAppDetails = getclientDetails(smURL, identityDomain, clusterName);

		String basicAuth = createBasicAuth(idcsAppDetails[0] + "_APPID", idcsAppDetails[1]);
		
		//String basicAuth = createEncodedText("D5164C73F4904C5A8A55817BCE90DD98_APPID", "62c17b7f-ab14-43f0-90de-a1055ff9f310");
        System.out.println("basicAuth....."+basicAuth);
		// String bearertoken = "";
		//String encodeURL = "grant_type=password&username=" + URLEncoder.encode(username, "UTF-8") + "&password="
				//+ URLEncoder.encode(password, "UTF-8") + "&scope=urn:opc:idm:__myscopes__";
		String encodeURL = "username=manoj.sankar%40oracle.com&scope=urn%3Aopc%3Aidm%3A__myscopes__&grant_type=password&client=Welcome1234%23";

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, encodeURL);
		Request request = new Request.Builder()
				.url("https://" + identityDomain + ".identity.c9dev1.oc9qadev.com:443/oauth2/v1/token").post(body)
				.addHeader("content-type", "application/x-www-form-urlencoded")
				.addHeader("authorization", "Basic " + basicAuth).build();
		
		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());
		

		Response response = client.newCall(request).execute();
		String ss = response.body().string();
		System.out.println("sivaaaa   "+ss);
		
		
		

		bearerToken = ss.split(":")[1].split(",")[0];
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
		bearerToken = genclusterBearerToken(smURL, identityDomain, clusterName, username, password);
		System.out.println("endpoint is.... ");
		System.out.println(
				"https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com/restproxy/topics/" + topicName);
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		RequestBody body = RequestBody.create(mediaType,
				"{  \"records\": [    {      \"key\": \"name\",      \"value\": \"siva\"    },    {       \"key\": \"company\",      \"value\": \"oracle\"    }  ]}");

		Request request = new Request.Builder()
				.url("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com/restproxy/topics/"
						+ topicName)
				.post(body).addHeader("authorization", "Bearer " + bearerToken)
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		response = client.newCall(request).execute();
		resposebody = response.body().string();
		System.out.println("Response..." + resposebody);
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

	}

	
	public void produceJsonMessagesonTopic(String smURL, String identityDomain, String topic, String username, String password) throws IOException {

		
		//bearerToken = gentopicBearerToken(smURL, identityDomain, topic, username, password);
		
		
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		RequestBody body = RequestBody.create(mediaType,
				"{  \"records\": [    {      \"key\": \"name\",      \"value\": \"siva\"    },    {       \"key\": \"company\",      \"value\": \"oracle\"    }  ]}");

		Request request = new Request.Builder()
				.url(getIdcsTopicInfo(smURL, identityDomain,topic).get("topicurl"))
				.post(body).addHeader("authorization", itd.getBearertoken())
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		response = client.newCall(request).execute();
		resposebody = response.body().string();
		System.out.println("Response..." + resposebody);
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

	}
	public void produceJsonMessagesonTopic( String topic) throws IOException {
		
		
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		RequestBody body = RequestBody.create(mediaType,
				"{  \"records\": [    {      \"key\": \"name\",      \"value\": \"siva\"    },    {       \"key\": \"company\",      \"value\": \"oracle\"    }  ]}");

		Request request = new Request.Builder()
				.url(getIdcsTopicInfo(smURL, identityDomain,topic).get("topicurl"))
				.post(body).addHeader("authorization", itd.getBearertoken())
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		response = client.newCall(request).execute();
		resposebody = response.body().string();
		System.out.println("Response..." + resposebody);
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

	}

	
	public void creteConsumerInstance(String bearertoken, String clusterName, String siteName, String topicname,
			boolean isPsmTopic, String identityDomain) throws IOException {
		if (isPsmTopic == true) {
			topicname = identityDomain + "-" + topicname;
		}

		// Create consumer instance
		System.out.println(" Create consumer instance");
		String clusterfullname = clusterName + "-" + siteName;
		String consumergroup = "consumergroup" + ranGen();
		String consumername = "consumername" + ranGen();
		String newconsumername = "\"" + consumername + "\"";
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");

		RequestBody body = RequestBody.create(mediaType, "{    \"name\":" + newconsumername
				+ ",    \"format\":\"json\",    \"auto.offset.reset\":\"earliest\",    \"auto.commit.enable\":\"true\"}");

		Request request = new Request.Builder()
				.url("https://" + clusterfullname + ".uscom-central-1.c9dev1.oc9qadev.com:443/restproxy/consumers/"
						+ consumergroup)
				.post(body).addHeader("authorization", "Bearer " + bearertoken)
				.addHeader("accept", "application/vnd.kafka.json.v2+json")
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		Response response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		// Subscribe consumer instance to topic
		System.out.println("Subscribe consumer instance to topic");
		topicname = "\"" + topicname + "\"";

		body = RequestBody.create(mediaType, "{\"topics\":[ " + topicname + "]}");
		request = new Request.Builder()
				.url("https://" + clusterfullname
						+ ".uscom-central-1.c9dev1.oc9qadev.com:443/restproxy/consumers/idcs-15245d97c94445888192e634a85e23a2-"
						+ consumergroup + "/instances/" + consumername + "/subscription")
				.post(body).addHeader("authorization", "Bearer " + bearertoken)
				.addHeader("content-type", "application/vnd.kafka.json.v2+json")
				.addHeader("accept", "application/vnd.kafka.json.v2+json").build();
		// Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		// consume messages
		System.out.println("consume messages");
		request = new Request.Builder()
				.url("https://" + clusterfullname
						+ ".uscom-central-1.c9dev1.oc9qadev.com:443/restproxy/consumers/idcs-15245d97c94445888192e634a85e23a2-"
						+ consumergroup + "/instances/" + consumername + "/records")
				.get().addHeader("authorization", "Bearer " + bearertoken)
				.addHeader("accept", "application/vnd.kafka.json.v2+json").build();
		// final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());

		System.out.println("request Headers are " + request.headers());
		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

	}
	public void creteConsumerInstanceontopicApp(String topic) throws IOException {
		

		// Create consumer instance
		System.out.println(" Create consumer instance");
		String consumergroup = "consumergroup" + ranGen();
		String consumername = "consumername" + ranGen();
		String newconsumername = "\"" + consumername + "\"";
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");

		RequestBody body = RequestBody.create(mediaType, "{    \"name\":" + newconsumername
				+ ",    \"format\":\"json\",    \"auto.offset.reset\":\"earliest\",    \"auto.commit.enable\":\"true\"}");

		Request request = new Request.Builder()
				.url(itd.getTopicurl()
						+ consumergroup)
				.post(body).addHeader("authorization", itd.getBearertoken())
				.addHeader("accept", "application/vnd.kafka.json.v2+json")
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		Response response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		// Subscribe consumer instance to topic
		System.out.println("Subscribe consumer instance to topic");
		//topicname = "\"" + topicname + "\"";

		//body = RequestBody.create(mediaType, "{\"topics\":[ " + topicname + "]}");
		request = new Request.Builder()
				.url(itd.getTopicurl()
						+ consumergroup + "/instances/" + consumername + "/subscription")
				.post(body).addHeader("authorization", itd.getBearertoken())
				.addHeader("content-type", "application/vnd.kafka.json.v2+json")
				.addHeader("accept", "application/vnd.kafka.json.v2+json").build();
		// Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		// consume messages
		System.out.println("consume messages");
		request = new Request.Builder()
				.url(itd.getTopicurl()
						+ consumergroup + "/instances/" + consumername + "/records")
				.get().addHeader("authorization", itd.getBearertoken())
				.addHeader("accept", "application/vnd.kafka.json.v2+json").build();
		// final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());

		System.out.println("request Headers are " + request.headers());
		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

	}

	
	
}
