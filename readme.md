### Maven liquibase plugin ###

Project has two profiles rebuild-db and run-exec 
 
 __rebuild-db__ - applies all changesets to DB (through liquibase maven plugin)
 __run-exec__ - runs application, which shows us information from DB
 
 For example:
 
 > mvn clean package -P rebuild-db,run-exec
 
 
