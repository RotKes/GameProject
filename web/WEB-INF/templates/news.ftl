<#ftl encoding="utf-8"/>
<#macro head>
<title>${newsService.findNewsById(news_id?number).getTitle()}</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>${newsService.findNewsById(news_id?number).getTitle()}</h1>
    <h4>${newsService.getGameNewsIsAbout(news_id?number).getName()}</h4>
</div>
<div class="video-description col-lg-12 text-justify">
    <p>${newsService.findNewsById(news_id?number).getText()}</p>
</div>
</#macro>
<#macro comments>
<div class="comments col-lg-12">
    <h3 class="title-comments">Комментарии (${newsCommentService.getNumberOfCommentsInThisNews(news_id?number)})</h3>
    <ul class="media-list">
        <#if newsCommentService.getAllNewsCommentsOfPostById(news_id?number)?has_content>
            <#list newsCommentService.getAllNewsCommentsOfPostById(news_id?number) as comment>
                <li class="media">
                    <div class="media-heading col-lg-2">
                        <div class="author">${newsCommentService.getCreatorOfMessage(comment.getId()).getLogin()}</div>
                        <div class="metadata">
                            <span class="date">${comment.getDate()}</span>
                        </div>
                    </div>
                    <div class="media-text text-justify col-lg-10">${comment.getText()}</div>
                </li>
            </#list>
        </#if>
    </ul>
    <#if current_user??>
        <form role="form" action="/topic?n=${news_id}" method="POST">
            <textarea class="col-lg-10" name="comment_text"></textarea>
            <div class="add col-lg-2">
                <button type="submit" class="btn btn-default">Ответить</button>
            </div>
        </form>
    </#if>
</div>
</#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>