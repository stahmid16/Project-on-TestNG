package testRunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ResetPage;
import setup.Setup;
import utils.UtilsReadMail;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetTestRunner extends Setup {

    @Test(priority = 1,description = "Wrong email")
    public void doReset() throws InterruptedException {
        ResetPage reset = new ResetPage(driver);

        // Ensure page is ready for reset
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resetLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Reset")));
        resetLink.click();

        reset.doReset("tahmid14@gmail.com");
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.tagName("p")).isDisplayed());


    }

    @Test(priority = 2, description = "Invalid Email")
    public void doReset_2() throws InterruptedException {
        ResetPage reset = new ResetPage(driver);

        WebElement inputField = driver.findElement(By.cssSelector("input[type='email']"));
        //inputField.click();
        inputField.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        Thread.sleep(1000);

        reset.doReset("tahmid14@");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String msg = driver.findElement(By.cssSelector("input[type='email']")).getAttribute("validationMessage");
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));
        String getMsg = emailInput.getAttribute("validationMessage");

        String acceptedMsg = "Please enter a part following '@'. 'tahmid14@' is incomplete.";
        Assert.assertTrue(getMsg.contains(acceptedMsg));

        driver.navigate().refresh();


    }





    @Test(priority = 3,description = "Correct Email")
    public void doReset_3() throws InterruptedException, IOException, ParseException {
        ResetPage reset = new ResetPage(driver);


        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement resetLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Reset")));
        //resetLink.click();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String uemail = jsonObj.get("email").toString();
        reset.doReset(uemail);
        UtilsReadMail readMail = new UtilsReadMail();
        Thread.sleep(10000);
        readMail.getMailread();
        String mailContent = readMail.readMail();
        String urlRegex = "(https?://[\\w.-]+(?:/\\S*)?)";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(mailContent);
        String extractedLink = null;
        if (matcher.find()) {
            extractedLink = matcher.group(1);
        }
        //System.out.println(extractedLink);
        String resetPasswordLink = extractedLink;

        driver.get(resetPasswordLink);
        List<WebElement> passField = driver.findElements(By.cssSelector("input[type='password']"));
        passField.get(0).sendKeys("12345");
        passField.get(1).sendKeys("12345");
        driver.findElement(By.cssSelector("[type='submit']")).click();

        Thread.sleep(1000);

    }

  @Test(priority = 4,description = "Ensure Login succesfull After reset Password")
    public void doLoginAfterReset() throws InterruptedException, IOException, ParseException {
        Thread.sleep(1000);
      JSONParser jsonParser=new JSONParser();
      JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader("./src/test/resources/user.json"));
      JSONObject jsonuobj= (JSONObject) jsonArray.get(jsonArray.size()-1);
      String uemail =jsonuobj.get("email").toString();
      driver.findElement(By.id("email")).sendKeys(uemail);
      driver.findElement(By.id("password")).sendKeys("12345");
      driver.findElement(By.cssSelector("[type='submit']")).click();

    }



}
