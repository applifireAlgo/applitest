Ext.define('Applitest.view.databrowsercalendar.DBCalendar', {
	extend : 'Applitest.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Applitest.view.databrowsercalendar.DBCalendarController',
	             'Applitest.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
