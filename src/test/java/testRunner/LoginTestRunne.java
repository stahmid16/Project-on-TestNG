package testRunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LoginPage;
import setup.Setup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTestRunne extends Setup {


    public void doLogin() throws IOException, ParseException, InterruptedException {
         LoginPage logingPage =new LoginPage(driver);
     JSONParser jsonParser=new JSONParser();
     JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
     JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);
     String uemail =jsonuobj.get("email").toString();
     String pw= jsonuobj.get("password").toString();
         logingPage.doLogin(uemail,pw);

        //String actualH2= driver.findElement(By.tagName("h2")).getText();
        //String acceptedH2="";
        //Assert.assertTrue(actualH2.contains(acceptedH2));
      Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.tagName("p")).isDisplayed());



    }

    public void updateLogin() throws IOException, ParseException, InterruptedException {
        LoginPage logingPage =new LoginPage(driver);
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);

        JSONParser jsonParser2 = new JSONParser();
        JSONArray emailJsonArray = (JSONArray) jsonParser2.parse(new FileReader("./src/test/resources/updateEmail.json"));
        JSONObject emailJsonObj = (JSONObject) emailJsonArray.get(emailJsonArray.size() - 1);
        String uemail = emailJsonObj.get("email").toString();


        String pw= jsonuobj.get("password").toString();
        logingPage.doLogin(uemail,pw);

    }
        @Test
     public void doAdminLogin() throws InterruptedException, IOException, ParseException {
        LoginPage logingPage =new LoginPage(driver);
        logingPage.doLogin("admin@test.com","admin123");
            //logingPage.doLogin(System.getProperty("email"), System.getProperty("password"));
            String actualH2= driver.findElement(By.tagName("h2")).getText();
        String acceptedH2="Admin Dashboard";
        Assert.assertTrue(actualH2.contains(acceptedH2));
        Assert.assertTrue(driver.findElement(By.className("total-count")).isDisplayed());
            Thread.sleep(1000);
            JSONParser jsonParser2 = new JSONParser();
            JSONArray emailJsonArray = (JSONArray) jsonParser2.parse(new FileReader("./src/test/resources/updateEmail.json"));
            JSONObject emailJsonObj = (JSONObject) emailJsonArray.get(emailJsonArray.size() - 1);
            String uemail = emailJsonObj.get("email").toString();
            driver.findElement(By.cssSelector("input[type='text']")).sendKeys(uemail);
            List<WebElement> alltd = driver.findElements(By.tagName("td"));
            String tableMail= alltd.get(2).getText();
            Assert.assertTrue(tableMail.contains(uemail));


    }


}
