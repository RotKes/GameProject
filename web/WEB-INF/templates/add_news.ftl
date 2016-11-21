<#ftl encoding="utf-8"/>
<#macro head>
<title>Reviews</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Добавить новость</h1>
</div>

<div class=" col-lg-6 col-md-offset-3">
    <form role="form" action="/add_news" method="POST">
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputHeader">Заголовок</label>
            <input required type="text" class="form-control" name="header" id="header" placeholder="Введите название">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputGame">Название игры</label>
            <select required class="form-control input-lg" id="InputType" name="game">
                <#list all_games as game>
                    <option value="${game.getName()}">${game.getName()}</option>
                </#list>
            </select>
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputText">Текст</label>
            <textarea required class="form-control" id="text" name="text" placeholder="Введите текст"></textarea>
        </div>
        <div class="col-sm-offset-7 col-sm-5">
            <button type="submit" class="btn btn-default">Отправить</button>
        </div>
    </form>
</div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">