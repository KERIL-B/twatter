package k.bessonov.twatter.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class EntryPoint : BasicAuthenticationEntryPoint() {

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        response.addHeader("WWW-Authenticate", """Basic realm="$realmName"""")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
    }

    override fun afterPropertiesSet() {
        realmName = twatterRealmName
    }
}