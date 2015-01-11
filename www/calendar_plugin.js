cordova.define("com.onbond.calendar.Calendar_plugin", function(require, exports, module) { var calendar = {
    createEntry: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Calendar', // mapped to our native Java class called "CalendarPlugin"
            'addCalendarEvent', // with this action name
            [{                  // and this array of custom arguments to create our entry
               
            }]
        ); 
    }
}
module.exports = calendar;

});
