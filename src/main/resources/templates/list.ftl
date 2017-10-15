<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="referrer" content="no-referrer" />
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <meta itemprop="url" content="https://v.vxv.space:8080/qq/list?url=${url?html}" />
    <meta itemprop="name" name="title" content="${video['title']?html}">
    <meta name="description" itemprop="description" content="${video['description']?html}">
    <meta itemprop="image" content="${video['horizontal_pic_url']?html}">
    <meta itemprop="thumbnailUrl" content="${video['horizontal_pic_url']?html}">

    <title>${video['title']}</title>
    <style>
        sup{
            color: red;
            font-size: 10px;
        }
        .link{
            display: inline-block;
            width: 40px;
            height: 40px;
            margin: 5px;
            background: gray;
            border-radius: 5px;
            text-align: center;
            line-height: 40px;
            color: white;
        }
    </style>
</head>
<body>
<div style="margin: 0 auto">
    <img src="${video['horizontal_pic_url']?html}" width="100%" height="100%">
</div>
<#list videos as p>
        <a href="/vip/?url=${baseUrl}/${p['V']?html}.html&vip=${p['F']?html}" class="link">
        ${p['E']?html}<#if p['F']==0><sup>预</sup></#if><#if p['F']==7><sup>V</sup></#if>
        </a>
</#list>
    <div><a href="/cover/save?url=${url?html}">保存</a></div>
</body>
</html>

