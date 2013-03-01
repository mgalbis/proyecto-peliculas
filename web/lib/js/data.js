/* 
 * 
 * 
 */


/*
 * Devuelve un array con los registros de la página
 */
function getPage(tabla, limit, pagactual){
    return $.ajax({
        url : tabla+'/'+limit+'/'+pagactual+'/json',
        type : 'GET',
        async: false,
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    })
}

/*
 * Devuelve la página en la que se encuentra el id introducido
 */
function getPageId(table, limit, id){
    return $.ajax({
        url : 'json/clientes?op=getPageId&limit='+limit+'&id='+id,
        type : 'GET',
        dataType : 'json',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    })
}

/*
 * Devuelve el número de páginas
 */
function getPages(table, limit) {
    return $.ajax({
        url : table+'/pages/'+limit,
        type : 'GET',
        dataType : 'json',
        async: false,
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    }) 
}

/*
 * Devuelve todos los registros
 */
function getAll(table) {
    return $.ajax({
        url : table+'/all/json',
        type : 'GET',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    })
}

/*
 * Devuelve los datos del id
 */
function get(table, id) {
    if(id == 0) return false;
    return $.ajax({
        url : table+'/'+id+'/json',
        type : 'GET',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
   
    })
}

function getFormJ(table) {
    return $.ajax({
        url : table+'/form/json',
        type : 'GET',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
   
    })
}

function eliminar(tabla, id){
     $.ajax({
        url : tabla+'/'+id+'/delete',
        type : 'GET',
        success: function(d){
            location.reload();
        },
        error : function(jqXHR, status, error) {
        }
        
    })
}

function guardar(tabla, objeto){
    return $.ajax({
        url : tabla+'/'+objeto+'/save',
        type : 'POST',
        dataType : 'json', 
        success: function(){
           window.location = tabla;
        },
        error : function(jqXHR, status, error) {
        }
        
    })
}

function getModalList(table, type){
    
    return $.ajax({
        url : table+'/'+type+'/modalList',
        type : 'POST',
        dataType : 'text', 
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
        
    })
}

/**
 * Devuelve una página jsp
 */
var getForm = function(type){
    return $.ajax({
        url : 'jsp/clientes?op=getForm&type='+type,
        type : 'GET',
        dataType : 'text',
        async: false,
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
   
    })
}

