package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperNavigation extends HelperBase{

  public HelperNavigation(WebDriver wd) {
    super(wd);
  }

  public void main() {
    wd.findElement(By.cssSelector("div.csui-icon-home")).click();
  }

  public void messagesIn() {
    wd.findElement(By.linkText("Внутренние письма")).click();
  }

  public void docsIn() {
    wd.findElement(By.linkText("Входящие документы")).click();
  }

  public void docsOut() {
    wd.findElement(By.linkText("Исходящие документы")).click();
  }

  public void errands() {
    wd.findElement(By.linkText("Поручения")).click();
  }

  public void docsRegulatory () {
    wd.findElement(By.linkText("Распорядительные документы")).click();
  }

}
