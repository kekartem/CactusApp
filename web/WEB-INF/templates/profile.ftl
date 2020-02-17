<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#macro content>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="/public/css/registration.css" rel="stylesheet">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/public/js/registration.js"></script>
    <!------ Include the above in your HEAD tag ---------->


    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <title>Profile</title>
    </head>
    <body>
    <main class="my-form">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Profile</div>
                        <div class="card-body">

                            <form name="my-form" onsubmit="return validform()" action="logout" method="post">
                                <button class="btn btn-primary" type="submit">logout</button>
                            </form>
                            <p>&nbsp;</p>

                            <div class="card" style="width: 18rem;">
                                <img class="card-img-top" src="/img/03.jpg" alt="Profile picture">
                                <div class="card-body">
                                    <h5 class="card-title">${user.getLastName()} &nbsp; ${user.getName()}</h5>
                                    <p class="card-text">email: ${user.getEmail()}</p>
                                    <p class="card-text">address: ${user.getAddress()}</p>
                                    <a href="#" class="btn btn-primary">Go somewhere</a>
                                </div>
                            </div>
                            <#if orders?has_content>
                                <#list orders as order>
                                    <div class="card" style="width: 18rem;">
                                    <div class="card-header">
                                        Item: ${order.getItemId()}
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Cras justo odio</li>
                                        <li class="list-group-item">Order number: ${order.getId()}</li>
                                        <li class="list-group-item">Order time: ${order.getOrderTime()}</li>
                                    </ul>
                                </div>

                                    <p>&nbsp;</p>
                                </#list>
                                <#else><p>No orders yet((</p>
                                    <a href="/catalog" class="btn btn-primary">Go shopping!</a>
                                </#if>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>

    </main>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>
<@main/>