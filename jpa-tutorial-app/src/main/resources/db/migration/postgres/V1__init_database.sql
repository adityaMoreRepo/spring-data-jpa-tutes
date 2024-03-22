
CREATE TABLE course
(
    course_id serial NOT NULL,
    standard int NOT NULL,
    PRIMARY KEY (course_id)
);

CREATE TABLE subject (
  subject_id serial NOT NULL,
  subject_name varchar(255) DEFAULT NULL,
  total_marks int NOT NULL,
  passing_marks int NOT NULL,
  marks_obtained int NOT NULL,
  course_id int DEFAULT NULL,
  PRIMARY KEY (subject_id),
  FOREIGN KEY (course_id) REFERENCES course(course_id)
)

