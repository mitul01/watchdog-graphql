package com.watchdog.watchdog.dto;

public class Constants {

    public final static String defaultAccountName = "PERSONAL";

    // Error Messages
    public final static String entityNotFoundErrorMsg = "No such %s exists";
    public final static String userNotFoundInAccountErrorMsg = "No such user exists in the selected account";
    public final static String expenseSplitSumNotMatchErrorMsg = "Split Expenses sum does not match total expense sum";
    public final static String expenseSplitRequiredErrorMsg = "No expense split found. Enter individual splits for the expense for this account as there are multiple users associated with it";

    // Logging Info Messages
    public final static String getRequestInfoLogMsg = "Received a request to fetch %s from database";
    public final static String createRequestInfoLogMsg = "Received a request to create new %s with payload %s";
    public final static String updateRequestInfoLogMsg = "Received a request to update %s(%s) with payload %s";
    public final static String deleteRequestInfoLogMsg = "Received a request to delete %s(%s) from database";
    public final static String createDefaultAccountInfoLogMsg = "Created new default account for user(%s)";

}
