package step_definitions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TradeJournalPage;
import utilities.BrowserUtils;
import utilities.Driver;

public class TradeAppAddTrade {
	
	TradeJournalPage tradepage = new TradeJournalPage();
	BrowserUtils utils = new BrowserUtils();
	List<String> list;
	
	
	// scenario step data table
	@Given("I am on the app log in page")
	public void i_am_on_the_app_log_in_page() {
	    Driver.getDriver().get("http://ec2-3-142-242-205.us-east-2.compute.amazonaws.com:8080/");
	}
	@When("I enter valid username {string} and password {string}")
	public void i_enter_valid_username_and_password(String string, String string2) {
	    tradepage.username.sendKeys(string);
	    tradepage.password.sendKeys(string2);
	    
	}
	
	@When("I click on trade signin button")
	public void i_click_on_trade_signin_button() {
		tradepage.signInBtn.click();
	}
	
	
	@Then("I should be in the trade homepage")
	public void i_should_be_in_the_trade_homepage() {
	    utils.waitUntilElementVisible(tradepage.addTradeBtn);
	    
	}
	@When("I click on Add Trade button")
	public void i_click_on_add_trade_button() {
		tradepage.addTradeBtn.click();
	}
	@Then("I should be on Save Trade Form")
	public void i_should_be_on_save_trade_form() {
	    utils.waitUntilElementVisible(tradepage.buySellDropdown);
	}
	@When("I enter following trade details")
	public void i_enter_following_trade_details(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	   list = dataTable.asList();
	   for (String string : list) {
		System.out.println(string);
	}
	   Select select = new Select(tradepage.buySellDropdown);
	   select.selectByVisibleText(list.get(0));
	   
	   tradepage.stockSymbol.sendKeys(list.get(1));
	   tradepage.entryDate.sendKeys(list.get(2));
	   tradepage.entryPrice.sendKeys(list.get(3));
	   tradepage.exitDate.sendKeys(list.get(4));
	   tradepage.exitPrice.sendKeys(list.get(5));
	   
	}
	@And("I click save button")
	public void i_click_save_button() {
	    tradepage.saveTradeBtn.click();
	}
	
	@Then("the trade is displayed in the trade table")
	public void the_trade_is_displayed_in_the_trade_table() throws ParseException {
		utils.waitUntilElementVisible(tradepage.addTradeBtn);
		List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//table[@class='table table-bordered table-striped']/tbody/tr[1]/td"));
		
		
		DateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		
		for (int i = 0; i < elements.size() - 1 ; i++) {
			if (i == 2 || i == 4) {
				System.out.println(list.get(i));
				Date date = (Date)sm.parse(list.get(i));
				System.out.println("Inside the if: " + date.toString());
				String celldata = elements.get(i).getText();
				Assert.assertEquals(celldata, date.toString());
				
			} else {
				String celldata = elements.get(i).getText();
				Assert.assertEquals(celldata, list.get(i));
			}
			
		}
		
	}

}
