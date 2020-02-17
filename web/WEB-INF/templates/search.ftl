<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#macro content>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="/public/css/registration.css" rel="stylesheet">
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

    <p><input id="query" oninput="f()"/>

    </p>


    <div id="res"></div>

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
                                $("#res").append("<li>" + msg.objects[i].label + "</li>");
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
                                $("#res").append("<li>" + msg.objects[i].label + "</li>");
                            }
                        } else {
                            $("#res").html("No items in catalog..");
                        }
                    }
                })
            }
        }
    </script>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>
<@main/>