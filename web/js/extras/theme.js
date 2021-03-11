jQuery(document).ready(function(){
var fileref = document.createElement('link');
fileref.setAttribute('rel', 'stylesheet');
var theme_name = 'skin';
fileref.setAttribute('type', 'text/css');
fileref.setAttribute('href', '/admon/css/themes/'+ theme_name + '/jquery-ui.min.css');
document.getElementsByTagName('head')[0].appendChild(fileref);
});

