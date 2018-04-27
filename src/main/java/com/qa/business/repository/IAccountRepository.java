package com.qa.business.repository;

import java.util.List;

import com.qa.persistence.domain.Account;

public interface IAccountRepository {
	Account createAccount(Account account);
	Account updateAccount(Long id, Account updatedAccount);
	Account deleteAccount(Long id);
	List<Account> getAllAccounts();
	Account getAccount(Long id);
}
