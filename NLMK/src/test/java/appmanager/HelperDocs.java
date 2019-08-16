package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void gotoNewPage() {
    //visibleOff(By.xpath("/html/body/div[3]/div[1]"));
    //visibleOff(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div/div[3]"));
    //visibleOff(By.className("loader"));
    visibleOffAll(By.className("load-container binf-hidden"));
    click(By.cssSelector("span.icon.icon-toolbarAdd"));
  }

  public void confirmAddDoc() {
    clickSimple(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[1]/preceding::button[1]"));
  }
}
