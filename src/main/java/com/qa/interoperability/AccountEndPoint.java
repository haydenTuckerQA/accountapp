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
	@Path("/update/{username}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateAccount(@PathParam("username") String username, String jsonAccount) {
		return service.updateAccount(username, jsonAccount);
	}
	
	@PUT
	@Path("/update/{username}/{oldPassword}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateAccountPassword(@PathParam("username") String username, @PathParam("oldPassword") String oldPassword, String jsonAccount) {
		return service.updateAccountPassword(username, oldPassword, jsonAccount);
	}
	
	@DELETE
	@Path("/delete/{username}")
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("username") String username) {
		return service.deleteAccount(username);
	}
	
	@GET
	@Path("/get")
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}
	
	@GET
	@Path("/get/{username}")
	@Produces({ "application/json" })
	public String getAccount(@PathParam("username") String username) {
		return service.getAccount(username);
	}
	
	@GET
	@Path("/login/{username}/{password}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		return service.login(username, password);
	}
}
