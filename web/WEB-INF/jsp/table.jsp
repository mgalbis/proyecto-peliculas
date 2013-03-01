
<h1><%=request.getAttribute("table")%></h1>
<div id="datos"></div>

<script>
    $(document).ready(function() {      
       var table = stripVowelAccent($('h1').text().toLowerCase());
       
       done++;
       $.when(getAll(table)).done(function(d){
                
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
        
        
    });
</script>

