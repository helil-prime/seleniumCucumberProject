package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class JSExecutor {

	static JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
	Actions action = new Actions(Driver.getDriver());

	public static void main(String[] args) throws InterruptedException {
		Driver.getDriver().get("https://amazon.com");

		WebElement what = Driver.getDriver().findElement(By.id("twotabsearchtextbox"));

		highlightElement(what);
		scrollDown();
		refreshPage();

	}

	public static void highlightElement(WebElement element) {
		for (int i = 0; i < 3; i++) {
			js.executeScript("arguments[0].style.border = '4px solid red'", element);
			js.executeScript("arguments[0].style.border = '4px blue'", element);
		}

	}

	// create alert popup
	public void createAlert() {
		js.executeScript("alert('Hey Yo!')");
	}

	// scroll down to certain point
	public static void scrollDown() {
		js.executeScript("javascript:window.scrollBy(0,400)");
	}

	// scroll down to an element
	public void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// javaScript to click type = hidden element
	public void clickOnElement(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void refreshPage() {
		js.executeScript("history.go(0)");
	}
	
	

	// action class util move to the element
	public void moveTo(WebElement element) {
		action.moveToElement(element).perform();
	}

	// hover over an element
	public void hover(WebElement element) {
		action.moveToElement(element).perform();
	}

}
