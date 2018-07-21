package com.prompt.marginplus.app;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Configuration
public class ShiroConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public Realm realm() {
		// uses 'classpath:shiro-users.properties' by default
		/*PropertiesRealm realm = new PropertiesRealm();
		// Caching isn't needed in this example, but we can still turn it on
		realm.setCachingEnabled(true);
		realm.init();*/
		
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(datasource);
		jdbcRealm.setPermissionsLookupEnabled(true);
		jdbcRealm.init();
		return jdbcRealm;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
	    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
	    shiroFilter.setLoginUrl("/login");
	    shiroFilter.setSuccessUrl("/index");
	    shiroFilter.setUnauthorizedUrl("/forbidden");
	    Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();
	   // filterChainDefinitionMapping.put("/services/**", "authcBasic");
	    shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
	    shiroFilter.setSecurityManager(securityManager());
	    /*Map<String, Filter> filters = new HashMap<String, Filter>();
	    filters.put("authc", new FormAuthenticationFilter());
	    filters.put("logout", new LogoutFilter());
	    filters.put("roles", new RolesAuthorizationFilter());
	    filters.put("user", new UserFilter());
	    shiroFilter.setFilters(filters);*/
	    return shiroFilter;
	}
	
	@Bean(name = "securityManager")
	public SecurityManager securityManager() {
	    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	    securityManager.setRealm(realm());
	    return securityManager;
	}

	@Bean
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@ExceptionHandler(UnauthenticatedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public void handleException(UnauthenticatedException e) {
		LOGGER.debug("{} was thrown", e);
	}
	
}
