/* 
 * 
 * 
 */


/*
 * Devuelve un array con los registros de la página
 */
var getPage = function(table, limit, pagactual){
    return $.ajax({
        url : table+'/?op=getPage&limit='+limit+'&page='+pagactual,
        type : 'GET',
        dataType : 'json',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    })
}

/*
 * Devuelve la página en la que se encuentra el id introducido
 */
var getPageId = function(table, limit, id){
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
var getPages = function(table, limit) {
    return $.ajax({
        url : 'json/clientes?op=getPages&limit='+limit,
        type : 'GET',
        dataType : 'json',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    }) 
}

/*
 * Devuelve todos los registros
 */
var getAll = function(table) {
    return $.ajax({
        url : table+'/list.json',
        type : 'GET',
        dataType : 'json',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
    })
}

/*
 * Devuelve los datos del id
 */
var get = function(table, id) {
    return $.ajax({
        url : table+'/single.json?id='+id,
        type : 'GET',
        dataType : 'json',
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
   
    })
}

function eliminar(tabla, id){
     $.ajax({
        url : tabla+'/delete.html?id='+id,
        type : 'GET',
        success: function(d){
            location.reload();
        },
        error : function(jqXHR, status, error) {
            alert('Error al procesar la solicitud: '+error);
        }
        
    })
}

function guardar(tabla, objeto){
    return $.ajax({
        url : tabla+'/save.html',
        data : 'form='+objeto,
        type : 'POST',
        dataType : 'json', 
        success: function(){
           window.location = 'peliculas/index.html';
        },
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