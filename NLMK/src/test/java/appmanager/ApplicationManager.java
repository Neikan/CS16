package appmanager;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
import java.sql.Timestamp;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  JavascriptExecutor js;
  BrowserMobProxyServer proxyServer;
  Proxy proxy;

  private HelperSession helperSession;
  private HelperNavigation helperNavigation;
  private HelperDocs helperDocs;
  private HelperDocsOut helperDocsOut;
  private String browser;
  private boolean isServerStarted;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() {
    String target = System.getProperty("target", "local");
    try {
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    } catch (IOException e) {
      e.printStackTrace();
    }

    helperDocs = new HelperDocs(this);
    helperDocsOut = new HelperDocsOut(this);
    helperNavigation = new HelperNavigation(this);
    helperSession = new HelperSession(this);
    proxyServer.newHar("MyExample");

     /* Alert Windowalert = wd.switchTo().alert() ; //Попытка отключить windows-запрос логина-пароля, неудачная
    Windowalert.authenticateUsing(new UserAndPassword(_user_name,_password));
    Windowalert.accept();
    wd.switchTo().defaultContent() ;*/
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public WebDriver getDriver(BrowserMobProxyServer proxyServer) {
    if (proxy == null) {
      System.out.println("2. Port Started On: " + proxyServer.getPort());
      proxy = ClientUtil.createSeleniumProxy(proxyServer);
      proxy.setHttpProxy("localhost:" + proxyServer.getPort());
      proxy.setSslProxy("localhost:" + proxyServer.getPort());
    }

    if (wd == null) {
      if ("".equals(properties.getProperty("selenium.server"))) {
        if (browser.equals(BrowserType.FIREFOX)) {
          DesiredCapabilities capabilities = DesiredCapabilities.firefox();
          capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager"); // normal (document.readyState == complete), eager (document.readyState == interactive), none – вообще не ждать
          capabilities.setCapability(CapabilityType.PROXY, proxy);

          FirefoxOptions options = new FirefoxOptions();
          options.merge(capabilities);
          wd = new FirefoxDriver(options);

        } else if (browser.equals(BrowserType.CHROME)) {
          DesiredCapabilities capabilities = DesiredCapabilities.chrome();
          capabilities.setCapability(CapabilityType.PROXY, proxy);
          capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal"); // eager не поддерживается в Chrome да и после решения проблемы с ожиданиями не факт, что нужна
          ChromeOptions options = new ChromeOptions();
          //options.addArguments("--disable-notifications"); // Попытка отключить windows-запрос логина-пароля, неудачная, т.к. опция с этим не срабатывает
          options.merge(capabilities);
          wd = new ChromeDriver(options);

        } else if (browser.equals(BrowserType.IE)) {
          DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
          capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
          capabilities.setCapability(CapabilityType.PROXY, proxy);
          wd = new InternetExplorerDriver();
        }
      } else {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        try {
          wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
      }

      js = (JavascriptExecutor) wd;
      wd.manage().window().maximize();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      //wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // Пока не нужно
      //wd.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS); // Пока не нужно

      proxyServer.newHar("MyExample");
      wd.get(properties.getProperty("web.baseUrl"));
/*
      try {
        String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
        String time = timeRaw.replace(":", "-").replace(" ", "T");
        proxyServer.getHar().writeTo(new File("results\\Test " + time + ".json"));
      } catch (IOException e) {
        e.printStackTrace();
      }*/
    }
    return wd;
  }

  protected BrowserMobProxyServer getProxy() {
    if (proxyServer == null) {
      proxyServer = new BrowserMobProxyServer();
      proxyServer.setTrustAllServers(true);
      proxyServer.setMitmManager(ImpersonatingMitmManager.builder().trustAllServers(true).build());
      try {
        proxyServer.start();
        System.out.println("1. Port Started On: " + proxyServer.getPort());
        isServerStarted = true;
      } catch (Exception e) {
        throw new RuntimeException("Cant start proxy-server on port: " + proxyServer.getPort(), e);
      }
    }
    proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT, CaptureType.RESPONSE_HEADERS);
    return proxyServer;
  }


  public void stop() {
    if (wd != null) {
      wd.quit();
    }
    if (isServerStarted) {
      proxyServer.stop();
    }
  }

  public HelperNavigation goTo() {
    if (helperNavigation == null) {
      helperNavigation = new HelperNavigation(this);
    }
    return helperNavigation;
  }

  public HelperDocs docs() {
    if (helperDocs == null) {
      helperDocs = new HelperDocs(this);
    }
    return helperDocs;
  }

  public HelperDocsOut docsout() {
    if (helperDocsOut == null) {
      helperDocsOut = new HelperDocsOut(this);
    }
    return helperDocsOut;
  }

  public HelperSession session() {
    if (helperSession == null) {
      helperSession = new HelperSession(this);
    }
    return helperSession;
  }

  private static void addJQuery(JavascriptExecutor js) {
    String script = "";
    boolean needInjection = (Boolean) (js.executeScript("return this.$ === undefined;"));
    if (needInjection) {
      URL u = Resources.getResource("jquery.js");
      try {
        script = Resources.toString(u, Charsets.UTF_8);
      } catch (IOException e) {
        e.printStackTrace();
      }
      js.executeScript(script);
    }
  }
}
