package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UpdateEmailPage {
//
//    //@FindBy(css = "[type=button]")
//    @FindBy(css = "[type=button]")
//    WebElement btnViwe;
//    //public List<WebElement> btnViwe;

    @FindBy(css = "[type=button]")
    public  List<WebElement> btnGet;

    @FindBy(css = "[role=menuitem]")
    public  List<WebElement> menuItem;

    @FindBy(name = "firstName")
    WebElement fName;
    @FindBy(name = "email")
    WebElement eMail;




    public UpdateEmailPage(WebDriver driver){
    PageFactory.initElements(driver,this);
}

public void doUpdate(String email) throws InterruptedException {
    btnGet.get(0).click();
    menuItem.get(0).click();
    btnGet.get(1).click();

  eMail.sendKeys(Keys.CONTROL ,"a",Keys.BACK_SPACE);
  eMail.sendKeys(email);

    btnGet.get(2).click();
//    btnGet.get(0).click();
//    menuItem.get(1).click();

}

}
