package appmanager;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HelperBase {
  protected ApplicationManager app;
  protected WebDriver wd;
  //protected JavascriptExecutor js;

  public HelperBase(ApplicationManager app) throws MalformedURLException {
    this.app = app;
    this.wd = app.getDriver();
    //this.js = app.js;
  }

  protected void click(By locator) {
    WebDriverWait wait = new WebDriverWait(wd, 30);
    wait.until(elementToBeClickable(locator));
    wd.findElement(locator).click();
  }

  protected void clickSimple(By locator) {
    WebDriverWait wait = new WebDriverWait(wd, 30);
    wait.until(elementToBeClickable(locator));
    wd.findElement(locator).click();
  }

  protected void doubleClick(By locator) {
    Actions actions = new Actions(wd);
    WebElement elementLocator = wd.findElement(locator);
    actions.doubleClick(elementLocator).perform();
  }

  protected void rightClick(By locator) {
    Actions actions = new Actions(wd);
    WebElement elementLocator = wd.findElement(locator);
    actions.contextClick(elementLocator).perform();
  }

  protected void scroll(By locator) {
    WebElement element = wd.findElement(locator);
    Actions actions = new Actions(wd);
    actions.moveToElement(element);
    actions.perform();
  }

  protected void sendKey(By locator, Keys keys) {
    wd.findElement(locator).sendKeys(keys);
  }

  protected void clickWait(By locator) {
    //WebElement explicitWait = (new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(locator));
    (new WebDriverWait(wd, 30)).until(ExpectedConditions.presenceOfElementLocated(locator)).click();
    //wd.findElement(locator).click();
  }

  protected void clickJS(By locator) {
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
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void typeWait(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file != null) {
      wd.findElement(locator).sendKeys(file.getAbsolutePath());
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
