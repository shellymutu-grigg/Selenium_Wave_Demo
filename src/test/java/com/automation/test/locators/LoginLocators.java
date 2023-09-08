package com.automation.test.locators;

import com.automation.test.data.TextData;

public class LoginLocators {
    public final static String ACCOUNT_MENU_ID = "id:nav-link-accountList-nav-line-1";
    public final static String CONTINUE_BUTTON_ID = "id:continue";
    public final static String LANDING_PAGE_ALT_ID = "css:.nav-footer-copyright";
    public final static String LOGIN_BUTTON_ID = "id:signInSubmit";
    public final static String TRY_DIFFERENT_IMAGE_ID = "//*[contains(text(), '" + TextData.TRY_DIFFERENT_IMAGE_TEXT +"')]";
    public final static String USER_EMAIL_ID = "id:ap_email";
    public final static String USER_PASSWORD_ID = "id:ap_password";
    public final static String YOUR_ACCOUNT_LINK_ID = "linkText:" + TextData.YOUR_ACCOUNT_TEXT;
}
