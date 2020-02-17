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

    <title>Registration</title>
    </head>
    <body>
    <main class="my-form">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Cart</div>
                        <div class="card-body">


                            <p>&nbsp;</p>
                            <#if items?has_content>
                                <#list items as item>
                                    <div class="card" style="width: 18rem;">
                                        <img class="card-img-top" src="/img/03.jpg"
                                             alt="Your picture might have been here.">
                                        <div class="card-body">
                                            <h5 class="card-title">${item.getLabel()}</h5>
                                            <p class="card-text">${item.getDescription()}</p>
                                            <p class="card-text">Seller: ${item.getCreator()}</p>
                                            <a href="#" class="btn btn-primary">Watch closer</a>
                                            <form name="remove" onsubmit="return validform()" action="remove" method="post">
                                                <input type="hidden" value="${item.getId()}" name="id"/>
                                                <button class="btn btn-primary" type="submit">Remove from
                                                    cart
                                                </button>
                                            </form>

                                        </div>
                                    </div>
                                    <p>&nbsp;</p>
                                </#list>


                            <#else> <p>No items in your cart((</p>
                                <a href="/catalog" class="btn btn-primary">Go shopping!</a>
                            </#if>

                            <form name="makeOrder" onsubmit="return validform()" action="makeOrder" method="post">
                            <#list numbers as number>

                                <input type="hidden" name="item${number}" value="${number}">

                            </#list>
                                <button class="btn btn-primary" type="submit">Make order</button>
                            </form>


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