package melnikov.pkmn.conf;
import melnikov.pkmn.security.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailsService jdbcUserDetailsManager;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                customizer -> customizer
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/cards/**",
                                "/api/v1/students/**",
                                "/api/v1/cards/card/image")
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/v1/cards",
                                "/api/v1/students")
                        .hasRole("ADMIN")
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        .requestMatchers("/auth/**").authenticated()
                        .anyRequest().authenticated()
        );
        httpSecurity.formLogin(form -> form.successForwardUrl("/auth/success"));
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.userDetailsService(jdbcUserDetailsManager);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}