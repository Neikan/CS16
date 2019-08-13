package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    wd.findElement(By.linkText("Исходящие документы")).click();  // не всегда работает, не всегда кликабелен, нужно что-то с ожиданиями придумать
    //(new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Исходящие документы"))).click(); // - не помогло
  }

  public void errands() {
    wd.findElement(By.linkText("Поручения")).click();
  }

  public void docsRegulatory () {
    wd.findElement(By.linkText("Распорядительные документы")).click();
  }

}
