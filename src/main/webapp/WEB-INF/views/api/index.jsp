<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../includes/taglib.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>코난테크놀로</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/webjars/swagger-ui/3.24.0/swagger-ui.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/webjars/swagger-ui-themes/3.0.0/themes/3.x/theme-flattop.css"/>" />
    <style>
        html
        {
            box-sizing: border-box;
            overflow: -moz-scrollbars-vertical;
            overflow-y: scroll;
        }
        *,
        *:before,
        *:after
        {
            box-sizing: inherit;
        }

        body
        {
            margin:0;
            background: #fafafa;
        }
        .swagger-ui .topbar .topbar-wrapper { display: none; }

    </style>
</head>

<body>
<div id="swagger-ui"></div>

<script src='<c:url value="/webjars/swagger-ui/3.24.0/swagger-ui-bundle.js"/>' type='text/javascript'></script>
<script src='<c:url value="/webjars/swagger-ui/3.24.0/swagger-ui-standalone-preset.js"/>' type='text/javascript'></script>
<script>


    window.onload = function() {

        // Build a system
        const ui = SwaggerUIBundle({
            url: "<c:url value='/v2/api-docs'/>",
            dom_id: '#swagger-ui',
            deepLinking: true,
            presets: [
                SwaggerUIBundle.presets.apis,
                SwaggerUIStandalonePreset
            ],
            plugins: [
                SwaggerUIBundle.plugins.DownloadUrl
            ],
            layout: "StandaloneLayout"
        })

        window.ui = ui
    }
</script>
</body>
</html>
