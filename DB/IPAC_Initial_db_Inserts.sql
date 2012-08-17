
-- 1) Load preset interface types

-- INTERFACE_TYPE
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('Main LAN', 'Public interface', FALSE, TRUE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('Private', 'Private interconnect interface', FALSE, TRUE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('Management', 'Management interface', FALSE, TRUE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('NetBackup', 'Interface dedicated to backup', FALSE, TRUE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('TEAM / BOND', 'Team or Bond interface - 2 or more physical', TRUE, FALSE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('NAT', 'NAT address interface', TRUE, TRUE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('Other', 'Other physical interface', FALSE, TRUE);
INSERT INTO interface_type ("name", "descr", "is_virtual", "is_selectable") VALUES ('VIP', 'Virtual interface', TRUE, TRUE);


-- 2) Insert preset data TODO: These will eventually be created via webgui

-- SITE
INSERT INTO site ("name", "notes", "date_created", "date_updated", "created_by", "updated_by") VALUES ('SITE-1', 'demo site', now(), now(), 'postgres', 'postgres');
INSERT INTO site ("name", "notes", "date_created", "date_updated", "created_by", "updated_by") VALUES ('SITE-2', 'another site', now(), now(), 'postgres', 'postgres');


-- SWITCH
INSERT INTO switch ("name", "descr", "model", "blade_count", "site_id", "date_created", "date_updated", "created_by", "updated_by") VALUES ('Core-A', 'Core switch', 'Cisco 6509', 9, 1, now(), now(), 'postgres', 'postgres');
INSERT INTO switch ("name", "descr", "model", "blade_count", "site_id", "date_created", "date_updated", "created_by", "updated_by") VALUES ('Core-Z', 'Another Core switch', 'Cisco 6509', 9, 1, now(), now(), 'postgres', 'postgres');

