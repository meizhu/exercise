define([
    'backbone.marionette',
    'handlebars',
    'text!app/templates/layouts/main.html'
], function(Marionette, Handlebars, mainLayoutTemplate){
    'use strict';

    return Marionette.Layout.extend({
        template: Handlebars.compile(mainLayoutTemplate),
        regions: {
            input: '.input',
            content: '.content'
        }
    });
});