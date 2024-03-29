INSERT INTO cohort (name, major, year) VALUES ('I12', 'Wirtschaftsinformatik', 2012);
INSERT INTO cohort (name, major, year) VALUES ('B12', 'Betriebswirtschaftslehre', 2012);
INSERT INTO cohort (name, major, year) VALUES ('W12', 'Wirtschaftsingenieurswesen', 2012);
INSERT INTO century (name, cohort_id, number_of_students, break_time) VALUES ('I12a', 1, 25, 15);
INSERT INTO century (name, cohort_id, number_of_students, break_time) VALUES ('I12b', 1, 30, 20);
INSERT INTO century (name, cohort_id, number_of_students, break_time) VALUES ('B12a', 2, 26, 16);
INSERT INTO century (name, cohort_id, number_of_students, break_time) VALUES ('B12b', 2, 29, 19);
INSERT INTO century (name, cohort_id, number_of_students, break_time) VALUES ('W12a', 3, 27, 17);
INSERT INTO century (name, cohort_id, number_of_students, break_time) VALUES ('W12b', 3, 28, 18);
INSERT INTO lecturer (name, break_time) VALUES ('Hinrich Schröder', 25);
INSERT INTO lecturer (name, break_time) VALUES ('Stephan Anft', 45);
INSERT INTO lecturer (name, break_time) VALUES ('Stefan Reichert', 45);
INSERT INTO room (name, break_time, available_seats) VALUES ('A001', 30, 25);
INSERT INTO room (name, break_time, available_seats) VALUES ('C101', 15, 30);
INSERT INTO course (name, break_time, lecturer_id,cohort_id,century_id) VALUES ('Controlling', 15, 1,2,null);
INSERT INTO course (name, break_time, lecturer_id,cohort_id,century_id) VALUES ('IAA', 15, 2,null,1);
INSERT INTO course (name, break_time, lecturer_id,cohort_id,century_id) VALUES ('AKDW', 5, 1,1,null);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-08 10:15:00','2014-11-08 15:00:00',1);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-09 10:15:00','2014-11-09 12:30:00',1);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-18 10:15:00','2014-11-18 15:00:00',1);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-19 10:15:00','2014-11-19 12:30:00',1);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-10 9:15:00','2014-11-10 16:20:00',2);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-11 11:30:00','2014-11-11 13:20:00',2);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-12 12:50:00','2014-11-12 14:45:00',2);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-13 9:40:00','2014-11-13 10:30:00',3);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-24 10:40:00','2014-11-24 12:30:00',3);
INSERT INTO lesson (start_date,end_date,course_id) VALUES ('2014-11-28 9:40:00','2014-11-28 10:30:00',3);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (1,1);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (1,2);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (2,2);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (3,1);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (4,2);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (5,1);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (6,2);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (7,1);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (8,2);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (9,1);
INSERT INTO lesson_room (lesson_id,room_id) VALUES (10,2);