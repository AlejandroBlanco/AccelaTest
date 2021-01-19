# AccelaTest

The git repo file contains the sources, a small set of tests (I could have expanded it but this is already a bit late so probably no real reason) and a POM file to autogenerate an executable file by running a maven install command.

I have used an instance of MySQL to host the data. the SQL script used is:

CREATE TABLE IF NOT EXISTS AGENDA (
    id INT AUTO_INCREMENT,
    firstName VARCHAR(255),
    surname VARCHAR(255),
    PRIMARY KEY (id)
)  ENGINE=INNODB;

And the DB connection info is:

USER = "agenda"
PASS = "Agenda654App"
CONN = "jdbc:mysql://127.0.0.1:3306/agenda?useSSL=false&useUnicode=yes&"
+ "characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"

It will run any command passed in the console as expected, plus it will display an internal menu to keep running commands withoout leaving the application
