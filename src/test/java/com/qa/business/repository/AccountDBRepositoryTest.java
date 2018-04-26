package com.qa.business.repository;

import static org.junit.Assert.*;

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
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {

	@InjectMocks
	private AccountDBRepository repo;
	
	@Mock
	private EntityManager manager;
	
	@Mock
	private TypedQuery<Account> query;
	
	@Mock
	Account account = new Account("Hayden", "Tucker", "1234");
	
	private JSONUtil jsonUtil;
	
	private static final String ACCOUNT_AS_JSON = "{\"firstName\":\"Hayden\",\"secondName\":\"Tucker\",\"accountNumber\":\"1234\"}";
	
	@Before
	public void initialise() {
		repo.setEntityManager(manager);
		jsonUtil = new JSONUtil();
		repo.setJSONUtil(jsonUtil);
	}
	
	@Test
	public void testCreateAccount() {
		String expectedValue = "{\"message\": \"Account has been successfully created!\"}";
		String actualValue = repo.createAccount(ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testUpdateAccount() {
		String expectedValue = "{\"message\": \"Account does not exist!\"}";
		String actualValue = repo.updateAccount(123L, ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(account);
		
		expectedValue = "{\"message\": \"Account has been successfully updated!\"}";
		actualValue = repo.updateAccount(123L, ACCOUNT_AS_JSON);
		Assert.assertEquals(expectedValue, actualValue);
	}

	@Test
	public void testDeleteAccount() {
		String expectedValue = "{\"message\": \"Account does not exist!\"}";
		String actualValue = repo.deleteAccount(123L);
		Assert.assertEquals(expectedValue, actualValue);
		
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(account);
		
		expectedValue = "{\"message\": \"Account has been successfully deleted!\"}";
		actualValue = repo.deleteAccount(123L);
		Assert.assertEquals(expectedValue, actualValue);
	}
}
