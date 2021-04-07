package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PepperFryAssign {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement livingRoom = driver.findElement(By.xpath("(//a[text()='Furniture'])[1]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(livingRoom).perform();
		driver.findElement(By.xpath("//div[@id='meta-furniture']/div[1]/div[3]/div[2]/div[12]/a[1]")).click();
		driver.findElement(By.xpath("//h5[text()='Executive Chairs']")).click();
		WebElement height = driver.findElement(
				By.xpath("(//div[@class='clip__filter-dimension-title'])[2]/preceding::input[@type='number'][2]"));
		height.clear();
		height.sendKeys("50", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@data-productid='1888861']")).click();
		WebElement bedroom = driver.findElement(By.linkText("Bedroom"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(bedroom).perform();
		driver.findElement(By.linkText("Study Tables")).click();
		driver.findElement(By.xpath("//label[text()='Spacewood']")).click();
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElement(By.xpath("//span[contains(@class,'wewidgeticon we_close')]")).click();
		driver.switchTo().defaultContent();
		WebElement price = driver.findElement(By.xpath("//label[@for='price7000-8000']"));
		builder.click(price).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@data-productid='1852714']")).click();
		String wishlistCount = driver
				.findElement(By.xpath("(//a[@class='trackyourorderPopup']//span[1]/following::span)[3]")).getText();
		System.out.println(wishlistCount);
		String wishCount = "2";
		if (wishCount.equals(wishlistCount)) {
			System.out.println("WishList Count Verified");
		} else {
			System.out.println("WishList Count did not match");
		}
		driver.findElement(By.xpath("//span[text()='Wishlist']")).click();
		driver.findElement(By.xpath(
				"//a[text()[normalize-space()='SOS Carter Study Table in Lorraine walnut & silver grey...']]/following::a[@class='addtocart_icon']"))
				.click();
		driver.findElement(By.xpath("//div[@class='minicart_footer']//a[1]")).click();
		driver.findElement(By.id("pin_code")).sendKeys("600028");
		driver.findElement(By.id("pin_check")).click();
		driver.findElement(By.xpath("//a[text()='PLACE ORDER'][1]")).click();
		driver.findElement(By.xpath("//span[text()='ORDER SUMMARY']")).click();
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snaps/pepperfry1.jpg");
		FileUtils.copyFile(screenshot, destination);
		System.out.println("Screenshot done");
	}

}
