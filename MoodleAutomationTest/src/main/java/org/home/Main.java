package org.home;



import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.test.Element;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Object;

import java.io.FileReader;


public class Main {
    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\d00ne\\OneDrive\\Área de Trabalho\\Unipampa\\chromedriver.exe");
        try {

            JSONParser jsonParser = new JSONParser();
            FileReader arquivo = new FileReader("C:\\Users\\d00ne\\IdeaProjects\\MoodleAutomationTest\\src\\main\\java\\org\\data\\file.json");
            Object objetoJson =  jsonParser.parse(arquivo);

            JSONObject objeto = new JSONObject(objetoJson.toString());

            Element element = new Element();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

}