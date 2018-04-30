package com.qa.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Default
@Transactional(SUPPORTS)
public class AccountDBRepository implements IAccountRepository {
	
	private static final Logger LOGGER = Logger.getLogger(AccountDBRepository.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Override
	@Transactional(REQUIRED)
	public Account createAccount(Account account) {
		//LOGGER.info("AccountDBRepository createAccount");
		this.manager.persist(account);
		return account;
	}

	@Override
	@Transactional(REQUIRED)
	public Account updateAccount(String username, Account updatedAccount) {
		//LOGGER.info("AccountDBRepository updateAccount");
		Account oldAccount = findAccount(username);
		
		if (oldAccount != null) {
			manager.merge(updatedAccount);
			return updatedAccount;
		} else {
			return oldAccount;
		}
	}

	@Override
	@Transactional(REQUIRED)
	public Account deleteAccount(String username) {
		//LOGGER.info("AccountDBRepository deleteAccount");
		Account account = findAccount(username);
		
		if (account != null) {
			manager.remove(account);
		}
		return account;
	}
	
	@Override
	public List<Account> getAllAccounts() {
		//LOGGER.info("AccountDBRepository getAllAccounts");
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a", Account.class);
		return query.getResultList();
	}
	
	@Override
	public Account getAccount(String username) {
		//LOGGER.info("AccountDBRepository getAccount");
		return findAccount(username);
	}
	
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	private Account findAccount(String username) {
		return manager.find(Account.class, username);
	}

	
}
