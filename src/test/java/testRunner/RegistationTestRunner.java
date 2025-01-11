package testRunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RegistationPage;
import setup.Setup;
import utils.Utils;
import utils.UtilsReadMail;

import java.io.IOException;

public class RegistationTestRunner extends Setup {

    @Test
    public void doRegistration() throws IOException, ParseException, InterruptedException {
        RegistationPage registrationPage = new RegistationPage(driver);
        Faker fake = new Faker();
        driver.findElement(By.partialLinkText("Register")).click();
        String fName = fake.name().firstName();
        int randmailID = Utils.generateRandnum(1, 100);
        String email = "tahmid16+" + randmailID + "@gmail.com";
        String password = "1234";
        String phnNumber = "017" + Utils.generateRandnum(10000000, 99999999);
        String address = fake.address().streetAddress();

        registrationPage.doRegistation(fName, email, password, phnNumber, address);

        Utils utils = new Utils();
        utils.saveData(email, password);
        Thread.sleep(20000);

        UtilsReadMail readMail = new UtilsReadMail();
        readMail.getMailread();
        String mailContent = readMail.readMail();
        System.out.println("Original Mail Content: " + mailContent);

        // Decode HTML-encoded content in mailContent
        mailContent = mailContent.replace("&#39;", "'");

        // Define the expected message
        String acceptedMsg = "Dear " + fName + ", Welcome to our platform! We're excited to have you onboard. Best regards, Road to Career";

        // Assert that mailContent contains the expected message
        Assert.assertTrue(mailContent.contains(acceptedMsg), "Email content does not match the expected message.");

        String successMessage = "Congratulations email is received";
        Assert.assertEquals(successMessage, "Congratulations email is received");

        System.out.println(successMessage);
    }


}
