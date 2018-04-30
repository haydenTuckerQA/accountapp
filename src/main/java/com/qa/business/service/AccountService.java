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
	public String updateAccount(String username, String jsonAccount) {
		Account updatedAccount = jsonUtil.getObjectForJSON(jsonAccount, Account.class);
		Account oldAccount = repo.getAccount(username);
		if (oldAccount != null) {
			updatedAccount.setPassword(oldAccount.getPassword());
			repo.updateAccount(username, updatedAccount);
			return "{\"message\": \"Account has been successfully updated!\"}";
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}
	
	@Override
	public String updateAccountPassword(String username, String oldPassword, String jsonAccount) {
		Account account = repo.getAccount(username);
		Account updatedAccount = jsonUtil.getObjectForJSON(jsonAccount, Account.class);
		if (account != null) {
			if (account.getPassword().equals(oldPassword)) {
				repo.updateAccount(username, updatedAccount);
				return "{\"message\": \"Password has been successfully updated!\"}";
			} else {
				return "{\"message\": \"Incorrect old password!\"}";
			}
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}

	@Override
	public String deleteAccount(String username) {
		Account account = repo.deleteAccount(username);
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
	public String getAccount(String username) {
		Account account = repo.getAccount(username);
		
		if (account != null) {
			return jsonUtil.getJSONForObject(account);
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}
	
	@Override
	public String login(String username, String password) {
		Account account = repo.getAccount(username);
		
		if (account != null) {
			if (account.getPassword().equals(password)) {
				account.setPassword("");
				return jsonUtil.getJSONForObject(account);
			} else {
				return "{\"message\": \"Incorrect password!\"}";
			}
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}
	
	public void setJSONUtil(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
}
