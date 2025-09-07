package AT07.POM.pages;

import AT07.Listeners.TestListener;
import Keywords.WebUI;
import Initialization.Init;
import helpers.ExcelHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

@Listeners(TestListener.class)
public class LoginPage extends Init {
	ExcelHelper excelHelper = new ExcelHelper();
	private Workbook wb;
	private Sheet sh;
	private String fileName;
	private String dataServicePage;
	private String sheetHeaderData;
	private final WebDriverWait wait;

	public LoginPage(WebDriver driver) {
//		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='email']")
	WebElement emailTB;
	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordTB;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBTN;
	private By errorMess = By.xpath("//div[contains(@class,'alert alert-danger')]");

	public void setEmail(String value) {
		WebUI.setText(emailTB, value);
	}

	public void setPasswordTB(String value) {
		WebUI.setText(passwordTB, value);
	}

	public void clickLoginBTN() {
		WebUI.clickWEBElement(loginBTN);
	}

	public void verifyLoginFailed() {
		WebUI.waitForPageLoaded();
//		WebUI.logConsole("vao day");
		Assert.assertTrue(WebUI.checkElementExist(errorMess),"Failed - The error message is not display");
		Assert.assertEquals(WebUI.getElementText(errorMess),"Invalid email or password");
	}
	public void loginAdminPage(String email, String password) {
		setEmail(email);
		setPasswordTB(password);
		sleep(2);
		clickLoginBTN();
	}

	public void loginWithInvalidEmail(String fileName, String sheetName) throws IOException {
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "DESCRIPTION");
		for (int i = 1; i <= lastRow; i++) {
			if(checkDescription(fileName,sheetName,"Invalid email",i)){
				excelHelper.setExcelFile(fileName, sheetName);
				String EMAIL = excelHelper.getCellData("EMAIL", i);
				String PASSWORD = excelHelper.getCellData("PASSWORD", i);
				loginAdminPage(EMAIL,PASSWORD);
			}
		}
		sleep(2);
		verifyLoginFailed();
	}
	public boolean checkResult(String fileName, String sheetName, int i) {
		excelHelper.setExcelFile(fileName, sheetName);
		return !Objects.equals("Passed", excelHelper.getCellData("RESULT", i));
	}
	public boolean checkDescription(String fileName, String sheetName,String Des, int i) {
		excelHelper.setExcelFile(fileName, sheetName);
		return Objects.equals(Des, excelHelper.getCellData("DESCRIPTION", i));
	}
}
