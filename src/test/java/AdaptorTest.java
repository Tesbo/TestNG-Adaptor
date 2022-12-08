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
        System.out.println("before");
    }
    @Test(dataProvider = "data-provider")
    public void TestingDataProvider(String val) {
        Reporter.log("Passed Parameter Is : " + val);
        Reporter.log("Passed Parameter Is : " + val);
    }


    @Test
    public void ABCD() {
        Reporter.log("ANCD Parameter Is : ");
        Reporter.log("NSFKFL Parameter Is :" );
        Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );  Reporter.log("NSFKFL Parameter Is :" );
    }

}
