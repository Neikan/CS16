package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class OpenWT extends TestBase {

  @Test
  public void testOpenWT () throws InterruptedException {

    Thread.sleep(30000);

    app.goTo().messagesIn();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

    app.goTo().docsIn();
    Thread.sleep(15000);
    app.goTo().main();
    Thread.sleep(15000);

    app.goTo().docsOut();
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
