# README for simpe CRUDE REST API 

This API uses calls and classes for *User collection* and *User account* implemented in [Fleetch API documentation](http://docs.fleetchapi.apiary.io/).

### Instalation and set up

For proper use of this API you should have Maven and PostgreSQL database instaled on your server.
Each of those can be downloaded from these links:

* [Maven download](http://maven.apache.org/download.cgi)
* [PostgreSQL download](https://www.postgresql.org/download/)

#### Maven tips

> Maven binary archive is all you need so look for **apache-maven-{version}-bin.tar.gz{zip}**. 
> Once you've extracted the zip(or tar.gz), you need to add its ./bin to your PATH variables.

#### PostgreSQL tips

> After installing the PostgreSQL onto your server, you can easily create database from avaliable [script](https://github.com/ljdivald/simple-API/blob/master/CreateDB.sql).

Once you've installed both Maven and PostgreSQL and with created database you are ready to go! 

Now all you need to do is compile and run your API.

### Compiling and running the API

position your self in downloaded folder of the API and run these commands:
``` mvn package && java -jar ./target/Task-0.0.1.jar ```
If everything went right your API should be up and running but if you were to come to any problems try using ``` mvn compile ``` before running previous line.
