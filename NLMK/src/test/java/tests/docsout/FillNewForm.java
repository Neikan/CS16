package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class FillNewForm extends TestBase {

  @Test
  public void fillNewForm() {
    app.session().loginAuthor();
    app.goTo().favorites("Исходящие документы");
    app.docsout().gotoNewPage();
    app.docsout().fillDocDetails();
    app.docsout().fillDocRoute();
    app.docsout().fillDocAccounting();
    app.docsout().confirmAddDoc();
    app.docsout().initModification();
    app.docsout().attachFiles();
    app.docsout().startWorkflow();
    app.goTo().main();
  }
}
