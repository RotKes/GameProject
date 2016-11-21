<#ftl encoding="utf-8"/>
<#macro head>
<title>Forum</title>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2 col-lg-6">
        <h1>Темы:</h1>
    </div>
    <div class="button col-md-offset-8">
        <a href="#myModal" class="btn btn-primary" data-toggle="modal">Добавить новую тему форума</a>
    </div>

    <div class="table-responsive col-md-offset-1 col-lg-10">
        <table class="table table-striped sortable">
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
    <!-- HTML-код модального окна -->
    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Добавить новую тему форума</h4>
                </div>
                <!-- Основное содержимое модального окна -->
                <form role="form" action="/forum" method="POST">
                    <div class="modal-body">
                        <textarea type="text" name="title" class="form-control" id="InputText" placeholder="Напишите название темы"></textarea>
                    </div>
                    <!-- Футер модального окна -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</#macro>
<#macro comments>
</#macro>
<#include "base_log.ftl">