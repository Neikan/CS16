package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

import java.net.MalformedURLException;

public class GetLocators extends TestBase {

  @Test
  public void testGetLocators() throws MalformedURLException, InterruptedException {
    app.session().loginAuthor();
    app.goTo().docsOutbound();
    app.docsout().gotoNewPage();
    app.docsout().docDetails();
    app.docsout().docLocator();
  }
}
