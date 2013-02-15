

/**
 * Crea un botón en html
 * param id (String), clase o clases (String), contenido del botón (String)
 **/
function createBtn(id, clas, content){
    return '<button type="button" id="'+id+'" class="btn '+clas+'" title="'+clas+'">'+content+'</button>';
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

function createForm(name, content){
    return '<form name="'+name+'">'+content+'</form>';
}

function createInput(type, name, value){
    return '<input type="'+type+'" name="'+name+'" value="'+value+'" />';
}

function confirm(){
     $('#mod .modal-body').html(d);
        $('#myModal .modal-body').find('h1').remove();
        $('#myModal').find('button.ok').after($('#myModal').find('.btn-primary'));
        
        $('#myModal').find('.ok').click(function(){
            $('#myModal').modal('hide');
            setTimeout(function(){
                $('#myModal .modal-footer').find('.btn-primary').remove();
            }, 300);
        });
        
        $('#myModal').find('.btn-primary').click(function(){
            alert('Cliente actualizado correctamente.');
            $('#myModal').modal('hide');
            setTimeout(function(){
                $('#myModal .modal-footer').find('.btn-primary').remove();
            }, 300);
        });
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
        $('#spinner').hide();
    } else {
        $('#spinner').show(0);
    }
}