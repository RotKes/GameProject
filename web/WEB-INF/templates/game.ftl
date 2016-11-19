<#ftl encoding="utf-8"/>
<#macro head>
<title>${gameService.findGameById(game_id?number).getName()}</title>
</#macro>
<#macro page>
<div class="heading col-md-offset-2 col-lg-6">
    <h1>${gameService.findGameById(game_id?number).getName()}</h1>
</div>
<div class="video">
    <div class="col-lg-7">
        <p class="img"><img class="img-responsive img-rounded"
                            src='${gameService.findGameById(game_id?number).getImageURL()}'
                            alt="Game image"></p>
    </div>
    <div class="video-game col-lg-4 ">
        <h3 class="date">Дата выхода: ${gameService.findGameById(game_id?number).getDate()}</h3>
        <h3 class="rating">Рейтинг: ${gameService.findGameById(game_id?number).getRating()}</h3>
    </div>
</div>
<div class="video-description col-lg-12 text-justify">
    <p>${gameService.findGameById(game_id?number).getDescription()}</p>
</div>
</#macro>
<#macro comments>
</#macro>
<#if current_user??>
    <#include "base_log.ftl">
<#else>
    <#include "base.ftl">
</#if>