
<h1>Géneros</h1>
<div id="datos"></div>

<script>
    $(document).ready(function() {      
        
       done++;
       $.when(getAll('generos')).done(function(d){
                

                $.each(d['list'], function(index, value){
                    var btn = '<div class="btn-group">'
                    //botones. params: id, class, content
                        + createBtn(value['#'], 'editar', '<i class="icon-pencil"></i>')
                        + createBtn(value['#'], 'eliminar', '<i class="icon-trash"></i>')
                        + '</div>';
                    
                    value['Acción'] = btn;
                    
                    delete value['peliculas'];
                    
                });
                
                //params: id, class, contenido(array[{}]) 
                var table = createTable('generos', '', d['list']);
                $('#datos').append(table);
  
                $('.editar').click(function(){
                    window.location = 'generos/form.html?id='+$(this).attr('id');
                });
                $('.eliminar').click(function(){
                    confirmDelete('generos', $(this).attr('id'));
                });
                
                done--;
           
        });
        
        
    });
</script>

