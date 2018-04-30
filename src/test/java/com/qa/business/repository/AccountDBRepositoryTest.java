package com.qa.business.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {
	

	@InjectMocks
	private AccountDBRepository repo;
	
	@Mock
	private EntityManager manager;
	
	@Mock
	private TypedQuery<Account> query;
	
	private Account account;
	private List<Account> accounts;
	
	//private static final String ACCOUNT_JSON = "{\"username\":\"username\",\"password\":\"password\",\"firstName\":\"Hayden\",\"lastName\":\"Tucker\",\"accountNumber\":\"1234\",\"accountType\":\"USER\"}";
	//private static final String ACCOUNT_LIST_JSON = "["{\"username\":\"username\",\"password\":\"password\",\"firstName\":\"Hayden\",\"lastName\":\"Tucker\",\"accountNumber\":\"1234\",\"accountType\":\"USER\"}";]";
	
	@Before
	public void initialise() {
		repo.setEntityManager(manager);
		
		account = new Account("username", "password", "Hayden", "Tucker", "1234", "USER");
		accounts = new ArrayList<Account>();
		accounts.add(account);
		//repo.setJSONUtil(jsonUtil);
	}
	
	@Test
	public void testCreateAccount() {
		Account expectedValue = account;
		Account actualValue = repo.createAccount(account);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testUpdateAccount() {
		account.setAccountNumber("1235");
		Account expectedValue = null;
		Account actualValue = repo.updateAccount("username", account);
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(account);
		
		expectedValue = account;
		actualValue = repo.updateAccount("username", account);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testDeleteAccount() {
		Account expectedValue = null;
		Account actualValue = repo.deleteAccount("username");
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(account);
		
		expectedValue = account;
		actualValue = repo.deleteAccount("username");
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString(), Mockito.eq(Account.class))).thenReturn(query);
		
		Mockito.when(query.getResultList()).thenReturn(accounts);
		
		List<Account> expectedValue = accounts;
		List<Account> actualValue =  repo.getAllAccounts();
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testGetAccount() {
		Account expectedValue = null;
		Account actualValue = repo.getAccount("username");
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(account);
		expectedValue = account;
		actualValue = repo.getAccount("username");
		Assert.assertEquals(expectedValue, actualValue);
	}
}
