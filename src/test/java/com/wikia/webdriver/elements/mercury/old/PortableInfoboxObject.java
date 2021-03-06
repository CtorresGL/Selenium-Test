package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PortableInfoboxObject {

  @FindBy(css = "body")
  private WebElement bodyElement;

  @FindBy(css = ".portable-infobox")
  private WebElement infoboxWrapper;

  @FindBy(css = ".portable-infobox-hero-image")
  private WebElement mainImage;

  @FindBy(css = ".portable-infobox .pi-hero-title")
  private WebElement title;

  @FindBy(css = ".portable-infobox .pi-title")
  private WebElement titleSmallImage;

  @FindBy(css = ".portable-infobox .pi-expand-button")
  private WebElement expandButton;

  @FindBy(css = ".article-content .collapsed")
  private WebElement infoboxIsCollapsed;

  @FindBy(css = ".tabber .article-image")
  private WebElement imageInTabber;

  @FindBy(css = ".tabber figcaption")
  private WebElement captionInTabber;

  @FindBy(css = ".portable-infobox .article-video")
  private WebElement video;

  @FindBy(css = ".portable-infobox .article-video figcaption")
  private WebElement videoCaption;

  @FindBy(css = ".pi-title img")
  private WebElement imageInTitle;

  @FindBy(css = ".pi-image-collection")
  private WebElement imageInCollection;

  @FindBy(css = ".portable-infobox .article-media-linked-gallery button")
  private List<WebElement> galleryButtonList;

  @FindBy(css = ".image-collection-actions .action-next")
  private WebElement nextImageArrow;

  @FindBy(css = ".portable-infobox .external")
  private List<WebElement> externalLinks;

  @FindBy(css = ".pi-item .pi-data-label")
  private List<WebElement> dataLabels;

  @FindBy(css = ".pi-item .pi-data-value")
  private List<WebElement> dataValues;

  @FindBy(css = ".portable-infobox .reference")
  private List<WebElement> references;

  @FindBy(css = ".portable-infobox ul li")
  private List<WebElement> unorderedLists;

  @FindBy(css = ".portable-infobox ol li")
  private List<WebElement> orderedLists;

  @FindBy(css = ".pi-header")
  private List<WebElement> headers;

  @FindBy(css = ".portable-infobox .pi-image img")
  private List<WebElement> images;

  private By imageInTitleSelector = By.cssSelector(".pi-title img");

  private Wait wait;
  private WebDriver driver;

  public PortableInfoboxObject(WebDriver driver) {
    this.wait = new Wait(driver);
    this.driver = driver;

    PageFactory.initElements(driver, this);
  }

  public String getExternalLinkName(int index) {
    Assertion.assertFalse(externalLinks.isEmpty());
    wait.forElementVisible(externalLinks.get(index));

    return externalLinks.get(index).getText();
  }

  public String getUrlFromExternalLinkAfterPageIsLoaded() {
    wait.forElementVisible(bodyElement);

    return driver.getCurrentUrl();
  }

  public String getHeaderName(int index) {
    Assertion.assertFalse(headers.isEmpty());
    wait.forElementVisible(headers.get(index));

    return headers.get(index).getText();
  }

  public PortableInfoboxObject tapInfoboxContent() {
    Assertion.assertFalse(dataLabels.isEmpty());
    dataLabels.get(0).click();

    return this;
  }

  public PortableInfoboxObject clickExpandButton() {
    wait.forElementVisible(expandButton);
    expandButton.click();

    return this;
  }

  public PortableInfoboxObject clickExternalLink(int index) {
    Assertion.assertFalse(externalLinks.isEmpty());
    wait.forElementVisible(externalLinks.get(index));

    externalLinks.get(index).click();
    return this;
  }

  public PortableInfoboxObject closeLightbox() {
    new LightboxComponentObject(driver).clickCloseButton();

    return this;
  }

  public PortableInfoboxObject clickMainImage() {
    wait.forElementVisible(mainImage);
    mainImage.click();

    return this;
  }

  public PortableInfoboxObject clickVideo() {
    wait.forElementVisible(video);
    video.click();

    return this;
  }

  public PortableInfoboxObject clickGalleryButton(int index) {
    Assertion.assertFalse(galleryButtonList.isEmpty());
    wait.forElementVisible(galleryButtonList.get(index));

    galleryButtonList.get(index).click();

    return this;
  }

  public PortableInfoboxObject clickNextImageArrow() {
    wait.forElementVisible(nextImageArrow);
    nextImageArrow.click();

    return this;
  }

  public PortableInfoboxObject isMainImageVisible() {
    wait.forElementVisible(mainImage);
    PageObjectLogging.log("Main image", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isLightboxOpened() {
    Assertion.assertTrue(new LightboxComponentObject(driver).isLightboxOpened());

    return this;
  }

  public PortableInfoboxObject isTitleOverImageVisible() {
    wait.forElementVisible(title);
    PageObjectLogging.log("Title over image", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isTitleAboveImageVisible() {
    wait.forElementVisible(titleSmallImage);
    PageObjectLogging.log("Title above image", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageInTitleNotVisible() {
    wait.forElementNotVisible(imageInTitleSelector);
    PageObjectLogging.log("Main image title", MercuryMessages.INVISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isInfoboxCollapsed() {
    wait.forElementVisible(infoboxIsCollapsed);
    PageObjectLogging.log("Infobox", MercuryMessages.COLLAPSED_MSG, true);

    return this;
  }

  public PortableInfoboxObject isInfoboxExpanded() {
    Assertion.assertEquals(infoboxWrapper.getAttribute("style"), "height: auto;");
    PageObjectLogging.log("Infobox", MercuryMessages.EXPANDED_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageInCollectionVisible() {
    wait.forElementVisible(imageInCollection);
    PageObjectLogging.log("Image in collection", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageInTabberVisible() {
    wait.forElementVisible(imageInTabber);
    PageObjectLogging.log("Image in tabber", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isImageCaptionInTabberVisible() {
    wait.forElementVisible(captionInTabber);
    PageObjectLogging.log("Image caption in tabber", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isVideoVisible() {
    wait.forElementVisible(video);
    PageObjectLogging.log("Video", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject isVideoCaptionVisible() {
    wait.forElementVisible(videoCaption);
    PageObjectLogging.log("Video caption", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  /**
   * Hero image is the image that appears on the top of infobox
   */
  public PortableInfoboxObject isHeroImageCentered() {
    Assertion.assertEquals(images.get(0).getCssValue("vertical-align"), "middle");
    PageObjectLogging.log("Hero image", "is centered", true);

    return this;
  }

  public PortableInfoboxObject isHeroImageSquare() {
    wait.forElementVisible(mainImage);
    Assertion.assertEquals(mainImage.getSize().getHeight(), mainImage.getSize().getWidth());
    PageObjectLogging.log("Hero image", "is square", true);
    return this;
  }

  public PortableInfoboxObject isNotHeroImageSquare() {
    wait.forElementVisible(mainImage);
    Assertion.assertNotEquals(mainImage.getSize().getHeight(), mainImage.getSize().getWidth());
    PageObjectLogging.log("Hero image", "is not square", true);
    return this;
  }

  public PortableInfoboxObject isExternalLinkLabelInURL(String name, String url) {
    Assertion.assertStringContains(url, name);
    PageObjectLogging.log("External links", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areUnorderedListsVisible() {
    Assertion.assertFalse(unorderedLists.isEmpty());
    PageObjectLogging.log("Unordered list", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areOrderedListsVisible() {
    Assertion.assertFalse(orderedLists.isEmpty());
    PageObjectLogging.log("Ordered list", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areHeadersVisible() {
    Assertion.assertFalse(headers.isEmpty());
    PageObjectLogging.log("Headers", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areLinksVisible() {
    Assertion.assertFalse(externalLinks.isEmpty());
    PageObjectLogging.log("Links", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areReferencesVisible() {
    Assertion.assertFalse(references.isEmpty());
    PageObjectLogging.log("References", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areDataLabelsVisible() {
    Assertion.assertFalse(dataLabels.isEmpty());
    PageObjectLogging.log("Data labels", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject areDataValuesVisible() {
    Assertion.assertFalse(dataValues.isEmpty());
    PageObjectLogging.log("Data values", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public PortableInfoboxObject verifyDataValueMargin() {
    Assertion.assertEquals(dataValues.get(0).getCssValue("margin"), "0px");
    PageObjectLogging.log("Data values", "have correct margin", true);

    return this;
  }

  public PortableInfoboxObject verifyListMargin() {
    Assertion.assertEquals(orderedLists.get(0).getCssValue("margin"), "0px 0px 8px 10px");
    PageObjectLogging.log("Ordered list ", "have correct margin", true);

    Assertion.assertEquals(unorderedLists.get(0).getCssValue("margin"), "0px 0px 8px 10px");
    PageObjectLogging.log("Unordered list ", "have correct margin", true);

    return this;
  }
}
