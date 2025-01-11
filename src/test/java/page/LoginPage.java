package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    @FindBy(id="email")
    WebElement txtEmail;
    @FindBy(id="password")
    WebElement txtPassword;
    @FindBy (css = "[type=submit]")
    WebElement btnLogin;
    @FindBy(css = "[role=menuitem]")
    public List<WebElement> menuROle;



  public LoginPage(WebDriver driver){
      PageFactory.initElements(driver,this);

  }


    public void doLogin(String email, String password){
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }


}

