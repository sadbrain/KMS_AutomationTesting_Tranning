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
WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/LoginPage/hrd_Header')), "Log in" )

//Verify login with empty email
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), "")
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Password'), "Jc7xH2iGJk-6CP*")
WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))
WebUI.verifyElementText(findTestObject('Object Repository/LoginPage/lbl_ErrorEmail'), 'The Email field is required.')

//Verify login with empty password
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), "test@gmail.com")
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Password'), "")
WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))
WebUI.verifyElementText(findTestObject('Object Repository/LoginPage/lbl_ErrorPassword'), 'The Password field is required.')

//Verify login with empty both email and password
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), "")
WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Password'), "")
WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))
WebUI.verifyElementText(findTestObject('Object Repository/LoginPage/lbl_ErrorEmail'), 'The Email field is required.')
WebUI.verifyElementText(findTestObject('Object Repository/LoginPage/lbl_ErrorPassword'), 'The Password field is required.')
