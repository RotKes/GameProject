<#ftl encoding="utf-8"/>
<#macro head>
<title>News</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Новости:</h1>
</div>
<div class="table-responsive col-md-offset-1 col-lg-10">
    <table class="table table-striped sortable">
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