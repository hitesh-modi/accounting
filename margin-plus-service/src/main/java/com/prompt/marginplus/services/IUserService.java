package com.prompt.marginplus.services;

import java.util.Collection;

import com.prompt.marginplus.exceptions.UserWithEmailExistsException;
import com.prompt.marginplus.exceptions.UserWithMobileExistsException;
import com.prompt.marginplus.models.AuthInfo;
import com.prompt.marginplus.models.StateModel;
import com.prompt.marginplus.models.UserModel;

public interface IUserService {
	
	public boolean login(AuthInfo authInfo);

	public boolean logout();
	
	public UserModel getUserInfo();

	public boolean isUserAuthenticated();

	public Collection<StateModel> getStates();

	public void registerUser(UserModel userToRegister) throws UserWithEmailExistsException,UserWithMobileExistsException;

}
