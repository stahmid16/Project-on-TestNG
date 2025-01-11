package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPage {

    @FindBy(css = "[type=email]")
    WebElement txtEmail;


    @FindBy(css = "[type=submit]")
    WebElement resetSubmit;

   // @FindBy(id = "reset")
    //WebElement clickReset;


    public ResetPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doReset(String email){
           txtEmail.sendKeys(email);
          //clickReset.click();
          resetSubmit.click();

    }

}
