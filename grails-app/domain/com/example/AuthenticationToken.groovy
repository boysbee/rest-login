package com.example

class AuthenticationToken {
    String tokenValue
	String username
    static constraints = {
        username blank: false, unique: true
        tokenValue blank: false
    }

    static mapping = {
    }
}
