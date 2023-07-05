package selenium_framework.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtilities {

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

	public static ExtentReports testReport() {
		// first we need to get the maven dependencies form mvn repo
		// here we need two classes to geneate the reports those are ExtentReports,
		// ExtentSparkReporter

		// To do some configuration we use ExtentSparkReporter class
		String reportPath = System.getProperty("user.dir") + "\\output\\extentreport\\index.html";
		ExtentSparkReporter reportConfig = new ExtentSparkReporter(reportPath);
		reportConfig.config().setReportName("My First Extent Report");
		reportConfig.config().setDocumentTitle("First Report");

		// now we use the main the class ExtentReports and map those config.
		ExtentReports report = new ExtentReports();
		report.attachReporter(reportConfig);
		report.setSystemInfo("Tester", "Sandeep Teegala");

		return report;

		// after this we use some methods like report.createTest() - starting of the
		// test.
		// report.flush() - ending of the test in our tests
		// after when we run the test a report when generated at the path we given
	}

}
