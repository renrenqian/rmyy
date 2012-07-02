/* create online db */
create database if not exists hzhos default character set utf8;

/* drop the db user, if not existed it will have the ERROR 1396, not matter go on*/
drop user hzhos@'localhost';
flush privileges;
/* create the db user, if existed it will have the ERROR 1396, not matter go on*/
create user hzhos@'localhost' identified by 'sdhzhos';
FLUSH PRIVILEGES;

/* grant the privilege to the user*/
GRANT Super ON *.* TO 'hzhos'@'localhost';
GRANT Alter ON hzhos.* TO 'hzhos'@'localhost';
GRANT Create ON hzhos.* TO 'hzhos'@'localhost';
GRANT Create view ON hzhos.* TO 'hzhos'@'localhost';
GRANT Delete ON hzhos.* TO 'hzhos'@'localhost';
GRANT Drop ON hzhos.* TO 'hzhos'@'localhost';
GRANT Grant option ON hzhos.* TO 'hzhos'@'localhost';
GRANT Index ON hzhos.* TO 'hzhos'@'localhost';
GRANT Insert ON hzhos.* TO 'hzhos'@'localhost';
GRANT References ON hzhos.* TO 'hzhos'@'localhost';
GRANT Select ON hzhos.* TO 'hzhos'@'localhost';
GRANT Show view ON hzhos.* TO 'hzhos'@'localhost';
GRANT Update ON hzhos.* TO 'hzhos'@'localhost';
GRANT Create routine ON hzhos.* TO 'hzhos'@'localhost';
FLUSH PRIVILEGES;
