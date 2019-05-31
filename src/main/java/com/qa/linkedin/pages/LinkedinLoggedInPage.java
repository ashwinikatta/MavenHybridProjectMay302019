package com.qa.linkedin.pages;

import java.io.IOException;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.linkedin.base.TestBase;

public class LinkedinLoggedInPage extends TestBase{

	public LinkedinLoggedInPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	WebElement Profile_rail_card;
	
	@FindBy(xpath="//*[contains(@class,'nav-item__profile-member-photo nav-item__icon ghost-person')]")
	WebElement profileimage;
	
	@FindBy(xpath="//*[contains(@data-control-name,'nav.settings_signout')]")
	WebElement signout_link;
	
	@FindBy(xpath="//*[@role='combobox' and @placeholder='Search']")
	WebElement search_editbox;
	
	@FindBy(xpath="//*[contains(@data-control-name,'nav.search_button')]")
	WebElement search_torch_icon;
	
	public boolean verifyprofileCard() {
		wait.until(ExpectedConditions.visibilityOf(Profile_rail_card));
		return Profile_rail_card.isDisplayed();
	}
	
	public void logOut() {
		profileimage.click();
		wait.until(ExpectedConditions.visibilityOf(signout_link));
		signout_link.click();
	}
	
	public searchResultsPage searchPeople(String peoplekeyword) throws InterruptedException, IOException {
		search_editbox.clear();
		search_editbox.sendKeys(peoplekeyword);
		//search_editbox.sendKeys(Keys.ENTER);
		search_torch_icon.click();
		//search_torch_icon.submit();
		Thread.sleep(3000);
		return new searchResultsPage();
	}
	

}
