import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://www.almosafer.com/en";
	
	Random rand = new Random();
	
	
	@BeforeTest
	public void mySetup()
	{
		driver.manage().window().maximize();
		driver.get(websiteURL);
		
		WebElement currencyLangEn = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		//WebElement currencyLangAr = driver.findElement(By.cssSelector(".sc-jTzLTM.eJkYKb.cta__button.cta__saudi.btn.btn-primary"));

		currencyLangEn.click();
	}
	
	@Test(priority = 1)
	public void CheckEngLanguageIsDefault()
	
	{
		//WebElement lang = driver.findElement(By.xpath("//html[@lang='en']"));
		
		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		String expectedLang = "en";
		Assert.assertEquals(actualLang, expectedLang);
		
	}
	@Test(priority = 2)
	public void CheckSARCurrencyIsDefault()
	{
		String actualCur = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		String expectedCur = "SAR";
		Assert.assertEquals(actualCur, expectedCur);
	}
	@Test(priority = 3)
	public void CheckContactNumberIsCorrect()
	{
		String actualNum = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String expectedNum = "+966554400000";
		Assert.assertEquals(actualNum, expectedNum);
	}
	@Test(priority = 4)
	public void CheckQitafLogo()
	{
		boolean actuallogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		boolean expectedlogo = true;
		Assert.assertEquals(actuallogo, expectedlogo);
	}
	@Test(priority = 5)
	public void CheckHotelsNotSelected()
	{
		WebElement hotelAtt = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String actualSelection = hotelAtt.getAttribute("aria-selected");
		String expectedSelection = "false";
		Assert.assertEquals(actualSelection, expectedSelection);

	}
	@Test(priority = 6 )
	public void CheckFlightDepartureDate() throws ParseException
	{
		LocalDate today = LocalDate.now().plusDays(1);
		//System.out.println(today);
		 
		//String expectedDate = today.toString();
		
		int day = today.getDayOfMonth();//27
		String expectedDay = Integer.toString(day);
		
		
		String actualDay = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
		Assert.assertEquals(actualDay, expectedDay);
		
//		String month = today.getMonth().toString() ;//10
//		
		 // sunday
//		String deptMonth = driver.findElement(By.cssSelector(".sc-hvvHee.cuAEQj")).getText(); // October
//		String dayNum = driver.findElement(By.cssSelector(".sc-eSePXt.ljMnJa")).getText(); // 27
		
//		String dd =  dayNum +"-"+deptMonth+"-"+deptDay; //27-October-Sunday
//		
//		String pattern = "E d MMMM";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		String date = simpleDateFormat.format(today);
//		System.out.println(date);
	}
	
	@Test(priority = 7)
	public void CheckFlightReturnDate()
	{

		LocalDate today = LocalDate.now().plusDays(2);
		System.out.println(today);
		 
		//String expectedDate = today.toString();
		
		int day = today.getDayOfMonth();
		String expectedDay = Integer.toString(day);
		
		
		String actualDay = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
		Assert.assertEquals(actualDay, expectedDay);
		
	}
	
	@Test(priority = 8)
	public void CheckLanguage()
	{
		String[] langWebsite = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
		int randomWebsite = rand.nextInt(langWebsite.length);
		driver.get(langWebsite[randomWebsite]);
		
		if (driver.getCurrentUrl().equals("\"https://www.almosafer.com/en\""))
		{
			String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedLang = "en";
			Assert.assertEquals(actualLang, expectedLang);
		}
		else
		{
			String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			String expectedLang = "ar";
			Assert.assertEquals(actualLang, expectedLang);
		}
	}
	
	
}
