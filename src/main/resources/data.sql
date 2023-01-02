INSERT INTO public.company (id,address,description,name,nif,phone) VALUES
	 (14,'Calle empresa 1','Descripcion 1','Empresa1','987654321A','987654'),
	 (15,'Calle empresa 2','Descripcion 2','Empresa2','54635','2343242567'),
	 (16,'Calle empresa 3','Descripcion 3','Empresa3','123456789P','324324'),
	 (177,'Calle empresa 4','Descripcion 4','Empresa4','123465','3432'),
	 (337,'Calle empresa 5','Descripcion 5','Empresa5','6768','3432');
	 
INSERT INTO public.machine (id,serial_number,company_id) VALUES
	 (66,'1',15),
	 (67,'2',15),
	 (62,'3',14),
	 (63,'4',14),
	 (64,'5',14),
	 (65,'6',14),
	 (90,'7',15),
	 (91,'8',15);

INSERT INTO public."user" (tipo,id,address,email,name,phone,experience_years,weld_mode,company_id,"password") VALUES
	 ('Manager',282,'Calle user 1','user1@user.es','Manager1','1111111',2,NULL,14,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam'),
	 ('Welder',104,'Calle user 2','user2@user.es','Welder1','222222',2,'1',14,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam'),
	 ('Manager',52,'Calle user 3','user3@user.es','Manager2','33333',4,NULL,14,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam'),
	 ('Welder',97,'Calle user 4','user4@user.es','Welder2','444444',2,'0',15,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam'),
	 ('User',36,'Calle user 5','user5@user.es','User1','5555555',NULL,NULL,NULL,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam'),
	 ('User',37,'Calle user 6','user6@user.es','User2','666666',NULL,NULL,NULL,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam'),
	 ('Manager',35,'Calle user 7','user7@user.es','Manager3','777777',5,NULL,16,'$2a$12$tIcgHwUV2mElfDc9/.T5Z.r.FiGpUoJ2pSfCyWcq0.BwCg44owPam');
 
	
INSERT INTO public.project (id,description,name,manager_id) VALUES
	 (54,'descripcion proyecto 1','proyecto 1',52),
	 (53,'descripcion proyecto 2','proyecto 2',52),
	 (57,'descripcion proyecto 3','proyecto 3',52),
	 (283,'descripcion proyecto 4','proyecto 4',282),
	 (339,'descripcion proyecto 5','proyecto 5',282),
	 (344,'descripcion proyecto 6','proyecto 6',282);

INSERT INTO public.project_machine (machine_id,project_id) VALUES
	 (62,283),
	 (63,283),
	 (65,339),
	 (62,344);

INSERT INTO public.piece (id,name,observaciones,project_machine_machine_id,project_machine_project_id,welder_id) VALUES
	 (2,'pieza 1','pieza proyecto 4',62,283,104),
	 (14,'pieza 2','proyecto 4',63,283,104),
	 (15,'pieza 5','proyecto 5',65,339,104);

INSERT INTO public.weld (id,amp,note,state,tolerance,volt,piece_id) VALUES
	 (3,105.0,'','FINALIZADA',5,21.0,2),
	 (17,115.0,'','FINALIZADA',2,22.0,2),
	 (16,103.0,'','FINALIZADA',5,20.5,15),
	 (39,105.0,'','FINALIZADA',10,20.5,14);

INSERT INTO public.reading (id,amp,"date",volt,weld_id) VALUES
	 (4,100.0,'2023-01-01 01:30:54.23',20.0,3),
	 (5,100.0,'2023-01-01 01:30:55.237',20.0,3),
	 (6,100.0,'2023-01-01 01:30:56.239',20.0,3),
	 (7,100.0,'2023-01-01 01:30:57.241',20.0,3),
	 (8,100.0,'2023-01-01 01:30:58.243',20.0,3),
	 (9,100.0,'2023-01-01 01:30:59.245',20.0,3),
	 (10,100.0,'2023-01-01 01:31:00.247',20.0,3),
	 (11,100.0,'2023-01-01 01:31:01.249',20.0,3),
	 (12,100.0,'2023-01-01 01:31:02.25',20.0,3),
	 (13,100.0,'2023-01-01 01:31:03.252',20.0,3);
INSERT INTO public.reading (id,amp,"date",volt,weld_id) VALUES
	 (18,100.0,'2023-01-01 01:36:20.709',20.0,17),
	 (19,100.0,'2023-01-01 01:36:21.71',20.0,17),
	 (20,100.0,'2023-01-01 01:36:22.711',20.0,17),
	 (21,100.0,'2023-01-01 01:36:23.713',20.0,17),
	 (22,100.0,'2023-01-01 01:36:24.714',20.0,17),
	 (23,100.0,'2023-01-01 01:36:25.716',20.0,17),
	 (24,100.0,'2023-01-01 01:36:26.718',20.0,17),
	 (25,100.0,'2023-01-01 01:36:27.719',20.0,17),
	 (26,100.0,'2023-01-01 01:36:28.721',20.0,17),
	 (27,100.0,'2023-01-01 01:36:29.723',20.0,17);
INSERT INTO public.reading (id,amp,"date",volt,weld_id) VALUES
	 (29,100.0,'2023-01-01 01:36:34.207',20.0,16),
	 (30,100.0,'2023-01-01 01:36:35.208',20.0,16),
	 (31,100.0,'2023-01-01 01:36:36.21',20.0,16),
	 (32,100.0,'2023-01-01 01:36:37.211',20.0,16),
	 (33,100.0,'2023-01-01 01:36:38.212',20.0,16),
	 (34,100.0,'2023-01-01 01:36:39.214',20.0,16),
	 (35,100.0,'2023-01-01 01:36:40.215',20.0,16),
	 (36,100.0,'2023-01-01 01:36:41.217',20.0,16),
	 (37,100.0,'2023-01-01 01:36:42.218',20.0,16),
	 (38,100.0,'2023-01-01 01:36:43.219',20.0,16);
INSERT INTO public.reading (id,amp,"date",volt,weld_id) VALUES
	 (40,100.0,'2023-01-01 23:51:36.461',20.0,39),
	 (41,100.0,'2023-01-01 23:51:37.469',20.0,39),
	 (42,100.0,'2023-01-01 23:51:38.471',20.0,39),
	 (43,100.0,'2023-01-01 23:51:39.473',20.0,39),
	 (44,100.0,'2023-01-01 23:51:40.474',20.0,39),
	 (45,100.0,'2023-01-01 23:51:41.476',20.0,39),
	 (46,100.0,'2023-01-01 23:51:42.478',20.0,39),
	 (47,100.0,'2023-01-01 23:51:43.48',20.0,39),
	 (48,100.0,'2023-01-01 23:51:44.482',20.0,39),
	 (49,100.0,'2023-01-01 23:51:45.483',20.0,39);

INSERT INTO public.alarm (id,info,name,weld_id) VALUES
	 (28,'Soldadura con parametros fuera de la tolerancia','Soldadura defectuosa',17);
