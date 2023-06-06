CREATE TABLE IF NOT EXISTS organizers (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(255) NOT NULL,
                                          organization VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS presenters (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(255) NOT NULL,
                                          expertise VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      name VARCHAR(255) NOT NULL,
                                      description VARCHAR(255) NOT NULL,
                                      organizer_id BIGINT,
                                      presenter_id BIGINT,
                                      date DATE,
                                      attendees INT,
                                      FOREIGN KEY (organizer_id) REFERENCES organizers(id),
                                      FOREIGN KEY (presenter_id) REFERENCES presenters(id)
);


CREATE TABLE IF NOT EXISTS attendees (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         name VARCHAR(255) NOT NULL,
                                         email VARCHAR(255) NOT NULL
);





-- Insert sample data for attendees
INSERT INTO attendees (name, email)
VALUES ('Attendee 1', 'attendee1@example.com');

INSERT INTO attendees (name, email)
VALUES ('Attendee 2', 'attendee2@example.com');

-- Insert sample data for presenters
INSERT INTO presenters (name, expertise)
VALUES ('Max', 'Web developer');
INSERT INTO presenters (name, expertise)
VALUES ('Alina', 'software engineer');
INSERT INTO presenters (name, expertise)
VALUES ('George', 'marketing lead');



-- Insert into organizers table
INSERT INTO organizers ( name, organization) VALUES ( 'Apple', 'anc');
INSERT INTO organizers ( name, organization) VALUES ( 'Google', 'ALPHABET');
INSERT INTO organizers ( name, organization) VALUES ( 'Facebook', 'FB');

-- Insert into events table
INSERT INTO events (name, description, organizer_id, date, attendees, presenter_id)
VALUES ('Apple keynote', 'Keynote is a presentation software application developed as a part of the iWork productivity suite by Apple Inc.', 1, '2023-06-10', 2600, 3);
INSERT INTO events (name, description, organizer_id, date, attendees , presenter_id)
VALUES ('Google I/O', 'Google I/O is an annual developer conference held by Google in Mountain View, California.', 2, '2023-06-10', 249, 1);
INSERT INTO events (name, description, organizer_id, date, attendees , presenter_id)
VALUES ('React Dev event', 'Upcoming React Conferences in 2023 ·', 3, '2023-06-10', 132, 2);
