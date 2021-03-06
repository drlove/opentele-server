<%@ page import="org.opentele.server.model.types.PermissionName; org.opentele.server.model.questionnaire.Questionnaire"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName" value="${message(code: 'questionnaire.label', default: 'Questionnaire')}" />
<title><g:message code="default.edit" args="[entityName]" /></title>
</head>
<body>

	<div id="edit-questionnaire" class="content scaffold-edit" role="main">
		<h1>
			<g:message code="default.edit" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<g:hasErrors bean="${questionnaireInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${questionnaireInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if> >
                        <g:message error="${error}" />
                    </li>
				</g:eachError>
			</ul>
		</g:hasErrors>
        
		<g:form method="post">
			<g:hiddenField name="id" value="${questionnaireInstance?.id}" />
			<g:hiddenField name="version" value="${questionnaireInstance?.version}" />
			<fieldset class="form">
				<g:render template="form" />
			</fieldset>
			<fieldset class="buttons">
				<g:actionSubmit class="save" action="update" value="${message(code: 'default.update')}" />
                <sec:ifAnyGranted roles="${PermissionName.QUESTIONNAIRE_DELETE}">
                    <g:actionSubmit class="delete" action="delete"
                        value="${message(code: 'default.button.delete.label')}"
                        formnovalidate=""
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message')}');" />
                </sec:ifAnyGranted>
			</fieldset>
		</g:form>
	</div>
</body>
</html>
