package AT07.POM.testcases;

import AT07.Listeners.TestListener;
import AT07.POM.pages.LoginPage;
import Initialization.Init;
import helpers.CaptureHelper;
import helpers.ExcelHelper;
import helpers.drivers.DriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import java.io.IOException;

@Listeners(TestListener.class)
public class LoginTest extends Init {
	private LoginPage loginPage;
	String fileName = "src/test/resources/testdata/DataCRM.xlsx";
	String sheetName = "Login";

	@Test
	public void loginTestInvalidEmail(Method method) throws IOException {
		loginPage = new LoginPage(DriverManager.getDriver());
		CaptureHelper.startRecord(method.getName());
		DriverManager.getDriver().get("https://crm.anhtester.com/admin/authentication");
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile(fileName, sheetName);
		loginPage.loginWithInvalidEmail(fileName, sheetName);
		sleep(2);
	}

	@Test
	public void loginTestInvalidPassword() {
		LoginPage loginPage = new LoginPage(DriverManager.getDriver());
		DriverManager.getDriver().get("https://crm.anhtester.com/admin/authentication");
		loginPage.loginAdminPage("admin@example.com", "123123");
		sleep(2);
	}
	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			CaptureHelper.captureScreenshot(result.getName());
		}
		CaptureHelper.stopRecord();
	}
}
