package tests;

import appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import tests.listeners.ListenerTestNG;

import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners(ListenerTestNG.class)
public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); //

  @BeforeSuite
  public void setUp(ITestContext context) {
    app.init();
    context.setAttribute("app", app);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    //logger.debug("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
    logger.info("Start test " + m.getName() + " with parameters" + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    //logger.debug("Stop test " + m.getName());
    logger.info("Stop test " + m.getName());
  }

}
