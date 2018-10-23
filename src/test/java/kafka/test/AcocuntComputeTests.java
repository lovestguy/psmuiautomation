package kafka.test;

import java.io.IOException;

import org.testng.annotations.Test;

import kafka.utils.KafkaTestUtils;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AcocuntComputeTests extends KafkaTestUtils{
	
	public void createAccountManojSitea() throws IOException {
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"accountName\": \"kafkasitea\","
				+ "\"serviceType\": \"OEHPCS\","
				+ "\"identityDomainName\": \"idcs-15245d97c94445888192e634a85e23a2\","
				+ "\"smutil_account_type\": \"PLATFORM\","
				+ "\"adminEmail\": \"manoj.sankar@oracle.com\","
				+ "\"adminUsername\": \"manoj.sankar@oracle.com\","
				+ "\"adminFirstName\": \"manoj\","
				+ "\"adminLastName\": \"sankar\","
				+ "\"status\": \"enabled\","
				+ "\"isOverageAllowed\":true,\"accountType\":\"CLOUD_ACCOUNT\","
				+ "\"accountId\":\"idcs-15245d97c94445888192e634a85e23a2\","
				+ "\"identityServiceInstanceId\":\"idcs-15245d97c94445888192e634a85e23a2\","
				+ "\"siteName\": \"kafkasitea\","
				+ "\"serviceEntitlementType\": \"CLOUD_CREDIT\","
				+ "\"serviceEntitlementId\": \"2415\","
				+ "\"subscriptionServiceType\": \"CLOUDCM\",\"subscriptionId\": \"2415\","
				+ "\"accountDataCenterFootprint\": {\"dataCenterId\": \"US001\","
				+ "\"dataCenterRegionId\": \"US001\"},"
				+ "\"provisionableResources\": [    {        \"name\": \"OEHPCS_EE_PAAS_ANY_OCPU_HOUR\"    }],"
				+ "\"dependentLinks\": [{\"serviceType\": \"Compute\",\"links\": [{\"uri\": \"http://api.ucfc2z3a.usdv1.oraclecloud.com\",\"type\": \"REST\"}, {\"uri\": \"http://api.ucfc2z3a.usdv1.oraclecloud.com\",\"type\": \"REST_INTERNAL\"}]}, "+ "{\"serviceType\": \"LBaaS\",\"links\": [{\"uri\":\"https://460336B85DE0F2DAE050F90A56A07479.balancer.c9dev1.oraclecorp.com\",\"type\": \"REST\"}, {\"uri\":\"https://460336B85DE0F2DAE050F90A56A07479.balancer.c9dev1.oraclecorp.com\",\"type\": \"REST_INTERNAL\"}]}],"
				+ "\"appIds\": [{\"dn\": \"/Compute-509734319/manoj.sankar@oracle.com\",\"password\": \"Welc0me1234#\"}, {\"dn\":\"POOL1LBAAS03_LBAASCONTROLPLANE_LBAASCONTROLPLANE_APPID\",\"password\":\"Qqfpy7trbh5_ea\"}]}");
		  
		Request request = new Request.Builder()
		.url("http://"+smURL+".us.oracle.com:7103/paas/api/v1.1/services")
		  .post(body)
		  .addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=")
		  .addHeader("content-type", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		
		
	}

	
	public void createSiteManojSitea() throws IOException{
		

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, " {\"name\": \"kafkasitea\",\"primaryURL\": \"http://api.ucfc2z3a.usdv1.oraclecloud.com\",\"dataCenterId\": \"DEFAULT\",\"secondaryURL\": \"http://api.ucfc2z3a.usdv1.oraclecloud.com\",\"attributes\": {\"image-list\": \"psm-jcs-image-18.1.0.0.350\",\"externalDomainSuffix\": \".ucfc2z3a.usdv1.oraclecloud.com\",\"jaas_scripts_volume_size\": \"10G\",\"password\": \"Welc0me1234#\",\"image-namespace\": \"/oracle/public\",\"jaas_tenant_storage_uri_formatter\": \"https://storage.oraclecorp.com/v1/{1}\",\"jaas_domain_volume_size\": \"8G\",\"username\": \"/Compute-509734319/manoj.sankar@oracle.com\",\"rac-image-list\": \"/oracle/public/racdbaas_121-17.3.5-1707251600\",\"nat-pool\": \"/oracle/public/ippool1\",\"db-image-list-11.2.0.4\": \"/oracle/public/db11_dbcs_psu-17.3.5-1707311639\",\"db-basic-image-list-12.1.0.2\": \"/oracle/public/db12_vi_psu-17.3.5-1707311639\",\"otd-image-list\": \"/oracle/public/psm-jcs-image-17.4.0.0.266\",\"db-image-list-12.1.0.2\": \"/oracle/public/db12_dbcs_psu-17.3.5-1707311639\","
				+ "\"sm.iaas.infra.psm.seciplist\": \"/Compute-509734319/public/paas/"+smURL+"\",\"jaas_backup_volume_size\": \"200G\",\"provisioning_timeout\": \"180\",\"otd_middleware_volume_size\": \"2G\",\"rac-image-list-12.2.0.1\": \"/oracle/public/racdbaas_122-17.3.5-1707251600\",\"jaas_jdk_volume_size\": \"2G\",\"db-basic-image-list-11.2.0.4\": \"/oracle/public/db11_vi_psu-17.3.5-1707311639\",\"domainSuffix\": \".oraclecloud.internal\",\"upperstack-image-list\": \"/oracle/public/psm-jcs-image-17.4.0.0.266\",\"jaas_storage_uri\": \"https://storage.oraclecorp.com\",\"jaas_middleware_volume_size\": \"8G\",\"db-image-list-12.2.0.1\": \"/oracle/public/db122_dbcs_psu-17.3.5-1707311639\",\"otd_domain_volume_size\": \"6G\",\"jaas_max_volume_size\": \"1024G\",\"jaas_storage_name\": \"Storage-StorageEval04admin\",\"jaas_storage_patching_container\": \"PSMQAStorage\",\"jaas_default_volume_size\": \"10G\",\"jaas_storage_container\": \"PSMQAStorage\"}\r\n }");
		Request request = new Request.Builder()
		  .url("http://den02bpk.us.oracle.com:7103/paas/api/v1.1/computeSites")
		  .post(body)
		  .addHeader("authorization", "Basic d2VibG9naWM6d2VsY29tZTE=")
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "f9363e5a-29ea-403a-c420-f0528767e54c")
		  .build();

		Response response = client.newCall(request).execute();
	}
	
	@Test
	public void createSiteandAccount() throws IOException{
		createSiteManojSitea();
		createAccountManojSitea();
	}
}
