



<h1>Películas</h1>
<div id="datos"></div>
<div id="spinner"></div>

<!-- Modal -->
<div id="mod" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <a class="close ok" data-dismiss="modal" aria-hidden="true">×</a>
        <h3 id="myModalLabel">Ventana Modal</h3>
    </div>
    <div class="modal-body">
        <p>One fine body?</p>
    </div>
    <div class="modal-footer">
        <button class="btn ok" data-dismiss="modal" aria-hidden="true">Cerrar</button> 
        <div id="result"></div>
    </div>
</div>

<script>
    var done = 0; //estado de carga del spinner (contador)
    
    $(document).ready(function() {      
        setInterval(spinner, 300); //comprueba el estado de carga cada 500 milisegundos
        
        done++;
       $.when(getAll('peliculas')).done(function(d){
                

                $.each(d['list'], function(index, value){
                    var btn = '<div class="btn-group">'
                    //botones. params: id, class, content
                        + createBtn(value['#'], 'ver', '<i class="icon-eye-open"></i>')
                        + createBtn(value['#'], 'editar', '<i class="icon-pencil"></i>')
                        + createBtn(value['#'], 'eliminar', '<i class="icon-trash"></i>')
                        + '</div>';
                    
                    value['Acción'] = btn;
                    
                    delete value['Descripción'];
                    delete value['Duración'];
                    delete value['Calificación'];
                    delete value['Actores'];
                    delete value['Director'];
                });
                
                //params: id, class, contenido(array[{}]) 
                var table = createTable('peliculas', '', d['list']);
                $('#datos').append(table);
  
                $('.ver').click(function(){});
                $('.editar').click(showSearch);
                $('.eliminar').click(function(){
                    
                });
                done--;
           
        });
        
        $('form').submit(function(){
            var data = $(this).serializeObject();
            enviar(JSON.stringify(data));
        
        return false;
    });
    });
</script>

