package Week5Day1Ass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Week5Day1.ExcelSheet1;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIncident extends BaseClassIncident{
	@Test(dataProvider = "sendData")
	public  void createIncident(String Des) throws InterruptedException {
		
		driver.findElementByXPath("(//div[text()='Create New'])[1]").click();
		driver.switchTo().frame("gsft_main");
		driver.findElementById("lookup.incident.caller_id").click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList=new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.findElementByXPath("(//a[@class='glide_ref_item_link'])[1]").click();
		driver.switchTo().window(windowHandlesList.get(0));
		Thread.sleep(20000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		String INCNO = driver.findElementByXPath("(//input[@name='incident.number'])").getAttribute("value");
		System.out.println("THE INCIDENT NUMBER IS:"+ INCNO);
		driver.findElementById("incident.short_description").sendKeys(Des);
		driver.findElementByXPath("(//button[@type='submit'])[1]").click();
		Thread.sleep(20000);
		WebElement Fortext = driver.findElementByXPath("//select[@class='form-control default-focus-outline']");
		Select drpdown=new Select(Fortext);
		drpdown.selectByVisibleText("for text");
		WebElement SEARCH = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		SEARCH.sendKeys(INCNO);
		Thread.sleep(20000);
		SEARCH.sendKeys(Keys.ENTER);
		Thread.sleep(20000);
		String VERFY = driver.findElementByXPath("(//a[@class='linked formlink'])[1]").getText();
		if(VERFY.contains(INCNO)) {
			System.out.println("THE INCIDENT IS CREATED");
		}
		else {
			System.out.println("THE INCIDENT IS NOT CREATED");
		}
	}
	@DataProvider
	public String[][] sendData() throws IOException {
		return ExcelSheetInc.readData("CreateIncident");
	}
	

}
