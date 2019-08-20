package appmanager;

public class HelperBrowserProxy extends HelperBase {


  public HelperBrowserProxy(ApplicationManager app) {
    super(app);
  }

 // private BrowserMobProxyServer proxyServer;
  //private boolean isServerStarted;

 /* public void startServer() {
    proxyServer = new BrowserMobProxyServer();
    proxyServer.setTrustAllServers(true);
    proxyServer.setMitmManager(ImpersonatingMitmManager.builder().trustAllServers(true).build());
    try {
      proxyServer.start();
      isServerStarted = true;
    } catch (Exception e) {
      throw new RuntimeException("Cant start proxy-server on port: " + proxyServer.getPort(), e);
    }
  }*/

 /* public Proxy launchProxy() {
    Proxy proxy = null;
    try {
      createHttpProxy(proxyServer.getPort());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    //createNewHar();
    // configure it as a desired capability
    //capabilities.setCapability(CapabilityType.PROXY, proxy);
    return proxy;
  }*/

  /*private Proxy createHttpProxy(int port) throws UnknownHostException {
    Proxy proxy = ClientUtil.createSeleniumProxy(proxyServer);
    proxy.setProxyType(Proxy.ProxyType.MANUAL);
    String proxyStr = String.format("%s:%d", InetAddress.getLocalHost().getCanonicalHostName(), port);
    proxy.setHttpProxy(proxyStr);
    proxy.setSslProxy(proxyStr);
    return proxy;
  }*/

/*  public void createNewHar() {
    proxyServer.newHar();
  }

  public Har getHar() {
    return proxyServer.getHar();
  }*/

 /* public void writeToFile() {
    String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
    String time = timeRaw.replace( ":", "-" ).replace( " ", "T" );
    try {
      proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
      proxyServer.newHar("google.ru");
      getHar().writeTo(new File("results\\Test " + time + ".har"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }*/
/*
  public void stopServer() {
    if (isServerStarted) {
      try {
        proxyServer.stop();
      } catch (Exception e) {
        throw new RuntimeException("Cant stop proxy server", e);
      }
    }
  }*/

}
