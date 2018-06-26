/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}

function openTab2(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent2");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks2");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}
function comprobarOption() {
    var opcion = document.frm.seleccion.options.value;
    if (opcion == 1) {
        document.frm.dni.disabled = true;
        document.frm.user.disabled = true;
        document.frm.pass.disabled = true;
        document.frm.nombre.disabled = true;
        document.frm.apellidos.disabled = true;
        document.frm.domicilio.disabled = true;
        document.frm.telefono.disabled = true;
        document.frm.email.disabled = true;
    } else {
        document.frm.dni.disabled = false;
        document.frm.user.disabled = false;
        document.frm.pass.disabled = false;
        document.frm.nombre.disabled = false;
        document.frm.apellidos.disabled = false;
        document.frm.domicilio.disabled = false;
        document.frm.telefono.disabled = false;
        document.frm.email.disabled = false;

    }
}
function comprobarOption1() {
    var opcion = document.frm1.seleccion.options.value;
    if (opcion == 1) {
        document.frm1.concepto.disabled = true;
        document.frm1.entidad.disabled = true;
        document.frm1.cantidad.disabled = true;
        document.frm1.descripcion.disabled = true;
    } else {
        document.frm1.concepto.disabled = false;
        document.frm1.entidad.disabled = false;
        document.frm1.cantidad.disabled = false;
        document.frm1.descripcion.disabled = false;

    }
}

function changetextbox()
{
    if (document.getElementById("seleccion").value === "1") {
        document.getElementById("concepto").disabled = 'true';
        document.getElementById("entidad").disabled = 'true';
        document.getElementById("cantidad").disabled = 'true';
        document.getElementById("descripcion").disabled = 'true';
    } else {
        document.getElementById("concepto").disabled = "";
        document.getElementById("entidad").disabled = "";
        document.getElementById("cantidad").disabled = "";
        document.getElementById("descripcion").disabled = "";
    }
}

function changetextboxUser()
{
    if (document.getElementById("seleccionUser").value === "1") {
        document.getElementById("dni").disabled = 'true';
        document.getElementById("usuario").disabled = 'true';
        document.getElementById("contraseña").disabled = 'true';
        document.getElementById("nombre").disabled = 'true';
        document.getElementById("apellidos").disabled = 'true';
        document.getElementById("domicilio").disabled = 'true';
        document.getElementById("telefono").disabled = 'true';
        document.getElementById("email").disabled = 'true';
    } else {
        document.getElementById("dni").disabled = "";
        document.getElementById("usuario").disabled = "";
        document.getElementById("contraseña").disabled = "";
        document.getElementById("nombre").disabled = "";
        document.getElementById("apellidos").disabled = "";
        document.getElementById("domicilio").disabled = "";
        document.getElementById("telefono").disabled = "";
        document.getElementById("email").disabled = "";
    }
}