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
	
	
}
