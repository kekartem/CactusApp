<#ftl encoding="UTF-8"/>
<#macro content></#macro>
<#macro main>
    <!doctype html>
    <html lang="en">

    <body>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="#">
            <img src="/public/img/logo.png" width="150" height="75" class="d-inline-block align-top" alt="">
        </a>
    </nav>
    <@content/>
    </body>
    </html>
</#macro>