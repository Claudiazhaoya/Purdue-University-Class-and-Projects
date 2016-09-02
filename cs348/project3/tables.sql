create table USERS(username varchar(50),password varchar(50));
create table ROLES(roleName varchar(50),encryptionKey integer);
create table USERROLES(username varchar(50), roleName varchar(50));
create table ROLEPRIVILEGES(roleName varchar(50), tableName varchar(50),privName varchar(50));
create table MOVIE(movie_title varchar(50),genre varchar(50),encryptedColumn integer, owner varchar(50));
create table AWARDS_EVENT(event_name varchar(50), venue varchar(50),encryptedColumn integer, owner varchar(50));
create table NOMINATION(event_name varchar(50), movie_title varchar(50), category varchar(50), won varchar(50),encryptedColumn integer, owner varchar(50));
