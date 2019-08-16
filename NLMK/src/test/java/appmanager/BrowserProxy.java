package appmanager;


import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserProxy {
/*  private BrowserMobProxy proxyServer;
  private boolean isServerStarted;

  public void startServer(DesiredCapabilities capabilities) {
    proxyServer = new BrowserMobProxyServer(0);
    try {
      proxyServer.start();
      isServerStarted = true;
    } catch (Exception e) {
      throw new RuntimeException("Cant start proxy-server on port: " + proxyServer.getPort(), e);
    }

    Proxy proxy = null;
    try {
      proxy = createHttpProxy(proxyServer.getPort());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    createNewHar();
    // configure it as a desired capability
    capabilities.setCapability(CapabilityType.PROXY, proxy);
  }

  private Proxy createHttpProxy(int port) throws UnknownHostException {
    Proxy proxy = new Proxy();
    proxy.setProxyType(Proxy.ProxyType.MANUAL);
    String proxyStr = String.format("%s:%d", InetAddress.getLocalHost().getCanonicalHostName(),  port);
    proxy.setHttpProxy(proxyStr);
    proxy.setSslProxy(proxyStr);
    return proxy;
  }

  public void createNewHar(){
    proxyServer.newHar();
  }

  public void stopServer() {
    if (isServerStarted) {
      try {
        proxyServer.stop();
      } catch (Exception e) {
        throw new RuntimeException("Cant stop proxy server", e);
      }

    }
  }


  public int getCallsCountByContainsKey(String key, boolean clearHistory) {
    int result = 0;
    Har har = proxyServer.getHar();
    if (har != null) {
      HarLog harLog = har.getLog();
      List<HarEntry> entries = harLog.getEntries();
      for (HarEntry entry : entries) {
        if (entry.getRequest().getUrl().contains(key)) {
          result++;
        }

      }

    } else {
      return -1;
    }
    if(clearHistory) {
      createNewHar();
    }
    return result;
  }

  public Har getHar() {
    return proxyServer.getHar();
  }*/
}
