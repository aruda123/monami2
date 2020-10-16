package monami.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;
import monami.service.CustomOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeRequests()
					.antMatchers("/", "/css/**", "/images/**", "/js/**", "/member/**", "/board/**").permitAll()
					//.antMatchers("/member/**", "/board/**").hasRole(Role.USER.name())
					//.anyRequest().authenticated()
			.and()
			
				.logout()
					.logoutSuccessUrl("/")
			.and()
				.oauth2Login()
					.userInfoEndpoint()
						.userService(customOAuth2UserService);
						
	}
	
	
	

}
