
call mvn clean package 
rmdir C:\apache\apache-tomcat-7.0.41\webapps\my-app /s /q 
move target\my-app C:\apache\apache-tomcat-7.0.41\webapps
set CATALINA_HOME=C:\apache\apache-tomcat-7.0.41
C:\apache\apache-tomcat-7.0.41\bin\startup.bat