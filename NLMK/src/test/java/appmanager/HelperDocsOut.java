package appmanager;

import model.DocOutboundData;
import model.DocsOutbound;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    visibleOffAll(By.className("loader")); // Это тоже переделать!
    typeFieldWithAutoComplete(fieldLookupNSIFor("Вид документа"),"Вид документа", "Письмо");
    type(fieldTextArea("Заголовок к тексту"), String.valueOf(new Timestamp(System.currentTimeMillis())));
    typeFieldWithAutoComplete(fieldLookupNSIId("Ограничение доступа"),"Ограничение доступа", "КТ");
    typeFieldWithAutoCompleteUser(fieldLookupUserId("Подписант"), "Подписант", "Рабовский");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Организация внешнего адресата"),"Организация внешнего адресата", "ООО \"Матрёшка\"");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Способ отправки внешнему адресату"),"Способ отправки внешнему адресату", "Электро");
    scroll(fieldLookupUserFor("Адресат внутренний"));
    typeFieldWithAutoCompleteUser(fieldLookupUserFor("Адресат внутренний"), "Адресат внутренний", "Кутузов");
  }

  public void fillDocRoute() {
    click(fieldSwitch("Проверка оформления"));
    click(fieldSwitch("Согласование руководителем инициатора"));
    click(fieldSwitch("Согласование юристами"));

    // Нормоконтролер - не заполняем пока что

    openLookupUser("Согласующие");
    typeLookupSeveralUser("Мягков", "3");
    typeLookupSeveralUser("Сыромятников", "3");
    typeLookupSeveralUser("Гахова", "3");
    clickButtonFooter("Сохранить");

    openLookupUser("Отправитель");
    typeLookupForeverAloneUser("Рокоссовский", "2");
    clickButtonFooter("Сохранить");

    openLookupNSI("Приоритет");
    doubleClick(getCellLookupTable("Высокий"));

    scroll(fieldTextArea("Обоснование приоритета"));
    type(fieldTextArea("Обоснование приоритета"), "Казнить, нельзя помиловать!");
  }

  public void fillDocAccounting() {
    type(fieldInteger("Количество листов документа"), "10");
    type(fieldInteger("Количество листов приложений"), "20");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Вид носителя"), "Вид носителя", "Бумажный");
    typeFieldWithAutoComplete(fieldLookupNSIId("Рубрика"), "Рубрика", "Рубрика а");
    type(fieldText("Номер почтового отправления"), "300");
    type(fieldText("В ответ на (номер)"), "200");
    type(fieldDateTextFor("В ответ на (дата)"), "03.09.2019");
  }

  private DocsOutbound docsOutboundCashe = null;

  public DocsOutbound all() {
    if (docsOutboundCashe != null) {
      return new DocsOutbound(docsOutboundCashe);
    }

    docsOutboundCashe = new DocsOutbound();
    List<WebElement> rows = wd.findElements(By.partialLinkText(" - ИСХ - ")); // Если эта часть статична, то ок
    for (WebElement row : rows) {
      String nameDoc = row.getText();
      String linkDoc = row.getAttribute("href");
      docsOutboundCashe.add(new DocOutboundData().withNameDoc(nameDoc).withLinkDoc(linkDoc));
    }
    return new DocsOutbound(docsOutboundCashe);
  }

  public void initDocOutboundModification(int id) {
    click(By.xpath("//a[@href='http://ot-nlmk-be-dev2.ot.dev.local/OTCS/cs.exe/app/nodes/" + id + "']"));
  }

  public void initModification() {
    visibleOffAll(By.className("load-container binf-hidden")); // Тут нужен какой-то таймаут
    openCard(getIdDoc());
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
    ((JavascriptExecutor) wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  ");
    click(By.linkText("Документ"));
    //attach(By.xpath("//input[@type='file']"), docOut.getFile());
    attach(By.xpath("//input[@type='file']"), fileXLS);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor) wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  ");
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), fileXLSX);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor) wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  ");
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), filePDF);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor) wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  ");
    click(By.linkText("Документ"));
    attach(By.xpath("//input[@type='file']"), fileDOC);
    visibleOffAll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ожидание'])[1]/preceding::div[6]"));
    visibleOffAll(By.className("load-container binf-hidden"));

    click(By.cssSelector("span.icon.icon-toolbarAdd"));
    ((JavascriptExecutor) wd).executeScript(
            "HTMLInputElement.prototype.click = function() {                     " +
                    "  if(this.type !== 'file') HTMLElement.prototype.click.call(this);  " +
                    "};                                                                  ");
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
