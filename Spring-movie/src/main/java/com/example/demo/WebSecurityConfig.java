package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <dd>セキュリティ設定クラス
 * <dl>DB認証
 * @author yuya
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// AUTHORIZE
				.authorizeRequests()
				/* ↓認証なしで見れるページ */
				.mvcMatchers("/hello").permitAll()
				/* ↓上記以外は認証を必要とする */
				.anyRequest()/*    */.authenticated().and()
				// LOGIN
				.formLogin()
				/* ↓ログイン成功後のデフォルトページ→ */
				.defaultSuccessUrl("/success");
	}
}
