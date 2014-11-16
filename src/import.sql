INSERT INTO Audience (id, name) VALUES (1, 'I12');
INSERT INTO Audience (id, name) VALUES (2, 'B12');
INSERT INTO Audience (id, name) VALUES (3, 'W12');
INSERT INTO Cohort (id, major, year) VALUES (1, 'Wirtschaftsinformatik', 2012);
INSERT INTO Cohort (id, major, year) VALUES (2, 'Betriebswirtschaftslehre', 2012);
INSERT INTO Cohort (id, major, year) VALUES (3, 'Wirtschaftsingenieurswesen', 2012);
INSERT INTO Audience (id, name) VALUES (4, 'I12a');
INSERT INTO Audience (id, name) VALUES (6, 'B12a');
INSERT INTO Audience (id, name) VALUES (8, 'W12a');
INSERT INTO Audience (id, name) VALUES (5, 'I12b');
INSERT INTO Audience (id, name) VALUES (7, 'B12b');
INSERT INTO Audience (id, name) VALUES (9, 'W12b');
INSERT INTO Century (id, cohort_id, number_of_students, break_time) VALUES (4, 1, 25, 15);
INSERT INTO Century (id, cohort_id, number_of_students, break_time) VALUES (5, 1, 30, 20);
INSERT INTO Century (id, cohort_id, number_of_students, break_time) VALUES (6, 2, 26, 16);
INSERT INTO Century (id, cohort_id, number_of_students, break_time) VALUES (7, 2, 29, 19);
INSERT INTO Century (id, cohort_id, number_of_students, break_time) VALUES (8, 3, 27, 17);
INSERT INTO Century (id, cohort_id, number_of_students, break_time) VALUES (9, 3, 28, 18);
INSERT INTO Lecturer (name, break_time) VALUES ('Hinrich Schröder', 25);
INSERT INTO Lecturer (name, break_time) VALUES ('Stephan Anft', 45);
INSERT INTO Room (name, available_seats, break_time) VALUES ('A001', 25, 10);
INSERT INTO Room (name, available_seats, break_time) VALUES ('C101', 35, 15);
INSERT INTO Course (name, audience_id, break_time, lecturer_id) VALUES ('IAA', 4, 25, 2);
INSERT INTO Course (name, audience_id, break_time, lecturer_id) VALUES ('Englisch', 1, 15, 2);