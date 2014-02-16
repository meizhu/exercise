define([
    'backbone.marionette',
    'handlebars',
    'text!app/templates/content.html'
], function(Marionette, Handlebars, contentTemplate) {
    'use strict';

    return Backbone.Marionette.ItemView.extend({
        template: Handlebars.compile(contentTemplate)
    });
});