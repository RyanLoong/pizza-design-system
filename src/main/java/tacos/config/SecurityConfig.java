package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author RyanLoong
 * @Date 2021/5/7 20:10
 * @Classname SecurityConfig
 * @Description SpringSecurity的配置类
 * EnableWebSecurity注解有两个作用:
 *  1.加载了WebSecurityConfiguration配置类, 配置安全认证策略。
 *  2.加载了AuthenticationConfiguration, 配置了认证信息。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 返回一个ExpressionInterceptUrlRegister对象通过这个对象可以设置一些url的安全访问要求
                .authorizeRequests()
                    // 表示只有具有ROLE_USER的权限的用户可以访问"design"和"orders"，也就是说没有获得token的请求是无法访问design和order页面的
                    .antMatchers("/design","/orders")
                        .access("hasRole('ROLE_USER')")
                    // 其他的请求所有用户都可以访问
                    .antMatchers("/","/**").permitAll()
                // 表示连接请求的方法
                .and()
                    // 配置自定义登陆表单
                    .formLogin()
                        .loginPage("/login")
                        // 设置登陆过好跳转的页面，第二个参数是无论请求前是那个页面登陆成功后都跳转到第一个参数设置的页面
                        .defaultSuccessUrl("/design",true)
                .and()
                    // 设置登出之后的跳转路径
                    .logout()
                    .logoutSuccessUrl("/");
    }
}
