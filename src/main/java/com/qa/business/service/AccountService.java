package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IAccountRepository;

public class AccountService implements IAccountService {

	@Inject
	private IAccountRepository repo;
	
	@Override
	public String createAccount(String jsonAccount) {
		return repo.createAccount(jsonAccount);
	}

	@Override
	public String updateAccount(Long id, String jsonAccount) {
		return repo.updateAccount(id, jsonAccount);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

}
