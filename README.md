# docker_postgresql_withinVirtualboxUbuntu

How to setup postgresql within a docker container (in virtualbox/ubuntu) and use it from within a spring boot application (I'm not using the official postgresql docker image).<br/>
<br/>
Setup<br/>
1) download and install virtualbox 5.2<br/>
2) download an ISO image of ubuntu 16.04<br/>
3) create a vm in virtualbox based on that ubuntu 16.04 iso<br/>
4) within virtualbox/ubuntu, install jdk8 : sudo apt-get install openjdk-8-jdk<br/>
5) within virtualbox/ubuntu, create JAVA_HOME environment variable and add it to the PATH :<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64<br/>
&nbsp;&nbsp;&nbsp;&nbsp;- export PATH=$PATH:$JAVA_HOME/bin<br/>
6) within virtualbox/ubuntu, install docker : I personnally followed the docker.com tutorial https://docs.docker.com/engine/install/ubuntu/<br/>
7) within virtualbox/ubuntu, start a container that runs postgresql : sameersbn/postgresql:12-20200524 (this is not the official image but it does the job), run :sudo docker run --name postgresql -itd --restart always --publish 5432:5432 --volume postgresql:/var/lib/postgresql --env 'PG_TRUST_LOCALNET=true' --env 'PG_PASSWORD=postgres' --env 'DB_USER=thollem' --env 'DB_PASS=thollem' --env 'DB_NAME=mydatabase' sameersbn/postgresql:12-20200524 -c listen_addresses='*'<br/>
8) within virtualbox/ubuntu, run the spring boot client to store data in that postgresql database : mvn spring-boot:run<br/>
9) within virtualbox/ubuntu, to see the content in the postgresql database you can : <br/>
&nbsp;&nbsp;&nbsp;&nbsp;(a)rerun the application with 'mvn spring-boot:run' again <br/>
&nbsp;&nbsp;&nbsp;&nbsp;(b)use psql <br/>
&nbsp;&nbsp;&nbsp;&nbsp;(c)use dbeaver (I personnally use that)<br/>
![alt text](https://github.com/thomascolomba/docker_postgresql_withinVirtualboxUbuntu/blob/master/docker_postgresql_springboot_dbeaver_firstCustomer.png?raw=true)
<br/>
<br/>
bonus : access postgresql from the guest machine.<br/>
1) allow communication between guest and virtual machine : https://2buntu.com/articles/1513/accessing-your-virtualbox-guest-from-your-host-os/<br/>
2) edit the application.properties file : change spring.datasource.url value :<br/>
&nbsp;&nbsp;&nbsp;&nbsp;from spring.datasource.url=jdbc:postgresql://localhost:5432/postgres to spring.datasource.url=jdbc:postgresql://192.168.99.101:5432/postgres<br/>
<br/>
<br/>
some troubleshoot commands :<br/>
- java -version<br/>
- sudo service postgresql stop<br/>
- sudo docker stop postgresql<br/>
- sudo docker rm postgresql<br/>
