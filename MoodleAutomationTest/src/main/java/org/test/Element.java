package org.test;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;


public class Element {
    //System.setProperty("webdriver.chrome.driver","C:\\Users\\d00ne\\OneDrive\\Área de Trabalho\\Unipampa\\chromedriver.exe");
    private static final String URL = "https://gmlunardi.pro.br/moodlerp2/login/index.php";
    JSONObject objeto;
    JSONObject objeto2;


    public Element(){

        try {

            JSONParser jsonParser = new JSONParser();
            FileReader arquivo = new FileReader("C:\\Users\\d00ne\\IdeaProjects\\MoodleAutomationTest\\src\\main\\java\\org\\data\\file.json");
            FileReader arquivo2 = new FileReader("C:\\Users\\d00ne\\IdeaProjects\\MoodleAutomationTest\\src\\main\\java\\org\\data\\data.json");
            Object objetoJson =  jsonParser.parse(arquivo);
            Object objetoJson2 =  jsonParser.parse(arquivo2);

            objeto = new JSONObject(objetoJson.toString());
            objeto2 = new JSONObject(objetoJson2.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    @BeforeEach
    public void setTopicName(){
        WebDriver driver = new ChromeDriver();
        driver.get(this.URL);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys(objeto.getString("usuario"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys(objeto.getString("senha"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
        button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://gmlunardi.pro.br/moodlerp2/my/courses.php");
        WebElement courseName = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div[1]/div/div"));
        courseName.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement menuOn = driver.findElement(By.className("custom-control-input"));
        menuOn.click();
        WebElement configurationTopic = driver.findElement(By.xpath("//*[@id=\"action-menu-toggle-3\"]"));
        configurationTopic.click();
        WebElement editTopicName = driver.findElement(By.xpath("//*[@id=\"actionmenuaction-8\"]"));
        editTopicName.click();
        WebElement personalizado = driver.findElement(By.xpath("//*[@id=\"id_name_customize\"]"));
        personalizado.click();
        WebElement inputTopic = driver.findElement(By.xpath("//*[@id=\"id_name_value\"]"));
        inputTopic.click();
        inputTopic.sendKeys(objeto.getString("topico"));

        WebElement saveChangesTopico = driver.findElement(By.xpath("//*[@id=\"fgroup_id_buttonar\"]/div[2]/fieldset/div/div[1]/span"));
        saveChangesTopico.click();
        //driver.quit();
    }

    //O teste objetifica testar se o conteúdo do tópico é mesmo vindo do arquivo.
    @Test
    public void TR001(){
        this.setTopicName();
        WebDriver driver = new ChromeDriver();

        driver.get(this.URL);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys(objeto.getString("usuario"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys(objeto.getString("senha"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
        button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://gmlunardi.pro.br/moodlerp2/my/courses.php");
        WebElement courseName = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div[1]/div/div"));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        courseName.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement menuOn = driver.findElement(By.className("custom-control-input"));
        menuOn.click();
        WebElement configurationTopicc = driver.findElement(By.xpath("//*[@id=\"action-menu-toggle-3\"]"));
        configurationTopicc.click();
        WebElement editTopicNamee = driver.findElement(By.xpath("//*[@id=\"actionmenuaction-8\"]"));
        editTopicNamee.click();
        WebElement inputTopicc = driver.findElement(By.id("id_name_value"));
        inputTopicc.click();
        String valorTopic = inputTopicc.getAttribute("value");
        WebElement cancelTopico = driver.findElement(By.xpath("//*[@id=\"fgroup_id_buttonar\"]/div[2]/fieldset/div/div[2]/span"));
        cancelTopico.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        System.out.println(valorTopic);
        Assert.assertEquals(objeto.getString("topico"),valorTopic);
        driver.close();
        driver.quit();

    }

    @Test
    public void TR002(){

        WebDriver driver = new ChromeDriver();
        driver.get(this.URL);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys(objeto.getString("usuario"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys(objeto.getString("senha"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
        button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://gmlunardi.pro.br/moodlerp2/my/courses.php");
        WebElement courseName = driver.findElement(By.xpath("//*[@id=\"course-info-container-8-3\"]/div/div/a/span[3]"));
        courseName.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement menuOn = driver.findElement(By.className("custom-control-input"));
        menuOn.click();

        WebElement configurationRotulo = driver.findElement(By.xpath("//*[@id=\"action-menu-toggle-5\"]"));
        configurationRotulo.click();
        WebElement editRotulo = driver.findElement(By.xpath("//*[@id=\"actionmenuaction-20\"]"));
        editRotulo.click();

        WebElement inputRotulo = driver.findElement(By.xpath("//*[@id=\"id_introeditoreditable\"]"));
        inputRotulo.click();
        inputRotulo.sendKeys(objeto.getString("rotulo"));
        WebElement saveChangesRotulo = driver.findElement(By.xpath("//*[@id=\"id_submitbutton2\"]"));
        saveChangesRotulo.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement configurationRotuloo = driver.findElement(By.xpath("//*[@id=\"action-menu-toggle-5\"]"));
        configurationRotuloo.click();
        WebElement editRotuloo = driver.findElement(By.xpath("//*[@id=\"actionmenuaction-20\"]"));
        editRotuloo.click();
        WebElement inputRotuloo = driver.findElement(By.xpath("//*[@id=\"id_introeditoreditable\"]"));
        inputRotuloo.click();
        String valorRotulo = inputRotuloo.getText();
        WebElement cancelRotulo = driver.findElement(By.xpath("//*[@id=\"id_cancel\"]"));
        cancelRotulo.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        Assert.assertEquals(objeto.getString("rotulo"),valorRotulo);
    }

    @Test
    public void TR003(){
        WebDriver driver = new ChromeDriver();
        driver.get(this.URL);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys(objeto.getString("usuario"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys(objeto.getString("senha"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
        button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://gmlunardi.pro.br/moodlerp2/my/courses.php");
        WebElement courseName = driver.findElement(By.xpath("//*[@id=\"course-info-container-8-3\"]/div/div/a/span[3]"));
        courseName.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement menuOn = driver.findElement(By.className("custom-control-input"));
        menuOn.click();

        WebElement configurationRotulo = driver.findElement(By.xpath("//*[@id=\"action-menu-toggle-5\"]"));
        configurationRotulo.click();
        WebElement editRotulo = driver.findElement(By.xpath("//*[@id=\"actionmenuaction-20\"]"));
        editRotulo.click();

        WebElement restringirAcess = driver.findElement(By.xpath("//*[@id=\"collapseElement-2\"]"));
        restringirAcess.click();

        WebElement dominio = driver.findElement(By.className("form-control"));
        String dominioEmail = dominio.getAttribute("value");
        WebElement cancelRestricao = driver.findElement(By.xpath("//*[@id=\"id_cancel\"]"));
        cancelRestricao.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        Assert.assertEquals(objeto2.getString("dominio"),dominioEmail);

    }
    @Test
    public void TR004(){
        WebDriver driver = new ChromeDriver();

        driver.get(this.URL);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys(objeto.getString("usuario"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys(objeto.getString("senha"));
        WebElement button = driver.findElement(By.xpath("//*[@id=\"loginbtn\"]"));
        button.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get("https://gmlunardi.pro.br/moodlerp2/my/courses.php");
        WebElement courseName = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div[1]/div/div"));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        courseName.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement menuOn = driver.findElement(By.className("custom-control-input"));
        menuOn.click();
        WebElement configurationTopicc = driver.findElement(By.xpath("//*[@id=\"action-menu-toggle-3\"]"));
        configurationTopicc.click();
        WebElement editTopicNamee = driver.findElement(By.xpath("//*[@id=\"actionmenuaction-8\"]"));
        editTopicNamee.click();
        WebElement inputTopicc = driver.findElement(By.id("id_name_value"));
        inputTopicc.click();
        String valorTopic = inputTopicc.getAttribute("value");
        WebElement cancelTopico = driver.findElement(By.xpath("//*[@id=\"fgroup_id_buttonar\"]/div[2]/fieldset/div/div[2]/span"));
        cancelTopico.click();
        int caracteresMax = 255;
        Assert.assertTrue(valorTopic.length()<=caracteresMax);
    }




}
