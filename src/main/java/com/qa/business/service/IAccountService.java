package com.qa.business.service;

public interface IAccountService {
	String createAccount(String jsonAccount);
	String updateAccount(String username, String jsonAccount);
	String deleteAccount(String username);
	String getAllAccounts();
	String getAccount(String username);
	String login(String username, String password);
	String updateAccountPassword(String username, String oldPassword, String jsonAccount);
}
