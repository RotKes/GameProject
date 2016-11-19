<#ftl encoding="utf-8"/>
<#macro head>
<title>News</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Новости:</h1>
</div>
<div class="search col-lg-8 col-md-offset-1">
    <h4 class="col-lg-2">Найти:</h4>
    <p><input id="ns" type="text" oninput="f()" class="form-control col-lg-8" style="width: 400px" placeholder="Search..."></p>
    <script type="application/javascript">
        var f = function() {
            $.ajax({
                'url': '/ajax-search',
                'data': {
                    'ns': $("#ns").val()
                },
                'method': 'get',
                'success': function(obj) {
                    $("#res").html(obj.result.join(", "));
                }
            });
        }
    </script>
</div>
<div id="res" class="col-lg-10 col-md-offset-1" style="margin-bottom: 15px">

</div>
<div class="table-responsive col-md-offset-1 col-lg-10">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№ п/п</th>
            <th>Название новости</th>
            <th>Название игры</th>
        </tr>
        </thead>
        <tbody>
            <#if all_news?has_content>
                <#list all_news as n>
                <tr>
                    <td>${n?counter}</td>
                    <td><a href="/topic?n=${n.getId()}">${n.getTitle()}</a></td>
                    <td><#if newsService?has_content>
                            ${newsService.getGameNewsIsAbout(n.getId()).getName()}
                        </#if>
                    </td>
                </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
</#macro>
<#macro comments>
</#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>