<#ftl encoding="utf-8"/>
<#macro head>
<title>LogIn</title>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2">
        <h1>Вход</h1>
    </div>
    <div class=" col-lg-6 col-md-offset-3">
        <form role="form" action="/login" method="POST">
            <div>
                <#if if_just_registered??>
                    ${if_just_registered}
                </#if>
            </div>
            <div class="form-group col-sm-10 col-md-offset-1">
                <label for="InputLogin">Логин</label>
                <input type="text" class="form-control" name="login" id="InputLogin" placeholder="Введите логин"
                    <#if login??>
                       value = '${login}'
                    </#if>>
            </div>
            <div class="form-group col-sm-10 col-md-offset-1">
                <label for="InputPassword">Пароль</label>
                <input type="password" class="form-control" name="password" id="InputPassword" placeholder="Введите пароль">
            </div>
            <div class="form-group col-sm-10 col-md-offset-3">
                <label class="checkbox">
                    <input type="checkbox" name="cookie" value="remember-me"> Запомнить меня
                </label>
            </div>
            <div class="col-sm-offset-7 col-sm-5">
                <button type="submit" class="btn btn-default">Войти</button>
            </div>
            <div>
                <#if incorrect_password??>
                    ${incorrect_password}
                <#elseif user_not_find??>
                    ${user_not_find}
                </#if>
            </div>
        </form>
    </div>
</#macro>
<#macro comments>
</#macro>
<#include "base.ftl">