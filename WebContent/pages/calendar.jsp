<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Calendário</title>
<link href='../script/fullcalendar.min.css' rel='stylesheet' />
<link href='../script/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='../script/lib/moment.min.js'></script>
<script src='../script/lib/jquery.min.js'></script>
<script src='../script/fullcalendar.min.js'></script>
<script>
	$(document).ready(function() {

		$('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,basicWeek,basicDay'
			},
			defaultDate : '2017-02-12',
			navLinks : true, // can click day/week names to navigate views
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			events : [ {
				title : 'All Day Event',
				start : '2017-02-01'
			}, {
				title : 'Long Event',
				start : '2017-02-07',
				end : '2017-02-10'
			}, {
				id : 999,
				title : 'Repeating Event',
				start : '2017-02-09T16:00:00'
			}, {
				id : 999,
				title : 'Repeating Event',
				start : '2017-02-16T16:00:00'
			}, {
				title : 'Conference',
				start : '2017-02-11',
				end : '2017-02-13'
			}, {
				title : 'Meeting',
				start : '2017-02-12T10:30:00',
				end : '2017-02-12T12:30:00'
			}, {
				title : 'Lunch',
				start : '2017-02-12T12:00:00'
			}, {
				title : 'Meeting',
				start : '2017-02-12T14:30:00'
			}, {
				title : 'Happy Hour',
				start : '2017-02-12T17:30:00'
			}, {
				title : 'Dinner',
				start : '2017-02-12T20:00:00'
			}, {
				title : 'Birthday Party',
				start : '2017-02-13T07:00:00'
			}, {
				title : 'Click for Google',
				url : 'http://google.com/',
				start : '2017-02-28'
			} ]
		});

	});
</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>

</head>
<body>
	<div id='calendar'></div>
</body>
</html>