<#ftl encoding="utf-8"/>
<#macro head>
    <title>Sign Up</title>
</#macro>
<#macro page>
<#if user_registration??>

<#else>
    <div class="heading col-md-offset-2">
        <h1>Регистрация</h1>
    </div>
    <div class=" col-lg-6 col-md-offset-3">
        <form role="form" method="post" action="/registration">
            <div class="form-group col-sm-10 col-md-offset-1">
                <label for="InputLogin">Логин</label>
                <input type="text" class="form-control" name="username" id="InputLogin" placeholder="Введите логин">
            </div>
            <div class="form-group col-sm-10 col-md-offset-1">
                <label for="InputEmail">Email</label>
                <input type="email" class="form-control" name="e-mail" id="InputEmail" placeholder="Введите email">
            </div>
            <div class="form-group col-sm-10 col-md-offset-1">
                <label for="InputPassword">Пароль</label>
                <input type="password" class="form-control" name="password" id="InputPassword" placeholder="Введите пароль">
            </div>
            <div class="col-sm-offset-7 col-sm-5">
                <button type="submit" class="btn btn-default">Зарегистрироваться</button>
            </div>
            <div>
                <#if wrong_size??>
                    ${wrong_size}
                <#elseif user_not_available??>
                    ${user_not_available}
                <#elseif email_not_available??>
                    ${email_not_available}
                </#if>
            </div>
        </form>
    </div>
</#if>
</#macro>
<#macro comments>
</#macro>
<#include "base.ftl">