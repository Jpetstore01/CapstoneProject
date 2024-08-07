package com.capstone_project1;

//import java.io.File;
import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class accountdetails extends baseclass { 

    @Test // Marks this method as a test case
    public void testLoginAndViewAccountDetails() throws IOException, InterruptedException { // Test method that can throw IOException and InterruptedException
        WebElement signInLink = driver.findElement(By.linkText("Sign In")); // Finds the "Sign In" link element
        signInLink.click(); // Clicks on the "Sign In" link
        test.log(Status.INFO, "Clicked on Sign In link"); // Logs the action

        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[1]/input")); // Finds the username input field
        usernameField.clear(); // Clears the input field
        usernameField.sendKeys("Rodrieth"); // Enters the username
        test.log(Status.INFO, "Entered username"); // Logs the action

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/label[2]/input")); // Finds the password input field
        passwordField.clear(); // Clears the input field
        passwordField.sendKeys("j2ee"); // Enters the password
        test.log(Status.INFO, "Entered password"); // Logs the action

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"Signon\"]/form/div/div/button")); // Finds the login button
        loginButton.click(); // Clicks the login button
        takeScreenshot("JPetStore_AfterLogin"); // Takes a screenshot
        test.log(Status.PASS, "Logged in successfully"); // Logs the action

        try { 
            WebElement myAccountLink = driver.findElement(By.linkText("My Account")); // Finds the "My Account" link element
            myAccountLink.click(); // Clicks on the "My Account" link
            Thread.sleep(2000); // Waits for 2 seconds
            test.log(Status.INFO, "Navigated to My Account page"); // Logs the action

            JavascriptExecutor js = (JavascriptExecutor) driver; // Casts the driver to JavascriptExecutor
            js.executeScript("window.scrollBy(0, 250)"); // Scrolls the window by 250 pixels
            js.executeScript("window.scrollBy(250, 450)"); // Scrolls the window by another 450 pixels
            takeScreenshot("JPetStores_AfterLogin"); // Takes a screenshot

            Thread.sleep(2000); // Waits for 2 seconds

            takeScreenshot("JPetStore_AccountDetails"); // Takes a screenshot
            test.log(Status.PASS, "Scrolled to account details section"); // Logs the action
        } catch (Exception e) { // Catches any exceptions
            test.log(Status.FAIL, "Failed to navigate to My Account page or scroll"); // Logs the failure
            test.fail(e); // Logs the exception
            return; // Exits the method
        }

        try { 
            WebElement accountDetailsHeader = driver.findElement(By.tagName("h3")); // Finds the header element with tag name "h3"
            Assert.assertEquals(accountDetailsHeader.getText(), "User Information", "Account details page is not displayed as expected."); // Asserts the header text
            test.log(Status.PASS, "Account details page is displayed correctly"); // Logs the action
        } catch (Exception e) { // Catches any exceptions
            test.log(Status.FAIL, "Account details page verification failed"); // Logs the failure
            test.fail(e); // Logs the exception
        }
    }
}
