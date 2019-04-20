package k.bessonov.twatter.config

import k.bessonov.twatter.services.UsersService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailService(private val usersService: UsersService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = usersService.getDetailsWithPass(username)
        val cryptedPassword = BCryptPasswordEncoder().encode(user.password)
        val authorities = listOf(SimpleGrantedAuthority(authority))
        return User(username, cryptedPassword, authorities)
    }
}