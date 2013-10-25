<%@ page import="org.opentele.server.model.questionnaire.QuestionnaireGroup2QuestionnaireHeader" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'questionnaireGroup2QuestionnaireHeader.label', default: 'QuestionnaireGroup2QuestionnaireHeader')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-questionnaireGroup2QuestionnaireHeader" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${questionnaireGroup2QuestionnaireHeaderInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${questionnaireGroup2QuestionnaireHeaderInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${id}" />
				<g:hiddenField name="version" value="${version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
                    <g:link class="goback" controller="questionnaireGroup" action="show" id="${questionnaireGroupId}">
                        <g:message code="default.button.goback.label2" />
                    </g:link>
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>