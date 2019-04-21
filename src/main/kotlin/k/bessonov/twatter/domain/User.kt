package k.bessonov.twatter.domain

data class User(
        val id: String? = null,
        val firstName: String,
        val lastName: String,
        val login: String,
        val avatar: String?
) {
    var password: String? = null
}

data class LoginBody(val login: String, val password: String)