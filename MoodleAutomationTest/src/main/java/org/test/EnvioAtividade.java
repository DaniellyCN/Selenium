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

public class EnvioAtividade {
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


    @Test
    public void enviouAtividadeTest(){
        JSONObject data = (JSONObject) objeto.get("envioAtividade");
        auxiliar.mudarPapel(this.driver,"Estudante");
        boolean verify = true;
        try{
            auxiliar.envioAtividade(this.driver);
            Assertions.assertTrue(verify, "O aluno ainda não submeteu a atividade.");
        }catch (Exception e){
            verify = false;
            Assertions.assertFalse(verify, "O aluno já submeteu a atividade");
        }
    }

    @Test
    public void tipoEnvioAtividadeTest(){
        auxiliar.mudarPapel(this.driver,"Estudante");
        Assertions.assertTrue(auxiliar.arquivoTipoTexto(this.driver),"A atividade exige o envio de um texto.");
    }

    @Test
    public void excedeLimitePalavras(){
        JSONObject data = (JSONObject) objeto.get("envioAtividade");
        auxiliar.ativarModoEdicao(this.driver);
        String[] qtdePalavras = data.get("texto").toString().split(" ");
        boolean expectedValue = qtdePalavras.length <= Integer.parseInt(data.get("limitePalavras").toString());
        Assertions.assertTrue(expectedValue);
    }

    @Test
    public void possivelBugMarcarComoFeito(){
        //importante notar que deve iniciar com a tarefa em "marcar como feita"
        auxiliar.mudarPapel(this.driver,"Estudante");
        auxiliar.concluirTarefa(this.driver);
        boolean addEnvioIsTrue = auxiliar.addEnvio(this.driver); //true
        String tarefaConcluida = driver.findElement(By.xpath("//section/div/div/div/button")).getText();
        Assertions.assertEquals(!(addEnvioIsTrue),tarefaConcluida.equals("Concluído"),"Quando uma atividade está concluída não se pode adicionar mais envios");
    }

    @AfterEach
    public void tearDownTest() throws Exception {
        driver.quit();
    }

}
