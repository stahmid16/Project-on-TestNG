package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {


    public static int generateRandnum(int min, int max){
        double randId= Math.random()*(max-min)+min;
        return (int) randId;

    }

 public void saveData(String email,String password) throws IOException, ParseException {
     String path= "./src/test/resources/user.json";
     JSONParser jsonParser=new JSONParser();
     JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
     JSONObject userjsonObject=new JSONObject();
     userjsonObject.put("email",email);
     userjsonObject.put("password",password);
     jsonArray.add(userjsonObject);
     FileWriter writer=new FileWriter(path);
     writer.write(jsonArray.toJSONString());
     writer.flush();
     writer.close();

 }



    public void saveUpdateEmail(String email) throws IOException, ParseException {
        String path= "./src/test/resources/updateEmail.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray=(JSONArray) jsonParser.parse(new FileReader(path));
        JSONObject userjsonObject=new JSONObject();
        userjsonObject.put("email",email);
        jsonArray.add(userjsonObject);
        FileWriter writer=new FileWriter(path);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();

    }

}
