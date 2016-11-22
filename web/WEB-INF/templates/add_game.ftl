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
    <form role="form" action="/add_game" enctype="multipart/form-data" method="POST">
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="name">Название игры</label>
            <input required type="text" class="form-control" id="name" name="name" placeholder="Введите название">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="pickyDate">Дата выхода</label>
            <input type="text" class="form-control" id="pickyDate" name="pickyDate">
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="text">Описание</label>
            <textarea required class="form-control" id="text" name="text" placeholder="Введите текст"></textarea>
        </div>
        <div class="form-group col-sm-10 col-md-offset-1">
            <label for="image">Картинка</label>
            <input type="file" id="image" name="image" accept="image/*,image/jpeg">
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