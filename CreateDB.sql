CREATE TABLE links(
	linkId serial primary key,
	rel text not null,
	href text not null 
);

CREATE TABLE countries(
	countryId serial primary key,
	name text not null,
	code text not null,
	phoneCode text not null
);

CREATE TABLE companies(
	companyId serial primary key,
	name text not null,
	fullCompanyName text not null,
	vatNumber text,
	links integer references links(linkId) not null
);

CREATE TABLE avatars(
	avatarId serial primary key,
	contentType text not null,
	url text not null,
	links integer references links(linkId) not null
);

CREATE TABLE users(
	userId serial primary key,
	title text not null,
	firstName text not null,
	lastName text not null,
	email text not null,
	phoneCode text,
	phoneNumber text,
	password text not null,
	tos text
);

CREATE TABLE userAccounts(
	userId serial primary key,
	title text not null,
	firstName text not null,
	lastName text not null,
	email text not null,
	phoneCode text,
	phoneNumber text,
	role integer not null,
	avatar integer references avatars(avatarId) not null,
	company integer references companies(companyId) not null
);

CREATE TABLE userLinks(
	linkId integer references links(linkId) not null,
	userId integer references userAccounts(userId) not null
);

CREATE TABLE billingAddresses(
	billingId serial primary key,
	userId integer references userAccounts(userId) not null,
	address text not null,
	streetName text not null,
	city text not null,
	zip text not null,
	country integer references countries(countryId) not null
);

CREATE TABLE billingLinks(
	linkId integer references links(linkId) not null,
	billingId integer references billingAddresses(billingId) not null
);

insert into links values(2, 'self', 'https://fleetch.com/api/v1/companies/12' );
insert into companies values(12, 'I-ways', 'I-ways d.o.o.', '121212121212',2);
insert into links values(1, 'self', 'https://fleetch.com/api/v1/image/23' );
insert into avatars values(23, 'image/jpeg', 'https://fleetch.com/upload/image/234klwncjnjk234',1);
insert into userAccounts values(22,'Mr.', 'John', 'Doe', 'john.doe@i-ways.hr', '+385', '9126565484', 1, 23, 12);
insert into countries values(1, 'Croatia', 'HR','385');
insert into billingAddresses values(34, 22,'Ilica 100', 'Ilica', 'Zagreb', '10000',1);