'use strict';


var Client = require('node-rest-client').Client;
var underWeatherBaseUrl = "http://api.wunderground.com/api/3d936d5bee1de223/conditions/q/";
var stations = ["CA/Campbell", "NE/Omaha", "TX/Austin"];

module.exports = function (app) {

   var client = new Client();

   app.get('/', function (req, res) {
        var allWeather={ weatherList: [],
                        finished: function() {
                            console.log("current returned data length: " + this.weatherList.length);
                            return this.weatherList.length===stations.length;
                        }

                        };
       for(var i=0; i<stations.length; i++)  {
           fetchWeatherData(stations[i], allWeather, res);
       }
    });

    function fetchWeatherData(station, allWeather, res){
        var restUrl = underWeatherBaseUrl + station + ".json";
        console.log("Fetching weather data: " + restUrl);
        var req = client.get(restUrl, function(data, response){
            console.log("Response data from " + restUrl +": " + data);
          //  console.log("Response object: " + response.toString());
            var weatherObj = JSON.parse(data);
            allWeather.weatherList.push(weatherObj);
            if(allWeather.finished()){
                console.log("All weather data acquired, about to render");
                res.render('index', allWeather);
            }else{
                console.log("Station " + station + " data is fetched, waiting for others to be done");
            }
        });
    }

};
