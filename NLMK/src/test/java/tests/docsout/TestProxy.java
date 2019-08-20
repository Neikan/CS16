package tests.docsout;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TestProxy {

  @Test
  public static void main() throws IOException {
    // TODO Auto-generated method stub

    BrowserMobProxyServer proxyServer = new BrowserMobProxyServer();
    proxyServer.start(0);
    System.out.println("Port Started On: " + proxyServer.getPort());
    //proxyServer.newHar("11"); тут работает
    proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT, CaptureType.RESPONSE_HEADERS);
    //proxyServer.newHar("11"); тут работает
    WebDriver wd = getDriver(proxyServer);
    //proxyServer.newHar("11"); тут работает

    wd.get("http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app");
    wd.quit();

    proxyServer.stop();

    String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
    String time = timeRaw.replace( ":", "-" ).replace( " ", "T" );
    proxyServer.getHar().writeTo(new File("results\\Working " + time + ".json"));

    System.out.println("Loaded browser ");
  }

  public static WebDriver getDriver(BrowserMobProxyServer proxyServer) throws IOException {
    //proxyServer.newHar("11"); работает
    Proxy proxy = ClientUtil.createSeleniumProxy(proxyServer);
    proxy.setHttpProxy("localhost:" + proxyServer.getPort());
    proxy.setSslProxy("localhost:" + proxyServer.getPort());

    DesiredCapabilities capabilities = new DesiredCapabilities();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--ignore-certificate-errors");

    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    capabilities.setCapability(CapabilityType.PROXY, proxy);
    WebDriver wd = new ChromeDriver(capabilities);
    proxyServer.newHar("11");

    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    //Не работает!!!
    //String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
    //String time = timeRaw.replace( ":", "-" ).replace( " ", "T" );
    //proxyServer.getHar().writeTo(new File("results\\Working " + time + ".json"));

    return wd;
  }
}
