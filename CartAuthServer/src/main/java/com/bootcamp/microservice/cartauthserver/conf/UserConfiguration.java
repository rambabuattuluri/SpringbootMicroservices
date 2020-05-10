package com.bootcamp.microservice.cartauthserver.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter {

    PasswordEncoder passwordEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.inMemoryAuthentication()
    	               .withUser("rambabu")
    	               .password(passwordEncoder.encode("rambabupassword"))
    	               .roles("ADMIN","USER","MANAGER","OPERATOR")
    	               .authorities("CREATE","WRITE","UPDATE","READ","DELETE")
    	               .and()
    	               .withUser("smrithika")
                       .password(passwordEncoder.encode("smrithakapassword"))
                       .roles("USER")
                       .authorities("CREATE","WRITE","READ");
    	
    }
	
}
