import io.github.bonigarcia.wdm.WebDriverManager;
import io.tesbo.report.reportUtility.ReportUtility;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdaptorTest {

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"one"}, {"two"}, {"First-Value"}, {"Second-Value"}, {"Ramesh"}, {"Kamlesh"},
                {"one"},
                {"one"},
                {"hjjhkj"}, {"one"},
                {"onedsas"},
                {"ondasde"}, {"one"},
                {"onasdae"},
                {"oneasd"}, {"one"}, {"one"}, {"one"}, {"one"}, {"one"}, {"one"}, {"one"},
                {"one"}, {"one"}, {"one"},
                {"one"},
                {"one"},
                {"one"},
                {"one"},
                {"one"},
                {"one"}, {"one"},
                {"one"},
                {"one"},

        };
    }


    @BeforeMethod
    public  void  mahesh()
    {
        Reporter.log("Passed Parameter Is ");
        Reporter.log("Passed Parameter Is ");

    }

    @Test(dataProvider = "data-provider")
    public void TestingDataProvider(String val) {
        Reporter.log("Passed Parameter Is : " + val);
        Reporter.log("Passed Parameter Is : " + val);
    }
/*
    @Test
    public void ABCDFailed() {
        Assert.assertTrue(false);
    }
*/

    @Test
    public void ABCD() {

        WebDriverManager.chromedriver().setup();

       /* ChromeDriver driver = new ChromeDriver();

        driver.get("https://google.com");

        Reporter.log(ReportUtility.captureScreenshot(driver));*/
        Reporter.log("ANCD Parameter Is : ");

        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );

    }

    @Test
    public void TestWithScreenShot() {

      Reporter.log("screenshot: /Users/viralpatel/Viral/TestNG-Adaptor_1/src/test/java/test.png");
      Reporter.log("<img src=\"https://images.unsplash.com/photo-1670272506160-bdf0c7a45d2b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxfHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=700&q=60\" alt=\"Trulli\" width=\"500\" height=\"333\">");

    }


}
