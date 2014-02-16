<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>My Web App</title>

    <link href="<c:url value="/resources/css/lib/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/app/app.css" />" rel="stylesheet">

    <script data-main="resources/js/app/Main"
            src="<c:url value="/resources/js/lib/require.js" />"></script>
    <script>
        define('config', {
            message: '${message}'
        });
    </script>
</head>
<body>
</body>
</html>
