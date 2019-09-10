package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.File;

@XStreamAlias("docOutbound")
public class DocOutboundData {
  // Глобальные параметры карточки
  private int id = Integer.MAX_VALUE;
  private String linkDoc; // Ссылка на карточку документа
  private String titleDoc; // Название карточки документа

  // Реквизиты документа
  private String typeDoc; // Вид документа
  private String titleText; // Заголовок к тексту
  private String signatory; // Подписант
  private String regDate; // Дата регистрации
  private String regNumber; // Регистрационный номер
  private String accessLevel; // Ограничение доступа
  private String status; // Статус
  private String addresseeExternal; // Организация внешнего адресата
  private String shippingMethodExternal; // Способ отправки внешнему адресату
  private String addresseeInternal; // Адресат внутренний

  // Маршрут документа
  private boolean checkFormalization; // Проверка оформления
  private boolean agreementHeadOfAuthor; // Согласование руководителем инициатора
  private boolean agreementLawyers; // Согласование юристами
  private String normController; // Нормоконтролер
  private String matchingOne; // Согласующий 1
  private String matchingTwo; // Согласующий 2
  private String matchingThree; // Согласующий 3
  private String sender; // Отправитель
  private String priority; // Приоритет
  private String priorityReason; // Обоснование приоритета

  // Учет и хранение
  private String countListDoc; // Количество листов документа
  private String countListAttach; // Количество листов приложений
  private String typeMedium; // Вид носителя
  private String rubric; // Рубрика
  private String postNumber; // Номер почтового отправления
  private String returnDate; // Дата возврата документа
  private String notificationDate; // Дата поступления уведомления
  private String responseToNumber; // В ответ на (номер)
  private String responseToDate; // В ответ на (дата)

  // Вложения
  private String fileXLS; // Файл .xls
  private String fileXLSX; // Файл .xlsx
  private String fileDOC; // Файл .doc
  private String fileDOCX; // Файл .docx
  private String filePDF; // Файл .pdf

  // Геттеры

  public int getId() {
    return id;
  }

  public String getLinkDoc() {
    return linkDoc;
  }

  public String getTitleDoc() {
    return titleDoc;
  }

  public String getTypeDoc() {
    return typeDoc;
  }

  public String getTitleText() {
    return titleText;
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

  public String getAccessLevel() {
    return accessLevel;
  }

  public String getStatus() {
    return status;
  }

  public String getAddresseeExternal() {
    return addresseeExternal;
  }

  public String getShippingMethodExternal() {
    return shippingMethodExternal;
  }

  public String getAddresseeInternal() {
    return addresseeInternal;
  }

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

  public String getMatchingOne() {
    return matchingOne;
  }

  public String getMatchingTwo() {
    return matchingTwo;
  }

  public String getMatchingThree() {
    return matchingThree;
  }

  public String getSender() {
    return sender;
  }

  public String getPriority() {
    return priority;
  }

  public String getPriorityReason() {
    return priorityReason;
  }

  public String getCountListDoc() {
    return countListDoc;
  }

  public String getCountListAttach() {
    return countListAttach;
  }

  public String getTypeMedium() {
    return typeMedium;
  }

  public String getRubric() {
    return rubric;
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

  public String getResponseToNumber() {
    return responseToNumber;
  }

  public String getResponseToDate() {
    return responseToDate;
  }

  public File getFileXLS() {
    if (fileXLS == null) {
      return null;
    }
    return new File(fileXLS);
  }

  public File getFileXLSX() {
    if (fileXLSX == null) {
      return null;
    }
    return new File(fileXLSX);
  }

  public File getFileDOC() {
    if (fileDOC == null) {
      return null;
    }
    return new File(fileDOC);
  }

  public File getFileDOCX() {
    if (fileDOCX == null) {
      return null;
    }
    return new File(fileDOCX);
  }

  public File getFilePDF() {
    if (filePDF == null) {
      return null;
    }
    return new File(filePDF);
  }

  // Сеттеры

  public DocOutboundData withId(int id) {
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


  public DocOutboundData withTypeDoc(String typeDoc) {
    this.typeDoc = typeDoc;
    return this;
  }

  public DocOutboundData withTitleText(String titleText) {
    this.titleText = titleText;
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

  public DocOutboundData withAccessLevel(String accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }

  public DocOutboundData withStatus(String status) {
    this.status = status;
    return this;
  }

  public DocOutboundData withAddresseeExternal(String addresseeExternal) {
    this.addresseeExternal = addresseeExternal;
    return this;
  }

  public DocOutboundData withShippingMethodExternal(String shippingMethodExternal) {
    this.shippingMethodExternal = shippingMethodExternal;
    return this;
  }

  public DocOutboundData withAddresseeInternal(String addresseeInternal) {
    this.addresseeInternal = addresseeInternal;
    return this;
  }

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

  public DocOutboundData withNormController(String normController) {
    this.normController = normController;
    return this;
  }

  public DocOutboundData withMatchingOne(String matchingOne) {
    this.matchingOne = matchingOne;
    return this;
  }

  public DocOutboundData withMatchingTwo(String matchingTwo) {
    this.matchingTwo = matchingTwo;
    return this;
  }

  public DocOutboundData withMatchingThree(String matchingThree) {
    this.matchingThree = matchingThree;
    return this;
  }

  public DocOutboundData withSender(String sender) {
    this.sender = sender;
    return this;
  }

  public DocOutboundData withPriority(String priority) {
    this.priority = priority;
    return this;
  }

  public DocOutboundData withPriorityReason(String priorityReason) {
    this.priorityReason = priorityReason;
    return this;
  }

  public DocOutboundData withCountListDoc(String countListDoc) {
    this.countListDoc = countListDoc;
    return this;
  }

  public DocOutboundData withCountListAttach(String countListAttach) {
    this.countListAttach = countListAttach;
    return this;
  }

  public DocOutboundData withTypeMedium(String typeMedium) {
    this.typeMedium = typeMedium;
    return this;
  }

  public DocOutboundData withRubric(String rubric) {
    this.rubric = rubric;
    return this;
  }

  public DocOutboundData withPostNumber(String postNumber) {
    this.postNumber = postNumber;
    return this;
  }

  public DocOutboundData withReturnDate(String returnDate) {
    this.returnDate = returnDate;
    return this;
  }

  public DocOutboundData withNotificationDate(String notificationDate) {
    this.notificationDate = notificationDate;
    return this;
  }

  public DocOutboundData withResponseToNumber(String responseToNumber) {
    this.responseToNumber = responseToNumber;
    return this;
  }

  public DocOutboundData withResponseToDate(String responseToDate) {
    this.responseToDate = responseToDate;
    return this;
  }

  public DocOutboundData withFileXLS(File file) {
    this.fileXLS = file.getPath();
    return this;
  }

  public DocOutboundData withFileXLSX(File file) {
    this.fileXLSX = file.getPath();
    return this;
  }

  public DocOutboundData withFileDOC(File file) {
    this.fileDOC = file.getPath();
    return this;
  }

  public DocOutboundData withFileDOCX(File file) {
    this.fileDOCX = file.getPath();
    return this;
  }

  public DocOutboundData withFilePDF(File file) {
    this.filePDF = file.getPath();
    return this;
  }
}