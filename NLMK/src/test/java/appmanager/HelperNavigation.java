package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class HelperNavigation extends HelperBase {

  public HelperNavigation(ApplicationManager app) throws MalformedURLException {
    super(app);
  }

  public void main() {
    click(By.cssSelector("div.csui-icon-home"));
  }

  public void messagesInternal() {
    click(By.linkText("Внутренние письма"));
  }

  public void docsInbound() {
    click(By.linkText("Входящие документы"));
  }

  public void docsOutbound() {
    click(By.linkText("Исходящие документы"));
    //(new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Исходящие документы"))).click(); // - не помогло
  }

  public void errands() {
    click(By.linkText("Поручения"));
  }

  public void docsRegulatory () {
    click(By.linkText("Распорядительные документы"));
  }

}
