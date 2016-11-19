<#ftl encoding="utf-8"/>
<#macro head>
<title>Add Review</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Добавить рецензию</h1>
</div>

<div class=" col-lg-6 col-md-offset-3">
    <form role="form" action="/add_review" method="POST">
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputHeader">Заголовок</label>
            <input type="text" class="form-control" id="InputHeader" name="title" placeholder="Введите название">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputText">Текст</label>
            <textarea class="form-control" id="InputText" name="text" placeholder="Введите текст"></textarea>
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputGame">Название игры</label>
            <select class="form-control input-lg" id="InputGame" name="game">
                <#list all_games as game>
                    <option value="${game.getName()}">${game.getName()}</option>
                </#list>
            </select>
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <p> <label>Оценка</label>
            <div id="reviewStars-input">
                <input id="star-4" type="radio" name="reviewStars" value="5"/>
                <label title="gorgeous" for="star-4"></label>

                <input id="star-3" type="radio" name="reviewStars" value="4"/>
                <label title="good" for="star-3"></label>

                <input id="star-2" type="radio" name="reviewStars" value="3"/>
                <label title="regular" for="star-2"></label>

                <input id="star-1" type="radio" name="reviewStars" value="2"/>
                <label title="poor" for="star-1"></label>

                <input id="star-0" type="radio" name="reviewStars" value="1"/>
                <label title="bad" for="star-0"></label>
            </div></p>
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