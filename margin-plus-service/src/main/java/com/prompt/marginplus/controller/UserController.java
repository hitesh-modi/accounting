package com.prompt.marginplus.controller;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.prompt.marginplus.models.UserModel;
import com.prompt.marginplus.services.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prompt.marginplus.exceptions.UserWithEmailExistsException;
import com.prompt.marginplus.exceptions.UserWithMobileExistsException;
import com.prompt.marginplus.models.AuthInfo;
import com.prompt.marginplus.models.Response;
import com.prompt.marginplus.models.ResponseStatus;
import com.prompt.marginplus.models.StateModel;

@Controller("userController")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IUserService userService;
	

	@RequestMapping("/logout")
	public String logout() {
		LOGGER.info("Logout request recieved");
		if(userService.logout()) {
				return "redirect:/";
		}
		return "";
	}
	
	
	@PostMapping("/login")
	public @ResponseBody UserModel login(@Valid @RequestBody String data) {
		LOGGER.info("Login method called with data : " + data);
		UserModel user = null;
		try {
			AuthInfo authData = new ObjectMapper().readValue(data, AuthInfo.class);
			userService.login(authData);
			user = userService.getUserInfo();

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@RequestMapping(value="/getUser")
	@RequiresPermissions("dashboard")
	public @ResponseBody
    UserModel getUserInfo() {
		return userService.getUserInfo();
	}

	@RequestMapping(value="/isUserAuthenticated")
	public @ResponseBody
	Boolean isUserAuthenticated() {
		return userService.isUserAuthenticated();
	}
	
	@RequestMapping(value="/getStates")
	public @ResponseBody Collection<StateModel> getStates() {
		return userService.getStates();
	}
	
	@RequestMapping(value="/greeting")
	@RequiresPermissions("dashboard")
	public String hello() {
		return "greeting";
	}
	
	@RequestMapping(value="/registerUser")
	public @ResponseBody Response registerUser(@Valid @RequestBody String userjson) {
		Response response = null;
		try {
			LOGGER.info("Request recieved for registering user: "+userjson);
			UserModel user = new ObjectMapper().readValue(userjson, UserModel.class);
			userService.registerUser(user);
			response = new Response(ResponseStatus.RESPONSE_SUCCESS.getStatus());
			LOGGER.info("User registered successfully : " + user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserWithEmailExistsException e) {
			LOGGER.info(e.getMessage());
			response = new Response(ResponseStatus.RESPONSE_USER_EMAIL_EXISTS.getStatus());
		} catch (UserWithMobileExistsException e) {
			LOGGER.info(e.getMessage());
			response = new Response(ResponseStatus.RESPONSE_USER_MOBILE_EXISTS.getStatus());
		}
		return response;
	}
	
}
