package sawglab;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class swagLabTestCases {

	String myWebsite = "https://www.saucedemo.com/inventory.html";
	String firstItem = "//div[normalize-space()='Sauce Labs Backpack']";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void mySetUp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(myWebsite);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void logInTest() throws InterruptedException {
		WebElement userName = driver.findElement(By.xpath("//input[@id='user-name']"));
		userName.sendKeys("standard_user");
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}

	@Test(enabled = false)
	public void printTest() {
		WebElement item1 = driver.findElement(By.xpath(firstItem));
		String firstText = item1.getText();
		System.out.println(firstText);
	}

	@Test(enabled = false)
	public void printAllItems() {
		List<WebElement> allItems = driver.findElements(By.className("inventory_item_name"));
		for (int i = 0; i < allItems.size(); i++) {
			System.out.println(allItems.get(i).getText());
		}
	}

	@Test(enabled = false)
	public void addAllItems() throws InterruptedException {
		List<WebElement> allAdded = driver.findElements(By.className("btn_primary"));
		for (WebElement button : allAdded) {
			button.click();
		}
		Thread.sleep(2000);
		// removeFromCart
		List<WebElement> removeAll = driver.findElements(By.className("btn_secondary"));
		for (int i = 0; i < removeAll.size(); i++) {
			allAdded.get(i).click();
		}
	}

	// هون بدي ياه يضيف عنصر اه وعنصر لا
	@Test(enabled = false)
	public void addEven() {
		List<WebElement> allAdded = driver.findElements(By.className("btn_primary"));
		for (int i = 0; i < allAdded.size(); i++) {
			if (i % 2 == 0)
				allAdded.get(i).click();
		}
	}

	// هون بدي ياه يضيف العناصر يلي بتحتوي على كلمة
	// bike او onesie
	// ويطبعلي سعر هدول العناصر قبل الضريبة وبعد الضريبة
	@Test(priority = 2)
	public void addBike() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> allAdded = driver.findElements(By.className("btn_primary"));
		List<WebElement> itemsNames = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> allPrices = driver.findElements(By.className("inventory_item_price"));
		for (int i = 0; i < itemsNames.size(); i++) {
			String itemName = itemsNames.get(i).getText();

			if (itemName.contains("Bike") || itemName.contains("Onesie")) {
				allAdded.get(i).click();
				String price = allPrices.get(i).getText();
				double priceAsDouble = Double.parseDouble(price.replace("$", ""));
				double tax = 0.10;
				double priceAfterTax = (priceAsDouble * tax) + priceAsDouble;
				System.out.println("The item " + itemName + " has the price " + price + " before tax ");
				System.out.println("The price after tax is " + priceAfterTax);

			}

		}
		Thread.sleep(3000);
		driver.findElement(By.className("shopping_cart_link")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Layan");
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("abood");
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@id='continue']")).click();

	}

	@AfterTest
	public void after() {
	}
}
