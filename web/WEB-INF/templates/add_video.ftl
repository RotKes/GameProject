<#ftl encoding="utf-8"/>
<#macro head>
<title>Add Video</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Добавить Видео</h1>
</div>

<div class=" col-lg-6 col-md-offset-3">
    <form role="form">
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputHeader">Заголовок видео</label>
            <input type="text" class="form-control" id="InputHeader" placeholder="Введите название">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputGame">Название игры</label>
            <input type="text" class="form-control" id="InputGame" placeholder="Введите название">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputText">Ссылка на видео</label>
            <textarea class="form-control" id="InputText" placeholder="Введите текст"></textarea>
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