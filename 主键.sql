alter table TASKZHUB add constraint PK_TASKZHUB primary key(INSTANCEID); 
 alter table TASKZIB add constraint PK_TASKZIB primary key(MASTERINSTANCEID,INSTANCEID); 
 alter table TASKZJGB add constraint PK_TASKZJGB primary key(instanceid); 
 alter table TASKJGFB add constraint PK_TASKJGFB primary key(masterinstanceid,recordsequence); 
 alter table TASKJGZIB add constraint PK_TASKJGZIB primary key(instanceid,recordsequence); 
 
 create index idx_BillCheckLog  on BillCheckLog(groupmember,checkdate); 