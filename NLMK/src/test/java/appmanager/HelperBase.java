package appmanager;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;
  protected BrowserMobProxyServer proxyServer;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.proxyServer = app.getProxy();
    this.wd = app.getDriver(proxyServer);
  }

  protected void click(By locator) {
    WebDriverWait wait = new WebDriverWait(wd, 30);
    wait.until(elementToBeClickable(locator)).click();
  }

  protected void clickSimple(By locator) {
    wd.findElement(locator).click();
  }

  protected void doubleClick(By locator) {
    Actions actions = new Actions(wd);
    actions.doubleClick(wd.findElement(locator)).perform();
  }

  protected void rightClick(By locator) {
    Actions actions = new Actions(wd);
    actions.contextClick(wd.findElement(locator)).perform();
  }

  protected void searchElem(String titleElem, By locator) {
    if (wd.getPageSource().contains(titleElem)) {
      wd.findElement(locator);
    }
  }

  protected String searchE(By locator, String title) {
    String str = null;
    List<WebElement> elements = wd.findElements(locator);
    for (WebElement elem : elements) {
      if (elem.getAttribute("title").equals(title)) {
        str = elem.getAttribute("for");
        System.out.println(elem.getAttribute("title"));
        System.out.println(elem.getAttribute("for"));
      }
    }
    return str;
  }

  protected void searchN(By locator, String title) {
    WebElement webElement = wd.findElement(By.id("csui-dmbooleanfield-" + searchE(locator, title)));
    //webElement.findElement("")
  }

  protected void clickSwitch(String title) {
    wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + title + "']"))
            .findElement(By.xpath("//div[@id='csui-dmbooleanfield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + title + "']"))
                    .getAttribute("for") + "']"))
            .findElement(By.className("binf-switch-container"))
            .click();
  }

      /*
    WebElement modal = wd.findElement(By.xpath("//div[@class='binf-modal-body']"));
    WebElement elemFirst = modal.findElement(By.xpath(".//label[@title='" + title + "']"));
    String id = modal.findElement(By.xpath(".//label[@title='" + title + "']")).getAttribute("for");
    //String id = elemFirst.getAttribute("for");
    WebElement elemSecond  = elemFirst.findElement(By.xpath("//div[@id='csui-dmbooleanfield-" + id +"']"));
    WebElement elemThird = elemSecond.findElement(By.className("binf-switch-container"));
    elemThird.click();*/

  protected void scroll(By locator) {
    Actions actions = new Actions(wd);
    actions.moveToElement(wd.findElement(locator));
    actions.perform();
  }

  protected void sendKey(By locator, Keys keys) {
    wd.findElement(locator).sendKeys(keys);
  }

  protected boolean visibility(By locator) { // Не используется в данный момент
    try {
      WebDriverWait wait = new WebDriverWait(wd, 60);
      wait.until(invisibilityOfElementLocated(locator));
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

  protected void visibleOff(By locator) { // Не используется в данный момент
    WebDriverWait wait = new WebDriverWait(wd, 30);
    wait.until(invisibilityOfElementLocated(locator));
  }

  protected void visibleOffAll(By locator) {
    List<WebElement> elements = wd.findElements(locator);
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(invisibilityOfAllElements(elements));
  }

  protected void visibleOnAll(By locator) {
    List<WebElement> elements = wd.findElements(locator);
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(visibilityOfAllElements(elements));
  }

  protected void clickWait(By locator) { // Не используется в данный момент
    (new WebDriverWait(wd, 30)).until(ExpectedConditions.presenceOfElementLocated(locator)).click();

  }

  protected void clickJS(By locator) { // Не используется в данный момент
    //js.executeScript( locator + ".click();");
    //js.executeScript( locator + ".click();");
    //js.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", we);
    //js.executeScript("var textArea.innerHTML; getTextAreaContent(cont); setTimeout(function() {elem.click();}, 100)");
  }

  protected void getLoc(By locator) {
    wd.findElement(locator).getAttribute("title");
    System.out.println(wd.findElement(locator).getAttribute("title"));
    System.out.println(wd.findElement(locator).getCssValue("title"));
  }

  protected void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  protected void typeWait(By locator, String text) { // Не используется в данный момент
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  protected String getIdDoc() { // Возможно переделаем на получение ID - return id;
    String id = null;
    String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
    String time = timeRaw.replace(":", "-").replace(" ", "T");
    List<HarEntry> entries = proxyServer.getHar().getLog().getEntries();
    for (HarEntry entry : entries) {
      if (entry.getRequest() != null && entry.getRequest().getUrl().contains("http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/api/v1/nodes")) {
        id = entry.getResponse().getContent().getText().replaceAll("\\D+", "");
        try (FileOutputStream fos = new FileOutputStream(new File("results\\Test " + time + ".json"))) {
          byte[] buffer = id.getBytes();
          fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
    return id;
  }

  protected void attach(By locator, File file) {
    try {
      if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
    } catch (Exception e) {
      System.out.println("can't upload the file " + e);
    }
  }

  protected void waitDoc() {
    try {
      wd.manage().wait(30);
    } catch (InterruptedException | IllegalMonitorStateException e) {
      e.printStackTrace();
    }

  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
