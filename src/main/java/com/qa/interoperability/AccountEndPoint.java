package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
	
	@DELETE
	@Path("/delete/{id}")
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);
	}
	
	@GET
	@Path("/get")
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	public String getAccount(@PathParam("id") Long id) {
		return service.getAccount(id);
	}
}
