var calendar_plugin = {
    createEvent_plugin: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Calendar_plugin', // mapped to our native Java class called "CalendarPlugin"
            'addCalendarEvent', // with this action name
            [{                  // and this array of custom arguments to create our entry
               
            }]
        ); 
    }
}
module.exports = calendar_plugin;
