package testRunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import page.RandomTwoIteamPage;
import setup.Setup;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RandomIteamTestRunner extends Setup {
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
@Test(priority = 1,description = "Fill up all field")
public void addIteam() throws InterruptedException {
    Utils uti=new Utils();
    driver.findElement(By.className("add-cost-button")).click();
    RandomTwoIteamPage iteam=new RandomTwoIteamPage(driver);
    Faker fake=new Faker();
    String iNmae=fake.name().nameWithMiddle();
    String iQuantity= String.valueOf(uti.generateRandnum(1,99));
    String iAmmount= String.valueOf(uti.generateRandnum(50,9999));
   String iRemark= fake.business().toString();

    iteam.doEntryIteam(iNmae,iQuantity,iAmmount,iRemark);
    Thread.sleep(3000);
    driver.switchTo().alert().accept();



}
  @Test(priority = 2,description = "only mendatory")
    public void addIteam2() throws InterruptedException {
        Utils uti=new Utils();
        driver.findElement(By.className("add-cost-button")).click();
        RandomTwoIteamPage iteam=new RandomTwoIteamPage(driver);
        Faker fake=new Faker();
        String iNmae=fake.name().nameWithMiddle();
        String iQuantity= String.valueOf(uti.generateRandnum(1,99));
        String iAmmount= String.valueOf(uti.generateRandnum(50,9999));
        //String iRemark= fake.business().toString();

        iteam.doEntryIteam(iNmae,iQuantity,iAmmount,"");
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.tagName("span")).isDisplayed());



    }

//    public void testAddIteamMultipleTimes() throws InterruptedException {
//        for (int i = 0; i < 2; i++) {
//            addIteam();
//        }
//    }
// @Test
//    public void mainTest() throws InterruptedException {
//    testAddIteamMultipleTimes();
//    Thread.sleep(2000);
//        Assert.assertTrue(driver.findElement(By.tagName("span")).isDisplayed());
//
//
//
//    }


}
