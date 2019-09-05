package appmanager;

import org.openqa.selenium.By;

public class HelperNavigation extends HelperBase {

  public HelperNavigation(ApplicationManager app) {
    super(app);
  }

  public void main() {
    click(By.cssSelector("div.csui-icon-home"));
  }

  public void favorites(String titleFavorite) {
    invisibleWidgetLoader("favorites");
    click(By.xpath("//div[@data-csui-widget_type='favorites']/.//span[@title='" + titleFavorite + "']"), 1);
  }
}
