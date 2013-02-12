IPAC - IP Addressing and Connectivity
====

Description
-------

###### IPAC stands for IP addressing and Connectivity - It is a web application that manages IP addresses and host connectivity.


IPAC is a IP addressing and Connectivity enterprise management application designed for Network Operations or Datacentre management teams.

The application manages the IP addresses on multiple sites...

#### Features

Here are some of the features available

* Site based architecture
* Multiple VLANs per site
* Hosts with configurable interfaces
* Manage subnets
* Set host IP address or get the next available
* User authentication, control access to sections
* Audit trail of activity and changes

#### How to get started

To use this application we need a few items:

* Postgres database
* Java application server
* JDK to build the deployable artifact

For more information about using this application see [Installation](#installation) to install or [Usage Intructions](ipac/tree/master/Usage_Instructions.md)


If you wish to contribute and enhance this application the please see the [Development](#development) section.



Contents
-------

Within this project there are the following sections:

* [Usage Intructions](ipac/tree/master/Usage_Instructions.md) - A guide to using the application
* /DB - Database schema creation scripts and initial data insert statements
* /WEBAPP - Web application source directory


Getting started
-------

### Installation


More information to follow...


#### Pre-requisites

* JDK >=1.7.x
* PostgreSQL >=9.1
* Application servers - Does not require full JEE stack; Apache Tomcat >=7.0 and Jetty is fine and tested
* Apache Maven ~3


#### Build instructions

More information to follow...



### Development


To get going with development (I am assuming you are using eclipse):

1. `git clone https://github.com/rob-murray/ipac.git`
2. cd to dir
3. `mvn eclipse:eclipse -Dwtpversion=2.0`

To run with Jetty; `mvn jetty:run`

Please use the GitHub pull-request mechanism to submit contributions.


License
-------

This project is available for use under the GNU General Public License.

For full details see [LICENSE](ipac/tree/master/LICENSE.md)

IPAC is a IP addressing and Connectivity enterprise management 
application designed for Network Operations or Datacentre management teams.
Copyright (C) 2013  robert murray

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

