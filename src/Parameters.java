import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://www.almosafer.com/en";
	
	Random rand = new Random();
	String expectedLang = "en";
	String expectedCur = "SAR";
	String expectedNum = "+966554400000";
	boolean expectedlogo = true;
	String expectedSelection = "false";
	LocalDate today = LocalDate.now().plusDays(1);
	int day = today.getDayOfMonth();//27
	String expectedDay = String.format("%02d",day);

	LocalDate todayReturn = LocalDate.now().plusDays(2);
	 
	int dayReturn = todayReturn.getDayOfMonth();
	String expectedDayReturn = Integer.toString(dayReturn);
	
	String[] engCityName = {"jeddah" , "riyadh","dubai"};
	String[] arCityName = {"دبي" , "جدة"};
	
	int randomEngCityName = rand.nextInt(engCityName.length);
	int randomArCityName = rand.nextInt(arCityName.length);
	
	String[] langWebsite = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
	
	int randomWebsite = rand.nextInt(langWebsite.length);
	

	public void Setup()

	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // da2eman baktobha

		driver.manage().window().maximize();
		driver.get(websiteURL);
		
		WebElement currencyLangEn = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		//WebElement currencyLangAr = driver.findElement(By.cssSelector(".sc-jTzLTM.eJkYKb.cta__button.cta__saudi.btn.btn-primary"));

		currencyLangEn.click();
	}
	
	public void Screenshot() throws IOException, InterruptedException
	{
		Thread.sleep(3000);
		Date myDate = new Date();
		String fileName = myDate.toString().replace(":", "-");
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		File destFile = new File("./Screenshots/"+fileName+".jpg");
		FileUtils.copyFile(srcFile, destFile);
	}
	
	public void CheckLangToFill(WebElement hotelSearchBar) throws InterruptedException
	{
		if (driver.getCurrentUrl().equals("https://www.almosafer.com/en"))
		{
			String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedLang = "en";
			Assert.assertEquals(actualLang, expectedLang);
			hotelSearchBar.sendKeys(engCityName[randomEngCityName]);
		}
		else
		{
			String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedLang = "ar";
			Assert.assertEquals(actualLang, expectedLang);
			hotelSearchBar.sendKeys(arCityName[randomArCityName]);
		}
		Thread.sleep(2000);
	}
	
	public void EnterNumberOfVisitor() throws InterruptedException
	{
		WebElement firstChoice = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		firstChoice.findElements(By.tagName("li")).get(1).click();
		
		WebElement selectList = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select select = new Select(selectList);
		
		int randomSelect = rand.nextInt(2);
		select.selectByIndex(randomSelect);
		Thread.sleep(3000);

		WebElement hotelsSearchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		hotelsSearchButton.click();
		Thread.sleep(35000);

	}
	
	public void CheckThePageLoad()
	{
		WebElement searchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		boolean actualResult = searchResult.getText().contains("found") || searchResult.getText().contains("مكان");
		boolean expectedResult = true;
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	public void RandomlyEnterWebsite()
	{
driver.get(langWebsite[randomWebsite]);
		
		WebElement hotelsTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelsTab.click();
		
	}
	
	public void SortOptionChecker() throws InterruptedException
	{
		
		WebElement Container = driver.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muiltr-1smo8f0"));

		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("SAR ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);
		} else {
	
			List<WebElement> priceList = Container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int lowestPrice = Integer.parseInt(priceList.get(0).getText().replace("ر.س. ", ""));
			System.out.println();
			int HighestPrice = Integer.parseInt(priceList.get(priceList.size() - 1).getText().replace("ر.س. ", ""));
			System.out.println(lowestPrice);
			System.out.println(HighestPrice);

			boolean ActualValue = lowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			System.out.println(ActualValue);
			System.out.println(ExpectedValue);

			Assert.assertEquals(ActualValue, ExpectedValue);

		}
	}
}
