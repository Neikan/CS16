package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

import java.net.MalformedURLException;

public class FillNewForm extends TestBase {

  @Test
  public void fillNewForm() throws MalformedURLException {
    app.session().loginAuthor();
    app.goTo().docsOutbound();
    app.docsout().gotoNewPage();
    app.docsout().docDetails();
    app.docsout().fillDocDetails();
    app.docsout().docRoute();
    app.docsout().fillDocRoute();
    app.docsout().docAccounting();
    app.docsout().fillDocAccounting();
    app.docsout().confirmAddDoc();
  }
}