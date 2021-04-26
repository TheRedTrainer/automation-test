package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test 
public class SeleniumTest {
	
	/*
	 *Configure Selenium or Cypress and make the following workflow:

		Open browser in http://localhost:3000/shows
		Enter a text in search box with text batman
		Press button search
		Navigate to the url that is show in second card of results
		Navigate back using browser features
		Change css background color to #4a148c to card with title Batman Unlimited
		Press back button
		Make sure that input for search is empty 
	 * *
	 */
	
	@Test
	public void searchTvShow()
	{
		WebDriver driver = new ChromeDriver();
		
		try {
		driver.get("http://localhost:3000/shows");
		WebElement txtSearch = driver.findElement(By.name("search"));
		txtSearch.sendKeys("batman");
		WebElement btnSearch = driver.findElement(By.className("btn"));
		btnSearch.click();
		WebElement secondElementLink = driver.findElement(By.xpath("(//*[contains(text(),'URL') and @class='white-text'])[2]"));
		secondElementLink.click();
		driver.navigate().back();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//querySelector('div[SomeText*]').innerTEXT;
		WebElement divToChange = driver.findElement(By.xpath(("//*[contains(text(),'Batman Unlimited')]")));
//		jse.executeScript("document.querySelector('div:contains(\"Batman Unlimited\")').style.backgroundColor=\"#4a148c\"");
//		jse.executeScript("document.evaluate('//*[contains('div',\"Batman Unlimited\")]').style.backgroundColor=\"#4a148c\"");
		jse.executeScript("arguments[0].parentElement.style.backgroundColor=\"#4a148c\"", divToChange);
		
		// Sleep para poder ver el color de la tarjea
		Thread.sleep(10000);
		
		//btn btn-primary
		WebElement linkBack = driver.findElement(By.xpath("(//*[contains(text(),'Back') and contains(@class,'btn btn-primary') ])"));
//		WebElement linkBack = driver.findElement(By.xpath("(//*[contains(text(),'BACK') and contains(@class,'btn btn-primary') ])"));
		linkBack.click();
		
		//Sleep para validar que se presionó el botón back
		Thread.sleep(5000);
		
		txtSearch = driver.findElement(By.name("search"));
		String txtFieldStatus="";
		if (txtSearch.getText().isBlank())
		{
			txtFieldStatus="El campo de búsqueda está vacío";
			Assert.assertTrue(true,txtFieldStatus);
		}else {
			txtFieldStatus="El campo de búsqueda NO esta vacío";
			Assert.assertTrue(false,txtFieldStatus);
		}
		System.out.println(txtFieldStatus);

		
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			driver.quit();
		}		
		
	}
	
}
