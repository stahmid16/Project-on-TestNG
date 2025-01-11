package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.ItemModel;

import java.security.Key;

public class RandomTwoIteamPage {
    @FindBy(id = "itemName")
    WebElement txtIteamName;

    @FindBy(css = "input[type='number']")
    WebElement txtNumberInput;

    @FindBy(id = "amount")
    WebElement txtAmmount;

    @FindBy(id = "purchaseDate")
    WebElement txtDatet;

    @FindBy(id = "month")
    WebElement txtMonth;

    @FindBy(id = "remarks")
    WebElement txtRemark;

    @FindBy(css = "[type='submit']")
    WebElement btnSubmit;






    public RandomTwoIteamPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

  public void doEntryIteam(String iNAme, String iQuantity, String iAmmmount,String iRemark){
       txtIteamName.sendKeys(iNAme);
        txtNumberInput.sendKeys(iQuantity);
        txtAmmount.sendKeys(iQuantity);
        txtDatet.click();
        txtDatet.sendKeys("04111999");
        txtMonth.click();
        txtMonth.sendKeys(Keys.ARROW_DOWN);
        txtMonth.click();
        txtRemark.sendKeys(iRemark);
        btnSubmit.click();
    }







}
