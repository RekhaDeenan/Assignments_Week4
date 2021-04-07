package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='SearchInputBox']")).sendKeys("Perfumes", Keys.ENTER);
		List<WebElement> productCards = driver
				.findElements(By.xpath("//div[@class='product-list-box card desktop-cart']"));
		TreeMap<Integer, WebElement> productMap = new TreeMap <Integer, WebElement>();
		for (WebElement webElement : productCards) {
			WebElement titleWebElement = webElement.findElement(By.tagName("h2"));
			System.out.println("Title ==>" + titleWebElement.getText());
			String price = webElement.findElement(By.className("post-card__content-price-offer")).getText();
			System.out.println(price);
			int priceInt = Integer.parseInt(price.replaceAll("\\D", ""));
			productMap.put(priceInt, webElement);
		}
		//System.out.println(productMap);
		int highestPriceKey  = productMap.descendingKeySet().first();
		productMap.get(highestPriceKey).click();
		Set<String> allHandles=driver.getWindowHandles();
		List<String> windowsAvailable=new ArrayList<String>(allHandles);
		String secondwindow=windowsAvailable.get(1);
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("//button[contains(@class,'combo-add-to-btn prdt-des-btn')]")).click();
		String addToBag = driver.findElement(By.className("add-to-bag-message")).getText();
		System.out.println(addToBag);
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		String total = driver.findElement(By.xpath("//div[@class='first-col']//div[1]")).getText();
		System.out.println("Grand Total====>"+total);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		driver.findElement(By.name("name")).sendKeys("Rekha");
		driver.findElement(By.name("email")).sendKeys("rekha@gmail.com");
		driver.findElement(By.name("phoneNumber")).sendKeys("9790238843");
		driver.findElement(By.name("pinCode")).sendKeys("600024");
		driver.findElement(By.xpath("//legend[@class='title']/following-sibling::textarea")).sendKeys("perumbakkam");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String cardError = driver.findElement(By.xpath("//span[@class='field-error']")).getText();
		System.out.println("Card Number ===>"+cardError);
		String expiry = driver.findElement(By.xpath("(//span[@class='field-error'])[2]")).getText();
		System.out.println("Card Expiry===>"+expiry);
		String cvv = driver.findElement(By.xpath("(//span[@class='field-error'])[3]")).getText();
		System.out.println("CVV====>"+cvv);
		driver.close();
	}

}
