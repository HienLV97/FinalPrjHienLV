package reports;
import helpers.drivers.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureManager {
	//Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	//Screenshot attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] saveScreenshotPNG() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	}
/*
	@Epic
	@Features
	@Stories/@Story
	@Severity(SeverityLevel.BLOCKER)
	@Description("In this cool test we will check cool thing")
	@Step
	@Attachment
	@Link

 */
}