package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAssignment {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("one plus 7 pro mobiles",Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[@class='a-price-symbol']/following-sibling::span")).getText();
		System.out.println("Mobile Price: "+price);
		driver.findElement(By.xpath(" //span[text()='Showing results for ']/following::img[1]")).click();
		Set<String> allHandles=driver.getWindowHandles();
		List<String> windowsAvailable=new ArrayList<String>(allHandles);
		String secondwindow=windowsAvailable.get(1);
		driver.switchTo().window(secondwindow);
		String review = driver.findElement(By.id("acrCustomerReviewText")).getText();
		System.out.println("Customer Ratings: "+review);
		driver.findElement(By.id("add-to-cart-button")).click();
		String success="Added to cart";
		String addedToCart = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-total-string']//b[1]/preceding::h4[@class='a-alert-heading'][1]")).getText();
		System.out.println(addedToCart);
		if(addedToCart.equalsIgnoreCase(success))
			System.out.println("Added to cart Verified");
		else
			System.out.println("Not Added");
		Thread.sleep(2000);
		String signin="Amazon Sign In";
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-checkout-button']/span[1]/input[1]")).click();
		String title=driver.getTitle();
		if(title.equalsIgnoreCase(signin))
		System.out.println("Amazon Signin title Verified");
		else
			System.out.println("Not an Amazon Signin title");
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		String mailid="Enter your email or mobile phone number";
				String mailtext = driver.findElement(By.xpath("(//div[@class='a-alert-content'])[2]")).getText();
		if(mailid.equalsIgnoreCase(mailtext))
			System.out.println("Error Message Verified");
		else
			System.out.println("No error message");
		driver.quit();
		

	}

}
