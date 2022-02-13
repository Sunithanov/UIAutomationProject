package ui.automation.blazedemo.pages;

import java.io.File;
//import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.automation.blazedemo.utils.*;
import com.automation.blazedemo.browser.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class BasePage {

  protected WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public SeleniumUtils seleniumUtil = new SeleniumUtils();
  
	
  protected BasePage(){
		PageFactory.initElements(DriverManager.getDriver(), this);
		this.driver = DriverManager.getDriver();
	}
  
	/*
	 * public BasePage(WebDriver driver) { this.driver = DriverManager.getDriver();
	 * PageFactory.initElements(driver, this); }
	 */

  public String getTitle() {
    return driver.getTitle();
  }
  /**
   * 
   * @param format
   * @param args
   * @return
   */
  	public List<WebElement> getElements(String format, Object... args) {

  		List<WebElement> elements = new ArrayList<WebElement>();
  		String str = String.format(format, args);

  		for (int i = 0; i <= 1; i++) {
  			try {
  				if (driver.findElements(By.xpath(str)).size() != 0) {

  					elements = driver.findElements(By.xpath(str));

  					return elements;
  				}
  			} catch (Exception e) {
  			}
  		}

  		return elements;

  	}
  /**
   * @author ssiddara
   * @param format
   * @param args
   * @return
   */
  	public WebElement getElement(String format, Object... args) {

  		WebElement element = null;
  		String str = String.format(format, args);
  		for (int i = 0; i <= 3; i++) {
  			try {
  				System.out.println("driver=" + driver);
  				if (driver.findElements(By.xpath(str)).size() != 0) {
  					element = driver.findElement(By.xpath(str));

  					return element;
  				}
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
  		if (element == null) {
  			try {
  				// TestReporter.assertFail("The required element '" + str + "' is not present");

  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		}
  		return element;

  	}
  	/**
  	 * @author ssiddara
  	 * @param format
  	 * @param args
  	 * @return
  	 */

  	public WebElement getElementWithoutError(String format, Object... args) {

  		WebElement element = null;
  		String str = String.format(format, args);

  		for (int i = 0; i < 2; i++) {
  			try {

  				if (driver.findElements(By.xpath(str)).size() != 0) {
  					// WaitAllegis.waitUntilElementVisible(driver,
  					// driver.findElement(By.xpath(str)));

  					element = driver.findElement(By.xpath(str));

  					return element;
  				}
  			} catch (Exception e) {
  			}
  		}
  		if (element == null) {
  			try {
  				// TestReporter.log("The required element '" + str + "' is not present");

  			} catch (Exception e) {
  			}
  		}
  		return element;

  	}

  	/**
  	 * @author ssiddara
  	 * @param s
  	 * @return
  	 */
  	public String removeFirstChar(String s) {
  		return s.substring(1);
  	}
  	public static void logScreenshot(String testClassName, WebDriver driver) {
    	
    	String destDir = String.format("%s%s%s%s",Constants.SCREENSHOT_FOLDER, Constants.DIR_SEPARATOR, testClassName,Constants.DIR_SEPARATOR);
        new File(destDir).mkdirs();
    
        String fileName     = UUID.randomUUID() + ".png";
        String fileLocation = destDir + fileName;
                try {
            FileUtils.writeByteArrayToFile(new File(fileLocation), ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

            
        } catch (Exception e) {
          
            e.printStackTrace();
            
           
        }
    }
  	
}
