package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) throws MalformedURLException {
    super(app);
  }


  public void gotoNewPage() {
    click(By.cssSelector("span.icon.icon-toolbarAdd"));
  }


}
