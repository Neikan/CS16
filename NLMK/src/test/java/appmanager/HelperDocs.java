package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HelperDocs extends HelperBase {

  public HelperDocs(ApplicationManager app) {
    super(app);
  }

  public void gotoNewPage() {
    invisibleAll(By.className("load-container binf-hidden"), 5);
    click(By.xpath("//div[@class='csui-addToolbar']/.//span[@class='icon icon-toolbarAdd']"));
  }

  public void confirmAddDoc() {
    clickButtonFooter("Добавить");
    waitElem(30);
  }

  public void gotoDocPage(String idDoc) {
    wd.get(app.getProperty("web.part.formUrl") + idDoc); // Нужно заменить на property
    //wd.get("http://ot-nlmk-be-dev1.ot.dev.local/OTCS/cs.exe/app/nodes/" + idDoc); // Нужно заменить на property
  }

  public void gotoRandomDoc() {
    wd.get(app.getProperty("web.part.formUrl") + "3750571"); // Тестовое открытие произвольной карточки
    invisibleAll(By.className("load-container binf-hidden"), 5); // Тут нужен какой-то таймаут
    System.out.println("ЫЫЫ3: " + wd.findElement(By.xpath("//div[@class='cs-header']/.//h2[@class='csui-item-name-block']")).getText());
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
    //invisibleWidgetLoader("workitem");
    invisibleAll(By.className("load-container binf-hidden"), 10);
    clickButtonFooter("Начать");
    type(By.xpath("//textarea[@class='comment-input csui-acc-focusable-active']"), "Запуск! 3... 2... 1...");
    clickButtonFooter("Отправить");
    invisibleWidgetLoader("attributes");
    //invisibleAll(By.className("load-container binf-hidden"), 10);
  }

}
