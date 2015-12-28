## Steps Followed


* The dependency on ui-framework does not get added to pom.xml file in omod. Bug in create-module. Added manually. Refer page [[https://wiki.openmrs.org/display/docs/Using+the+UI+Framework+in+Your+Module]] 
* Added fragments folder, fragment.controller, page.controller, resources.images, resources.styles


## Notes
* ui.includeJavascript("yourmoduleid", "jquery.js")
* ui.includeCss("yourmoduleid", "main.css")
* ${ ui.resourceLinks() }
* <link rel="icon" type="image/png\" href="/${ ui.contextPath() }/images/openmrs-favicon.png"/>
* <img alt="" id="patientimg" src="${pageContext.request.contextPath}/moduleResources/patientimage/images/${patient.gender}.png" />


## ToDo
* Link from Admin page
* Constants
* Data Model


