IPAC - IP Addressing and Connectivity
====

## Description

IPAC is a IP addressing and Connectivity enterprise management application designed for Network Operations or Datacentre management teams.

The application manages the IP addresses on multiple sites


## Pre-requisites

* JDK >=1.7.x
* PostgreSQL >=9.1
* Application servers - Does not require full JEE stack; Apache Tomcat >=7.0 and Jetty is fine and tested

## Contents

* /DB - contains database schema creation script and initial data inserts
* /WEBAPP - web application source roots

## Build instructions

More information to follow...


## Deployment instructions


More information to follow...



## Contributions

Please use the GitHub pull-request mechanism to submit contributions.

To get going:

1) <pre>git clone https://github.com/rob-murray/ipac.git</pre>
2) move to dir
3) <pre>mvn eclipse:eclipse -Dwtpversion=2.0</pre>
4) <pre>mvn jetty:run</pre>


## License

This project is available for use under the GNU General Public License.

For full details see LICENSE.md

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

