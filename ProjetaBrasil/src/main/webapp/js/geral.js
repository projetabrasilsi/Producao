function consultaCep(cep) {
         var cep_code = $('#cep').val();
         if (cep_code.length <= 0)
               return;
         $.get("http://apps.widenet.com.br/busca-cep/api/cep.json", {
               code : cep_code
         }, function(result) {
               if (result.status != 1) {
                      alert(result.message || "Houve um erro desconhecido");
                      return;
               }
               var cep = result.code;
               var estado = result.state;
               var cidade = result.city;
               var bairro = result.district;
               var endereco = result.address;
               setarValoresCEP([ {
                      name : 'cep',
                      value : cep
               }, {
                      name : 'estado',
                      value : estado
               }, {
                      name : 'cidade',
                      value : cidade
               }, {
                      name : 'bairro',
                      value : bairro
               }, {
                      name : 'endereco',
                      value : endereco
               } ]);
         });
  }

function setfocus(id){
	document.getElementById(id).focus()
	}

function log(msg) {
    setTimeout(function() {
        throw new Error(msg);
    }, 0);
}

$(document).ready(function() {
    $(":input:visible:enabled:first").focus()
});

function centerAndShowDialog(dialog)
{
    $(dialog).css("top",Math.max(0,(($(window).height() - $(dialog).outerHeight()) / 2) + $(window).scrollTop()) + "px");
    $(dialog).css("left",Math.max(0, (($(window).width() - $(dialog).outerWidth()) / 2) + $(window).scrollLeft()) + "px");
    
}

function disponiveis(date)
{
  var day = date.getDay(); 
  var disponiveis = document.getElementById('formTemplate:formCalendario:disponiveis').value;
  return [ disponiveis.indexOf(day) != -1 ]
}
PrimeFaces.locales['pt_BR'] = {
	    closeText : 'Fechar',
	    prevText : 'Anterior',
	    nextText : 'Próximo',
	    currentText : 'Começo',
	    monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho','Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	    monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago','Set', 'Out', 'Nov', 'Dez' ],
	    dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta','Sábado' ],
	    dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
	    dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	    weekHeader : 'Semana',
	    firstDay : 1,
	    isRTL : false,
	    showMonthAfterYear : false,
	    yearSuffix : '',
	    timeOnlyTitle : 'Só Horas',
	    timeText : 'Tempo',
	    hourText : 'Hora',
	    minuteText : 'Minuto',
	    secondText : 'Segundo',
	    ampm : false,
	    month : 'Mês',
	    week : 'Semana',
	    day : 'Dia',
	    allDayText : 'Todo Dia'
	};





