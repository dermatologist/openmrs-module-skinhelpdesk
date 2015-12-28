## Steps Followed


* The dependency on ui-framework does not get added to pom.xml file in omod. Bug in create-module. Added manually. Refer page [[https://wiki.openmrs.org/display/docs/Using+the+UI+Framework+in+Your+Module]] 
* Added fragments folder, fragment.controller, page.controller, resources.images, resources.styles in /omod/..


## Notes
* ui.includeJavascript("yourmoduleid", "jquery.js")
* ui.includeCss("yourmoduleid", "main.css")
* ${ ui.resourceLinks() }
* <link rel="icon" type="image/png\" href="/${ ui.contextPath() }/images/openmrs-favicon.png"/>
* <img alt="" id="patientimg" src="${pageContext.request.contextPath}/moduleResources/patientimage/images/${patient.gender}.png" />
* currentSession.saveOrUpdate(addressBook); currentSession.save(addressBook); 
* return (SkinHelpDesk) sessionFactory.getCurrentSession().createQuery("from skinhelpdesk where patientid = " + patientId).uniqueResult();
* //return (SkinHelpDesk) sessionFactory.getCurrentSession().get(SkinHelpDesk.class, patientId);



## ToDo
* Link from Admin page
* Constants
* Data Model


## Steps in Data Model creation
* Update the Model file Ex: /api/src/main/java.../api/SkinHelpDesk
* Updated Hibernate ORM Ex: /api/src/main/resources/SkinHelpDesk.hbm.xml
* Updated Liquibase Ex: /api/src/main/resources/liquibase.xml
* Updated SkinHelpDeskDAO (Interface) Ex: /api/src/main/java.../api/db/SkinHelpDeskDAO.java
* Updated HibernateSkinHelpDeskDAO (Implementation) Ex: /api/src/main/java.../api/db/hibernate/HibernateSkinHelpDeskDAO.java