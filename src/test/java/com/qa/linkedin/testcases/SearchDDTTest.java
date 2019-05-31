package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedInPage;
import com.qa.linkedin.pages.LinkedinLogginPage;
import com.qa.linkedin.pages.searchResultsPage;
import com.qa.linkedin.util.TestUtil;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class SearchDDTTest extends TestBase{
	LinkedinHomePage hmpg=null;
	LinkedinLogginPage lpg=null;
	LinkedinLoggedInPage lggpg=null;
	searchResultsPage srpg=null;
  public SearchDDTTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

@Test(dataProvider = "dp")
  public void searchTest(String s) throws InterruptedException, IOException {
	Assert.assertTrue(lggpg.verifyprofileCard());
	lggpg.searchPeople(s);
	int cnt=srpg.getResultsCount();
	System.out.println("results count for "+s+" is-->"+cnt);
	driver.navigate().back();
	driver.navigate().refresh();
	
	
  }

  @DataProvider
  public Object[][] dp() throws IOException {
    Object[][] data=TestUtil.getTestData(TestUtil.TESTDATA_SHEET_PATH1, "Sheet1");
     return data; 
  }
  @BeforeClass
  public void beforeClass() throws IOException {
	  initWebdriver(); //this initiliaze browser and webdriver urls also
	  hmpg=new LinkedinHomePage();
	  lpg=new LinkedinLogginPage();
	  lggpg=new LinkedinLoggedInPage();
	  srpg=new searchResultsPage(); 
	  hmpg.clickonSigninLink();
	  lpg.login(prop.getProperty("username"),prop.getProperty("pwd"));  
  }

  @AfterClass
  public void afterClass() {
	  lggpg.logOut();
	  driver.close();
  }

}
