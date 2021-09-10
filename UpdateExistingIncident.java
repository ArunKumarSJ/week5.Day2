package Week5Day1Ass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingIncident extends BaseClassIncident {
	@Test
	public void UpdateIncident() throws InterruptedException {

		driver.findElementByXPath("(//div[text()='Incidents'])[2]").click();
		// SWITCH IN TO FRAME
		driver.switchTo().frame(0);
		// CLICKING ON EXISTING INCIDENT
		driver.findElementByXPath("//a[@class='linked formlink']").click();
		// SELECTING PRIORITY VALUE
		WebElement dropdown = driver.findElementById("incident.urgency");
		Select Drp = new Select(dropdown);
		Drp.selectByVisibleText("1 - High");
		// GETTING THE INCIDENT VALUE FOR REFERENCE
		String VAL = driver.findElementById("incident.number").getAttribute("value");
		System.out.println(VAL);
		// SELECTING THE STATE VALUE
		WebElement STATE = driver.findElementById("incident.state");
		Select drp = new Select(STATE);
		drp.selectByIndex(1);
		// CLICKING ON UPDATE BUTTON
		driver.findElementByXPath("(//button[text()='Update'])[1]").click();
		WebElement Fortext1 = driver.findElementByXPath("//select[@class='form-control default-focus-outline']");
		Select drpdown = new Select(Fortext1);
		drpdown.selectByVisibleText("for text");
		// ENTERING THE INCIDENT VALUE TO CHECK
		WebElement CHECK = driver.findElementByXPath("(//input[@class='form-control'])[1]");
		CHECK.sendKeys(VAL);
		CHECK.sendKeys(Keys.ENTER);
		driver.findElementByXPath("//a[@class='linked formlink']").click();
		// VERIFYING THE PRIORITY VALUE
		WebElement Verfy = driver.findElementById("incident.urgency");
		Select drp1 = new Select(Verfy);
		String text = drp1.getFirstSelectedOption().getText();
		System.out.println(text);
		if (text.contains("1 - High")) {
			System.out.println("SELECTED PRIORITY VALUE IS CORRECT");
		} else {
			System.out.println("SELECTED PRIORITY VALUE IS INCORRECT");
		}
		// VERFYING THE STATE VALUE
		WebElement verfy2 = driver.findElementById("incident.state");
		Select drp2 = new Select(verfy2);
		String text2 = drp2.getFirstSelectedOption().getText();
		System.out.println(text2);
		if (text2.contains("In Progress")) {
			System.out.println("SELECTED STATE VALUE IS CORRECT");

		} else {
			System.out.println("SELECTED STATE VALUE IS INCORRECT");
		}
	}

}
