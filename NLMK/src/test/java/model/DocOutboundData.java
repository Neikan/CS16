package model;

import java.util.Date;

public class DocOutboundData {
  private int id = Integer.MAX_VALUE;
  private String typeDoc; // Вид документа
  private String titleText; // Заголовок к тексту
  private String signatory; // Подписант
  private Date regDate; // Дата регистрации
  private String accessLevel; // Ограничение доступа
  private String status; // Статус

  // Перечислить остальные поля и таблицы

  public int getId() {
    return id;
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

  public Date getRegDate() {
    return regDate;
  }

  public String getAccessLevel() {
    return accessLevel;
  }

  public String getStatus() {
    return status;
  }

  public DocOutboundData withId(int id) {
    this.id = id;
    return this;
  }

  public DocOutboundData withFirstname(String typeDoc) {
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

  public DocOutboundData withRegDate(Date regDate) {
    this.regDate = regDate;
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
}