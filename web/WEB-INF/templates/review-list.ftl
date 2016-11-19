<#ftl encoding="utf-8"/>
<#macro head>
<title>Reviews</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Рецензии:</h1>
</div>
<div class="answer col-lg-4">
    <p class="button">
        <button type="button" class="btn btn-primary" style="width: 200px" onclick="location.href='/add_review'">Добавить рецензию</button>
    </p>
</div>
<div class="table-responsive col-md-offset-1 col-lg-10">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№ п/п</th>
            <th>Название рецензии</th>
            <th>Название игры</th>
            <th>Оценка</th>
        </tr>
        </thead>
        <tbody style="text-align: left">
            <#if all_reviews?has_content>
                <#list all_reviews as r>
                <tr>
                    <td>${r?counter}</td>
                    <td><a href="/review?r=${r.getId()}">${r.getTitle()}</a></td>
                    <td><#if reviewService?has_content>
                            ${reviewService.getGameReviewIsAbout(r.getId()).getName()}
                        </#if>
                    </td>
                    <td>${r.getRate()}</td>
                </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">