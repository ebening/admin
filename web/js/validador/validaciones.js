// Tamaño minimo de caracteres 2
ProfileMinLength2 = {
    name: "validanguage.validateMinlength(text, 2)",
    errorMsg: 'Mínimo 2 caracteres',
    onerror: utilEffect.showValidationTooltip,
    onsuccess: utilEffect.hideValidationTooltip
}
// Campo requerido
ProfileRequiredField = {
    name: "validanguage.validateRequired(text)",
    errorMsg: "Este valor es requerido",
    onerror: utilEffect.showValidationTooltip,
    onsuccess: utilEffect.hideValidationTooltip
}
// Uso de caracteres no validos
ProfileCheckForInvalidCharacter = {
    name: "validanguage.validateCharacters(text)",
    errorMsg: jQuery('#msgValidacionCaracter').val(),
    onerror: utilEffect.showValidationTooltip,
    onsuccess: utilEffect.hideValidationTooltip
}
// mail
ProfileMail = {
    name: "validanguage.validateEmail",
    errorMsg: jQuery('#msgValidacionMail').val(),
    onerror: utilEffect.showValidationTooltip,
    onsuccess: utilEffect.hideValidationTooltip
}
// Elimina espacios en blanco antes y despues del string
ProfileStripWhitespace = {
    name: validanguage.stripWhitespace
}
//Combo no seleccionado
ProfileRequiredComboField = {
	name: function(){
		if(this.value=="" || this.value=="-1") return false;
		return true;
	},
    errorMsg: "Este valor es requerido",
    onerror: utilEffect.showValidationTooltip,
    onsuccess: utilEffect.hideValidationTooltip
}

