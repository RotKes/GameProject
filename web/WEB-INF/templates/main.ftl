<#ftl encoding="utf-8"/>
<#macro head>
<title>Main</title>
<script type="application/javascript" src="/js/jquery-1.5.min.js"></script>

<script type="application/javascript" src="/js/jquery.jcarousel.js"></script>

<script type="application/javascript">
    jQuery(document).ready(function () {
        // Initialise the first and second carousel by class selector.
        // Note that they use both the same configuration options (none in this case).
        jQuery('.d-carousel .carousel').jcarousel({
            scroll: 1
        });
    });
</script>
</#macro>
<#macro page>
    <div class="main">
        <div class="jumbotron" id="mainpage">
            <h1>Добро пожаловать!</h1>
            <p class="lead">Информация о портале</p>
        </div>

        <div class="news">
            <div class="heading col-md-offset-2">
                <h1>Новости</h1>
            </div>

            <div class="news">
                <div class="d-carousel col-lg-11 col-md-offset-1">
                    <ul class="carousel">
                        <#if latest_news?has_content>
                            <#list latest_news as news>
                                <div class="col-lg-4">
                                    <p class="img"><img class="img-responsive img-rounded"
                                                        src="http://mywishlist.ru/pic/i/wish/orig/006/163/366.png" alt="News image"></p>
                                    <h3 style="font-weight: bold">${news.getTitle()}</h3>
                                    <p>${news.getText()?substring(0,150)}...</p>
                                    <p><a class="btn btn-default" href="/topic?n=${news.getId()}" role="button">Подробнее &raquo;</a></p>
                                </div>
                            </#list>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro comments>
</#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>

