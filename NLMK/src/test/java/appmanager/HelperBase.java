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

  // Методы определения типов полей

  protected WebElement fieldSwitch(String titleField) { // Флаг
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//div[@id='csui-dmbooleanfield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"))
            .findElement(By.className("binf-switch-container"));
    return webElement;
  }

  protected WebElement fieldText(String titleField) { // Однострочный текст
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//input[@id='csui-dmtextfield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"));
    return webElement;
  }

  protected WebElement fieldTextArea(String titleField) { // Многострочный текст
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//textarea[@id='csui-dmtextareafield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"));
    return webElement;
  }

  protected WebElement fieldInteger(String titleField) { // Числовое поле
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//input[@id='csui-integerfield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"));
    return webElement;
  }

  /*
  protected WebElement fieldLookupNSIFor(String titleField) { // Поле выбора данных из справочника по alpacaFOR
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//div[@id='csui-dmreffield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"))
            .findElement(By.xpath(".//input[@type='search']"));
    return webElement;
  } */

  protected WebElement fieldLookupNSIForExperimental(String titleField) { // Поле выбора данных из справочника по alpacaFOR
    return wd.findElement(By.xpath("//div[@id='csui-dmreffield-" + getAttribute(titleField, "for") + "']"))
            .findElement(By.xpath(".//input[@type='search']"));
  }

  protected WebElement fieldLookupNSIId(String titleField) { // Поле выбора данных из справочника по alpacaID
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//input[@type='search' and @aria-labelledby='"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("id") + "']"));
    return webElement;
  }

  protected WebElement fieldDateTextFor(String titleField) { // Поле даты как текста по alpacaFOR
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//div[@id='csui-dmdatefield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"))
            .findElement(By.xpath(".//input[@type='text']"));
    return webElement;
  }

  protected WebElement fieldDateCalendarFor(String titleField) { // Поле даты из календаря по alpacaFOR
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//div[@id='csui-dmdatefield-"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"))
            .findElement(By.xpath(".//span[@class='icon-date_picker ']"));
    return webElement;
  }

  protected WebElement fieldLookupUserId(String titleField) { // Поле выбора пользователя из справочника по alpacaID
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//input[@aria-label='" + titleField + "' and @aria-labelledby='"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("id") + "']"));
    return webElement;
  }

  protected WebElement fieldLookupUserFor(String titleField) { // Поле выбора пользователя из справочника по alpacaFOR
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"))
            .findElement(By.xpath("//input[@type='text' and @id='"
                    + wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
                    .findElement(By.xpath(".//label[@title='" + titleField + "']"))
                    .getAttribute("for") + "']"));
    return webElement;
  }

  // Методы получения атрибутов элементов
  // - Метод получения заданного атрибута по названию поля
  protected String getAttribute(String titleField, String attribute) { // attribute = {"for", "id"}
    return wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']")).getAttribute(attribute);
  }

  // Методы для нажатия на элемент
  // - Методы ЛКМ
  // -- Одинарный
  protected void click(By locator) {
    WebDriverWait wait = new WebDriverWait(wd, 30);
    wait.until(elementToBeClickable(locator)).click();
  }

  protected void click(WebElement webElement) {
    webElement.click();
  }

  // - Двойной
  protected void doubleClick(By locator) {
    Actions actions = new Actions(wd);
    actions.doubleClick(wd.findElement(locator)).perform();
  }

  protected void doubleClick(WebElement webElement) {
    Actions actions = new Actions(wd);
    actions.doubleClick(webElement).perform();
  }

  // - Методы ПКМ
  protected void rightClick(By locator) {
    Actions actions = new Actions(wd);
    actions.contextClick(wd.findElement(locator)).perform();
  }


  // Методы для заполнения полей

  protected void type(By locator, String value) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(value);
  }

  protected void type(WebElement webElement, String value) {
    click(webElement);
    webElement.clear();
    webElement.sendKeys(value);
  }

  protected void typeLookupSeveralUser(String value, String numberColumn ) {
    type(filterLookupTable(numberColumn), value);
    sendKey(filterLookupTable(numberColumn), Keys.ENTER);
    clickCheckBoxLookupTable();
  }

  protected void typeLookupForeverAloneUser(String value, String numberColumn ) {
    type(filterLookupTable(numberColumn), value);
    sendKey(filterLookupTable(numberColumn), Keys.ENTER);
    click(getCellLookupTable(value));
  }

  protected void typeFieldWithAutoComplete(WebElement webElement, String titleField, String value) {
    type(webElement, value);
    autoComplete(titleField, value);
  }

  protected void typeFieldWithAutoCompleteUser(WebElement webElement, String titleField, String value) {
    type(webElement, value);
    autoCompleteUser(titleField, value);
  }

  protected void typeFieldDate() {

  }

  // Методы очистки элементов
  protected void clear(WebElement webElement) {
    webElement.clear();
  }

  // Методы скроллинга к элементам
  protected void scroll(WebElement webElement) {
    Actions actions = new Actions(wd);
    actions.moveToElement(webElement).perform();
  }

  protected void scroll(By locator) {
    Actions actions = new Actions(wd);
    actions.moveToElement(wd.findElement(locator)).perform();
  }

  // Методы отправки нажатий клавиш на клавиатуре
  protected void sendKey(By locator, Keys keys) {
    wd.findElement(locator).sendKeys(keys);
  }

  protected void sendKey(WebElement webElement, Keys keys) {
    webElement.sendKeys(keys);
  }

  // Методы ожидания исчезновения элементов
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

  protected void waitElem(int wait) {
    try {
      wd.manage().wait(wait);
    } catch (InterruptedException | IllegalMonitorStateException e) {
      e.printStackTrace();
    }
  }

  protected void autoComplete(String titleField, String value) { // нужно переделать, т.к. сейчас title вообще не при делах
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"));
    try {
      while (!isElementPresent(webElement, By.xpath("//strong[text()[contains(.,'" + value + "')]]"))) {
        wait(5);
      }
      click(webElement.findElement(By.xpath("//strong[text()[contains(.,'" + value + "')]]")));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected void autoCompleteUser(String titleField, String value) {
    WebElement webElement = wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']"));
    try {
      while (!isElementPresent(webElement, By.xpath("//span[text()[contains(.,'" + value + "')]]"))) {
        wait(5);
      }
      click(webElement.findElement(By.xpath("//span[text()[contains(.,'" + value + "')]]")));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  protected void openLookupNSI(String titleField) {
    click(wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']/..//span[@class='assignment-task edit-list-container']")));
  }

  protected void openLookupUser(String titleField) {
    click(wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']/..//span[@class='dmemployee edit-list-container']")));
  }

  protected WebElement filterLookupTable(String numberColumn) {
    try {
      while (!isElementPresent(By.xpath("//tr[@class='jsgrid-filter-row']/.//td[" + numberColumn + "]/input"))) {
        wait(5);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return wd.findElement(By.xpath("//tr[@class='jsgrid-filter-row']/.//td[" + numberColumn + "]/input"));
  }

  protected WebElement getCellLookupTable(String value) {
    return wd.findElement(By.xpath("//table[@class='jsgrid-table']/.//td[text()[(.='" + value + "')]]"));
  }

  protected void clickCheckBoxLookupTable() {
    try {
      while (!isElementPresent(By.xpath("//tr[@class='jsgrid-row']/.//td/input[@type='checkbox']"))) {
        wait(5);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    click(By.xpath("//tr[@class='jsgrid-row']/.//td/input[@type='checkbox']"));
  }

  protected void clickButtonFooter(String textButton) {
    click(wd.findElement(By.xpath("//div[@class='binf-modal-footer']/.//button[text()[(.='" + textButton + "')]]")));
  }

  protected void buttonCancelNewDoc() {
    wd.findElement(By.xpath("//button[@class='binf-btn binf-btn-default csui-acc-tab-region csui-acc-focusable-active']"));
  }

  protected WebElement button(String tabIndex, String textButton) {
    WebElement webElement = null;
    List<WebElement> elements = wd.findElements(By.xpath("//button[@tabindex='" + tabIndex + "']"));
    for (WebElement elem : elements) {
      if (elem.getText().equals(textButton)) {
        webElement = elem;
        break;
      }
    }
    return webElement;
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isElementPresent(WebElement webElement, By locator) {
    try {
      webElement.findElement(locator);
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
