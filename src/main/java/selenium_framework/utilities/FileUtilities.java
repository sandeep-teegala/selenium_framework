package selenium_framework.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtilities {

	WebDriver driver;

	public FileUtilities(WebDriver driver) {
		this.driver = driver;
	}

	public String getScreenShot(String testName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(
				System.getProperty("user.dir") + "//selenium_framework//output//screenshots" + testName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//selenium_framework//output//screenshots" + testName + ".png";

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Read json to string using fileutiles class
		String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath),
				StandardCharsets.UTF_8);

		// String to hashMap using Jackson Databind dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
