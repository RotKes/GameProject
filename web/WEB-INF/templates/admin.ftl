<#ftl encoding="utf-8"/>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<link href="/css/style.css"
      rel="stylesheet"
      type="text/css"/>
<link href = "/css/bootstrap.css"
      rel = "stylesheet"
      type="text/css"/>
<script src="/js/sorttable.js"></script>
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>
    <nav role="navigation" class="navbar navbar-default ">
        <div class="masthead">
            <ul class="nav nav-justified">
                <li><a href="/admin?t=users">Пользователи</a></li>
                <li><a href="/admin?t=news">Новости</a></li>
                <li><a href="/admin?t=games">Игры</a></li>
                <li><a href="/admin?t=videos">Видео</a></li>
                <li><a href="/admin?t=topics">Форум</a></li>
                <li><a href="/admin?t=cookies">Куки</a></li>
                <li><a href="/admin?t=reviews">Обзоры</a></li>
                <li><a href="/admin?t=groups">Роли пользователей</a></li>
                <li><a href="/admin?t=messages">Сообщения на форуме</a></li>
                <li><a href="/admin?t=comment_news">Комменты к новостям</a></li>
                <li><a href="/admin?t=comment_videos">Комменты к видео</a></li>
                <li><a href="/admin?t=comment_reviews">Комменты к обзорам</a></li>
                <li><a href="/">Выход</a></li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="table-responsive col-md-offset-1 col-lg-10">
            <table class="table table-bordered sortable">
                <thead>
                <tr>
                    <#if all_columns?has_content>
                        <#list all_columns as column>
                                <td>${column}</td>
                        </#list>
                        <td>Удалить</td>
                    </#if>
                </tr>
                </thead>
                <tbody style="text-align: left">
                    <#if all_users?has_content>
                        <#list all_users as user>
                        <tr>
                            <td>${user.getId()?c}</td>
                            <td>${user.getGroup_id()}</td>
                            <td>${user.getLogin()}</td>
                            <td>${user.getEmail()}</td>
                            <td>${user.getPassword()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="user_id" value="${user.getId()?c}">Удалить</button>
                                </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_news_comments?has_content>
                        <#list all_news_comments as cn>
                        <tr>
                            <td>${cn.getId()}</td>
                            <td>${cn.getPost_id()}</td>
                            <td>${cn.getCreator_id()}</td>
                            <td>${cn.getText()}</td>
                            <td>${cn.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="comment_news" value="${cn.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_video_comments?has_content>
                        <#list all_video_comments as cn>
                        <tr>
                            <td>${cn.getId()}</td>
                            <td>${cn.getPost_id()}</td>
                            <td>${cn.getCreator_id()}</td>
                            <td>${cn.getText()}</td>
                            <td>${cn.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="comment_videos" value="${cn.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_review_comments?has_content>
                        <#list all_review_comments as cn>
                        <tr>
                            <td>${cn.getId()}</td>
                            <td>${cn.getPost_id()}</td>
                            <td>${cn.getCreator_id()}</td>
                            <td>${cn.getText()}</td>
                            <td>${cn.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="comment_reviews" value="${cn.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_videos?has_content>
                        <#list all_videos as video>
                        <tr>
                            <td>${video.getId()}</td>
                            <td>${video.getGame_id()}</td>
                            <td>${video.getVideoLink()}</td>
                            <td>${video.getTitle()}</td>
                            <td>${video.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="video_id" value="${video.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_topics?has_content>
                        <#list all_topics as topic>
                        <tr>
                            <td>${topic.getId()}</td>
                            <td>${topic.getCreator_id()}</td>
                            <td>${topic.getTitle()}</td>
                            <td>${topic.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="topic_id" value="${topic.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_messages?has_content>
                        <#list all_messages as message>
                        <tr>
                            <td>${message.getId()}</td>
                            <td>${message.getTopic_id()}</td>
                            <td>${message.getCreator_id()}</td>
                            <td>${message.getText()}</td>
                            <td>${message.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="message_id" value="${message.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_cookies?has_content>
                        <#list all_cookies as cookie>
                        <tr>
                            <td>${cookie.getUser_id()}</td>
                            <td>${cookie.getToken()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="token" value="${cookie.getToken()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_groups?has_content>
                        <#list all_groups as group>
                        <tr>
                            <td>${group.getId()}</td>
                            <td>${group.getName()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="group_id" value="${group.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_news?has_content>
                        <#list all_news as news>
                        <tr>
                            <td>${news.getId()}</td>
                            <td>${news.getGame_id()}</td>
                            <td>${news.getTitle()}</td>
                            <td>${news.getText()}</td>
                            <td>${news.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="news_id" value="${news.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_games?has_content>
                        <#list all_games as game>
                        <tr>
                            <td>${game.getId()}</td>
                            <td>${game.getName()}</td>
                            <td>${game.getDate()}</td>
                            <td>${game.getDescription()}</td>
                            <td>${game.getImageURL()}</td>
                            <td>${game.getRating()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="game_id" value="${game.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    <#elseif all_reviews?has_content>
                        <#list all_reviews as review>
                        <tr>
                            <td>${review.getId()}</td>
                            <td>${review.getCreator_id()}</td>
                            <td>${review.getGame_id()}</td>
                            <td>${review.getTitle()}</td>
                            <td>${review.getText()}</td>
                            <td>${review.getRate()}</td>
                            <td>${review.getDate()}</td>
                            <td><form action="/admin" method="post">
                                <button class="btn btn-primary" type="submit" name="review_id" value="${review.getId()}">Удалить</button>
                            </form>
                            </td>
                        </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>