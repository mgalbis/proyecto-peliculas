
<div id="form"></div>
<div class="modal fade" id="datos">
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
                legend: (id == null? "Crear ":"Editar ")+leg,
                class: 'form-horizontal'
            });
        })
        //$.when(get('peliculas', <%=request.getAttribute("id")%>)).done(function(d){
            
        done--;
        //})
        
    })
</script>