package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;

@XStreamAlias("docOutbound")
public class DocOutboundData {
  // Переменные
  // - Глобальные параметры карточки
  private String id;
  private String linkDoc; // Ссылка на карточку документа
  private String titleDoc; // Название карточки документа

  // - Реквизиты документа
  private boolean isTypeDoc; // Вид документа
  private String typeDoc; // Вид документа

  private boolean isTitleText; // Заголовок к тексту
  private String titleText; // Заголовок к тексту

  private boolean isAccessLevel; // Ограничение доступа
  private String accessLevel; // Ограничение доступа

  private boolean isSignatory; // Подписант
  private String signatory; // Подписант

  private String regDate; // Дата регистрации

  private String regNumber; // Регистрационный номер

  private String status; // Статус

  private boolean isAddresseeExternal; // Организация внешнего адресата
  private String addresseeExternal; // Организация внешнего адресата

  private boolean isShippingMethodExternal; // Способ отправки внешнему адресату
  private String shippingMethodExternal; // Способ отправки внешнему адресату

  private boolean isAddresseeInternal; // Адресат внутренний
  private String addresseeInternal; // Адресат внутренний

  // - Маршрут документа
  private boolean checkFormalization; // Проверка оформления

  private boolean agreementHeadOfAuthor; // Согласование руководителем инициатора

  private boolean agreementLawyers; // Согласование юристами

  private boolean isNormController; // Нормоконтролер
  private String normController; // Нормоконтролер

  private boolean isMatchingOne; // Заполняем Согласующий 1?
  private String matchingOne; // Согласующий 1

  private boolean isMatchingTwo; // Заполняем Согласующий 2?
  private String matchingTwo; // Согласующий 2

  private boolean isMatchingThree; // Заполняем Согласующий 3?
  private String matchingThree; // Согласующий 3

  private boolean isSender; // Отправитель
  private String sender; // Отправитель

  private boolean isPriority; // Приоритет
  private String priority; // Приоритет

  private boolean isPriorityReason; // Обоснование приоритета
  private String priorityReason; // Обоснование приоритета

  // - Учет и хранение
  private boolean isCountListDoc; // Количество листов документа
  private String countListDoc; // Количество листов документа

  private boolean isCountListAttach; // Количество листов приложений
  private String countListAttach; // Количество листов приложений

  private boolean isTypeCarrier; // Вид носителя
  private String typeCarrier; // Вид носителя

  private boolean isRubric; // Рубрика
  private String rubric; // Рубрика

  private boolean isPostNumber; // Номер почтового отправления
  private String postNumber; // Номер почтового отправления

  private boolean isReturnDate; // Дата возврата документа
  private String returnDate; // Дата возврата документа

  private boolean isNotificationDate; // Дата поступления уведомления
  private String notificationDate; // Дата поступления уведомления

  private boolean isResponseToNumber; // В ответ на (номер)
  private String responseToNumber; // В ответ на (номер)

  private boolean isResponseToDate; // В ответ на (дата)
  private String responseToDate; // В ответ на (дата)

  // - Вложения
  private boolean isFileXls; // Загружаем файл .xls?
  private String fileXls; // Файл .xls

  private boolean isFileXlsx; // Загружаем файл .xlsx?
  private String fileXlsx; // Файл .xlsx

  private boolean isFileDoc; // Загружаем файл .doc?
  private String fileDoc; // Файл .doc

  private boolean isFileDocx; // Загружаем файл .docx?
  private String fileDocx; // Файл .docx

  private boolean isFilePdf; // Загружаем файл .pdf?
  private String filePdf; // Файл .pdf


  // Getters
  // - Глобальные параметры карточки
  public String getId() {
    return id;
  }

  public String getLinkDoc() {
    return linkDoc;
  }

  public String getTitleDoc() {
    return titleDoc;
  }

  // - Реквизиты документа
  public boolean isTypeDoc() {
    return isTypeDoc;
  }

  public String getTypeDoc() {
    return typeDoc;
  }

  public boolean isTitleText() {
    return isTitleText;
  }

  public String getTitleText() {
    return titleText;
  }

  public boolean isAccessLevel() {
    return isAccessLevel;
  }

  public String getAccessLevel() {
    return accessLevel;
  }

  public boolean isSignatory() {
    return isSignatory;
  }

  public String getSignatory() {
    return signatory;
  }

  public String getRegDate() {
    return regDate;
  }

  public String getRegNumber() {
    return regNumber;
  }

  public String getStatus() {
    return status;
  }

  public boolean isAddresseeExternal() {
    return isAddresseeExternal;
  }

  public String getAddresseeExternal() {
    return addresseeExternal;
  }

  public boolean isShippingMethodExternal() {
    return isShippingMethodExternal;
  }

  public String getShippingMethodExternal() {
    return shippingMethodExternal;
  }

  public boolean isAddresseeInternal() {
    return isAddresseeInternal;
  }

  public String getAddresseeInternal() {
    return addresseeInternal;
  }

  // - Маршрут документа
  public boolean isCheckFormalization() {
    return checkFormalization;
  }

  public boolean isAgreementHeadOfAuthor() {
    return agreementHeadOfAuthor;
  }

  public boolean isAgreementLawyers() {
    return agreementLawyers;
  }

  public String getNormController() {
    return normController;
  }

  public boolean isMatchingOne() {
    return isMatchingOne;
  }

  public String getMatchingOne() {
    return matchingOne;
  }

  public boolean isMatchingTwo() {
    return isMatchingTwo;
  }

  public String getMatchingTwo() {
    return matchingTwo;
  }

  public boolean isMatchingThree() {
    return isMatchingThree;
  }

  public String getMatchingThree() {
    return matchingThree;
  }

  public boolean isSender() {
    return isSender;
  }

  public String getSender() {
    return sender;
  }

  public boolean isPriority() {
    return isPriority;
  }

  public String getPriority() {
    return priority;
  }

  public boolean isPriorityReason() {
    return isPriorityReason;
  }

  public String getPriorityReason() {
    return priorityReason;
  }

  // - Учет и хранение
  public boolean isCountListDoc() {
    return isCountListDoc;
  }

  public String getCountListDoc() {
    return countListDoc;
  }

  public boolean isCountListAttach() {
    return isCountListAttach;
  }

  public String getCountListAttach() {
    return countListAttach;
  }

  public boolean isTypeCarrier() {
    return isTypeCarrier;
  }

  public String getTypeCarrier() {
    return typeCarrier;
  }

  public boolean isRubric() {
    return isRubric;
  }

  public String getRubric() {
    return rubric;
  }

  public boolean isPostNumber() {
    return isPostNumber;
  }

  public String getPostNumber() {
    return postNumber;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public String getNotificationDate() {
    return notificationDate;
  }

  public boolean isResponseToNumber() {
    return isResponseToNumber;
  }

  public String getResponseToNumber() {
    return responseToNumber;
  }

  public boolean isResponseToDate() {
    return isResponseToDate;
  }

  public String getResponseToDate() {
    return responseToDate;
  }

  // - Вложения
  public File getFileXls() {
    if (fileXls == null) {
      return null;
    }
    return new File(fileXls);
  }

  public File getFileXlsx() {
    if (fileXlsx == null) {
      return null;
    }
    return new File(fileXlsx);
  }

  public File getFileDoc() {
    if (fileDoc == null) {
      return null;
    }
    return new File(fileDoc);
  }

  public File getFileDocx() {
    if (fileDocx == null) {
      return null;
    }
    return new File(fileDocx);
  }

  public File getFilePdf() {
    if (filePdf == null) {
      return null;
    }
    return new File(filePdf);
  }

  public boolean isFileXls() {
    return isFileXls;
  }

  public boolean isFileXlsx() {
    return isFileXlsx;
  }

  public boolean isFileDoc() {
    return isFileDoc;
  }

  public boolean isFileDocx() {
    return isFileDocx;
  }

  public boolean isFilePdf() {
    return isFilePdf;
  }

  // Setters
  // - Глобальные
  public DocOutboundData withId(String id) {
    this.id = id;
    return this;
  }

  public DocOutboundData withLinkDoc(String linkDoc) {
    this.linkDoc = linkDoc;
    return this;
  }

  public DocOutboundData withTitleDoc(String titleDoc) {
    this.titleDoc = titleDoc;
    return this;
  }

  // - Реквизиты документа
  public DocOutboundData withIsTypeDoc(boolean isTypeDoc) {
    this.isTypeDoc = isTypeDoc;
    return this;
  }

  public DocOutboundData withTypeDoc(String typeDoc) {
    this.typeDoc = typeDoc;
    return this;
  }

  public DocOutboundData withIsTitleText(boolean isTitleText) {
    this.isTitleText = isTitleText;
    return this;
  }

  public DocOutboundData withTitleText(String titleText) {
    this.titleText = titleText;
    return this;
  }

  public DocOutboundData withIsAccessLevel(boolean isAccessLevel) {
    this.isAccessLevel = isAccessLevel;
    return this;
  }

  public DocOutboundData withAccessLevel(String accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }

  public DocOutboundData withIsSignatory(boolean isSignatory) {
    this.isSignatory = isSignatory;
    return this;
  }

  public DocOutboundData withSignatory(String signatory) {
    this.signatory = signatory;
    return this;
  }

  public DocOutboundData withRegDate(String regDate) {
    this.regDate = regDate;
    return this;
  }

  public DocOutboundData withRegNumber(String regNumber) {
    this.regNumber = regNumber;
    return this;
  }

  public DocOutboundData withStatus(String status) {
    this.status = status;
    return this;
  }

  public DocOutboundData withIsAddresseeExternal(boolean isAddresseeExternal) {
    this.isAddresseeExternal = isAddresseeExternal;
    return this;
  }

  public DocOutboundData withAddresseeExternal(String addresseeExternal) {
    this.addresseeExternal = addresseeExternal;
    return this;
  }

  public DocOutboundData withIsShippingMethodExternal(boolean isShippingMethodExternal) {
    this.isShippingMethodExternal = isShippingMethodExternal;
    return this;
  }

  public DocOutboundData withShippingMethodExternal(String shippingMethodExternal) {
    this.shippingMethodExternal = shippingMethodExternal;
    return this;
  }

  public DocOutboundData withIsAddresseeInternal(boolean isAddresseeInternal) {
    this.isAddresseeInternal = isAddresseeInternal;
    return this;
  }

  public DocOutboundData withAddresseeInternal(String addresseeInternal) {
    this.addresseeInternal = addresseeInternal;
    return this;
  }

  // - Маршрут документа
  public DocOutboundData withCheckFormalization(boolean checkFormalization) {
    this.checkFormalization = checkFormalization;
    return this;
  }

  public DocOutboundData withAgreementHeadOfAuthor(boolean agreementHeadOfAuthor) {
    this.agreementHeadOfAuthor = agreementHeadOfAuthor;
    return this;
  }

  public DocOutboundData withAgreementLawyers(boolean agreementLawyers) {
    this.agreementLawyers = agreementLawyers;
    return this;
  }

  public DocOutboundData withIsNormController(boolean normController) {
    this.isNormController = isNormController;
    return this;
  }

  public DocOutboundData withNormController(String normController) {
    this.normController = normController;
    return this;
  }

  public DocOutboundData withIsMatchingOne(boolean isMatchingOne) {
    this.isMatchingOne = isMatchingOne;
    return this;
  }

  public DocOutboundData withMatchingOne(String matchingOne) {
    this.matchingOne = matchingOne;
    return this;
  }

  public DocOutboundData withIsMatchingTwo(boolean isMatchingTwo) {
    this.isMatchingTwo = isMatchingTwo;
    return this;
  }

  public DocOutboundData withMatchingTwo(String matchingTwo) {
    this.matchingTwo = matchingTwo;
    return this;
  }

  public DocOutboundData withIsMatchingThree(boolean isMatchingThree) {
    this.isMatchingThree = isMatchingThree;
    return this;
  }

  public DocOutboundData withMatchingThree(String matchingThree) {
    this.matchingThree = matchingThree;
    return this;
  }

  public DocOutboundData withIsSender(boolean isSender) {
    this.isSender = isSender;
    return this;
  }

  public DocOutboundData withSender(String sender) {
    this.sender = sender;
    return this;
  }

  public DocOutboundData withIsPriority(boolean priority) {
    this.isPriority = isPriority;
    return this;
  }

  public DocOutboundData withPriority(String priority) {
    this.priority = priority;
    return this;
  }

  public DocOutboundData withIsPriorityReason(boolean isPriorityReason) {
    this.isPriorityReason = isPriorityReason;
    return this;
  }

  public DocOutboundData withPriorityReason(String priorityReason) {
    this.priorityReason = priorityReason;
    return this;
  }

  // - Учет и хранение
  public DocOutboundData withIsCountListDoc(boolean isCountListDoc) {
    this.isCountListDoc = isCountListDoc;
    return this;
  }

  public DocOutboundData withCountListDoc(String countListDoc) {
    this.countListDoc = countListDoc;
    return this;
  }

  public DocOutboundData withIsCountListAttach(boolean isCountListAttach) {
    this.isCountListAttach = isCountListAttach;
    return this;
  }

  public DocOutboundData withCountListAttach(String countListAttach) {
    this.countListAttach = countListAttach;
    return this;
  }

  public DocOutboundData withIsTypeCarrier(boolean isTypeCarrier) {
    this.isTypeCarrier = isTypeCarrier;
    return this;
  }

  public DocOutboundData withTypeCarrier(String typeCarrier) {
    this.typeCarrier = typeCarrier;
    return this;
  }

  public DocOutboundData withIsRubric(boolean isRubric) {
    this.isRubric = isRubric;
    return this;
  }

  public DocOutboundData withRubric(String rubric) {
    this.rubric = rubric;
    return this;
  }

  public DocOutboundData withIsPostNumber(boolean postNumber) {
    this.isPostNumber = isPostNumber;
    return this;
  }

  public DocOutboundData withPostNumber(String postNumber) {
    this.postNumber = postNumber;
    return this;
  }

  public DocOutboundData withIsReturnDate(boolean isReturnDate) {
    this.isReturnDate = isReturnDate;
    return this;
  }

  public DocOutboundData withReturnDate(String returnDate) {
    this.returnDate = returnDate;
    return this;
  }

  public DocOutboundData withIsNotificationDate(boolean isNotificationDate) {
    this.isNotificationDate = isNotificationDate;
    return this;
  }

  public DocOutboundData withNotificationDate(String notificationDate) {
    this.notificationDate = notificationDate;
    return this;
  }

  public DocOutboundData withIsResponseToNumber(boolean isResponseToNumber) {
    this.isResponseToNumber = isResponseToNumber;
    return this;
  }

  public DocOutboundData withResponseToNumber(String responseToNumber) {
    this.responseToNumber = responseToNumber;
    return this;
  }

  public DocOutboundData withIsResponseToDate(boolean isResponseToDate) {
    this.isResponseToDate = isResponseToDate;
    return this;
  }

  public DocOutboundData withResponseToDate(String responseToDate) {
    this.responseToDate = responseToDate;
    return this;
  }

  // - Вложения
  public DocOutboundData withIsFileXls(boolean isFileXls) {
    this.isFileXls = isFileXls;
    return this;
  }

  public DocOutboundData withFileXls(File file) {
    this.fileXls = file.getPath();
    return this;
  }

  public DocOutboundData withIsFileXlsx(boolean isFileXlsx) {
    this.isFileXlsx = isFileXlsx;
    return this;
  }

  public DocOutboundData withFileXlsx(File file) {
    this.fileXlsx = file.getPath();
    return this;
  }

  public DocOutboundData withIsFileDoc(boolean isFileDoc) {
    this.isFileDoc = isFileDoc;
    return this;
  }

  public DocOutboundData withFileDoc(File file) {
    this.fileDoc = file.getPath();
    return this;
  }

  public DocOutboundData withIsFileDocx(boolean isFileDocx) {
    this.isFileDocx = isFileDocx;
    return this;
  }

  public DocOutboundData withFileDocx(File file) {
    this.fileDocx = file.getPath();
    return this;
  }

  public DocOutboundData withIsFilePdf(boolean isFilePdf) {
    this.isFilePdf = isFilePdf;
    return this;
  }

  public DocOutboundData withFilePdf(File file) {
    this.filePdf = file.getPath();
    return this;
  }
}