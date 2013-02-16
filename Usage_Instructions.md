IPAC Usage Instructions
====


Overview
-------


This is what it does…


#### Relationship


Here is how the objects in the IPAC are related to each other in the database and what they represent in the real world.

###### Attributes

All entities have the following attributes

1. created by - The username that created the object
2. updated by - The username that updated the object (if any)
3. date created - The date when the object was created
4. date updated - The date when the object was updated (if any)

##### Host

A **Host** is any peice of networked equipment that needs to be in the IPAC, for example a server, switch, router - anything that has an interface to your network.

###### Attributes

1. name
2. notes


*@has_many*:
* **Interface**

*@belongs_to*:
* **Site**


##### Interface

A **Interface** is a physical or virtual interface on a host. A server could have NIC1, NIC2 and iLO and a teamed interface of NIC1 and NIC2 - these are all instances of **Interface**

###### Attributes

1. name
2. notes


*@has_one*:
* **InterfaceType**
* **InterfaceIp**

*@belongs_to*:
* **Host**
* **Vlan**


##### InterfaceIp

A **InterfaceIp** is an IP Address 3. created by
4. updated by
5. date created
6. date updatedand is assigned to an Interface whilst being on a subnet.

###### Attributes

1. IP address

*@belongs_to*:
* **Interface**
* **Subnet**

##### InterfaceType

A **InterfaceType** is a list of types defining the use of an **Interface**. Examples are Main LAN, management

###### Attributes

1. name
2. desc
3. is_virtual
4. is_selectable

*@belongs_to*:
* **Interface**


##### Site

A **Site** is a top level entity, it is a location of a network such as an office or datacentre. You can have many of these to represent each distinct network but they do not have to interact.

###### Attributes

1. name
2. notes

*@has_many*:
* **Host**
* **Vlan**
* **Switch**


##### Subnet

A **Subnet** is a block of IP addresses, it can be a specific network address of range. All references are in CIDR notation.

###### Attributes

1. IP address

*@has_many*:
* **InterfaceIp**

*@belongs_to*:
* **Vlan**

##### Switch

A **Switch** is a networking 

###### Attributes

1. name
2. desc
3. model
4. number of blades

*@has_many*:
* **Switchport**

*@belongs_to*:
* **Site*

##### Switchport

A **Switchport** is a physical connection between two networked devices at the switch end - the opposite to the **Interface**

###### Attributes

1. chassis
2. blade
3. port

*@belongs_to*:
* **Switch**
* **Interface**


##### Vlan

A **Vlan** is as in networking terms a virtual LAN - it is a segredation of subnets and as such can contain many subnets

###### Attributes

1. name
2. desc
3. VLAN ID
4. routeable

*@has_many*:
* **Subnet**

*@belongs_to*:
* **Site**

#### How to…

For each object describe how to create/update/delete

