package io.tesbo.report.screenshotutility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        File file = null;
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        file = new File("src/test/resources/failed_test_screenshot/" + screenshotName + "_" + RandomStringUtils.randomAlphanumeric(5) + ".png");
        try {
            FileUtils.copyFile(src, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePathLog = "<img src=\"" + file.getAbsolutePath() + "\" alt=\"test\" width=\"1024\" height=\"640\">";
        return filePathLog;
    }

}
