package kafka.test;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Test;

import kafka.bean.TopicDetails;
import kafka.idcs.IDCSKafkaTestUtils;

public class IDCSDedicatedKafkaLCM extends IDCSKafkaTestUtils{

	
    String kafkaBody = jsonBodyBasicidcs;
    
	@Test
	public void testIDCSDedicatedKafkaLCM() throws IOException, InterruptedException {

	  // kafkaname = provisioningDedicatedKafka(smURL, identityDomain,kafkaBody);
		//String topic = provisioningDedicatedKafkaTopic(smURL, identityDomain,kafkaname);
		AssignAppRole(topic, "manoj.sankar@oracle.com","manoj.sankar@oracle.com","Welcome1234#");
		
		produceJsonMessagesonTopic(topic);
		
	//	produceJsonMessages(smURL, identityDomain, kafkaname, topic,
		//		"dedicatedidcskafka", true, "manoj.sankar@oracle.com", "Welcome1234#");
		//creteConsumerInstance(bearerToken, kafkaname, "dedicatedidcskafka","Test123",true,identityDomain);		

		//terminateTopicService(smURL,identityDomain,topic);
	//	terminateDedicatedKafkaService(smURL, identityDomain,kafkaname);
	}
	
	@Test
	public void searchBearertoken() throws IOException{
		
		// gettopicidcsClinetID(smURL,identityDomain,topic);
		//AssignAppRole(smURL,identityDomain,topic,"manoj.sankar@oracle.com","Welcome1234#");
		produceJsonMessagesonTopic(smURL,identityDomain,topic,"manoj.sankar@oracle.com","Welcome1234#");
			
		//AssignAppRole();
	}
	@Test
	public void test123() throws IOException {
		
		getIdcsTopicDetails(topic,"manoj.sankar@oracle.com","Welcome1234#") ;

		creteConsumerInstanceontopicApp(topic);
		//getIdcsTopicDetails(topic,"manoj.sankar@oracle.com","Welcome1234#") ;
		//grantConsumerRole("narayana.balusu@oracle.com");
		//produceJsonMessagesonTopic(topic);
     //getIdcsTopicInfo(smURL, identityDomain, topic);
	//	System.out.println(Arrays.asList(st));
		//System.out.println(getPublicToken());
		
	}
	


}
