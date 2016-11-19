<#ftl encoding="utf-8"/>
<#macro head>
<title>Theme</title>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2 col-lg-6">
        <h1>${topicService.findTopicById(theme_id?number).getTitle()}</h1>
    </div>
    <div class="answer col-lg-4">
        <p class="button">
            <a href="#myModal" class="btn btn-primary" data-toggle="modal">Ответить</a>
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
    <!-- HTML-код модального окна -->
    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Написать сообщение</h4>
                </div>
                <!-- Основное содержимое модального окна -->
                <form role="form" action="/theme?id=${theme_id}" method="POST">
                    <div class="modal-body">
                                <textarea class="form-control" name="text" id="InputText" type="text"
                                          placeholder="Напишите свое сообщение"></textarea>
                    </div>
                    <!-- Футер модального окна -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info">Отправить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">