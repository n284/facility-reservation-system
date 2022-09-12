package jp.co.ginga.web.util.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.ginga.web.util.security.handler.ErrorAccessDeniedHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 全体に対するセキュリティ設定
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/css/**", "/js/**", "/img/**");
	}

	/**
	 * 認証方法の実装の設定
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());

	}

	/**
	 * URLごとに異なるセキュリティ設定を行う
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 直リンクの禁止＆ログイン不要ページの設定
		http
				.authorizeRequests()
				//ログインページは直リンクOK
				.antMatchers("/login")
				.permitAll()
				.antMatchers("/css/**", "/js/**", "/img/**")
				.permitAll()
				.antMatchers("/error/**")
				.permitAll() // エラー

				//各ページの権限設定
				//hasRoleは、ROLE_ プレフィックスを補完します
				.antMatchers("/user/**").hasRole("ADMIN")
				.antMatchers("/facility/**").hasRole("ADMIN")
				.antMatchers("/facilitytype/**").hasRole("ADMIN")
				.anyRequest().authenticated()//それ以外は直リンク禁止

				//認証エラ―時におけるエラー遷移処理
				.and()
				.exceptionHandling().accessDeniedHandler(new ErrorAccessDeniedHandler());

		//ログイン認証
		http
				.formLogin()
				.loginPage("/login")				//ログイン画面のURL
				.defaultSuccessUrl("/menu", true)	//ログインが成功した時のURL
				.permitAll()
				.and()
				//httpsの使用を強制する設定
				.requiresChannel()
				.antMatchers("/login**")
				.requiresSecure();

		//ログアウト
		http
				.logout()
				.logoutSuccessUrl("/login")		//ログアウト成功したあとのURL
				.deleteCookies("JSESSIONID")	//ログアウト後、cookieの削除
                .invalidateHttpSession(true)	//ログアウト後、sessionの破棄
                ;
	}
}
