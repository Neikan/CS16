package appmanager;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  JavascriptExecutor js;

  private HelperBase helperBase;
  private HelperSession helperSession;
  private HelperNavigation helperNavigation;
  private HelperDocs helperDocs;
  private HelperDocsOut helperDocsOut;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
 /* Alert Windowalert = wd.switchTo().alert() ;
    //Windowalert.authenticateUsing(new UserAndPassword(_user_name,_password));
    Windowalert.accept();
    wd.switchTo().defaultContent() ;*/

    helperBase = new HelperBase(this);
    helperDocs = new HelperDocs(this);
    helperDocsOut = new HelperDocsOut(this);
    helperNavigation = new HelperNavigation(this);
    helperSession = new HelperSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public WebDriver getDriver() throws MalformedURLException {
    if (wd == null) {
      if ("".equals(properties.getProperty("selenium.server"))) {
        if (browser.equals(BrowserType.FIREFOX)) {
          wd = new FirefoxDriver();
          DesiredCapabilities capabilities = DesiredCapabilities.firefox();
          //capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
          capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager"); // normal (document.readyState == complete), eager (document.readyState == interactive), none – вообще не ждать

        } else if (browser.equals(BrowserType.CHROME)) {
          wd = new ChromeDriver();
          DesiredCapabilities capabilities = DesiredCapabilities.chrome();
          //capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
          capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        } else if (browser.equals(BrowserType.IE)) {
          wd = new InternetExplorerDriver();
          DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
          //capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
          capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        }
      } else {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        //capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
      }

      js = (JavascriptExecutor) wd;
      wd.manage().window().maximize();
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      //wd.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
      //wd.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HelperNavigation goTo() throws MalformedURLException {
    if (helperNavigation == null) {
      helperNavigation = new HelperNavigation(this);
    }
    return helperNavigation;
  }

  public HelperDocs docs() throws MalformedURLException {
    if (helperDocs == null) {
      helperDocs = new HelperDocs(this);
    }
    return helperDocs;
  }

  public HelperDocsOut docsout() throws MalformedURLException {
    if (helperDocsOut == null) {
      helperDocsOut = new HelperDocsOut(this);
    }
    return helperDocsOut;
  }

  public HelperSession session() throws MalformedURLException {
    if (helperSession == null) {
      helperSession = new HelperSession(this);
    }
    return helperSession;
  }

  private static void addJQuery (JavascriptExecutor js) {
    String script = "";
    boolean needInjection = (Boolean)(js.executeScript("return this.$ === undefined;"));
    if(needInjection) {
      URL u = Resources.getResource("jquery.js");
      try {
        script = Resources.toString(u, Charsets.UTF_8);
      } catch(IOException e) {
        e.printStackTrace();
      }
      js.executeScript(script);
    }
  }

}
