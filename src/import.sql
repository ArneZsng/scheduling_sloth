INSERT INTO Cohort (name, major, year) VALUES ('I12', 'Wirtschaftsinformatik', 2012);
INSERT INTO Cohort (name, major, year) VALUES ('B12', 'Betriebswirtschaftslehre', 2012);
INSERT INTO Cohort (name, major, year) VALUES ('W12', 'Wirtschaftsingenieurswesen', 2012);
INSERT INTO Century (name, cohort_id, number_of_students, break_time) VALUES ('I12a', 1, 25, 15);
INSERT INTO Century (name, cohort_id, number_of_students, break_time) VALUES ('I12b', 1, 30, 20);
INSERT INTO Century (name, cohort_id, number_of_students, break_time) VALUES ('B12a', 2, 26, 16);
INSERT INTO Century (name, cohort_id, number_of_students, break_time) VALUES ('B12b', 2, 29, 19);
INSERT INTO Century (name, cohort_id, number_of_students, break_time) VALUES ('W12a', 3, 27, 17);
INSERT INTO Century (name, cohort_id, number_of_students, break_time) VALUES ('W12b', 3, 28, 18);
INSERT INTO Lecturer (name, break_time) VALUES ('Hinrich Schröder', 25);
INSERT INTO Lecturer (name, break_time) VALUES ('Stephan Anft', 45);
INSERT INTO Room (name, available_seats, change_time) VALUES ('A001', 25, 10);
INSERT INTO Room (name, available_seats, change_time) VALUES ('C101', 35, 15);