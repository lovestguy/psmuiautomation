package kafka.test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import org.openqa.selenium.security.UserAndPassword;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import kafka.utils.UIKafkaUtils;

public class TopicConsoleUI extends UIKafkaUtils {
	
	
	

	
	public TopicConsoleUI() throws IOException {
		
	}
	@Test
	public void openConsolepage(){
		driver.get("http://den02bpk.us.oracle.com:7103/psmui/faces/paasRunner.jspx");
		
		driver.findElement(By.id("pt1:sections:it1::content")).clear();
		driver.findElement(By.id("pt1:sections:it1::content")).sendKeys("weblogic");
		
		driver.findElement(By.id("pt1:sections:it2::content")).clear();
		driver.findElement(By.id("pt1:sections:it2::content")).sendKeys("welcome1");
		
		driver.findElement(By.id("pt1:sections:it4::content")).clear();
		driver.findElement(By.id("pt1:sections:it4::content")).sendKeys("idcs-882d635be9db40c499e4b509aff29d1a");
		
		driver.findElement(By.id("pt1:sections:it5::content")).clear();
		driver.findElement(By.id("pt1:sections:it5::content")).sendKeys("OEHPCS");
		
		driver.findElement(By.id("pt1:sections:sign_in")).click();

        
		driver.findElement(By.xpath("//*[@title='Manage this instance']/..//a | //*[@title='Manage this service']/..//a")).click();
		driver.findElement(By.xpath("//*[contains(@id,'ScrollContent')]/tbody/tr/td[contains(text(),'Restart')] | //*[contains(@id,'emConsole')]//span[contains(text(),'Restart')] | //*[contains(@id,'srvpop::content')]//span[contains(text(),'Restart')] | //*[contains(@id,'selectSvcopup2::content')]//span[contains(text(),'Restart')]")).click();


	}
    @Test
	public void openconlosepagedirectly() throws InterruptedException, IOException{
    	
    	//createOCSSinkConnector("topicdefault");


    }
		  
	}

