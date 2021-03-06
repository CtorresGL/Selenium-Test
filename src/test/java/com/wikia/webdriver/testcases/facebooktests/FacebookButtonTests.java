package com.wikia.webdriver.testcases.facebooktests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject.DropDownComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.login.SpecialUserLoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject.tabNames;

import org.testng.annotations.Test;

/* 
 * Check for facebook button on the page
 */
public class FacebookButtonTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"FBButton_001", "FacebookButton"})
  public void facebookButtonIsVisibleOnDropdwon() {
    DropDownComponentObject dropDown = new DropDownComponentObject(driver);
    dropDown.openDropDown();
    dropDown.verifyDropDownFBButtonVisible();
  }

  @Test(groups = {"FBButton_002", "FacebookButton"})
  public void facebookButtonIsVisibleOnSignupPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    SignUpPageObject signUpPage = base.openSpecialSignUpPage(wikiURL);
    signUpPage.verifyFBButtonVisible();
  }

  @Test(groups = {"FBButton_003", "FacebookButton"})
  public void facebookButtonIsVisibleOnLoginPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialUserLoginPageObject login = base.openSpecialUserLogin(wikiURL);
    login.verifyFBButtonVisible();
  }

  @Test(groups = {"FBButton_004", "FacebookButton"})
  public void facebookButtonIsVisibleOnForcedLoginModal() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPageObject specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getNewFilesSpecialPageTitle());
    specialPage.addPhoto();
    specialPage.verifyModalLoginAppeared();
    specialPage.verifyModalFBButtonVisible();
  }

  @Test(groups = {"FBButton_005", "FacebookButton"})
  @Execute(asUser = User.USER)
  public void facebookButtonIsVisibleOnUserPreferencesPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    PreferencesPageObject prefsPage = base.openSpecialPreferencesPage(wikiURL);
    prefsPage.selectTab(tabNames.FACEBOOK);
    prefsPage.verifyFBButtonVisible();
  }

}

