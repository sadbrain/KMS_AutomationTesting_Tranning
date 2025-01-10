import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser("")
WebUI.navigateToUrl(GlobalVariable.LOGIN)

//Login
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), "admin@dotnetmastery.com")
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Password'), "Admin123*")
WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))

// Verify the fields displayed in the User List table
WebUI.click(findTestObject('Object Repository/UserManagementPage/lnk_ContentManagemen'))
WebUI.click(findTestObject('Object Repository/UserManagementPage/lnk_ManageUser'))
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/th_Name'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/th_Email'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/th_Phone'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/th_Company'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/th_Role'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/th_Action'), 10)

// Verify changing the Role of a user
WebUI.click(findTestObject('Object Repository/UserManagementPage/btn_Permission'))
WebUI.selectOptionByValue(findTestObject('Object Repository/UserManagementPage/ddl_Role'), 'Customer', false)
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/UserManagementPage/btn_UpdateRole'))
WebUI.verifyElementPresent(findTestObject('Object Repository/UserManagementPage/tr_ResultUpdate'), 10)

//Verify btn Back to List
WebUI.click(findTestObject('Object Repository/UserManagementPage/btn_Permission'))
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/UserManagementPage/btn_BackToList'))


