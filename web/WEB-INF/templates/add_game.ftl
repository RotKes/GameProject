<#ftl encoding="utf-8"/>
<#macro head>
<title>Add Game</title>
</#macro>
<#macro page>
<link href="/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css"/>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Добавить Игру</h1>
</div>

<div class=" col-lg-6 col-md-offset-3">
    <form role="form" action="/add_game" method="POST">
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputName">Название игры</label>
            <input required type="text" class="form-control" id="InputName" name="name" placeholder="Введите название">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputDate">Дата выхода</label>
            <input type="text" class="form-control" id="pickyDate" name="date">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputText">Описание</label>
            <textarea required class="form-control" id="InputText" name="text" placeholder="Введите текст"></textarea>
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="InputLink">Ссылка на картинку</label>
            <input required type="text" class="form-control" id="InputLink" name="link" placeholder="Введите путь">
        </div>
        <div class="col-sm-offset-7 col-sm-5">
            <button type="submit" class="btn btn-default">Отправить</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#pickyDate').datepicker({
            format: "dd.mm.yyyy"
        });
    });
</script>
<script src="/js/bootstrap-datepicker.js"></script>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">