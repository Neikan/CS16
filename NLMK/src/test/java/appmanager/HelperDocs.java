package appmanager;

import org.openqa.selenium.By;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) {
    super(app);
  }

  public void gotoNewPage() {
    visibleOffAll(By.className("load-container binf-hidden"));
    click(By.cssSelector("span.icon.icon-toolbarAdd"));
  }

  public void confirmAddDoc() {
    clickSimple(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[1]/preceding::button[1]"));
    writeHar();
  }
}
