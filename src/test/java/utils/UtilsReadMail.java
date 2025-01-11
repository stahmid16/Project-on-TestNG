package utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UtilsReadMail {
    String mailTOken="ya29.a0ARW5m74NH73j8OhgbcekoF_j5FZFPjkzHiEOmDp6bVX2JEJUTDTcRx_e5rGp_aJ8nCbi1ZbIous2vVc5DA7FrjKt-4HNQw0oUvzI3QhbOr4ZbnQr2nq39-FvK-T9noz7-joj7mPMH4pe7wAPsCGFijBPs_eg6d7vNMf9hC7YaCgYKAbQSARISFQHGX2MiYAXeH9zpupl0XBuuHmDBAQ0175";


    public void getMailread() throws IOException {
        String token=mailTOken;

        RestAssured.baseURI="https://gmail.googleapis.com";
        Response response=given().contentType("application/json").header("Authorization","Bearer "+token)
                .when().get("/gmail/v1/users/me/messages");
        //System.out.println(response.asString());
        JsonPath jsonPath=response.jsonPath();
        String mailId= jsonPath.get("messages[0].id");
        //System.out.println(mailId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mailId", mailId);

        FileWriter fileWriter = new FileWriter("./src/test/resources/mailId.json");
        fileWriter.write(jsonObject.toString()); // Format the JSON output with an indentation of 4 spaces
        fileWriter.close();
    }



    public String readMail() throws IOException, ParseException {
        // Parse the JSON file as a JSONObject
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("./src/test/resources/mailId.json"));

        // Extract the mailId
        String emailId = jsonObject.get("mailId").toString();
        //System.out.println("Mail ID: " + emailId);

        // Use the mailId in your API request
        String token =mailTOken;

        RestAssured.baseURI = "https://gmail.googleapis.com";
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/gmail/v1/users/me/messages/" + emailId);

        //System.out.println("Response: " + response.asString());

        JsonPath jsonPath=response.jsonPath();
          String mailBody= jsonPath.get("snippet");
        //System.out.println(mailBody);
        return mailBody;
    }



}
