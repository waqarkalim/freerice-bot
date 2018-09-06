import org.apache.commons.io.FileUtils; 
import java.awt.Desktop;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class freerice {

    public static void main(String[] args) throws Exception{

        WebElement finalElement = null;

        int count = 0;
        int totalScore = 0;

        String solution = "";

        // String url="http://freerice.com/#/basic-math-pre-algebra/16851";
        String url = "http://freerice.com/#/multiplication-table/17513";

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        System.out.println("Arguments added...");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);

        while (true) {

            count = count + 1;

            boolean solved = false;

            WebDriverWait wait = new WebDriverWait(driver, 15);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"question-title\"]")));

            WebElement questionElement = driver.findElement(By.xpath("//*[@id=\"question-title\"]"));
            String questionText = questionElement.getText();
            System.out.println(questionElement.getText());

            String[] questionSplit = questionText.split("\\s+");

            try {


                String numberOne = questionSplit[0];
                String operator = questionSplit[1];
                String numberTwo = questionSplit[2];

                if (numberOne.startsWith(".")) {
                    numberOne = "0";
                    operator = "+";
                    numberTwo = "0";
                }

                if (numberOne.contains("/")) {
                    String[] numberOneSplit = numberOne.split("/");
                    numberOne = Integer.toString(Integer.parseInt(numberOneSplit[0]) / Integer.parseInt(numberOneSplit[1]));
                }

                if (numberTwo.contains("/")) {
                    String[] numberTwoSplit = numberTwo.split("/");
                    numberTwo = Integer.toString(Integer.parseInt(numberTwoSplit[0]) / Integer.parseInt(numberTwoSplit[1]));
                }

                try {

                    if (operator.equals("+")) {
                        solution = Integer.toString(Integer.parseInt(numberOne) + Integer.parseInt(numberTwo));
                    } else if (operator.equals("-")) {
                        solution = Integer.toString(Integer.parseInt(numberOne) - Integer.parseInt(numberTwo));
                    } else if (operator.equals("x")) {
                        solution = Integer.toString(Integer.parseInt(numberOne) * Integer.parseInt(numberTwo));
                    } else if (operator.equals("/")) {
                        solution = Integer.toString(Integer.parseInt(numberOne) / Integer.parseInt(numberTwo));
                    } else {
                        solution = "0";
                    }
                } catch (NumberFormatException e) {
                    solution = "0";
                }

                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                System.out.println("The answer is " + solution);

            } catch (NumberFormatException e) {

                solution = "0";
                solved = false;

            } catch (ArrayIndexOutOfBoundsException e) {
                solution = "0";
                solved = false;
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='answer-item']")));

            List<WebElement> elementList = (driver.findElements(By.cssSelector("a[class='answer-item']")));

            // *** To click ads ***
            //
            // if ((count % 10) == 0) {
            //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"er_ban16_125688900466166959\"]/a/img")));

            //     WebElement link  = (driver.findElement(By.xpath("//*[@id=\"er_ban16_125688900466166959\"]/a/img")));
            //     Actions builder = new Actions(driver);
            //     Action openLinkInNewTab = builder
            //         .moveToElement(link)
            //         .sendKeys(link, Keys.CONTROL)
            //         .click(link)
            //         .keyUp(Keys.CONTROL)
            //         .build();

            //     openLinkInNewTab.perform();
            //     System.out.println("Ad clicked");
            // }

            for (WebElement el: elementList){
                if ((count % 10) == 0) {
                    if (!(solution.equals(el.getText()))) {
                        System.out.println("  " + el.getText() + " *");
                        finalElement = el;
                        solved = true;
                    } else {
                        System.out.println("  " + el.getText());
                    }
                } else {
                    if (solution.equals(el.getText())) {
                        System.out.println("  " + el.getText() + " *");
                        finalElement = el;
                        solved = true;
                    } else {
                        System.out.println("  " + el.getText());
                    }
                }
            }


            if (solved) {
                // totalScore = totalScore + 10;
                // System.out.println("Total score is " + Integer.toString(totalScore) + "\n------------------------------------");
                finalElement.click();
            } else {
                (elementList.get(0)).click();
            }


            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"game-status-right\"]")));

            WebElement scoreElement  = (driver.findElement(By.xpath("//*[@id=\"game-status-right\"]")));

            System.out.println(scoreElement.getText() + "\n------------------------------------");

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

        }


    }    

}


