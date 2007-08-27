-- ---------------------------
-- Table structure for `random_spawn_loc`
-- ---------------------------
DROP TABLE IF EXISTS `random_spawn_loc`;
CREATE TABLE `random_spawn_loc` (
  `groupId` int(11) NOT NULL default '0',
  `x` int(11) NOT NULL default '0',
  `y` int(11) NOT NULL default '0',
  `z` int(11) NOT NULL default '0',
  `heading` int(11) NOT NULL default '-1',
  PRIMARY KEY  (`groupId`,`x`,`y`,`z`,`heading`)
) DEFAULT CHARSET=utf8;

INSERT INTO `random_spawn_loc` VALUES 
(1,151680,-174891,-1782,-1),
(1,154153,-220105,-3402,-1),
(1,178834,-184336,-355,-1),
(2,-12345,121695,-2984,0),
(3,81360,150016,-3528,16000),
(4,120493,76520,-2136,35500),
(5,-84745,151732,-3128,50000),
(6,113481,218450,-3624,33000),
(7,80129,56947,-1552,32500),
(8,146986,29566,-2271,32500),
(9,183280,-11905,-4904,-1),
(10,185662,-13093,-5498,-1),
(11,-52172,78884,-4741,-1),
(11,-41350,209876,-5087,-1),
(11,-21657,77164,-5173,-1),
(11,45029,123802,-5413,-1),
(11,83175,208998,-5439,-1),
(11,111337,173804,-5439,-1),
(11,118343,132578,-4831,-1),
(11,172373,-17833,-4901,-1),
(12,-19360,13278,-4901,-1),
(12,-53131,-250502,-7909,-1), -- Heretic (undersea)
(12,46303,170091,-4981,-1),
(12,-20485,-251008,-8165,-1), -- Apostate (undersea)
(12,12669,-248698,-9581,-1), -- Forbidden Path (undersea)
(12,140519,79464,-5429,-1),
(13,-84356,152742,-3204,-1),
(14,-83157,149846,-3155,-1),
(15,-79563,152726,-3204,-1),
(16,-79123,155098,-3204,-1),
(17,-16168,124129,-3143,-1),
(18,-14563,121461,-3015,-1),
(19,-14199,126128,-3171,-1),
(20,-12344,123605,-3132,-1),
(21,16901,144748,-3027,-1),
(22,17557,147084,-3150,-1),
(23,19038,142923,-3078,-1),
(24,20826,145769,-3171,-1),
(25,77830,148627,-3623,-1),
(26,81540,144204,-3559,-1),
(27,81556,152183,-3559,-1),
(28,83872,143882,-3431,-1),
(29,85328,147352,-3431,-1),
(30,114907,77916,-2662,-1),
(31,115509,74857,-2625,-1),
(32,118273,74592,-2529,-1),
(33,107251,218166,-3701,-1),
(34,115482,219266,-3689,-1),
(35,80039,54291,-1586,-1),
(36,81002,53495,-1586,-1),
(37,81527,56009,-1551,-1),
(38,143926,26468,-2338,-1),
(39,147460,20537,-2101,-1),
(40,150417,25182,-2141,-1),
(41,-83107,150270,-3155,-1),
(42,-83070,152740,-3204,-1),
(43,-80752,152738,-3204,-1),
(44,-80690,149996,-3070,-1),
(45,-80037,154344,-3204,-1),
(46,-15549,124170,-3143,-1),
(47,-14480,122330,-3126,-1),
(48,-14268,124941,-3156,-1),
(49,-13252,123689,-3143,-1),
(50,-12591,122673,-3142,-1),
(51,15722,142877,-2732,-1),
(52,18172,145740,-3140,-1),
(53,19096,143980,-3096,-1),
(54,19823,145863,-3142,-1),
(55,79825,148619,-3559,-1),
(56,81535,146866,-3559,-1),
(57,81547,150347,-3559,-1),
(58,83319,148054,-3431,-1),
(59,83894,147495,-3431,-1),
(60,115893,77551,-2729,-1),
(61,115936,75382,-2625,-1),
(62,117098,77066,-2720,-1),
(63,118049,75783,-2715,-1),
(64,107256,218615,-3701,-1),
(65,109660,217339,-3775,-1),
(66,110189,221393,-3569,-1),
(67,111389,219257,-3572,-1),
(68,113479,217795,-3731,-1),
(69,114086,220214,-3568,-1),
(70,80416,55959,-1586,-1),
(71,80617,54116,-1586,-1),
(72,82048,55417,-1551,-1),
(73,82863,53290,-1522,-1),
(74,145015,25269,-2167,-1),
(75,145394,27629,-2295,-1),
(76,146616,25816,-2039,-1),
(77,147440,30047,-2487,-1),
(78,147459,21148,-2167,-1),
(79,148013,27029,-2231,-1),
(80,149515,27641,-2295,-1),
(81,149899,24719,-2167,-1),
(82,-41312,206625,-3412,-1),
(83,-41312,206625,-3412,-1),
(84,-55428,79357,-3059,-1),
(85,-55428,79357,-3059,-1),
(86,-24895,77634,-3495,-1),
(87,-24895,77634,-3495,-1),
(88,-22594,13760,-3216,-1),
(89,-22594,13760,-3216,-1),
(90,43074,170560,-3298,-1),
(91,43074,170560,-3298,-1),
(92,39872,144193,-3707,-1),
(93,39872,144193,-3707,-1),
(94,45504,127550,-3734,-1),
(95,45504,127550,-3734,-1),
(96,79946,209470,-3760,-1),
(97,79946,209470,-3760,-1),
(98,108097,174274,-3769,-1),
(99,108097,174274,-3769,-1),
(100,115136,133057,-3161,-1),
(101,115136,133057,-3161,-1),
(102,74561,78656,-3446,-1),
(103,74561,78656,-3446,-1),
(104,110784,84800,-4867,-1),
(105,110784,84800,-4867,-1),
(106,137278,79936,-3751,-1),
(107,137278,79936,-3751,-1),
(108,169152,-17344,-3228,-1),
(109,169152,-17344,-3228,-1),
(110,184410,-10111,-5488,-1), -- Lilith (80)
(111,185000,-13000,-5488,-1), -- Anakim (80)
(112,171708,43964,-4972,-1);
INSERT INTO `random_spawn_loc` VALUES
(113,147943,-56176,-2781,-1),   
(114,148064,-56288,-2781,-1),   
(115,147703,-58879,-2981,-1),   
(116,147704,-58710,-2981,-1),   
(117,150550,-57471,-2981,-1),   
(118,150425,-57370,-2981,-1),   
(119,144841,-57493,-2981,-1),   
(120,144980,-57403,-2981,-1),   
(121,148867,-58156,-2981,-1),   
(122,149180,-58022,-2981,-1),   
(123,87390,-140065,-1536,-1),   
(124,87302,-140189,-1536,-1),   
(125,87363,-142460,-1336,-1),   
(126,87500,-142523,-1336,-1),   
(127,89738,-141420,-1536,-1),   
(128,89661,-141242,-1536,-1),   
(129,43961,-50913,-792,-1),     
(130,43859,-50954,-796,-1),     
(131,38588,-48222,896,-1),      
(132,38695,-48308,896,-1),
-- Runaway Youth quest
(133,118600,-161235,-1119,-1),
(133,108380,-150268,-2376,-1),
(133,123254,-148126,-3425,-1),
-- Wild Maiden quest
(134,66578,72351,-3731,-1),
(134,67994,79605,-3685,-1),
(134,72414,89582,-3068,-1),
-- Blacksmith of wind Rooney
(135,179815,-115465,-3598,-1),
(135,178091,-119661,-4087,-1),
(135,190447,-117063,-3280,-1),
(135,189472,-111287,-3280,-1),
(135,183442,-111453,-3615,-1),
(135,175916,-113220,-3471,-1),
(135,178880,-112665,-5821,-1),
(135,181809,-109360,-5830,-1),
(135,186321,-109182,-5865,-1),
(135,184699,-116825,-6102,-1),
(135,180717,-116163,-6102,-1);