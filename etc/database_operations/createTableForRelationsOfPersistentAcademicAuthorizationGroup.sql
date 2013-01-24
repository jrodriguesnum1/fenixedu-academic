create table `ACADEMIC_AUTHORIZATION_PROGRAMS` (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP bigint unsigned, OID_ACADEMIC_PROGRAM bigint unsigned, primary key(OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP, OID_ACADEMIC_PROGRAM), index (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP), index (OID_ACADEMIC_PROGRAM)) ENGINE=InnoDB, character set utf8;
create table `ACADEMIC_AUTHORIZATION_OFFICES` (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP bigint unsigned, OID_ADMINISTRATIVE_OFFICE bigint unsigned, primary key(OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP, OID_ADMINISTRATIVE_OFFICE), index (OID_PERSISTENT_ACADEMIC_AUTHORIZATION_GROUP), index (OID_ADMINISTRATIVE_OFFICE)) ENGINE=InnoDB, character set utf8;
