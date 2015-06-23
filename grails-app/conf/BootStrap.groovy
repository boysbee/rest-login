import com.example.Book
import com.example.Role
import com.example.User
import com.example.UserRole

class BootStrap {

    def init = { servletContext ->
        new Book(title:"Java Persistence with Hibernate", author:"Gavin King", price:99.00).save()
        new Book(title:"Spring Live", author:"Matt Raible", price:29.00).save()

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(flush: true)
        def guestRole = Role.findByAuthority('ROLE_GUEST') ?: new Role(authority: 'ROLE_GUEST').save(flush: true)
        def authRole = Role.findByAuthority('ROLE_AUTOAUTH') ?: new Role(authority: 'ROLE_AUTOAUTH').save(flush: true)

        def admin = User.findByUsername('admin') ?: new User(username: 'admin', enabled: true, password: "admin")
        admin.save(flush: true)

        if(!UserRole.findByUserAndRole(admin, adminRole)) {
            UserRole.create admin, adminRole, true
        }

//        // NOTE!!! Need to update password of admin in production!!!
//        if(Environment.PRODUCTION == Environment.current) {
//            admin.password = "lb'F9oeF=8" // 'สิงโตนำโชค' in English keyboard, !!! NOTE NO space !!!.
//            admin.save(flush: true, failOnError: true)
//        }
        // NOTE!!! Need to update password of admin in production!!!

        def user = User.findByUsername('user') ?: new User(username: 'user', enabled: true, password: "user")
        user.save(flush: true)

        if(!UserRole.findByUserAndRole(user, userRole)) {
            UserRole.create user, userRole, true
        }

        assert User.count() >= 1
        assert Role.count() >= 4
        assert UserRole.count() >= 1
    }
    def destroy = {
    }
}
