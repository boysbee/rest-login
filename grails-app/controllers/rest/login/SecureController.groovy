package rest.login

import grails.plugin.springsecurity.annotation.Secured

class SecureController {
    def springSecurityService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            render("yes logged in....")
        }
    }
}
