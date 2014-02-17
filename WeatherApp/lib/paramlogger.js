'use strict';
var url = require('url');
module.exports = function () {
    return function (req, res, next) {
        var queryParams = url.parse(req.url).query;
        console.log("Request parameter: " + queryParams);
        next();
    };
};
