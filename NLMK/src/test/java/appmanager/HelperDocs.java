package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HelperDocs extends HelperBase{

  public HelperDocs(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void gotoNewPage() {
    click(By.cssSelector("span.icon.icon-toolbarAdd")); // не всегда работает, не всегда кликабелен, нужно что-то с ожиданиями придумать
  }

  public void confirmAddDoc() {
    clickSimple(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[1]/preceding::button[1]"));
  }
}
