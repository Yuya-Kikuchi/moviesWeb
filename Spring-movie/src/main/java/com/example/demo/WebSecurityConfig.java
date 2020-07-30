package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.security.DemoUserDetailsService;

/**
 * <dd>セキュリティ設定クラス
 * <dl>DB認証
 * @author yuya
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** <DD>ユーザー情報のサービスクラスのメソッドを使えるようインスタンス化 */
    @Autowired
    private DemoUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // AUTHORIZE
                .authorizeRequests()
                /* ↓認証なしで見れるページ */
                .antMatchers("/movies/sign_up").permitAll()
                /* ↓上記以外は認証を必要とする */
                .anyRequest()/*    */.authenticated().and()
                // LOGIN
                .formLogin().loginPage("/movies/login") //←ログインページに遷移するとしてのリクエストを指定。認証不要。ここではメインコントローラーのIndexメソッドに一旦飛ばしてそこからログインページに誘導している。
                .loginProcessingUrl("/login") //←フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
                .usernameParameter("eMail") //←リクエストパラメータのname属性を明示
                .passwordParameter("password").permitAll()
                /* ↓ログイン成功後のデフォルトページ→ */
                .defaultSuccessUrl("/movies/success", true);
    }

    /**
     * <DD>認可設定を無視するリクエストを設定
     * <DL>静的リソース(image,javascript,css)を認可処理の対象から除外する
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/css/**", "/javascript/**");
    }

    /**
     * <DD>フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * <DD>認証時に利用するデータソースを定義する設定メソッド
     * <DL>ここではDBから取得したユーザ情報をuserDetailsServiceへセットすることで認証時の比較情報としている
     * @param auth アウストラロピテクス
     * @throws Exception
     * AuthenticationManagerBuilderは認証系の機能を有している。
     * userDetailsServiceもその一つでフォームに入力されたユーザが使用可能か判断します。
     * https://docs.spring.io/spring-security/site/docs/4.0.x/apidocs/org/springframework/security/config/annotation/authentication/builders/AuthenticationManagerBuilder.html
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        /*
         * ↓インメモリの場合は以下を使います。 auth .inMemoryAuthentication()
         * .withUser("user").password("{noop}password").roles("USER");
         */
    }
}
