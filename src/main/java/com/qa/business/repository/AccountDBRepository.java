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
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String jsonAccount) {
		//LOGGER.info("AccountDBRepository updateAccount");
		Account updatedAccount = jsonUtil.getObjectForJSON(jsonAccount, Account.class);
		Account oldAccount = findAccount(id);
		
		if (oldAccount != null) {
			manager.merge(updatedAccount);
			return "{\"message\": \"Account has been successfully updated!\"}";
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		//LOGGER.info("AccountDBRepository deleteAccount");
		Account account = findAccount(id);
		
		if (account != null) {
			manager.remove(account);
			return "{\"message\": \"Account has been successfully deleted!\"}";
		} else {
			return "{\"message\": \"Account does not exist!\"}";
		}
	}

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setJSONUtil(JSONUtil jsonUtil) {
		this.jsonUtil = jsonUtil;
	}
	
	private Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}
}
