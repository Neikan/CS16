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
    return wd.findElement(By.xpath("//div[@id='csui-dmbooleanfield-"
            + getAttribute(titleField, "for") + "']"))
            .findElement(By.className("binf-switch-container"));
  }

  protected WebElement fieldText(String titleField) { // Однострочный текст
    return wd.findElement(By.xpath("//input[@id='csui-dmtextfield-"
            + getAttribute(titleField, "for") + "']"));
  }

  protected WebElement fieldTextArea(String titleField) { // Многострочный текст
    return wd.findElement(By.xpath("//textarea[@id='csui-dmtextareafield-"
            + getAttribute(titleField, "for") + "']"));
  }

  protected WebElement fieldInteger(String titleField) { // Числовое поле
    return wd.findElement(By.xpath("//input[@id='csui-integerfield-"
            + getAttribute(titleField, "for") + "']"));
  }

  protected WebElement fieldLookupNSIFor(String titleField) { // Поле выбора данных из справочника по alpacaFOR
    return wd.findElement(By.xpath("//div[@id='csui-dmreffield-"
            + getAttribute(titleField, "for") + "']"))
            .findElement(By.xpath(".//input[@type='search']"));
  }

  protected WebElement fieldLookupNSIId(String titleField) { // Поле выбора данных из справочника по alpacaID
    return wd.findElement(By.xpath("//input[@type='search' and @aria-labelledby='"
            + getAttribute(titleField, "id") + "']"));
  }

  protected WebElement fieldDateTex(String titleField) { // Поле даты как текста по alpacaFOR
    return wd.findElement(By.xpath("//div[@id='csui-dmdatefield-"
            + getAttribute(titleField, "for") + "']"))
            .findElement(By.xpath(".//input[@type='text']"));
  }

  protected WebElement fieldDateCalendarFor(String titleField) { // Поле даты из календаря по alpacaFOR - не работает!
    return wd.findElement(By.xpath("//div[@id='csui-dmdatefield-"
            + getAttribute(titleField, "for")
            + "']/.//span[@class='icon-date_picker ']"));
  }

  protected WebElement fieldLookupUserId(String titleField) { // Поле выбора пользователя из справочника по alpacaID
    return wd.findElement(By.xpath("//input[@aria-label='" + titleField + "' and @aria-labelledby='"
            + getAttribute(titleField, "id") + "']"));
  }

  protected WebElement fieldLookupUserFor(String titleField) { // Поле выбора пользователя из справочника по alpacaFOR
    return wd.findElement(By.xpath("//input[@type='text' and @id='"
            + getAttribute(titleField, "for") + "']"));
  }

  // Методы получения атрибутов элементов
  // - Метод получения заданного атрибута по названию поля
  protected String getAttribute(String titleField, String attribute) { // attribute - html-тег атрибута
    return wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']")).getAttribute(attribute);
  }

  // Методы для нажатия на элемент
  // - Методы ЛКМ
  // -- Одинарный
  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void click(By locator, int waitTime) {
    /*WebDriverWait wait = new WebDriverWait(wd, waitTime);
    wait.until(elementToBeClickable(locator)).click();*/
    WebDriverWait wait = new WebDriverWait(wd, waitTime);
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
  }

  protected void click(WebElement webElement) {
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
  }

  protected void click(WebElement webElement, boolean isFill) {
    if (isFill) {
      WebDriverWait wait = new WebDriverWait(wd, 5);
      wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }
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

  protected void typeLookupSeveralUser(String value, String numberColumn) {
    type(filterLookupTable(numberColumn), value);
    sendKey(filterLookupTable(numberColumn), Keys.ENTER);
    clickCheckBoxLookupTable();
  }

  protected void typeLookupSeveralUser(boolean isFill, String value, String numberColumn) {
    if (isFill) {
      type(filterLookupTable(numberColumn), value);
      sendKey(filterLookupTable(numberColumn), Keys.ENTER);
      clickCheckBoxLookupTable();
    }
  }

  protected void typeLookupForeverAloneUser(String value, String numberColumn) {
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

  // Методы ожидания визуального исчезновения элементов
  protected boolean visibility(By locator) { // Не используется в данный момент
    try {
      WebDriverWait wait = new WebDriverWait(wd, 60);
      wait.until(invisibilityOfElementLocated(locator));
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

  protected void invisible(By locator) {
    WebDriverWait wait = new WebDriverWait(wd, 5);
    wait.until(invisibilityOfElementLocated(locator));
  }

  protected void invisibleWidgetLoader(String nameWidget) {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(invisibilityOfElementLocated(By.xpath("//div[@data-csui-widget_type='" + nameWidget + "']/.//div[@class='load-container binf-hidden']")));
  }

  protected void invisibleAll(By locator, int waitTime) {
    List<WebElement> elements = wd.findElements(locator);
    WebDriverWait wait = new WebDriverWait(wd, waitTime);
    wait.until(invisibilityOfAllElements(elements));
  }

  protected void visible(By locator) {
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

  // Метод получения ID созданного нового документа
  protected String getIdDoc() { // Возможно переделаем на получение ID - return id для модели данных;
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

  // Методы прикрепления вложений
  protected void attach(By locator, File file) {
    try {
      if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
      }
    } catch (Exception e) {
      System.out.println("can't upload the file " + e);
    }
  }

  // Методы ожидания элементов
  protected void waitElem(int wait) {
    try {
      wd.manage().wait(wait);
    } catch (InterruptedException | IllegalMonitorStateException e) {
      e.printStackTrace();
    }
  }

  protected void waitAlertOff() {
    try {
      while (isElementPresent(By.xpath("//div[@class='cs-names-progress']"))) {
        wait(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // Методы выбора значений из "подсказок" (автокомлит)
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

  // Методы открытия справочников
  // - Метод открытия справочников с данными
  protected void openLookupNSI(String titleField) {
    click(wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']/..//span[@class='assignment-task edit-list-container']")));
  }

  // - Метод открытия справочников с пользователями и группами
  protected void openLookupUser(String titleField) {
    click(wd.findElement(By.xpath("//div[@class='binf-modal-body']"))
            .findElement(By.xpath(".//label[@title='" + titleField + "']/..//span[@class='dmemployee edit-list-container']")));
  }

  // Методы фильтрации справочников
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

  // Методы нажатия на кнопки
  // - Метод нажатия кнопок в footer'e
  protected void clickButtonFooter(String textButton) {
    click(wd.findElement(By.xpath("//div[contains(@class, 'binf-modal-footer')]/.//button[text()[(.='" + textButton + "')]]")));
  }

  // - Метод нажатия кнопок в tab-content'e
  protected void clickButtonTabContent(String textButton) {
    click(wd.findElement(By.xpath("//div[@class='binf-tab-pane binf-active']/.//button[text()[(.='" + textButton + "')]]")));
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

  // Методы прикрепления файлов
  protected void attachFile(File file) {
    click(By.xpath("//div[@class='csui-addToolbar']/.//span[@class='icon icon-toolbarAdd']"));
    ((JavascriptExecutor) wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  ");
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), file);
    waitAlertOff();
  }

  protected void attachFile(boolean isFill, File file) {
    if (isFill) {
      click(By.xpath("//div[@class='csui-addToolbar']/.//span[@class='icon icon-toolbarAdd']"));
      ((JavascriptExecutor) wd).executeScript(
              "HTMLInputElement.prototype.click = function() {                     " +
                      "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                      "};                                                                  ");
      click(By.linkText("Документ"));
      attach(By.xpath("//input[@type='file']"), file);
      waitAlertOff();
    }
  }

  // Методы проверки появления/наличия элементов

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
