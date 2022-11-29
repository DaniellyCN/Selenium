package org.test;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Supplier;

import javax.xml.xpath.XPath;
public class CorrecaoAtividade {

    private static final String URL = "https://gmlunardi.pro.br/moodlerp2/login/index.php";
    private JSONObject objeto;
    private WebDriver driver = new ChromeDriver();

    Auxiliar auxiliar = new Auxiliar();

    @BeforeEach
    public void loginSystem() throws Exception{

        try {
            JSONParser jsonParser = new JSONParser();
            FileReader arquivo = new FileReader("C:\\Users\\d00ne\\IdeaProjects\\MoodleAutomationTest\\src\\main\\java\\org\\data\\file.json");
            Object objetoJson =  jsonParser.parse(arquivo);

            objeto = new JSONObject(objetoJson.toString());

            JSONObject data = (JSONObject) objeto.get("login");
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            username.sendKeys(data.get("usuario").toString());
            WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            password.sendKeys(data.get("senha").toString());
            WebElement button = driver.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
            button.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://gmlunardi.pro.br/moodlerp2/my/courses.php");
            WebElement course = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div[1]/div/div"));
            course.click();
            WebElement courseName = driver.findElement(By.xpath("//div[@id='course-info-container-8-3']/div/div/a/span[3]"));
            courseName.click();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    //Veriifica se todos matriculados enviaram a atividade
    @Test
    public void todosEnviaramTeste(WebDriver driver){
    WebElement atividade = driver.findElement(By.xpath("//li[5]/div/div/div/div/div/div[2]/div[2]/a"));
    atividade.click();
    String participantes = driver.findElement(By.xpath("//tr[2]/td")).getText();
    String enviado = driver.findElement(By.xpath("//tr[3]/td")).getText();
    Assertions.assertEquals(participantes,enviado,"Se todos matriculados enviaram a atividade a resposta é verdadeira");
    }



    @AfterEach
    public void tearDownTest() throws Exception {
        driver.quit();
    }
}
