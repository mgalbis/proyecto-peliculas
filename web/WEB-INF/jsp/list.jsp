
<!-- div id="filter" class="form-horizontal">
    <fieldset>
        <input type="text" placeholder="Buscar..." class="input-large" id="texto">
        <p id="error" class="error-text"></p>
    </fieldset>
</div -->

<div id="list"></div>

<div id="pag"></div>

<script>
    $(document).ready(function() {      

        var table = '<%=request.getAttribute("table")%>';
        var type = '<%=request.getAttribute("type")%>';
        
        $.when(getAll(table.toLowerCase())).done(function(d){
                
            $('#list').ModalList({
                data: d['list']
            });
            
            
            $('i').click(function(){
                
                if(table == 'directores'){
                    table = table.substring(0, table.length-1);
                    if(table.charAt(table.length-1) == 'e') table = table.substring(0, table.length-1);
                }
                
                var click = $(this).parent();
                var isIn;
                
                $('#'+table).find('li').each(function(){
                    
                    if(click.val() == $(this).val()) isIn = true;
                })
                
                if(!isIn){
                    var cont = $(this).parent().clone();
                    cont.find('i').remove();
                
                    switch(type){
                        case 'simple':
                            $('#'+table).html(cont);
                
                            $('#datos').modal('hide');
                            $('.modal-body').empty();
                            break;
                        case 'multiple':
                            $('#'+table).append(cont);
                            break;
                    }
                    
                    $('#info').html('Elemento añadido').fadeOut(0)
                        .fadeIn().delay(1000).fadeOut();
                } else {
                    $('#info').html('Elemento ya seleccionado').fadeOut(0)
                        .fadeIn().delay(1000).fadeOut();
                }
                
                $('ul.input-xlarge li').click(function(){
                        $(this).remove()
                    })
            });
        });
        
   
        
    });
</script>

