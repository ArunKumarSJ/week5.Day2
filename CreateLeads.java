package Week5Day1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLeads extends BaseClass {

	@Test(dataProvider = "sendData")
	public void loginPage(String fName, String LName, String flName, String LlName) {

		driver.findElementByLinkText("Contacts").click();
		driver.findElementByLinkText("Create Contact").click();
		driver.findElement(By.id("firstNameField")).sendKeys(fName);
		driver.findElement(By.id("lastNameField")).sendKeys(LName);
		driver.findElement(By.id("createContactForm_firstNameLocal")).sendKeys(flName);
		driver.findElement(By.id("createContactForm_lastNameLocal")).sendKeys(LlName);
		driver.findElement(By.id("createContactForm_departmentName")).sendKeys("CS");
		driver.findElement(By.id("createContactForm_description")).sendKeys("NIL");
		driver.findElement(By.id("createContactForm_primaryEmail")).sendKeys("sarva@gmail.com");

		WebElement Drop = driver.findElementByName("generalStateProvinceGeoId");
		Select drpDwn1 = new Select(Drop);
		drpDwn1.selectByVisibleText("New York");

		driver.findElementByName("submitButton").click();
		driver.findElementByLinkText("Edit").click();
		driver.findElement(By.id("updateContactForm_description")).clear();
		driver.findElement(By.id("updateContactForm_importantNote")).sendKeys("SAI");
		driver.findElementByXPath("//input[@type='submit']").click();
		String Title = driver.getTitle();
		System.out.println("The title of the page is:" + Title);

	}

	@DataProvider
	public String[][] sendData() throws IOException {

		return ExcelSheet1.readData("CreateLead");
		
	}
}
