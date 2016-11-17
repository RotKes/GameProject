<#ftl encoding="utf-8"/>
<#macro head>
<title>Files</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Файлы:</h1>
</div>
<div class="search col-lg-8 col-md-offset-1">
    <h4 class="col-lg-2">Найти:</h4>
    <p><input type="text" class="form-control col-lg-8" style="width: 400px" placeholder="Search..."></p>
</div>
<div class="table-responsive col-md-offset-1 col-lg-10">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№ п/п</th>
            <th>Название файла</th>
            <th>Название игры</th>
            <th>Автор</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td><a href="#">Одно из названий</a></td>
            <td>Какое-то название игры</td>
            <td>Автор</td>
        </tr>
        <tr>
            <td>2</td>
            <td><a href="#">Другое название</a></td>
            <td>Название игры</td>
            <td>Автор</td>
        </tr>
        </tbody>
    </table>
</div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">