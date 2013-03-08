
<h1><%=request.getAttribute("table")%></h1>

<div class="form-inline" id="buscador">
    <input type="text" class="input-medium" value="" /><button class="btn">Buscar</button>
</div>

<div id="datos"></div>
<div class="pie table pagination">
    <ul></ul>
</div>
<script>  
    $(document).ready(function() {      
        var table = stripVowelAccent($('h1').text().toLowerCase());
        var search = null;
        pagina(table, 10, 1, search);
        
        //boton filtro
        $('#buscador .btn').click(function(){
            search = $('#buscador input[type="text"]').val() == '' ? null : $('#buscador input[type="text"]').val();
            pagina(table, 10, 1, search);
        })
    });
    
    function pagina(table, limit, pagactual, search){
        done++;
        $.when(getPage(table, limit, pagactual, search)).done(function(d){
                
            $('#datos').Table({
                data: d['list'],
                objects: 'nombre',
                crud: true
            });
  
            $('.ver').click(function(){
                window.location = table+'/'+$(this).attr('id')+'/view';
            });
            $('.editar').click(function(){
                window.location = table+'/'+$(this).attr('id')+'/form';
            });
            $('.borrar').click(function(){
                confirmDelete(table, $(this).attr('id'));
            });
                
            done--;
           
        });
        
        done++;
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
                    pagina(table, limit, parseInt($(this).html()), search) 
                });
            }
            done--;
        });
    
    }
</script>

