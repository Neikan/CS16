package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void gotoNewPage() {
    if (isElementPresent(By.cssSelector("span.icon.icon-toolbarAdd"))) {
      return;
    }
    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    //(new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.icon.icon-toolbarAdd"))).click();

  }

  public void confirmAddDoc() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[1]/preceding::button[1]"));
  }
}
