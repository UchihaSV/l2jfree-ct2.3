-- ---------------------------
-- Table structure for table `custom_npc`
-- ---------------------------
CREATE TABLE IF NOT EXISTS `custom_npc` (
  `id` decimal(11,0) NOT NULL DEFAULT '0',
  `idTemplate` int(11) NOT NULL DEFAULT '0',
  `name` varchar(200) DEFAULT NULL,
  `serverSideName` int(1) DEFAULT '0',
  `title` varchar(45) DEFAULT '',
  `serverSideTitle` int(1) DEFAULT '0',
  `class` varchar(200) DEFAULT NULL,
  `collision_radius` decimal(5,2) DEFAULT NULL,
  `collision_height` decimal(5,2) DEFAULT NULL,
  `level` decimal(2,0) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `attackrange` int(11) DEFAULT NULL,
  `hp` decimal(8,0) DEFAULT NULL,
  `mp` decimal(8,0) DEFAULT NULL,
  `hpreg` decimal(8,2) DEFAULT NULL,
  `mpreg` decimal(5,2) DEFAULT NULL,
  `str` decimal(7,0) DEFAULT NULL,
  `con` decimal(7,0) DEFAULT NULL,
  `dex` decimal(7,0) DEFAULT NULL,
  `int` decimal(7,0) DEFAULT NULL,
  `wit` decimal(7,0) DEFAULT NULL,
  `men` decimal(7,0) DEFAULT NULL,
  `exp` decimal(9,0) DEFAULT NULL,
  `sp` decimal(8,0) DEFAULT NULL,
  `patk` decimal(5,0) DEFAULT NULL,
  `pdef` decimal(5,0) DEFAULT NULL,
  `matk` decimal(5,0) DEFAULT NULL,
  `mdef` decimal(5,0) DEFAULT NULL,
  `atkspd` decimal(3,0) DEFAULT NULL,
  `aggro` decimal(6,0) DEFAULT NULL,
  `matkspd` decimal(4,0) DEFAULT NULL,
  `rhand` decimal(4,0) DEFAULT NULL,
  `lhand` decimal(4,0) DEFAULT NULL,
  `armor` decimal(1,0) DEFAULT NULL,
  `walkspd` decimal(3,0) DEFAULT NULL,
  `runspd` decimal(3,0) DEFAULT NULL,
  `faction_id` varchar(40) DEFAULT NULL,
  `faction_range` decimal(4,0) DEFAULT NULL,
  `isUndead` int(11) DEFAULT '0',
  `absorb_level` decimal(2,0) DEFAULT '0',
  `absorb_type` enum('FULL_PARTY','LAST_HIT','PARTY_ONE_RANDOM') DEFAULT 'LAST_HIT' NOT NULL,
  `ss` int(4) DEFAULT 0,
  `bss` int(4) DEFAULT 0,
  `ss_rate` int(3) DEFAULT 0,
  `AI` varchar(8) DEFAULT "fighter",
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT IGNORE `custom_npc` (`id`,`idTemplate`,`name`,`serverSideName`,`title`,`serverSideTitle`,`class`,`collision_radius`,`collision_height`,`level`,`sex`,`type`,`attackrange`,`hp`,`mp`,`hpreg`,`mpreg`,`str`,`con`,`dex`,`int`,`wit`,`men`,`exp`,`sp`,`patk`,`pdef`,`matk`,`mdef`,`atkspd`,`aggro`,`matkspd`,`rhand`,`lhand`,`armor`,`walkspd`,`runspd`,`faction_id`,`faction_range`,`isUndead`,`absorb_level`,`absorb_type`) VALUES
(6001, 31774, 'Beryl the Cat', 0, 'ItemMall', 1, 'Monster2.queen_of_cat', 8.00, 15.00, 70, 'female', 'L2Npc', 40, 3862, 1493, NULL, NULL, 40, 43, 30, 21, 20, 10, 0, 0, 1314, 470, 780, 382, 278, 0, 253, 0, 0, 0, 80, 120, NULL, 0, 0, 0,'LAST_HIT'),
(6002, 35461, 'Caska', 1, 'NPC Buffer', 1, 'NPC.a_teleporter_FHuman', 8.00, 25.00, 70, 'female', 'L2Npc', 40, 3862, 1494, NULL, NULL, 40, 43, 30, 21, 20, 10, 5879, 590, 1444, 514, 760, 381, 253, 0, 253, 0, 0, 0, 80, 120, NULL, NULL, 0, 0,'LAST_HIT'),
(7077, 31275, 'Tinkerbell', 1, 'Luxury Gatekeeper', 1, 'NPC.a_teleporter_FHuman', 8.00, 25.00, 70, 'female', 'L2Teleporter', 40, 3862, 1494, NULL, NULL, 40, 43, 30, 21, 20, 10, 5879, 590, 1444, 514, 760, 381, 253, 0, 253, 0, 0, 0, 80, 120, NULL, NULL, 0, 0,'LAST_HIT'),
(2001, 29020, 'Baium', 1, 'Event', 1, 'Monster.baium', 65.00, 174.00, 75, 'male', 'L2GrandBoss', 40, 790857, 3347, 668.78, 3.09, 60, 57, 73, 76, 35, 80, 10253400, 1081544, 6559, 6282, 4378, 4601, 333, 0, 2362, 0, 0, 0, 80, 120, NULL, 0, 0, 12,'LAST_HIT'),
(2002, 25319, 'Ember', 1, 'Event', 1, 'Monster2.inferno_drake_100_bi', 48.00, 73.00, 85, 'male', 'L2RaidBoss', 40, 257725, 3718, 823.48, 9.81, 60, 57, 73, 76, 35, 80, 2535975, 1356048, 11906, 5036, 18324, 2045, 409, 0, 2901, 0, 0, 0, 80, 120, NULL, 0, 0, 13,'LAST_HIT'),
(2003, 29022, 'Zaken', 1, 'Event', 1, 'Monster.zaken', 16.00, 32.00, 60, 'male', 'L2GrandBoss', 40, 858518, 1975, 799.68, 2.45, 60, 57, 73, 76, 35, 80, 4879745, 423589, 7273, 2951, 19762, 1197, 333, 0, 2362, 0, 0, 0, 80, 120, NULL, 0, 1, 12,'LAST_HIT'),
(30038, 30175, 'Andromeda', 1, 'Wedding Priest', 1, 'NPC.a_casino_FDarkElf', 8.00, 23.00, 70, 'female', 'L2WeddingManager', 40, 3862, 1493, 11.85, 2.78, 40, 43, 30, 21, 35, 10, 5879, 590, 1314, 470, 780, 382, 278, 0, 253, 0, 0, 0, 80, 120, NULL, 0, 1, 0,'LAST_HIT');

INSERT IGNORE `custom_npc` VALUES 
('16031', '16031', 'Little Angel Agathion', '0', '', '0', 'LineageNPC2.Agathion_AG', '32.00', '23.56', '70', 'male', 'L2Npc', '40', '2444', '2444', '0.00', '0.00', '10', '10', '10', '10', '10', '10', '0', '0', '500', '500', '500', '500', '253', '0', '253', '0', '0', null, '80', '120', 'NULL', '0', '0', '0', 'LAST_HIT', '0', '0', '0', 'balanced'), 
('16032', '16032', 'Little Devil Agathion', '0', '', '0', 'LineageNPC2.Agathion_ST', '32.00', '23.56', '70', 'male', 'L2Npc', '40', '2444', '2444', '0.00', '0.00', '10', '10', '10', '10', '10', '10', '0', '0', '500', '500', '500', '500', '253', '0', '253', '0', '0', null, '80', '120', 'NULL', '0', '0', '0', 'LAST_HIT', '0', '0', '0', 'balanced'), 
('16033', '16033', 'Rudolph Agathion', '0', '', '0', 'LineageNPC2.Agathion_RD', '32.00', '23.56', '70', 'male', 'L2Npc', '40', '2444', '2444', '0.00', '0.00', '10', '10', '10', '10', '10', '10', '0', '0', '500', '500', '500', '500', '253', '0', '253', '0', '0', null, '80', '120', 'NULL', '0', '0', '0', 'LAST_HIT', '0', '0', '0', 'balanced');