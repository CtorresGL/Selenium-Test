package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.Special.Block.SpecialBlockListPageObject;
import com.wikia.webdriver.PageObjects.PageObject.Special.Block.SpecialBlockPageObject;
import com.wikia.webdriver.PageObjects.PageObject.Special.Block.SpecialContributionsPageObject;
import com.wikia.webdriver.PageObjects.PageObject.Special.Block.SpecialUnblockPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;

public class UserAndRights extends TestTemplate{
	
	@Test(groups = {"usersAndRights001", "UsersAndRights"})
	public void usersAndRights001_Block(){
		CommonFunctions.logOut(driver);
		SpecialBlockPageObject block = new SpecialBlockPageObject(driver);
		block.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		block = block.openSpecialBlockPage();
		block.deselectAllSelections();
		block.typeInUserName(Properties.userNameBlocked);
		block.selectExpiration("2 hours");
		block.clickBlockButton();
		CommonFunctions.logOut(driver);
	}
	
	@Test(groups = {"usersAndRights002", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights002_VerifyBlockedUser(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameBlocked, Properties.passwordBlocked);
		String ts = article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(ts, 1);
		edit.verifyBlockedUserMessage();
	}
	
	@Test(groups = {"usersAndRights003", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights003_BlockListBlocked(){
		CommonFunctions.logOut(driver);
		SpecialBlockListPageObject list = new SpecialBlockListPageObject(driver, Global.DOMAIN);
		list.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		list = list.openSpecialBlockListPage();
		list.searchForUser(Properties.userNameBlocked);
		list.verifyUserBlocked(Properties.userNameBlocked);
	}
	
//	public void 
	
	@Test(groups = {"usersAndRights003", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights004_Unblock(){
		CommonFunctions.logOut(driver);
		SpecialUnblockPageObject unblock = new SpecialUnblockPageObject(driver, Global.DOMAIN);
		unblock.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		unblock = unblock.openSpecialUnblockPage();
		unblock.unblockUser(Properties.userNameBlocked);
		unblock.verifyUnblockMessage(Properties.userNameBlocked);
	}
	
	@Test(groups = {"usersAndRights005", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights005_VerifyUnblockedUser(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver, Global.DOMAIN, "");
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameBlocked, Properties.passwordBlocked);
		String ts = article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(ts, 1);
		edit.clickOnPublishButton();
	}
	
	@Test(groups = {"usersAndRights006", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights006_BlockListUnblocked(){
		CommonFunctions.logOut(driver);
		SpecialBlockListPageObject list = new SpecialBlockListPageObject(driver, Global.DOMAIN);
		list.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		list = list.openSpecialBlockListPage();
		list.searchForUser(Properties.userNameBlocked);
		list.verifyUserUnblocked();
	}

	@Test(groups = {"usersAndRights006", "UsersAndRights"})
	public void usersAndRights007_Contributions(){
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String pageName = wiki.getTimeStamp() + "Special:Contributions test article title";
		String pageContent = wiki.getTimeStamp() + "Special:Contributions test article content";
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewArticle(pageName, 1);
		edit.deleteArticleContent();
		edit.typeInContent(pageContent);
		edit.clickOnPublishButton();
		SpecialContributionsPageObject contribution = new SpecialContributionsPageObject(driver);
		contribution = contribution.openContributionsPage();
		contribution.searchContributions(Properties.userNameStaff);
		contribution.verifyNewPageOnList(pageName, pageContent);
		CommonFunctions.logOut(driver);
	}
}