<%@ page import="org.opentele.server.model.types.PermissionName; org.opentele.server.model.Patient"%>
<%@ page import="org.opentele.server.model.types.MeasurementTypeName"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title><g:message code="patient.conference.title"/></title>
</head>

<body>
	<div id="show-patient" class="content scaffold-show" role="main">
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

		<h1 class="fieldcontain"><g:message code="patient.conference" /></h1>

        <ol class="property-list patient">
            <li class="fieldcontain">
                <span class="property-label">
                    <g:message code="patient.conference.heldByClinician"/>:
                </span>
                <span class="property-value" aria-labelledby="user-label">
                    ${conference.clinician.name}
                </span>
            </li>
            <li class="fieldcontain">
                <span class="property-label">
                    <g:message code="patient.conference.heldAt" />:
                </span>
                <span class="property-value" aria-labelledby="user-label">
                    <g:formatDate formatName="default.date.format" date="${conference.createdDate}" />
                </span>
            </li>
            <li class="fieldcontain">
                <span class="property-label">
                    <g:message code="patient.measurements.label" />:
                </span>
            </li>
        </ol>
        <conference:measurementsTable conference="${conference}"/>
	</div>
</body>
</html>
