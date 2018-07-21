package com.prompt.marginplus.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import com.prompt.marginplus.models.UserModel;
import com.prompt.marginplus.repositories.StateRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.prompt.marginplus.entities.State;
import com.prompt.marginplus.entities.User;
import com.prompt.marginplus.entities.UserRole;
import com.prompt.marginplus.exceptions.UserWithEmailExistsException;
import com.prompt.marginplus.exceptions.UserWithMobileExistsException;
import com.prompt.marginplus.models.AuthInfo;
import com.prompt.marginplus.models.StateModel;
import com.prompt.marginplus.repositories.UserRepository;
import com.prompt.marginplus.repositories.UserRoleRepository;
import com.prompt.marginplus.utility.Util;

@Component("userService")
public class UserService implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Resource(name="stateRepo")
	private StateRepository stateRepo;
	
	@Resource(name="userRepo")
	private UserRepository userRepo;
	
	@Resource(name="userRoleRepo")
	private UserRoleRepository userRoleRepo;
	
	@Override
	public boolean login(AuthInfo authInfo) {
		boolean loginSuccess = false;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			LOGGER.info("Login service called for user : " + currentUser.getPrincipal());
			
			if(!currentUser.isAuthenticated()) {
				LOGGER.info("User " + authInfo.getUser() + " is not authenticated hence logging the user into the system.");
				UsernamePasswordToken token = new UsernamePasswordToken(authInfo.getUser(), authInfo.getPassword());
				currentUser.login(token);
				loginSuccess = true;
				LOGGER.info("User " + authInfo.getUser() + " logged in successfully.");
			} 
			else {
				loginSuccess = true;
			}
		} catch (AuthenticationException e) {
			LOGGER.error("Error while loging in the user " + authInfo.getUser());
			loginSuccess = false;
		}
		
		return loginSuccess;
	}
	
	@Override
	public UserModel getUserInfo() {
		String userid = (String)SecurityUtils.getSubject().getPrincipal();
		
		User user = userRepo.getUser(userid);
		UserModel userModel = new UserModel();
		userModel.setUserid(user.getUsername());
		userModel.setUserName(constructUserName(user.getFirstname(), user.getMiddlename(), user.getLastname()));
		userModel.setAddress(user.getAddress());
		userModel.setGstin(user.getGstin());
		userModel.setFirmName(user.getBusinessname());
		StateModel state = new StateModel();
		state.setStatename(user.getState().getStatename());
		state.setStatecode(user.getState().getStatecode());
		userModel.setState(state);
		return userModel;
	}
	
	@Override
	public Collection<StateModel> getStates() {
		Iterable<State> states = stateRepo.findAll();
		LOGGER.info("Getting states from database");
		Collection<StateModel> stateList = new ArrayList<>();
		for (State state : states) {
			StateModel stateModel = new StateModel();
			stateModel.setStatecode(state.getStatecode());
			stateModel.setStatename(state.getStatename());
			stateList.add(stateModel);
		}
		LOGGER.info("Got " + stateList.size() + " states from DB.");
		return stateList;
	}

	@Override
	public void registerUser(UserModel userToRegister) throws UserWithEmailExistsException, UserWithMobileExistsException{
		
		LOGGER.info("Checking if user with email id or phone number already exists.");
		User existingUserByEmail = userRepo.checkUserExistenceByEmail(userToRegister.getEmail());
		if(existingUserByEmail != null) {
			throw new UserWithEmailExistsException("User already exists with email id");
		}
		
		User existingUserByContact = userRepo.checkUserExistenceByContactNumber(userToRegister.getContactNumber());
		if(existingUserByContact != null) {
			throw new UserWithMobileExistsException("User already exists with contact");
		}
		
		LOGGER.info("Registering user : "+ userToRegister);
		User user = new User();
		user.setAddress(userToRegister.getAddress());
		user.setBusinessname(userToRegister.getFirmName());
		user.setFirstname(userToRegister.getFname());
		user.setMiddlename(userToRegister.getMname());
		user.setLastname(userToRegister.getLname());
		user.setGstin(userToRegister.getGstin());
		user.setContactnumber(userToRegister.getContactNumber());
		
		State state = stateRepo.findById(userToRegister.getState().getStatecode()).get();
		
		user.setState(state);
		user.setUsername(userToRegister.getEmail());
		user.setPassword(Util.getDecodedPassword(userToRegister.getPassword()));
		userRepo.save(user);
		
		List<UserRole> roles = new ArrayList<>();
		UserRole role = new UserRole();
		role.setRoleName("normalUser");
		role.setUsername(userToRegister.getEmail());
		roles.add(role);
		user.setUserRoles(roles);
		
		userRoleRepo.save(role);
		
		LOGGER.info("User registered successfully");
	}

	@Override
	public boolean logout() {
		Subject currUser = SecurityUtils.getSubject();
		boolean returnValue = false;
		LOGGER.info("Logging out user : " + currUser.getPrincipal());
		try {
			currUser.getSession().stop();
			returnValue = true;
		} catch (InvalidSessionException e) {
			e.printStackTrace();
		}
		LOGGER.info("Logged out user : " + currUser.getPrincipal());
		return returnValue;
	}
	
	private String constructUserName(String fname, String mname, String lname) {
		StringBuilder nameBuilder = new StringBuilder();
		nameBuilder.append(fname);
		if(!StringUtils.isEmpty(mname)) {
			nameBuilder.append(" ");
			nameBuilder.append(mname);
		}
		nameBuilder.append(" ");
		nameBuilder.append(lname);
		return nameBuilder.toString();
	}
	
}
