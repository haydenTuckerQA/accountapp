package com.qa.business.repository;

import java.util.List;

import com.qa.persistence.domain.Account;

public interface IAccountRepository {
	Account createAccount(Account account);
	Account updateAccount(String username, Account updatedAccount);
	Account deleteAccount(String username);
	List<Account> getAllAccounts();
	Account getAccount(String username);
}
