<#include "base.ftl"/>
<#macro content>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.." rel="stylesheet" id="bootstrap-css">
    <link href="/public/css/registration.css" rel="stylesheet">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap..."></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.mi.."></script>
    <script src="/public/js/registration.js"></script>
    <!----— Include the above in your HEAD tag —-------->

    <!— Required meta tags —>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!— Fonts —>
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

    <!— Bootstrap CSS —>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <title>Registration</title>
    </head>
    <body>
    <main class="my-form">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header"><input id="query" oninput="f()"/></div>
                        <div class="card-body">

                            <a href="/addItem" class="btn btn-primary">Add new item</a>

                            <div id="res"><#list items as item>
                                    <div class="card" style="width: 18rem;">
                                        <img class="card-img-top" src="/img/03.jpg" alt="Your picture might have been here.">
                                        <div class="card-body">
                                            <h5 class="card-title">${item.getLabel()}</h5>
                                            <p class="card-text">${item.getDescription()}</p>
                                            <p class="card-text">Ordered ${item.getOrders()} times</p>

                                            <a href="#" class="btn btn-primary">Watch closer</a>
                                            <form name="my-form" onsubmit="return validform()" action="addToCart" method="post">
                                                <input type="hidden" value="${item.getId()}" name="id"/>
                                                <button class="btn btn-primary" type="submit">Add to shopping cart</button>
                                            </form>
                                        </div>
                                    </div>
                                    <p>&nbsp;</p>
                                </#list>
                            </div>

                            <script type="application/javascript">
                                function f() {
                                    if ($("#query").val().length >= 1) {
                                        $.ajax({
                                            url: "/dosearch",
                                            data: {"query": $("#query").val()},
                                            dataType: "json",
                                            success: function (msg) {
                                                if (msg.objects.length > 0) {
                                                    $("#res").html("");
                                                    for (var i = 0; i < msg.objects.length; i++) {
                                                        $("#res").append("<div class=\"card\" style=\"width:18rem;\"> <img class=\"card-img-top\" src=\"/img/03.jpg\" " +
                                                            "alt=\"Your picture might have been here.\"> <div class=\"card-body\"><h5 class=\"card-title\">" + msg.objects[i].label +
                                                            "</h5> <p class=\"card-text\">" + msg.objects[i].description + "</p> <p class=\"card-text\">Ordered " +
                                                            msg.objects[i].orders + " times. </p> <a href=\"#\" class=\"btn btn-primary\">Watch closer</a> " +
                                                            "<form name=\"my-form\" action=\"addToCart\" method=\"post\"> " +
                                                            "<input type=\"hidden\" value=\"" + msg.objects[i].id + "\" name =\"id\"> <button class=\"btn btn-primary\" type=\"submit\">Add to shopping cart</button></form> </div> </div>")

                                                    }
                                                } else {
                                                    $("#res").html("No results..");
                                                }
                                            }
                                        })
                                    }
                                    else {
                                        $.ajax({
                                            url: "/dosearch",
                                            data: {"query": $("#query").val()},
                                            dataType: "json",
                                            success: function (msg) {
                                                if (msg.objects.length > 0) {
                                                    $("#res").html("");
                                                    for (var i = 0; i < msg.objects.length; i++) {
                                                        $("#res").append("<div class=\"card\" style=\"width:18rem;\"> <img class=\"card-img-top\" src=\"/img/03.jpg\" " +
                                                            "alt=\"Your picture might have been here.\"> <div class=\"card-body\"><h5 class=\"card-title\">" + msg.objects[i].label +
                                                            "</h5> <p class=\"card-text\">" + msg.objects[i].description + "</p> <p class=\"card-text\">Ordered " +
                                                            msg.objects[i].orders + " times. </p> <a href=\"#\" class=\"btn btn-primary\">Watch closer</a> " +
                                                            "<form name=\"my-form\" action=\"addToCart\" method=\"post\"> " +
                                                            "<input type=\"hidden\" value=\"" + msg.objects[i].id + "\" name =\"id\"> <button class=\"btn btn-primary\" type=\"submit\">Add to shopping cart</button></form> </div> </div>")

                                                    }
                                                } else {
                                                    $("#res").html("No items in catalog..");
                                                }
                                            }
                                        })
                                    }
                                }
                            </script>

                            <p>&nbsp;</p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>

    </main>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>
<@main/>