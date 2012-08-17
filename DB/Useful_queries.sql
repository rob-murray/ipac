
GRANT ALL ON ALL TABLES IN SCHEMA ipac TO ipacappuser;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA ipac TO ipacappuser;
GRANT ALL ON ALL SEQUENCES IN SCHEMA ipac TO ipacappuser;


--Find host and interface info for a subnet ID
SELECT host.id, host.name, interface.id, interface.name 
FROM host 
INNER JOIN interface ON (interface.host_id = host.id) 
WHERE interface.id IN 
(
SELECT interface_id 
FROM interface_ip 
WHERE subnet_id = 6
);


--List all IP addresses and interfaces and host for interfaces for subnet id
SELECT 
  interface_ip.id AS interfaceIpId,
  interface_ip.ip_address AS interfaceIpAddr,
  interface.id AS interfaceId,
  interface.name AS interfaceName,
  host.id AS hostId, 
  host.name AS hostName
FROM 
  public.host, 
  public.interface, 
  public.interface_ip
WHERE 
  interface.host_id = host.id AND
  interface_ip.interface_id = interface.id AND
  interface_ip.subnet_id = 6
ORDER BY interface_ip.ip_address ASC;

-- Select ALL Available IP addresses from subnet NOT INC .1 - .10 & .250 - .254
SELECT sub.ip FROM (SELECT set_masklen(((generate_series(1, (2 ^ (32 -
masklen('10.10.100.0/24'::cidr)))::integer - 2) +
'10.10.100.0/24'::cidr)::inet), 32) as ip) AS sub 
WHERE sub.ip NOT IN
(SELECT ip_address from interface_ip)
AND sub.ip > set_masklen('10.10.100.0/24', 32)+10
AND sub.ip < set_masklen(broadcast('10.10.100.0/24')::inet, 32)-5;



CREATE FUNCTION nextips_for(cidr) RETURNS SETOF inet AS
$$
SELECT sub.ip FROM (SELECT set_masklen(((generate_series(1, (2 ^ (32 -
masklen($1::cidr)))::integer - 2) +
$1::cidr)::inet), 32) as ip) AS sub 
WHERE sub.ip NOT IN
(SELECT ip_address from interface_ip)
AND sub.ip > set_masklen($1, 32)+10
AND sub.ip < set_masklen(broadcast($1)::inet, 32)-5;
$$ LANGUAGE SQL STABLE STRICT;

