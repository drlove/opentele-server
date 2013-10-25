grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.war.file = "target/${appName}.war"

// Plugin stuff
//grails.plugin.location.kihauditlog = "../kih-auditlog"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    def gebVersion = "0.9.0"
    def seleniumVersion = "2.21.0"


    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo name: "SilverbulletExt", root: "http://ci.silverbullet.dk/artifactory/ext-release-local"
        mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime 'mysql:mysql-connector-java:5.1.18'
        runtime 'net.sourceforge.jtds:jtds:1.2.6'
        runtime 'com.github.groovy-wslite:groovy-wslite:0.7.2'

        // Add seal libraries
        runtime('dk.sosi.seal:seal:2.1.3') {
            //exclude "xml-apis"
            //exclude "xalan"
            //exclude "jaxb-impl"
            //exclude "xerces"
            //exclude "xercesImpl"
            exclude "axis-jaxrpc"
        }

        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude "xml-apis"
        }
        test("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
        test("org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion")
        test("org.seleniumhq.selenium:selenium-support:$seleniumVersion")

// You usually only need one of these, but this project uses both
        test "org.gebish:geb-spock:$gebVersion"
        test "org.gebish:geb-junit4:$gebVersion"

        // Included due to ecludes for the :rest:0.7 plugin which includes version 4.0.x
        runtime("org.apache.httpcomponents:httpclient:4.1.2",
                 "org.apache.httpcomponents:httpcore:4.1.3")
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.5"
        runtime ":kih-auditlog:0.36" // Our very own audit log plugin
        compile ":webflow:2.0.0"
        runtime ":database-migration:1.3.1"
        compile ":quartz:1.0-RC2"


        build ":tomcat:$grailsVersion"

        runtime ":build-test-data:2.0.5"

        runtime ":codenarc:0.17"
        runtime ":famfamfam:1.0.1"
        runtime ":jquery:1.8.0"
        runtime ":jquery-ui:1.8.15"
        runtime ":mail:1.0.1"
        compile ":greenmail:1.3.4"

        runtime(":rest:0.7") {
            excludes("httpclient","httpcore")
        }
        runtime ":spring-security-core:1.2.7.3"
        runtime ":tooltip:0.7"
        compile ":artefact-messaging:0.3"


        test ":geb:$gebVersion"
        test ":spock:0.7"
        test ":code-coverage:1.2.5"


    }
}

codenarc.reports = {

// Each report definition is of the form: // REPORT-NAME(REPORT-TYPE) { // PROPERTY-NAME = PROPERTY-VALUE // PROPERTY-NAME = PROPERTY-VALUE // }

    MyXmlReport('xml') { // The report name "MyXmlReport" is user-defined; Report type is 'xml'
    outputFile = 'target/test-reports/CodeNarc-Report.xml' // Set the 'outputFile' property of the (XML) Report
    }

    MyXmlReport('html') { // The report name "MyXmlReport" is user-defined; Report type is 'xml'
        outputFile = 'target/test-reports/CodeNarc-Report.html' // Set the 'outputFile' property of the (XML) Report
    }

}

codenarc.properties = {
    // Each property definition is of the form:  RULE.PROPERTY-NAME = PROPERTY-VALUE
    GrailsPublicControllerMethod.enabled = false
    EmptyIfStatement.priority = 1
    coberturaXmlFile = "target/test-reports/cobertura/coverage.xml"

}

codenarc.ruleSetFiles = ["rulesets/basic.xml,rulesets/exceptions.xml, rulesets/imports.xml,rulesets/grails.xml, rulesets/unused.xml, rulesets/size.xml"]

def vidyoPluginDirectory = '../opentele-server-vidyo-plugin'
if (new File(vidyoPluginDirectory).exists()) {
    grails.plugin.location.'VidyoGrailsPlugin' = vidyoPluginDirectory
}
