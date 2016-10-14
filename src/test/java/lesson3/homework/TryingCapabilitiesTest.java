package lesson3.homework;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

/**
 * Created by AlexSh on 12.10.2016.
 */

/*
1. FirefoxProfile for Firefox
2. ChromeOptions for Chrome
3. DesiredCapabilities for Internet Explorer

Selenium WebDriver ... is implemented through a browser-specific browser driver,
which ... aims to provide a basic set of building blocks from which developers can create
their own Domain Specific Language(c)
*/
/*
Three Rings for the Elven-kings under the sky....(c)
*/



public class TryingCapabilitiesTest {

     private WebDriver myPersonalDriver;
     private DesiredCapabilities allThatYouWant;

    //In mysterious way it will work for Opera too.
     private ChromeOptions options = new ChromeOptions();

    //Browsers we understand
     ArrayList<String> browsersHerd= new ArrayList<String> (Arrays.asList(new String[]{"chrome", "opera"}));

    //System Properties we like
     String[] sysProperty = new String[]{
            "webdriver.opera.driver",
            "webdriver.chrome.driver"

    };

    //Path to driver will be initialized in beforeTest
    private String driversPath;
    //browser type will be initialized in beforeTest
    private String myBrowser;

        @BeforeTest
        //Parameters from testng.xml
        @Parameters({ "browser", "pathToDriver"})
        public void doBeforeTest(@Optional("chrome") String browser, @Optional("D:\\PersonalDrivers\\chromedriver.exe") String pathToDriver ) {

            //Since now everybody can get it
            myBrowser = browser;
            driversPath = pathToDriver;

            //Checking whether file is exist
            File f = new File(pathToDriver);



            if(!f.exists() || f.isDirectory()) {
                System.out.println("Error! Check your browser's path in testng.xml!");
                Assert.fail("Error! Check your browser's path in testng.xml!");
            }
            //Checking  whether browser of correct type
            if (!browsersHerd.contains(myBrowser.toLowerCase())){
                System.out.println("Error! Check your browser type in testng.xml!");
                System.out.println("only chrome or opera for now");
                Assert.fail("Error! Check your browser type testng.xml!");
            }

            System.out.println(myBrowser+" "+driversPath);

        }

    @DataProvider(name = "useragent")
    public Object[][] createUserAgent() {
        return new Object[][] {
                {"Android Webkit", "--user-agent=Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36" },
                {"BlackBerry","--user-agent=Mozilla/5.0 (BlackBerry; U; BlackBerry 9900; en) AppleWebKit/534.11+ (KHTML, like Gecko) Version/7.1.0.346 Mobile Safari/534.11+"},
                {"Netscape Navigator from early 2000-s :))) Sun Microsystems Sparc! I had it in my college...", "--user-agent=Mozilla/4.5 (X11; I; SunOS 5.6 sun4u)"},

        };
    }

    @Test (dataProvider = "useragent")
    public void doTest(String nickName, String trueAgent007Name) {



        //Since Java 7 we can use String
        switch (myBrowser.toLowerCase()) {

            case "opera":
                System.setProperty(sysProperty[0], driversPath);

                options.addArguments(trueAgent007Name);
                allThatYouWant = DesiredCapabilities.chrome();
                //What are trick! ChromeOptions are working for Opera
                allThatYouWant.setCapability(ChromeOptions.CAPABILITY, options);
                myPersonalDriver = new OperaDriver(allThatYouWant);

                break;

            case "chrome":
                System.setProperty(sysProperty[1], driversPath);
                options.addArguments(trueAgent007Name);
                allThatYouWant = DesiredCapabilities.chrome();
                allThatYouWant.setCapability(ChromeOptions.CAPABILITY, options);
                myPersonalDriver = new ChromeDriver(allThatYouWant);

                break;
        }

        System.out.println(options);
        System.out.println(allThatYouWant);


        myPersonalDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        myPersonalDriver.get("http://www.whatsmyua.com");
        myPersonalDriver.manage().window().maximize();

        String searchString = "//*[@id='ua-link'][contains(@href,'Android')" +
                               " "+"or contains(@href,'BlackBerry')" +
                               " "+"or contains(@href,'SunOS')]";

        //Looking in href for words  "Linux" , "Android"  and id='ua-link'
        assertTrue (myPersonalDriver.findElements(By.xpath(searchString)).size()!=0);
        myPersonalDriver.close();
    }

    @AfterTest
    public void doAfterTest() {

       myPersonalDriver.quit();

    }
    /*@AfterSuite
    public void doAfterSuite() {

        myPersonalDriver.quit();
    }*/

}

