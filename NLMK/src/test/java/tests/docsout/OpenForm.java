package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

import java.net.MalformedURLException;

public class OpenForm extends TestBase {

  @Test
  public void testOpenForm() throws MalformedURLException {
    app.session().loginAuthor();
    app.goTo().docsOutbound();
    app.docsout().gotoNewPage();
    app.docsout().docDetails();
    app.docsout().fillDocDetails();

    app.docsout().docRoute();
    app.docsout().fillDocRoute();

    app.docsout().docAccounting();
    app.docsout().fillDocAccounting();
  }
}