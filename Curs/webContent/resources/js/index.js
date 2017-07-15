$(document).ready(function() {

	$('.parallax').parallax();

    $('#swipe-dates').tabs();

	$('select').material_select();

	$('.button-collapse').sideNav();
	
	$('.datepicker').pickadate({
	    selectMonths: true, 
	    selectYears: 2, 
	    format: 'dd.mm.yyyy',
	    min: Date.now(),
	    firstDay: 'Monday',
	 // Strings and translations
	    monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
	    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
	    weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
	    weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
	    showMonthsShort: undefined,
	    showWeekdaysFull: undefined,

	    // Buttons
	    today: 'Today',
	    clear: 'Clear',
	    close: 'Close',

	    // Accessibility labels
	    labelMonthNext: 'Next month',
	    labelMonthPrev: 'Previous month',
	    labelMonthSelect: 'Select a month',
	    labelYearSelect: 'Select a year'
	  });

    $('.timepicker').pickatime({
        default: 'now',
        twelvehour: false, // change to 12 hour AM/PM clock from 24 hour
        donetext: 'OK',
        autoclose: false,
        vibrate: true // vibrate the device when dragging clock hand
    });

});

function reload () {
   location.reload();
}



function changeLocale(lang) {

	$.ajax({
		url : "changeLocale.jsp",
		cache : false,
		type : 'POST',
		data : {
			lang : lang
		},
		success : function(){
			reload();
		}

	});

}

