package appmanager;

import com.google.gson.internal.$Gson$Preconditions;
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

  public void docLocator() {
    getLoc(By.xpath("(//input[@type='search'])[13]"));
  }

  public void fillDocDetails() {

    // Вид документа
    //Способ 1
    type(By.xpath("(//input[@type='search'])[13]"), "Письмо");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид документа'])[2]/following::strong[1]"));

    //Способ 2
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Вид документа'])[2]/following::span[2]"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='outgoing@letter'])[1]/following::td[1]"));
    clickWait(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Please, wait...'])[1]/following::button[1]"));

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
    // Проверка оформления
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Проверка оформления'])[1]/following::span[1]"));

    // Согласование руководителем инициатора
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласование руководителем инициатора'])[2]/following::span[1]"));

    // Согласование юристами
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласование юристами'])[2]/following::span[1]"));

    // Нормоконтролер - не заполняем пока что

    // Согласующие
    //Способ 1
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[1]"), "Мягков");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мягков Александр Николаевич'])[1]/following::span[1]"));
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мягков Александр Николаевич'])[1]/following::span[1]"));

/*    click(By.xpath("//div[5]/div/div/div/div[2]/div/button[2]/i"));
    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[2]"), "Сыромятников");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='УПРАВЛЕНИЕ ПО ОПЕРАТИВНОМУ ПЛАНИРОВАНИЮ'])[1]/following::span[1]"));

    type(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Согласующие'])[2]/following::input[3]"), "Гахова");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ЕГ'])[1]/following::div[1]"));
*/

    //Способ 2


    // Отправитель
    type(By.cssSelector("#alpaca212"), "Рокоссовский");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Старший техник'])[1]/following::span[1]"));

    // Приоритет
    type(By.xpath("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::input[2]"), "Высокий");
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Приоритет'])[2]/following::li[1]"));


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

}
