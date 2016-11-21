<#ftl encoding="utf-8"/>
<#macro head>
<title>My Profile</title>
</#macro>
<#macro page>
    <#if userService.findUserId(user_id).getGroup_id() == 2>
    <div class="heading col-lg-12">
        <div class="col-md-offset-2 col-lg-6">
            <h1>Администратор</h1>
        </div>
    </div>
    <div class="col-lg-12">
        <div class="but col-md-offset-3">
            <button type="button" class="btn btn-primary" style="width: 200px" onclick="location.href = '/add_news'">Добавить новость</button>
            <button type="button" class="btn btn-primary col-md-offset-3" style="width: 200px" onclick="location.href ='/add_video'">Добавить видео</button>
            <button type="button" class="btn btn-primary" style="width: 200px" onclick="location.href = '/add_game'">Добавить игру</button>
        </div>
    </div>
    <#elseif userService.findUserId(user_id).getGroup_id() == 1>
    <div class="heading col-lg-12">
        <div class="col-md-offset-2 col-lg-6">
            <h1>Личная страница</h1>
        </div>
    </div>
    </#if>
    <div class="table-responsive col-lg-8 col-md-offset-2">
        <table class="table" style="border: 0px">
            <tbody>
            <tr>
                <td class="active"><h4>Имя пользователя:</h4></td>
                <td><h5>${userService.findUserId(user_id).getLogin()}</h5></td>
            </tr>
            <tr>
                <td class="active"><h4>E-mail:</h4></td>
                <td><h5>${userService.findUserId(user_id).getEmail()}</h5></td>
            </tr>
            </tbody>
        </table>
    </div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">