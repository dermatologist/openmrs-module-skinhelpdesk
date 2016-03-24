## Steps Followed


* The dependency on ui-framework does not get added to pom.xml file in omod. Bug in create-module. Added manually. Refer page [[https://wiki.openmrs.org/display/docs/Using+the+UI+Framework+in+Your+Module]] 
* Added fragments folder, fragment.controller, page.controller, resources.images, resources.styles in /omod/..


## Notes
* ui.includeJavascript("yourmoduleid", "jquery.js")
* ui.includeCss("yourmoduleid", "main.css")
* ${ ui.resourceLinks() }
```
 <link rel="icon" type="image/png\" href="/${ ui.contextPath() }/images/openmrs-favicon.png"/>
 <img alt="" id="patientimg" src="${pageContext.request.contextPath}/moduleResources/patientimage/images/${patient.gender}.png" />
```
* currentSession.saveOrUpdate(addressBook); currentSession.save(addressBook); 
* return (SkinHelpDesk) sessionFactory.getCurrentSession().createQuery("from skinhelpdesk where patientid = " + patientId).uniqueResult();
* //return (SkinHelpDesk) sessionFactory.getCurrentSession().get(SkinHelpDesk.class, patientId);
* List<addressbook> list = currentSession.createCriteria(AddressBook.class).add(Restrictions.eq("name", name)).list();  
* _extension and _app in the app folder under resources
```
  <a href="${ ui.pageLink("orderentryui", "drugOrders", [patient: patient.id, returnUrl: ui.thisUrl()]) }">
```
* java.util.Date and java.lang.String
* executeDataSet("YourTestDataSyncCreateTest.xml");
* In order to have your Context sensitive tests actually commit to the database 
instead of rolling back at the end, all you need to do is add 
the @Rollback(false) annotation in your test method. 
* CHANGE VERSION NUMBER IN ALL pom.xml
* Author name in config.xml
* Downgrade fiefox for selenium [[http://askubuntu.com/questions/500644/how-to-downgrade-firefox-from-30-to-28]]
* Interface testing from mvn mvn -Dtest=LoginTest test

## ToDo
* Link from Admin page
* Data Model


## Steps in Data Model creation
* Update the Model file Ex: /api/src/main/java.../api/SkinHelpDesk
* Updated Hibernate ORM Ex: /api/src/main/resources/SkinHelpDesk.hbm.xml
* Updated Liquibase Ex: /api/src/main/resources/liquibase.xml
* Updated SkinHelpDeskDAO (Interface) Ex: /api/src/main/java.../api/db/SkinHelpDeskDAO.java
* Updated HibernateSkinHelpDeskDAO (Implementation) Ex: /api/src/main/java.../api/db/hibernate/HibernateSkinHelpDeskDAO.java
* Update SkinHelpDeskService (Interface) Ex: /api/src/main/java.../api/db/SkinHelpDeskService.java 
* Update SkinHelpDeskServiceImpl (Implementation) Ex: /api/src/main/java.../api/db/impl/SkinHelpDeskServiceImpl.java 


## Hibernate Relationships
* many-to-one 
Ex: <many-to-one name="address" column="address" class="Address" not-null="true"/>
* one-to-one is similar to many-to-one with unique column 
Ex: <many-to-one name="address" column="address" unique="true" class="Address" not-null="true"/>
* One-to-Many (Ex: One Employee has many Certificates) mapping can be implemented using a Set java collection that does not contain any duplicate element.
Ex: private Set certificates; in POJO
EMployee
```
   <set name="certificates" cascade="all">
         <key column="employee_id"/>
         <one-to-many class="Certificate"/>
      </set>
```
* Many-to-many  private Set certificates; in POJO
```
     <set name="certificates" cascade="save-update" table="EMP_CERT">
         <key column="employee_id"/>
         <many-to-many column="certificate_id" class="Certificate"/>
      </set>
```      
      ** Additional Table required in liquibase 
      
## Release
* mvn release:prepare
* mvn release:rollback
* mvn release:perform