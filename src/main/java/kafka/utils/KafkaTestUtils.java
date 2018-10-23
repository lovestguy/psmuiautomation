package kafka.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.client.HttpClient;
import org.testng.Assert;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kafka.bean.KafkaJobStatus;
import kafka.bean.TopicDetails;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class KafkaTestUtils {

	private static final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return new java.security.cert.X509Certificate[] {};
		}
	} };

	private static final SSLContext trustAllSslContext;
	static {
		try {
			trustAllSslContext = SSLContext.getInstance("SSL");
			trustAllSslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			throw new RuntimeException(e);
		}
	}
	private static final SSLSocketFactory trustAllSslSocketFactory = trustAllSslContext.getSocketFactory();

	public static OkHttpClient trustAllSslClient(OkHttpClient client) {
		System.out.println("Using the trustAllSslClient is highly discouraged and should not be used in Production!");
		Builder builder = client.newBuilder();
		builder.sslSocketFactory(trustAllSslSocketFactory, (X509TrustManager) trustAllCerts[0]);
		builder.hostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		return builder.build();
	}

	public OkHttpClient client = trustAllSslClient(new OkHttpClient());
	public Request request = null;
	public Response response = null;
	public Gson gson = new Gson();
	
	//public String smURL="adc01fkh";
	public String smURL="den02bpk";

	//public String identityDomain="idcs-15245d97c94445888192e634a85e23a2";
	//public String identityDomain="idcs-882d635be9db40c499e4b509aff29d1a";
	public String identityDomain="psmsvc1";

	public String topic="oehcs4924";
	public String kafkaname;


	String cluster = "\"" + "oehpcs" + ranGen() + "\"";

 public	   String jsonBodyBasicwithConnect = "{  \"serviceName\":" + cluster + ",  "
			+ "\"serviceDescription\":\"my first test service\", " + " \"serviceVersion\":\"1.1\", "
			+ " \"vmUser\":\"opc\", "
			+ " \"vmPublicKeyText\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDOOVKYC3NI6FQd63NTlEGhvGuk7+H69VCYXLC6JGIhaNQGb0DnEukcDVlONInrY0OFKD1NYFGPwuh+C65mgv3af+fCerUedWZwFKzuo+vNikQ9DOI7OIpCN3YHgZW43OmK51G2hfmi7QFjyNpJdUkw2GQb+IlP3lVAF4cQ5Pf1LZfn8oJVfDpAlZuIqR5MBDcoi/dNEO2a6o+Wm5tCOrkTuOLjOFqdWG0ugAsZyz/KwIZL9/ks4AGeM+RrJr8KA6Ck4XlSG62sMD4ph5GZSXQYsvodJjypC8XnAb6nW5LHEq6KYSooG/UBgzUVW0bsxFQoHO1nGtzZmn0KJd5Gu3rt xperiment\",  "
			+ " \"components\":{\"kafka\":{\"shape\": \"oc1m\"," + "\"kafkaZkClusterSize\": \"1\","
			+ " \"dataStorage\": \"25\"," + "\"deploymentType\": \"Basic\"},"
			+ " \"restprxy\":{\"createRestprxy\": \"true\"," + " \"restprxyClusterSize\": \"1\","
			+ " \"restprxyShape\": \"oc1m\", " + " \"restprxyPassword\": \"Welcome1#\","
			+ " \"restprxyUser\": \"admin\"}," + "\"connect\": {" + "\"connectClusterSize\":\"1\","
			+ " \"connectShape\":\"oc1m\"," + "\"connectUser\":\"admin\", " + "\"connectPassword\":\"Welcome1#\","
			+ "\"createConnect\":\"true\" }" + "}}";
 public 	String jsonBodyRecom = "{  \"serviceName\":" + cluster + ",  "
			+ "\"serviceDescription\":\"my first test service\", " + " \"serviceVersion\":\"1.1\", "
			+ " \"vmUser\":\"opc\", "
			+ " \"vmPublicKeyText\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDOOVKYC3NI6FQd63NTlEGhvGuk7+H69VCYXLC6JGIhaNQGb0DnEukcDVlONInrY0OFKD1NYFGPwuh+C65mgv3af+fCerUedWZwFKzuo+vNikQ9DOI7OIpCN3YHgZW43OmK51G2hfmi7QFjyNpJdUkw2GQb+IlP3lVAF4cQ5Pf1LZfn8oJVfDpAlZuIqR5MBDcoi/dNEO2a6o+Wm5tCOrkTuOLjOFqdWG0ugAsZyz/KwIZL9/ks4AGeM+RrJr8KA6Ck4XlSG62sMD4ph5GZSXQYsvodJjypC8XnAb6nW5LHEq6KYSooG/UBgzUVW0bsxFQoHO1nGtzZmn0KJd5Gu3rt xperiment\",  "
			+ " \"components\":{\"kafka\":{\"shape\": \"oc1m\"," + "\"kafkaClusterSize\": \"3\","
			+ "\"zkClusterSize\": \"1\"," + "\"zkShape\": \"oc1m\"," + " \"dataStorage\": \"25\","
			+ "\"deploymentType\": \"Recommended\"}," + " \"restprxy\":{\"createRestprxy\": \"true\","
			+ " \"restprxyClusterSize\": \"2\"," + " \"restprxyShape\": \"oc1m\", "
			+ " \"restprxyPassword\": \"Welcome1#\"," + " \"restprxyUser\": \"admin\"}" + "}}";
 public 	String jsonBodyBasic = "{  \"serviceName\":"
			+ cluster + ",  "
			+ "\"serviceDescription\":\"my first test service\", "
			+ " \"vmUser\":\"opc\", "
			+ " \"vmPublicKeyText\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDOOVKYC3NI6FQd63NTlEGhvGuk7+H69VCYXLC6JGIhaNQGb0DnEukcDVlONInrY0OFKD1NYFGPwuh+C65mgv3af+fCerUedWZwFKzuo+vNikQ9DOI7OIpCN3YHgZW43OmK51G2hfmi7QFjyNpJdUkw2GQb+IlP3lVAF4cQ5Pf1LZfn8oJVfDpAlZuIqR5MBDcoi/dNEO2a6o+Wm5tCOrkTuOLjOFqdWG0ugAsZyz/KwIZL9/ks4AGeM+RrJr8KA6Ck4XlSG62sMD4ph5GZSXQYsvodJjypC8XnAb6nW5LHEq6KYSooG/UBgzUVW0bsxFQoHO1nGtzZmn0KJd5Gu3rt xperiment\",  "
			+ " \"components\":{\"kafka\":{\"shape\": \"oc1m\"," + "\"kafkaZkClusterSize\": \"1\","
			+ " \"dataStorage\": \"25\"," + "\"deploymentType\": \"Basic\"},"
			+ " \"restprxy\":{\"createRestprxy\": \"true\"," + " \"restprxyClusterSize\": \"1\","
			+ " \"restprxyShape\": \"oc1m\", " + " \"restprxyPassword\": \"Welcome1#\","
			+ " \"restprxyUser\": \"admin\"}" + "}}";
 public 	String jsonBodyBasicidcs = "{  \"serviceName\":" + cluster + ",  "
			+ "\"serviceDescription\":\"my first test service\", " + " \"serviceVersion\":\"1.1\", "
			+ " \"vmUser\":\"opc\", "
			+ " \"useIdcsSecurity\":\"true\", "
			+ " \"vmPublicKeyText\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDOOVKYC3NI6FQd63NTlEGhvGuk7+H69VCYXLC6JGIhaNQGb0DnEukcDVlONInrY0OFKD1NYFGPwuh+C65mgv3af+fCerUedWZwFKzuo+vNikQ9DOI7OIpCN3YHgZW43OmK51G2hfmi7QFjyNpJdUkw2GQb+IlP3lVAF4cQ5Pf1LZfn8oJVfDpAlZuIqR5MBDcoi/dNEO2a6o+Wm5tCOrkTuOLjOFqdWG0ugAsZyz/KwIZL9/ks4AGeM+RrJr8KA6Ck4XlSG62sMD4ph5GZSXQYsvodJjypC8XnAb6nW5LHEq6KYSooG/UBgzUVW0bsxFQoHO1nGtzZmn0KJd5Gu3rt xperiment\",  "
			+ " \"components\":"
			
			+ "{\"kafka\":{\"shape\": \"oc1m\","
			+ "\"kafkaZkClusterSize\": \"1\","
			+ " \"dataStorage\": \"25\"," 
			+ "\"deploymentType\": \"Basic\"},"
			
			+ " \"restprxy\":{\"createRestprxy\": \"true\","
			+ " \"restprxyClusterSize\": \"1\","
			+ " \"restprxyShape\": \"oc1m\", " 
			+ " \"restprxyPassword\": \"Welcome1#\","
			+ " \"restprxyUser\": \"admin\"}" + "}}";

 public	   String jsonBodyBasicwithConnectBMC = "{  \"serviceName\":" + cluster + ",  "
			+ "\"serviceDescription\":\"my first test service\", " + " \"serviceVersion\":\"1.1\", "
			+ " \"vmUser\":\"opc\", "
			+ " \"region\":\"us-phoenix-1\", "
			+ " \"availabilityDomain\":\"KiJR:PHX-AD-2\", "

			+ " \"vmPublicKeyText\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDOOVKYC3NI6FQd63NTlEGhvGuk7+H69VCYXLC6JGIhaNQGb0DnEukcDVlONInrY0OFKD1NYFGPwuh+C65mgv3af+fCerUedWZwFKzuo+vNikQ9DOI7OIpCN3YHgZW43OmK51G2hfmi7QFjyNpJdUkw2GQb+IlP3lVAF4cQ5Pf1LZfn8oJVfDpAlZuIqR5MBDcoi/dNEO2a6o+Wm5tCOrkTuOLjOFqdWG0ugAsZyz/KwIZL9/ks4AGeM+RrJr8KA6Ck4XlSG62sMD4ph5GZSXQYsvodJjypC8XnAb6nW5LHEq6KYSooG/UBgzUVW0bsxFQoHO1nGtzZmn0KJd5Gu3rt xperiment\",  "
			+ " \"components\":{\"kafka\":{\"shape\": \"oc1m\"," + "\"kafkaZkClusterSize\": \"1\","
			+ " \"dataStorage\": \"25\"," + "\"deploymentType\": \"Basic\"},"
			+ " \"restprxy\":{\"createRestprxy\": \"true\"," + " \"restprxyClusterSize\": \"1\","
			+ " \"restprxyShape\": \"oc1m\", " + " \"restprxyPassword\": \"Welcome1#\","
			+ " \"restprxyUser\": \"admin\"}," + "\"connect\": {" + "\"connectClusterSize\":\"1\","
			+ " \"connectShape\":\"oc1m\"," + "\"connectUser\":\"admin\", " + "\"connectPassword\":\"Welcome1#\","
			+ "\"createConnect\":\"true\" }" + "}}";

 
	public static String ranGen() {
		Random rand = new Random();
		return String.valueOf(rand.nextInt(10000));

	}

	public String provisioningDedicatedKafka(String smURL, String identityDomain, String jsonbody)
			throws IOException, InterruptedException {

		System.out.println(cluster);
		MediaType mediaType = MediaType.parse("application/vnd.com.oracle.oracloud.provisioning.Service+json");
		RequestBody body = RequestBody.create(mediaType, jsonbody);

		Request request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHPCS/instances")
				.post(body).addHeader("accept", "application/json")
				.addHeader("content-type", "application/vnd.com.oracle.oracloud.provisioning.Service+json")
				.addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		Response response = client.newCall(request).execute();
		String responsebody = response.body().string();
         System.out.println(responsebody);
		String jobId = responsebody.split(",")[1].split(":")[1];

		jobId = jobId.substring(1, jobId.length() - 3);
		System.out.println("JobId is ....." + jobId);
        boolean isterminationcompleted = false;
		
		while (isterminationcompleted==false){
			Thread.sleep(60*1000);
			request = new Request.Builder()
					.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/activitylog/" + identityDomain
							+ "/job/" + jobId)
					.get().addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();
			
			response = client.newCall(request).execute();
			
			KafkaJobStatus kjs = gson.fromJson(response.body().string(), KafkaJobStatus.class);
			System.out.println("Status of provisioning "+cluster+" is "+kjs.getStatus());
			if(kjs.getStatus().equalsIgnoreCase("SUCCEED")){
				isterminationcompleted=true;}
			
		}
	
		return cluster.substring(1, cluster.length() - 1);
	}

	public TopicDetails getTopicInstanceDetails(String smURL, String identityDomain, String topic) throws IOException {
		smURL = this.smURL;
		identityDomain=this.identityDomain;
		
		request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHCS/instances/" + topic)
				.get().addHeader("accept", "application/json")
				.addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		response = client.newCall(request).execute();
		TopicDetails topicdetails = gson.fromJson(response.body().string(), TopicDetails.class);
		return topicdetails;

	}
	public TopicDetails getTopicInstanceDetails( String topic) throws IOException {
		
		request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHCS/instances/" + topic)
				.get().addHeader("accept", "application/json")
				.addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		response = client.newCall(request).execute();
		String responsebody = response.body().string();
		System.out.println("IDCS topic response is..."+responsebody);
		TopicDetails topicdetails = gson.fromJson(responsebody, TopicDetails.class);
		return topicdetails;

	}

	public String provisioningDedicatedKafkaTopic(String smURL, String identityDomain, String clustername)
			throws IOException, InterruptedException {

		String topic = "oehcs" + ranGen();
		String topicname = "\"" + topic + "\"";

		System.out.println(
				"\n **********Creating topic instance " + topic + " on " + clustername + "*******************");

		MediaType mediaType = MediaType.parse("application/vnd.com.oracle.oracloud.provisioning.Service+json");
		RequestBody body = RequestBody.create(mediaType, " { \"systemName\":" + "\"" + clustername + "\"" + ","
				+ "\"serviceName\":" + topicname + "," + "\"numPartitions\":2," + "\"retentionPeriod\":24,"
				+ "\"serviceDescription\":\"Sample Elastic-search service.\","
				+ "\"serviceLevel\":\"PAAS\",\"meteringFrequency\":\"HOURLY\"," + "\"serviceVersion\":\"0.10\"}");

		Request request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHCS/instances")
				.post(body).addHeader("accept", "application/json")
				.addHeader("content-type", "application/vnd.com.oracle.oracloud.provisioning.Service+json")
				.addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		Response response = client.newCall(request).execute();
		String jobId = response.body().string();
		jobId = jobId.split(",")[1].split(":")[1];

		jobId = jobId.substring(1, jobId.length() - 3);
		System.out.println("JobId is ....." + jobId);

		if (response.code() == 202) {
			String status = "RUNNING";
			while ("RUNNING".equalsIgnoreCase(status)) {
				request = new Request.Builder()
						.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/activitylog/" + identityDomain
								+ "/job/" + jobId)
						.get().addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();
				response = client.newCall(request).execute();
				String res = response.body().string();

				String[] ress = res.split(",");
				for (int i = 0; i < ress.length; i++) {
					if (ress[i].contains("status")) {
						status = ress[i].substring(10, ress[i].length() - 1);
						break;
					}

				}
				Thread.sleep(1000 * 10);
				System.out.println("Status is.." + status);

			}
		}
		return topic;

	}

	public void produceJsonMessages(String topicurl) throws IOException {

		// String fulltopicname = identityDomain+"-"+topic;
		System.out.println("\n **********Produce json message on to the topic *******************");

		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");
		RequestBody body = RequestBody.create(mediaType,
				" { \"records\": [   {     \"key\": \"name\",     \"value\": \"sivakumar\"   },   {      \"key\": \"company\",     \"value\": \"Oracle\"   } ]\r\n}");
		Request request = new Request.Builder().url(topicurl).post(body)
				.addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();
		final Buffer buffer = new Buffer();
		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());
	}

	public void consumingMessages(String restproxy, String topic, String identityDomain) throws IOException {

		// Create consumer instance
		System.out.println("\n ************* Create consumer instance ****************");

		String consumername = "consumername" + ranGen();
		String consumergroup = "consumergroup" + ranGen();
		String consumerURL = "https://" + restproxy + ":1080/restproxy/consumers/" + consumergroup;

		String newconsumername = "\"" + consumername + "\"";
		MediaType mediaType = MediaType.parse("application/vnd.kafka.json.v2+json");

		RequestBody body = RequestBody.create(mediaType, "{    \"name\":" + newconsumername
				+ ",    \"format\":\"json\",    \"auto.offset.reset\":\"earliest\",    \"auto.commit.enable\":\"true\"}");

		Request request = null;

		try {
			request = new Request.Builder().url(consumerURL).post(body)
					.addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
					.addHeader("accept", "application/vnd.kafka.json.v2+json")
					.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Buffer buffer = new Buffer();

		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());

		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());
		
		System.out.println("Trying second time......");
		
		try {
			response = client.newCall(request).execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

		// Subscribe consumer instance to topic
		System.out.println("\n ***************- Subscribe consumer instance to topic -********************");
		// topic = "\""+topic+"\"";
		String fulltopicname = identityDomain + "-" + topic;
		fulltopicname = "\"" + fulltopicname + "\"";

		body = RequestBody.create(mediaType, "{\"topics\":[" + fulltopicname + "]}");
		request = new Request.Builder().url("https://" + restproxy + ":1080/restproxy/consumers/" + identityDomain + "-"
				+ consumergroup + "/instances/" + consumername + "/subscription")

				.post(body).addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();
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
		System.out.println("\n ***************** -consume messages - *********************");
		request = new Request.Builder()
				.url("https://" + restproxy + ":1080/restproxy/consumers/" + identityDomain + "-" + consumergroup
						+ "/instances/" + consumername + "/records")
				.get().addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
				.addHeader("accept", "application/vnd.kafka.json.v2+json")
				.addHeader("content-type", "application/vnd.kafka.json.v2+json").build();

		System.out.println("request URI is " + request.url());

		System.out.println("request Headers are " + request.headers());
		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());

	}

	public void terminateTopicService(String smURL, String identityDomain, String topicname)
			throws IOException, InterruptedException {

		System.out.println("\n *************Terminating topic" + topicname + " ****************");

		MediaType mediaType = MediaType.parse("application/vnd.com.oracle.oracloud.provisioning.Service+json");
		RequestBody body = RequestBody.create(mediaType, "{ }");
		Request request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHCS/instances/" + topicname)
				.put(body).addHeader("accept", "application/json")
				.addHeader("content-type", "application/vnd.com.oracle.oracloud.provisioning.Service+json")
				.addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		System.out.println("request URI is " + request.url());
		System.out.println("request Headers are " + request.headers());
		Response response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());
		Thread.sleep(10 * 1000);
		request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHCS/instances/" + topicname)
				.get().addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();
		response = client.newCall(request).execute();
		System.out.println("Response..." + response.body().string());
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());
		Assert.assertEquals(404, response.code());

	}

	public void terminateDedicatedKafkaService(String smURL, String identityDomain, String kafkaname)
			throws IOException, InterruptedException {

		System.out.println("\n *************Terminating kafka instace  " + kafkaname + " ****************");

		MediaType mediaType = MediaType.parse("application/vnd.com.oracle.oracloud.provisioning.Service+json");
		RequestBody body = RequestBody.create(mediaType, "{ }");
		Request request = new Request.Builder()
				.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/instancemgmt/" + identityDomain
						+ "/services/OEHPCS/instances/" + kafkaname)
				.put(body).addHeader("accept", "application/json")
				.addHeader("content-type", "application/vnd.com.oracle.oracloud.provisioning.Service+json")
				.addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();

		System.out.println("request URI is " + request.url());
		System.out.println("request Headers are " + request.headers());
		Response response = client.newCall(request).execute();
		
		String responsebody = response.body().string();
		System.out.println("Response..." + responsebody);
		System.out.println("Response code..." + response.code());
		System.out.println("Response message..." + response.message());
		String jobId = responsebody.split(",")[1].split(":")[1];

		 jobId = jobId.substring(1, jobId.length() - 3);
		System.out.println("JobId is ....." + jobId);
        boolean isterminationcompleted = false;
		
		while (isterminationcompleted==false){
			Thread.sleep(60*1000);
			request = new Request.Builder()
					.url("http://" + smURL + ".us.oracle.com:7103/paas/api/v1.1/activitylog/" + identityDomain
							+ "/job/" + jobId)
					.get().addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=").build();
			
			response = client.newCall(request).execute();
			
			KafkaJobStatus kjs = gson.fromJson(response.body().string(), KafkaJobStatus.class);
			System.out.println("Status of provisioning "+cluster+" is "+kjs.getStatus());
			if(kjs.getStatus().equalsIgnoreCase("SUCCEED")){
				isterminationcompleted=true;}
		
		}
	}

	public void deletePSMJob(String smURL,String jobId) throws IOException{
         
		MediaType mediaType = MediaType.parse("application/vnd.com.oracle.oracloud.provisioning.Service+json");

		RequestBody body = RequestBody.create(mediaType, "");

		 request = new Request.Builder()
		  .url("http://"+smURL+".us.oracle.com:7103/paas/api/v1.1/job/bmc/"+jobId+"?method=Patch&controlFlag=Cancel")
		  .post(body)
		  .addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=")
		  .build();

		 response = client.newCall(request).execute();
		 Assert.assertEquals(response.code(), 200);
		
	}
   
	// connectors
	
	public void getConnectors(String sm, String identitydomain, String topic) throws IOException{
		TopicDetails td = getTopicInstanceDetails(sm, identitydomain, topic);
		String connecturi = td.getConnectUri();
		System.out.println(connecturi+"connectors");
		request = new Request.Builder()
				  .url(connecturi)
				  .get()
				  .addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj")
				  .build();
		
		response = client.newCall(request).execute();
		System.out.println(response.body().string());

	}
	public void createOCSsinkConnectorNimbula(String sm, String identitydomain, String topic) throws IOException {
         
		// Create consumer instance
				System.out.println("\n ************* Creating OCS-OCIC Sink connector ****************");
		
		System.out.println("Creating OCS-OCIC sink connector");
		TopicDetails td = getTopicInstanceDetails(sm, identitydomain, topic);
		String connecturi = td.getConnectUri();
		String topicname = "\"" + identitydomain + "-" + topic + "\"";
		System.out.println(connecturi);
		String connectorname = "connector" + ranGen();
		connectorname = "\"" + connectorname + "\"";

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{" + "\"name\":"+ connectorname + ","
				+ "\"config\":{\"connector.class\":\"oracle.oehpcs.connect.swift.SwiftSinkConnector\", "
				+ "\"tasks.max\":\"1\"," + "\"topics\":" + topicname + ","
				+ "\"key.converter\":\"oracle.oehpcs.connect.swift.ByteArrayConverter\","
				+ "\"partitioner.class\":\"oracle.oehpcs.connect.swift.partitioner.SwiftDefaultPartitioner\","
				+ "\"flush.size\":\"1\"," + "\"value.converter\":\"oracle.oehpcs.connect.swift.ByteArrayConverter\","
				+ "\"storage.class\":\"oracle.oehpcs.connect.swift.SwiftStorage\","
				+ "\"format.class\":\"oracle.oehpcs.connect.swift.SourceFormat\","
				+ "\"ocs.container\":\"https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto\","
				+ "\"ocs.username\":\"Storageadmin\","
				+ "\"ocs.password\":\"Welcome1\"" + "}   }");
		request = new Request.Builder().url(connecturi + "/connectors").post(body)
				.addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj").addHeader("content-type", "application/json")
				.build();
         
		
		final Buffer buffer = new Buffer();
		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());
		
		response = client.newCall(request).execute();

		System.out.println(response.code());
		System.out.println(response.body().string());


	}
	public  String    connectPSMQAStorage() throws IOException{
		request = new Request.Builder()
				  .url("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto")
				  .get()
				  .addHeader("authorization", "Basic U3RvcmFnZWFkbWluOldlbGNvbWUx")
				  .build();
		response = client.newCall(request).execute();
		return response.body().string();
	}
	public  Response  connectPSMQAStorageFile(String URI) throws IOException{
		
		// Cleaning the old log files from container
		System.out.println("\n ************* verifying the dump file from the PSMQA storage ****************");
		
		request = new Request.Builder()
				  .url(URI)
				  .get()
				  .addHeader("authorization", "Basic U3RvcmFnZWFkbWluOldlbGNvbWUx")
				  .build();
		
		
		System.out.println(request.url());
		response = client.newCall(request).execute();
		     System.out.println(response.body().string());
		     System.out.println(response.code());
		     System.out.println(response.message());
		return response;
	}
	
	public void createOCSsinkConnectorBMC(String sm, String identitydomain, String topic) throws IOException {
         
		// Create consumer instance
				System.out.println("\n ************* Creating OCS-BMC Sink connector ****************");
		
		System.out.println("Creating OCS-OCIC sink connector");
		TopicDetails td = getTopicInstanceDetails(sm, identitydomain, topic);
		String connecturi = td.getConnectUri();
		String topicname = "\"" + identitydomain + "-" + topic + "\"";
		System.out.println(connecturi);
		String connectorname = "bmcconnector" + ranGen();
		connectorname = "\"" + connectorname + "\"";

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{" + "\"name\":"+ connectorname + ","
				+ "\"config\":{\"connector.class\":\"oracle.oehpcs.connect.oci.OCIObjectStorageSinkConnector\", "
				+ "\"tasks.max\":\"1\","
				+ "\"topics\":" + topicname + ","
				+ "\"key.converter\":\"oracle.oehpcs.connect.swift.ByteArrayConverter\","
				+ "\"partitioner.class\":\"oracle.oehpcs.connect.swift.partitioner.SwiftDefaultPartitioner\","
				+ "\"flush.size\":\"1\","
				+ "\"value.converter\":\"oracle.oehpcs.connect.swift.ByteArrayConverter\","
				+ "\"storage.class\":\"oracle.oehpcs.connect.oci.OCIObjectStorage\","
				+ "\"format.class\":\"oracle.oehpcs.connect.swift.SourceFormat\","
				+ "\"oci.hostname\":\"https://objectstorage.us-phoenix-1.oraclecloud.com\","
				+ "\"oci.fingerprint\":\"d5:c0:62:a7:07:ab:48:f4:b6:21:67:59:33:d7:e6:6f\","
				+ "\"oci.url\":\"ocid1.user.oc1..aaaaaaaabk5etdudzbbwtocdygoo4zn2o62i5ddkf6btjl2ad2khlajmjlva\"," 
				+ "\"oci.tenant.id\":\"ocid1.tenancy.oc1..aaaaaaaatybtdg7agihbztc2ga4jyiipwbxw2vnl6fes3snbt3d7uwistxba\"," 
				+"\"oci.pem.file.path\":\"/u01/app/oracle/tools/home/oracle/.ssh/oci_api_key.pem\""+ "}   }");
		
		
		request = new Request.Builder().url(connecturi + "/connectors").post(body)
				.addHeader("authorization", "Basic YWRtaW46V2VsY29tZTEj").addHeader("content-type", "application/json")
				.build();
         
		
		final Buffer buffer = new Buffer();
		System.out.println("request URI is " + request.url());
		request.body().writeTo(buffer);
		System.out.println("request body is " + buffer.readUtf8());
		System.out.println("request Headers are " + request.headers());
		
		response = client.newCall(request).execute();

		System.out.println(response.code());
		System.out.println(response.body().string());


	}
		
	
		public  void deleteFromStorage() throws IOException{
		
		// Cleaning the old log files from container
				System.out.println("\n ************* Cleaning the old log files from container ****************");
		String responsebody = connectPSMQAStorage();
		 String[] str =responsebody.split("\\n");
		    int i;
		    for(i=0; i<str.length;i++){
		    	System.out.println(i+" line is "+str[i]);
		    	if(str[i].contains("topics")){
		    		
		    	
		    	request = new Request.Builder()
						  .url("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto/"+str[i])
						  .delete(null)
						  .addHeader("authorization", "Basic U3RvcmFnZWFkbWluOldlbGNvbWUx")
						  .addHeader("content-type", "application/json")
						  .build();
			     response = client.newCall(request).execute();
			     System.out.println(response.body());
			     System.out.println(response.code());
			     System.out.println(response.message());

			     System.out.println(response.isSuccessful());
	}
		    } 
		    }
	
	public static void main(String[] args) throws IOException, JsonParseException {
		String jobId ="\"51621\"";
		jobId = jobId.substring(1, jobId.length()-1);
		System.out.println(jobId);
	}

	
	
	
}