import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters
{

	@BeforeTest
	public void mySetup()
	{
		Setup();
	}
	
	@Test(priority = 1, enabled = true)
	public void CheckEngLanguageIsDefault() throws IOException, InterruptedException
	{
		String actualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(actualLang, expectedLang);
		Screenshot();
	}
	@Test(priority = 2, enabled = false)
	public void CheckSARCurrencyIsDefault() throws IOException, InterruptedException
	{
		String actualCur = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO")).getText();
		Assert.assertEquals(actualCur, expectedCur);
		
		Screenshot();
	}
	@Test(priority = 3, enabled = false)
	public void CheckContactNumberIsCorrect() throws IOException, InterruptedException
	{
		String actualNum = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		Assert.assertEquals(actualNum, expectedNum);
		Screenshot();
	}
	@Test(priority = 4, enabled = false)
	public void CheckQitafLogo() throws IOException, InterruptedException
	{
		boolean actuallogo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		Assert.assertEquals(actuallogo, expectedlogo);
		Screenshot();
	}
	@Test(priority = 5, enabled = false)
	public void CheckHotelsNotSelected() throws IOException, InterruptedException
	{
		WebElement hotelAtt = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String actualSelection = hotelAtt.getAttribute("aria-selected");
		Assert.assertEquals(actualSelection, expectedSelection);
		Screenshot();
	}
	@Test(priority = 6, enabled = false )
	public void CheckFlightDepartureDate() throws ParseException, IOException, InterruptedException
	{
		String actualDay = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']")).getText();
		Assert.assertEquals(actualDay, expectedDay);
		Screenshot();
	}
	
	@Test(priority = 7, enabled = false )
	public void CheckFlightReturnDate() throws IOException, InterruptedException
	{
		String actualDay = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']")).getText();
		Assert.assertEquals(actualDay, expectedDayReturn);
		Screenshot();
	}
	
	@Test(priority = 8, enabled=false)
	public void CheckLanguage() throws InterruptedException
	{
		
		RandomlyEnterWebsite();
		
		WebElement searchInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		
		CheckLangToFill(searchInput);
		
		EnterNumberOfVisitor();
		
		CheckThePageLoad();
	}
	@Test(priority = 9, enabled = false)
	public void CheckSortOption() throws InterruptedException
	{
		driver.get("https://www.almosafer.com/en/hotels/%D8%AC%D8%AF%D8%A9/14-11-2024/15-11-2024/1_adult?placeId=ChIJWX4TsR_QwxUR2xixN5dXWeA&city=%D8%AC%D8%AF%D8%A9&sortBy=LOWEST_PRICE");
		Thread.sleep(25000);
		
		WebElement lowestPrice = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		lowestPrice.click();
		
		
		SortOptionChecker();	
	
		
	}
	
}
