'use strict';


var IndexModel = require('../models/index');
var Client = require('node-rest-client').Client;


module.exports = function (app) {

    var model = new IndexModel();
    var client = new Client();

    app.get('/', function (req, res) {
        var underWeatherBaseUrl = "http://api.wunderground.com/api/3d936d5bee1de223/forecast/conditions/q/";
        var caCampbell =underWeatherBaseUrl +"CA/Campbell.json";
        var req = client.get(caCampbell, function(data, response){
                console.log("Response data from " + caCampbell +": " + data);
           console.log("Type: "+ typeof data);
            var myObject = JSON.parse(data);
            console.log("temp: "+ myObject['current_observation']['temp_f']);
            var forecast = myObject['forecast']['simpleforecast']['forecastday'];
            console.log("Number of days: " + forecast.length);
            for(var i=0;i< forecast.length; i++){
                var dayCast = forecast[i];
                var currDate = new Date(dayCast['date']['year'], dayCast['date']['month']-1, dayCast['date']['day'] );
                var date2 = new Date(2014, 1, 15);
                console.log("Same date? " +(currDate.getTime() ==date2.getTime()) +", date2=" + date2.toDateString());
                console.log("Day=" + currDate.toDateString()
                           +", high=" + dayCast['high']['fahrenheit']
                            +", low=" + dayCast['low']['fahrenheit']
                            +", condition=" + dayCast['conditions']);
            }
             //   console.log(response);
               model.name =data;
                res.render('index', model);
        });


        //res.render('index', model);
        
    });

};
