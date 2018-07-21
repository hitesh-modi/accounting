package com.prompt.marginplus.models;

import java.util.Base64;

public class AuthInfo {
	
	private String authorization;

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	public String getUser() {
		String authWithoutBasic = authorization.replace("Basic ", "");
		byte[] authDecoded = Base64.getDecoder().decode(authWithoutBasic);
		String authInfo = new String(authDecoded);
		String[] authInfoSplitted = authInfo.split(":");
		return authInfoSplitted[0];
	}
	
	public String getPassword() {
		String authWithoutBasic = authorization.replace("Basic ", "");
		byte[] authDecoded = Base64.getDecoder().decode(authWithoutBasic);
		String authInfo = new String(authDecoded);
		String[] authInfoSplitted = authInfo.split(":");
		return authInfoSplitted[1];
	}
	
}
