package es.gresybano.gresybano.data.utils

import es.gresybano.gresybano.domain.response.CodeExceptions

class CustomException(val codeExceptions: CodeExceptions, message: String) : Exception(message)