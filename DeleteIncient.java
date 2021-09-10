package Week5Day1Ass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncient extends BaseClassIncident{
	@Test
	public  void DeleteIncident() throws InterruptedException {
		
		// CLICKING ON INCIDENTS
		driver.findElementByXPath("(//div[text()='Incidents'])[2]").click();
		// SWITCHING INTO FRAME
		driver.switchTo().frame(0);
		// CLICK ON THE EXISTING INCIDENT
		driver.findElementByXPath("(//a[@class='linked formlink'])[1]").click();
		// GETTING THE INCIDENT NUMBER
		String DELINC = driver.findElementById("incident.number").getAttribute("value");
		System.out.println(DELINC);
		// DELETE THE INCIDENT
		driver.findElementByXPath("(//button[text()='Delete'])[1]").click();
		Thread.sleep(1000);
		driver.findElementById("ok_button").click();
		Thread.sleep(20000);
		//VERFYING THE INCIDENT
		WebElement Fortext = driver.findElementByXPath("//select[@class='form-control default-focus-outline']");
		Select drpdown=new Select(Fortext);
		drpdown.selectByVisibleText("for text");
		
		WebElement DELVER = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		DELVER.sendKeys(DELINC);
		DELVER.sendKeys(Keys.ENTER);
		String text = driver.findElementByXPath("(//td[@colspan='13'])[1]").getText();
		if(text.contains("No records to display")) {
			System.out.println("INCIDENT IS DELETED");
		}
	}

}
