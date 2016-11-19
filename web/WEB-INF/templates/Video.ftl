<#ftl encoding="utf-8"/>
<#macro head>
<title>Videos</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>Видео:</h1>
</div>
<div class="search col-lg-8 col-md-offset-1">
    <h4 class="col-lg-2">Найти:</h4>
    <p><input id="vs" type="text" oninput="f()" class="form-control col-lg-8" style="width: 400px" placeholder="Search..."></p>
    <script type="application/javascript">
        var f = function() {
            $.ajax({
                'url': '/ajax-search',
                'data': {
                    'vs': $("#vs").val()
                },
                'method': 'get',
                'success': function(obj) {
                    $("#res").html(obj.result.join(", "));
                }
            });
        }
    </script>
</div>
<div id="res" class="col-lg-10 col-md-offset-1" style="margin-bottom: 15px">

</div>
<div class="table-responsive col-md-offset-1 col-lg-10">
    <table class="table table-striped">
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