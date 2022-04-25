package com.gft.security;

import com.gft.filter.FiltroAutenticacao;
import com.gft.services.AutenticacaoService;
import com.gft.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
        authManager.userDetailsService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/categorias/*").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/api/categorias").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/api/categorias/*").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/api/categorias").hasAnyAuthority("user", "admin")
                .antMatchers(HttpMethod.GET, "/api/categorias/*").hasAnyAuthority("user", "admin")

                .antMatchers(HttpMethod.PUT, "/api/starter/*").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/api/starter").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/api/upload/starter").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE, "/api/starter/*").hasAuthority("admin")
                .antMatchers(HttpMethod.GET, "/api/starter").hasAnyAuthority("user", "admin")
                .antMatchers(HttpMethod.GET, "/api/starter/*").hasAnyAuthority("user", "admin")

                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new FiltroAutenticacao(autenticacaoService, usuarioService),
                        UsernamePasswordAuthenticationFilter.class);
    }
}
