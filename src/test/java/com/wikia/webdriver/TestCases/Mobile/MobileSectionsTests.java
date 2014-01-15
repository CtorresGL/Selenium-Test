package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileEditModePageObject;

/**
 * @author PMG
 *
 *1. all testcases should be deleted
 *
 * */

public class MobileSectionsTests extends NewTestTemplate{

	@Test(groups={"sectionTest_001", "sectionsTests", "mobile"}, enabled = false)
	public void Sections_001_chevronTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections(wikiURL);
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickSection(1);
		article.verifySectionInvisibility();
	}

	@Test(groups={"sectionTest_002", "sectionsTests", "mobile"}, enabled = false)
	public void Sections_002_hideTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections(wikiURL);
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickHideButton();
		article.verifySectionInvisibility();
	}

	@Test(groups={"sectionTest_002", "sectionsTests", "mobile"}, enabled = false)
	public void Sections_003_editSection() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.openSections(wikiURL);
		article.clickSection(1);
		String sectionText = article.getSectionText(0);
		MobileEditModePageObject mobileEdit = article.editSection(0);
		mobileEdit.verifyEditModeContent(sectionText);
	}
}
