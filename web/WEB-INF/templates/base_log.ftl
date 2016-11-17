<#ftl encoding="utf-8"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <@head/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="header">
        <div class="row">
            <div class="col-lg-7 col-md-offset-1 name_of_site">
                <a href="/">Games</a>
            </div>
            <div class="col-lg-4">
                <p>
                    <button type="button" class="btn btn-default " onclick="location.href = '/user?u=${userService.getCurrentUserId(current_user)}'">Личный кабинет</button>
                </p>
                <p>
                    <button type="button" class="btn btn-default" onclick="location.href = '/logout'">Выйти</button>
                </p>
            </div>
        </div>
        <nav role="navigation" class="navbar navbar-default ">
            <div class="masthead">
                <ul class="nav nav-justified">
                    <li class="active"><a href="/news">Новости</a></li>
                    <li><a href="/forum">Форум</a></li>
                    <li><a href="/games">Игры</a></li>
                    <li><a href="#">Рецензии</a></li>
                    <li><a href="/videos">Видео</a></li>
                    <li><a href="/files">Файлы</a></li>
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