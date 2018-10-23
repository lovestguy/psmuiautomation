package kafka.test;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.commons.net.util.Base64;
import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import kafka.utils.KafkaTestUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DedicatedKafkaLCM extends KafkaTestUtils {
	
    String topic = "oehcs82";
    String kafkaBody = jsonBodyBasic;
    
	
	
	@Test
	public void testProvisioningDedicatedKafka() throws IOException, InterruptedException {

	    kafkaname = provisioningDedicatedKafka(smURL, identityDomain,kafkaBody);
		String topic = provisioningDedicatedKafkaTopic(smURL, identityDomain,kafkaname);
		String topicurl = getTopicInstanceDetails(smURL,identityDomain,topic).getRestProxyUri();
		String restIp = topicurl.split("//")[1].split(":")[0];
		System.out.println("restIp...."+restIp);
		produceJsonMessages(topicurl);
		consumingMessages(restIp,topic,identityDomain);
		//terminateTopicService(smURL,identityDomain,topic);
	//	terminateDedicatedKafkaService(smURL, identityDomain,kafkaname);
	}
	public static void main(String[] args) {
		

	}
	
	@Test
	public void deletePSMJob() throws IOException{
		deletePSMJob(smURL,"43195");
	}
	
	
	
	
}
