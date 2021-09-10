package Week5Day1Ass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignIncident extends BaseClassIncident {

	@Test(dataProvider = "sendData")
	public void AssignIncident(String GRP,String UPD) throws InterruptedException {

		// CLICKING ON OPEN BUTTON
		driver.findElementByXPath("(//div[text()='Open'])[1]").click();
		// SWTICHING TO FRAME
		driver.switchTo().frame(0);
		// CLICKING ON THE EXISTING INCIDENT
		driver.findElementByXPath("(//a[@class='linked formlink'])[1]").click();
		// GETTING THE INCIDENT NUMBER
		String INC2 = driver.findElementById("incident.number").getAttribute("value");
		System.out.println(INC2);
		// CLICKING ON ASSIGNMENT GROUP
		driver.findElementByXPath("(//button[@name='lookup.incident.assignment_group'])[1]").click();
		// SWITCHING TO WINDOWS
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		// SELECTING SOFTWARE GROUP
		WebElement SGP = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		SGP.sendKeys(GRP);
		Thread.sleep(10000);
		SGP.sendKeys(Keys.ENTER);
		// CLICKING ON SOFTWARE
		driver.findElementByXPath("//a[text()='Software']").click();
		// SWITCHING BACK TO PARENT WINDOW
		driver.switchTo().window(windowHandlesList.get(0));
		// SWITCHING OUT OF FRAME
		driver.switchTo().defaultContent();
		// SWITCHING IN TO FRAME
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		JavascriptExecutor scroll = (JavascriptExecutor) driver;
		scroll.executeScript("window.scrollBy(0,400)");
		// UPDATING THE NOTES
		driver.findElementById("activity-stream-textarea").sendKeys(UPD);
		// CLICK ON UPDATE
		driver.findElementByXPath("(//button[text()='Update'])[1]").click();

		WebElement Fortext1 = driver.findElementByXPath("//select[@class='form-control default-focus-outline']");
		Select drpdown = new Select(Fortext1);
		drpdown.selectByVisibleText("for text");

		// ENTERING THE INCIDENT FOR VERFICATION
		WebElement INVver = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		INVver.sendKeys(INC2);
		INVver.sendKeys(Keys.ENTER);
		// CLICKING ON THE INCIDENT
		driver.findElementByXPath("(//a[@class='linked formlink'])[1]").click();
		// VERFYING GROUP
		// driver.findElementByXPath("(//input[@name='sys_display.incident.assignment_group'])")
		WebElement ASS = driver.findElement(By.xpath("//input[@id='sys_display.incident.assignment_group']"));
		String Value = ASS.getAttribute("value");
		if (Value.contains("Software")) {
			System.out.println("Assigned to Software group");
		} else {
			System.out.println("Not Assigned to Software group");
		}

	}

	@DataProvider
	public String[][] sendData() throws IOException {
		return ExcelSheetInc.readData("AssignIncident");
	}

}
