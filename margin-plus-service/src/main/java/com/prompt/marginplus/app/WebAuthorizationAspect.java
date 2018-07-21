package com.prompt.marginplus.app;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class WebAuthorizationAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebAuthorizationAspect.class);
	 @Around("(@target(org.springframework.web.bind.annotation.RestController) || @target(org.springframework.stereotype.Controller)) && @annotation(requiresPermission)")
	    public Object assertAuthorized(ProceedingJoinPoint jp, RequiresPermissions requiresPermission) throws Throwable {
		 MethodSignature signature =  (MethodSignature)jp.getSignature();
		 Class returnType = signature.getReturnType();
		 HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	        try {
	        	Subject currentUser = SecurityUtils.getSubject();
	        	LOGGER.info("Checking permission on method : " + jp.getSignature().getName() + " for user " + currentUser.getPrincipal());
	        	currentUser.checkPermissions(requiresPermission.value());
	        	Object returnObj = jp.proceed();
	        	return returnType.cast(returnObj);
			} catch (AuthorizationException e) {
				LOGGER.error("User does not permission to view this page.");
				try {
					response.sendRedirect("/");
					return null;
				} catch (IOException e1) {
					LOGGER.error("Error redirecting to home.");
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
	        return null;
	    }
}
