# rest-login 
The example for grails with Spring Rest Security plugin.
## Tech
* Grails 2.5.0
* spring-security-core:2.0-RC3
* spring-security-rest:1.5.1

## How to run
```sh
$ grails run-app
```

## How to test
use curl for test

### login
<a>http://localhost:8080/rest-login/api/login</a>
```sh
$ curl -X POST -H 'Accept: application/json' -H 'Content-type:application/json' -d '{"username":"admin","password":"admin"}' http://localhost:8080/rest-login/api/login > output
```

### books
open output file copy 'access_token' and curl to <a>http://localhost:8080/rest-login/api/books</a>

```sh
$ curl -k -i -H "Authorization: Bearer {access-token}" http://localhost:8080/rest-login/api/books
```


