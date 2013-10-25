<%@ page import="org.opentele.server.model.types.PermissionName; org.opentele.server.model.Patient"%>
<%@ page import="org.opentele.server.model.types.MeasurementTypeName"%>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />
    <g:set var="title" value="${message(code: 'patient.questionnaires.title', args: [patientInstance.name.encodeAsHTML()])}"/>
    <title>${title}</title>
    <g:javascript src="knockout-2.2.0.js" />
    <g:javascript src="OpenTeleTablePreferences.js" />
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'measurement_results_tables.css')}" type="text/css">
    <script type="text/javascript">
        $(document).ready(function() {
            if(document.getElementById("rightTable")) {
                document.getElementById("rightTable").focus();
            }

            new QuestionnaireTableViewModel(${patientInstance.id},${questionPreferences}).init();

            $('.acknowledge').click(function(event) {
                event.preventDefault();
                var message = '${g.message(code: 'default.confirm.msg', args: [message(code: 'confirm.context.msg.questionnaire')]).encodeAsJavaScript()}';
                if (confirm(message)) {
                    var automessage = $(this).attr('data-automessage');
                    var questionnaireId = $(this).attr('data-questionnaire-id');

                    window.location = '${g.createLink(controller: 'questionnaire', action: 'acknowledge', absolute:true)}/?id=' + questionnaireId + '&withAutoMessage=' + automessage;
                }
            });
        });
    </script>
</head>

<body>
	<div id="show-patient" class="content scaffold-show" role="main">
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
        <h1 class="fieldcontain">${title}</h1>
        <g:if test="${greenCompletedAndUnacknowledgedQuestionnaires}">
            <sec:ifAnyGranted roles="${PermissionName.QUESTIONNAIRE_ACKNOWLEDGE}">
                <div class="acknowledgeAll">
                    <span>
                        <g:message code="patientOverview.questionnaire.acknowledge.green.label"/>
                    </span><cq:renderAcknowledgeAllGreenButtons
                            completedQuestionnaires="${greenCompletedAndUnacknowledgedQuestionnaires}"
                            patient="${patientInstance}"/>
                </div>
            </sec:ifAnyGranted>
        </g:if>
        <g:if test="${questionnairesNumber > 0 && completedNumber > 0}">
            <sec:ifAnyGranted roles="${PermissionName.PATIENT_PREFERENCES_WRITE}">
                <g:form url="[action:'savePrefs',controller:'patient']" onsubmit="return createFormFields();">
                    <fieldset id="hiddenparams"></fieldset>
                    <fieldset id="patientparams">
                        <g:hiddenField name="patientID" value="${patientInstance?.id}" />
                    </fieldset>

                    <div display="hidden" id="patientPrefs" value=${questionPreferences}></div>
                    <cq:renderResultTableForPatient patientID="${patientInstance.id}" withPrefs="${true}" unacknowledgedOnly="${false}" />
                    <fieldset class="buttons">
                        <g:submitButton name="savePrefs" class="save" value="Gem foretrukne felter" />
                    </fieldset>
                </g:form>
            </sec:ifAnyGranted>
		</g:if>
		<g:else>
			<g:if test="${questionnairesNumber > 0 && completedNumber == 0}">
				<g:message code="patient.questionnaire.nocompletequestionnaires" />
			</g:if>
			<g:else>
				<g:message code="patient.questionnaire.noquestionnaires" />
			</g:else>
		</g:else>
	</div>

	<!-- Templates for Knockout.js -->
	<script id="prefRowTemplate" type="text/html">
		<tr id="prefQuestion" class="prefQuestion" data-bind="attr: {'selectedQuestionID': $root.getQuestionID($data)}">
			<td>
                <div>
                    <select data-bind="options: $root.questions, optionsText: 'text', value: $data.questionObj, optionsCaption: 'Vælg..'" onmouseover="tooltip.show('Vælg foretrukken værdi, denne kopieres til toppen af skemaet.');" onmouseout='tooltip.hide();'></select>
                    <!-- ko if: $root.notLastRow($data) -->
                        <button id="removeBtn" class="remove" data-bind="click: function(){ $data.remove(); }" onmouseover="tooltip.show('Fjern denne række');" onmouseout='tooltip.hide();'><img src="../../images/cancel.png"/></button>
                    <!-- /ko -->
                </div>
            </td>
		</tr>
	</script>
	<script id="prefRowResTemplate" type="text/html">
		<!-- ko if: $data.resultObj() -->
			<tr class="prefResult" data-bind="html: $data.resultObj().text"></tr>
		<!-- /ko -->
	</script>
	<!-- JS to copy selected preferences to Grails' hiddenfields in the (submit) form -->
	<script>
	//Could be done with afterAdd on the table binding, but 
	//no need to do it until we actually want to submit
	function createFormFields() {
		var hiddenparamsElem = $("#hiddenparams");
		$("#leftTable tbody tr.prefQuestion").each(function(idx, elem) {
			var id = elem.getAttribute('selectedQuestionID');
			
			if((! isNaN (id-0) && id != null)) {
				hiddenparamsElem.append($('<input type=\"hidden\" id=\"preferredQuestionId\" name=\"preferredQuestionId\" value=\"'+id+'\"/>'));	
			} 
		});

		return true;
	}
	</script>
</body>
</html>