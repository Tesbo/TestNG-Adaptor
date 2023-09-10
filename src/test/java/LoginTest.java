import io.tesbo.selfadaptor.TesboReportTest;
import io.tesbo.selfadaptor.TestSuite;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest {

    TestSuite testSuite = new TestSuite();

    @BeforeSuite
    public void beforeSuite() {
        testSuite.createSuite("JyUvHcM61YSIbBNa3VR9x5RudZxQuAcn");
        testSuite.testSuiteName = "Regression";
    }

    @BeforeMethod
    public void beforeMethod() {

    }


    @Test
    public void ABCD() {
        TesboReportTest test = new TesboReportTest();

        test.moduleName = "Login";
        test.browser = "Chrome";


        test.testName = "Verify user is able to login successfully";
        test.addStep("Loginv button is clicked", "PASS");
        test.addStep("Loginv button is clicked", "PASS");
        test.addStep("Loginv button is clicked", "PASS");
        test.addStep("Loginv button is clicked", "PASS");
        test.addStep("Loginv button is clicked", "PASS");
        test.addStep("Loginv button is clicked", "PASS");

        testSuite.saveTest(test, "PASS");

        TesboReportTest test2 = new TesboReportTest();
        test2.browser = "Chrome";
        test2.moduleName = "Login";
        test2.testName = "Verify user is able to login successfully with false credentials";
        test2.addStep("sdfsdfsdf ", "PASS");
        test2.addStep("sdwqwejqlwkejhlqkweklqfsdfsdf ", TesboReportTest.Status.PASS.toString());


        try {
            Assert.assertTrue(false);
            testSuite.saveTest(test2, "PASS");
        } catch (AssertionError e) {

            testSuite.recordTestFailure(test2, e);
        }


    }


    @Test
    public void Mahesh() {

        TesboReportTest test = new TesboReportTest();

        test.testName = "Mahesh is testing";
        test.addStep("Mahesh", "PASS");
        test.addStep("Ramehs", "PASS");
        test.addStep("Suresh", "PASS");
        test.addStep("Ajfkj", "PASS");
        testSuite.saveTest(test, "PASS");

    }

    @AfterSuite
    public void afterSuite() {

    }


}
