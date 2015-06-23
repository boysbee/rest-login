package com.example
/**
 * Created by duangdat on 23/06/2558.
 */
import java.io.IOException
import java.io.PrintWriter

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


import org.springframework.http.HttpHeaders
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint


public class RestApiAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {


    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response,
                          AuthenticationException authException)
            throws IOException, ServletException {

        String contentType
        String content
        def type = request.getHeader(HttpHeaders.ACCEPT)

        switch(type) {
            case ~/.*xml.*/:
                contentType = 'application/xml'
                content = "<Errors><Error><Code>${HttpServletResponse.SC_UNAUTHORIZED}</Code></Error></Errors>"
                break
            case ~/.*json.*/:
                contentType = 'application/json'
                content = "{ \"errors\" : [ { \"code\":\"${HttpServletResponse.SC_UNAUTHORIZED}\" } ] }"
                break
            default:
                contentType = 'plain/text'
                content = HttpServletResponse.SC_UNAUTHORIZED + " - " + authException.getMessage()
                break
        }


        response.addHeader("Content-Type", contentType )
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"")
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
        PrintWriter writer = response.getWriter()
        writer.println(content)
    }
}