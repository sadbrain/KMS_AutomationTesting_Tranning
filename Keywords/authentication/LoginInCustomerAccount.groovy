package authentication

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class LoginInCustomerAccount {
	static def login() {
		WebUI.openBrowser("")
		WebUI.navigateToUrl(GlobalVariable.LOGIN)

		//Verify header
		WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/LoginPage/hrd_Header')), "Log in" )

		//Enter Email and Password
		WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Email'), "Customer1@gmail.com")
		WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Password'), "Customer1@gmail.com")

		WebUI.click(findTestObject('Object Repository/LoginPage/btn_Login'))

		WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/LoginPage/lnk_Home')), "HOME" )
	}
}
