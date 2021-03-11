var vtmax = 0;
var imgUrlPath      = '/Fletes/css/jqgrid/themes/basic/images';
var imgUrlPathError = '<img width="15" src="'+imgUrlPath+'/tab_close-on.gif" border=0>';
var imgUrlPathAdd   = imgUrlPath+'/row_edit.gif';
var imgUrlPathDel   = imgUrlPath+'/row_delete.gif';
var vIncorrecto = imgUrlPathError;
var funciones={
    contexto:"/Fletes",
    anchoGrid: 930,
    parseDate: function(data){
        if (data==""){
            data="01/01/2000";
        }
        return new Date(data.substring(3,5)+"/"+data.substring(0,2)+"/"+data.substring(6,10));
    },
    getD:function(campo,id){
        for(var i=0;i<document.getElementById(campo).length;i++){
            if (document.getElementById(campo)[i].value==id){
                return (document.getElementById(campo).options[i].text);
                break;
            }
        }
    },

    getData:function(dataSource, divID){
        //        try {
        var XMLHttpRequestObject = false;
        if (window.XMLHttpRequest) {
            XMLHttpRequestObject = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if(dataSource != "null"){
            if(XMLHttpRequestObject){
                XMLHttpRequestObject.open("GET", dataSource);
                XMLHttpRequestObject.setRequestHeader('Content-Type','text/html;charset=UTF-8');
                XMLHttpRequestObject.onreadystatechange = function() {
                    if (XMLHttpRequestObject.readyState == 4 ){
                        if (XMLHttpRequestObject.status != 200) {
                            if (XMLHttpRequestObject.status==500){
                                vtmax++;
                                if (vtmax<3){
                                    getData(dataSource, divID);
                                }
                            }
                        //                                else {
                        //                                    alert('Error : Status '+XMLHttpRequestObject.status+' Pagina : '+dataSource+'');
                        //                                    document.getElementById(divID).innerHTML="";
                        //                                }
                        } else {
                            document.getElementById(divID).innerHTML = XMLHttpRequestObject.responseText;
                        }
                    }
                }
                XMLHttpRequestObject.send(null);
            }
        }else{
            document.getElementById(divID).innerHTML = "";
        }
    //        }
    //        catch(e) {
    //            alert(e.name + " - "+e.message);
    //        }
    },

    getDataF:function(dataSource, divID, funcion){
        //        try {
        var XMLHttpRequestObject = false;
        if (window.XMLHttpRequest) {
            XMLHttpRequestObject = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if(dataSource != "null"){
            if(XMLHttpRequestObject){
                XMLHttpRequestObject.open("GET", dataSource);
                XMLHttpRequestObject.setRequestHeader('Content-Type','text/html;charset=UTF-8');
                XMLHttpRequestObject.onreadystatechange = function() {
                    if (XMLHttpRequestObject.readyState == 4 ){
                        if (XMLHttpRequestObject.status != 200) {
                            if (XMLHttpRequestObject.status==500){
                                vtmax++;
                                if (vtmax<3){
                                    getData(dataSource, divID);
                                }
                            }
                            else {
                                alert('Error : Status '+XMLHttpRequestObject.status+' Pagina : '+dataSource+'');
                                document.getElementById(divID).innerHTML="";
                            }
                        } else {
                            document.getElementById(divID).innerHTML = XMLHttpRequestObject.responseText;
                            setTimeout(funcion,10);
                        }
                    }
                }
                XMLHttpRequestObject.send(null);
            }
        }else{
            document.getElementById(divID).innerHTML = "";
        }
    //        } catch(e) {
    //            alert(e.name + " - "+e.message);
    //        }
    },
    getDataF2:function(dataSource, divID,funcion,funcion2){
        //        try {
        var XMLHttpRequestObject = false;
        if (window.XMLHttpRequest) {
            XMLHttpRequestObject = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
        }
        if(dataSource != "null"){
            if(XMLHttpRequestObject){
                XMLHttpRequestObject.open("GET", dataSource);
                XMLHttpRequestObject.setRequestHeader('Content-Type','text/html;charset=UTF-8');
                XMLHttpRequestObject.onreadystatechange = function() {
                    if (XMLHttpRequestObject.readyState == 4 ){
                        if (XMLHttpRequestObject.status != 200) {
                            if (XMLHttpRequestObject.status==500){
                                vtmax++;
                                if (vtmax<3){
                                    getData(dataSource, divID);
                                }
                            }
                        //                                else {
                        //                                    alert('Error : Status '+XMLHttpRequestObject.status+' Pagina : '+dataSource+'');
                        //                                    document.getElementById(divID).innerHTML="";
                        //                                }
                        } else {
                            document.getElementById(divID).innerHTML = XMLHttpRequestObject.responseText;
                            setTimeout(funcion,10);
                            setTimeout(funcion2,400);
                        }
                    }
                }
                XMLHttpRequestObject.send(null);
            }
        }else{
            document.getElementById(divID).innerHTML = "";
        }
    //        } catch(e) {
    //            alert(e.name + " - "+e.message);
    //        }
    },
    
   
    menuExtra:function(){
        if ($('#divExtra').is (':visible')){
            $('#divExtra').hide();
        }else{
            $('#divExtra').show();
        }
    },

    opcionMenuCategorias:function(dataSource, divID){
        funciones.getData(dataSource, divID);
    },

    subcategoriaMenu:function(categoriaId){
        funciones.botonizquierdo(0,1);
        window.location="/portal/site/BusquedaCategoria.action?categoriaId="+categoriaId+"&pagina="+1+"&ordenId="+1;
    },

    //MGT *Funcion que oculta o habilita el div del panel izquierdo
    botonizquierdo:function(fijo,habilita){
        $('#divPrincipalContenidoIzquierda').show();
        if(fijo == 1 && habilita == 1){ //Oculta el panel izquierdo y muestra el boton
            //$('#divPrincipalContenidoIzquierda').hide();            
            $("#contenedorDinamico").animate({
                left: "-200px"
            }, "slow" );
            $("#contenedorDinamicoHidden").css({
                width: "230px"
            });
            $("#panelIzq").show();
        }else if(fijo == 1 && habilita == 0){ //Oculta el panel izquierdo y oculta el boton
            $("#contenedorDinamico").animate({
                left: "-200px"
            }, "slow" );
            $("#contenedorDinamicoHidden").css({
                width: "40px"
            });
            $("#panelIzq").hide();
        }else if(fijo == 0 && habilita == 0){ //Muestra el panel izquierdo y oculta el boton
            $('#divPrincipalContenidoIzquierda').show();
            $('#contenedorDinamico').show("fast", function(){
                $("#contenedorDinamico").css({
                    left: "0px"
                });
            });
            $("#panelIzq").hide();
        }else{ // (0,1) Muestra el panel izquierdo y muestra el boton
            $('#divPrincipalContenidoIzquierda').show();
            $('#contenedorDinamico').show("fast", function(){
                $("#contenedorDinamico").css({
                    left: "0px"
                });
            });
            $("#panelIzq").show();
        }
        $("#panelIzq").bind("click",function(){ //Funcion del onclick que esta en el bot?n del panel deslizable
            var valorLeft1 = $("#contenedorDinamico").css("left");
            if(valorLeft1 == "0px"){
                $("#panelIzq").removeClass("pesta?aMostrarPres pesta?aOculta pesta?aOcultaPres");
                $("#panelIzq").addClass("pesta?aMostrar");
                $("#contenedorDinamico").animate({
                    left: "-200px"
                }, "slow" );
                $("#contenedorDinamicoHidden").animate({
                    width: "40px"
                }, "slow");
            }else{
                $("#panelIzq").removeClass("pesta?aMostrarPres pesta?aMostrar pesta?aOcultaPres");
                $("#panelIzq").addClass("pesta?aOculta");
                $("#contenedorDinamico").animate({
                    left: "0px"
                }, "slow" );
                $("#contenedorDinamicoHidden").animate({
                    width: "230px"
                }, "slow");
            }
        });

        $("#panelIzq").mouseover(function(){// Cambia el color del boton del panel izquierdo cuando
            //se pasa el mause sobre el
            var valorLeft1 = $("#contenedorDinamico").css("left");
            if(valorLeft1 == "0px"){
                $("#panelIzq").addClass("pesta?aOcultaPres");
                $("#panelIzq").removeClass("pesta?aOculta");
            }else{
                $("#panelIzq").addClass("pesta?aMostrarPres");
                $("#panelIzq").removeClass("pesta?aMostrar");
            }
        }).mouseout(function(){
            var valorLeft1 = $("#contenedorDinamico").css("left");
            if(valorLeft1 == "0px"){
                $("#panelIzq").removeClass("pesta?aOcultaPres");
                $("#panelIzq").addClass("pesta?aOculta");
            }else{
                $("#panelIzq").removeClass("pesta?aMostrarPres");
                $("#panelIzq").addClass("pesta?aMostrar");
            }
        });
    },
    confirmaeliminar:function(){
        if(confirm(dwr.util.getValue("key_confirmaeliminar"))){
            return true;
        }else{
            return false;
        }
    },
    confirmaguardar:function(){
        if(confirm(dwr.util.getValue("key_confirmaguardar"))){
            return true;
        }else{
            return false;
        }
    },
    confirmaActualizar:function(){
        if(confirm(dwr.util.getValue("key_confirmaactualizar"))){
            return true;
        }else{
            return false;
        }
    },
    validarnombre:function(nm){
        var expReg = /[\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\-,;.:_ a-zA-Z0-9]+/;
        if ((nm.value.match(expReg)==nm.value)){
            return true;
        }else{
            return false;
        }
    },
    validarnombre1:function(nm){
        var expReg = /[\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\-,;.:_ a-zA-Z]+/;
        if ((nm.value.match(expReg)==nm.value)){
            return true;
        }else{
            return false;
        }
    },
   
    validarnombre2:function(nm){
        var expReg = /[\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\&-,;.:\/_ a-zA-Z0-9]+/;
        if ((nm.value.match(expReg)==nm.value)){
            return true;
        }else{
            return false;
        }
    },
    validaralfabeto:function(nm){
        var expReg = /[a-zA-Z]+/;
        if ((nm.value.match(expReg)==nm.value)){
            return true;
        }else{
            return false;
        }
    },
    validarNumeroEntero:function(campo){
        if(campo.value!=''){
            var RegExPattern = /^[0-9]+$/;
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                return true
            } else {
                funciones.showError(campo.id, document.getElementById("key_funciones_numeroincorrecto").value); 
                return false;
            }
        }
        return true;
    },
    validarNumeroEnteroGenerico:function(campo){
        if(campo.value!=''){
            var RegExPattern = /^[0-9]+$/;
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                return true;
            } else {
                alert(dwr.util.getValue("key_funciones_numeroincorrecto"));
                return false;
            }
        }
        return true;
    },

    validateCampEntero:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern = /^[0-9-]+$/;
            e=document.getElementById(txtId);
            if (campo.value.match(RegExPattern)) {
                document.getElementById(txtId+"Error").innerHTML = "";
                return true;
            }else{
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(document.getElementById("key_funciones_numeroincorrecto").value);
                setTimeout('e.focus()',50);
                return false;
            }
        }else{
            document.getElementById(txtId+"Error").innerHTML = "";
            return true;
        }
    },
    camposInfo:function(name, msgName, longitud, msgLongitud, isnumerico, msgNumerico) {
        this.name           = name;
        this.msgName        = msgName;
        this.longitud       = longitud;
        this.msgLongitud    = msgLongitud;
        this.isnumerico     = isnumerico;
        this.msgNumerico    = msgNumerico;
    },
    showError:function(name, msg){
        vIncorrecto = imgUrlPathError;
        document.getElementById(name+"Error").innerHTML = vIncorrecto;
        document.getElementById(name).focus();
        alert(msg);
    },
    validaCampos:function(campos){
        vInval = true;
        for(var i=0; i<campos.length; i++){
            funciones.trim(document.getElementById(campos[i].name));
            if(dwr.util.getValue(campos[i].name) == ""){
                funciones.showError(campos[i].name, campos[i].msgName);
                vInval = false;
                return (vInval);
            }
            else{
                if(dwr.util.getValue(campos[i].name).length > parseInt(campos[i].longitud)){
                    funciones.showError(campos[i].name, campos[i].msgLongitud);
                    vInval = false;
                    return (vInval);
                }else{
                    if(campos[i].isnumerico == true && isNaN(dwr.util.getValue(campos[i].name))){
                        funciones.showError(campos[i].name, campos[i].msgNumerico); 
                        vInval = false;
                        return (vInval);
                    }
                }
            }
        }
        return (vInval);
    },

    validatePassword:function(campo,txtId){
        if(campo.value!=''){
            var RegExPattern =/^[0-9A-Za-z,.%&()+-_]{8,20}$/;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            } else {
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(document.getElementById("key_funciones_passwordincorrecto").value);
                setTimeout('e.focus()',50);
            }
        }else{
            document.getElementById(txtId+"Error").innerHTML = "";
        }
    },

    
    //Esta funci?n valida un texto con formato de correo electronico (el nombre de la cuenta)
    //debe de contener 3 caracteres al inicio, seguidos de un @ seguido
    //de al menos 2 caracteres, seguidos de 1 punto (y repeticiones de esta) (el dominio(s))
    //y debe terminar con 2-4 caracteres (el tipo de dominio com, mx, org, etc)
    validateCampEmail:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern = /[\w-\.]{3,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            }else{
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(document.getElementById("key_funciones_mailincorrecto").value);
                setTimeout('e.focus()',50);
            }
        }else{
            document.getElementById(txtId+"Error").innerHTML = "";
        }
    },

    validaNombreUsuario:function(nm){
        var expReg = /[\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\-,;.:_a-zA-Z]+/;
        if ((nm.value.match(expReg)==nm.value)){
            return true;
        }else{
            alert(document.getElementById("key_nombreinvalido").value);
            setTimeout('nm.focus()',50);
            return false;
        }
    },
    validateTextoEspecial:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern = /^\s*[a-zA-Z0-9,;.-:??_\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\-\s]+\s*$/ ;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            } else {
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(document.getElementById("key_funciones_textoespecialincorrecto").value);
                setTimeout('e.focus()',50);
            }
        }else{
            document.getElementById(txtId+"Error").innerHTML = "";
        }
    },
    validateTextoEspecialTodosCaracteres:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern = /^\s*[a-zA-Z0-9.,;:{}_-\u00BA\u0040\u00AE\u00A8\u002F\u003F\u00A6\u00BF\u002B\u002A\u0029\u0028\u0027\u0026\u0025\u0021\u0022\u0023\u0024\u005E\u005D\u005C\u005B\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\-\s]+\s*$/ ;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            } else {
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(document.getElementById("key_funciones_textoespecialincorrecto").value);
                setTimeout('e.focus()',50);
            }
        }else{
            document.getElementById(txtId+"Error").innerHTML = "";
        }
    },
    oNumero:function(numero){
        //Propiedades
        this.valor = numero || 0
        this.dec = -1;
        //Métodos
        this.formato = numFormat;
        this.ponValor = ponValor;
        //Definición de los métodos
        function ponValor(cad){
            if (cad =='-' || cad=='+') return
            if (cad.length ==0) return
            if (cad.indexOf('.') >=0)
                this.valor = parseFloat(cad);
            else
                this.valor = parseInt(cad);
        }
        function numFormat(dec, miles)
        {
            var num = this.valor, signo=3, expr;
            var cad = ""+this.valor;
            var ceros = "", pos, pdec, i;
            for (i=0; i < dec; i++)
                ceros += '0';
            pos = cad.indexOf('.')
            if (pos < 0)
                cad = cad+"."+ceros;
            else
            {
                pdec = cad.length - pos -1;
                if (pdec <= dec)
                {
                    for (i=0; i< (dec-pdec); i++)
                        cad += '0';
                }
                else
                {
                    num = num*Math.pow(10, dec);
                    num = Math.round(num);
                    num = num/Math.pow(10, dec);
                    cad = new String(num);
                }
            }
            pos = cad.indexOf('.')
            if (pos < 0) pos = cad.lentgh
            if (cad.substr(0,1)=='-' || cad.substr(0,1) == '+')
                signo = 4;
            if (miles && pos > signo)
                do{
                    expr = /([+-]?\d)(\d{3}[\.\,]\d*)/
                    cad.match(expr)
                    cad=cad.replace(expr, RegExp.$1+','+RegExp.$2)
                }
                while (cad.indexOf(',') > signo)
            if (dec<0) cad = cad.replace(/\./,'')
            return cad;
        }
    },//Fin del objeto oNumero
    getDataXF:function(dataSource,funcion){
        try {
            var XMLHttpRequestObject = false;
            if (window.XMLHttpRequest) {
                XMLHttpRequestObject = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
            }
            if(dataSource != "null"){
                if(XMLHttpRequestObject){
                    XMLHttpRequestObject.open("GET", dataSource);
                    XMLHttpRequestObject.setRequestHeader('Content-Type','text/html;charset=UTF-8');
                    XMLHttpRequestObject.onreadystatechange = function() {
                        if (XMLHttpRequestObject.readyState == 4 ){
                            setTimeout(funcion,10);
                        }
                    }
                    XMLHttpRequestObject.send(null);
                }
            }
        } catch(e) {
            alert(e.name + " - "+e.message);
        }
    },
    setCookie:function(c_name,value,expiredays){
        var exdate=new Date();
        exdate.setDate(exdate.getDate()+expiredays);
        document.cookie=c_name+ "=" +escape(value)+
        ((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
    },
    validateNumeroDecimal:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern = /^[0-9]{1,12}(.[0-9]{1,2})?$/;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            } else {
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(document.getElementById("key_funciones_numerodecimalincorrecto").value);
                setTimeout('e.focus()',50);
                
            }
        }else{
            document.getElementById(txtId+"Error").innerHTML = "";
        }
    },
    validateNumeroDecimalGenerico:function(campo) {
        if(campo.value!=''){
            var RegExPattern = /^[0-9]{1,10}(\.[0-9]{1,2})?$/;
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
               
            } else {
                
                alert(document.getElementById("key_funciones_numerodecimalincorrecto").value);
                
            }
        }
        return true;
    },
    trim : function(obj) {
        obj.value=obj.value.replace(/^\s+|\s+$/g,"");
    },
    ltrim: function(obj) {
        obj.value=obj.value.replace(/^\s+/,"");
    },
    rtrim : function(obj) {
        obj.value=obj.value.replace(/\s+$/,"");
    },

    validaNumFromKeyboard: function(evt){
        //asignamos el valor de la tecla a keynum
        if(window.event){// IE
            keynum = evt.keyCode;
        }else{
            keynum = evt.which;
        }
        //comprobamos si se encuentra en el rango
        if(keynum>47 && keynum<58){
            return true;
        }else{
            return false;
        }

    },

    //funci?n que valida nombres propios
    //mayusculas, minusculas, ?, ?,
    //vocales con acento (mayusculas y minusculas), espacios, punto, guion, dieresis, ampersand, asterisco, grados, diagonal, parentesis, coma
    validateCalle:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern = /^[a-zA-Z0-9\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC\/\&\?\?\?\?\?\?\?\?\*\?\(\)\,\.\-\s]+\s*$/ ;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            } else {
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(dwr.util.getValue("key_funciones_textoespecialincorrecto"));
                setTimeout('e.focus()',50);
            }
        }
    },

    //funci?n que valida textos que incluyan mayusculas, minusculas, ?,?
    //vocales con acento, espacios, punto, gui?n, dieresis
    validateCalleNumero:function(campo,txtId) {
        if(campo.value!=''){
            var RegExPattern =/^[a-zA-Z0-9\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC\.\?\?\?\?\?\?\?\?\-\s]+\s*$/;
            e=document.getElementById(txtId);
            if ((campo.value.match(RegExPattern)) && (campo.value!='')) {
                document.getElementById(txtId+"Error").innerHTML = "";
            } else {
                document.getElementById(txtId+"Error").innerHTML = vIncorrecto;
                alert(dwr.util.getValue("key_funciones_textoespecialincorrecto"));
                setTimeout('e.focus()',50);
            }
        }
    },

    unformatNumber: function(num) {
        return num.replace(/([^0-9\.\-])/g,'')*1;
    },
        
    setFocusCampo:function(campoNombre){
        $('#' + campoNombre).focus();
    }


//Mas en: http://javascript.espaciolatino.com/
//Objeto oNumero
//    oNumero:function(numero){
//        //Propiedades
//        this.valor = numero || 0
//        this.dec = -1;
//        //Métodos
//        this.formato = numFormat;
//        this.ponValor = ponValor;
//        //Definición de los métodos
//        function ponValor(cad)
//        {
//            if (cad =='-' || cad=='+') return
//            if (cad.length ==0) return
//            if (cad.indexOf('.') >=0)
//                this.valor = parseFloat(cad);
//            else
//                this.valor = parseInt(cad);
//        }
//        function numFormat(dec, miles)
//        {
//            var num = this.valor, signo=3, expr;
//            var cad = ""+this.valor;
//            var ceros = "", pos, pdec, i;
//            for (i=0; i < dec; i++)
//                ceros += '0';
//            pos = cad.indexOf('.')
//            if (pos < 0)
//                cad = cad+"."+ceros;
//            else
//            {
//                pdec = cad.length - pos -1;
//                if (pdec <= dec)
//                {
//                    for (i=0; i< (dec-pdec); i++)
//                        cad += '0';
//                }
//                else
//                {
//                    num = num*Math.pow(10, dec);
//                    num = Math.round(num);
//                    num = num/Math.pow(10, dec);
//                    cad = new String(num);
//                }
//            }
//            pos = cad.indexOf('.')
//            if (pos < 0) pos = cad.lentgh
//            if (cad.substr(0,1)=='-' || cad.substr(0,1) == '+')
//                signo = 4;
//            if (miles && pos > signo)
//                do{
//                    expr = /([+-]?\d)(\d{3}[\.\,]\d*)/
//                    cad.match(expr)
//                    cad=cad.replace(expr, RegExp.$1+','+RegExp.$2)
//                }
//            while (cad.indexOf(',') > signo)
//                if (dec<0) cad = cad.replace(/\./,'')
//            return cad;
//        }
//    }//Fin del objeto oNumero
};

var navegadorNegocio={
    nombre : navigator.appName,
    IE: false,
    NS: false,
    OP:false,
    getNavegador: function(){
        if (/Chrome[\/\s](\d+\.\d+)/.test(navigator.userAgent)){
            return "CR";
        }else{
            if (/Safari[\/\s](\d+\.\d+)/.test(navigator.userAgent)){
                return "SF";
            }else{
                this.IE = this.nombre.toUpperCase().indexOf('MICROSOFT') >=0;
                if (this.IE)
                    return "IE";
                this.NS = this.nombre.toUpperCase().indexOf('NETSCAPE') >=0;
                if (this.NS)
                    return "NS";
                this.OP = this.nombre.toUpperCase().indexOf('OPERA') >= 0;
                if (this.OP)
                    return "OP";
            }
        }
    }
};

var dateFormat = function () {
    var	token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
    timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
    timezoneClip = /[^-+\dA-Z]/g,
    pad = function (val, len) {
        val = String(val);
        len = len || 2;
        while (val.length < len) val = "0" + val;
        return val;
    };

    // Regexes and supporting functions are cached through closure
    return function (date, mask, utc) {
        var dF = dateFormat;

        // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
        if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
            mask = date;
            date = undefined;
        }

        // Passing date through Date applies Date.parse, if necessary
        date = date ? new Date(date) : new Date;
        if (isNaN(date)) throw SyntaxError("invalid date");

        mask = String(dF.masks[mask] || mask || dF.masks["default"]);

        // Allow setting the utc argument via the mask
        if (mask.slice(0, 4) == "UTC:") {
            mask = mask.slice(4);
            utc = true;
        }

        var	_ = utc ? "getUTC" : "get",
        d = date[_ + "Date"](),
        D = date[_ + "Day"](),
        m = date[_ + "Month"](),
        y = date[_ + "FullYear"](),
        H = date[_ + "Hours"](),
        M = date[_ + "Minutes"](),
        s = date[_ + "Seconds"](),
        L = date[_ + "Milliseconds"](),
        o = utc ? 0 : date.getTimezoneOffset(),
        flags = {
            d:    d,
            dd:   pad(d),
            ddd:  dF.i18n.dayNames[D],
            dddd: dF.i18n.dayNames[D + 7],
            m:    m + 1,
            mm:   pad(m + 1),
            mmm:  dF.i18n.monthNames[m],
            mmmm: dF.i18n.monthNames[m + 12],
            yy:   String(y).slice(2),
            yyyy: y,
            h:    H % 12 || 12,
            hh:   pad(H % 12 || 12),
            H:    H,
            HH:   pad(H),
            M:    M,
            MM:   pad(M),
            s:    s,
            ss:   pad(s),
            l:    pad(L, 3),
            L:    pad(L > 99 ? Math.round(L / 10) : L),
            t:    H < 12 ? "a"  : "p",
            tt:   H < 12 ? "am" : "pm",
            T:    H < 12 ? "A"  : "P",
            TT:   H < 12 ? "AM" : "PM",
            Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
            o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
            S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
        };

        return mask.replace(token, function ($0) {
            return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
        });
    };
}();

// Some common format strings
dateFormat.masks = {
    "default":      "ddd mmm dd yyyy HH:MM:ss",
    shortDate:      "m/d/yy",
    mediumDate:     "mmm d, yyyy",
    longDate:       "mmmm d, yyyy",
    fullDate:       "dddd, mmmm d, yyyy",
    shortTime:      "h:MM TT",
    mediumTime:     "h:MM:ss TT",
    longTime:       "h:MM:ss TT Z",
    isoDate:        "yyyy-mm-dd",
    isoTime:        "HH:MM:ss",
    isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
    isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
dateFormat.i18n = {
    dayNames: [
    "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
    "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    ],
    monthNames: [
    "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
    "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    ]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
    return dateFormat(this, mask, utc);
};



