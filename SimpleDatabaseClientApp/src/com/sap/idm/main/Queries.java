package com.sap.idm.main;

public class Queries {
	
	public final static String TEST_QUERY = "select mcUniqueId,mcLinkType,mccontextmskey,mccontextentrytype,mcthismskey,mcthisentrydisplayname,mcThisEntryMskeyvalue,mcothermskey,mcotherentrydisplayname,mcOtherEntryMskeyvalue,"
			+ " mcthisentrytype,mcOtherEntryType,mcLinkState,mcValidFrom,mcValidTo,mcReason,mcAssignedDirect,mcexecstate,mcexecstatehierarchy,mcmodifynewvalidfrom,mcmodifynewvalidto,mcdirvalidfrom,"
			+ " mcdirvalidto,mcassigneddirect,mcexecstate,mcmodifyreason,mcAssignedDynamicGroup,mcAssignedInheritCount,mcAssignedMasterPrivilege,mcOrphan,mcAssigner,mcAddedTime,mcModifyTime,mcAttrId,"
			+ " mcdirvalidto,mcassigneddirect,mcexecstate,mcmodifyreason,mcAssignedDynamicGroup,mcAssignedInheritCount,mcAssignedMasterPrivilege,mcOrphan,mcAssigner,mcAddedTime,mcModifyTime,mcAttrId"
			+ " from mxiv_link_entry VALS "
			+ " WHERE ( ((mcLinkState=0 OR (mcLinkState=1 AND (mcExecState>=512 OR mcExecState=2 OR mcExecState=4 OR mcAssignedDirect=1 OR mcAssignedDynamicGroup=1)))) "
			+ "  OR (mcLinkState<>2 AND mcLinkState=0) ) AND mcAttrId=?  AND mcThisMskey=?  AND EXISTS ( "
			+ " SELECT mcMskey FROM mxiv_entry E  WHERE mcMskey=VALS.mcothermskey  and ((mcACEntry=0) or (mcACentry=1 AND ((? IN ( "
			+ " SELECT MemberMskey FROM idmv_members  WHERE EntryMskey=E.mcMSKEY)) OR (? IN ("
			+ " SELECT OwnerExpandedMskey FROM idmv_owners  WHERE EntryMSKEY=E.mcMSKEY)))) or ( mcACEntry=2 AND ? IN ( "
			+ " SELECT OwnerExpandedMskey FROM idmv_owners  WHERE EntryMSKEY=E.mcMSKEY)))) ORDER BY mcUniqueId DESC ";

}
