package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

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
    visibleOffAll(By.className("load-container binf-hidden"));
    click(By.linkText("Исходящие документы"));
  }

  public void errands() {
    click(By.linkText("Поручения"));
  }

  public void docsRegulatory() {
    click(By.linkText("Распорядительные документы"));
  }

}
