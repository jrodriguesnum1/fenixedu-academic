<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:include page="commonNavLocalHeader.jsp"></jsp:include>

<li class="navheader">Mensagens e Avisos</li>
<li> 
	<html:link module="/manager" page="/advisoriesManagement/listCurrentAdvisories.faces">
		Gest�o de Avisos
	</html:link>
</li>
<li> 
	<html:link module="/manager" page="/sendMail.faces">
		Envio de E-mails
	</html:link>
</li>
<br />
<li class="navheader"><bean:message bundle="MANAGER_RESOURCES" key="title.manager.organizationalStructureManagement"/></li>
<li> 
	<html:link module="/manager" page="/organizationalStructureManagament/listAllUnits.faces">
		<bean:message bundle="MANAGER_RESOURCES" key="link.manager.organizationalStructureManagement" />
	</html:link>
</li>
<li> 
	<html:link module="/manager" page="/rulesManagement.do?method=listRules">
		<bean:message bundle="MANAGER_RESOURCES" key="link.manager.rules.management" />
	</html:link>
</li>

<br />
<li class="navheader">Gest�o de Execu��es</li>
<li> 
		<html:link module="/manager" module="/manager" page="/readDegrees.do">
			<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.readDegrees" />
		</html:link>
</li>
<li> 
	  	<html:link module="/manager" module="/manager" page="/curricularPlans/chooseCurricularPlan.faces">
	  		Consulta de Curr�culo
	  	</html:link>  
</li>
<li> 
	  	<html:link module="/manager" page="/bolonha/curricularPlans/curricularPlansManagement.faces">
	  		<bean:message bundle="MANAGER_RESOURCES" key="label.manager.bolonhaCurricularPlansManagement" />
	  	</html:link>  
</li>

<li> 
	<html:link module="/manager" page="/executionDegreesManagementMainPage.do">
		<bean:message bundle="MANAGER_RESOURCES" key="label.manager.executionDegreeManagement" />
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/manageExecutionPeriods.do?method=prepare">
		Gest�o de Periodos Execu��o
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/executionCourseManagement.do?method=firstPage">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.executionCourseManagement" />
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/competenceCourseManagement.do?method=prepare">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.competence.course.management" />
	</html:link>
</li>

<li> 
	<html:link module="/manager" module="/manager" page="/manageEnrolementPeriods.do?method=prepare">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="title.manage.enrolement.period" />
	</html:link>
</li>

<br />
<li class="navheader">Gest�o de Pessoal</li>
<li> 
	<html:link module="/manager" module="/manager" page="/teachersManagement.do?method=firstPage">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="link.manager.teachersManagement" />
	</html:link>
</li>

<li> 
	<html:link module="/manager" module="/manager" page="/studentsManagement.do?method=show">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="link.manager.studentsManagement" />
	</html:link>
</li>

<li>
	<html:link module="/manager" module="/manager" page="/personManagement.do?method=firstPage">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manager.personManagement" />
	</html:link>
</li>

<br />
<li class="navheader"><bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manageFiles"/></li>
<li>
	<html:link module="/manager" module="/manager" page="/generateFiles.do?method=firstPage">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.generateFiles"/>
	</html:link>
</li>
<li>
	<html:link module="/manager" module="/manager" page="/uploadFiles.do?method=firstPage">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.uploadFiles"/>
	</html:link>
</li>

<br />
<li class="navheader"><bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.manageFinance"/></li>
<li>
	<html:link module="/manager" module="/manager" page="/guideManagement.do?method=firstPage">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="label.guidesManagement"/>
	</html:link>	
</li>
<li>
	<html:link module="/manager" module="/manager" page="/gratuity/updateGratuitySituations.faces">
		Actualizar Situa��es de Propina
	</html:link>
</li>

<br/>
<li class="navheader">Gest�o de CMS</li>
<li> 
	<html:link module="/cms" action="/personalGroupsManagement.do?method=prepare" titleKey="person.userGroupsManagement.label.title">
		<bean:message bundle="MANAGER_RESOURCES" key="link.userGroupsManagement" />
	</html:link>
	</li>
<li>
	<html:link module="/cms" action="/cmsConfigurationManagement.do?method=prepare" titleKey="person.userGroupsManagement.label.title">
		<bean:message bundle="MANAGER_RESOURCES" key="link.cmsConfiguration" />
	</html:link>	
</li>
<li>
	<html:link module="/cms" action="/executionCourseWebsiteManagement?method=viewAll" >
		<bean:message bundle="MANAGER_RESOURCES" key="cms.executionCourseWebsite.label" />
	</html:link>	
</li>

<br />
<li class="navheader">Gest�o de Suporte</li>
<li>
	<html:link module="/manager" module="/manager" page="/manageGlossary.do?method=prepare">
		Gest�o de Gloss�rio
	</html:link>
</li>
<li>
	<html:link module="/manager" module="/manager" page="/manageFAQs.do?method=prepare">
		Gest�o de FAQ's
	</html:link>
</li>

<br />
<li class="navheader">Gest�o de Objectos</li>
<li>
	<html:link module="/manager"page="/domainObjectStringPropertyFormatter.do?method=prepare">
		Formata��o de Propriedades
	</html:link>
</li>

<br />
<li class="navheader">Gest�o do Sistema</li>
<li> 

	<html:link module="/manager" page="/manageCache.do?method=prepare">
		Gest�o da Cache
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/monitorServices.do?method=monitor">
		Monitoriza��o de Servi�os
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/monitorRequestLogs.do?method=listFiles">
		<bean:message bundle="MANAGER_RESOURCES" bundle="MANAGER_RESOURCES" key="manager.monitor.requests.title"/>
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/monitorUsers.do?method=monitor">
		Monitoriza��o de Utilizadores
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/monitorSystem.do?method=monitor">
		Informa��es do Sistema
	</html:link>
</li>
<li> 
	<html:link module="/manager" module="/manager" page="/somePage.faces">
		Java Server Faces Example
	</html:link>
</li>	
<li> 
	<html:link module="/manager" page="/someStrutsPage.do?method=showFirstPage">
		Struts Example
	</html:link>
<li> 
    <html:link module="/manager" page="/reloadStruts.do">
        Reload Struts Configuration
    </html:link>
<li> 
	<html:link module="/manager" module="/manager" page="/changePasswordForward.do">
		Kerberos Test
	</html:link>
</li>

<br />
<li class="navheader">Renderers</li>
<li>
    <html:link module="/manager" page="/renderers/reload.do">Reler configura��o</html:link>
</li>
<!-- FIXME: This is only temporary to show some examples of how renderers can be used -->
<li>
    <html:link module="/manager" page="/renderers/index.do">Exemplos</html:link>
</li>

</ul>
