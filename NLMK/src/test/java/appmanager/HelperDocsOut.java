package appmanager;

import model.DocOutboundData;
import model.DocsOutbound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

public class HelperDocsOut extends HelperDocs {
  public HelperDocsOut(ApplicationManager app) {
    super(app);
  }

  public void fillDocDetails() {
    switchTabDoc("Реквизиты документа");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Вид документа"), "Вид документа", "Письмо");
    type(fieldTextArea("Заголовок к тексту"), String.valueOf(new Timestamp(System.currentTimeMillis())));
    typeFieldWithAutoComplete(fieldLookupNSIId("Ограничение доступа"), "Ограничение доступа", "КТ");
    typeFieldWithAutoCompleteUser(fieldLookupUserId("Подписант"), "Подписант", "Рабовский");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Организация внешнего адресата"), "Организация внешнего адресата", "ООО \"Матрёшка\"");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Способ отправки внешнему адресату"), "Способ отправки внешнему адресату", "Электро");
    scroll(fieldLookupUserFor("Адресат внутренний"));
    typeFieldWithAutoCompleteUser(fieldLookupUserFor("Адресат внутренний"), "Адресат внутренний", "Кутузов");
  }

  public void fillDocRoute() {
    switchTabDoc("Маршрут документа");
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
    switchTabDoc("Учет и хранение");
    type(fieldInteger("Количество листов документа"), "10");
    type(fieldInteger("Количество листов приложений"), "20");
    typeFieldWithAutoComplete(fieldLookupNSIFor("Вид носителя"), "Вид носителя", "Бумажный");
    typeFieldWithAutoComplete(fieldLookupNSIId("Рубрика"), "Рубрика", "Рубрика а");
    type(fieldText("Номер почтового отправления"), "300");
    type(fieldText("В ответ на (номер)"), "200");
    type(fieldDateTex("В ответ на (дата)"), "03.09.2019");
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

  public void initModification() {
    invisibleAll(By.className("load-container binf-hidden"), 5); // Тут нужен какой-то таймаут
    gotoDocPage(getIdDoc());
  }

  public void attachFiles() {
    File fileXLS = new File("src/test/resources/Attachments/Excel XLS.xls");
    //DocOutboundData docOut = new DocOutboundData().withFile(fileXLS);

    File fileXLSX = new File("src/test/resources/Attachments/Excel XLSX.xlsx");
    File filePDF = new File("src/test/resources/Attachments/PDF 1.pdf");
    File fileDOC = new File("src/test/resources/Attachments/Служебная записка 1.doc");
    File fileDOCX = new File("src/test/resources/Attachments/Тестовый документ №1.docx");

    invisibleWidgetLoader("attributes");
    switchTabLink("Файлы");
    attachFile(fileXLS);
    attachFile(fileXLSX);
    attachFile(fileDOC);
    attachFile(fileDOCX);
    attachFile(filePDF);
  }
}
