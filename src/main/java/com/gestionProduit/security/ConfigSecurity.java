package com.gestionProduit.security;

/*import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity*/
public class ConfigSecurity /* extends WebSecurityConfigurerAdapter */ {

	/*
	 * @Autowired private DataSource dataSource;
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.inMemoryAuthentication().withUser("admin").password("1234").roles(
	 * "USER", "ADMIN"); // ça veut dire que les utilisateurs sont en mémoire pour
	 * le moment. l'admin à deux roles : USER et ADMIN
	 * auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER")
	 * ;
	 * 
	 * auth.jdbcAuthentication() .dataSource(dataSource) // la meme base de donnée
	 * de l'application
	 * .usersByUsernameQuery("select login as principal,pass as credentials,active from users where login=?"
	 * ) // cherche si l'utulisateur existe ou pas (il connait le utilisateur et le
	 * mot de passe)
	 * .authoritiesByUsernameQuery("select login as principal, role as role from users_roles where login=? "
	 * ) // pour conner le role de l'utulisateur .passwordEncoder(new
	 * Md5PasswordEncoder()) .rolePrefix("ROLE_");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.formLogin().loginPage("/login"); // c'est veut dire entrain d'indiqué à
	 * spring security que l'opération d'authentification passe dabord par un
	 * formulaire d'authentification http.csrf().disable();
	 * http.authorizeRequests().antMatchers("/user/*" "/index" ).hasRole("USER"); //
	 * ça veut dire que une requette http dans l'URL /index nécessite une
	 * authentification avec un utilisateur qui à le role USER
	 * http.authorizeRequests().antMatchers("/admin/*" "/form", "/save", "/edit",
	 * "/delete" ).hasRole("ADMIN");
	 * http.exceptionHandling().accessDeniedPage("/403"); }
	 * 
	 * }
	 */
}
