package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class GetLocators extends TestBase {

  @Test
  public void testGetLocators() {
    app.session().loginAuthor();
    app.goTo().docsOutbound();
    app.docsout().gotoNewPage();
    app.docsout().docDetails();
    app.docsout().docLocator();
  }
}
