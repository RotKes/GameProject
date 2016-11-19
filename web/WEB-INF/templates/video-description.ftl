<#ftl encoding="utf-8"/>
<#macro head>
    <title>Video</title>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2 col-lg-6">
        <h1>${videoService.findVideoById(video_id?number).getTitle()}</h1>
    </div>
    <div class="video">
        <div class="col-lg-7">
            <iframe width="600" height="370" src='${videoService.findVideoById(video_id?number).getVideoLink()}' frameborder="0"
                    allowfullscreen></iframe>
        </div>
        <div class="video-game col-lg-4 ">
            <h3>${videoService.getGameVideoIsAbout(video_id?number).getName()}</h3>
            <p><small>${videoService.getGameVideoIsAbout(video_id?number).getDescription()}</small></p>
        </div>
    </div>
    <div class="date col-lg-12">
        <p>${videoService.findVideoById(video_id?number).getDate()}</p>
    </div>
</#macro>
<#macro comments>
<div class="comments col-lg-12">
    <h3 class="title-comments">Комментарии (${videoCommentService.getNumberOfCommentsInThisVideo(video_id?number)})</h3>
    <ul class="media-list">
        <#if videoCommentService.getAllVideoCommentsOfPostById(video_id?number)?has_content>
            <#list videoCommentService.getAllVideoCommentsOfPostById(video_id?number) as comment>
                <li class="media">
                    <div class="media-heading col-lg-2">
                        <div class="author">${videoCommentService.getCreatorOfMessage(comment.getId()).getLogin()}</div>
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
        <form role="form" action="/watch?v=${video_id}" method="POST">
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
