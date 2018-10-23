package kafka.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kafka.bean.TopicDetails;
import kafka.idcs.IDCSKafkaTestUtils;

public class UIKafkaUtils extends IDCSKafkaTestUtils {
	public WebDriver driver = null;
    public WebDriverWait wait= null;
	Properties properties = new Properties();
	private final static Logger logger = Logger.getLogger(UIKafkaUtils.class.getName());
	public String kafkaname="OEHPCS282";
	public UIKafkaUtils() throws IOException {
		logger.setLevel(Level.INFO);
		Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

		System.setProperty("webdriver.gecko.driver",
				"D:\\Softwares\\sts-bundle\\sts-3.8.4.RELEASE\\MetadataBasedServiceUIAccessRulesTest\\HudsonMaven\\resources\\geckodriver.exe");
		driver = new FirefoxDriver();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("capability.policy.default.Window.frameElement.get", "allAccess");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS).pageLoadTimeout(60,  TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver, 60);

	}

	{
		System.out.println("Loading Object repositary files");
		properties.load(new FileInputStream(
				System.getProperty("user.dir") + "\\resources\\ObjectRepositary\\psm_signin.properties"));
		properties.load(new FileInputStream(
				System.getProperty("user.dir") + "\\resources\\ObjectRepositary\\CreateInstance.properties"));
	}

	public void enterValue(WebElement we, String value) {
		
	    wait.until(ExpectedConditions.visibilityOf(we));

		we.clear();
		we.sendKeys(value);
	}

	public void enterValue(String xpath, String value) throws InterruptedException {
		
       logger.info("Entering "+value +" in "+xpath);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).clear();
		driver.findElement(By.xpath(xpath)).sendKeys(value);
    	Thread.sleep(1000);
	}
	
	public void clickButton(WebElement we) throws InterruptedException {
	      
		WebDriverWait wait = new WebDriverWait(driver, 60);
	    wait.until(ExpectedConditions.visibilityOf(we));
	    we.click();
		waitForPageLoaded();

	}

	public WebElement findElement(WebDriver driver, By xpath) {

		return driver.findElement(xpath);

	}

	public WebElement findElement(String xpath) {

		return driver.findElement(By.xpath(xpath));

	}
	
	public void waitForPageLoaded() {
		
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {  public Boolean apply(WebDriver driver) { return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");  }};
       
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
	

	public String getXpath(String key) {
		return properties.getProperty(key);
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}

	public void waitProvisioningCompletion(String instancename) throws InterruptedException{
		
		enterValue(getXpath("searchbox"),instancename);
		for (int i=0;i<60;i++){
			logger.info("status of provisioning is Creating service");
			click("refreshbutton");
			
			if(driver.findElements(By.xpath(getXpath("provisioningstatus"))).size() > 0){
			Thread.sleep(60*1000);
		}
		else{
			logger.info("Provisioning completed");
			break;
		}
		}
	}

	/*
	 * public void createOCSSinkConnector(String topic) throws
	 * InterruptedException, IOException{
	 * 
	 * 
	 * String topicURL =
	 * getTopicInstanceDetails(smURL,identityDomain,topic).getRestProxyUri();
	 * String restIp = topicURL.split("//")[1].split(":")[0];
	 * 
	 * driver.get("https://admin:Welcome1%23@"+restIp+":1080/oehcsui/#/topics");
	 * Thread.sleep(3000); findElement("//li[@id='connect']/span/span").click();
	 * findElement("//*[contains(@title,'New Connector')]").click();
	 * findElement("//*[contains(@title,'Source Connector')]").click();
	 * 
	 * findElement("//*[contains(@title,'Sink Connector')]").click();
	 * Thread.sleep(5000); findElement("//button[@id='next-step']").click();
	 * JavascriptExecutor js = ((JavascriptExecutor) driver); WebDriverWait wait
	 * = new WebDriverWait(driver, 3);
	 * 
	 * By by = By.xpath("//*[text()='"+identityDomain+"-"+topic+
	 * "']/../../../../../..//INPUT"); WebElement element =
	 * driver.findElement(by);
	 * js.executeScript("arguments[0].scrollIntoView(true);", element);
	 * wait.until(ExpectedConditions.elementToBeClickable(by)); element.click();
	 * 
	 * Thread.sleep(5000); findElement("//input[@id='id_3']")).click();
	 * findElement("//button[@id='next-step']")).click();
	 * 
	 * 
	 * driver.findElement(By.id("name")).clear();
	 * driver.findElement(By.id("name")).sendKeys("ocsnimbula"+ranGen());
	 * 
	 * driver.findElement(By.id("ocsusername")).clear();
	 * driver.findElement(By.id("ocsusername")).sendKeys("Storageadmin");
	 * 
	 * driver.findElement(By.id("ocspassword")).clear();
	 * driver.findElement(By.id("ocspassword")).sendKeys("Welcome1");
	 * 
	 * driver.findElement(By.id("flushsize")).clear();
	 * driver.findElement(By.id("flushsize")).sendKeys("1");
	 * 
	 * driver.findElement(By.id("ocscontainer")).clear();
	 * driver.findElement(By.id("ocscontainer")).sendKeys(
	 * "https://storage.oraclecorp.com/v1/Storage-StorageEval02admin/kafkaapiauto"
	 * );
	 * 
	 * findElement("//button[@id='next-step']").click(); Thread.sleep(5000);
	 * findElement("//button[@id='next-step']").click();
	 * 
	 * }
	 */
	@Test
	public String createKafkaInstance() throws IOException, InterruptedException {

		signInToPSM();
		
		gotoCreateInstancePage();
		
		 String kafkaname = fillInstanceDetails();
		
 		
 		waitProvisioningCompletion(kafkaname);
 		click("useroptions");
 		click("signoutbutton");
         return      kafkaname;
         
         

	}

	public String fillInstanceDetails() throws InterruptedException {
		String[] instancedetails = new String[]{"instancename","description","instancename"};
		
		 for (String st :instancedetails ){
			// enterText(st,st+"value"+ranGen());
				enterValue(getXpath(st), getValue(st+"value")+ranGen());

	        }
		 
		  kafkaname = findElement(getXpath("instancename")).getAttribute("value");
		 System.out.println("Kakfa Instance name = "+kafkaname);
		 
		click("nextbutton");
		
		click("ssheditbutton");
		enterValue(getXpath("xperimentfile"), System.getProperty("user.dir") +getValue("xperimentfilepath"));

		//enterText("xperimentfile","xperimentfilepath");
		click("enterbutton");
		
		Map<String, String> map = new HashMap<String, String>() {
			{
				put("kafkanodes", "kafkanodesvalue");
				put("kafkacomputeshape", "kafkacomputeshapevalue");
				put("restproxynodes", "restproxynodesvalue");
				put("restproxycomputeshape", "restproxycomputeshapevalue");
			}

		};
		for (Map.Entry<String, String> kafka : map.entrySet()) {
			Thread.sleep(1000);
			new Select(findElement(getXpath(kafka.getKey()))).selectByVisibleText(getValue(kafka.getValue()));

		}
	
     	//enterValue();
		enterText("restproxypassword","restproxypasswordvalue");
		enterText("confirmrestproxypassword","restproxypasswordvalue");
         
 		click("nextbutton");
 		click("createbutton");
		return kafkaname;
	}

	public void gotoCreateInstancePage() throws InterruptedException {
		click("instancestab");
		click("createinstancebutton");
	}

	public void signInToPSM() throws InterruptedException {
		driver.get("http://" + smURL + ".us.oracle.com:7103/psmui/faces/paasRunner.jspx");
        String[] signinparams = new String[]{"Username","Password","IdentityDomain","ServiceType"};
        for (String st :signinparams ){
        	enterText(st,st+"value");
        }

		click("SignInButton");
	}

	private void enterText(String textfield, String value) throws InterruptedException {
		enterValue(getXpath(textfield), getValue(value));
	}

	private void click(String st) throws InterruptedException {
		clickButton(findElement(getXpath(st)));
	}

	
	public void testStopInstance() throws InterruptedException{
		
		openManageThisServicePage();
		click("stopmenuoption");
		click("stopbutton");
		searchInstance();

		for (int i=0;i<60;i++){
			Thread.sleep(60*1000);
			click("refreshbutton");
		if(driver.findElements(By.xpath(getXpath("provisioningstatus"))).size() > 0){
			if(driver.findElement(By.xpath(getXpath("provisioningstatus"))).getText().equalsIgnoreCase("Service stopped")){
				logger.info("stopping completed");
				break;
			}
			logger.info("status of instance is stopping......");
		}
		
			
		
	}
		doLogoff();
	}

    public void testStartInstance() throws InterruptedException{
		
		openManageThisServicePage();
		click("startmenuoption");
		click("startbutton");
		searchInstance();
		for (int i=0;i<60;i++){
			click("refreshbutton");
		if(driver.findElements(By.xpath(getXpath("provisioningstatus"))).size() > 0){
			logger.info("status of  instance is starting...");
			Thread.sleep(60*1000);
		}
		else{
			logger.info("starting completed");
			break;
		}
	}
		doLogoff();
}

    public void testDeleteInstance() throws InterruptedException{
		
		openManageThisServicePage();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		click("deletemenuoption");
		click("deletebutton");
		searchInstance();
		for (int i=0;i<60;i++){
			click("refreshbutton");
		if(driver.findElements(By.xpath(getXpath("provisioningstatus"))).size() > 0){
			logger.info("status of  instance is Terminting Instance...");
			Thread.sleep(60*1000);
		}
		else{
			logger.info("deletion completed");
			break;
		}
	}
		doLogoff();
}

    
	public void doLogoff() throws InterruptedException {
		Thread.sleep(2000);
		click("useroptions");
 		click("signoutbutton");
	}

    public void searchInstance() throws InterruptedException{
	 enterValue(getXpath("searchbox"),kafkaname);
	 Thread.sleep(2000);
	 click("searchbutton");
	 
 }
	public void openManageThisServicePage() throws InterruptedException {
		signInToPSM();
		enterValue(getXpath("searchbox"),kafkaname);
		click("refreshbutton");
		click("managethisservice");
	}

	@Test
	public void test123() throws IOException, InterruptedException{
		
		createKafkaInstance();
		testStopInstance();

		testStartInstance();

		//testStopInstance();
		//testDeleteInstance();


		}
}
