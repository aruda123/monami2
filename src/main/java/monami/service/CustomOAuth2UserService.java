package monami.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import monami.domain.dto.OAuthAttributes;
import monami.domain.dto.SessionUser;
import monami.domain.entity.User;
import monami.domain.entity.UserRepository;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
	
	private final UserRepository userRepository;
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
									   .getUserInfoEndpoint().getUserNameAttributeName();
		
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		User user = saveOrUpdate(attributes);
		
		httpSession.setAttribute("login", new SessionUser(user));
		
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
				, attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	private User saveOrUpdate(OAuthAttributes attributes) {
		
		User user = userRepository.findByEmail(attributes.getEmail())
				.map(e -> e.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		return userRepository.save(user);
	}

}
