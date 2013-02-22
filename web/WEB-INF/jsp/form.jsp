
<div id="form"></div>
<div class="modal fade" id="datos" style="min-height: 200px">
    <div class="modal-header" style="height: 20px">
        <a class="close ok" data-dismiss="modal">×</a>
        <span id="info"></span>
    </div>
    <div class="modal-body">

    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() { 
        done++;
        var table = '<%=request.getAttribute("table")%>';
        var id =  <%=request.getAttribute("id")%>;
        
        $.when(getFormJ(stripVowelAccent(table.toLowerCase()))).done(function(d) {
        
            var leg = table.substring(0, table.length-1);
            if(leg.charAt(leg.length-1) == 'e') leg = leg.substring(0, leg.length-1);
       
            $('#form').Form({
                data: d,
                legend: (id == null? "Crear ":"Editar ")+leg.toLowerCase(),
                class: 'form-horizontal'
            });
            
            
            $('button').click(function(){
                var t = $(this).parent().find('ul').attr('id');
                if(t == 'director') t = 'directores';
                
                $.when(getModalList(t.toLowerCase(), $(this).attr('class'))).done(function(d){
                    //alert(d);
                    $('.modal-body').append(d);
                    $('#datos').modal('show');
                    
                });
            });
            
            $('form').submit(function(e){
                e.preventDefault();
                var data = $(this).Serialize();
                //alert(JSON.stringify(data));
                guardar(stripVowelAccent(table.toLowerCase()), JSON.stringify(data));
                
            });
            
            done--;
        });

        if(id != null){
            $('form').css('opacity', '0');
            done++;
            setTimeout(function(){
                $.when(get(stripVowelAccent(table.toLowerCase()), id)).done(function(d){
            
                    $('form').Fill({
                        data: d
                    })
            
                 $('ul.input-xlarge li').click(function(){
                        $(this).remove()
                    })
                    $('form').css('opacity', '1');
                    done--;
                })
            }, 700)
        
        }
    })
</script>