<#ftl encoding="utf-8"/>
<#macro head>
<title>Forum</title>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2 col-lg-6">
        <h1>Темы:</h1>
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
                <th>Тема</th>
                <th>Количество сообщений</th>
                <th>Последнее сообщение от :</th>
            </tr>
            </thead>
            <tbody style="text-align: left">
                <#if all_topics?has_content>
                    <#list all_topics as tp>
                    <tr>
                        <td>${tp?counter}</td>
                        <td><a href="/theme?id=${tp.getId()}">${tp.getTitle()}</a></td>
                        <td>${topicService.getNumberOfMessagesInTopic(tp.getId())}</td>
                        <td><#if topicService.getLastMessageOfTopic(tp.getId())??>
                            ${topicService.getCreatorOfMessage(topicService.getLastMessageOfTopic(tp.getId()).getId()).getLogin()}
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
<#include "base_log.ftl">