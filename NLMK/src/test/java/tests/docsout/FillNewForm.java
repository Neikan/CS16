package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class FillNewForm extends TestBase {

  @Test
  public void fillNewForm() {
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
    app.docsout().initModification();
    app.docsout().attachFile();
    app.docsout().startWorkflow();
    System.out.println("Stop 3");
  }
}
