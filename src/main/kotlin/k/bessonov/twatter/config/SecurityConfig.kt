package k.bessonov.twatter.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration

const val twatterRealmName = "Twatter authentication"
const val authority = "TWATTER_USER"

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig(private val entryPoint: EntryPoint,
                     private val userDetailService: UserDetailService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .cors().configurationSource { CorsConfiguration().applyPermitDefaultValues() }.and()
                .authorizeRequests()
                .antMatchers("/api/**").hasAuthority(authority)
                .and().httpBasic().realmName(twatterRealmName)
                .authenticationEntryPoint(entryPoint)
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService).passwordEncoder(BCryptPasswordEncoder())
    }
}