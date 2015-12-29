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