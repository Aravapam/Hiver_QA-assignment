package Mani;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class flipkart {

	WebDriver driver = null;
	String chromeDriverPath = "D:\\ResourceFolder\\ExecutionDriversAndJars\\chromedriver.exe" ;
	String flipkartUrl = "https://www.flipkart.com/" ;
	WebElement electronics = null;
	Actions actionObj = null;

	public static void main(String[] args) {
		new flipkart().execute();

	}

	void execute() {
		try {
			setup();
			closePopup();
			exposeElectronicsSubList();
			navigateToPixel3a();
			goToPhonePage();
			switchToTab();
			addToCart();
			Thread.sleep(2000);
			tearDown();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	void setup() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath );
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get( flipkartUrl );
	}

	void closePopup() {
		for( WebElement temp : driver.findElements(By.tagName("button")) ) {
			if (  temp.getAttribute("class").contains("_2AkmmA _29YdH8") ) {
				temp.click();
				break;
			}
		}		  
	}

	void exposeElectronicsSubList() {

		//Find the Electronics webElement
		for( WebElement temp : driver.findElement(By.className("_114Zhd")).findElements(By.className("_1QZ6fC")) ) {
			if( temp.getText().equalsIgnoreCase("ELECTRONICS"))
				electronics = temp;
		}

		actionObj = new Actions(driver) ;
		//Hover mouse over Electronics element to expose the options
		actionObj.moveToElement( electronics );
		actionObj.build().perform();
	}

	void navigateToPixel3a() throws Exception{

		for( WebElement temp : driver.findElement(By.className("_2GG4xt")).findElements(By.tagName("a"))) {
			if( temp.getText().equalsIgnoreCase("Pixel 3a | 3a XL")) {
				actionObj = new Actions(driver) ;
				actionObj.moveToElement(temp);
				actionObj.click(temp);
				actionObj.build().perform();
				break;
			}
		}
		Thread.sleep(1000);
	}

	void goToPhonePage() throws Exception{
		//click on the first entry, it opens in new tab
		driver.switchTo().defaultContent();
		System.out.println(  driver.findElements(By.className("_3O0U0u")).size() );
		driver.findElement(By.className("_3O0U0u")).findElement(By.tagName("a")).click();
		Thread.sleep(2000);
		
	}
	
	void switchToTab() {
		//
		ArrayList tabs = new ArrayList (driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window( tabs.get(0).toString() ); 
		//driver.switchTo().window(mainWindowHandle);
		driver.findElement(By.cssSelector("body")).sendKeys( Keys.CONTROL +"\t");
		driver.switchTo().defaultContent();
	}

	void addToCart() {
		driver.switchTo().defaultContent();
		driver.findElement(By.className("_2Npkh4")).click();
	}

	void tearDown() {
		driver.close();		
	}

}
