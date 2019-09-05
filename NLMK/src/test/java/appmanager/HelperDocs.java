package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) {
    super(app);
  }

  public void gotoNewPage() {
    invisibleAll(By.className("load-container binf-hidden"), 1);
    click(By.xpath("//div[@class='csui-addToolbar']/.//span[@class='icon icon-toolbarAdd']"));
  }

  public void confirmAddDoc() {
    clickButtonFooter("Добавить");
    waitElem(30);
  }

  public void gotoDocPage(String idDoc) {
    wd.get("http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app/nodes/" + idDoc);
  }

  public void switchTabDoc(String titleTab) {
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath("//a[contains(.,'" + titleTab + "')]"));
    scroll(webElement);
    click(webElement);
  }

  public void switchTabLink(String titleTab) {
    WebElement webElement = wd.findElement(By.xpath("//div[@class='tab-links-bar']"))
            .findElement(By.xpath("//a[contains(.,'" + titleTab + "')]"));
    scroll(webElement);
    click(webElement);
  }

  public void startWorkflow() {
    clickButtonTabContent("Запустить маршрут");
    invisibleWidgetLoader("workitem");
    clickButtonFooter("Начать");
    type(By.xpath("//textarea[@class='comment-input csui-acc-focusable-active']"), "Запуск! 3... 2... 1...");
    clickButtonFooter("Отправить");
    invisibleWidgetLoader("attributes");
  }
}
