package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;

public class TradeJournalPage {

	public TradeJournalPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id = "username")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(xpath = "//button[text()='Sign in']")
	public WebElement signInBtn;
	
	@FindBy(linkText = "Add trade")
	public WebElement addTradeBtn;
	
	@FindBy(id = "longTrade")
	public WebElement buySellDropdown;
	
	@FindBy(id = "symbol")
	public WebElement stockSymbol;
	
	@FindBy(id = "openDate")
	public WebElement entryDate;
	
	@FindBy(id = "entry")
	public WebElement entryPrice;
	
	@FindBy(id = "closeDate")
	public WebElement exitDate;
	
	@FindBy(id = "exit")
	public WebElement exitPrice;
	
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement saveTradeBtn;

}
