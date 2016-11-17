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
        <p><input id="q" type="text" oninput="f()" class="form-control col-lg-8" style="width: 400px" placeholder="Search..."></p>
        <script type="application/javascript">
            var f = function() {
                $.ajax({
                    'url': '/ajax-search',
                    'data': {
                        'q': $("#q").val()
                    },
                    'method': 'get',
                    'success': function(obj) {
                        $("#res").html(obj.result.join(", "));
                    }
                });
            }
        </script>
    </div>
    <div id="res" class="col-lg-12 col-md-offset-1">

    </div>
    <#if all_games?has_content>
        <#list all_games as games>
        <li style="list-style-type: none"><div class="col-lg-12">
            <div class="game">
                <div class="col-lg-2">
                    <p class="img"><img class="img-responsive img-rounded"
                                        src='${games.getImageURL()}'
                                        alt="Game image"></p>
                </div>
                <div class="description col-lg-10">
                    <h3><a href="#">${games.getName()}</a></h3>
                    <div class="rating">
                        <p>Рейтинг: ${games.getRating()}</p>
                    </div>
                </div>
            </div>
        </div></li>
        </#list>
    </#if>
</#macro>
<#macro comments>
</#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>