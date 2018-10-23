package kafka.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import kafka.utils.KafkaTestUtils;
import kafka.utils.UIKafkaUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class KafkaConnector extends KafkaTestUtils{
	
	UIKafkaUtils uku = null;
	
	
	
	KafkaConnector() throws IOException, InterruptedException{
		
		deleteFromStorage();
	   // kafkaname = provisioningDedicatedKafka(smURL, identityDomain,jsonBodyBasicwithConnect);
		kafkaname = provisioningDedicatedKafka(smURL, identityDomain,jsonBodyBasicwithConnect);
		topic = provisioningDedicatedKafkaTopic(smURL, identityDomain,kafkaname);
	}
	
	
	
	@Test
	public void OCICConnectors() throws IOException, InterruptedException{
		createOCSsinkConnectorNimbula(smURL,identityDomain,topic);
		// uku = new UIKafkaUtils();
		//uku.createOCSSinkConnector(topic);
		String topicurl = getTopicInstanceDetails(smURL,identityDomain,topic).getRestProxyUri();
		produceJsonMessages(topicurl);
		Thread.sleep(60*1000);
		Response response = connectPSMQAStorageFile("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto/topics/"+identityDomain+"-"+topic+"/partition-0/"+identityDomain+"-"+topic+"_0_0000000000_0000000000");
		
		assertEquals(response.code(), 200);
		assertEquals(response.message(), "OK");
		assertEquals(response.body().string().contains("sivakumar"),true);
		
        response = connectPSMQAStorageFile("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto/topics/"+identityDomain+"-"+topic+"/partition-1/"+identityDomain+"-"+topic+"_1_0000000000_0000000000");
		
		assertEquals(response.code(), 200);
		assertEquals(response.message(), "OK");
		assertEquals(response.body().string().contains("Oracle"),true);
   
	}
	
	
	@Test
	public void OCIConnectors() throws IOException, InterruptedException{
		createOCSsinkConnectorNimbula(smURL,identityDomain,topic);
		// uku = new UIKafkaUtils();
		//uku.createOCSSinkConnector(topic);
		String topicurl = getTopicInstanceDetails(smURL,identityDomain,topic).getRestProxyUri();
		produceJsonMessages(topicurl);
		Thread.sleep(60*1000);
		Response response = connectPSMQAStorageFile("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto/topics/"+identityDomain+"-"+topic+"/partition-0/"+identityDomain+"-"+topic+"_0_0000000000_0000000000");
		
		assertEquals(response.code(), 200);
		assertEquals(response.message(), "OK");
		assertEquals(response.body().string().contains("sivakumar"),true);
		
        response = connectPSMQAStorageFile("https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto/topics/"+identityDomain+"-"+topic+"/partition-1/"+identityDomain+"-"+topic+"_1_0000000000_0000000000");
		
		assertEquals(response.code(), 200);
		assertEquals(response.message(), "OK");
		assertEquals(response.body().string().contains("Oracle"),true);
   
	}

	
}
