<#ftl encoding="utf-8"/>
<!DOCTYPE html>
<html lang="en">
<link href="/css/style.css"
      rel="stylesheet"
      type="text/css"/>
<link href = "/css/bootstrap.css"
      rel = "stylesheet"
      type="text/css"/>
<link href="/css/jquery.rating.css" rel="stylesheet" type="text/css"/>
<link href="/css/styles_rating.css" rel="stylesheet" type="text/css"/>
<head>
    <meta charset="UTF-8">
    <@head/>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="row">
            <div class="col-lg-7 col-md-offset-1 name_of_site">
                <a href="/">Epic Frag</a>
            </div>
            <div class="col-lg-4">
                <p class="button">
                    <button type="button" class="btn btn-primary " style="width: 200px" onclick="location.href = '/login'">Вход</button>
                </p>
                <p>
                    <button type="button" class="btn btn-primary" style="width: 200px" onclick="location.href = '/registration'">Регистрация</button>
                </p>
            </div>
        </div>
        <nav role="navigation" class="navbar navbar-default ">
            <div class="masthead">
                <ul class="nav nav-justified">
                    <li class="active"><a href="/news">Новости</a></li>
                    <li><a href="/games">Игры</a></li>
                    <li><a href="/videos">Видео</a></li>
                </ul>
            </div>
        </nav>
    </div>
    <@page/>
    <footer>
        <@comments/>
    </footer>
</div>
</body>
</html>