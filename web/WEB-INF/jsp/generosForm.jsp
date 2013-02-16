<% Integer id = (Integer) request.getAttribute("id");%>
<form class="form-horizontal">
    <fieldset>
        <legend><%=(id == 0 ? "Crear" : "Editar")%> Género</legend>
        <div class="control-group">
            <label class="control-label" for="Nombre">Nombre</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Nombre">
            </div>
        </div>
         <div class="form-actions">
            <button type="submit" class="btn btn-primary">Guardar cambios</button>
            <a class="btn" href="peliculas/index.html">Cancelar</a>
        </div>
    </fieldset>
</form>
<script>
    $(document).ready(function() { 
        done++;
        $.when(get('generos', <%=id%>)).done(function(d){
            $('form').append(createInput('hidden', 'id', 'id', ''));
            
            $.each(d, function(index, value){ if(index == '#') index = 'id'; })
           
            $('#Nombre').val(d['Nombre']);
            $('#id').val(d['id']);
        

       
            done--;
        })
    })
</script>