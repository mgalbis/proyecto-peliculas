
<h1><%=request.getAttribute("table")%></h1>
<div id="datos"></div>
<div class="pie table pagination">
    <ul></ul>
</div>
<script>
    $(document).ready(function() {      
        var table = stripVowelAccent($('h1').text().toLowerCase());
       
        pagina(table, 10, 1);
    });
    
    function pagina(table, limit, pagactual){
        done++;
        $.when(getPage(table, limit, pagactual)).done(function(d){
                
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
        $.when(getPages(table, limit)).done(function(data){

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
                $('.pagination li:not(.active, .disabled) a').click(function(){ 
                    pagina(limit, parseInt($(this).html())) 
                });
            }
            done--;
        });
    
    }
</script>

