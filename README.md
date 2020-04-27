This repository contains code to create two Spring Boot sample applications to demonstrate how containers are created and executed. Each application comes with a Jenkinsfile to help aid in rapid build and deployment. 

Openshift-Sample-Frontend
- A simple application that hosts a single web page able to communicate to the Frontend container. The Frontend container has a small set of API calls that allow communication to happen between the Frontend and Backend containers.

Openshift-Sample-Backend
- A simple application that accepts calls from the Frontend container and performs Database queries and actions. The Backend has no route to accept traffic from external sources.

Database
- A MySQL instance created in Openshift. 
- oc process mysql-ephemeral -n openshift -p MYSQL_USER=sampleuser -p MYSQL_PASSWORD=password123 -p MYSQL_DATABASE=applicantdb | oc create -f -


