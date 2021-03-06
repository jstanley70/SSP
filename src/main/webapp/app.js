/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
Ext.Loader.setConfig({
	enabled: true,
	paths: {
		'Ssp': '/ssp/app',
		'ContextName': 'ssp'
	}
});

Ext.require([
    'Ssp.view.admin.AdminMain',
    'Ssp.view.admin.AdminTreeMenu',
    'Ssp.view.admin.AdminForms',
    'Ssp.view.Main',
    'Ssp.view.Search',
    'Ssp.view.SearchForm',
    'Ssp.view.StudentRecord',
    'Ssp.view.ProgramStatusChangeReasonWindow',
    'Ssp.view.person.CaseloadAssignment',
    'Ssp.view.person.EditPerson',
    'Ssp.view.person.Coach',
    'Ssp.view.person.Appointment',
    'Ssp.view.person.SpecialServiceGroups',
    'Ssp.view.person.ReferralSources',
    'Ssp.view.person.ServiceReasons',
    'Ssp.view.person.AnticipatedStartDate',    
    'Ssp.view.component.MappedTextField',
    'Ssp.view.component.MappedTextArea',
    'Ssp.view.component.MappedCheckBox',
    'Ssp.view.component.MappedRadioButton',
    'Ssp.view.ToolsMenu',
    'Ssp.view.Tools',
    'Ssp.view.tools.profile.Profile',
    'Ssp.view.tools.profile.Person',
    'Ssp.view.tools.profile.SpecialServiceGroups',
    'Ssp.view.tools.profile.ReferralSources',
    'Ssp.view.tools.profile.ServicesProvided',
    'Ssp.view.tools.profile.ServiceReasons',
	'Ssp.view.tools.profile.Coach',
	'Ssp.view.tools.profile.Contact',
	'Ssp.view.tools.profile.Placement',
    'Ssp.view.tools.profile.Transcript',
	'Ssp.view.tools.profile.RecentSSPActivity',
	'Ssp.view.tools.profile.Schedule',
    'Ssp.view.tools.profile.CurrentSchedule',
    'Ssp.view.tools.profile.DroppedCourses',
	'Ssp.view.tools.profile.Details',
	'Ssp.view.tools.profile.Dashboard',
	'Ssp.view.tools.profile.AcademicProgram',
	'Ssp.view.tools.profile.RecentTermActivity',
    'Ssp.view.tools.actionplan.ActionPlan',
    'Ssp.view.tools.actionplan.Tasks',
    'Ssp.view.tools.actionplan.AddTask',
    'Ssp.view.tools.actionplan.AddTaskForm',
    'Ssp.view.tools.actionplan.EditGoalForm',
    'Ssp.view.tools.actionplan.DisplayActionPlan',
    'Ssp.view.tools.actionplan.DisplayActionPlanGoals',
    'Ssp.view.tools.actionplan.DisplayStrengths',
    'Ssp.view.tools.actionplan.TaskTree',
    'Ssp.view.tools.studentintake.StudentIntake',
    'Ssp.view.tools.studentintake.Challenges',
    'Ssp.view.tools.studentintake.Demographics',
    'Ssp.view.tools.studentintake.EducationGoals',
    'Ssp.view.tools.studentintake.EducationLevels',
    'Ssp.view.tools.studentintake.EducationPlans',
    'Ssp.view.tools.studentintake.Funding',
    'Ssp.view.tools.studentintake.Personal',
    'Ssp.view.tools.journal.Journal',
    'Ssp.view.tools.journal.EditJournal',
    'Ssp.view.tools.journal.DisplayDetails',
    'Ssp.view.tools.journal.TrackTree',
	'Ssp.view.tools.journal.JournalList',
    'Ssp.view.tools.earlyalert.EarlyAlert',
    'Ssp.view.tools.earlyalert.EarlyAlertResponse',
    'Ssp.view.tools.earlyalert.EarlyAlertReferrals',
    'Ssp.view.tools.earlyalert.EarlyAlertDetails',
    'Ssp.view.tools.earlyalert.EarlyAlertResponseDetails',
    'Ssp.view.tools.document.StudentDocuments',
    'Ssp.view.tools.document.EditDocument',
    'Ssp.view.tools.sis.StudentInformationSystem',
    'Ssp.view.tools.sis.Registration',
    'Ssp.view.tools.sis.Assessment',

    //'Ssp.view.tools.sis.Transcript',
    'Ssp.view.tools.accommodation.Accommodation',
    'Ssp.view.tools.accommodation.General',
    'Ssp.view.tools.accommodation.AgencyContacts',
    'Ssp.view.tools.accommodation.DisabilityTypes',
    'Ssp.view.tools.accommodation.Disposition',
    'Ssp.view.tools.accommodation.Accommodations',

    'Ssp.view.tools.displacedworker.DisplacedWorker',
    'Ssp.view.tools.studentsuccess.StudentSuccess',
    'Ssp.view.admin.AdminForms',
    'Ssp.view.admin.forms.AbstractReferenceAdmin',
    'Ssp.view.admin.forms.ConfidentialityDisclosureAgreementAdmin',
	
	//MAP Views
    'Ssp.view.tools.map.MAP',
	'Ssp.view.tools.map.MAPTool',
	'Ssp.view.tools.map.CoursesView',
	'Ssp.view.tools.map.SemesterGrid',
	'Ssp.view.tools.map.SemesterGridTranscript',
    'Ssp.view.tools.map.SemesterPanel',
    'Ssp.view.tools.map.SemesterPanelContainer',
    'Ssp.view.tools.map.MAPTool',
    'Ssp.view.tools.map.FAView',
	'Ssp.view.tools.map.MAPView',
    'Ssp.view.tools.map.MovePlan',
	'Ssp.view.tools.map.MovePlanDialog',
    'Ssp.view.tools.map.PlanTool',
    'Ssp.view.tools.map.LoadPlans',
	'Ssp.view.tools.map.PlanNotes',
	'Ssp.view.tools.map.LoadTemplates',
	'Ssp.view.tools.map.SaveTemplate',
	'Ssp.view.tools.map.SavePlan',
    'Ssp.view.tools.map.CourseNotes',
    'Ssp.view.tools.map.TermNotes',
    'Ssp.view.tools.map.EmailPlan',
    'Ssp.view.tools.map.PrintPlan',
    'Ssp.view.tools.map.CourseDetails',
    'Ssp.view.tools.map.CoursesGrid',
	'Ssp.view.tools.map.CoursesGridPanel',
	
	//PERSON NOTES TOOL
	'Ssp.view.tools.notes.Notes',
	
	'Ssp.view.tools.documents.Documents',
	'Ssp.view.tools.documents.UploadDocuments',
    
    // COUNSELING REFERENCE GUIDE ADMIN VIEWS
    'Ssp.view.admin.forms.crg.ChallengeAdmin',
    'Ssp.view.admin.forms.crg.ChallengeReferralAdmin',
    'Ssp.view.admin.forms.crg.AssociateChallengeCategoriesAdmin',
    'Ssp.view.admin.forms.crg.AssociateChallengeReferralsAdmin',
    'Ssp.view.admin.forms.crg.DisplayChallengesAdmin',
    'Ssp.view.admin.forms.crg.DisplayReferralsAdmin',
    'Ssp.view.admin.forms.crg.EditChallenge',
    'Ssp.view.admin.forms.crg.EditReferral',

	
    //CASELOAD REASSIGNMENT TOOLS
    'Ssp.view.admin.forms.caseload.CaseloadReassignment',
    'Ssp.view.admin.forms.caseload.CaseloadReassignmentSource',
    'Ssp.view.admin.forms.caseload.CaseloadReassignmentTarget',

    //MyGPS ADMIN TOOLS
    'Ssp.view.admin.forms.shg.SelfHelpGuideAdmin',
    'Ssp.view.admin.forms.shg.SelfHelpGuidesDisplayAdmin',
    'Ssp.view.admin.forms.shg.EditSelfHelpGuide',
    'Ssp.view.admin.forms.shg.EditSelfHelpGuideChallenges',
    'Ssp.view.admin.forms.shg.EditSelfHelpGuideDetails',
    'Ssp.view.admin.forms.shg.EditSelfHelpGuideAvailableChallengesAdmin',
    'Ssp.view.admin.forms.shg.EditSelfHelpGuideEditChallenges',    
    
  
    // JOURNAL ADMIN VIEWS
    'Ssp.view.admin.forms.journal.JournalStepAdmin',
    'Ssp.view.admin.forms.journal.JournalStepDetailAdmin',
    'Ssp.view.admin.forms.journal.AssociateTrackStepsAdmin',
    'Ssp.view.admin.forms.journal.AssociateStepDetailsAdmin',
    'Ssp.view.admin.forms.journal.DisplayDetailsAdmin',
    'Ssp.view.admin.forms.journal.DisplayStepsAdmin',
    'Ssp.view.admin.forms.journal.EditStep',
    'Ssp.view.admin.forms.journal.EditStepDetail',
    
    // CAMPUS ADMIN VIEWS
    'Ssp.view.admin.forms.campus.CampusAdmin',
    'Ssp.view.admin.forms.campus.DefineCampus',
    'Ssp.view.admin.forms.campus.EditCampus',
    'Ssp.view.admin.forms.campus.CampusEarlyAlertRoutingsAdmin',
    'Ssp.view.admin.forms.campus.EarlyAlertRoutingsAdmin',
    'Ssp.view.admin.forms.campus.EditCampusEarlyAlertRouting',
	
	//CONFIG ADMIN VIEWS
	'Ssp.view.admin.forms.config.ConfigurationOptionsAdmin',
    'Ssp.view.admin.forms.config.ConfigurationOptionsDisplayAdmin',
	'Ssp.view.admin.forms.config.MessageTemplatesAdmin',
    'Ssp.view.admin.forms.config.MessageTemplatesDisplayAdmin',
	'Ssp.view.admin.forms.config.MessageTemplateDetails',
	'Ssp.view.admin.forms.config.RegistrationLoadAdmin',
	'Ssp.view.admin.forms.config.RegistrationLoadAddAdmin',
	'Ssp.view.admin.forms.config.RegistrationLoadListAdmin',
	'Ssp.view.admin.forms.config.CourseWorkHoursAdmin',
	'Ssp.view.admin.forms.config.CourseWorkHoursListAdmin',
	'Ssp.view.admin.forms.config.CourseWorkHoursAddAdmin',
	'Ssp.view.admin.forms.config.EnrollmentStatusAdmin',
	'Ssp.view.admin.forms.config.EnrollmentStatusAddAdmin',
	'Ssp.view.admin.forms.config.EnrollmentStatusListAdmin',
    
    // ERROR DISPLAYS
    'Ssp.view.ErrorWindow',
    
    // REPORT DISPLAY
    'Ssp.view.Report',
    
    'Ssp.model.SimpleItemDisplay',
    'Ssp.model.ObjectPermission',
    'Ssp.model.AuthenticatedPerson',
    'Ssp.model.Preferences',
    'Ssp.model.FieldError',
    'Ssp.model.util.TreeRequest',
    'Ssp.model.Configuration',
	'Ssp.model.Person',
	'Ssp.model.PersonAppointment',
	'Ssp.model.Appointment',
	'Ssp.model.CaseloadPerson',
	'Ssp.model.SearchPerson',
	'Ssp.model.SearchCriteria',
	'Ssp.model.CaseloadFilterCriteria',
	'Ssp.model.PersonGoal',
	'Ssp.model.PersonLite',
	'Ssp.model.PersonSearchLite',
	'Ssp.model.Placement',
	'Ssp.model.PersonProgramStatus',
	'Ssp.model.CourseTranscript',
	'Ssp.model.TermTranscript',
	'Ssp.model.StudentActivity',
	'Ssp.model.Transcript',
	'Ssp.model.FilterDiscreteValues',
	'Ssp.model.MessageTemplates',
	'Ssp.model.tool.studentintake.StudentIntakeForm',
	'Ssp.model.tool.studentintake.PersonDemographics',
	'Ssp.model.tool.studentintake.PersonEducationGoal',
	'Ssp.model.tool.studentintake.PersonEducationPlan',
	'Ssp.model.tool.accommodation.AccommodationForm',
	'Ssp.model.tool.accommodation.PersonDisability',
	'Ssp.model.tool.accommodation.PersonDisabilityAgency',
	'Ssp.model.tool.accommodation.PersonDisabilityType',
	'Ssp.model.tool.accommodation.PersonDisabilityAccommodation',
	'Ssp.model.tool.actionplan.Task',
	'Ssp.model.tool.earlyalert.PersonEarlyAlert',
	'Ssp.model.tool.earlyalert.PersonEarlyAlertTree',
	'Ssp.model.tool.earlyalert.EarlyAlertResponse',
	'Ssp.model.tool.earlyalert.EarlyAlertResponseGrid',
	'Ssp.model.tool.journal.JournalEntry',
	'Ssp.model.tool.journal.JournalEntryDetail',
	'Ssp.model.tool.documents.StudentDocument',
	'Ssp.model.tool.map.SemesterCourse',
	'Ssp.model.tool.map.Plan',
	'Ssp.model.tool.map.TermNote',
	'Ssp.model.tool.map.PlanCourse',
	'Ssp.model.tool.map.PlanOutputData',
	'Ssp.model.tool.shg.SelfHelpGuides',
	'Ssp.model.tool.shg.SelfHelpGuideQuestions',
	'Ssp.model.tool.caseload.CaseloadReassignmentRequest',
	'Ssp.model.reference.AbstractReference',
    'Ssp.model.reference.Challenge',
    'Ssp.model.reference.ChallengeCategory',
    'Ssp.model.reference.ChallengeReferral',
    'Ssp.model.reference.JournalTrack',
    'Ssp.model.reference.JournalStep',
    'Ssp.model.reference.JournalStepDetail',
	'Ssp.model.reference.ConfidentialityLevel',
	'Ssp.model.reference.ConfidentialityDisclosureAgreement',
	'Ssp.model.reference.EarlyAlertReferral',
	'Ssp.model.external.Course',
	'Ssp.model.external.CourseRequisite',
	'Ssp.model.external.PersonNote',
	'Ssp.model.ApiUrl',
	'Ssp.mixin.ApiProperties',
	'Ssp.mixin.controller.ItemSelectorInitializer',
    'Ssp.util.ResponseDispatcher',
	'Ssp.util.FormRendererUtils',
	'Ssp.util.ColumnRendererUtils',
	'Ssp.util.TreeRendererUtils',
	'Ssp.util.Constants',
	'Ssp.util.Util',
	'Ssp.store.Coaches',
	'Ssp.store.Caseload',
    'Ssp.store.Tasks',
    'Ssp.store.StudentActivities',
    'Ssp.store.Goals',
    'Ssp.store.SelfHelpGuides',
    'Ssp.store.SelfHelpGuideQuestions',
    'Ssp.store.JournalEntries',
    'Ssp.store.JournalEntriesUnpaged',
    'Ssp.store.JournalEntryDetails',
    'Ssp.store.EarlyAlerts',
    'Ssp.store.StudentDocuments',
	'Ssp.store.reference.AbstractReferences',
	'Ssp.store.admin.AdminTreeMenus',
	'Ssp.store.reference.AnticipatedStartTerms',
	'Ssp.store.reference.Campuses',
	'Ssp.store.reference.CampusEarlyAlertRoutings',
	'Ssp.store.reference.CampusServices',
	'Ssp.store.reference.Challenges',
	'Ssp.store.reference.ChallengeCategories',
	'Ssp.store.reference.ChallengeReferrals',
    'Ssp.store.reference.ChildCareArrangements',
    'Ssp.store.reference.Citizenships',
    'Ssp.store.reference.Colors',
	'Ssp.store.reference.ConfidentialityLevels',
	'Ssp.store.reference.ConfigurationOptions',
	'Ssp.store.reference.DisabilityAccommodations',
	'Ssp.store.reference.DisabilityAgencies',
	'Ssp.store.reference.DisabilityStatuses',
	'Ssp.store.reference.DisabilityTypes',
	'Ssp.store.reference.EarlyAlertOutcomes',
	'Ssp.store.reference.EarlyAlertOutreaches',
	'Ssp.store.reference.EarlyAlertReasons',
	'Ssp.store.reference.EarlyAlertReferrals',
	'Ssp.store.reference.EarlyAlertSuggestions',
	'Ssp.store.reference.Electives',
    'Ssp.store.reference.EmploymentShifts',
    'Ssp.store.reference.Ethnicities',
    'Ssp.store.reference.FundingSources',
    'Ssp.store.reference.Genders',
    'Ssp.store.reference.JournalSources',
    'Ssp.store.reference.JournalSourcesUnpaged',
    'Ssp.store.reference.JournalStepDetails',
    'Ssp.store.reference.JournalSteps',
    'Ssp.store.reference.JournalTracks',
    'Ssp.store.reference.JournalTracksUnpaged',
    'Ssp.store.reference.Lassis',
    'Ssp.store.reference.MaritalStatuses',
    'Ssp.store.reference.MilitaryAffiliations',
	'Ssp.store.reference.MessageTemplates',
    'Ssp.store.People',
    'Ssp.store.Placement',
    'Ssp.store.PeopleSearchLite',
    'Ssp.store.reference.RegistrationLoadRanges', 
    'Ssp.store.reference.WeeklyCourseWorkHourRanges', 
    'Ssp.store.reference.PersonalityTypes',
    'Ssp.store.reference.ProgramStatuses',
    'Ssp.store.reference.ProgramStatusChangeReasons',
    'Ssp.store.reference.ReferralSources',
    'Ssp.store.reference.ServiceReasons',
    'Ssp.store.reference.SpecialServiceGroups',
    'Ssp.store.reference.States', 
    'Ssp.store.external.Terms',
	'Ssp.store.external.TermsFaceted',
	'Ssp.store.external.Programs',
	'Ssp.store.external.ProgramsFaceted',
	'Ssp.store.external.Departments',
	'Ssp.store.external.Divisions',
    'Ssp.store.external.Courses',
    'Ssp.store.external.PersonNotes',
    'Ssp.store.Students',
	'Ssp.store.SemesterCourses',
    'Ssp.store.Search',
    'Ssp.store.reference.StudentStatuses',
    'Ssp.store.reference.StudentTypes',
	'Ssp.store.reference.Tags',
	'Ssp.store.reference.FacetedTags',
    'Ssp.store.Tools',
    'Ssp.store.reference.VeteranStatuses',
    'Ssp.store.PlanStatus',
    'Ssp.store.MAPStatus',
    'Ssp.store.CurrentlyRegistered',
    'Ssp.store.FinancialAidSAPStatus',
    'Ssp.service.AbstractService',
    'Ssp.service.AppointmentService',
    'Ssp.service.AssessmentService',
    'Ssp.service.CaseloadService',
    'Ssp.service.CampusService',
    'Ssp.service.CampusEarlyAlertRoutingService',
    'Ssp.service.ConfidentialityDisclosureAgreementService',
    'Ssp.service.AccommodationService',
    'Ssp.service.EarlyAlertService',
    'Ssp.service.EarlyAlertResponseService',
    'Ssp.service.EarlyAlertReferralService',
    'Ssp.service.JournalEntryService',
    'Ssp.service.PersonService',
    'Ssp.service.PlacementService',
	'Ssp.service.PersonNoteService',
    'Ssp.service.ProgramStatusService',
    'Ssp.service.ReferralSourceService',
    'Ssp.service.SearchService',
    'Ssp.service.SpecialServiceGroupService',
    'Ssp.service.StudentIntakeService',
    'Ssp.service.TranscriptService',
	'Ssp.service.MapPlanService',
	'Ssp.service.CourseService', 
    'Ssp.controller.ApplicationEventsController',
    'Ext.tab.*',
	'Ext.util.Filter',
	'Ext.data.TreeStore',
	'Ext.dd.*',
	'Ext.data.Store',
	'Ext.form.field.VTypes',
	'Ext.form.field.Text',
	'Ext.form.field.TextArea',
	'Ext.form.FieldSet',
	'Ext.ux.CheckColumn',
	'Ext.ux.form.MultiSelect',
	'Ext.ux.form.ItemSelector',
	'Ext.util.MixedCollection',
	'Ext.util.TaskRunner',
	'Ext.tree.*',
	'Ext.toolbar.Spacer',
	'Ext.form.field.ComboBox',
	'Ext.grid.column.Action',
	'Ext.grid.feature.Grouping'
]);

var apiUrls = [
  {name: 'campus', url: 'reference/campus'},
  {name: 'campusEarlyAlertRouting', url: 'reference/campus/{id}/earlyAlertRouting'},
  {name: 'campusService', url: 'reference/campusService'},
  {name: 'category', url: 'reference/category'},
  {name: 'challenge', url: 'reference/challenge'},
  {name: 'challengeReferral', url: 'reference/challengeReferral'},
  {name: 'childCareArrangement', url: 'reference/childCareArrangement'},
  {name: 'citizenship', url: 'reference/citizenship'},
  {name: 'color', url: 'reference/color'},
  {name: 'confidentialityDisclosureAgreement', url: 'reference/confidentialityDisclosureAgreement'},
  {name: 'printConfidentialityDisclosureAgreement', url: '/forms/ConfidentialityAgreement.jsp'},
  {name: 'confidentialityLevel', url: 'reference/confidentialityLevel'},
  {name: 'config', url: 'reference/config'},
  {name: 'configuration', url: 'reference/configuration'},
  {name: 'disabilityAccommodation', url: 'reference/disabilityAccommodation'},
  {name: 'accommodationTool', url: 'tool/accommodation'},
  {name: 'disabilityAgency', url: 'reference/disabilityAgency'},
  {name: 'disabilityStatus', url: 'reference/disabilityStatus'},
  {name: 'disabilityType', url: 'reference/disabilityType'},
  {name: 'earlyAlertOutcome', url: 'reference/earlyAlertOutcome'},
  {name: 'earlyAlertOutreach', url: 'reference/earlyAlertOutreach'},
  {name: 'earlyAlertReason', url: 'reference/earlyAlertReason'},
  {name: 'earlyAlertReferral', url: 'reference/earlyAlertReferral'},
  {name: 'earlyAlertSuggestion', url: 'reference/earlyAlertSuggestion'},
  {name: 'educationGoal', url: 'reference/educationGoal'},
  {name: 'educationLevel', url: 'reference/educationLevel'},
  {name: 'elective', url: 'reference/elective'},
  {name: 'ethnicity', url: 'reference/ethnicity'},
  {name: 'fundingSource', url: 'reference/fundingSource'},
  {name: 'journalSource', url: 'reference/journalSource'},
  {name: 'journalStep', url: 'reference/journalStep'},
  {name: 'journalTrack', url: 'reference/journalTrack'},
  {name: 'journalStepDetail', url: 'reference/journalStepDetail'},
  {name: 'lassi', url: 'reference/lassi'},
  {name: 'maritalStatus', url: 'reference/maritalStatus'},
  {name: 'militaryAffiliation', url: 'reference/militaryAffiliation'},
  {name: 'messageTemplate', url: 'reference/messageTemplate'},
  {name: 'studentStatus', url: 'reference/studentStatus'},
  {name: 'veteranStatus', url: 'reference/veteranStatus'},
  {name: 'person', url: 'person'},
  {name: 'personAppointment', url: 'person/{id}/appointment'},
  {name: 'personAssessment', url: 'person/{id}/test'},
  {name: 'personCaseload', url: 'person/caseload'},
  {name: 'personCaseloadId', url: 'person/{id}/caseload'},
  {name: 'personMasterCaseload', url: 'person/{id}/caseload'},
  {name: 'personChallenge', url: 'person/{id}/challenge'},
  {name: 'personCoach', url: 'person/coach'},
  {name: 'personDocument', url: 'person/{id}/document'},
  {name: 'personEarlyAlert', url: 'person/{personId}/earlyAlert'},
  {name: 'personEarlyAlertResponse', url: 'person/{personId}/earlyAlert/{earlyAlertId}/response'},
  {name: 'personGoal', url: 'person/{id}/goal'},
  {name: 'personJournalEntry', url: 'person/{id}/journalEntry'},
  {name: 'personTask', url: 'person/{id}/task'},
  {name: 'personTaskGroup', url: 'person/{id}/task/group'},
  {name: 'studentActivities', url: 'person/{id}/studentactivity'},
  {name: 'personalityType', url: 'reference/personalityType'},
  {name: 'personTranscript', url: 'person/{id}/transcript'},
  {name: 'personNote', url: 'person/{schoolId}/note'},
  {name: 'personEmailTask', url: 'person/{id}/task/email'},
  {name: 'personViewHistory', url: 'person/{id}/history/print'},
  {name: 'personPrintTask', url: 'person/{id}/task/print'},
  {name: 'studentSearch', url: 'person/students/search'},
  {name: 'personSearch', url: 'person/search'},
  {name: 'personMapPlan', url: 'person/{id}/map/plan'},
  {name: 'templatePlan', url: 'reference/map/template'},
  {name: 'placement', url: 'person/{id}/test'},
  {name: 'studentDocument', url: 'person/{id}/studentdocument'},
  {name: 'registrationLoadRanges', url: 'reference/config/?name=registration_load_ranges'},
  {name: 'selfHelpGuides', url: 'selfHelpGuides/search'},
  {name: 'selfHelpGuideQuestions', url: 'selfHelpGuides/selfHelpGuideQuestions'},
  {name: 'personProgramStatus', url: 'person/{id}/programStatus'},
  {name: 'programStatus', url: 'reference/programStatus'},
  {name: 'programStatusChangeReason', url: 'reference/programStatusChangeReason'},
  {name: 'referralSource', url: 'reference/referralSource'},
  {name: 'serviceReason', url: 'reference/serviceReason'},
  {name: 'session', url: 'session'},
  {name: 'server', url: 'server'},
  {name: 'serverDateTime', url: 'server/datetime'},
  {name: 'specialServiceGroup', url: 'reference/specialServiceGroup'},
  {name: 'studentIntakeTool', url: 'tool/studentIntake'},
  {name: 'studentType', url: 'reference/studentType'},
  {name: 'terms', url: 'reference/term/?sort=startDate&start=0&limit=10000&sortDirection=DESC'},
  {name: 'course', url: 'reference/course'},
  {name: 'program', url: 'reference/program/all'},
  {name: 'programfaceted', url: 'reference/program/facet'},
  {name: 'department', url: 'reference/department/all'},//TODO Change to facets.
  {name: 'division', url: 'reference/division/all'},
  {name: 'courserequisite', url: 'reference/courserequisites'},
  {name: 'tag', url: 'reference/tag'},
  {name: 'facetedtag', url: 'reference/tag/facet'},
  {name: 'futureTerms', url: 'reference/term/future'},
  {name: 'termsfaceted', url: 'reference/term/facet'},
  {name: 'weeklyCourseWorkHourRanges', url: 'reference/config/?name=weekly_course_work_hour_ranges'}
];

Ext.onReady(function(){	

    // load the authenticated user
	Ext.Ajax.request({
		url: Ssp.mixin.ApiProperties.getBaseApiUrl() + 'session/getAuthenticatedPerson',
		method: 'GET',
		headers: { 'Accept' : 'application/json','Content-Type': 'application/json' },
		success: function(response){
			var r = Ext.decode(response.responseText);
			var user={};
			
			if (r != null)
			{			
				// authenticated user
			    user=r;

				// configure the application
				Deft.Injector.configure({
					sspParentDivId: {
				        value: sspParentDivId
				    },
				    renderSSPFullScreen: {
				        value: renderSSPFullScreen
				    },
					apiUrlStore: {
						fn: function(){
							var urlStore = Ext.create('Ext.data.Store', {
							     model: 'Ssp.model.ApiUrl',
							     storeId: 'apiUrlStore'
							 });
							
							urlStore.loadData( apiUrls );
							
							return urlStore;
						},
						singleton: true
					},
					sspConfig: {
				        fn: function(){
				            return new Ssp.model.Configuration({});
				        },
				        singleton: true
				    },
					currentPerson: {
				        fn: function(){
				            return new Ssp.model.Person({id:""});
				        },
				        singleton: true
				    },
				    personLite: {
				    	fn: function(){
				    		return new Ssp.model.PersonLite({id:""});
				    	},
				    	singleton: true
				    },
				    authenticatedPerson: {
				        fn: function(){
				        	var p = new Ssp.model.AuthenticatedPerson();
				        	p.populateFromGenericObject( user );
				        	p.setObjectPermissions();
				            return p;
				        },
				        singleton: true
				    },
				    preferences: {
				        fn: function(){
				            return new Ssp.model.Preferences();
				        },
				        singleton: true
				    },
				    itemSelectorInitializer: {
				        fn: function(){
				            return new Ssp.mixin.controller.ItemSelectorInitializer({});
				        },
                        // Not a singleton b/c this is really intended to work
                        // more like a mixin on a view component, so needs to
                        // allowed to maintain state and smaller scopes than
                        // 'application'.
				        singleton: false
				    },
				    apiProperties: {
				        fn: function(){
				            return new Ssp.mixin.ApiProperties({});
				        },
				        singleton: true
				    },
				    formRendererUtils:{
				        fn: function(){
				            return new Ssp.util.FormRendererUtils({});
				    	},
				        singleton: true
				    },
				    columnRendererUtils:{
				        fn: function(){
				            return new Ssp.util.ColumnRendererUtils({});
				    	},
				        singleton: true
				    },
				    treeRendererUtils:{
				        fn: function(){
				            return new Ssp.util.TreeRendererUtils({});
				    	},
				        singleton: true
				    },
					util:{
						fn: function(){
							return new Ssp.util.Util({});
						},
						singleton: true
					},
			        appEventsController:{
				        fn: function(){
				            return new Ssp.controller.ApplicationEventsController({});
				    	},
				        singleton: true
			        },
					currentAppointment: {
				        fn: function(){
				            return new Ssp.model.Appointment({id:""});
				        },
				        singleton: true
				    },
					currentPersonAppointment: {
				        fn: function(){
				            return new Ssp.model.PersonAppointment({id:""});
				        },
				        singleton: true
				    },
			        currentChallenge:{
				        fn: function(){
				            return new Ssp.model.reference.Challenge({id:""});
				    	},
				        singleton: true
			        },
			        currentChallengeReferral:{
				        fn: function(){
				            return new Ssp.model.reference.ChallengeReferral({id:""});
				    	},
				        singleton: true
			        },
			        currentJournalStep:{
				        fn: function(){
				            return new Ssp.model.reference.JournalStep({id:""});
				    	},
				        singleton: true
			        },
			        currentStudentDocument: {
				        fn: function(){
				            return new Ssp.model.tool.documents.StudentDocument({id:""});
				    	},
				        singleton: true			        },
			        currentJournalStepDetail:{
				        fn: function(){
				            return new Ssp.model.reference.JournalStepDetail({id:""});
				    	},
				        singleton: true
			        },
					currentMapPlan: {
				        fn: function(){
				            return new Ssp.model.tool.map.Plan({id:""});
				        },
				        singleton: true
				    },
					currentSemesterStores: {
				        fn: function(){
				            return {};
				        },
				        singleton: true
				    },
			        currentTask:{
				        fn: function(){
				            return new Ssp.model.tool.actionplan.Task({id:""});
				    	},
				        singleton: true
			        },
			        currentGoal:{
				        fn: function(){
				            return new Ssp.model.PersonGoal({id:""});
				    	},
				        singleton: true
			        },
			        currentStudentIntake: {
			        	fn: function(){
			        		return new Ssp.model.tool.studentintake.StudentIntakeForm();
			        	},
				        singleton: true
			        },
			        currentAccommodation: {
			        	fn: function(){
			        		return new Ssp.model.tool.accommodation.AccommodationForm();
			        	},
				        singleton: true
			        },
			        currentJournalEntry:{
				        fn: function(){
				            return new Ssp.model.tool.journal.JournalEntry({id:""});
				    	},
				        singleton: true
			        },
			        currentEarlyAlert:{
				        fn: function(){
				            return new Ssp.model.tool.earlyalert.PersonEarlyAlert({id:""});
				    	},
				        singleton: true
			        },
			        currentEarlyAlertResponse:{
				        fn: function(){
				            return new Ssp.model.tool.earlyalert.EarlyAlertResponse({id:""});
				    	},
				        singleton: true
			        },
					currentEarlyAlertResponsesGridStore: {
                        fn: function() {
                            return Ext.create('Ext.data.Store',{
                                model: 'Ssp.model.tool.earlyalert.EarlyAlertResponseGrid',
                                storeId: 'currentEarlyAlertResponsesGridStore'
                            }); 
                        },
                        singleton: true
                    },
			        currentDocument:{
				        fn: function(){
				            return new Ssp.model.tool.documents.StudentDocument({id:""});
				    	},
				        singleton: true
			        },
			        currentCampus:{
				        fn: function(){
				            return new Ssp.model.reference.Campus({id:""});
				    	},
				        singleton: true
			        },
			        currentCampusEarlyAlertRouting:{
				        fn: function(){
				            return new Ssp.model.reference.CampusEarlyAlertRouting({id:""});
				    	},
				        singleton: true
			        },
			        currentSelfHelpGuide:{
				        fn: function(){
				            return new Ssp.model.tool.shg.SelfHelpGuides({id:""});
				    	},
				        singleton: true
			        },		
			        currentSelfHelpGuideQuestions:{
				        fn: function(){
				            return new Ssp.model.tool.shg.SelfHelpGuideQuestions({id:""});
				    	},
				        singleton: true
			        },
					courseTranscriptsStore: {
						fn: function(){
							return Ext.create('Ext.data.Store',{
								model: 'Ssp.model.CourseTranscript'
							});
						},
						singleton: true
					},
					currentScheduleStore: {
						fn: function(){
							return Ext.create('Ext.data.Store',{
								model: 'Ssp.model.CourseTranscript'
							});
						},
						singleton: true
					},
					currentDroppedScheduleStore:{
						fn: function(){
							return Ext.create('Ext.data.Store',{
								model: 'Ssp.model.CourseTranscript'
							});
						},
						singleton: true
					},
					
					termTranscriptsStore: {
						fn: function(){
							return Ext.create('Ext.data.Store',{
								model: 'Ssp.model.TermTranscript'
							});
						},
						singleton: true
					},
			        treeStore:{
				        fn: function(){
				            return Ext.create('Ext.data.TreeStore',{
				            	root: {
				    	          text: 'root',
				    	          expanded: true,
				    	          children: []
				    	        }
				            });
				    	},
				        singleton: true
			        },
			        earlyAlertsTreeStore:{
				        fn: function(){
				            return Ext.create('Ext.data.TreeStore',{
				            	model: 'Ssp.model.tool.earlyalert.PersonEarlyAlertTree'
				                ,proxy: {
				                	type: 'ajax',
				                	url: ''
				                }
				            });
				    	},
				        singleton: true
			        },
				    earlyAlertDetailsSuggestionsStore: {
				    	fn: function(){
				    		return Ext.create('Ext.data.Store', {
							     model: 'Ssp.model.SimpleItemDisplay',
							     storeId: 'earlyAlertDetailsSuggestionsStore'
							 });
				    	},
				    	singleton: true
				    },
				    earlyAlertDetailsReasonsStore: {
				    	fn: function(){
				    		return Ext.create('Ext.data.Store', {
							     model: 'Ssp.model.SimpleItemDisplay',
							     storeId: 'earlyAlertDetailsReasonsStore',
							     sorters: [{property: 'name'}]
							 });
				    	},
				    	singleton: true
				    },
				    earlyAlertResponseDetailsOutreachesStore: {
				    	fn: function(){
				    		return Ext.create('Ext.data.Store', {
							     model: 'Ssp.model.SimpleItemDisplay',
							     storeId: 'earlyAlertResponseDetailsOutreachesStore'
							 });
				    	},
				    	singleton: true
				    },
				    earlyAlertResponseDetailsReferralsStore: {
				    	fn: function(){
				    		return Ext.create('Ext.data.Store', {
							     model: 'Ssp.model.SimpleItemDisplay',
							     storeId: 'earlyAlertResponseDetailsReferralsStore'
							 });
				    	},
				    	singleton: true
				    },
			        profileSpecialServiceGroupsStore:{
				        fn: function(){
				            return Ext.create('Ext.data.Store',{
				            	model: 'Ssp.model.reference.SpecialServiceGroup',
								filterOnLoad: true,
								filters: [{property:"objectStatus", value:'ACTIVE'}]
				            });
				    	},
				        singleton: true
			        },
			        profileReferralSourcesStore:{
				        fn: function(){
				            return Ext.create('Ext.data.Store',{
				            	model: 'Ssp.model.reference.ReferralSource',
								filterOnLoad: true,
								filters: [{property:"objectStatus", value:'ACTIVE'}]
				            });
				    	},
				        singleton: true
			        },
			        profileServiceReasonsStore:{
				        fn: function(){
				            return Ext.create('Ext.data.Store',{
				            	model: 'Ssp.model.reference.ServiceReason',
								filterOnLoad: true,
								filters: [{property:"objectStatus", value:'ACTIVE'}]
				            });
				    	},
				        singleton: true
			        },
			        errorsStore:{
				        fn: function(){
				            return Ext.create('Ext.data.Store',{
				            	model: 'Ssp.model.FieldError'
				            });
				    	},
				        singleton: true
			        },
				    searchCriteria: {
				    	fn: function(){
				    		return new Ssp.model.SearchCriteria();
				    	},
				    	singleton: true
				    },
				    caseloadFilterCriteria: {
				    	fn: function(){
				    		return new Ssp.model.CaseloadFilterCriteria();
				    	},
				    	singleton: true
				    },
					currentMessageTemplate:{
				        fn: function(){
				            return new Ssp.model.MessageTemplates({id:""});
				    	},
				        singleton: true
			        },
			        // STORES
					abstractReferencesStore: 'Ssp.store.reference.AbstractReferences',
				    adminTreeMenusStore: 'Ssp.store.admin.AdminTreeMenus',
				    anticipatedStartTermsStore: 'Ssp.store.reference.AnticipatedStartTerms',
					campusesStore: 'Ssp.store.reference.Campuses',
					campusesAllUnpagedStore: {
				    	fn: function(){
				    		return Ext.create('Ssp.store.reference.Campuses', {
							     storeId: 'campusesAllUnpagedStore',		
							     extraParams: {status: "ALL", limit: "-1"}
							 });
				    	},
				    	singleton: true
				    },
					campusEarlyAlertRoutingsStore: 'Ssp.store.reference.CampusEarlyAlertRoutings',
					campusServicesStore: 'Ssp.store.reference.CampusServices',
					caseloadStore: 'Ssp.store.Caseload',
					reassignCaseloadStore: 'Ssp.store.Caseload',
					challengesStore: 'Ssp.store.reference.Challenges',
					challengeCategoriesStore: 'Ssp.store.reference.ChallengeCategories',
					challengeReferralsStore: 'Ssp.store.reference.ChallengeReferrals',
				    childCareArrangementsStore: 'Ssp.store.reference.ChildCareArrangements',
				    citizenshipsStore: 'Ssp.store.reference.Citizenships',
			    	coachesStore: 'Ssp.store.Coaches',
			    	allCoachesStore: 'Ssp.store.CoachesAll',
				    confidentialityDisclosureAgreementsStore: 'Ssp.store.reference.ConfidentialityDisclosureAgreements',
					configurationOptionsStore: 'Ssp.store.reference.ConfigurationOptions',
				    colorsStore: {
				    	fn: function(){
				    		return Ext.create('Ssp.store.reference.Colors', {
							     storeId: 'colorsStore'						    
							 });
				    	},
				    	singleton: true
				    }, 
				    colorsUnpagedStore: {
				    	fn: function(){
				    		return Ext.create('Ssp.store.reference.Colors', {
							     storeId: 'colorsUnpagedStore',		
							     extraParams: {limit: "-1"}
							 });
				    	},
				    	singleton: true
				    },
				    colorsAllStore: {
				    	fn: function(){
				    		return Ext.create('Ssp.store.reference.Colors', {
							     storeId: 'colorsAllStore',		
							     extraParams: {status: "ALL"}
							 });
				    	},
				    	singleton: true
				    },
				    colorsAllUnpagedStore: {
				    	fn: function(){
				    		return Ext.create('Ssp.store.reference.Colors', {
							     storeId: 'colorsAllUnpagedStore',		
							     extraParams: {status: "ALL", limit: "-1"}
							 });
				    	},
				    	singleton: true
				    },
				    confidentialityLevelsStore: 'Ssp.store.reference.ConfidentialityLevels',
					confidentialityLevelsAllUnpagedStore: {
						fn: function(){
							return Ext.create('Ssp.store.reference.ConfidentialityLevels', {
								storeId: 'confidentialityLevelsAllUnpagedStore',
								extraParams: {status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					},
					
				    disabilityAccommodationsStore: 'Ssp.store.reference.DisabilityAccommodations',
				    disabilityAgenciesStore: 'Ssp.store.reference.DisabilityAgencies',
				    disabilityStatusesStore: 'Ssp.store.reference.DisabilityStatuses',
				    disabilityTypesStore: 'Ssp.store.reference.DisabilityTypes',
				    earlyAlertOutcomesStore: 'Ssp.store.reference.EarlyAlertOutcomes',
				    earlyAlertOutcomesAllUnpagedStore: {
						fn: function(){
							return Ext.create('Ssp.store.reference.EarlyAlertOutcomes', {
								storeId: 'earlyAlertOutcomesAllUnpagedStore',
								extraParams: {status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					},
				    earlyAlertOutreachesStore: 'Ssp.store.reference.EarlyAlertOutreaches',
					earlyAlertOutreachesAllUnpagedStore: {
						fn: function(){
							return Ext.create('Ssp.store.reference.EarlyAlertOutreaches', {
								storeId: 'earlyAlertOutreachesAllUnpagedStore',
								extraParams: {status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					},
					earlyAlertReasonsStore: 'Ssp.store.reference.EarlyAlertReasons',
					earlyAlertReasonsAllUnpagedStore: {
						fn: function(){
							return Ext.create('Ssp.store.reference.EarlyAlertReasons', {
								storeId: 'earlyAlertReasonsAllUnpagedStore',
								extraParams: {status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					},
					earlyAlertReferralsStore: 'Ssp.store.reference.EarlyAlertReferrals',
					earlyAlertReferralsAllUnpagedStore: {
						fn: function(){
							return Ext.create('Ssp.store.reference.EarlyAlertReferrals', {
								storeId: 'earlyAlertReferralsAllUnpagedStore',
								extraParams: {status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					},
					earlyAlertReferralsBindStore: 'Ssp.store.reference.EarlyAlertReferralsBind',
					earlyAlertsStore: 'Ssp.store.EarlyAlerts',
					earlyAlertSuggestionsStore: 'Ssp.store.reference.EarlyAlertSuggestions',
					earlyAlertSuggestionsAllUnpagedStore: {
						fn: function(){
							return Ext.create('Ssp.store.reference.EarlyAlertSuggestions', {
								storeId: 'earlyAlertSuggestionsAllUnpagedStore',
								extraParams: {status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					},
				    educationGoalsStore: 'Ssp.store.reference.EducationGoals',
			    	educationLevelsStore: 'Ssp.store.reference.EducationLevels',
			    	electivesStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.Electives', {
							     storeId: 'electivesStore',
							     extraParams: {sort: "sortOrder", sortDirection: "ASC" }
							 });
					    },
					    singleton: true
					},
					electivesUnpagedStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.Electives', {
							     storeId: 'electivesUnpagedStore',
							     extraParams: {sort: "sortOrder", sortDirection: "ASC", limit: "-1"}
							 });
					    },
					    singleton: true
					},
					electivesAllStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.Electives', {
							     storeId: 'electivsAllStore',		
							     extraParams: {sort: "sortOrder", sortDirection: "ASC", status: "ALL"}
							 });
					    },
					    singleton: true
					},
					electivesAllUnpagedStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.Electives', {
							     storeId: 'electivsAllUnpagedStore',		
							     extraParams: {sort: "sortOrder", sortDirection: "ASC", status: "ALL", limit: "-1"}
							 });
					    },
					    singleton: true
					},
					
					planTemplatesSummaryStore: {
						fn: function(){
							return Ext.create('Ssp.store.PlanTemplatesSummary', {
							     storeId: 'planTemplatesSummaryStore',		
							     extraParams: {sort: "name", sortDirection: "DESC", status: "ALL", limit: "-1"}
							});
						},
						singleton: true
					}, 
			    	employmentShiftsStore: 'Ssp.store.reference.EmploymentShifts',
			    	ethnicitiesStore: 'Ssp.store.reference.Ethnicities',
			    	fundingSourcesStore: 'Ssp.store.reference.FundingSources',
			    	gendersStore: 'Ssp.store.reference.Genders',
				    goalsStore: 'Ssp.store.Goals',
			    	journalEntriesStore: 'Ssp.store.JournalEntries',
			    	journalEntriesUnpagedStore: 'Ssp.store.JournalEntriesUnpaged',
			    	journalEntryDetailsStore: 'Ssp.store.JournalEntryDetails',
			    	journalSourcesStore: 'Ssp.store.reference.JournalSources',
			    	journalSourcesUnpagedStore: 'Ssp.store.reference.JournalSourcesUnpaged',
					journalSourcesAllUnpagedStore: 'Ssp.store.reference.JournalSourcesAllUnpaged',
			        journalStepsStore: 'Ssp.store.reference.JournalSteps',
			        journalDetailsStore: 'Ssp.store.reference.JournalStepDetails',
			        journalTracksStore: 'Ssp.store.reference.JournalTracks',
			        journalTracksUnpagedStore: 'Ssp.store.reference.JournalTracksUnpaged',
					journalTracksAllUnpagedStore: 'Ssp.store.reference.JournalTracksAllUnpaged',
			        lassisStore: 'Ssp.store.reference.Lassis',
			        maritalStatusesStore: 'Ssp.store.reference.MaritalStatuses',
			    	militaryAffiliationsStore: 'Ssp.store.reference.MilitaryAffiliations',
					messageTemplatesStore: 'Ssp.store.reference.MessageTemplates',
			    	peopleSearchLiteStore: 'Ssp.store.PeopleSearchLite',			    	
			    	peopleStore: 'Ssp.store.People',
			    	personalityTypesStore: 'Ssp.store.reference.PersonalityTypes',
			    	placementStore: 'Ssp.store.Placement',
			    	planStore: 'Ssp.store.Plan',			    	
					programStatusesStore: 'Ssp.store.reference.ProgramStatuses',
			    	programStatusChangeReasonsStore: 'Ssp.store.reference.ProgramStatusChangeReasons',
				    referralSourcesStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.ReferralSources', { });
					    },
					    singleton: true
					},
					referralSourcesAllUnpagedStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.ReferralSources', {
					    		extraParams: {status: "ALL", limit: "-1"}
					    	});
					    },
					    singleton: true
					},
				    searchStore: 'Ssp.store.Search',
				    studentsSearchStore: 'Ssp.store.StudentsSearch',
				    selfHelpGuidesStore: 'Ssp.store.SelfHelpGuides',
				    selfHelpGuideQuestionsStore: 'Ssp.store.SelfHelpGuideQuestions',
				    serviceReasonsStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.ServiceReasons', {
					    	});
					    },
					    singleton: true
					},
				    serviceReasonsAllUnpagedStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.ServiceReasons', {
					    		extraParams: {status: "ALL", limit: "-1"}
					    	});
					    },
					    singleton: true
					},
				    specialServiceGroupsStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.SpecialServiceGroups', { });
					    },
					    singleton: true
					},
				    specialServiceGroupsAllUnpagedStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.SpecialServiceGroups', {
							     extraParams: {status: "ALL", limit: "-1"}
							 });
					    },
					    singleton: true
					},
				    statesStore: 'Ssp.store.reference.States',
				    studentDocumentsStore: 'Ssp.store.StudentDocuments',
				    studentsStore: 'Ssp.store.Students',
				    studentStatusesStore: 'Ssp.store.reference.StudentStatuses',
				    studentTypesStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.StudentTypes', {});
					    },
					    singleton: true
					},
					studentTypesAllUnpagedStore: {
			    		fn: function(){
					    	return Ext.create('Ssp.store.reference.StudentTypes', {
							     extraParams: {status: "ALL", limit: "-1"}
							 });
					    },
					    singleton: true
					},
				    registrationLoadRangesStore: 'Ssp.store.reference.RegistrationLoadRanges', 
				    weeklyCourseWorkHourRangesStore: 'Ssp.store.reference.WeeklyCourseWorkHourRanges',
				    termsStore:'Ssp.store.external.Terms',
					termsFacetedStore:'Ssp.store.external.TermsFaceted',
				    programsStore:'Ssp.store.external.Programs',
					programsFacetedStore:'Ssp.store.external.ProgramsFaceted',
				    divisionsStore:'Ssp.store.external.Divisions',
				    personNotesStore:'Ssp.store.external.PersonNotes',
				    departmentsStore:'Ssp.store.external.Departments',
					coursesStore:'Ssp.store.external.Courses',
					tagsStore: 'Ssp.store.reference.Tags',
					facetedTagsStore: 'Ssp.store.reference.FacetedTags',
				    tasksStore: 'Ssp.store.Tasks',
				    studentActivitiesStore: 'Ssp.store.StudentActivities',
				    toolsStore: 'Ssp.store.Tools',
			    	veteranStatusesStore: 'Ssp.store.reference.VeteranStatuses',
			        planStatusStore: 'Ssp.store.PlanStatus',
			        financialAidSAPStatus: 'Ssp.store.FinancialAidSAPStatus',
			        mapStatusStore: 'Ssp.store.MAPStatus',
			        currentlyRegisteredStore: 'Ssp.store.CurrentlyRegistered',
			        
			        
			        	
			        // SERVICES
			        appointmentService: 'Ssp.service.AppointmentService',
			        assessmentService: 'Ssp.service.AssessmentService',
			        campusService: 'Ssp.service.CampusService',
			        campusEarlyAlertRoutingService: 'Ssp.service.CampusEarlyAlertRoutingService',
			        caseloadService: 'Ssp.service.CaseloadService',
			        confidentialityDisclosureAgreementService: 'Ssp.service.ConfidentialityDisclosureAgreementService',
			        accommodationService: 'Ssp.service.AccommodationService',
			        earlyAlertService: 'Ssp.service.EarlyAlertService',
			        earlyAlertReferralService: 'Ssp.service.EarlyAlertReferralService',
			        earlyAlertResponseService: 'Ssp.service.EarlyAlertResponseService',
			        journalEntryService: 'Ssp.service.JournalEntryService',
			        personService: 'Ssp.service.PersonService',
			        placementService: 'Ssp.service.PlacementService',
					personNoteService: 'Ssp.service.PersonNoteService',
			        personProgramStatusService: 'Ssp.service.PersonProgramStatusService',
			        programStatusService: 'Ssp.service.ProgramStatusService',
			        referralSourceService: 'Ssp.service.ReferralSourceService',
			        searchService: 'Ssp.service.SearchService',
			        specialServiceGroupService: 'Ssp.service.SpecialServiceGroupService',
			        studentIntakeService: 'Ssp.service.StudentIntakeService',
			        transcriptService: 'Ssp.service.TranscriptService',
			        mapPlanService: 'Ssp.service.MapPlanService',
			        studentActivityService: 'Ssp.service.StudentActivityService',
			        courseService: 'Ssp.service.CourseService'
				});


				// Do not use 'autoCreateViewport: true' here. It will trigger
				// initialization of the Deft IoC container before the
				// Application exists, so some managed components may be only
				// partially initialized. This is particularly problematic for
				// AppEventsController which needs a reference to the
				// Application. If it does not have that reference, the first
				// components to load (those associated with Viewport) cannot
				// register Application-scoped events during their
				// initialization. The resulting deferred event listener
				// binding has been the direct cause of subtle bugs.
				Ext.application({
				    name: 'Ssp',
				    appFolder: Ext.Loader.getPath('Ssp'),
				    launch: function( app ) {
				    	var me=this;
				    	Deft.Injector.resolve("appEventsController").setApp(me);
				    	
				    	// Date patterns for formatting by a description
				    	// rather than a date format
				    	Ext.Date.patterns = {
				    		    ISO8601Long:"Y-m-d H:i:s",
				    		    ISO8601Short:"Y-m-d",
				    		    ShortDate: "n/j/Y",
				    		    LongDate: "l, F d, Y",
				    		    FullDateTime: "l, F d, Y g:i:s A",
				    		    MonthDay: "F d",
				    		    ShortTime: "g:i A",
				    		    LongTime: "g:i:s A",
				    		    SortableDateTime: "Y-m-d\\TH:i:s",
				    		    UniversalSortableDateTime: "Y-m-d H:i:sO",
				    		    YearMonth: "F, Y"
				    	};
				    	
				    	// Global error handling for Ajax calls 
				    	Ext.override(Ext.data.proxy.Server, {
				            constructor: function(config)
				            {
				                this.callOverridden([config]);
				                this.addListener("exception",  function (proxy, response, operation) {
				            		if (response.status==403)
				            		{
				            			Ext.Msg.confirm({
				            	   		     title:'Access Denied Error',
				            	   		     msg: "It looks like you are trying to access restricted information or your login session has expired. Would you like to login to continue working in SSP?",
				            	   		     buttons: Ext.Msg.YESNO,
				            	   		     fn: function( btnId ){
				            	   		    	if (btnId=="yes")
				            	   		    	{
				            	   		    		// force a login
				            	   		    		window.location.reload();
				            	   		    	}else{
				            	   		    		// force a login
				            	   		    		window.location.reload();
				            	   		    	}
				            	   		    },
				            	   		     scope: me
				            	   		});
				            		}
				            		
				            		// Handle call not found result
				            		if (response.status==404)
				            		{
				            			Ext.Msg.alert('SSP Error', '404 Server Error. See logs for additional details');
				            		}
				                });
				            }
				        });
				    	
				    	/*
				    	 * Provide global asterisks next to required fields
				    	 */
				    	Ext.Function.interceptAfter(Ext.form.Field.prototype,'initComponent', function(){
				    		var fl=this.fieldLabel, ab=this.allowBlank;
				    		if (fl){
				    			this.labelStyle=Ssp.util.Constants.SSP_LABEL_STYLE;
				    		}
				    		if (ab===false && fl){
				    			this.fieldLabel += Ssp.util.Constants.REQUIRED_ASTERISK_DISPLAY;
				    		}
				    	});		

				    	/*
				    	 * Provide global asterisks next to required field containers
				    	 */
				    	Ext.Function.interceptAfter(Ext.form.FieldContainer.prototype,'initComponent', function(){
				    		var fl=this.fieldLabel, ab=this.allowBlank;
				    		if (fl){
				    			this.labelStyle=Ssp.util.Constants.SSP_LABEL_STYLE;
				    		}
				    		if (ab===false && fl){
				    			this.fieldLabel += Ssp.util.Constants.REQUIRED_ASTERISK_DISPLAY;
				    		}
				    	});				    	
				    	
				    	/*
				    	 * Per Animal, http://www.extjs.com/forum/showthread.php?p=450116#post450116
				    	 * Override to provide a function to determine the invalid
				    	 * fields in a form.
				    	 */
				    	Ext.override(Ext.form.BasicForm, {
				    	    findInvalid: function() {
				    	        var result = [], it = this.getFields().items, l = it.length, i, f;
				    	        for (i = 0; i < l; i++) {
				    	            if(!(f = it[i]).disabled && f.isValid()){
				    	                result.push(f);
				    	            }
				    	        }
				    	        return result;
				    	    }
				    	});	    	

				    	/*
				    	 * Per Animal, http://www.extjs.com/forum/showthread.php?p=450116#post450116
				    	 * Override component so that the first invalid field
				    	 * will be displayed for the user when a form is invalid.
				    	 */
				    	Ext.override(Ext.Component, {
				    	    ensureVisible: function(stopAt) {
				    	    	var p;
				    	        this.ownerCt.bubble(function(c) {
				    	        	if (p = c.ownerCt) {
				    	                if (p instanceof Ext.TabPanel) {
				    	                    p.setActiveTab(c);
				    	                } else if (p.layout.setActiveItem) {
				    	                    p.layout.setActiveItem(c);
				    	                } else if (p.layout.type == 'accordion'){
				    	                	c.expand();
				    	                }
				    	            }
				    	            return (c !== stopAt);
				    	        });
				    	        //this.el.scrollIntoView(this.el.up(':scrollable'));
				    	        return this;
				    	    }
				    	});

				    	/*
				    	 * Per Animal, http://www.extjs.com/forum/showthread.php?p=450116#post450116
				    	 * Enables scrolling to the nearest visible elements
				    	 * in a form for use with the above override for
				    	 * visually indicating when a form validation fails
				    	 * and setting the user to see the first invalid field.    	 
				    	Ext.DomQuery.pseudos.scrollable = function(c, t) {
				    	    var r = [], ri = -1;
				    	    for(var i = 0, ci; ci = c[i]; i++){
				    	        var o = ci.style.overflow;
				    	        if(o=='auto'||o=='scroll') {
				    	            if (ci.scrollHeight < Ext.fly(ci).getHeight(true)) r[++ri] = ci;
				    	        }
				    	    }
				    	    return r;
				    	};
				    	*/    	
				    	
			   	    	// load the main view
			    		Ext.apply(me,{
				    		items: [{xtype:'sspview'}]
				    	});

				    	// Since we're not using 'autoCreateViewport: true',
				    	// we need to create the default view ourselves.
				    	// (Frankly not sure exactly what the relationship is
				    	// between this and the xtype-based lookup of the same
				    	// component type immediately above. But you'll get
				    	// a blank screen without this explicit create.
				    	Ext.create( "Ssp.view.Viewport");
				    	
				   }
				});
				
			}else{
				Ext.Msg.alert('Error','Unable to determine authenticated user. Please see your system administrator for assistance.');
			}
		}
	}, this);		
	
});
