<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>

<portlet:renderURL var="enterAlertUrl" escapeXml="false">
    <portlet:param name="action" value="enterAlert"/>
    <portlet:param name="studentId" value="STUDENTID"/>
    <portlet:param name="courseId" value="COURSEID"/>
</portlet:renderURL>

<c:set var="n"><portlet:namespace/></c:set>

<script src="<rs:resourceURL value="/rs/jquery/1.6.1/jquery-1.6.1.min.js"/>" type="text/javascript"></script>
<script src="<rs:resourceURL value="/rs/jqueryui/1.8.13/jquery-ui-1.8.13.min.js"/>" type="text/javascript"></script>
<script src="<rs:resourceURL value="/rs/fluid/1.4.0/js/fluid-all-1.4.0.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/js/early-alert-roster.js" />" type="text/javascript"></script>

<link href="<c:url value="/resources/css/early-alert.css" />" rel="stylesheet" type="text/css">

<!-- Portlet -->
<div id="${n}earlyAlert" class="fl-widget portlet early-alert" role="section">
  
  	<!-- Portlet Message (success) -->
  <div class="portlet-msg-success portlet-msg success sent-message" role="status" style="display: none;">
    <div class="titlebar">
      <h3 class="title"><spring:message code="early.alert.sent"/></h3>
    </div>
    <div class="content">
      <p><spring:message code="alert.sent.prefix"/> <strong><c:out value="${studentName}"/></strong> <spring:message code="alert.sent.suffix"/></p>
    </div>
  </div>

  <!-- Portlet Titlebar -->
  <div class="fl-widget-titlebar titlebar portlet-titlebar" role="sectionhead">
  	<h2 class="title" role="heading"><spring:message code="course.roster"/></h2>
    <div class="fl-col-flex2 toolbar" role="toolbar">
      <div class="fl-col">
        <!--label for="courseSelect" class="course-label"><spring:message code="course"/>:</label-->
        <select class="course-select">
        </select>
      </div>
      <div class="fl-col fl-text-align-right">&nbsp;</div>
    </div>
    <div style="clear:both"></div>
  </div>

  <!-- Portlet Content -->
  <div class="fl-widget-content content portlet-content" role="main">
  
  	<!-- Portlet Message (loading) -->
  	<div class="portlet-msg-info portlet-msg info loading-message" role="status" style="display: none;">
    	<div class="titlebar">
        <h3 class="title"><spring:message code="loading"/> . . .</h3>
      </div>
      <div class="content">
    	  <p><spring:message code="please.wait.while.the.system.finishes.loading.roster"/></p>
      </div>
    </div>

    <!-- Portlet Section -->
    <div class="fl-pager roster">   
        <div class="fl-col-flex2">
          <div class="fl-col view-filter">&nbsp;
            <!-- Search... perhaps later -->
            <!--form class="roster-search-form" style="display:inline">
              <input type="text" class="roster-search-input"/>
              <input type="submit" value="<spring:message code="search"/>"/>
            </form-->
          </div>
          <div class="fl-col flc-pager-top view-pager">
            <ul id="pager-top" class="fl-pager-ui">
              <li class="flc-pager-previous"><a href="javascript:;">&lt; <spring:message code="previous"/></a></li>
              <li>
                <ul class="fl-pager-links flc-pager-links" style="margin:0; display:inline">
                  <li class="flc-pager-pageLink"><a href="javascript:;">1</a></li>
                </ul>
              </li>
              <li class="flc-pager-next"><a href="javascript:;"><spring:message code="next"/> &gt;</a></li>
              <li>
                <span class="flc-pager-summary"><spring:message code="show"/></span>
                <span> <select class="pager-page-size flc-pager-page-size">
                <option value="10">10</option>
                <option value="20">20</option>
                <option value="50">50</option>
                </select></span> <spring:message code="per.page"/>
              </li>
            </ul>
          </div>
        </div>
        <div style="clear:both"></div>

        <table summary="<spring:message code="roster"/>" xmlns:rsf="http://ponder.org.uk" class="portlet-table roster-table" style="width:100%;">
          <thead>
            <tr rsf:id="header:">
              <th id="${n}firstName" class="flc-pager-sort-header"><a rsf:id="firstName" title="Click to sort" href="javascript:;"><spring:message code="first.name"/></a></th>
              <th id="${n}middleInitial" class="flc-pager-sort-header"><a rsf:id="middleInitial" title="Click to sort" href="javascript:;"><spring:message code="middle.initial"/></a></th>
              <th id="${n}lastName" class="flc-pager-sort-header"><a rsf:id="lastName" title="Click to sort" href="javascript:;"><spring:message code="last.name"/></a></th>
              <th id="${n}studentType" class="flc-pager-sort-header"><a rsf:id="studentType" title="Click to sort" href="javascript:;"><spring:message code="student.type"/></a></th>
            </tr>
          </thead>
          <tbody class="roster-body">
            <tr rsf:id="row:">
              <td headers="${n}firstName"><span rsf:id="firstName"></span></td>
              <td headers="${n}middleInitial" rsf:id="middleInitial"></td>
              <td headers="${n}lastName" rsf:id="lastName"></td>
              <td headers="${n}studentType" rsf:id="studentType"></td>
            </tr>
          </tbody>
        </table>
        
        <p><spring:message code="click.a.student.from.the.roster.to.send.an.early.alert"/></p>

      </div>
    
  </div> <!-- end: portlet-body -->

</div> <!-- end: portlet -->
    	
<script type="text/javascript">
    var ${n} = {};
    ${n}.jQuery = jQuery.noConflict(true);
    ${n}.fluid = fluid;
    fluid = null;
    fluid_1_4 = null;

    ${n}.jQuery(function() {
        var $ = up.jQuery;
        var fluid = up.fluid;

        ssp.EarlyAlertRoster('#${n}earlyAlert', { enterAlertUrl: '${enterAlertUrl}' });
        
        // Confirm submission
        if (${studentName != null ? 'true' : 'false'}) {
            $('#${n}earlyAlert .sent-message').slideDown(1000);
        }

    });
</script>