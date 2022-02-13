package ui.automation.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.automation.blazedemo.browser.DriverManager;

/**
 * This class used for Homepage/Login page of Blazedemo
 * @author sunit
 *
 */
public class HomePage extends BasePage {

  
  @FindBy(xpath="//*[@id='email']")
  private WebElement emailAdress;
  @FindBy(xpath="//*[@id='password']")
  private WebElement pwd;
  
  public HomePage() {
	  PageFactory.initElements(DriverManager.getDriver(), this);
  }
}
