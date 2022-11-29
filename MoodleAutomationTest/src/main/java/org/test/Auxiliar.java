package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Auxiliar {

    public boolean arquivoTipoTexto(WebDriver driver){
        WebElement tarefa = driver.findElement(By.xpath("//li[4]/div/div/div/div/div/div[2]/div[2]/a"));
        tarefa.click();
        WebElement addAtividade = driver.findElement(By.xpath("//button[contains(.,'Adicionar envio')]"));
        addAtividade.click();
        try{
            WebElement texto = driver.findElement(By.id("id_onlinetext_editoreditable"));
            texto.click();
            return true;
        }catch (Exception e ){
            return false;
        }

    }
    // se existir o botão de "Adicionar Envio" retorna true
    public boolean addEnvio(WebDriver driver){
        WebElement tarefa = driver.findElement(By.xpath("//li[4]/div/div/div/div/div/div[2]/div[2]/a"));
        tarefa.click();
        try{
            WebElement addAtividade = driver.findElement(By.xpath("//button[contains(.,'Adicionar envio')]"));
            addAtividade.click();
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public void envioAtividade(WebDriver driver){
        WebElement tarefa = driver.findElement(By.xpath("//li[4]/div/div/div/div/div/div[2]/div[2]/a"));
        tarefa.click();
        WebElement addAtividade = driver.findElement(By.xpath("//button[contains(.,'Adicionar envio')]"));
        addAtividade.click();
        WebElement arrastarAtividade = driver.findElement(By.xpath("//div[4]/div/div[2]/div/div"));
        arrastarAtividade.click();
        WebElement escolherArquivo = driver.findElement(By.xpath("//p[contains(.,'Relatorio II - marco 02 (1).pdf')]"));
        escolherArquivo.click();
        WebElement arquivoEscolhido = driver.findElement(By.xpath("//button[contains(.,'Selecione este arquivo')]"));
        arquivoEscolhido.click();

        try {
            WebElement salvar = driver.findElement(By.name("submitbutton"));
            salvar.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.name("submitbutton")));

        }

    }
    public void mudarPapel(WebDriver driver,String papel){
        WebElement user = driver.findElement(By.id("user-menu-toggle"));
        user.click();
        WebElement mudarPapel = driver.findElement(By.xpath("//a[contains(text(),'Mudar papel para...')]"));
        mudarPapel.click();
        if(papel.equals("Estudante")){
            WebElement aluno = driver.findElement(By.xpath("//div[3]/div/form/button"));
            aluno.click();
        }

    }

    public void ativarModoEdicao(WebDriver driver){
        WebElement menuOn = driver.findElement(By.className("custom-control-input"));
        menuOn.click();
    }
    public void configCriarAtividade(WebDriver driver, String limite){
        WebElement configuracoes = driver.findElement(By.xpath("//a[@id='action-menu-toggle-9']/i"));
        configuracoes.click();
        WebElement editConf = driver.findElement(By.xpath("//div[@id='action-menu-9-menu']/a/span"));
        editConf.click();
        WebElement habilitarLimitePalavras = driver.findElement(By.xpath("//input[@id='id_assignsubmission_onlinetext_wordlimit_enabled']"));
        habilitarLimitePalavras.click();
        WebElement limitePalavras = driver.findElement(By.xpath("//input[@id='id_assignsubmission_onlinetext_wordlimit']"));
        limitePalavras.click();
        limitePalavras.sendKeys(limite);
        WebElement salvar = driver.findElement(By.xpath("//div[4]/div[2]/fieldset/div/div/span/input"));
        salvar.click();
    }
    // clica em marcar como feito. A tarefa passa a estar concluida
    public void concluirTarefa(WebDriver driver){
        WebElement concluido = driver.findElement(By.xpath("//li[4]/div/div/div/div[2]/div/div/button"));
        if(concluido.getText().equals("Marcar como feito")){
            concluido.click();
        }
    }




}
