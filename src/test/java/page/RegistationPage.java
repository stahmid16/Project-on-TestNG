package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistationPage {

    @FindBy(id = "firstName")
    WebElement txtFname;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "phoneNumber")
    WebElement txtNumber;

    @FindBy(id = "address")
    WebElement txtAddress;

    @FindBy(css = "[type=radio]")
    List<WebElement> btnRadio;

    @FindBy(css = "[type=checkbox]")
    WebElement chkBoox;

    @FindBy(id = "register")
    WebElement subReg;

    public RegistationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public void doRegistation(String fname, String email,String password ,String phnNumber,String address){
        txtFname.sendKeys(fname);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtNumber.sendKeys(phnNumber);
        txtAddress.sendKeys(address);
        btnRadio.get(0).click();
        chkBoox.click();
        subReg.click();


    }


}



