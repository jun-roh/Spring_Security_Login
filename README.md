## Simple Sign Up & Simple Login

#### Purpose

* User Signup with Spring Security
* User Authority
* Move pages by User Authority when user login

#### Structure

- SimpleLogin
    - src
        - main
            - java
                - com.jun.login
                    - config
                    - controller
                    - domain
                    - repository
                    - service
                - LoginApplication.java
            - resource
                - static
                    - css
                    - font
                    - img
                    - js
                    - log
                - template
                    - layout
                    - index.html
                - application.properties
                - log4jdbc.log4j2.properties
                - logback-spring.xml
        - test
    - build.gradle

#### Authority
setting at com.jun.login.security.securityConfig
* Basic
* Client
* Admin

#### Go to Page After Login
setting at com.jun.login.security.securityHandler

#### ScreenShot
* Sign in
![Alt text](/screenshot/signin.png "Optional title")

* Sign up
![Alt text](/screenshot/signup.png "Optional title")


##### Reference
* Login Page Design Reference
    * https://bootsnipp.com/tags/login

