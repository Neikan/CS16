package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class OpenWT extends TestBase {

  @Test
  public void testOpenWT () {
    app.session().loginAdmin();

    app.goTo().main();

    app.goTo().main();

    app.goTo().main();

    app.goTo().main();

    app.goTo().main();
  }
}
