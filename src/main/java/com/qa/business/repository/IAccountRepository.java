package com.qa.business.repository;

public interface IAccountRepository {
	
	public String createAccount(String jsonAccount);
	
	public String updateAccount(Long id, String jsonAccount);
	
	public String deleteAccount(Long id);
}
