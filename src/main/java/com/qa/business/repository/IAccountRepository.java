package com.qa.business.repository;

public interface IAccountRepository {
	
	public String createAccount(String accountAsJson);
	
	public String updateAccount(Long id, String accountAsJson);
	
	public String deleteAccount(Long id);
}
