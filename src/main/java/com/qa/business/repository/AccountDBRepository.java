package com.qa.business.repository;

import javax.persistence.EntityManager;

import com.qa.util.JSONUtil;

public class AccountDBRepository implements IAccountRepository {

	@Override
	public String createAccount(String accountAsJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAccount(Long id, String accountAsJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEntityManager(EntityManager manager) {
		// TODO Auto-generated method stub
		
	}

	public void setJSONUtil(JSONUtil jsonUtil) {
		// TODO Auto-generated method stub
		
	}

}
