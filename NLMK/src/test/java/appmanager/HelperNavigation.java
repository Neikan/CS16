package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class HelperNavigation extends HelperBase{

  public HelperNavigation(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void main() {
    wd.findElement(By.cssSelector("div.csui-icon-home")).click();
  }

  public void messagesInternal() {
    wd.findElement(By.linkText("Внутренние письма")).click();
  }

  public void docsInbound() {
    wd.findElement(By.linkText("Входящие документы")).click();
  }

  public void docsOutbound() {
    wd.findElement(By.linkText("Исходящие документы")).click();
  }

  public void errands() {
    wd.findElement(By.linkText("Поручения")).click();
  }

  public void docsRegulatory () {
    wd.findElement(By.linkText("Распорядительные документы")).click();
  }

}
