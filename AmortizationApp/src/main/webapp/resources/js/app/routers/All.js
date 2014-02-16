define([
    'app/routers/Main',
    'app/controllers/Main'
], function(MainRouter, MainController) {
    'use strict';

    return function(app) {
        this.prototype = {
            MainRouter: new MainRouter({ controller: new MainController(app) })
        };
    };
});
