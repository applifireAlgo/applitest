Ext.define('Applitest.view.fw.DropDownRangeController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.DropDownRangeController',
	afterRadioChange:function(){
		var dropDownRadio=this.getView().down("#dropDownRadio");
		var dropdownrange=this.getView("#dropdownrange");
		if(dropDownRadio.getValue().dropdownRadio=="custom"){
			var fromCombo=this.getView().down("#fromCombo");
			var toCombo=this.getView().down("#toCombo");
			fromCombo.setValue(dropdownrange.defaultValue);
			toCombo.setValue(dropdownrange.toDefaultValue);
		}
	}
});