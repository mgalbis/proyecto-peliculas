<div class="row-fluid">
    <form method="post" id="loginForm" action="/appfuse-jsf/j_security_check"
          onsubmit="saveUsername(this);return validateForm(this)" class="form-signin" autocomplete="off">
        <h2 class="form-signin-heading">
            Control de acceso
        </h2>

        <input type="text" name="j_username" id="j_username" class="input-block-level"
               placeholder="Usuario" required tabindex="1">
        <input type="password" class="input-block-level" name="j_password" id="j_password" tabindex="2"
               placeholder="Contraseña" required>

        <button type="submit" class="btn btn-large btn-primary" name="login" tabindex="4">
            Entrar
        </button>
    </form>

    <p>
        Usuario: admin, Contraseña: admin
    </p>
</div>
