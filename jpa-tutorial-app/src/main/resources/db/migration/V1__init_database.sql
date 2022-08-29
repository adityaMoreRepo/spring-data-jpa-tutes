DROP DATABASE IF EXISTS `student_schema_dup`;
CREATE DATABASE `student_schema_dup`;
USE `student_schema_dup`;

CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `standard` int NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
   `total_marks` int NOT NULL,
   `passing_marks` int NOT NULL,
  `marks_obtained` int NOT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `FKnxhd764cm1ie783v26t3jsdlx` (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



