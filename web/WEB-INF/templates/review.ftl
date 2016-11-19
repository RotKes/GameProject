<#ftl encoding="utf-8"/>
<#macro head>
<title>${reviewService.findReviewById(review_id?number).getTitle()}</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>${reviewService.findReviewById(review_id?number).getTitle()} (${reviewService.getAuthorOfReview(review_id?number).getLogin()})</h1>
</div>
<div class="date col-lg-12">
    <p>${reviewService.findReviewById(review_id?number).getDate()}</p>
</div>
<div class="rating col-lg-12">
    <p>Рейтинг: ${reviewService.findReviewById(review_id?number).getRate()}</p>
</div>
<div class="video-game col-lg-4 ">
    <h3>${reviewService.getGameReviewIsAbout(review_id?number).getName()}</h3>
</div>
<div class="video-description col-lg-12 text-justify">
    <p>${reviewService.findReviewById(review_id?number).getText()}</p>
</div>
</#macro>
<#macro comments>
<div class="comments col-lg-12">
    <h3 class="title-comments">Комментарии (${reviewCommentService.getNumberOfCommentsInThisReview(review_id?number)})</h3>
    <ul class="media-list">
        <#if reviewCommentService.getAllReviewCommentsOfPostById(review_id?number)?has_content>
            <#list reviewCommentService.getAllReviewCommentsOfPostById(review_id?number) as comment>
                <li class="media">
                    <div class="media-heading col-lg-2">
                        <div class="author">${reviewCommentService.getCreatorOfMessage(comment.getId()).getLogin()}</div>
                        <div class="metadata">
                            <span class="date">${comment.getDate()}</span>
                        </div>
                    </div>
                    <div class="media-text text-justify col-lg-10">${comment.getText()}</div>
                </li>
            </#list>
        </#if>
    </ul>
    <form role="form" action="/review?r=${review_id}" method="POST">
        <textarea class="col-lg-10" name="comment_text"></textarea>
        <div class="add col-lg-2">
            <button type="submit" class="btn btn-default">Ответить</button>
        </div>
    </form>
</div>
</#macro>
<#include "base_log.ftl">