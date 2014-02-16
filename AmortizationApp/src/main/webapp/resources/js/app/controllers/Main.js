define([
    'backbone.marionette',
    'app/views/layouts/Main',
    'app/views/Input',
    'app/views/Content'
], function(Marionette, MainLayout, InputView, ContentView) {
	'use strict';

    var layout = new MainLayout();
	
    return Marionette.Controller.extend({
        initialize: function(app) {
            this.app = app;
        },

        index: function() {
            this.app.container.show(layout);
            layout.input.show(new InputView());
            layout.content.show(new ContentView());
        }
    });
});