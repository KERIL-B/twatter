package k.bessonov.twatter.domain

interface Response {
    val status: ResponseStatus
}

data class Positive(override val status: ResponseStatus = ResponseStatus.OK) : Response
data class Negative(val message: String?, override val status: ResponseStatus = ResponseStatus.ERROR) : Response

enum class ResponseStatus {
    OK, ERROR
}