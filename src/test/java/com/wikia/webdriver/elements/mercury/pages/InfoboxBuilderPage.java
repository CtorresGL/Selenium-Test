package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InfoboxBuilderPage extends SpecialPageObject {

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;

  @FindBy(css = ".sub-head--done")
  private WebElement saveButton;

  @FindBy(css = ".infobox-builder-sidebar-header .infobox-builder-sidebar-header-icon-delete")
  private WebElement deleteButton;

  @FindBy(css = ".pop-over-container .infobox-builder-sidebar-header-icon-delete")
  private WebElement deletePopUp;

  @FindBy(css = ".infobox-builder-sidebar-padding .check-box-input")
  private WebElement sidebarCheckbox;

  @FindBy(css = ".infobox-builder-sidebar-padding .text-field-input")
  private WebElement sidebarInputField;

  @FindBy(css = ".infobox-builder-sidebar-header-icon-back")
  private WebElement backArrowButton;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement previewArea;

  @FindBy(css = ".on-hover-tooltip")
  private WebElement tooltip;

  @FindBy(css = ".infobox-builder-spinner")
  private WebElement savingSpinner;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement builderBackground;

  @FindBy(css = "#go-to-source")
  private WebElement goToSourceButton;

  @FindBy(css = ".infobox-builder-go-to-source-modal")
  private WebElement goToSourceModalBackground;

  @FindBy(css = ".pop-over .orient-below")
  private WebElement sectionTooltipOrientedBelow;

  @FindBy(css = ".pop-over .orient-above")
  private WebElement sectionTooltipOrientedAbove;

  @FindBy(css = ".infobox-builder-go-to-source-modal > div")
  private WebElement modalGoToSource;

  @FindBy(css = ".infobox-builder-go-to-source-modal .modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement saveChangesButton;

  @FindBy(css = ".infobox-builder-go-to-source-modal .modal-dialog div.modal-bottom-row > button:nth-child(2)")
  private WebElement dropChangesButton;

  @FindBy(css = ".text-field-error-message")
  private WebElement errorMessage;

  @FindBy(css = ".infobox-builder-edit-title-modal > div")
  private WebElement modalEditTitle;

  @FindBy(css = ".infobox-builder-edit-title-modal .modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement publishEditedTitleButton;

  @FindBy(css = "#editTemplateTitle")
  private WebElement editTemplateTitleInput;

  @FindBy(css = ".portable-infobox .pi-data-label")
  private List<WebElement> rowLabels;

  @FindBy(css = ".infobox-builder-buttons")
  private WebElement componentsButtonsWrapper;

  @FindBy(css = ".infobox-builder-button")
  private List<WebElement> componentsButtons;

  @FindBy(css = ".portable-infobox .pi-data")
  private List<WebElement> rows;

  @FindBy(css = ".portable-infobox .pi-title")
  private List<WebElement> titles;

  @FindBy(css = ".portable-infobox .pi-image")
  private List<WebElement> images;

  @FindBy(css = ".portable-infobox .pi-header")
  private List<WebElement> headers;

  @FindBy(css = ".portable-infobox .sortable-item")
  private List<WebElement> component;

  @FindBy(css = ".infobox-builder-chevron-area")
  private List<WebElement> sectionHeadersChevron;

  public InfoboxBuilderPage() {
    super();
  }

  public InfoboxBuilderPage openNew(String templateName) {
    new TemplateEditPage().open(templateName)
        .getTemplateClassification()
        .selectInfoboxTemplate()
        .clickAddButton();

    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public InfoboxBuilderPage openExisting(String templateName) {
    new TemplateEditPage().open(templateName)
        .openCurrectArticleSourceMode();
    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public InfoboxBuilderPage open() {
    new TemplateEditPage().open("temp_template");
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(), URLsContent.SPECIAL_INFOBOX_BUILDER));
    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public boolean isGoToSourceModalPresent() {
    wait.forElementVisible(modalGoToSource);

    return modalGoToSource.isDisplayed();
  }

  public boolean isModalEditTitlePresent() {
    wait.forElementVisible(modalEditTitle);

    return modalEditTitle.isDisplayed();
  }

  public boolean isEditTemplateTitleInputPresent() {
    wait.forElementVisible(editTemplateTitleInput);

    return editTemplateTitleInput.isDisplayed();
  }

  public InfoboxBuilderPage insertTemplateTitle(String title) {
    wait.forElementClickable(editTemplateTitleInput);
    editTemplateTitleInput.sendKeys(title);

    return this;
  }

  public boolean isErrorMessagePresent() {
    wait.forElementVisible(errorMessage);

    return errorMessage.isDisplayed();
  }

  public boolean isInfoboxBuilderOpened() {
    wait.forElementClickable(builderBackground);

    return builderBackground.isDisplayed();
  }

  public boolean isSidebarInputFieldFocused() {
    return sidebarInputField.equals(driver.switchTo().activeElement());
  }

  public boolean isInputFieldPresent() {
    wait.forElementVisible(sidebarInputField);

    return sidebarInputField.isDisplayed();
  }

  public boolean isInfoboxBuilderPresent() {
    return builderIFrame.isDisplayed();
  }

  public boolean isSectionTooltipPresentAbove(int index) {
    hoverOverSectionChevron(index);
    wait.forElementVisible(sectionTooltipOrientedAbove);

    return sectionTooltipOrientedAbove.isDisplayed();
  }

  public boolean isSectionTooltipPresentBelow(int index) {
    hoverOverSectionChevron(index);
    wait.forElementVisible(sectionTooltipOrientedBelow);

    return sectionTooltipOrientedBelow.isDisplayed();
  }

  public boolean isSpinnerPresent() {
    wait.forElementVisible(savingSpinner);

    return savingSpinner.isDisplayed();
  }

  public boolean areAddButtonsPresent() {
    wait.forElementVisible(componentsButtonsWrapper);

    return componentsButtonsWrapper.isDisplayed();
  }

  public boolean isTitleUsingArticleName(int titleIndex) {
    this.selectTitleWithIndex(titleIndex);
    wait.forElementClickable(sidebarCheckbox);

    return sidebarCheckbox.isSelected();
  }

  public InfoboxBuilderPage setTitleToUseArticleName(int index) {
    this.selectTitleWithIndex(index);
    wait.forElementClickable(sidebarCheckbox);

    if (!sidebarCheckbox.isSelected()) {
      sidebarCheckbox.click();
    }

    return this;
  }

  public void clickDropChangesButton() {
    wait.forElementClickable(dropChangesButton);
    dropChangesButton.click();
  }

  public void clickSaveChangesButton() {
    wait.forElementClickable(saveChangesButton);
    saveChangesButton.click();
  }

  public void clickPublishEditedTitleButton() {
    wait.forElementClickable(publishEditedTitleButton);
    publishEditedTitleButton.click();
  }

  public InfoboxBuilderPage clickBuilderBackground() {
    wait.forElementClickable(builderBackground);
    builderBackground.click();

    return this;
  }

  public InfoboxBuilderPage clickBackArrow() {
    wait.forElementClickable(backArrowButton);
    backArrowButton.click();

    return this;
  }

  public InfoboxBuilderPage clickGoToSourceButton() {
    wait.forElementClickable(goToSourceButton);
    goToSourceButton.click();

    return this;
  }

  public InfoboxBuilderPage clickGoToSourceModalBackground() {
    wait.forElementClickable(goToSourceModalBackground);
    goToSourceModalBackground.click();

    return this;
  }

  public InfoboxBuilderPage clickPublish() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    return this;
  }

  public int countRows() {
    return rows.size();
  }

  public int countTitles() {
    return titles.size();
  }

  public int countImages() {
    return images.size();
  }

  public int countHeaders() {
    return headers.size();
  }

  public InfoboxBuilderPage changeHeaderCollapsibilityState(int index) {
    headers.get(index).click();

    wait.forElementClickable(sidebarCheckbox);
    sidebarCheckbox.click();

    String script = "return window.getComputedStyle(document"
                    + ".querySelector('.pi-header'),':after').content";
    String chevronContent = driver.executeScript(script).toString();

    if (sidebarCheckbox.isSelected()) {
      Assertion.assertFalse(chevronContent.isEmpty());
    } else {
      Assertion.assertTrue(chevronContent.isEmpty());
    }

    return this;
  }

  public String getComponentBackgroundColor(int index) {
    wait.forElementVisible(component.get(index));

    return component.get(index).getCssValue("background-color");
  }

  public String getPreviewBackgroundColor() {
    wait.forElementVisible(previewArea);

    return previewArea.getCssValue("background-color");
  }

  public String getBorderStyle() {
    WebElement selectedComponent = component.get(0);
    wait.forElementClickable(selectedComponent);
    selectedComponent.click();

    String script = "return window.getComputedStyle("
                    + "document.querySelector('.active'),':before').getPropertyValue('Border')";

    return driver.executeScript(script).toString();
  }

  public int getInfoboxWidth() {
    return titles.get(0).getSize().getWidth();
  }

  public InfoboxBuilderPage selectTitleWithIndex(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementClickable(selectedTitle);
    selectedTitle.click();

    return this;
  }

  public InfoboxBuilderPage selectImageWithIndex(int index) {
    WebElement selectedImage = images.get(index);
    wait.forElementClickable(selectedImage);
    selectedImage.click();

    return this;
  }

  public InfoboxBuilderPage selectRowWithIndex(int index) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    selectedRow.click();

    return this;
  }

  public InfoboxBuilderPage selectHeaderWithIndex(int index) {
    WebElement selectedHeader = headers.get(index);
    wait.forElementClickable(selectedHeader);
    selectedHeader.click();

    return this;
  }

  public InfoboxBuilderPage addRowComponent() {
    WebElement rowComponentButton = componentsButtons.get(2);
    wait.forElementClickable(rowComponentButton);
    rowComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addImageComponent() {
    WebElement imageComponentButton = componentsButtons.get(1);
    wait.forElementClickable(imageComponentButton);
    imageComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addTitleComponent() {
    WebElement titleComponentButton = componentsButtons.get(0);
    wait.forElementClickable(titleComponentButton);
    titleComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addHeaderComponent() {
    WebElement headerComponentButton = componentsButtons.get(3);
    wait.forElementClickable(headerComponentButton);
    headerComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteTitleUsingButton(int index) {
    deleteItem(titles.get(index), deleteButton);

    return this;
  }

  public InfoboxBuilderPage deleteRowUsingButton(int index) {
    deleteItem(rows.get(index), deleteButton);

    return this;
  }

  public InfoboxBuilderPage deleteImageUsingButton(int index) {
    deleteItem(images.get(index), deleteButton);

    return this;
  }

  public InfoboxBuilderPage deleteHeaderUsingButton(int index) {
    deleteItem(headers.get(index), deleteButton);

    return this;
  }

  public InfoboxBuilderPage deleteTitleUsingPopUp(int index) {
    deleteItem(titles.get(index), deletePopUp);

    return this;
  }

  public InfoboxBuilderPage deleteRowUsingPopUp(int index) {
    deleteItem(rows.get(index), deletePopUp);

    return this;
  }

  public InfoboxBuilderPage deleteImageUsingPopUp(int index) {
    deleteItem(images.get(index), deletePopUp);

    return this;
  }

  private void deleteItem(WebElement item, WebElement deleteMethod) {
    wait.forElementClickable(item);
    item.click();

    wait.forElementClickable(deleteMethod);
    deleteMethod.click();
  }

  public InfoboxBuilderPage verifyScrollbarIsVisible() {
    Assertion.assertEquals(previewArea.getCssValue("overflow"), "auto");
    return this;
  }

  public InfoboxBuilderPage verifyTooltipOnHover() {
    wait.forElementVisible(component.get(0));
    builder.moveToElement(component.get(0)).perform();
    Assertion.assertTrue(tooltip.isDisplayed());

    return this;
  }

  public InfoboxBuilderPage setAndVerifyRowLabel(int index, String labelName) {
    this.selectRowWithIndex(index);

    wait.forElementClickable(sidebarInputField);
    sidebarInputField.click();
    sidebarInputField.clear();
    sidebarInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage setLongLabelNameAndVerifyBreakLine(int index, String labelName) {
    this.selectRowWithIndex(index);

    wait.forElementClickable(sidebarInputField);
    sidebarInputField.click();
    sidebarInputField.clear();
    sidebarInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getCssValue("word-wrap"), "break-word");

    return this;
  }

  public InfoboxBuilderPage setAndVerifyHeaderName(int index, String labelName) {
    this.selectHeaderWithIndex(index);

    wait.forElementClickable(sidebarInputField);
    sidebarInputField.click();
    sidebarInputField.clear();
    sidebarInputField.sendKeys(labelName);
    Assertion.assertEquals(headers.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage moveToLastComponent() {
    WebElement lastComponent = component.get(component.size() - 1);
    wait.forElementVisible(lastComponent);
    builder.moveToElement(lastComponent).perform();

    return this;
  }

  public InfoboxBuilderPage dragAndDropToTheTop(int index) {
    this.wait.forElementClickable(component.get(index));
    String componentToBeMovedText = component.get(index).getText();
    Point location = component.get(component.size() - 1).getLocation();
    Dimension size = component.get(component.size() - 1).getSize();
    Integer targetY = location.getY() + size.getHeight();

    new Actions(driver)
        .clickAndHold(component.get(index))
        .moveByOffset(0, targetY)
        .release(component.get(index))
        .perform();

    wait.forElementClickable(component.get(component.size() - 1));
    component.get(component.size() - 1).click();
    Assertion.assertEquals(componentToBeMovedText, component.get(0).getText());

    return this;
  }

  public void hoverOverSectionChevron(int index) {
    wait.forElementVisible(sectionHeadersChevron.get(index));
    builder.moveToElement(sectionHeadersChevron.get(index)).perform();
  }
}
