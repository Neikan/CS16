package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class OpenWT extends TestBase {

  @Test
  public void testOpenWT () {
    app.session().loginAdmin();
    app.goTo().messagesInternal();
    app.goTo().main();
    app.goTo().docsInbound();
    app.goTo().main();
    app.goTo().docsOutbound();
    app.goTo().main();
    app.goTo().errands();
    app.goTo().main();
    app.goTo().docsRegulatory();
    app.goTo().main();
  }
}
