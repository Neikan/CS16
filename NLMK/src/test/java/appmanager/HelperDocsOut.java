package appmanager;

import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.sql.Timestamp;

public class HelperDocsOut extends HelperDocs {
  public HelperDocsOut(ApplicationManager app) throws MalformedURLException {
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

  public void fillDocDetails() {
    type(By.xpath("(//input[@type='search'])[13]"), "Письмо"); // Вид документа
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид документа'])[2]/following::strong[1]"));

    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Заголовок к тексту'])[3]/following::textarea[1]"), String.valueOf(new Timestamp(System.currentTimeMillis()))); // Заголовок к тексту

    type(By.xpath("(//input[@type='search'])[14]"), "КТ"); // Ограничение доступа
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ограничение доступа'])[1]/following::li[1]"));

    type(By.cssSelector("#alpaca104"), "Рабовский"); // Подписант
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Рабовский Илья Александрович'])[1]/following::span[1]")); // Подписант

    type(By.xpath("(//input[@type='search'])[15]"), "ООО \"Матрёшка\""); // Внешний адресат
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Организация внешнего адресата'])[2]/following::td[3]"));  // Внешний адресат

    scroll(By.cssSelector("#alpaca167")); // Внутренний адресат
    type(By.cssSelector("#alpaca167"), "Кутузов"); // Внутренний адресат
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Главнокомандующий русской армией'])[1]/following::span[1]")); // Внутренний адресат
  }

  public void fillDocRoute() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Проверка оформления'])[1]/following::span[1]")); // Проверка оформления
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласование руководителем инициатора'])[2]/following::span[1]")); // Согласование руководителем инициатора
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласование юристами'])[2]/following::span[1]")); // Согласование юристами

    // Нормоконтролер - не заполняем пока что

     // Согласующие
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[1]"), "Мягков");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='УПРАВЛЕНИЕ ПО ОПЕРАТИВНОМУ ПЛАНИРОВАНИЮ'])[1]/following::span[1]"));

    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Добавить значение'])[25]/following::i[2]"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Добавить значение'])[25]/following::input[1]"), "Сыромятников");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=concat('ПАО ', '\"', 'НЛМК', '\"', '')])[1]/following::span[1]"));

    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Добавить значение'])[26]/following::i[2]"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Добавить значение'])[26]/following::input[1]"), "Гахова");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ЕГ'])[1]/following::div[1]"));

    // Отправитель
    type(By.cssSelector("#alpaca212"), "Рокоссовский");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Старший техник'])[1]/following::span[1]"));

    // Приоритет
    type(By.xpath("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::input[2]"), "Высокий");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::li[1]"));
  }

  public void fillDocAccounting() {

  }

}
