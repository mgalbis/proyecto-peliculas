

/**
 * Crea un botón en html
 * param id (String), clase o clases (String), contenido del botón (String)
 **/
function createBtn(id, clas, content){
    return '<button type="button" id="'+id+'" class="btn '+clas+'" title="'+clas+'">'+content+'</button>';
}

function createLink(clas, href, content){
    return '<a class="'+clas+'" href="'+href+'">'+content+'</a>'
}

/**
 * Crea una tabla html. Tomará como cabecera los nombres de los indices de los objetos
 * params id (String), clase o clases (String), body (array[{}])
 **/
function createTable(id, clas, content){
    var txt = '<table'+(id == ''?'':' id="'+id+'"')+' class="table '+clas+'"><thead>';
    txt += '<tr>';
    
    var thead = $.map(content[0], function(n, i) {
        return i;
    });
    
    $.each(thead, function(i, v){

        txt += '<th>'+v+'</th>';
 
    })
    txt += '</tr></thead><tbody>';
    
    $.each(content, function(i,v){
        txt += '<tr>';
        for(var c=0;c<thead.length;c++){
            if(!$.isArray(v[thead[c]])){
                txt += '<td>'+v[thead[c]]+'</td>';
            }
        }
        txt += '</tr>';
    })
    txt += '</tbody></table>';
    
    return txt;
}

function createForm(id, name, content){
    return '<form id="'+id+'" name="'+name+'">'+content+'</form>';
}

function createInput(type, id, name, value){
    return '<input type="'+type+'" id="'+id+'" name="'+name+'" value="'+value+'" />';
}

function createModal(id){
    return '<div id="'+id+'" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modLabel" aria-hidden="true">'
    +'<div class="modal-header">'
    +'<a class="close ok" data-dismiss="modal" aria-hidden="true">×</a>'
    +'<h3 id="modLabel">Ventana Modal</h3>'
    +'</div>'
    +'<div class="modal-body">'
    +'<p>One fine body…</p>'
    +'</div>'
    +'<div class="modal-footer">'
    +'<button class="btn ok" data-dismiss="modal" aria-hidden="true">Cerrar</button> '
    +'<div id="result"></div>'
    +'</div>'
    +'</div>';
}

function confirmDelete(table, id){
    $('#datos').append(createModal('mod'));
    
    $('#mod').modal({
        backdrop: 'static', 
        keyboard: false
    });
    
    var mensaje = '<p>¿Está seguro que quiere eliminar el elmento seleccionado?</p>';
    var input = createInput('hidden', '', 'id', id);
    var form = createForm('eliminar','eliminar', mensaje+input);
     
    $('#mod .modal-body').html(form);
    $('#mod').find('h3').html('Eliminar película');
    $('#mod').find('button.ok').after(createBtn('', 'btn-primary', 'Aceptar'));
        
    $('#mod').find('.ok').click(function(){
        $('#mod').modal('hide');
        setTimeout(function(){
            $('#mod .modal-footer').find('.btn-primary').remove();
            $('#mod').remove();
        }, 300);
    });
        
    $('#mod').find('.btn-primary').click(function(){
            
        eliminar(table, id);
            
        $('#myModal').modal('hide');
        setTimeout(function(){
            $('#mod .modal-footer').find('.btn-primary').remove();
            $('#mod').remove();
        }, 300);
    });
        
    $('#mod').modal('show');
}

$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if(this.name == 'id'){
            if(/^0/.test(this.value)) { //quita ceros a la izquierda
                while(/^0/.test(this.value)) this.value = (this.value).substr(1, (this.value).length);
            }
            if(isNaN(parseInt(this.value))) this.value = '0';
        }
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push($.trim(this.value) || null);
        } else {
            o[this.name] = $.trim(this.value) || null;
        }
    });
    return o;
}


/*
 * Muestra u oculta el spinner según el estado de carga de
 * los datos
 */
var spinner = function(){
    //si contador es 0, no está cargando nada
    if(done == 0){
        $('#spinner').fadeOut(100);
    } else {
        $('#spinner').fadeIn(100);
    }
}