<#ftl encoding="utf-8"/>
<#macro head>
<title>Videos</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Видео:</h1>
</div>
<div class="table-responsive col-md-offset-1 col-lg-10">
    <table class="table table-striped sortable">
        <thead>
        <tr>
            <th>№ п/п</th>
            <th>Название видео</th>
            <th>Игра</th>
        </tr>
        </thead>
        <tbody style="text-align: left">
            <#if all_videos?has_content>
                <#list all_videos as vid>
                <tr>
                    <td>${vid?counter}</td>
                    <td><a href="/watch?v=${vid.getId()}">${vid.getTitle()}</a></td>
                    <td><#if videoService?has_content>
                            ${videoService.getGameVideoIsAbout(vid.getId()).getName()}
                        </#if>
                    </td>
                </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
</#macro>
<#macro comments></#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>