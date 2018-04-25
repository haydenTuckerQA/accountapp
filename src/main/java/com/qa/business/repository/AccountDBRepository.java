package com.qa.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class AccountDBRepository implements IAccountRepository {
	
	private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);
	
	@PersistenceContext(unitName ="primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil jsonUtil;
	
	@Override
	@Transactional(REQUIRED)
	public String createAccount(String jsonAccount) {
		//LOGGER.info("AccountDBRepository createAccount");
		Account account = jsonUtil.getObjectForJSON(jsonAccount, Account.class);
		this.manager.persist(account);
		return "{\"message\": \"Account has been successfully created!\"}";
	}

	@Override
	public String updateAccount(Long id, String jsonAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setJSONUtil(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}

}
