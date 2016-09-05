package pageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

public class HotelsPage {
	WebDriver driver;
	ExtentTest test;

	public HotelsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='#HOTELS']")
	public WebElement HOTELS_Tab;

	@FindBy(xpath = ".//*[@id='HOTELS']/div/form/div[1]/input")
	public WebElement locationBox;

	@FindBy(xpath = ".//*[@id='dpd1']/input")
	public WebElement checkInDate;

	@FindBy(xpath = ".//*[@id='dpd2']/input")
	public WebElement checkOutDate;

	@FindBy(xpath = ".//*[@id='HOTELS']/div/form/div[5]//input")
	public WebElement adultPassenger;

	@FindBy(xpath = ".//*[@id='HOTELS']/div/form/div[6]//input")
	public WebElement childPassenger;

	@FindBy(xpath = ".//*[@id='HOTELS']/div/form/button")
	public WebElement searchButton;

	public void clear() {
		locationBox.clear();
		adultPassenger.clear();
		childPassenger.clear();
	}

	public void fillInLocation(String _location) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		locationBox.sendKeys(_location);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='HOTELS']//ul//li[2]")));
		driver.findElement(By.xpath(".//*[@id='HOTELS']//ul//li[2]")).click();
	}

	public void setDates(String _chkInDate, String _chkOutDate) {

		checkInDate.click();
		List<WebElement> chkInDays = driver.findElements(By.xpath(".//*[@id='top']/div[10]//td"));
		for (WebElement day : chkInDays) {
			if (day.getText().equals(_chkInDate)) {
				day.click();
				break;
			}
		}
		List<WebElement> chkOutDays = driver.findElements(By.xpath(".//*[@id='top']/div[11]//td"));
		for (WebElement day : chkOutDays) {
			if (day.getText().equals(_chkOutDate)) {
				day.click();
				break;

			}
		}
	}

	public void clickSearchButton() throws Exception {

		WebDriverWait wd = new WebDriverWait(driver, 5);
		wd.until(ExpectedConditions.elementToBeClickable(searchButton));

		searchButton.click();
		wd.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='top']/div[3]/div/div/div[2]/ul/li[7]/a")));
		driver.navigate().back();
	}

}
