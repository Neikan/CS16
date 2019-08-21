package appmanager;

import org.openqa.selenium.By;

public class HelperSession extends HelperBase {

  public HelperSession(ApplicationManager app) {
    super(app);
  }

  public void loginAdmin() {
    type(By.name("otds_username"), app.getProperty("web.loginAdmin"));
    type(By.name("otds_password"), app.getProperty("web.passwordAdmin"));
    click(By.id("loginbutton"));
  }

  public void loginAuthor() {
    type(By.name("otds_username"), app.getProperty("web.loginAuthor"));
    type(By.name("otds_password"), app.getProperty("web.passwordAuthor"));
    click(By.id("loginbutton"));
  }

  public void loginHeadOfAuthor() {
    type(By.name("otds_username"), app.getProperty("web.loginHeadOfAuthor"));
    type(By.name("otds_password"), app.getProperty("web.passwordHeadOfAuthor"));
    click(By.id("loginbutton"));
  }

  public void loginAddressee() {
    type(By.name("otds_username"), app.getProperty("web.loginAddressee"));
    type(By.name("otds_password"), app.getProperty("web.passwordAddressee"));
    click(By.id("loginbutton"));
  }

  public void loginController() {
    type(By.name("otds_username"), app.getProperty("web.loginController"));
    type(By.name("otds_password"), app.getProperty("web.passwordController"));
    click(By.id("loginbutton"));
  }

  public void loginMatchingOne() {
    type(By.name("otds_username"), app.getProperty("web.loginMatchingOne"));
    type(By.name("otds_password"), app.getProperty("web.passwordMatchingOne"));
    click(By.id("loginbutton"));
  }

  public void loginMatchingTwo() {
    type(By.name("otds_username"), app.getProperty("web.loginMatchingTwo"));
    type(By.name("otds_password"), app.getProperty("web.passwordMatchingTwo"));
    click(By.id("loginbutton"));
  }

  public void loginMatchingThree() {
    type(By.name("otds_username"), app.getProperty("web.loginMatchingThree"));
    type(By.name("otds_password"), app.getProperty("web.passwordMatchingThree"));
    click(By.id("loginbutton"));
  }

  public void loginLawyer() {
    type(By.name("otds_username"), app.getProperty("web.loginLawyer"));
    type(By.name("otds_password"), app.getProperty("web.passwordLawyer"));
    click(By.id("loginbutton"));
  }

  public void loginSignatory() {
    type(By.name("otds_username"), app.getProperty("web.loginSignatory"));
    type(By.name("otds_password"), app.getProperty("web.passwordSignatory"));
    click(By.id("loginbutton"));
  }

  public void loginOutRegistrator() {
    type(By.name("otds_username"), app.getProperty("web.loginOutRegistrator"));
    type(By.name("otds_password"), app.getProperty("web.passwordOutRegistrator"));
    click(By.id("loginbutton"));
  }

  public void loginSender() {
    type(By.name("otds_username"), app.getProperty("web.loginSender"));
    type(By.name("otds_password"), app.getProperty("web.passwordSender"));
    click(By.id("loginbutton"));
  }
}
