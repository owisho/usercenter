package per.owisho.learn.usercenter.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.AuthenticationMethod;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class OAuth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
                .anyRequest().authenticated()
        )
                .oauth2Login().loginProcessingUrl("/index.html").and()
//                .oauth2Login(configure -> configure
//                .loginProcessingUrl("/index.html")
//                .tokenEndpoint(tokenEndpointConfig ->
//                        tokenEndpointConfig.accessTokenResponseClient(responseClient(restTemplate))))
                .oauth2Client((configure) -> configure.clientRegistrationRepository(clientRegistrationRepository()));
    }

    public DefaultAuthorizationCodeTokenResponseClient responseClient(RestTemplate restTemplate) {
        DefaultAuthorizationCodeTokenResponseClient client = new DefaultAuthorizationCodeTokenResponseClient();
        client.setRestOperations(restTemplate);
        return client;
    }

    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId("registration");
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
//        builder.redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}");
        builder.redirectUri("http://localhost:9999/index.html");
        builder.authorizationUri("http://per.owisho.learn:8080/oauth/authorize");
        builder.tokenUri("http://per.owisho.learn:8080/oauth/token");
        builder.userInfoUri("http://per.owisho.learn:8080/user");
        builder.userNameAttributeName("userName");
        builder.userInfoAuthenticationMethod(AuthenticationMethod.FORM);
        builder.scope("web");
        builder.clientName("newsapp");
        builder.clientId("newsapp");
        builder.clientSecret("12345678");
        return new InMemoryClientRegistrationRepository(builder.build());
    }

}
