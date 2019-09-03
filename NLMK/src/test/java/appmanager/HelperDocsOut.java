package appmanager;

import model.DocOutboundData;
import model.DocsOutbound;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class HelperDocsOut extends HelperDocs {
  public HelperDocsOut(ApplicationManager app) {
    super(app);
  }

  public void docDetails() {
    scroll(By.linkText("1. Реквизиты документа"));
    click(By.linkText("1. Реквизиты документа"));
  }

  public void docRoute() {
    scroll(By.linkText("2. Маршрут документа"));
    click(By.linkText("2. Маршрут документа"));
  }

  public void docAccounting() {
    scroll(By.linkText("3. Учет и хранение"));
    click(By.linkText("3. Учет и хранение"));
  }

  public void docLocator() {
    getLoc(By.xpath("(//input[@type='search'])[13]"));
  }

  public void fillDocDetails() {
    visibleOffAll(By.className("loader"));

    type(fieldLookupNSIFor("Вид документа"), "Письмо"); // Вид документа
    autoComplete("Вид документа", "Письмо");

    type(fieldTextArea("Заголовок к тексту"), String.valueOf(new Timestamp(System.currentTimeMillis()))); // Заголовок к тексту

    type(fieldLookupNSIId("Ограничение доступа"), "КТ"); // Ограничение доступа
    autoComplete("Ограничение доступа", "КТ");

    type(fieldLookupUserId("Подписант"), "Рабовский"); // Подписант
    autoCompleteUser("Подписант", "Рабовский");

    type(fieldLookupNSIFor("Организация внешнего адресата"), "ООО \"Матрёшка\""); // Внешний адресат
    autoComplete("Организация внешнего адресата", "ООО \"Матрёшка\"");

    type(fieldLookupNSIFor("Способ отправки внешнему адресату"), "Электро"); //Способ отправки внешнему адресату
    autoComplete("Способ отправки внешнему адресату", "Электро");

    scroll(fieldLookupUserFor("Адресат внутренний")); // Внутренний адресат
    type(fieldLookupUserFor("Адресат внутренний"), "Кутузов");
    autoCompleteUser("Адресат внутренний", "Кутузов");
  }

  public void fillDocRoute() {
    click(fieldSwitch("Проверка оформления")); //Флаг "Проверка оформления"
    click(fieldSwitch("Согласование руководителем инициатора")); // Флаг "Согласование руководителем инициатора"
    click(fieldSwitch("Согласование юристами")); // Флаг "Согласование юристами"

    // Нормоконтролер - не заполняем пока что

    // Согласующие
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::span[2]"));

    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), "Мягков");
    sendKey(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), Keys.ENTER);
    click(By.xpath("//div[2]/table/tbody/tr/td/input"));

    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), "Сыромятников");
    sendKey(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), Keys.ENTER);
    click(By.xpath("//div[2]/table/tbody/tr/td/input"));

    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), "Гахова");
    sendKey(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), Keys.ENTER);
    click(By.xpath("//div[2]/table/tbody/tr/td/input"));

    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Закрыть'])[1]/preceding::button[1]"));

    // Отправитель
    // Способ 1
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отправитель'])[2]/following::span[2]"));
    scroll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), "Рокоссовский");
    sendKey(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), Keys.ENTER);
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::td[15]"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Закрыть'])[1]/preceding::button[1]"));
    // Способ 2
    //typeLookupWithForforUser("Отправитель", "Рокоссовский"); - допилить выбор пользователя

    // Приоритет - нельзя очистить поле, нужно через справочник выбирать только
    //clearLookupWithFor("Приоритет");
    //typeLookupWithFor("Приоритет", "Высокий");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::span[2]"));
    doubleClick(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Обычный'])[2]/following::td[2]"));

    // Обоснование приоритета
    scroll(fieldTextArea("Обоснование приоритета"));
    type(fieldTextArea("Обоснование приоритета"), "Казнить, нельзя помиловать!");
  }

  public void fillDocAccounting() {
    type(fieldInteger("Количество листов документа"),  "10"); //Количество листов документа
    type(fieldInteger("Количество листов приложений"), "20"); //Количество листов приложений

    type(fieldLookupNSIFor("Вид носителя"), "Бумажный"); //Вид носителя
    autoComplete("Вид носителя", "Бумажный");

    type(fieldLookupNSIId("Рубрика"), "Рубрика а"); //Рубрика
    autoComplete("Вид носителя", "Рубрика а");

    type(fieldText("Номер почтового отправления"), "300"); //Номер почтового отправления
    type(fieldText("В ответ на (номер)"), "200"); //В ответ на (номер)

    //В ответ на (дата)
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='В ответ на (дата)'])[2]/following::span[2]"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс'])[1]/following::td[8]"));

    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='В ответ на (дата)'])[2]/following::button[2]"));

  }

  private DocsOutbound docsOutboundCashe = null;

  public DocsOutbound all() {
    if (docsOutboundCashe != null) {
      return new DocsOutbound(docsOutboundCashe);
    }

    docsOutboundCashe = new DocsOutbound();
    List<WebElement> rows = wd.findElements(By.partialLinkText(" - ИСХ - ")); // Если эта часть статична, то ок
    //List<WebElement> rows = wd.findElements(By.partialLinkText("temp document name"));
    for (WebElement row : rows) {
      String nameDoc = row.getText();
      String linkDoc = row.getAttribute("href");
      docsOutboundCashe.add(new DocOutboundData().withNameDoc(nameDoc).withLinkDoc(linkDoc));
    }
      return new DocsOutbound(docsOutboundCashe);
  }

  public void initDocOutboundModification(int id) {
    click(By.xpath("//a[@href='http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app/nodes/"+ id +"']"));
  }

  public void initModification() {
    visibleOffAll(By.className("load-container binf-hidden")); // Тут нужен какой-то таймаут
    openCard(getIdDoc());
    //getIdDoc();
    //DocOutboundData doc = all().iterator().next();
    //System.out.println(doc.getNameDoc());
    //System.out.println(doc.getLinkDoc());
  }

  public void attachFile() {
    File fileXLS = new File("src/test/resources/Attachments/Excel XLS.xls");
    //DocOutboundData docOut = new DocOutboundData().withFile(fileXLS);

    File fileXLSX = new File("src/test/resources/Attachments/Excel XLSX.xlsx");
    File filePDF = new File("src/test/resources/Attachments/PDF 1.pdf");
    File fileDOC = new File("src/test/resources/Attachments/Служебная записка 1.doc");
    File fileDOCX = new File("src/test/resources/Attachments/Тестовый документ №1.docx");

    visibleOffAll(By.className("load-container binf-hidden"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Реквизиты'])[1]/following::span[1]"));
    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor)wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  " );
    click(By.linkText("Документ"));
    //attach(By.xpath("//input[@type='file']"), docOut.getFile());
    attach(By.xpath("//input[@type='file']"), fileXLS);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor)wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  " );
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), fileXLSX);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor)wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  " );
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), filePDF);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor)wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  " );
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), fileDOC);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor)wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  " );
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), fileDOCX);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));
  }

  public void openRandomCard() {
    wd.get("http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app/nodes/3651853");
  }

  public void openCard(String idDoc) {
    wd.get("http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app/nodes/" + idDoc);
  }

  public void startWorkflow() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Создать поручение'])[2]/preceding::button[1]"));

    visibleOffAll(By.className("load-container binf-hidden"));
    click(By.cssSelector("button.binf-btn.binf-btn-primary"));
    visibleOffAll(By.className("load-container binf-hidden"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отправить'])[1]/preceding::textarea[1]"), "Запуск! 3... 2... 1...");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отмена'])[2]/preceding::button[1]"));
    visibleOffAll(By.className("load-container binf-hidden"));
  }
}
