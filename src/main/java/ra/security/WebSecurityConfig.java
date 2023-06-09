package ra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.jwt.JwtAuthenticationFilter;

//import ra.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService) // Cung cap customUserDetailService cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() // Ngăn chặn request từ một domain khác
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/register").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
                .antMatchers("/api/v1/user/login").permitAll()
                .antMatchers("/api/v1/catalog/getCatalogForUser").permitAll()
                .antMatchers("/api/v1/color/getColorForUser").permitAll()
                .antMatchers("/api/v1/size/getSizeForUser").permitAll()
                .antMatchers("/api/v1/product/displayProduct").permitAll()
                .antMatchers("/api/v1/file/**").permitAll()
                .antMatchers("/api/v1/productDetail/getProductDetailForPageByProductId/{proId}").permitAll()
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập

//         Thêm một lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
