define([
    'backbone.marionette',
    'handlebars',
    'text!app/templates/input.html'
], function(Marionette, Handlebars, inputTemplate) {
    'use strict';

    return Backbone.Marionette.ItemView.extend({
        template: Handlebars.compile(inputTemplate)
    });
});