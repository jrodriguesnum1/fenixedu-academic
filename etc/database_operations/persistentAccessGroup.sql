create table `PERSISTENT_ACCESS_GROUP` (
	`OID` bigint unsigned,
	`OID_OLD_GROUP` bigint unsigned,
	`OID_DELETED_ROOT_DOMAIN_OBJECT` bigint unsigned,
	`OID_CREATOR` bigint unsigned,
	`OID_NEW_GROUP` bigint unsigned,
	`OPERATION` text,
	`CREATED` timestamp NULL default NULL,
	`OJB_CONCRETE_CLASS` varchar(255) NOT NULL DEFAULT '',
	`OID_ROOT_DOMAIN_OBJECT` bigint unsigned,
	`ID_INTERNAL` int(11) NOT NULL auto_increment,
	primary key (ID_INTERNAL),
	index (OID),
	index (OID_DELETED_ROOT_DOMAIN_OBJECT),
	index (OID_CREATOR),
	index (OID_ROOT_DOMAIN_OBJECT)
) ENGINE=InnoDB, character set utf8;

create table `PERSISTENT_ACCESS_GROUP_MEMBERS` (
	`OID_PERSISTENT_ACCESS_GROUP` bigint unsigned,
	`OID_PARTY` bigint unsigned,
	primary key (OID_PERSISTENT_ACCESS_GROUP, OID_PARTY),
	index (OID_PERSISTENT_ACCESS_GROUP),
	index (OID_PARTY)
) ENGINE=InnoDB, character set utf8;

create table `ACADEMIC_AUTHORIZATION_OFFICES` (
	`OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP` bigint unsigned,
	`OID_ADMINISTRATIVE_OFFICE` bigint unsigned,
	primary key (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP, OID_ADMINISTRATIVE_OFFICE),
	index (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP),
	index (OID_ADMINISTRATIVE_OFFICE)
) ENGINE=InnoDB, character set utf8;

create table `ACADEMIC_AUTHORIZATION_PROGRAMS` (
	`OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP` bigint unsigned,
	`OID_ACADEMIC_PROGRAM` bigint unsigned,
	primary key (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP, OID_ACADEMIC_PROGRAM),
	index (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP),
	index (OID_ACADEMIC_PROGRAM)
) ENGINE=InnoDB, character set utf8;

alter table `SERVICE_AGREEMENT_TEMPLATE` drop key OID_ADMINISTRATIVE_OFFICE, drop OID_ADMINISTRATIVE_OFFICE;
