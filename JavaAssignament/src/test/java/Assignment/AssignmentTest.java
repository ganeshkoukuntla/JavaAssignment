package Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssignmentTest {
	public WebDriver driver;
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ganesh.koukuntla\\eclipse-workspace\\AssignmentProject\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	@Test (priority = 1)
	public void facelogin() {
		driver.get("https://www.facebook.com/reg");
		String facebookTitle = driver.getTitle();
		Assert.assertEquals("Sign up for Facebook | Facebook", facebookTitle);
 	}
	
	@Test (priority =2)
	public void enteringDetails() {
		driver.findElement(By.name("firstname")).sendKeys("Sourcefuse");
		driver.findElement(By.name("lastname")).sendKeys("QA");
		driver.findElement(By.name("reg_email__")).sendKeys("09874563211");
		driver.findElement(By.id("password_step_input")).sendKeys("1234@bcde");
		//Selection of year in dropdown     
	    WebElement Year=driver.findElement(By.id("year")); 
	    Select year= new Select(Year); 
	    year.selectByVisibleText("2000");
		WebElement  gender = driver.findElement(By.xpath("//input[@type='radio' and @value='2']"));
		Actions actions = new Actions(driver);
		actions.click(gender).perform();
	} 
	
	@Test (priority =3)
	public void clickSubmit() {
		Actions actions = new Actions(driver);
		WebElement submitButton = driver.findElement(By.name("websubmit"));
		actions.click(submitButton).perform();
		String userInfo = driver.findElement(By.className("uiHeaderTitle")).getText();
		if(userInfo.equalsIgnoreCase("Enter the confirmation code from the text message")) {
			System.out.println("Facebook user registered");
		}
	}
	
	@Test (priority =4)
	public void launchToolsQA() {
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://demoqa.com/alertsWindows");
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://demoqa.com/alertsWindows", currentUrl);
		String toolsQATitle = driver.getTitle();
		Assert.assertEquals("ToolsQA", toolsQATitle);
	}
	@Test(priority =5)
	public void clickOnAlerts() {
		Actions actions = new Actions(driver);
		//click on Alerts
		WebElement alertsItem = driver.findElement(By.xpath("//*[@id='item-1']//*[contains(text(),'Alerts')]"));
		actions.click(alertsItem).perform();
		WebElement alertButton = driver.findElement(By.xpath("//*[@id='alertButton']"));
		actions.click(alertButton).perform();
		driver.switchTo().alert().accept();
	}
	
	@Test(priority =6)
	public void Nestedrames() {
		Actions actions = new Actions(driver);
		//click on Nested Frames
		WebElement nestedFrame = driver.findElement(By.xpath("//*[@id='item-3']//*[contains(text(),'Nested Frames')]"));
		actions.click(nestedFrame).perform();
		WebElement childFrame = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));
		driver.switchTo().frame(childFrame);
		String childText = childFrame.getText();
		System.out.println(childText);
		//switching to the parent frame
		driver.switchTo().defaultContent();
	}
	
	@Test(priority =7)
	public void verifyUrl() {
		String newCurrentUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://demoqa.com/nestedframes", newCurrentUrl);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
