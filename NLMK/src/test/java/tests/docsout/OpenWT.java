package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

import java.net.MalformedURLException;

public class OpenWT extends TestBase {

  @Test
  public void testOpenWT () throws InterruptedException, MalformedURLException {

    Thread.sleep(30000);

    app.session().loginAdmin();

    Thread.sleep(30000);

    app.goTo().messagesInternal();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

    app.goTo().docsInbound();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

    app.goTo().docsOutbound();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

    app.goTo().errands();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

    app.goTo().docsRegulatory();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

  }
}
