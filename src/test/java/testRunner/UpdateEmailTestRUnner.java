package testRunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import page.UpdateEmailPage;
import setup.Setup;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UpdateEmailTestRUnner extends Setup {

    @BeforeTest
    public void dpLogin() throws IOException, ParseException, InterruptedException {
        LoginPage login=new LoginPage(driver);
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String uemail =jsonuobj.get("email").toString();
        String pw= jsonuobj.get("password").toString();
        login.doLogin(uemail,"12345");
        Thread.sleep(1000);
    }


    @Test(priority = 1,description = "Update Email")
    public void doUpdate() throws InterruptedException, IOException, ParseException {
        UpdateEmailPage updateEmail=new UpdateEmailPage(driver);

        Faker fake=new Faker();
        int randmailID= Utils.generateRandnum(1,100);
        String email="tahmid16+"+randmailID+"@gmail.com";
        updateEmail.doUpdate(email);
        Utils uEmail=new Utils();
        uEmail.saveUpdateEmail(email);

        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);

    }
    @Test(priority = 2,description = "Logout After Update Email")
    public void logout() throws InterruptedException, IOException, ParseException {
        UpdateEmailPage lout=new UpdateEmailPage(driver);
        Thread.sleep(5000);
        lout.btnGet.get(0).click();
        lout.menuItem.get(1).click();

    }
    @Test(priority = 3,description = "Login with update EMail")
    public void doLoginAfterUpdate() throws IOException, ParseException, InterruptedException {

        Thread.sleep(2000);
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);

        JSONParser jsonParser2 = new JSONParser();
        JSONArray emailJsonArray = (JSONArray) jsonParser2.parse(new FileReader("./src/test/resources/updateEmail.json"));
        JSONObject emailJsonObj = (JSONObject) emailJsonArray.get(emailJsonArray.size() - 1);
        String uemail = emailJsonObj.get("email").toString();


        String pw= jsonuobj.get("password").toString();
        driver.findElement(By.id("email")).sendKeys(uemail);
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.cssSelector("[type='submit']")).click();

        String actualH2= driver.findElement(By.tagName("h2")).getText();
        String acceptedH2="User Daily Costs";
        Assert.assertTrue(actualH2.contains(acceptedH2));

    }
      @Test(priority = 4,description = "Logput After update Email login")
    public void logoutAfterLogin() throws InterruptedException, IOException, ParseException {
        UpdateEmailPage lout = new UpdateEmailPage(driver);
        Thread.sleep(5000);
        lout.btnGet.get(0).click();
        lout.menuItem.get(1).click();
    }


   @Test(priority = 5,description = "Login with Before Update Email")
    public void doLoginBeforeUpdateEMail() throws IOException, ParseException, InterruptedException {
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);
        String uemail =jsonuobj.get("email").toString();
        String pw= jsonuobj.get("password").toString();
        driver.findElement(By.id("email")).sendKeys(uemail);
        driver.findElement(By.id("password")).sendKeys(pw);
        driver.findElement(By.cssSelector("[type='submit']")).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.tagName("p")).isDisplayed());


    }



}
