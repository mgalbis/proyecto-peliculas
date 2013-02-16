

<% Integer id = (Integer) request.getAttribute("id");%>
<form class="form-horizontal">
    <fieldset>
        <legend><%=(id == 0 ? "Crear" : "Editar")%> Película</legend>
        <div class="control-group">
            <label class="control-label" for="Título">Título</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Título">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="V.O.">V.O.</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="VO">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Fecha">Fecha</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Fecha">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Descripción">Descripción</label>
            <div class="controls">
                <textarea class="input-xlarge" id="Descripción" rows="3"></textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Duración">Duración (min.)</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Duración">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Calificación">Calificación</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Calificación">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Género">Género</label>
            <div class="controls">
                <select id="Género">
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Director">Director</label>
            <div class="controls">
                <select id="Director">
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="Actores">Actores</label>
            <div class="controls">
                <select multiple="multiple" id="Actores">
                </select>
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
        $.when(get('peliculas', <%=id%>)).done(function(d){
            $('form').append(createInput('hidden', 'id', 'id', ''));
            
            $.each(d, function(index, value){ if(index == '#') index = 'id'; })
            
            $('#Descripción').text(d['Descripción']);
            $('#Título').val(d['Título']);
            $('#VO').val(d['VO']);
            $('#Fecha').val(d['Fecha']);
            $('#Duración').val(d['Duración']);
            $('#Calificación').val(d['Calificación']);
            $('#id').val(d['id']);
        
           
           
           //carga combo Genero
           $.when(getAll('generos')).done(function(a){
               cargaCombo('Género', a['list']);
           })
           
           //carga combo Director
           $.when(getAll('directores')).done(function(a){
               cargaCombo('Director', a['list']);
           })
           
           //carga lista actores
          $.when(getAll('actores')).done(function(a){
               cargaCombo('Actores', a['list']);
           })

       
            done--;
        })
    })
</script>