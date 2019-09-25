package appmanager;

import model.DocOutboundData;
import model.DocsOutbound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class HelperDocsOut extends HelperDocs {
  public HelperDocsOut(ApplicationManager app) {
    super(app);
  }

  public void fillForm(DocOutboundData docOutbound) { // Надо допилить все поля на форму
    fillDocDetails(docOutbound);
    fillDocRoute(docOutbound);
    fillDocAccounting(docOutbound);
  }

  public void fillDocDetails(DocOutboundData docOutbound) {
    switchTabDoc("Реквизиты документа");

    if (docOutbound.isTypeDoc()) {
      typeFieldWithAutoComplete(fieldLookupNSIFor("Вид документа"), "Вид документа", docOutbound.getTypeDoc());
    }

    if (docOutbound.isTitleText()) {
      if (docOutbound.getTitleText().equals("Timestamp")) {
        type(fieldTextArea("Заголовок к тексту"), String.valueOf(new Timestamp(System.currentTimeMillis())));
      } else
        type(fieldTextArea("Заголовок к тексту"), docOutbound.getTitleText());
    }

    if (docOutbound.isAccessLevel()) {
      typeFieldWithAutoComplete(fieldLookupNSIId("Ограничение доступа"), "Ограничение доступа", docOutbound.getAccessLevel());
    }

    if (docOutbound.isSignatory()) {
      typeFieldWithAutoCompleteUser(fieldLookupUserId("Подписант"), "Подписант", docOutbound.getSignatory());
    }

    if (docOutbound.isAddresseeExternal()) {
      typeFieldWithAutoComplete(fieldLookupNSIFor("Организация внешнего адресата"), "Организация внешнего адресата", docOutbound.getAddresseeExternal());
    }

    if (docOutbound.isAddresseeExternalFullName()) {
      type(fieldText("Адресат внешний"), docOutbound.getAddresseeExternalFullName());
    }

    if (docOutbound.isAddresseeExternalFullNameDative()) {
      type(fieldText("ФИО внешнего адресата (в дательном падеже)"), docOutbound.getAddresseeExternalFullNameDative());
    }

    if (docOutbound.isAddresseeExternalEmail()) {
      type(fieldText("E-mail внешнего адресата"), docOutbound.getAddresseeExternalEmail());
    }

    if (docOutbound.isShippingMethodExternal()) {
      typeFieldWithAutoComplete(fieldLookupNSIFor("Способ отправки внешнему адресату"), "Способ отправки внешнему адресату", docOutbound.getShippingMethodExternal());
    }

    if (docOutbound.isAddresseeInternal()) {
      openLookupUser("Адресат внутренний");
      typeLookupForeverAloneUser(docOutbound.getAddresseeInternal(), "2");
      clickButtonFooter("Сохранить");
    }
  }

  public void fillDocRoute(DocOutboundData docOutbound) {
    switchTabDoc("Маршрут документа");

    if (docOutbound.isCheckFormalization()) {
      click(fieldSwitch("Проверка оформления"));
    }

    if (docOutbound.isAgreementHeadOfAuthor()) {
      click(fieldSwitch("Согласование руководителем инициатора"));
    }

    if (docOutbound.isAgreementLawyers()) {
      click(fieldSwitch("Согласование юристами"));
    }

    // Нормоконтролер - не заполняем пока что

    if (docOutbound.isMatchingOne() || docOutbound.isMatchingTwo() || docOutbound.isMatchingThree()) {
      openLookupUser("Согласующие");
      typeLookupSeveralUser(docOutbound.isMatchingOne(), docOutbound.getMatchingOne(), "3");
      typeLookupSeveralUser(docOutbound.isMatchingTwo(), docOutbound.getMatchingTwo(), "3");
      typeLookupSeveralUser(docOutbound.isMatchingThree(), docOutbound.getMatchingThree(), "3");
      clickButtonFooter("Сохранить");
    }

    if (docOutbound.isSender()) {
      openLookupUser("Отправитель");
      typeLookupForeverAloneUser(docOutbound.getSender(), "2");
      clickButtonFooter("Сохранить");
    }

    if (docOutbound.isPriority()) {
      openLookupNSI("Приоритет");
      doubleClick(getCellLookupTable(docOutbound.getPriority()));
    }

    if (docOutbound.isPriorityReason()) {
      scroll(fieldTextArea("Обоснование приоритета"));
      type(fieldTextArea("Обоснование приоритета"), docOutbound.getPriorityReason());
    }
  }

  public void fillDocAccounting(DocOutboundData docOutbound) {
    switchTabDoc("Учет и хранение");

    if (docOutbound.isCountListDoc()) {
      type(fieldInteger("Количество листов документа"), docOutbound.getCountListDoc());
    }

    if (docOutbound.isCountListAttach()) {
      type(fieldInteger("Количество листов приложений"), docOutbound.getCountListAttach());
    }

    if (docOutbound.isTypeCarrier()) {
      typeFieldWithAutoComplete(fieldLookupNSIFor("Вид носителя"), "Вид носителя", docOutbound.getTypeCarrier());
    }

    if (docOutbound.isRubric()) {
      typeFieldWithAutoComplete(fieldLookupNSIId("Рубрика"), "Рубрика", docOutbound.getRubric());
    }

    if (docOutbound.isPostNumber()) {
      type(fieldText("Номер почтового отправления"), docOutbound.getPostNumber());
    }

    if (docOutbound.isResponseToNumber()) {
      type(fieldText("В ответ на (номер)"), docOutbound.getResponseToNumber());
    }

    if (docOutbound.isResponseToDate()) {
      type(fieldDateTex("В ответ на (дата)"), docOutbound.getResponseToDate());
    }
  }

  private DocsOutbound docsOutboundCashe = null;

  public DocsOutbound all() {
    if (docsOutboundCashe != null) {
      return new DocsOutbound(docsOutboundCashe);
    }

    docsOutboundCashe = new DocsOutbound();
    List<WebElement> rows = wd.findElements(By.partialLinkText(" - ИСХ - ")); // Если эта часть статична, то ок
    for (WebElement row : rows) {
      String titleDoc = row.getText();
      String linkDoc = row.getAttribute("href");
      docsOutboundCashe.add(new DocOutboundData().withTitleDoc(titleDoc).withLinkDoc(linkDoc));
    }
    return new DocsOutbound(docsOutboundCashe);
  }

  public void initModification(DocOutboundData docOutbound) {
    invisibleAll(By.className("load-container binf-hidden"), 5); // Тут нужен какой-то таймаут
    docOutbound.withId(getIdDoc());
    gotoDocPage(docOutbound.getId());
  }

  public void attachFiles(DocOutboundData docOutbound) {
    //invisibleAll(By.className("load-container binf-hidden"), 10);
    invisibleWidgetLoader("attributes");
    switchTabLink("Файлы");
    attachFile(docOutbound.isFileXls(), docOutbound.getFileXls());
    attachFile(docOutbound.isFileXlsx(), docOutbound.getFileXlsx());
    attachFile(docOutbound.isFileDoc(), docOutbound.getFileDoc());
    attachFile(docOutbound.isFileDocx(), docOutbound.getFileDocx());
    attachFile(docOutbound.isFilePdf(), docOutbound.getFilePdf());
  }

  // Тестовые методы
  public void randomInvis() {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(invisibilityOfElementLocated(By.xpath("//div[@class='load-container csui-global']")));
  }

  public void randomDisplay() {
    System.out.println(wd.findElement(By.xpath("//div[@class='load-container csui-global']")).isDisplayed());
    while (wd.findElement(By.xpath("//div[@class='load-container csui-global']")).isDisplayed()) {
      try {
        wd.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void randomDis() {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='load-container csui-global']")));
  }

  public void getTitleOut(DocOutboundData docOutbound) {
    invisibleWidgetLoader("attributes");
    docOutbound.withTitleDoc(wd.findElement(By.xpath("//div[@class='cs-header']/.//h2[@class='csui-item-name-block']")).getText());
  }

  public void openTask(DocOutboundData docOutbound) {
    invisibleAll(By.className("load-container binf-hidden"), 10);
    scroll(By.xpath("//div[@class='SLIDescription']/.//span[text()[contains(.,'" + docOutbound.getTitleDoc() + "')]]"));
    click(By.xpath("//div[@class='SLIDescription']/.//span[text()[contains(.,'" + docOutbound.getTitleDoc() + "')]]"));
  }
}
