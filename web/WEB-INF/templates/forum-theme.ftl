<#ftl encoding="utf-8"/>
<#macro head>
<title>Theme</title>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2 col-lg-6">
        <h1>${topicService.findTopicById(theme?number).getTitle()}</h1>
    </div>
    <div class="answer col-lg-4">
        <p class="button">
            <button type="button" class="btn btn-primary" style="width: 200px" href="#">Ответить</button>
        </p>
    </div>
    <div class="messages">
        <#if all_messages?has_content>
            <#list all_messages as mes>
                <#if mes??>
                    <div class="message-part col-md-offset-1 col-lg-10">
                        <div class="date">
                            <p>${mes.getDate()}</p>
                        </div>
                        <div class="message">
                            <div class="media-heading col-lg-2">
                                <div class="author">${topicService.getCreatorOfMessage(mes.getId())}</div>
                            </div>
                            <div class="media-text text-justify col-lg-10">${mes.getText()}</div>
                        </div>
                    </div>
                </#if>
            </#list>
        </#if>
    </div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">