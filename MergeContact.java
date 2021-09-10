package Week5Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact extends BaseClass{
	

	@Test
	public void loginPage() throws InterruptedException {
		
		driver.findElementByLinkText("Contacts").click();
		driver.findElementByXPath("//a[text()='Merge Contacts']").click();
		driver.findElementByXPath("(//img[@alt='Lookup'])[1]").click();
		// handling Windows
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a").click();
		driver.switchTo().window(windowHandlesList.get(0));
		driver.findElementByXPath("(//img[@alt='Lookup'])[2]").click();
		// creating windows handling again
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		driver.switchTo().window(windowHandlesList1.get(1));
		driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a").click();
		driver.switchTo().window(windowHandlesList1.get(0));
		driver.findElementByXPath("//a[text()='Merge']").click();
		Alert alert1 = driver.switchTo().alert();
		alert1.accept();
		Thread.sleep(20000);
		String title = driver.getTitle();
		System.out.println("THE TITLE OF THE PAGE:" + title);
	}
}