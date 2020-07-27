package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
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

	/** ↓メッセージソース */
	@Autowired
	MessageSource ms;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* ↓静的リソースには認証不要 */
		http.authorizeRequests().antMatchers("/css/**","/js/**","/img/**").permitAll();
		http
				// AUTHORIZE
				.authorizeRequests()
				/* ↓認証なしで見れるページ */
				.mvcMatchers("/hello").permitAll()
				/* ↓上記以外は認証を必要とする */
				.anyRequest()/*    */.authenticated().and()
				// LOGIN
				.formLogin()
				//↓ログインページに遷移するとしてのリクエストを指定。認証不要。ここではメインコントローラーのIndexメソッドに一旦飛ばしてそこからログインページに誘導している。
				.loginPage("/movies").permitAll()
				/* ↓ログイン成功後のデフォルトページ→ */
				.defaultSuccessUrl("/success");
		
		//↓メッセージをカスタマイズするためにメッセージソースを設定
		AuthenticationManager authentication = this.authenticationManager();

        if (authentication instanceof ProviderManager) {
            ProviderManager a2 = (ProviderManager)authentication;
            a2.getProviders().forEach(p -> {
                if (p instanceof MessageSourceAware) {
                    ((MessageSourceAware)p).setMessageSource(ms);
                }
            });
        }
	}
}
