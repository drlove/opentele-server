
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
      <title></title>
        <meta name="layout" content="measurements_patient_mobil">

    </head>
    <body>
        <g:each in="${tables}" var="tableData">
            <g:link mapping="patientMeasurementsTypeMobile" params="[type:tableData.type]">
                <h1>
                    ${message(code: "patientMeasurements.label." + tableData.type) }
                </h1>
            </g:link>
            <hr/>
        </g:each>
    </body>
</html>