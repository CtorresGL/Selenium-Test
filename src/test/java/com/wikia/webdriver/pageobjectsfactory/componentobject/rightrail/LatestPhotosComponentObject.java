package com.wikia.webdriver.pageobjectsfactory.componentobject.rightrail;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LatestPhotosComponentObject extends WikiBasePageObject {

  @FindBy(css = ".carousel li.thumbs")
  private List<WebElement> latestPhotosList;

  public LatestPhotosComponentObject(WebDriver driver) {
    super();
  }

  public LightboxComponentObject openLightboxForImage(int imageNumber) {
    scrollAndClick(latestPhotosList.get(imageNumber));
    PageObjectLogging.log("openLightboxForImage", "lightbox for image opened", true);
    return new LightboxComponentObject(driver);
  }
}
