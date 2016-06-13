Ext.define('Applitest.view.fw.DropDownRange', {
    extend : 'Ext.panel.Panel',
    requires : ['Applitest.view.fw.DropDownRangeController'],
	xtype : 'dropdownrange',
	controller:'DropDownRangeController',
	items : [ {
		xtype : 'fieldset',
		itemId:'filedSetId',
		defaults : {
			anchor : '100%'
		},
		layout : 'anchor',
		items : [ {
			xtype : 'radiogroup',
			itemId : 'dropDownRadio',
			margin : '0 0 10 0',
			vertical : true,
			columns : 2,
			items : [ {
				boxLabel : 'All',
				name : 'dropdownRadio',
				inputValue : 'range',
				margin : '0 25 0 0',
				checked : true
			}, {
				boxLabel : 'Custom',
				name : 'dropdownRadio',
				inputValue : 'custom'
			} ],
			listeners : {
				change : 'afterRadioChange'
			}
		}, {
			xtype:'combo',
			itemId:'fromCombo',
			emptyText:'Select',
			labelWidth:130,
			queryMode : 'local',
			displayField : "primaryDisplay",
			valueField : "primaryKey"
				
		},{
			xtype:'combo',
			itemId:'toCombo',
			emptyText:'Select',
			labelWidth:130,
			queryMode : 'local',
			displayField : "primaryDisplay",
			valueField : "primaryKey"
		} ]
	}]

});
