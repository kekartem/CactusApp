<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#macro content>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="/public/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/public/css/auth.css">
    </head>
    <body>

    <div class="container">
        <div class="row">
            <div class="col-sm">
            </div>
            <div class="col-sm second">
                <div class="d-flex p-2 bd-highlight">
                    <!-- Default form login -->
                    <form class="text-center border border-light p-5" action="login" method = "post">

                        <p class="h4 mb-4">Join us!</p>

                        <!-- Login -->
                        <input type="text" id="defaultLoginFormLogin" class="form-control mb-4" placeholder="Login"
                               name="login">

                        <!-- Password -->
                        <input type="password" id="defaultLoginFormPassword" class="form-control mb-4"
                               placeholder="Password"
                               name="password">

                        <div class="d-flex justify-content-around">
                            <div>
                                <!-- Remember me -->
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember"
                                           value="Yes"
                                           name="checkbox">
                                    <label class="custom-control-label" for="defaultLoginFormRemember">Remember
                                        me</label>
                                </div>
                            </div>
                        </div>

                        <!-- Sign in button -->
                        <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

                        <!-- Register -->
                        <p>Have not you join us yet?
                            <a href="/registration">Let's do it</a>
                        </p>
                    </form>
                </div>
            </div>
            <div class="col-sm">
            </div>
        </div>
    </div>
</#macro>
<@main/>