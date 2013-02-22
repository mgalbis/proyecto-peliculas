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
                        html: true
                    })
                    + $().Button({ 
                        id: v[thead[0]], 
                        class: 'editar btn', 
                        content: '<i class="icon-pencil"></i>', 
                        html: true
                    })
                    + $().Button({ 
                        id: v[thead[0]], 
                        class: 'borrar btn', 
                        content: '<i class="icon-trash"></i>', 
                        html: true
                    })
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

//******* CREATE MODAL-LIST **********************//

(function ($) {
    "use strict";
    
    var defaults = {
        data : {}
    }, settings,
        
    methods = {
        
        init: function () {
            var object = methods.filter(settings.data),  //aplicamos filtro
            btn,     //botones crud
            txt = '<ul class="modal-list">';  //creamos tabla y cabecera
         
            //mapeamos el objeto json y creamos un array sólo con los índices
            var thead = $.map (object[0], function(n, i) {  
                return i;
            });

            
            $.each(object, function (i,v) {  //por cada fila
 
                txt += '<li value="'+v[thead[0]]+'">'
                +v[thead[1]]+'<i class="icon-plus"></i></li>';  //creamos celda

            })
            txt += '</ul>'; // cerramos tabla
    
            return txt;
           
        },
        
        filter: function (object) {

            $.each(object, function (i,v) {
                $.each(v, function (i2, v2) {

                    if($.isArray(v2)) delete v[i2];
                });
            })
           
            
            $.each(object, function (i,v) {
                $.each(v, function (i2, v2) {

                    if($.isPlainObject(v2)) {
                        
                        delete v[i2];
     
                        
                    }
                })
            })

            return object;
        }
        
        
    };
    
    $.fn.ModalList = function (options) {
        settings = $.extend ({}, defaults, options);

        var table = methods.init();
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
        
        number: function () {
            var txt = '<input type="number"';
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
            var txt = '<div class="input-append date" data-date-format="dd-mm-yyyy">'
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
    }, settings,
        
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
            
            txt += '<div class="control-group"><div class="controls">'
            +'<button type="submit" id="submitFormCliente" class="btn btn-primary">Guardar</button>'
            +'</div></div>';
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
            $.each(content, function(i,v){
                index = i
            });
            //alert(index);
            if($.isPlainObject(content)){
                if(index == 'simple' | index == 'multiple'){
                    txt = $().Field({
                        type: index, 
                        id: stripVowelAccent(label.toLowerCase()), 
                        content: content[index], 
                        html: true,
                        class: 'input-xlarge'
                    });
                } else {
                    txt = $().Field({
                        type: 'select', 
                        id: stripVowelAccent(label.toLowerCase()), 
                        content: content[index], 
                        html: true,
                        class: 'input-xlarge'
                    });
                }
                
            } else if($.isArray(content)){
                
                txt = $().Field({
                    type: 'select', 
                    id: stripVowelAccent(label.toLowerCase()), 
                    class: 'input-xlarge',
                    content: content,
                    html: true
                });
                
            //specialData = content;
            } else {
                
                txt = $().Field({
                    type: content, 
                    id: stripVowelAccent(label.toLowerCase()),  
                    html: true,
                    class: 'input-xlarge'
                });
                
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

//******** VIEW *****************************//
/*
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
*/

//************************************************//

//******** CREATE FILL-FORM ****************************//

(function($){
    "use strict";
    
    $.fn.Fill = function(options){
        settings = $.extend({}, defaults, options);
        form = this;
        methods.init();
    };
    
    var defaults = {
        data: {}
    }, settings, form,
        
    methods = {
        init: function(){
            var aux = {}
            $.each(settings.data, function(i,v){
                aux[stripVowelAccent(i.toLowerCase())] = v;
            })
            settings.data = aux;
            
            
            form.find('.controls input, .controls textarea').each(function() {
                $(this).val(settings.data[$(this).attr('id')])
            })
                
            $.each(settings.data, function(i,v){
                
            
                form.find('.controls select').each(function() {
                     
                    if(i == $(this).attr('id')){
                    $(this).find('option').each(function(){
                            if($(this).val() == v['id']) $(this).attr('selected', 'selected')
                        });
                    }
                })
            
                form.find('.controls ul').each(function() {
                    
                    if(i == $(this).attr('id')){
                      
                      var ul = $(this);
                        if($.isArray(v)){
                            $.each(v, function(ind, val){
                                
                                var li = '<li value="'+val['id']+'">'+(val['nombre']==undefined?val['titulo']:val['nombre'])+'</li>';
                            ul.append(li);
                            })
                        } else {
                            var li = '<li value="'+v['id']+'">'+v['nombre']+'</li>';
                            ul.append(li);
                        }
                    }
                })
            });
        }

        
    }
    
})(jQuery);

//************************************************//

(function($){
    "use strict";
    
    $.fn.Serialize = function(){
        //settings = $.extend({}, defaults, options);
        //alert('hola');
        methods.init.apply(this);
        return fields;
    };
    
    var fields = {},
        
    methods = {
     
        init: function(){
            
            this.find('.controls input, .controls textarea').each(function() {
                fields[$(this).attr('id')] = $(this).val();
            })
            
            this.find('.controls select').each(function() {
                var aux = {};
                aux['id'] = $(this).find('option:selected').val();
                aux['nombre'] = $(this).find('option:selected').text();
                fields[$(this).attr('id')] = aux;
            })
            
            this.find('.controls ul').each(function() {
                var aux;
                if($(this).find('li').length == 1){
                    aux = {}
                    aux['id'] = $(this).find('li').val();
                    aux['nombre'] = $(this).find('li').html();
                   
                } else {
                    aux = new Array();
                    $(this).find('li').each(function(){
                        var aux2 = {};
                        aux2['id'] = $(this).val();
                        if(aux2['nombre'] == undefined){
                            aux2['titulo'] = $(this).html();
                        } else {
                            aux2['nombre'] = $(this).html();
                        }
                        aux.push(aux2);
                    })
                }
                
                fields[$(this).attr('id')] = aux;
            })
            
        }
        
    };
    
})(jQuery);
