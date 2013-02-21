/**
 * Librería de pluguins para crear elementos en el DOM
 * a partir de objetos
 *
 * @autor: María Galbis
 * @version: 1.0
 **/

//**** CREATE TABLE *************//
(function ($) {
    "use strict";
    
    var defaults = {
        data : {},
        nulls: false, //si el valor está vacío
        objects: false, //si el valor es un objeto. Si es true: 'nombre del indice'
        lists: false,  //si el valor es una lista
        maxChars: 140,  //cualquier número: 'all'
        crud: false  //si es una tabla de mantenimiento
    }, settings,
        
    methods = {
        
        init: function () {
            var object = methods.filter(settings.data),  //aplicamos filtro
            btn,     //botones crud
            txt = '<table class="table "><thead><tr>';  //creamos tabla y cabecera
         
          //mapeamos el objeto json y creamos un array sólo con los índices
            var thead = $.map (object[0], function(n, i) {  
                return i;
            });
    
            $.each (thead, function (i, v) { //los colocamos en la cabecera
               
                    txt += '<th>'+(v=='Id'|v=='id'?'#':v)+'</th>';
            })
            
            if(settings.crud) {
                txt += '<th></th>';
            }
            
            txt += '</tr></thead><tbody>'; //abrimos el cuerpo de la tabla
            
            $.each(object, function (i,v) {  //por cada fila
                txt += '<tr>';
                for(var c=0;c<thead.length;c++) { //recorremos campos
                    
                        txt += '<td>'+v[thead[c]]+'</td>';  //creamos celda
                }
                
                
                if(settings.crud){  //si están activados los botones
                    txt += '<td><div class="btn-group">'
                        + $().Button({ 
                            id: v[thead[0]], 
                            class: 'ver btn', 
                            content: '<i class="icon-eye-open"></i>',
                            html: true })
                        + $().Button({ 
                            id: v[thead[0]], 
                            class: 'editar btn', 
                            content: '<i class="icon-pencil"></i>', 
                            html: true })
                        + $().Button({ 
                            id: v[thead[0]], 
                            class: 'borrar btn', 
                            content: '<i class="icon-trash"></i>', 
                            html: true })
                        + '</div></td>';
                } 

            })
            txt += '</tbody></table>'; // cerramos tabla
    
            return txt;
           
        },
        
        //filtra columnas según la configuración elegida
        filter: function (object) {

            if(!settings.lists) {
                $.each(object, function (i,v) {
                    $.each(v, function (i2, v2) {

                        if($.isArray(v2)) delete v[i2];
                    });
                })
            }
            
            $.each(object, function (i,v) {
                $.each(v, function (i2, v2) {

                    if($.isPlainObject(v2)) {
                        if(!settings.objects) {
                            delete v[i2];
                        } else {
                            v[i2] = v[i2][settings.objects];
                        }
                        
                    }
                })
            })

            if(!settings.nulls) {
                $.each(object, function (i,v) {
                    $.each(v, function (i2, v2) {

                        if(v2 == '') delete v[i2];
                    });
                })
            }
            
            if(settings.maxChars != 'all') {
                var column = new Array();
                
                $.each(object, function (i,v) {
                    $.each(v, function (i2, v2) {

                        if(v2.toString().length > settings.maxChars) column.push(i2); 
                    });
                })
      
                $.each(object, function (i,v) {
                    $.each(v, function (i2, v2) {
                        
                        if($.inArray(i2, column) != -1) delete v[i2];
                    });
                })
            }
            
            return object;
        }
        
        
    };
    
    $.fn.Table = function (options) {
        settings = $.extend ({}, defaults, options);

        var table = methods.init ()
        return this.html(table);
    };
    
})(jQuery);

//************************************************//


//******* CREATE BUTTON **************************//

(function ($) {
    "use strict";
    
    var defaults = {
        id: false,
        class: false,
        title: false,
        content: 'boton',
        html: false
    }, settings,
        
    init = function () {
        var txt = '<button type="button" ';
        if(settings.id != false) txt +='id="'+settings.id+'" ';
        if(settings.class != false) txt += 'class="'+settings.class+'" ';
        if(settings.title != false) txt += 'title="'+settings.title+'"';
        txt += '>'+settings.content+'</button>';
        return txt; 
    };

    $.fn.Button = function(options) {
        settings = $.extend({}, defaults, options);
        var button = init();
        if(settings.html) return button;
        else return this.append(button);
    };
    
})(jQuery);

//*************************************************//

//******** CREATE FIELD ***************************//

(function ($) {
    "use strict";
    
    var defaults = {
        id: false,
        class: false,
        name: false,
        placeholder: false,
        content: '',
        value: '',
        type: 'text',
        disabled: false,
        html: false
    }, settings,
        
    methods = {
        
        init: function () {
            return methods[settings.type=='simple'|settings.type=='multiple'?'list':settings.type]();
        },
        
        text: function () {
            var txt = '<input type="text"';
            txt += methods.attributes();
            txt += ' value="'+settings.value+'" />';
            return txt;
        },
        
        hidden: function(){
            var txt = '<input type="hidden"';
            txt += methods.attributes();
            txt += ' value="'+settings.value+'" />';
            return txt;
        },
        
        typeahead: function () {
            var txt = '<input type="text"';
            txt += methods.attributes();
            txt += ' value="'+settings.value+'" />';
            return txt;
        },
        
        date: function () {
            var txt = '<div class="input-append date" id="dp3" data-date-format="dd-mm-yyyy">'
                +'<input type="text" ';
            txt += methods.attributes();
            txt += ' value="'+settings.value+'" disabled/>'
                + '<span class="add-on"><i class="icon-calendar"></i></span></div>';
            return txt;
        },
        
        textarea: function () {
            var txt = '<textarea ';
            txt += methods.attributes();
            txt += '>'+settings.content+'</textarea>';
            return txt;
        },
        
        radio: function () {
            var txt = '<input type="radio"';
            txt += methods.attributes();
            txt += ' value="'+settings.value+'" />';
            txt += settings.content;
            return txt;
        },
        
        checkbox: function () {
            var txt = '<input type="checkbox"';
            txt += methods.attributes();
            txt += ' value="'+settings.value+'" />';
            txt += settings.content;
            return txt;
        },
        
        select: function () {
            var txt = '<select ';
            txt += methods.attributes();
            txt += '>'+methods.options()+'</select>';
            return txt;
        },
        
        list: function () {
            var txt = '<ul ';
            txt += methods.attributes() + '>';
            txt += methods.li();
            txt += '</ul>';
            txt += $().Button({
                html: true,
                class: settings.type,
                content: '<i class="icon-list"></i>'
            });
            
            return txt;
        },
        
        options: function () {
            var txt = '';

            $.each(settings.content, function(){
                txt += '<option value="'+this.id+'">'+this.nombre+'</option>';
            })
            
            return txt;
        },
        
        li: function(){
            var txt = '';

            $.each(settings.content, function(){
                var val = this.nombre == undefined ? this.titulo : this.nombre;
                txt += '<li value="'+this.id+'">'+val+'</li>';
            })
            
            return txt;
        },
        
        attributes: function () {
            var txt = '';
            
            if(settings.id != false) txt +='id="'+settings.id+'" ';
            if(settings.class != false) txt += 'class="'+settings.class+'" ';
            if(settings.name != false) txt += 'name="'+settings.name+'" ';
            if(settings.placeholder != false) txt += 'placeholder="'+settings.placeholder+'" ';
            if(settings.disabled) txt += 'disabled="disabled" ';
                
            return txt;
        }
        
    };

    $.fn.Field = function (options) {
        settings = $.extend({}, defaults, options);
        
        var field = methods.init();

        if(settings.html) return field;
        else return this.append(field);
    };
    
})(jQuery);

//*************************************************//


//******** CREATE FORM ****************************//

(function ($) {
    "use strict";
    
    var defaults = {
        data: {},
        id: false, 
        class: false,
        html: false,
        legend: ''
    }, settings, specialData,
        
    methods = {
     
        init: function () {
            var txt = '<form ';
            if(settings.id != false) txt +='id="'+settings.id+'" ';
            if(settings.class != false) txt += 'class="'+settings.class+'" ';
            if(settings.name != false) txt += 'name="'+settings.name+'" ';
            txt += '>'
                + '<fieldset><legend>'+settings.legend+'</legend>';
            
            $.each(settings.data, function (i,v) {
                    txt += methods.control(i,v);
            })
            
            txt += '</form>';
            
            return txt;
        },
        
        control: function (label, content) {
            
            var txt = '<div class="control-group">';
            if(content != 'hidden'){
              txt += '<label class="control-label" for="'+label+'">'+label+'</label>';
            }
              txt += '<div class="controls">';
                //alert(label+' '+content);
            txt += methods.typeValidation(label,content);
            
            txt += '</div></div>';
           
            return txt;
            
        },
        
        typeValidation: function (label,content) {
            var txt = '';
            var index;
            $.each(content, function(i,v){ index = i });
            //alert(index);
            if($.isPlainObject(content)){
                if(index == 'simple' | index == 'multiple'){
                    txt = $().Field({
                        type: index, 
                        id: label, 
                        content: content[index], 
                        html: true,
                        class: 'input-xlarge'
                    });
                } else {
                    txt = $().Field({
                        type: 'select', 
                        id: label, 
                        content: content[index], 
                        html: true,
                        class: 'input-xlarge'
                    });
                }
                
            } else if($.isArray(content)){
                
                txt = $().Field({
                    type: 'select', 
                    id: label, 
                    class: 'input-xlarge',
                    content: content,
                    html: true
                });
                
                //specialData = content;
            }
            
            else {
                
                if(content == 'long'){
                    txt = $().Field({
                        type: 'textarea', 
                        id: label,  
                        html: true,
                        class: 'input-xlarge'
                    });
                    
                } else if(content == 'date'){
                    txt = $().Field({
                        type: 'date', 
                        id: label, 
                        html: true,
                        class: 'input-xlarge'
                    });
                
                } else if(content == 'hidden'){
                    txt = $().Field({
                       type: 'hidden',
                       id: label,
                       html: true,
                       class: 'input-xlarge'
                    });
                } else {
                    txt = $().Field({
                        type: 'text', 
                        id: label, 
                        html: true,
                        class: 'input-xlarge'
                    });
                }
                
                
                
            }
            
            return txt;
        }
        
    };
    
    $.fn.Form = function (options, callback) {
       
        settings = $.extend({}, defaults, options);
       
        var form = methods.init();

        if(settings.html) return form;
        else this.append(form);
        
        $('.date').datepicker({
            language: 'es',
            autoclose: true
            });
        //alert($('#form').html())
        if(callback) callback();
    };
    
})(jQuery);

//************************************************//

//******** FILL FORM *****************************//

(function ($) {
    
    var defaults = {
        data: {}
    }, settings,
        
    methods = {
        init: function() {
            var fields = this.find('.controls').children();
            
            $.each(fields, function() {
                if($(this).parent().html().search('<input') != -1){
                    
                    
                        $(this).val(settings.data[$(this).attr('id')]);
                  
                    
                } else if($(this).parent().html().search('<textarea') != -1){ 
                    
                    $(this).text(settings.data[$(this).attr('id')]);

                } else if($(this).parent().html().search('<select') != -1){
  
                
                        $(this).find('option[value='+settings.data[$(this).attr('id')]['id']+']').attr('selected',true);

                }
            })
        },
        
        formatId: function() {
            var o = {};
            $.each(settings.data, function(i, v) {
                o[stripVowelAccent(i.toLowerCase())] = v;
            })
            return o;
        }
    };
    
    $.fn.Fill = function(options) {
        settings = $.extend({}, defaults, options);
        settings.data = methods.formatId();
        methods.init.apply(this);
    };
    
})(jQuery);


//************************************************//

//******** CREATE VIEW ****************************//

(function($){
    "use strict";
    
   $.fn.Form = function(options){
       
       
   };
    
    var defaults = {
        data: {}
    }, settings,
        
    methods = {
     
        init: function(){
            
        },
        
        control: function(label){

        }
        
    }
    
});

//************************************************//