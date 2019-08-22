package tests.docsout;

import org.testng.annotations.Test;
import tests.TestBase;

public class RandomTest extends TestBase {

  @Test
  public static void randomTest() throws Exception {
    app.session().loginAuthor();
    app.docsout().openRandomCard();
    //app.docsout().attachFile();
    System.out.println("");
    app.docsout().startWorkflow();
  }
}