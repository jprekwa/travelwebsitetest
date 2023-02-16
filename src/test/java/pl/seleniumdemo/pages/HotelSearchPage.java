package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {

   @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
   private WebElement searchHotelSpan;

   @FindBy(xpath = "//div[@id='select2-drop']//input")
   private WebElement searchHotelInput;

   @FindBy(name = "checkin")
   private WebElement checkInInput;

   @FindBy(name = "checkout")
   private WebElement checkOutInput;

   @FindBy(id = "travellersInput")
   private WebElement travellersInput;

   @FindBy(id = "adultPlusBtn")
   private WebElement adultPlusBtn;

   @FindBy(id = "childPlusBtn")
   private WebElement childPlusBtn;

   @FindBy(xpath = "//button[@class='btn btn-lg btn-block btn-primary pfb0 loader']")
   private WebElement searchButton;

   @FindBy(xpath = "//li[@id='li_myaccount']")
   private List<WebElement> myAccountLink;

   @FindBy(xpath = "//a[text()='  Sign Up']")
   private List<WebElement> signUpLink;

   private WebDriver driver;

   public HotelSearchPage(WebDriver driver){
       PageFactory.initElements(driver, this);
       this.driver = driver;
   }

   public void setCityName(String cityName){
       searchHotelSpan.click();
       searchHotelInput.sendKeys(cityName);
       String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
       driver.findElement(By.xpath(xpath)).click();
   }

   public void setDates(String checkInDate, String checkOutDate){
       checkInInput.sendKeys(checkInDate);
       checkOutInput.sendKeys(checkOutDate);
   }

   public void setTravellers(int adultsToAdd, int childToAdd){
       travellersInput.click();
       addTraveler(adultPlusBtn, adultsToAdd);
       addTraveler(childPlusBtn, childToAdd);

   }

   public void performSearch(){
       searchButton.click();
   }

   private void addTraveler(WebElement travellerButton, int numberOfTravellers){
       for(int i=0; i<numberOfTravellers; i++){
           travellerButton.click();
       }
   }

   public void openSignUpForm(){
       myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
       signUpLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
   }

}