FROM tomcat:8.5.54-jdk8-corretto

EXPOSE 8080

COPY "./target/*.war"  "/usr/local/tomcat/webapps/ROOT.war"

RUN chmod -R g=u /usr/local/tomcat/ \
   && chgrp -R 0 /usr/local/tomcat/

CMD ["catalina.sh", "run"]