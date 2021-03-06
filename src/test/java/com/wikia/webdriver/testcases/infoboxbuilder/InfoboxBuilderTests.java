package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.InfoboxBuilderPage;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.elements.oasis.pages.WikiFeatures;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "InfoboxBuilderTests")
@Execute(onWikia = "mediawiki119")
public class InfoboxBuilderTests extends NewTestTemplate {

  private static final int EUROPA_INFOBOX_WIDTH = 300;
  private static final int DEFAULT_INFOBOX_WIDTH = 270;

  @Execute(asUser = User.USER)
  public void verifyDefaultStructure() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderVerifyDefaultStructure");

    Assertion.assertEquals(builderPage.countTitles(), 1);
    Assertion.assertEquals(builderPage.countImages(), 1);
    Assertion.assertEquals(builderPage.countRows(), 2);
    Assertion.assertEquals(builderPage.countHeaders(), 0);

    Assertion.assertTrue(builderPage.isTitleUsingArticleName(0));
  }

  @Execute(asUser = User.USER)
  public void addingComponents() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderAddingComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();
    int headerComponents = builderPage.countHeaders();

    builderPage.addRowComponent().addImageComponent().addTitleComponent().addHeaderComponent();

    Assertion.assertEquals(rowComponents + 1, builderPage.countRows());
    Assertion.assertEquals(titleComponents + 1, builderPage.countTitles());
    Assertion.assertEquals(imageComponents + 1, builderPage.countImages());
    Assertion.assertEquals(headerComponents + 1, builderPage.countHeaders());
  }

  @Execute(asUser = User.USER)
  public void savingTemplate() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderSavingTemplate")
        .deleteRowUsingButton(0)
        .addRowComponent()
        .clickPublish()
        .isSpinnerPresent();

    Assertion.assertEquals("infoboxbuildersavingtemplate",
                           new TemplatePage().getHeaderText().toLowerCase());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponents() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingDefaultComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();

    Assertion.assertEquals(rowComponents - 1,
                           builderPage.deleteRowUsingButton(0).countRows());
    Assertion.assertEquals(titleComponents - 1,
                           builderPage.deleteTitleUsingButton(0).countTitles());
    Assertion.assertEquals(imageComponents - 1,
                           builderPage.deleteImageUsingButton(0).countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingDefaultComponentsUsingPopUp() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingDefaultComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();

    Assertion.assertEquals(rowComponents - 1,
                           builderPage.deleteRowUsingPopUp(0).countRows());
    Assertion.assertEquals(titleComponents - 1,
                           builderPage.deleteTitleUsingPopUp(0).countTitles());
    Assertion.assertEquals(imageComponents - 1,
                           builderPage.deleteImageUsingPopUp(0).countImages());
  }

  @Execute(asUser = User.USER)
  public void deletingAddedComponents() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openNew("InfoboxBuilderDeletingAddedComponents");

    int rowComponents = builderPage.countRows();
    int titleComponents = builderPage.countTitles();
    int imageComponents = builderPage.countImages();
    int headerComponents = builderPage.countHeaders();

    builderPage.addImageComponent().addTitleComponent().addRowComponent().addHeaderComponent();

    /* deleting last (newly added) components */
    Assertion.assertEquals(
        rowComponents,
        builderPage.deleteRowUsingButton(builderPage.countRows() - 1).countRows()
    );
    Assertion.assertEquals(
        titleComponents,
        builderPage.deleteTitleUsingButton(builderPage.countTitles() - 1).countTitles()
    );
    Assertion.assertEquals(
        imageComponents,
        builderPage.deleteImageUsingButton(builderPage.countImages() - 1).countImages()
    );
    Assertion.assertEquals(
        headerComponents,
        builderPage.deleteHeaderUsingButton(builderPage.countHeaders() - 1).countHeaders()
    );
  }

  @Execute(asUser = User.USER)
  public void customizingComponents() {
    TemplatePage template = new TemplatePage();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("InfoboxBuilderCustomizingComponents")
        .changeHeaderCollapsibilityState(0)
        .setAndVerifyRowLabel(0, "AutomatedTest")
        .setLongLabelNameAndVerifyBreakLine(1, "AutomatedTestVeryLongName")
        .setAndVerifyHeaderName(0, "AutomatedTestHeader")
        .clickPublish();

    Assertion.assertTrue(builderPage.isSpinnerPresent());

    Assertion.assertTrue(template.isTemplatePagePresent());

    String infoboxTitle = template.getPortableInfobox().getTitleTextWithIndex(0);
    Assertion.assertEquals("InfoboxBuilderCustomizingComponents", infoboxTitle);
  }

  @Execute(asUser = User.USER)
  public void setInfoboxTitleToUseArticleName() {
    TemplatePage template = new TemplatePage();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("SetInfoboxTitleToUseArticleName")
        .deleteTitleUsingPopUp(0)
        .addTitleComponent()
        .setTitleToUseArticleName(0)
        .clickPublish();

    Assertion.assertTrue(builderPage.isSpinnerPresent());
    Assertion.assertTrue(template.isTemplatePagePresent());

    String infoboxTitle = template.getPortableInfobox().getTitleTextWithIndex(0);
    Assertion.assertEquals("SetInfoboxTitleToUseArticleName", infoboxTitle);
  }

  @Execute(asUser = User.USER)
  public void newTemplateCreation() {
    new TemplateEditPage().open("InfoboxBuilderNewTemplateCreation")
        .getTemplateClassification()
        .changeTemplateType()
        .clickAddButton();

    Assertion.assertTrue(new InfoboxBuilderPage().isInfoboxBuilderPresent());
  }

  @Execute(asUser = User.USER)
  public void verifySidebarBackArrow() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyInterfaceFunctionality");

    Assertion.assertTrue(builderPage.selectRowWithIndex(0).clickBackArrow().areAddButtonsPresent());
    Assertion
        .assertTrue(builderPage.selectTitleWithIndex(0).clickBackArrow().areAddButtonsPresent());
    Assertion
        .assertTrue(builderPage.selectImageWithIndex(0).clickBackArrow().areAddButtonsPresent());
    Assertion
        .assertTrue(builderPage.selectHeaderWithIndex(0).clickBackArrow().areAddButtonsPresent());
  }

  @Execute(asUser = User.STAFF)
  public void verifyInfoboxPreviewTheme() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage();
    SpecialThemeDesignerPageObject themeDesigner = new SpecialThemeDesignerPageObject(driver);
    TemplatePage template = new TemplatePage();

    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(0);
    themeDesigner.submitTheme();

    String templateBgColor =
        template.open(PageContent.PORTABLE_INFOBOX_01).getPageBackgroundColor();
    String previewBgColor =
        builderPage.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroundColor();

    Assertion.assertEquals(previewBgColor, templateBgColor);

    themeDesigner.openSpecialDesignerPage(wikiURL).selectTheme(2);
    themeDesigner.submitTheme();

    templateBgColor = template.open(PageContent.PORTABLE_INFOBOX_01).getPageBackgroundColor();
    previewBgColor =
        builderPage.openExisting("InfoboxBuilderVerifyInfoboxTheme").getPreviewBackgroundColor();

    Assertion.assertEquals(templateBgColor, previewBgColor);
  }

  /* Verify if scrolling is enabled when Infobox's height in
  preview is greater than the preview area height. */
  @Execute(asUser = User.USER)
  public void verifyScrolling() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderVerifyScrolling")
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .addImageComponent()
        .verifyScrollbarIsVisible()
        .moveToLastComponent();
  }

  @Execute(asUser = User.STAFF)
  public void verifyUserInteractions() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("InfoboxBuilderVerifySelectedBorderStyling")
        .verifyTooltipOnHover();

    String borderStyle = builderPage.getBorderStyle();
    Assertion.assertEquals(borderStyle, "1px solid rgb(26, 94, 184)");

    borderStyle = builderPage.clickBuilderBackground().getComponentBackgroundColor(0);
    Assertion.assertNotEquals(borderStyle, "1px solid rgb(26, 94, 184)");
  }

  @Execute(asUser = User.STAFF)
  public void verifyReordering() {
    new InfoboxBuilderPage().openNew("InfoboxBuilderVerifyReordering")
        .dragAndDropToTheTop(2)
        .dragAndDropToTheTop(3)
        .dragAndDropToTheTop(1);
  }

  @Execute(asUser = User.USER)
  public void verifyRedirectingUnsupportedInfoboxes() {
    new TemplatePage().open("InfoboxBuilderMultipleInfoboxes").editArticleInSrcUsingDropdown();
    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());

    new TemplatePage().open("InfoboxBuilderUnsupportedMarkup").editArticleInSrcUsingDropdown();
    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
  }

  @Execute(asUser = User.USER)
  public void immutableSources() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderImmutableRows")
        .setAndVerifyRowLabel(0, "AutomatedTest")
        .clickPublish();

    String invocationLabelText = new PortableInfobox().open("InfoboxBuilderImmutableExample")
        .getDataLabelTextWithIndex(0);

    Assertion.assertEquals("AutomatedTest", invocationLabelText);
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorClickOnModalBackground() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("Infobox_verify_go_to_source");

    builderPage.clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickGoToSourceModalBackground();

    Assertion.assertTrue(builderPage.isInfoboxBuilderOpened());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorSaveChanges() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openExisting("Infobox_verify_go_to_source_save_changes")
        .addRowComponent()
        .deleteRowUsingButton(1)
        .clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickSaveChangesButton();

    TemplateEditPage template = new TemplateEditPage();

    Assert.assertTrue(template.isEditAreaDisplayed());
    Assert.assertFalse(template.isEditAreaEmpty());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorDropChanges() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage()
        .openNew("Infobox_verify_go_to_source_drop_changes")
        .clickGoToSourceButton();

    Assertion.assertTrue(builderPage.isGoToSourceModalPresent());

    builderPage.clickDropChangesButton();

    TemplateEditPage template = new TemplateEditPage();
    template.getTemplateClassification()
        .selectInfoboxTemplate()
        .clickAddButton();

    Assertion.assertTrue(template.isEditAreaDisplayed());
    Assertion.assertTrue(template.isEditAreaEmpty());
  }

  @Execute(asUser = User.USER)
  public void verifyGoToSourceEditorNonEditedInfobox() {
    new InfoboxBuilderPage().openExisting("InfoboxBuilderSavingTemplate")
        .clickGoToSourceButton();

    Assertion.assertTrue(new TemplateEditPage().isEditAreaDisplayed());
    Assertion.assertTrue(driver.getCurrentUrl().contains("InfoboxBuilderSavingTemplate"));
  }

  @Execute(asUser = User.USER)
  public void verifyIfInputFieldIsFocusedOnSelectItem() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openNew("Infobox_verify_focus")
        .addHeaderComponent()
        .selectRowWithIndex(0);

    Assertion.assertTrue(builderPage.isInputFieldPresent());
    Assertion.assertTrue(builderPage.isSidebarInputFieldFocused());

    builderPage.selectHeaderWithIndex(0);

    Assertion.assertTrue(builderPage.isInputFieldPresent());
    Assertion.assertTrue(builderPage.isSidebarInputFieldFocused());
  }

  @Execute(asUser = User.USER)
  public void verifyChevronTooltip() {
    InfoboxBuilderPage builderPage =
        new InfoboxBuilderPage().openExisting("InfoboxBuilderChevronPopup");

    Assertion.assertTrue(builderPage.isSectionTooltipPresentBelow(0));
    Assertion.assertTrue(builderPage.isSectionTooltipPresentAbove(1));

    builderPage.addHeaderComponent().changeHeaderCollapsibilityState(2);

    Assertion.assertTrue(builderPage.isSectionTooltipPresentAbove(2));
  }

  @Execute(asUser = User.STAFF)
  public void verifyLoadingEuropaTheme() {
    new WikiFeatures().openWikiFeatures(wikiURL).enableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting("Infobox_theme_europa");

    Assertion.assertEquals(builderPage.getInfoboxWidth(), EUROPA_INFOBOX_WIDTH);
  }

  @Execute(asUser = User.STAFF)
  public void verifyLoadingDefaultTheme() {
    new WikiFeatures().openWikiFeatures(wikiURL).disableEuropaInfoboxTheme();

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting("Infobox_theme_default");

    Assertion.assertEquals(builderPage.getInfoboxWidth(), DEFAULT_INFOBOX_WIDTH);
  }

  @Execute(asUser = User.ANONYMOUS)
  public void verifyBuilderAuthentication() {
    TemplateEditPage template = new TemplateEditPage().open("InfoboxBuilderAuthentication");

    Assertion.assertTrue(template.isPermissionErrorDisplayed());
  }

  public void verifyOtherContentIsNotChanged() {
    final String templateName = "Infobox_other_content";
    final String infoboxRegexp = "(?s)<infobox[^>]*>.*</infobox>";
    String beforePublish = new TemplatePage().getRawContent(templateName)
        .replaceAll(infoboxRegexp, "");

    new TemplatePage().open(templateName).loginAs(User.USER);

    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().openExisting(templateName);

    builderPage.addRowComponent()
        .deleteRowUsingButton(0)
        .clickPublish();

    builderPage.logOut();

    String afterPublish =
        new TemplatePage().getRawContent(templateName).replaceAll(infoboxRegexp, "");

    Assert.assertEquals(beforePublish, afterPublish);
  }

  @Execute(asUser = User.USER)
  public void verifyNamingConflictTypingNameAfterClickingPublish() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().open();
    builderPage.clickPublish();

    Assertion.assertTrue(builderPage.isModalEditTitlePresent());
    Assertion.assertTrue(builderPage.isEditTemplateTitleInputPresent());

    builderPage.insertTemplateTitle("InfoboxNamingConflict").clickPublishEditedTitleButton();

    Assertion.assertTrue(builderPage.isErrorMessagePresent());
  }

  @Execute(asUser = User.USER)
  public void mercuryNavBarShouldNotBeVisible() {
    InfoboxBuilderPage builderPage = new InfoboxBuilderPage().open();

    Assertion.assertTrue(builderPage.isInfoboxBuilderOpened());
    Assertion.assertFalse(new TopBar(driver).isNavigationBarVisible());
  }
}
