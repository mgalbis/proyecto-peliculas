
<div id="form"></div>
<script type="text/javascript">
    $(document).ready(function() { 
        done++;
        var dat = <%=(String) request.getAttribute("form")%>;
        var id =  <%=request.getAttribute("id")%>;
        
        $('#form').Form({
                data: dat,
                legend: id == null? "Crear":"Editar",
                class: 'form-horizontal'
            });
            
        //$.when(get('peliculas', <%=request.getAttribute("id")%>)).done(function(d){
            
            done--;
        //})
        
    })
</script>