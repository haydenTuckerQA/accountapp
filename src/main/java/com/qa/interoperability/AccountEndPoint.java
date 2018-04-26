package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.IAccountService;

@Path("account")
public class AccountEndPoint {
	
	@Inject
	private IAccountService service;
	
	@POST
	@Path("/create")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String createAccount(String jsonAccount) {
		return service.createAccount(jsonAccount);
	}
	
	@PUT
	@Path("/update/{id}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateAccount(@PathParam("id") Long id, String jsonAccount) {
		return service.updateAccount(id, jsonAccount);
	}
}
