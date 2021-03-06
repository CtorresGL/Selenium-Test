package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiHistoryPageObject extends WikiBasePageObject {

  protected String articlename;

  @FindBy(css = "input[value='Go']")
  private WebElement goButton;
  @FindBy(css = "input#year")
  private WebElement fromYearField;
  @FindBy(xpath = "//a[contains(text(), 'Back to page')]")
  private WebElement backToPageLink;
  @FindBy(css = "span.mw-rollback-link a")
  private WebElement rollbackButton;
  @FindBy(xpath = "//h1[contains(text(), 'Action complete')]")
  private WebElement rollbackCompleteMessage;
  @FindBy(css = "p#mw-returnto a")
  private WebElement backToPageLinkOnRollbackPage;


  public WikiHistoryPageObject(WebDriver driver) {
    super();
    this.articlename = articlename;
    PageFactory.initElements(driver, this);
  }

  public WikiArticleRevisionEditMode clickUndoRevision(int revision) {
    WebElement
        undo =
        driver.findElement(By.xpath(
            "//ul[@id='pagehistory']/li[" + revision + "]//span[@class='mw-history-undo']/a"));
//		WebElement undo = driver.findElement(By.cssSelector("ul#pagehistory li:nth-child("+revision+") .mw-history-undo"));
    scrollAndClick(undo);
    return new WikiArticleRevisionEditMode(driver);
  }

  public void rollbackPage() {
    wait.forElementVisible(rollbackButton);
    scrollAndClick(rollbackButton);
    wait.forElementVisible(rollbackCompleteMessage);
  }

  public WikiArticlePageObject enterPageAfterRollback() {
    wait.forElementVisible(backToPageLinkOnRollbackPage);
    scrollAndClick(backToPageLinkOnRollbackPage);
    return new WikiArticlePageObject(driver);
  }

  public void verifyImportandPageElements() {
    wait.forElementVisible(fromYearField);
    wait.forElementVisible(backToPageLink);
    wait.forElementVisible(goButton);
    PageObjectLogging
        .log("verifyImportandPageElements", "several importand history page elements are present",
             true, driver);
  }

  public void verifyLatestEditSummary(String text) {
    String editSummary = getFirstCssRevision();
    editSummary = editSummary.substring(1, editSummary.length() - 1);
    Assertion.assertEquals(editSummary, text);
  }
}
