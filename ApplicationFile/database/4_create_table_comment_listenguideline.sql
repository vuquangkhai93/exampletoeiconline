use toeiconline;

create table listenguiline(
  listenguilineid bigint not null primary key auto_increment,
  title varchar(512) null,
  image varchar(255) null,
  content text null,
  createdday timestamp  null,
  modifiedday timestamp  null
);

create table comment(
  commentid bigint not null primary key auto_increment,
  content text null,
  userid bigint null,
  listenguilineid bigint null,
  createdday timestamp  null
);