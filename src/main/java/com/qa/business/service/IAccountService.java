package com.qa.business.service;

public interface IAccountService {
	String createAccount(String jsonAccount);
	String updateAccount(Long id, String jsonAccount);
	String deleteAccount(Long id);
}
