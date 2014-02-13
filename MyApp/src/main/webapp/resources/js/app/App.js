define([
    'backbone',
    'backbone.marionette',
    'app/routers/All'
], function(Backbone, Marionette, Routers) {
	'use strict';

    var app = new Marionette.Application();

    app.addRegions({
        container: 'body'
    });

    app.addInitializer(function(options) {
        new Routers(app);
        Backbone.history.start({ pushState: true, root: '/my-app' });
    });
    // Return the app instance.
    return app;
});
