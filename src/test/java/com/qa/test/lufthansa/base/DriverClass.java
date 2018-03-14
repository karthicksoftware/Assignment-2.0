package com.qa.test.lufthansa.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;


public class DriverClass {
	public static WebDriver driver = null;
	public static Logger log = Logger.getLogger(DriverClass.class);

	@BeforeClass
	public void initializeLogger() throws Exception {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\Loggers\\logger.properties");
	}
	
	public static void initializeBrowsers(String browser) throws Exception {
		System.out.println("Inside initialize Browser");
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") +"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") +"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
	}
	
	/*@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null)
			quitBrowser();
	}*/

	protected void launchApp(String url) throws Exception {
		driver.get(url);
		Thread.sleep(5000);
	}

	protected String getTitle() throws Exception {
		return driver.getTitle();
	}

	protected void quitBrowser() throws Exception {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	protected static String getProperty(String name) throws Exception {
		Properties prop = new Properties();
		InputStream propertyFile = new FileInputStream(
				System.getProperty("user.dir") + "\\ExternalData\\TextData.properties");
		prop.load(propertyFile);
		return prop.getProperty(name);
	}
	
	protected static void waitForPageToLoad() throws Exception{
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(new ExpectedCondition<Boolean>() {
		      public Boolean apply(WebDriver d) {		          
		          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		       }
		});
	}

	protected WebElement getElement(String locator) throws Exception {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,20);
		return wait.until(ExpectedConditions.presenceOfElementLocated(getLocatorType(locator)));
	}

	protected List<WebElement> getAllElements(String locator) throws Exception {
		return driver.findElements(getLocatorType(locator));
	}

	/**
	 * <p>
	 * Method to identify locator type. As part of this minor framework, we have
	 * used only ID, Xpath & Css selectors
	 * </p>
	 * 
	 * @param locator
	 * @return
	 * @throws Exception
	 * @author Ramya
	 */
	public By getLocatorType(String locator) throws Exception {
		if (locator.startsWith("//"))
			return By.xpath(locator);
		else if (locator.startsWith("css="))
			return By.cssSelector(locator.split("css=")[1]);
		else
			return By.id(locator);
	}

	protected void clickElement(String locator) throws Exception {
		getElement(locator).click();
	}
	
	protected void clickElementJS(String locator) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", getElement(locator));
	}
	protected void clickElementJS(WebElement locator) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", locator);
	}
	protected void clickElement(WebElement element) throws Exception {
		element.click();
	}

	protected void typeOnElement(String element, String text) throws Exception {
		getElement(element).sendKeys(text);
	}

	protected static String getData(String name) throws Exception {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\ExternalData\\ExcelData.xlsx");
		Workbook wb = new XSSFWorkbook(fs);
		Sheet sh = wb.getSheetAt(0);
		int totalNoOfRows = sh.getPhysicalNumberOfRows();
		String value = null;
		DataFormatter df = new DataFormatter();
		for (int row = 0; row < totalNoOfRows; row++) {
			if(sh.getRow(row).getCell(0).getStringCellValue().equalsIgnoreCase(name)){
				value = df.formatCellValue(sh.getRow(row).getCell(1));
				System.out.println("Value:"+value);
			}
		}
		return value;
	}

	protected String getText(String locator) throws Exception{
		return getElement(locator).getText();
	}
	protected String getText(WebElement locator) throws Exception{
		return locator.getText();
	}
	
	public WebElement moveToWebElement(String locator) throws Exception{
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator));
		action.perform();
		return getElement(locator);
	}
	
	public WebElement moveToWebElement(WebElement locator) throws Exception{
		Actions action = new Actions(driver);
		action.moveToElement(locator);
		action.perform();
		return locator;
	}
	
	
}
