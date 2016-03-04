-- create database vastikaeis;
--
-- deploy your program into tomcat server (Run on Server)
-- 
-- use vastikaeis;
 
 CREATE TABLE IF NOT EXISTS `address` (
     `id` BIGINT(20) NOT NULL,
     `city` VARCHAR(255) NOT NULL,
     `endDate` DATE DEFAULT NULL,
     `startDate` DATE NOT NULL,
     `state` VARCHAR(255) NOT NULL,
     `street` VARCHAR(255) NOT NULL,
     `zipCode` VARCHAR(255) NOT NULL,
     `userId` BIGINT(20) DEFAULT NULL,
     `addressLine1` VARCHAR(255) DEFAULT NULL,
     `status` CHAR(1) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=24 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `client` (
     `id` BIGINT(20) NOT NULL,
     `email` VARCHAR(255) NOT NULL,
     `name` VARCHAR(255) NOT NULL,
     `status` VARCHAR(255) NOT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `clientinterestheroes` (
     `id` BIGINT(20) NOT NULL,
     `jobSummary` VARCHAR(255) DEFAULT NULL,
     `role` VARCHAR(255) NOT NULL,
     `clientId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `users` (
     `id` BIGINT(20) NOT NULL,
     `enabled` TINYINT(1) NOT NULL,
     `dob` DATE NOT NULL,
     `email` VARCHAR(255) NOT NULL,
     `firstName` VARCHAR(255) NOT NULL,
     `gender` CHAR(1) DEFAULT NULL,
     `lastName` VARCHAR(255) NOT NULL,
     `middleName` VARCHAR(255) DEFAULT NULL,
     `password` VARCHAR(255) NOT NULL,
     `tokenValue` VARCHAR(255) DEFAULT NULL,
     `username` VARCHAR(255) NOT NULL,
     `userType` CHAR(1) DEFAULT NULL,
     `createdDate` DATE DEFAULT NULL,
     `createdBy` BIGINT(20) DEFAULT NULL,
     `heroes_id` BIGINT(20) DEFAULT NULL,
     `updatedDate` DATE DEFAULT NULL,
     `updatedBy` BIGINT(20) DEFAULT NULL,
     `modifiedDate` DATE DEFAULT NULL,
     `modifiedBy` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=31 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `contacts` (
     `id` BIGINT(20) NOT NULL,
     `contactNumber` VARCHAR(255) DEFAULT NULL,
     `userId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=22 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `documents` (
     `id` BIGINT(20) NOT NULL,
     `description` VARCHAR(255) DEFAULT NULL,
     `encryptedFileName` VARCHAR(255) DEFAULT NULL,
     `expiryDate` DATE DEFAULT NULL,
     `filePath` VARCHAR(255) DEFAULT NULL,
     `name` VARCHAR(255) DEFAULT NULL,
     `realFileName` VARCHAR(255) DEFAULT NULL,
     `uploadedDate` DATE DEFAULT NULL,
     `visaCopy` VARCHAR(255) DEFAULT NULL,
     `documentType` BIGINT(20) DEFAULT NULL,
     `userId` BIGINT(20) DEFAULT NULL,
     `status` CHAR(1) DEFAULT NULL,
     `fileUpload_id` BIGINT(20) DEFAULT NULL,
     `heroesId` BIGINT(20) DEFAULT NULL,
     `modifiedDate` DATE DEFAULT NULL,
     `modifiedBy` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=13 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `documenttype` (
     `id` BIGINT(20) NOT NULL,
     `description` VARCHAR(255) DEFAULT NULL,
     `status` VARCHAR(255) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=11 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `fileupload` (
     `id` BIGINT(20) NOT NULL,
     `encryptedFileName` VARCHAR(255) DEFAULT NULL,
     `filePath` VARCHAR(255) DEFAULT NULL,
     `realFileName` VARCHAR(255) DEFAULT NULL,
     `uploadedDate` DATETIME DEFAULT NULL,
     `uploadedBy_id` BIGINT(20) DEFAULT NULL,
     `uploadedBy` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=23 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `heroes` (
     `id` BIGINT(20) NOT NULL,
     `availability` VARCHAR(255) DEFAULT NULL,
     `primeOK` CHAR(1) DEFAULT NULL,
     `skillSpecialization` VARCHAR(255) DEFAULT NULL,
     `status` VARCHAR(255) DEFAULT NULL,
     `skillCategory` BIGINT(20) DEFAULT NULL,
     `visaStatus` VARCHAR(255) DEFAULT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `heroesresume` (
     `id` BIGINT(20) NOT NULL,
     `email` VARCHAR(255) DEFAULT NULL,
     `resumeContact` VARCHAR(255) DEFAULT NULL,
     `title` VARCHAR(255) NOT NULL,
     `visibility` VARCHAR(255) DEFAULT NULL,
     `fileUpload_id` BIGINT(20) DEFAULT NULL,
     `heroesId` BIGINT(20) DEFAULT NULL,
     `status` CHAR(1) DEFAULT NULL,
     `modifiedDate` DATE DEFAULT NULL,
     `modifiedBy` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `heroesskills` (
     `id` BIGINT(20) NOT NULL,
     `yearsOfExperience` INT(11) DEFAULT NULL,
     `heroesId` BIGINT(20) DEFAULT NULL,
     `skillId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=21 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `permissions` (
     `id` BIGINT(20) NOT NULL,
     `name` VARCHAR(255) DEFAULT NULL,
     `parentId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `remindernote` (
     `id` BIGINT(20) NOT NULL,
     `note` VARCHAR(255) DEFAULT NULL,
     `reminderTime` DATE NOT NULL,
     `userId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `resumemarketing` (
     `id` BIGINT(20) NOT NULL,
     `heroResumeId` BIGINT(20) DEFAULT NULL,
     `heroesId` BIGINT(20) DEFAULT NULL,
     `recruiterId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=14 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `resumemarketingfollowup` (
     `id` BIGINT(20) NOT NULL,
     `followupDate` DATE NOT NULL,
     `followupSummary` VARCHAR(255) DEFAULT NULL,
     `nextFollowupDate` DATE NOT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `resumesubmission` (
     `id` BIGINT(20) NOT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `role_members` (
     `id` BIGINT(20) NOT NULL,
     `roles_id` BIGINT(20) DEFAULT NULL,
     `members_id` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `role_permissions` (
     `id` BIGINT(20) NOT NULL,
     `permissions_id` BIGINT(20) DEFAULT NULL,
     `roles_id` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `roles` (
     `id` BIGINT(20) NOT NULL,
     `name` VARCHAR(255) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `skill` (
     `id` BIGINT(20) NOT NULL,
     `skillName` VARCHAR(255) NOT NULL,
     `status` CHAR(1) NOT NULL,
     `skillCategory_id` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `skillcategory` (
     `id` BIGINT(20) NOT NULL,
     `categoryDesc` VARCHAR(255) DEFAULT NULL,
     `categoryName` VARCHAR(255) NOT NULL,
     `status` CHAR(1) NOT NULL
 )  ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `skillspecialization` (
     `id` BIGINT(20) NOT NULL,
     `title` VARCHAR(255) DEFAULT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 CREATE TABLE IF NOT EXISTS `userdesignation` (
     `userDesignationId` INT(11) NOT NULL,
     `type` VARCHAR(255) NOT NULL,
     `userId` BIGINT(20) DEFAULT NULL
 )  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
 
 INSERT INTO `users` (`id`, `enabled`, `dob`, `email`, `firstName`, `gender`, `lastName`, `middleName`, `password`, `tokenValue`, `username`, `userType`, `createdDate`, `createdBy`, `modifiedDate`, `modifiedBy`) VALUES
 (1, 1, '2015-06-05', 'a@y.com', 'Supreme', 'M', 'Admin', '', '$2a$10$36l6Z0R9c12IchyNvXlDfuB6lQY4i70Aeua0JrW68CwoWaJJv8hbG', '9io890', 'admin', 'O', NULL, NULL, '2015-09-02', NULL),
 (2, 1, '2015-09-25', 'swastik@gmail.com', 'Swastik', 'M', 'Kayastha', '', '$2a$10$36l6Z0R9c12IchyNvXlDfuB6lQY4i70Aeua0JrW68CwoWaJJv8hbG', NULL, 'swastik', 'O', NULL, NULL, '2015-09-02', NULL),
 (3, 1, '2015-09-25', 'dummy@outlook.com', 'Consultant', 'M', 'One', '', '$2a$10$zcITn6RobvMScKmQ.9COL.ZrKSJNvMS.lPcMAsx/1HCUAUbHFKnqG', NULL, 'dummy', 'H', NULL, NULL, '2015-09-02', NULL),
 (4, 1, '2015-09-25', 'c2@gmail.com', 'Consultant', 'F', 'Two', '', '$2a$10$zcITn6RobvMScKmQ.9COL.ZrKSJNvMS.lPcMAsx/1HCUAUbHFKnqG', NULL, 'c2', 'H', NULL, NULL, '2015-09-02', NULL),
 (5, 1, '2015-09-25', 'marketer@gmail.com', 'Marketer', 'F', 'One', '', '$2a$10$zcITn6RobvMScKmQ.9COL.ZrKSJNvMS.lPcMAsx/1HCUAUbHFKnqG', NULL, 'marketer', 'O', NULL, NULL, '2015-09-02', NULL);
 
 
 INSERT INTO `skillcategory` (`id`, `categoryDesc`, `categoryName`, `status`) VALUES
 (1, 'java', 'Java Developer', 'A'),
 (2, '.Net', '.Net Developer', 'A'),
 (3, 'sql', 'SQL Developer', 'A'),
 (4, 'etl', 'ETL Developer', 'A'),
 (5, 'ba', 'Business Analyst', 'A'),
 (6, 'qa', 'Quality Analyst', 'A'),
 (7, 'linux', 'Linux Admin', 'A');
 
 INSERT INTO `skill` (`id`, `skillName`, `status`, `skillCategory_id`) VALUES
 (1, 'Java', 'A', 1),
 (2, 'Core Java', 'A', 1),
 (3, 'J2EE', 'A', 1),
 (4, 'Oracle', 'A', 1),
 (5, 'Forms', 'A', 2),
 (6, 'C#', 'A', 2),
 (7, 'MVC', 'A', 2),
 (8, 'SQL', 'A', 2),
 (9, 'MySQL', 'A', 3),
 (10, 'PL SQL', 'A', 3),
 (11, 'Oracle', 'A', 3),
 (12, 'DB2', 'A', 3),
 (13, 'ETL', 'A', 4),
 (14, 'MySQL Workbench', 'A', 4),
 (15, 'Programming', 'A', 4),
 (16, 'Oracle', 'A', 4),
 (17, 'SQL', 'A', 5),
 (18, 'Excel', 'A',5),
 (19, 'Access', 'A', 5),
 (20, 'Oracle', 'A', 5),
 (21, 'Testing', 'A', 6),
 (22, 'BlackBox Testing', 'A', 6),
 (23, 'Excel', 'A', 6),
 (24, 'Organization', 'A', 6),
 (25, 'Bash', 'A', 7),
 (26, 'Virtual Machine', 'A', 7),
 (27, 'AMS', 'A', 7),
 (28, 'Script', 'A', 7);
 
 INSERT INTO `permissions` (`id`, `name`, `parentId`) VALUES
 (1, 'Users', NULL),
 (2, 'Add User', 1),
 (3, 'View Users', 1),
 (4, 'Edit User', 1),
 (5, 'Delete User', 1),
 (6, 'Heroes', NULL),
 (7, 'Add Hero', 6),
 (8, 'My Hero', 6),
 (9, 'Edit Hero', 6),
 (10, 'Delete Hero', 6),
 (11, 'Marketing', NULL),
 (12, 'Assign Marketer', 11),
 (13, 'Get Marketer', 11),
 (14, 'Delete Marketer', 11),
 (15, 'Skill Category', NULL),
 (16, 'Add Skill Category', 15),
 (17, 'Get Skill Category', 15),
 (18, 'Edit Skill Category', 15),
 (19, 'Delete Skill Category', 15),
 (20, 'Roles', NULL),
 (21, 'Add Role', 20),
 (22, 'View Role', 20),
 (23, 'Edit Role', 20), 
 (24, 'Delete Role', 20),
 (25, 'Assign Permission', 20);
 
 INSERT INTO `roles` (`id`, `name`) VALUES
 (1, 'Administrator'),
 (2, 'Marketer'),
 (3, 'Consultant');
 
 INSERT INTO `role_permissions` (`id`, `permissions_id`, `roles_id`) VALUES
 (1,1,1),
 (2,2,1),
 (3,3,1),
 (4,4,1),
 (5,5,1),
 (6,6,1),
 (7,7,1),
 (8,8,1),
 (9,9,1),
 (10,10,1),
 (11,11,1),
 (12,12,1),
 (13,13,1),
 (14,14,1),
 (15,15,1),
 (16,16,1),
 (17,17,1),
 (18,18,1),
 (19,19,1),
 (20,20,1),
 (21,21,1),
 (22,22,1),
 (23,23,1),
 (24,24,1),
 (25,25,1),
 (26,6,2),
 (27,7,2),
 (28,8,2),
 (29,9,2),
 (30,10,2),
 (31,11,2),
 (32,12,2),
 (33,13,2),
 (34,15,2),
 (35,16,2),
 (36,17,2);
 
 INSERT INTO `role_members` (`id`, `roles_id`, `members_id`) VALUES
 (1, 1, 1),
 (2, 2, 5);
 
 INSERT INTO `contacts` (`id`, `contactNumber`, `userId`) VALUES 
 (1, '2142268243', 1),
 (2, '8374030434', 2),
 (3, '4923943924', 3),
 (4, '9873498421', 4),
 (5, '8987349234', 5);
 
 INSERT INTO `address` (`id`, `city`, `endDate`, `startDate`, `state`, `street`, `zipCode`, `userId`, `addressLine1`, `status`) VALUES
 (1, 'Ames', NULL, '2015-08-19', 'Iowa', '1000 N 4th street', '75038', 1, NULL, NULL),
 (2, 'Ames', NULL, '2015-08-19', 'Iowa', '1000 N 4th street', '75038', 2, NULL, NULL),
 (3, 'Ames', NULL, '2015-08-19', 'Iowa', '1000 N 4th street', '75038', 3, NULL, NULL),
 (4, 'Ames', NULL, '2015-08-19', 'Iowa', '1000 N 4th street', '75038', 4, NULL, NULL),
 (5, 'Ames', NULL, '2015-08-19', 'Iowa', '1000 N 4th street', '75038', 5, NULL, NULL);
 
 INSERT INTO `documenttype` VALUES 
 (1,'Drivers License','A'),
 (2,'EAD Card','A'),
 (3,'Green Card','A'),
 (4,'Transcript','A'),
 (5,'ID Card','A');
 
 INSERT INTO `heroes` VALUES (3,'3 weeks','Y','Java Dev','Active','F1-CPT',1),(4,'2 weeks','Y','Java Dev','Active','F1-CPT',1),(7,'2 weeks','Y','Java Dev','Active','F1-CPT',1),(8,'One Week','Y','Java Developer','Marketing','F1-OPT',1);