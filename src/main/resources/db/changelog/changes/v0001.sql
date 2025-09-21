--sequential shorturl generation with sequence starting from 1,000,000(10^6=7 digit)
create sequence SHORT_URL_SEQ start with 1000000;

create table tiny_url (
  short_url bigint not null default nextval('SHORT_URL_SEQ'),
  long_url varchar(2000) not null,
  primary key (short_url)
);