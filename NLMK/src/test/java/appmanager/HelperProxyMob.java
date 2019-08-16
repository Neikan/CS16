package appmanager;

import java.net.MalformedURLException;

public class HelperProxyMob extends HelperBase {

  public HelperProxyMob(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  // start the proxy
/*
  BrowserMobProxy proxy = new BrowserMobProxyServer();
    proxy.start(0);

  // get the Selenium proxy object
  Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

  // configure it as a desired capability
  DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

  // start the browser up
  WebDriver driver = new FirefoxDriver(capabilities);

  // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

  // create a new HAR with the label "yahoo.com"
    proxy.newHar("yahoo.com");

  // open yahoo.com
    driver.get("http://yahoo.com");

  // get the HAR data
  Har har = proxy.getHar();
*/
}
