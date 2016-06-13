Ext.define('Applitest.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Applitest.view.appreportui.ReportViewController',
	            'Applitest.view.appreportui.datagrid.DataGridPanel',
	            'Applitest.view.appreportui.datagrid.DataGridView',
	            'Applitest.view.appreportui.querycriteria.QueryCriteriaView',
	            'Applitest.view.appreportui.chart.ChartView',
	            'Applitest.view.appreportui.datapoint.DataPointView',
	            'Applitest.view.googlemaps.map.MapPanel',
	            'Applitest.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
