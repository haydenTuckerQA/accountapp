package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qa.business.service.IAccountService;

@Path("account")
public class AccountEndPoint {
	
	@Inject
	private IAccountService service;
	
	@POST
	@Path("create_account")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String createAccount(String jsonAccount) {
		return service.createAccount(jsonAccount);
	}
}
