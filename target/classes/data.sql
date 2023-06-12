-- Create organizers table
CREATE TABLE IF NOT EXISTS organizers (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(255) NOT NULL,
                                          organization VARCHAR(255) NOT NULL
);

-- Create presenters table
CREATE TABLE IF NOT EXISTS presenters (
                                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(255) NOT NULL,
                                          expertise VARCHAR(255) NOT NULL,
                                          organizer_id BIGINT,
                                          FOREIGN KEY (organizer_id) REFERENCES organizers(id) ON DELETE SET NULL
);

-- Create events table
CREATE TABLE IF NOT EXISTS events (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      name VARCHAR(255) NOT NULL,
                                      description VARCHAR(255) NOT NULL,
                                      organizer_id BIGINT,
                                      presenter_id BIGINT,
                                      date DATE,
                                      max_attendees INT,
                                      FOREIGN KEY (organizer_id) REFERENCES organizers(id) ON DELETE SET NULL,
                                      FOREIGN KEY (presenter_id) REFERENCES presenters(id) ON DELETE SET NULL
);


-- Create attendees table
CREATE TABLE IF NOT EXISTS attendees (
                                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                         name VARCHAR(255) NOT NULL,
                                         email VARCHAR(255) NOT NULL,
                                         event_id BIGINT,
                                         FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE SET NULL


);


-- Insert sample data for organizers
INSERT INTO organizers (name, organization) VALUES ('Apple', 'Apple Inc. is an American multinational technology company headquartered in Cupertino, California.');
INSERT INTO organizers (name, organization) VALUES ('Google', 'Google LLC is an American multinational technology company focusing on artificial intelligence and online advertising.');
INSERT INTO organizers (name, organization) VALUES ('Facebook', 'Meta Platforms, Inc., formerly named Facebook, Inc., and TheFacebook, Inc., is an American multinational technology conglomerate based in Menlo Park, California. ');

-- Insert sample data for presenters
INSERT INTO presenters (name, expertise, organizer_id) VALUES ('Max', 'Web developer', 1);
INSERT INTO presenters (name, expertise, organizer_id) VALUES ('Alina', 'Software engineer', 3);
INSERT INTO presenters (name, expertise, organizer_id) VALUES ('George', 'Marketing lead', 2);


-- Insert sample data for events
INSERT INTO events (name, description, organizer_id, date, max_attendees, presenter_id)
VALUES ('Apple keynote', 'Keynote is a presentation software application developed as a part of the iWork productivity suite by Apple Inc.', 1, '2023-06-10', 2600, 1);

INSERT INTO events (name, description, organizer_id, date, max_attendees, presenter_id)
VALUES ('Google I/O', 'Google I/O is an annual developer conference held by Google in Mountain View, California.', 2, '2023-06-10', 249, 3);

INSERT INTO events (name, description, organizer_id, date, max_attendees, presenter_id)
VALUES ('React Dev event', 'Upcoming React Conferences in 2023', 3, '2023-06-10', 132, 2);


-- Insert sample data for attendees
INSERT INTO attendees (name, email, event_id) VALUES ('Wisam abadi', 'wisam@example.com', 1);
INSERT INTO attendees (name, email, event_id) VALUES ('John will', 'jhon@example.com', 1);

