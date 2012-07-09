/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012/7/9 23:14:39                            */
/*==============================================================*/


drop table if exists Staff;

drop table if exists batch;

drop table if exists dailyStaffDisq;

drop table if exists department;

drop table if exists disqDetail;

drop table if exists disqKind;

drop table if exists flowpath;

drop table if exists process;

drop table if exists product;

drop table if exists productLine;

drop table if exists scheduleTab;

drop table if exists staffDetail;

drop table if exists staffKind;

drop table if exists user;

drop table if exists workForm;

drop table if exists workHoursTab;

drop table if exists workTab;

/*==============================================================*/
/* Table: Staff                                                 */
/*==============================================================*/
create table Staff
(
   Id                   int not null,
   deptId               int,
   kindId               int,
   staName              varchar(20),
   staNo                varchar(20),
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id),
   key AK_Key_2 (Id)
);

/*==============================================================*/
/* Table: batch                                                 */
/*==============================================================*/
create table batch
(
   Id                   int not null,
   batchNo              varchar(20),
   flowId               int,
   proId                int,
   workTabId            int,
   status               int,
   startTime            date,
   endTime              date,
   disqNum              int,
   disqPercent          int,
   totalNum             int,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: dailyStaffDisq                                        */
/*==============================================================*/
create table dailyStaffDisq
(
   Id                   int not null,
   disqdeId             int,
   staffId              int,
   totalNum             int,
   time                 date,
   primary key (Id)
);

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   Id                   int not null,
   deptNo               varchar(20),
   deptName             varchar(20),
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: disqDetail                                            */
/*==============================================================*/
create table disqDetail
(
   Id                   int not null,
   disKId               int,
   nextId               int,
   num                  int,
   primary key (Id)
);

/*==============================================================*/
/* Table: disqKind                                              */
/*==============================================================*/
create table disqKind
(
   Id                   int not null,
   disDesc              varchar(30),
   kind                 int,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: flowpath                                              */
/*==============================================================*/
create table flowpath
(
   Id                   int not null,
   sequence             varchar(20),
   proId                int,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: process                                               */
/*==============================================================*/
create table process
(
   Id                   int not null,
   procName             varchar(20),
   colorNo              varchar(10),
   procNo               varchar(20),
   unitOutput           int,
   unitCost             int,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
   Id                   int not null,
   deptId               int,
   prolineId            int,
   proNo                varchar(20),
   proName              varchar(20),
   time                 date,
   disqNum              int,
   disqPerc             double,
   totalNum             int,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: productLine                                           */
/*==============================================================*/
create table productLine
(
   Id                   int not null,
   lineDesc             varchar(20),
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: scheduleTab                                           */
/*==============================================================*/
create table scheduleTab
(
   Id                   int not null,
   batchId              int,
   time                 date,
   colorNo              varchar(10),
   num                  int,
   primary key (Id)
);

/*==============================================================*/
/* Table: staffDetail                                           */
/*==============================================================*/
create table staffDetail
(
   Id                   int not null,
   staffId              int,
   proName              varchar(20),
   proNo                varchar(20),
   procName             varchar(10),
   quaNum               int,
   gWaste               int,
   lWaste               int,
   workHours            double,
   primary key (Id)
);

/*==============================================================*/
/* Table: staffKind                                             */
/*==============================================================*/
create table staffKind
(
   Id                   int not null,
   kindDesc             varchar(20),
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   Id                   int not null,
   username             varchar(20),
   password             varchar(20),
   level                int,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: workForm                                              */
/*==============================================================*/
create table workForm
(
   Id                   int not null,
   staId                int,
   procId               int,
   batchId              int,
   proId                int,
   quaNum               int,
   disDetail            varchar(0),
   time                 date,
   isDelete             int default 0,
   deleteTime           date,
   primary key (Id)
);

/*==============================================================*/
/* Table: workHoursTab                                          */
/*==============================================================*/
create table workHoursTab
(
   Id                   int not null,
   staId                int,
   time                 date,
   workHours            double,
   salary               double,
   primary key (Id)
);

/*==============================================================*/
/* Table: workTab                                               */
/*==============================================================*/
create table workTab
(
   Id                   int not null,
   procId               int,
   quNum                int,
   disqNum              int,
   isOver               int,
   overTime             date,
   isEnd                int,
   primary key (Id)
);

alter table Staff add constraint FK_Reference_15 foreign key (deptId)
      references department (Id) on delete restrict on update restrict;

alter table Staff add constraint FK_Reference_24 foreign key (kindId)
      references staffKind (Id) on delete restrict on update restrict;

alter table batch add constraint FK_Reference_12 foreign key (workTabId)
      references workTab (Id) on delete restrict on update restrict;

alter table batch add constraint FK_Reference_5 foreign key (flowId)
      references flowpath (Id) on delete restrict on update restrict;

alter table batch add constraint FK_Reference_6 foreign key (proId)
      references product (Id) on delete restrict on update restrict;

alter table dailyStaffDisq add constraint FK_Reference_20 foreign key (disqdeId)
      references disqDetail (Id) on delete restrict on update restrict;

alter table dailyStaffDisq add constraint FK_Reference_21 foreign key (staffId)
      references Staff (Id) on delete restrict on update restrict;

alter table disqDetail add constraint FK_Reference_2 foreign key (disKId)
      references disqKind (Id) on delete restrict on update restrict;

alter table flowpath add constraint FK_Reference_10 foreign key (proId)
      references product (Id) on delete restrict on update restrict;

alter table product add constraint FK_Reference_14 foreign key (deptId)
      references department (Id) on delete restrict on update restrict;

alter table product add constraint FK_Reference_22 foreign key (prolineId)
      references productLine (Id) on delete restrict on update restrict;

alter table scheduleTab add constraint FK_Reference_16 foreign key (batchId)
      references batch (Id) on delete restrict on update restrict;

alter table staffDetail add constraint FK_Reference_18 foreign key (staffId)
      references Staff (Id) on delete restrict on update restrict;

alter table workForm add constraint FK_Reference_11 foreign key (proId)
      references product (Id) on delete restrict on update restrict;

alter table workForm add constraint FK_Reference_7 foreign key (batchId)
      references batch (Id) on delete restrict on update restrict;

alter table workForm add constraint FK_Reference_8 foreign key (staId)
      references Staff (Id) on delete restrict on update restrict;

alter table workForm add constraint FK_Reference_9 foreign key (procId)
      references process (Id) on delete restrict on update restrict;

alter table workHoursTab add constraint FK_Reference_17 foreign key (staId)
      references Staff (Id) on delete restrict on update restrict;

alter table workTab add constraint FK_Reference_13 foreign key (procId)
      references process (Id) on delete restrict on update restrict;

