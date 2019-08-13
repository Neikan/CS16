package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void gotoNewPage() {
    //click(By.className("icon icon-toolbarAdd")); - не работает
    click(By.cssSelector("span.icon.icon-toolbarAdd")); // не всегда работает, не всегда кликабелен, нужно что-то с ожиданиями придумать
    //click(By.xpath("//div[2]/ul/li/a/span")); - не работает
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Документы ПАО «НЛМК»'])[1]/following::span[2]")); - не работает
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Исходящие документы'])[2]/following::span[2]")); - не работает

  }

  public void confirmAddDoc() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[1]/preceding::button[1]"));
  }
}
