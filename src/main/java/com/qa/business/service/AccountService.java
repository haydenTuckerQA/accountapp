package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IAccountRepository;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountService implements IAccountService {

	@Inject
	private IAccountRepository repo;
	
	@Inject
	private JSONUtil jsonUtil;
	
	@Override
	public String createAccount(String jsonAccount) {
		Account account = jsonUtil.getObjectForJSON(jsonAccount, Account.class);
		repo.createAccount(account);
		return "{\"message\": \"Account has been successfully created!\"}";
	}

	@Override
	public String updateAccount(Long id, String jsonAccount) {
		Account updatedAccount = jsonUtil.getObjectForJSON(jsonAccount, Account.class);
		Account account = repo.updateAccount(id, updatedAccount);
		if (account != null) {
			return "{\"message\": \"Account has been successfully updated!\"}";
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}

	@Override
	public String deleteAccount(Long id) {
		Account account = repo.deleteAccount(id);
		if (account != null) {
			return "{\"message\": \"Account has been successfully deleted!\"}";
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}

	@Override
	public String getAllAccounts() {
		return jsonUtil.getJSONForObject(repo.getAllAccounts());
	}

	@Override
	public String getAccount(Long id) {
		Account account = repo.getAccount(id);
		
		if (account != null) {
			return jsonUtil.getJSONForObject(account);
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}
	
	public void setJSONUtil(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}

}
