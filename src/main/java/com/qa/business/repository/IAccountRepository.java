package com.qa.business.repository;

public interface IAccountRepository {
	String createAccount(String jsonAccount);
	String updateAccount(Long id, String jsonAccount);
	String deleteAccount(Long id);
}
