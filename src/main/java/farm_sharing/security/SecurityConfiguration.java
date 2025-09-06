package farm_sharing.security;

import farm_sharing.security.jwt.JwtFilter;
import farm_sharing.user.model.Role;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    private final JwtFilter jwtFilter;
    final CustomWebSecurity webSecurity;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        })
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/offers").permitAll()
                        .requestMatchers("/cart/**").hasRole(Role.CLIENT.name())
                        .requestMatchers(HttpMethod.DELETE,"/user/{nickname}")
                            .access(new WebExpressionAuthorizationManager("authentication.name == #nickname || hasRole('ADMINISTRATOR')"))
                        .requestMatchers("/user/new-admin")
                            .hasRole(Role.ADMINISTRATOR.name())
                        .requestMatchers(HttpMethod.PUT,"/offers/{id}")
                            .access((auth,context) -> new AuthorizationDecision(webSecurity.checkOfferOwner(Long.valueOf(context.getVariables().get("id")),auth.get().getName())))
                        .requestMatchers(HttpMethod.DELETE,"/offers/{id}")
                        .access((auth,context) -> {
                            boolean checkOfferOwner = webSecurity.checkOfferOwner(Long.valueOf(context.getVariables().get("id")),auth.get().getName());
                            boolean checkAdmin = context.getRequest().isUserInRole(Role.ADMINISTRATOR.name());
                            return new AuthorizationDecision(checkOfferOwner || checkAdmin);
                        })
                        .anyRequest().authenticated()
                );
        return http.build();

    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
