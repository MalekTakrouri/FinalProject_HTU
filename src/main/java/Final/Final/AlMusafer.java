package Final.Final;

import org.testng.annotations.BeforeTest;

import java.awt.im.InputContext;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlMusafer {

	private static final String enabled = null;
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String[] webLang = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };

	@BeforeTest
	public void mySetup() {

		driver.get("https://www.almosafer.com/en?ncr=1");

		driver.manage().window().maximize();

	}

	@Test(priority = 1, enabled = false)
	public void checkLang() {

		String websiteLang = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(websiteLang, "en");

		if (websiteLang.equals("en")) {
			System.out.print("Test is passed");
		}

	}

	@Test(priority = 2, enabled = false)
	public void checkCurrency() {

		WebElement websiteCurrency = driver.findElement(By.xpath("//div[@data-testid='Header__CurrencySelector']"));
//			System.out.print(websiteCurrency.getText());

		Assert.assertEquals(websiteCurrency.getText(), "SAR");

		if (websiteCurrency.getText().equals("SAR")) {
			System.out.print("Test is passed");
		}

	}

	@Test(priority = 3, enabled = false)
	public void contactNumber() {
		WebElement contactNumber = driver.findElement(By.xpath("//div[@style='direction:ltr;order:1']"));

//			System.out.print(websiteCurrency.getText());

		Assert.assertEquals(contactNumber.getText(), "+966554400000");

		if (contactNumber.getText().equals("+966554400000")) {
			System.out.print("contactNumber is right");
		}

	}

	@Test(priority = 4, enabled = false)
	public void qitaLlogoDisplay() {
		WebElement qitafLogo = driver.findElement(By.tagName("footer"));
		boolean qitafLogoDisplayed = qitafLogo.findElement(By.xpath("//img[@alt='qitaf']")).isDisplayed();
		Assert.assertEquals(qitafLogoDisplayed, true);

	}

	@Test(priority = 5, enabled = false)
	public void flightDepature() throws InterruptedException {

		LocalDate date = LocalDate.now();
		LocalDate nextDay = date.plusDays(1);
		int expected = nextDay.getDayOfMonth();
		String expectedAsString = Integer.toString(expected);

		Thread.sleep(2000);
		String ActualDate = driver.findElement(By.id("testIdPickerPrefix__DatePicker__DepartureDate"))
				.getDomAttribute("value");

		Assert.assertEquals(true, ActualDate.contains(expectedAsString));
	}

	@Test(priority = 6, enabled = false)
	public void flightReturn() throws InterruptedException {

		LocalDate date = LocalDate.now();
		LocalDate nextDay = date.plusDays(2);
		int expected = nextDay.getDayOfMonth();
		String expectedAsString = Integer.toString(expected);

		Thread.sleep(2000);
		String ActualDate = driver.findElement(By.id("testIdPickerPrefix__DatePicker__ArrivalDate"))
				.getDomAttribute("value");

		Assert.assertEquals(true, ActualDate.contains(expectedAsString));
	}

	@Test(priority = 7, enabled = false)
	public void changeLanguage() {

		int randomLand = rand.nextInt(webLang.length);
		driver.get(webLang[randomLand]);

	}

	@Test(priority = 8, enabled = false)
	public void randomCityStays() {

		String[] CitiesInEnglish = { "dubai", "jeddah", "riyadh" };
		String[] CitiesInArabic = { "الرياض", "دبي", "جدة" };
		int randomEnglishCity = rand.nextInt(CitiesInEnglish.length);
		int randomArabicCity = rand.nextInt(CitiesInArabic.length);

		WebElement staysTab = driver.findElement(By.id("tab-hotels"));
		staysTab.click();
		WebElement SearchInputField = driver.findElement(By.id("DesktopSearchWidget_Destination_InputField_Test_Id"));

		if (driver.getCurrentUrl().contains("ar")) {
			SearchInputField.sendKeys(CitiesInArabic[randomArabicCity]);

		} else {
			SearchInputField.sendKeys(CitiesInEnglish[randomEnglishCity]);

			driver.findElement(By.id("mui-4")).click();
		}

	}
	
	
	@Test(priority = 9, enabled = false)
	public void ApplyPriceFlitering() throws InterruptedException {
		Thread.sleep(15000);

		WebElement PriceFilter = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		PriceFilter.click();
	}

	@Test(priority = 10, enabled = false)
	public void ApplyRatingFlitering() throws InterruptedException {
		Thread.sleep(15000);

		WebElement StarsFilter = driver.findElement(By.xpath("//div[contains(text(),'4 stars')]"));
		StarsFilter.click();

	}

	@Test(priority = 11, enabled = false)
	public void ChangeCurrency() throws InterruptedException {
		// Click on the currency list
		WebElement currencyList = driver.findElement(By.xpath("//div[@data-testid='Header__CurrencySelector']"));
		currencyList.click();

		Thread.sleep(1000);

		String[] currencyPrefixes = { "QAR", "AED", "USD", "EGP", "EUR" };

		int randomCurrencyIndex = rand.nextInt(currencyPrefixes.length);
		String selectedCurrency = currencyPrefixes[randomCurrencyIndex];

		WebElement randomCurrency = driver
				.findElement(By.xpath("//div[@data-testid='CurrencySelection__" + selectedCurrency + "']"));

		randomCurrency.click();

		System.out.println("Selected Currency: " + selectedCurrency);

	}


	@Test(priority = 12, enabled = false)
	public void checkAPILoaded() throws InterruptedException {
		Thread.sleep(10000);
		String APIResult = driver.findElement(By.cssSelector(".MuiTypography-root.MuiTypography-heading4SemBld.__ds__comp.undefined.muiltr-13ipltw")).getText();
		if (driver.getCurrentUrl().contains("en")) {
			Assert.assertEquals(APIResult.contains("found"), true);

		} else {
			Assert.assertEquals(APIResult.contains("مكان إقامة"), true);

		}

	}

//		__________________________________________________________________________________

	@Test(priority = 13, enabled = false)
	public void randomCityTwoWay() throws InterruptedException {

		String[] CitiesTwoWayInEnglish = { "dubai", "jeddah", "riyadh" };
		String[] CitiesTwoWayInArabic = { "الرياض", "دبي", "جدة" };

		Random rand = new Random();

		int randomTwoWayEnglishCity = rand.nextInt(CitiesTwoWayInEnglish.length);
		int randomTwoWayArabicCity = rand.nextInt(CitiesTwoWayInArabic.length);

		int randomDestEnglishCity;
		do {
			randomDestEnglishCity = rand.nextInt(CitiesTwoWayInEnglish.length);
		} while (randomDestEnglishCity == randomTwoWayEnglishCity);

		int randomDestArabicCity;
		do {
			randomDestArabicCity = rand.nextInt(CitiesTwoWayInArabic.length);
		} while (randomDestArabicCity == randomTwoWayArabicCity);

		WebElement FlightsOneWay = driver.findElement(By.xpath("//a[@data-testid='FlightHome__RoundTrip']"));
		FlightsOneWay.click();

		WebElement OriginOneWay = driver.findElement(By.id("FlightHome__AirportPicker__OriginSearchInput"));
		WebElement DestinationOneWay = driver.findElement(By.id("FlightHome__AirportPicker__DestinationSearchInput"));

		if (driver.getCurrentUrl().contains("ar")) {
			OriginOneWay.sendKeys(CitiesTwoWayInArabic[randomTwoWayArabicCity]);
			OriginOneWay.click();
			Thread.sleep(1000);
			DestinationOneWay.sendKeys(CitiesTwoWayInArabic[randomDestArabicCity]);
			DestinationOneWay.click();

		} else {
			OriginOneWay.sendKeys(CitiesTwoWayInEnglish[randomTwoWayEnglishCity]);
			OriginOneWay.click();
			Thread.sleep(1000);
			DestinationOneWay.sendKeys(CitiesTwoWayInEnglish[randomDestEnglishCity]);
			DestinationOneWay.click();

		}

		WebElement SearchOneWayFlights = driver.findElement(By.id("mui-1"));
		SearchOneWayFlights.click();

	}
//			

	@Test(priority = 14, enabled = false)
	public void randomCityOneWay() throws InterruptedException {

		String[] CitiesOneWayInEnglish = { "dubai", "jeddah", "riyadh" };
		String[] CitiesOneWayInArabic = { "الرياض", "دبي", "جدة" };

		Random rand = new Random();

		int randomOneWayEnglishCity = rand.nextInt(CitiesOneWayInEnglish.length);
		int randomOneWayArabicCity = rand.nextInt(CitiesOneWayInArabic.length);

		int randomDestEnglishCity;
		do {
			randomDestEnglishCity = rand.nextInt(CitiesOneWayInEnglish.length);
		} while (randomDestEnglishCity == randomOneWayEnglishCity);

		int randomDestArabicCity;
		do {
			randomDestArabicCity = rand.nextInt(CitiesOneWayInArabic.length);
		} while (randomDestArabicCity == randomOneWayArabicCity);

		WebElement FlightsOneWay = driver.findElement(By.xpath("//a[@data-testid='FlightHome__OneWay']"));
		FlightsOneWay.click();

		WebElement OriginOneWay = driver.findElement(By.id("FlightHome__AirportPicker__OriginSearchInput"));
		WebElement DestinationOneWay = driver.findElement(By.id("FlightHome__AirportPicker__DestinationSearchInput"));

		if (driver.getCurrentUrl().contains("ar")) {
			OriginOneWay.sendKeys(CitiesOneWayInArabic[randomOneWayArabicCity]);
			OriginOneWay.click();
			Thread.sleep(1000);
			DestinationOneWay.sendKeys(CitiesOneWayInArabic[randomDestArabicCity]);
			DestinationOneWay.click();

		} else {
			OriginOneWay.sendKeys(CitiesOneWayInEnglish[randomOneWayEnglishCity]);
			OriginOneWay.click();
			Thread.sleep(1000);
			DestinationOneWay.sendKeys(CitiesOneWayInEnglish[randomDestEnglishCity]);
			DestinationOneWay.click();

		}

		WebElement SearchOneWayFlights = driver.findElement(By.id("mui-1"));
		SearchOneWayFlights.click();

	}

	
	@Test(priority = 15, enabled = false)
	public void NavigationToTermsConditionsPage() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement TermsConditionsPage = driver.findElement(By.xpath("//a[@data-testid='TermsAndConditionsButton']"));
		TermsConditionsPage.click();
	}

}
