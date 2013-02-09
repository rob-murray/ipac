--
-- IPAC_Create_DB_Schema.sql
-- Desc: Creates the IPAC Postgres DB Schema
-- @see https://github.com/rob-murray/ipac


SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: ipac; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA ipac;


ALTER SCHEMA ipac OWNER TO postgres;

--
-- Name: SCHEMA ipac; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA ipac IS 'Schema for IPAC application';


--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = ipac, pg_catalog;

--
-- Name: nextips_for(cidr); Type: FUNCTION; Schema: ipac; Owner: postgres
--

CREATE FUNCTION nextips_for(cidr) RETURNS SETOF inet
    LANGUAGE sql STABLE STRICT
    AS $_$
SELECT sub.ip FROM (SELECT set_masklen(((generate_series(1, (2 ^ (32 -
masklen($1::cidr)))::integer - 2) +
$1::cidr)::inet), 32) as ip) AS sub 
WHERE sub.ip NOT IN
(SELECT ip_address from ipac.interface_ip)
AND sub.ip > set_masklen($1, 32)+10
AND sub.ip < set_masklen(broadcast($1)::inet, 32)-5;
$_$;


ALTER FUNCTION ipac.nextips_for(cidr) OWNER TO postgres;

--
-- Name: update_date_updated_column(); Type: FUNCTION; Schema: ipac; Owner: postgres
--

CREATE FUNCTION update_date_updated_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
NEW.date_updated = now(); 
RETURN NEW;
END;
$$;


ALTER FUNCTION ipac.update_date_updated_column() OWNER TO postgres;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: aud_host; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_host (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_by character varying(255),
    date_created timestamp without time zone,
    date_updated timestamp without time zone,
    name character varying(50),
    notes character varying(255),
    updated_by character varying(255),
    site_id integer
);


ALTER TABLE ipac.aud_host OWNER TO postgres;

--
-- Name: aud_interface; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_interface (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_by character varying(255),
    date_created timestamp without time zone,
    date_updated timestamp without time zone,
    name character varying(25),
    notes character varying(255),
    teamed_interface_id integer,
    type_id integer,
    updated_by character varying(255),
    host_id integer
);


ALTER TABLE ipac.aud_interface OWNER TO postgres;

--
-- Name: aud_interface_ip; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_interface_ip (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_by character varying(255),
    date_created timestamp without time zone,
    date_updated timestamp without time zone,
    interface_id integer,
    ip_address inet,
    subnet_id integer,
    updated_by character varying(255)
);


ALTER TABLE ipac.aud_interface_ip OWNER TO postgres;

--
-- Name: aud_interface_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_interface_type (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    descr character varying(255),
    is_selectable boolean,
    is_virtual boolean,
    name character varying(25)
);


ALTER TABLE ipac.aud_interface_type OWNER TO postgres;

--
-- Name: aud_site; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_site (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_by character varying(255),
    date_created timestamp without time zone,
    date_updated timestamp without time zone,
    name character varying(25),
    notes character varying(255),
    updated_by character varying(255)
);


ALTER TABLE ipac.aud_site OWNER TO postgres;

--
-- Name: aud_subnet; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_subnet (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_by character varying(255),
    date_created timestamp without time zone,
    date_updated timestamp without time zone,
    ip_address inet,
    updated_by character varying(255),
    vlan_id integer
);


ALTER TABLE ipac.aud_subnet OWNER TO postgres;

--
-- Name: aud_switchport; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_switchport (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    blade integer,
    chassis integer,
    interface_id integer,
    port integer,
    switch_id integer
);


ALTER TABLE ipac.aud_switchport OWNER TO postgres;

--
-- Name: aud_vlan; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE aud_vlan (
    id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_by character varying(255),
    date_created timestamp without time zone,
    date_updated timestamp without time zone,
    descr character varying(255),
    name character varying(25),
    routable boolean,
    sw_vlan_id integer,
    updated_by character varying(255),
    site_id integer
);


ALTER TABLE ipac.aud_vlan OWNER TO postgres;

--
-- Name: revinfo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE revinfo (
    rev integer NOT NULL,
    revtstmp bigint
);


ALTER TABLE ipac.revinfo OWNER TO postgres;


--
-- Name: host; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE host (
    id serial NOT NULL,
    name character varying(50) NOT NULL,
    notes character varying(255),
	site_id integer NOT NULL,
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
    created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
	updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.host OWNER TO postgres;

--
-- Name: Host_view; Type: VIEW; Schema: ipac; Owner: postgres
--

CREATE VIEW "Host_view" AS
    SELECT host.id, host.name, host.notes AS descr FROM host;


ALTER TABLE ipac."Host_view" OWNER TO postgres;

--
-- Name: interface; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE interface (
    id serial NOT NULL,
    host_id integer NOT NULL,
    type_id integer NOT NULL,
    name character varying(25) NOT NULL,
    notes character varying(255),
    teamed_interface_id integer DEFAULT 0 NOT NULL,
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
    created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
	updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.interface OWNER TO postgres;




--
-- Name: interface_ip; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE interface_ip (
    id serial NOT NULL,
    ip_address inet NOT NULL,
    subnet_id integer NOT NULL,
    interface_id integer NOT NULL,
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
    created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
	updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.interface_ip OWNER TO postgres;


--
-- Name: interface_type; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE interface_type (
    id serial NOT NULL,
    name character varying(25) NOT NULL,
    descr character varying(255),
    is_virtual boolean DEFAULT true NOT NULL,
    is_selectable boolean DEFAULT true NOT NULL,
	version integer DEFAULT 0
);


ALTER TABLE ipac.interface_type OWNER TO postgres;



--
-- Name: site; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE site (
    id serial NOT NULL,
    name character varying(25) NOT NULL,
    notes character varying(255),
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
	created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
	updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.site OWNER TO postgres;


--
-- Name: subnet; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE subnet (
    id serial NOT NULL,
    ip_address inet NOT NULL,
    vlan_id integer NOT NULL,
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
    created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
    updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.subnet OWNER TO postgres;

--
-- Name: switch; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE switch (
    id serial NOT NULL,
    name character varying(25) NOT NULL,
    descr character varying(255),
    model character varying(50) NOT NULL,
    blade_count integer NOT NULL,
	site_id integer NOT NULL,
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
    created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
	updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.switch OWNER TO postgres;


--
-- Name: switchport; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE switchport (
    id serial NOT NULL,
    switch_id integer NOT NULL,
    chassis integer,
    blade integer,
    port integer NOT NULL,
    interface_id integer NOT NULL,
	version integer DEFAULT 0
);


ALTER TABLE ipac.switchport OWNER TO postgres;

--
-- Name: vlan; Type: TABLE; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE TABLE vlan (
    id serial NOT NULL,
    name character varying(25) NOT NULL,
    descr character varying(255),
    sw_vlan_id integer NOT NULL,
	site_id integer NOT NULL,
    routable boolean DEFAULT true NOT NULL,
    date_created timestamp without time zone NOT NULL,
    date_updated timestamp without time zone,
    created_by character varying(25) DEFAULT 'admin'::character varying NOT NULL,
    updated_by character varying(25),
	version integer DEFAULT 0
);


ALTER TABLE ipac.vlan OWNER TO postgres;


CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ipac.hibernate_sequence OWNER TO postgres;


--
-- Name: aud_host_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_host
    ADD CONSTRAINT aud_host_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_interface_ip_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_interface_ip
    ADD CONSTRAINT aud_interface_ip_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_interface_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_interface
    ADD CONSTRAINT aud_interface_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_interface_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_interface_type
    ADD CONSTRAINT aud_interface_type_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_site_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_site
    ADD CONSTRAINT aud_site_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_subnet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_subnet
    ADD CONSTRAINT aud_subnet_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_switchport_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_switchport
    ADD CONSTRAINT aud_switchport_pkey PRIMARY KEY (id, rev);


--
-- Name: aud_vlan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY aud_vlan
    ADD CONSTRAINT aud_vlan_pkey PRIMARY KEY (id, rev);
	
--
-- Name: revinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (rev);

--
-- Name: host_name_uniq; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY host
    ADD CONSTRAINT host_name_uniq UNIQUE (name);


--
-- Name: host_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY host
    ADD CONSTRAINT host_pk PRIMARY KEY (id);


--
-- Name: interface_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY interface
    ADD CONSTRAINT interface_pk PRIMARY KEY (id);


--
-- Name: inttype_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY interface_type
    ADD CONSTRAINT inttype_pk PRIMARY KEY (id);


--
-- Name: ipaddress_id_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY interface_ip
    ADD CONSTRAINT ipaddress_id_pk PRIMARY KEY (id);


--
-- Name: site_id_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_id_pk PRIMARY KEY (id);


--
-- Name: subnet_id_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subnet
    ADD CONSTRAINT subnet_id_pk PRIMARY KEY (id);


--
-- Name: switch_id_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY switch
    ADD CONSTRAINT switch_id_pk PRIMARY KEY (id);


--
-- Name: switchport_id_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY switchport
    ADD CONSTRAINT switchport_id_pk PRIMARY KEY (id);


--
-- Name: vlan_id_pk; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY vlan
    ADD CONSTRAINT vlan_id_pk PRIMARY KEY (id);


--
-- Name: vlan_swid_uniq; Type: CONSTRAINT; Schema: ipac; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY vlan
    ADD CONSTRAINT vlan_swid_uniq UNIQUE (sw_vlan_id);
	
	--
-- Name: fk154e822adf74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_interface
    ADD CONSTRAINT fk154e822adf74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);

--
-- Name: fk2e80882fdf74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_interface_type
    ADD CONSTRAINT fk2e80882fdf74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);

	--
-- Name: fk6a0441f7df74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_host
    ADD CONSTRAINT fk6a0441f7df74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);
	
--
-- Name: fk6a092b96df74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_site
    ADD CONSTRAINT fk6a092b96df74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);

--
-- Name: fk6a0a91b2df74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_vlan
    ADD CONSTRAINT fk6a0a91b2df74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);


--
-- Name: fk7b1ec35cdf74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_interface_ip
    ADD CONSTRAINT fk7b1ec35cdf74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);


--
-- Name: fk926f375de95b8f76; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subnet
    ADD CONSTRAINT fk926f375de95b8f76 FOREIGN KEY (vlan_id) REFERENCES vlan(id);

--
-- Name: fkd0db3ecdf74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_subnet
    ADD CONSTRAINT fkd0db3ecdf74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);

--
-- Name: fkdf231ec4df74e053; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aud_switchport
    ADD CONSTRAINT fkdf231ec4df74e053 FOREIGN KEY (rev) REFERENCES revinfo(rev);	


--
-- Name: host_name_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX host_name_indx ON host USING btree (upper((name)::text));


--
-- Name: interface_hostid_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX interface_hostid_indx ON interface USING btree (host_id);


--
-- Name: interface_ip_intid_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX interface_ip_intid_indx ON interface_ip USING btree (interface_id);


--
-- Name: interface_ip_ip_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX interface_ip_ip_indx ON interface_ip USING btree (ip_address);


--
-- Name: interface_ip_subnet_id_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX interface_ip_subnet_id_indx ON interface_ip USING btree (subnet_id);


--
-- Name: interface_teamedinterfaceid_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX interface_teamedinterfaceid_indx ON interface USING btree (teamed_interface_id);


--
-- Name: interface_type_id_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX interface_type_id_indx ON interface_type USING btree (id);


--
-- Name: subnet_vlanid_indx; Type: INDEX; Schema: ipac; Owner: postgres; Tablespace: 
--

CREATE INDEX subnet_vlanid_indx ON subnet USING btree (vlan_id);


--
-- Name: host_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER host_tsupd_tr BEFORE UPDATE ON host FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: interface_ip_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER interface_ip_tsupd_tr BEFORE UPDATE ON interface_ip FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: interface_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER interface_tsupd_tr BEFORE UPDATE ON interface FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: site_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER site_tsupd_tr BEFORE UPDATE ON site FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: subnet_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER subnet_tsupd_tr BEFORE UPDATE ON subnet FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: switch_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER switch_tsupd_tr BEFORE UPDATE ON switch FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: vlan_tsupd_tr; Type: TRIGGER; Schema: ipac; Owner: postgres
--

CREATE TRIGGER vlan_tsupd_tr BEFORE UPDATE ON vlan FOR EACH ROW EXECUTE PROCEDURE update_date_updated_column();


--
-- Name: int_host_id_fk; Type: FK CONSTRAINT; Schema: ipac; Owner: postgres
--

ALTER TABLE ONLY interface
    ADD CONSTRAINT int_host_id_fk FOREIGN KEY (host_id) REFERENCES host(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: interface_type_id_fk; Type: FK CONSTRAINT; Schema: ipac; Owner: postgres
--

ALTER TABLE ONLY interface
    ADD CONSTRAINT interface_type_id_fk FOREIGN KEY (type_id) REFERENCES interface_type(id);
	
ALTER TABLE ONLY host
    ADD CONSTRAINT host_site_id_fk FOREIGN KEY (site_id) REFERENCES site(id);	
		

ALTER TABLE ONLY switch
    ADD CONSTRAINT switch_site_id_fk FOREIGN KEY (site_id) REFERENCES site(id);
	
ALTER TABLE ONLY vlan
    ADD CONSTRAINT vlan_site_id_fk FOREIGN KEY (site_id) REFERENCES site(id);	
--
-- Name: interfaceip_intid_fk; Type: FK CONSTRAINT; Schema: ipac; Owner: postgres
--

ALTER TABLE ONLY interface_ip
    ADD CONSTRAINT interfaceip_intid_fk FOREIGN KEY (interface_id) REFERENCES interface(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ipaddress_subnet_id_fk; Type: FK CONSTRAINT; Schema: ipac; Owner: postgres
--

ALTER TABLE ONLY interface_ip
    ADD CONSTRAINT ipaddress_subnet_id_fk FOREIGN KEY (subnet_id) REFERENCES subnet(id);


--
-- Name: subnet_vlan_id_fk; Type: FK CONSTRAINT; Schema: ipac; Owner: postgres
--

ALTER TABLE ONLY subnet
    ADD CONSTRAINT subnet_vlan_id_fk FOREIGN KEY (vlan_id) REFERENCES vlan(id);


--
-- Name: switchport_int_id_fk; Type: FK CONSTRAINT; Schema: ipac; Owner: postgres
--

ALTER TABLE ONLY switchport
    ADD CONSTRAINT switchport_int_id_fk FOREIGN KEY (interface_id) REFERENCES interface(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ipac; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA ipac FROM PUBLIC;
REVOKE ALL ON SCHEMA ipac FROM postgres;
GRANT ALL ON SCHEMA ipac TO postgres;
GRANT ALL ON SCHEMA ipac TO PUBLIC;



--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: nextips_for(cidr); Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON FUNCTION nextips_for(cidr) FROM PUBLIC;
REVOKE ALL ON FUNCTION nextips_for(cidr) FROM postgres;
GRANT ALL ON FUNCTION nextips_for(cidr) TO postgres;
GRANT ALL ON FUNCTION nextips_for(cidr) TO PUBLIC;





--
-- Name: host; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE host FROM PUBLIC;
REVOKE ALL ON TABLE host FROM postgres;
GRANT ALL ON TABLE host TO postgres;




--
-- Name: interface; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE interface FROM PUBLIC;
REVOKE ALL ON TABLE interface FROM postgres;
GRANT ALL ON TABLE interface TO postgres;



--
-- Name: interface_ip; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE interface_ip FROM PUBLIC;
REVOKE ALL ON TABLE interface_ip FROM postgres;
GRANT ALL ON TABLE interface_ip TO postgres;

--
-- Name: interface_type; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE interface_type FROM PUBLIC;
REVOKE ALL ON TABLE interface_type FROM postgres;
GRANT ALL ON TABLE interface_type TO postgres;


--
-- Name: subnet; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE subnet FROM PUBLIC;
REVOKE ALL ON TABLE subnet FROM postgres;
GRANT ALL ON TABLE subnet TO postgres;




--
-- Name: switch; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE switch FROM PUBLIC;
REVOKE ALL ON TABLE switch FROM postgres;
GRANT ALL ON TABLE switch TO postgres;



--
-- Name: switchport; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE switchport FROM PUBLIC;
REVOKE ALL ON TABLE switchport FROM postgres;
GRANT ALL ON TABLE switchport TO postgres;



--
-- Name: vlan; Type: ACL; Schema: ipac; Owner: postgres
--

REVOKE ALL ON TABLE vlan FROM PUBLIC;
REVOKE ALL ON TABLE vlan FROM postgres;
GRANT ALL ON TABLE vlan TO postgres;


-- END
