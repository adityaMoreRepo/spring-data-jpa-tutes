drop table if exists course_composite;
create table course_composite
(
    course_name varchar(255) NOT NULL,
    id int NOT NULL,
    standard varchar(255) NOT NULL,
    primary key(course_name, id)
);

