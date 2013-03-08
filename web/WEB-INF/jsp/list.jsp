
<!-- div id="filter" class="form-horizontal">
    <fieldset>
        <input type="text" placeholder="Buscar..." class="input-large" id="texto">
        <p id="error" class="error-text"></p>
    </fieldset>
</div -->

<div id="list"></div>

<div class="pie pagination"><ul></ul></div>

<script>
    $(document).ready(function() {      

        var table = '<%=request.getAttribute("table")%>';
        var type = '<%=request.getAttribute("type")%>';
        
        
        page(table, 10, 1, null, type);
    });
    
    function page(table, limit, pagactual, search, type){
        $.when(getPage(table.toLowerCase(), limit, pagactual, search)).done(function(d){
                
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
        
        $.when(getPages(table, limit, search)).done(function(data){

            var pag = $('.pagination').find('ul');
            pag.empty();
            var active;
            var i;
            var paginas = parseInt(data['pages']);
            
            if(paginas > 1){
                if(paginas > 5){
                    if(pagactual < 4){
                        for(i=1;i<=4;i++){
                            active = i==pagactual ? ' class="active"':'';
                            pag.append('<li'+active+'><a>'+i+'</a></li>');
                        }
                        pag.append('<li class="disabled"><a>...</a></li>');
                        pag.append('<li><a>'+paginas+'</a></li>');
                    }
                    if(pagactual > 3 && pagactual < (paginas - 2)){
                        pag.append('<li><a>1</a></li>');
                        pag.append('<li class="disabled"><a>...</a></li>');
                    
                        for(i=pagactual - 1;i<=pagactual + 1;i++){
                            active = i==pagactual ? ' class="active"':'';
                            pag.append('<li'+active+'><a>'+i+'</a></li>');
                        }
                        pag.append('<li class="disabled"><a>...</a></li>');
                        pag.append('<li><a>'+paginas+'</a></li>');
                    }
                    if(pagactual > (paginas - 3)){
                        pag.append('<li><a>1</a></li>');
                        pag.append('<li class="disabled"><a>...</a></li>');
                    
                        for(i=(paginas - 3);i<=paginas;i++){
                            active = i==pagactual ? ' class="active"':'';
                            pag.append('<li'+active+'><a>'+i+'</a></li>');
                        }
                    }
                } else {
                    for(i=1;i<=paginas;i++){
                        active = i==pagactual ? ' class="active"':'';
                        pag.append('<li'+active+'><a>'+i+'</a></li>');
                    }
                }
            
                //tras cargar la paginación le agregamos evento a 'a'
                $('.pagination li:not(.active) a, .pagination li:not(.disabled) a').click(function(){ 
                    page(table, limit, parseInt($(this).html()), search, type) 
                });
            }
            
        });
    }
</script>

