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

    // Вид документа
    // Способ 1
    type(By.xpath("(//input[@type='search'])[13]"), "Письмо");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид документа'])[2]/following::strong[1]"));

    // Способ 2
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид документа'])[2]/following::span[2]"));
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='outgoing@letter'])[1]/following::td[1]"));
    //clickWait(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Please, wait...'])[1]/following::button[1]"));

    // Заголовок к тексту
    type(By.xpath("//div[3]/div/div/div/div/div/div/textarea"), String.valueOf(new Timestamp(System.currentTimeMillis())));

    // Ограничение доступа
    type(By.xpath("(//input[@type='search'])[14]"), "КТ");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ограничение доступа'])[1]/following::li[1]"));

    // Подписант
    type(By.cssSelector("#alpaca104"), "Рабовский");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Рабовский Илья Александрович'])[1]/following::span[1]"));

    // Внешний адресат
    type(By.xpath("(//input[@type='search'])[15]"), "ООО \"Матрёшка\"");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация внешнего адресата'])[2]/following::td[3]"));

    //Способ отправки внешнему адресату
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Способ отправки внешнему адресату'])[2]/following::input[2]"), "Электро");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Способ отправки внешнему адресату'])[2]/following::p[1]"));

    // Внутренний адресат
    scroll(By.cssSelector("#alpaca167"));
    type(By.cssSelector("#alpaca167"), "Кутузов");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Главнокомандующий русской армией'])[1]/following::span[1]"));
  }

  public void fillDocRoute() {
    clickSwitch("Проверка оформления"); //Флаг "Проверка оформления"
    clickSwitch("Согласование руководителем инициатора"); // Флаг "Согласование руководителем инициатора"
    clickSwitch("Согласование юристами"); // Флаг "Согласование юристами"

    // Нормоконтролер - не заполняем пока что

    // Согласующие
    // Способ 1
    //type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[1]"), "Мягков");
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мягков Александр Николаевич'])[1]/following::span[1]"));
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мягков Александр Николаевич'])[1]/following::span[1]"));

    //click(By.xpath("//div[5]/div/div/div/div[2]/div/button[2]/i"));
    //type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[2]"), "Сыромятников");
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='УПРАВЛЕНИЕ ПО ОПЕРАТИВНОМУ ПЛАНИРОВАНИЮ'])[1]/following::span[1]"));

    //type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[3]"), "Гахова");
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ЕГ'])[1]/following::div[1]"));

    // Способ 2
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
    //clickWait(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отправитель'])[2]/following::span[2]"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отправитель'])[2]/following::span[2]"));
    scroll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"));
    //visibleOff(By.xpath("//*[@id=\"templates\"]/div[1]/table/tbody/tr[2]/td[2]"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), "Рокоссовский");
    sendKey(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::input[2]"), Keys.ENTER);
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация'])[1]/following::td[15]"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Закрыть'])[1]/preceding::button[1]"));

    // Способ 2
    //type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отправитель'])[2]/following::input[1]"), "Рокоссовский");
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Отправитель'])[2]/following::div[18]"));

    // Приоритет
    // Способ 1
    //typeWait(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::input[2]"), "Высокий"); - не подходит из-за скрытого поля
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::li[1]"));

    //Способ 2
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::span[2]"));
    //type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[4]/following::input[1]"), "Высокий");
    //sendKey(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[4]/following::input[1]"), Keys.ENTER);
    //click(By.xpath("//div[2]/table/tbody/tr/td"));
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пожалуйста, подождите...'])[1]/following::button[1]"));
    doubleClick(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Обычный'])[2]/following::td[2]"));
    //click(By.xpath("//*[@id=\"refsadmin-container-panel\"]/div[2]/table/tbody/tr[3]/td[1]")); - проверить
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Высокий'])[2]/following::td[2]"));
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='>>'])[1]/following::button[1]"));

    // Обоснование приоритета
    scroll(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Обоснование приоритета'])[1]/following::textarea[1]"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Обоснование приоритета'])[1]/following::textarea[1]"), "Казнить, нельзя помиловать!");
  }

  public void fillDocAccounting() {
    //Количество листов документа
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Количество листов документа'])[2]/following::input[1]"), "10");

    //Количество листов приложений
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Количество листов приложений'])[2]/following::input[1]"), "5");

    //Вид носителя
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид носителя'])[2]/following::input[2]"), "Бумажный");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид носителя'])[2]/following::p[1]"));

    //Рубрика
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Рубрика'])[2]/following::input[2]"), "Рубрика");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Рубрика'])[2]/following::li[1]"));

    //В ответ на (номер)
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='В ответ на (номер)'])[2]/following::input[1]"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='В ответ на (номер)'])[2]/following::input[1]"), "2333");

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
