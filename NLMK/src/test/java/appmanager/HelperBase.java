package appmanager;

import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
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

  protected void writeHar() {
    try {
      String timeRaw = String.valueOf(new Timestamp(System.currentTimeMillis()));
      String time = timeRaw.replace(":", "-").replace(" ", "T");
      proxyServer.getHar().writeTo(new File("results\\Test " + time + ".json"));
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  protected void waitDoc() throws InterruptedException {
    wd.manage().wait(10);
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
