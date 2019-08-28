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
    System.out.println("Stop 1");
    app.docsout().initModification();
    System.out.println("Stop 2");
    app.docs().writesHar();
    //app.docsout().attachFile();
    //app.docsout().startWorkflow();
    System.out.println("Stop 3");
  }
}
