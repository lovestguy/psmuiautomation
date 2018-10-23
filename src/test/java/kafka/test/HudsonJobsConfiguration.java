package kafka.test;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.TestNG;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class HudsonJobsConfiguration {

	String release = "18.4.2";
	String oldrelease = "18.3.6";
	WebDriver driver;

	List<String> nimbula_jobs = new ArrayList<>(Arrays.asList("OEHCS_Provisioning_Terminate_loop_", "OEHCS_Topic_UI_",
			"OEHPCS-OEHCS_SRG_CLI_", "OEHPCS_Account_LifeCycle_", "OEHPCS_addSSH_",
			"OEHPCS_API_patchRollback_Scaleout_", "OEHPCS_Basic_connect_SRG_API_", "OEHPCS_Basic_SRG_API_",
			"OEHPCS_connect_OCS_", "OEHPCS_connect_Provisioning_Terminate_Loop_", "OEHPCS_NativeApi_LRG_",
			"OEHPCS_PARTITION_REBALANCE_VALIDATION_", "OEHPCS_Provisioning_Combination_Loop_",
			"OEHPCS_Provisioning_Terminate_Loop_100_", "OEHPCS_Provisioning_Terminate_Loop_",
			"OEHPCS_Recommended_connect_SRG_API_", "OEHPCS_Recommended_ocs_connect_SRG_API_",
			"OEHPCS_Recommended_SRG_API_", "OEHPCS_REST_PROXY_V2_LRG_", "OEHPCS_SASSL_",
			"OEHPCS_START_STOP_RESTART_LOOP_", "OEHPCS_TOPIC_LOG_COMPACT_", "OEHPCS_TOPIC_UI_METRICS_",
			"OEHPCS_UI_MATS_", "OEHPCS_UPGRADE_MATS_AFTER_TOOLS_UPDATE_", "OEHPCS_jdbc_connect_SRG_API_",
			"OEHPCS_ocs_connect_SRG_API_", "OEHPCS_jdbc_keystore_ocs_connect_API_",
			"OEHPCS_ocs_keystore_connect_SRG_API_", "OEHPCS_connect_OCS_"));
    
	List<String> bmc_jobs = new ArrayList<>(Arrays.asList("OEHPCS_BASIC_SRG_BMC_", "OEHPCS_BASIC_SRG_BMC_MULTI_AD_",
			"OEHPCS_BMC_PROVISION_TERMINATION_LOOP_", "OEHPCS_BMC_PROVISIONING_TERMINATE_100_LOOP_",
			"OEHPCS_OCI_API_patchRollback_Scaleout_", "OEHPCS_OCI_JDBC_connect_SRG_API_",
			"OEHPCS_OCI_OCS_connect_SRG_API_", "OEHPCS_OCI_START_STOP_RESTART_LOOP_",
			"OEHPCS_OCI_UPGRADE_MATS_AFTER_TOOLS_UPDATE_", "OEHPCS_RECOMENDED_SRG_BMC_",
			"OEHPCS_REST_PROXY_V2_LRG_BMC_"));
	List<String> jobs;
	
	public HudsonJobsConfiguration() {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Softwares\\sts-bundle\\sts-3.8.4.RELEASE\\MetadataBasedServiceUIAccessRulesTest\\HudsonMaven\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	private void hudsonLogin(String computeType) throws InterruptedException {
		getHudsonView(computeType);
		driver.findElement(By.linkText("log in")).click();

		driver.findElement(By.id("j_username")).sendKeys("siva.kumar");
		driver.findElement(By.id("j_password")).sendKeys("siva123");

		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(10000);
	}

	private void getHudsonView(String computeType) {
		if(computeType.equalsIgnoreCase("nimbula")){
		driver.get("http://sca00cod.us.oracle.com:8080/hudson/view/OEHPCS/view/OEHPCS_" + release + "/");
		jobs = nimbula_jobs;
		}
		else{
			driver.get("http://sca00cod.us.oracle.com:8080/hudson/view/OEHPCS/view/OEHPCS_OCI_" + release + "/");
			jobs = bmc_jobs;
		}
	}

	
	public void createKafkaHudosnJobs(String computeType) throws InterruptedException {

		hudsonLogin(computeType);
		for (String job : jobs) {
			driver.findElement(By.linkText("New Job")).click();
			driver.findElement(By.xpath("//*[@id='main-panel']/form/table/tbody/tr[18]/td/input")).click();
			driver.findElement(By.id("from")).sendKeys(job+oldrelease);
			driver.findElement(By.id("name")).sendKeys(job + release);
			driver.findElement(By.id("ok")).click();
			if (driver.getPageSource().contains("A job already exists with the name")) {
				System.out.println(job + release + " job already created");
				getHudsonView(computeType);

			}

			else {
				System.out.println(job + release + " job not already created....so creating now");
				driver.findElement(By.xpath("(//INPUT[@name='parameter.defaultValue'])[2]")).clear();
				driver.findElement(By.xpath("(//INPUT[@name='parameter.defaultValue'])[2]")).sendKeys(release);
				Thread.sleep(2*1000);
				driver.findElement(By.name("Submit")).click();
				getHudsonView(computeType);			}
		}
	}

	@Test
	public void createKafkaBMCHudosnJobs() throws InterruptedException {
		
		createKafkaHudosnJobs("bmc");
		createKafkaHudosnJobs("nimbula");

	}
		
	@Test
	public static void test123() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"D:\\Softwares\\sts-bundle\\sts-3.8.4.RELEASE\\MetadataBasedServiceUIAccessRulesTest\\HudsonMaven\\resources\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://10.90.90.87:1080/oehcsui/#/connect");
		// Alert alt = driver.switchTo().alert();
		Thread.sleep(1000 * 15);
		// alt.sendKeys("admin");
		// alt.sendKeys("Welcome1#");
		// alt.dismiss();
		// driver.findElement(By.xpath("//*[@id='appheader2']/div[1]/span")).click();
		// driver.findElement(By.xpath("//*[@id='connect']/span/span")).click();
		driver.findElement(By
				.xpath("//BUTTON[@class='oj-button-jqui oj-button oj-component oj-enabled oj-button-half-chrome oj-button-text-icon-end oj-component-initnode oj-focus oj-focus-highlight oj-hover']"))
				.click();
		driver.findElement(By.xpath("//SPAN[@title='Task Details'][text()='Task Details']")).click();
		// driver.findElement(By.xpath("//*[text()='New Connector']")).click();
		// *[@id="appheader2"]/div[1]/span
	}

	@Test
	public void deleteJobs() throws InterruptedException {
		
		release = "18.1.4";
		hudsonLogin("nimbula");
		for (String job : jobs) {

			String h1 = "/html/body/h1";
			driver.get("http://sca00cod.us.oracle.com:8080/hudson/job/" + job + release);
			if (driver.getPageSource().contains("HTTP Status 404 – Not Found")) {
				System.out.println("there is no job " + job + release);
			}

			else {
				System.out.println("there is a job " + job + release);

				driver.findElement(By.linkText("Delete Job")).click();
				// driver.findElement(By.xpath("//*[@id='tasks']/div[5]/a[2]")).click();

				// driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
				driver.findElement(By.cssSelector("span.ui-button-text")).click();

				Thread.sleep(60 * 1000);

			}
		}
	}

	
	@Test
	public void deleteJobsFromView() throws InterruptedException {
		
		List<String> rele = new ArrayList<>(Arrays.asList("18.1.6","18.2.2","18.2.4"));
		hudsonLogin("nimbula");

			
			for (String re :rele){
			
			for (int i =2 ;i<100;i++){
				try {
					driver.get("http://sca00cod.us.oracle.com:8080/hudson/view/OEHPCS/view/OEHPCS_IDCS_DED_"+re);
					driver.findElement(By.xpath("//*[@id='projectstatus']/tbody/tr[2]/td[3]/a")).click();
					driver.findElement(By.linkText("Delete Job")).click();
					driver.findElement(By.cssSelector("span.ui-button-text")).click();
					Thread.sleep(15*1000);
				} catch (NoSuchElementException exception) {
	                System.out.println("All the jobs are deleted from OEHPCS_MT_"+re);
	                break;
				}
				
			}
			
			}
			
		

}
	}

