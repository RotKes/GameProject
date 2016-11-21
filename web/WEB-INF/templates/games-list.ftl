<#ftl encoding="utf-8"/>
<#macro head>
    <title>Games</title>
    <script type="application/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</#macro>
<#macro page>
    <div class="heading col-md-offset-2 col-lg-6">
        <h1>Игры:</h1>
    </div>
    <div class="search col-lg-12 col-md-offset-1">
        <h4 class="col-lg-2">Найти:</h4>
        <p><input id="gs" type="text" oninput="f()" class="form-control col-lg-8" style="width: 400px" placeholder="Search..."></p>
        <script type="application/javascript">
            var f = function() {
                $.ajax({
                    'contentType': 'application/javascript; charset=utf-8',
                    'url': '/ajax-search',
                    'data': {
                        'gs': $("#gs").val()
                    },
                    'method': 'get',
                    'success': function(obj) {
                        var data = obj.result;
                        var htmlText = "";
                        for (var key in data) {
                            htmlText += "<div class=\"col-lg-12\">";
                            htmlText += "<div class=\"game\">";
                            htmlText += "<div class=\"col-lg-2\">";
                            htmlText += "<p class=\"img\"><img class=\"img-responsive img-rounded\" src=\"" + data[key].imageURL + "\" alt=\"Game image\"></p>";
                            htmlText += "</div>";
                            htmlText += "<div class=\"description col-lg-10\">";
                            htmlText += "<h3><a href=\"/game?g=" + data[key].id + "\">" + data[key].name + "</a></h3>";
                            htmlText += "<div class=\"rating\">";
                            htmlText += "<p>Рейтинг: "+ data[key].rating + "</p>";
                            htmlText += "</div>";
                            htmlText += "</div>";
                            htmlText += "</div>";
                            htmlText += "</div>";
                        }
                        $("#res").html(htmlText);
                    }
                });
            }

            $(document).ready()
            {
                f();
            }
        </script>
    </div>
    <div id="res" class="col-lg-10 col-md-offset-1" style="margin-bottom: 15px">

    </div>
</#macro>
<#macro comments>
</#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>