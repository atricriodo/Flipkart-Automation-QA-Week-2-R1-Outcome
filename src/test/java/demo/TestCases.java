package demo;
import demo.utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    
    WebDriver driver;

    @BeforeClass
    public void setup(){
        System.out.println("Constructor: Driver");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Successfully Created Driver");
    }

    @Test(priority = 0, description = "Search Washing Machine. Sort by popularity and print the count of items with rating less than or equal to 4 stars")
    public void testCase01() throws InterruptedException{
        System.out.println("Beginning Test Case 01");

        String sortByString = "Newest First";
        double starRating = 4.0;
        // Go to Flipkart
        driver.get("http://www.flipkart.com");
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        // Find the Search Box and Send Washing Machine as Text
        Boolean flow1Result = Utilities.sendKeysWrapper(driver, By.xpath("//button[@aria-label='Search for Products, Brands and More']/../div/input"), "Washing Machine");
        if(flow1Result){
            System.out.println("Flow 1 success");
        }
        else System.out.println("Failure in flow 1");
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        // Sort By Popularity
        Boolean flow2Result = Utilities.clickWrapper(driver, By.xpath("//span[normalize-space(text())='Sort By']/../div[text()='"+sortByString+"']"));
        if(flow2Result){
            System.out.println("Flow 2 success");
        }
        else System.out.println("Failure in flow 2");        
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        // Print the Number of Items with Rating Less than or Equal to 4 Stars
        ArrayList<String> flow3Result = Utilities.searchStarAndPrint(driver, By.xpath("//*[contains(text(), 'Ratings') and not(./*) and not(contains(text(), 'Customer'))]/../../../../div[1]"), By.xpath("//*[contains(text(), 'Ratings') and not(./*) and not(contains(text(), 'Customer'))]/../../../span[1]/div[1]"), starRating);
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        for(String res : flow3Result){
            System.out.println(res);
        }
        System.out.println("Ending Test Case 01");
    }

    @Test(priority = 1, description = "Search “iPhone”, print the Titles and discount % of items with more than 17% discount")
    public void testCase02() throws InterruptedException{
        System.out.println("Beginning Test Case 02");

        double discountPercent = 10.0;
        // Go to Flipkart
        driver.get("http://www.flipkart.com");
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        // Search iPhone
        Boolean flow1Result = Utilities.sendKeysWrapper(driver, By.xpath("//button[@aria-label='Search for Products, Brands and More']/../div/input"), "iPhone");
        if(flow1Result){
            System.out.println("Flow 1 success");
        }
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        // Print titles with more than 17% Discount
        ArrayList<String> flow2Result = Utilities.searchStarAndPrint(driver, By.xpath("//*[contains(text(), 'Ratings') and not(./*) and not(contains(text(), 'Customer'))]/../../../../div[1]"), By.xpath("//span[contains(., '% off')]"), discountPercent);
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        for(String res : flow2Result){
            System.out.println(res);
        }
        System.out.println("Ending Test Case 02");   
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
    }

    @Test(priority = 3, description = "Search “Coffee Mug”, select 4 stars and above, and print the Title and image URL of the 5 items with highest number of reviews")
    public void testCase03() throws InterruptedException{
        System.out.println("Beginning Test Case 03");
        driver.get("http://www.flipkart.com");
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        Utilities.sendKeysWrapper(driver, By.xpath("//button[@aria-label='Search for Products, Brands and More']/../div/input"), "Coffee Mug");
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        Utilities.clickWrapper(driver, By.xpath("//div[text()='4★ & above']//ancestor::div[1]//div[1]"));
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        List<String> result = Utilities.searchHighestReview(driver, By.xpath("//a[@rel='noopener noreferrer'][2]"), By.xpath("//a[@rel='noopener noreferrer'][2]/../div[3]//span[2]"));
        for(String s: result){
            System.out.println(s);
        }
        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        System.out.println("Ending Test Case 03");
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Destroying Driver Instance");
        driver.close();
        driver.quit();
        System.out.println("Successfully Destroyed Driver");
    }
}
