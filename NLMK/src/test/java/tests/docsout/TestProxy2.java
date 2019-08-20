package tests.docsout;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TestProxy2 {

  @Test
  public static void main() throws Exception {
    // TODO Auto-generated method stub

    BrowserMobProxyServer browserMobProxy = new BrowserMobProxyServer();
    browserMobProxy.setTrustAllServers(true);
    browserMobProxy.setMitmManager(ImpersonatingMitmManager.builder().trustAllServers(true).build());
    browserMobProxy.start(0);

    System.out.println("Port Started On: " + browserMobProxy.getPort());
    //System.setProperty("webdriver.chrome.driver", "/Users/pankaj.katiyar/Desktop/Automation/Lenskart_Automation/tpt/drivers/chromedriver");

    browserMobProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT, CaptureType.RESPONSE_HEADERS);

    WebDriver driver = getDriver_CapProxy(browserMobProxy);

    browserMobProxy.newHar("11");

    driver.get("http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app");
    driver.quit();

    browserMobProxy.stop();

    String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
    String time = timeRaw.replace( ":", "-" ).replace( " ", "T" );
    browserMobProxy.getHar().writeTo(new File("results\\Test 2 " + time + ".har"));

    System.out.println("Loaded browser ");
  }


  public static WebDriver getDriver_CapProxy(BrowserMobProxyServer browserMobProxy) throws UnknownHostException {
    Proxy proxy = ClientUtil.createSeleniumProxy(browserMobProxy);
    proxy.setHttpProxy("localhost:" + browserMobProxy.getPort());

    DesiredCapabilities cap = new DesiredCapabilities();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--ignore-certificate-errors");

    cap.setCapability(ChromeOptions.CAPABILITY, options);
    cap.setCapability(CapabilityType.PROXY, proxy);

    //WebDriver driver = new ChromeDriver(options);

    WebDriver driver = new ChromeDriver(cap);

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return driver;
  }
}
