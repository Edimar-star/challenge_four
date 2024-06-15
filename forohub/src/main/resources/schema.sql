-- DDL

CREATE TABLE COURSE (
    ID INTEGER PRIMARY KEY,
    NAME VARCHAR(30),
    CATEGORY VARCHAR(30)
);

CREATE TABLE AUTHOR (
    ID INTEGER PRIMARY KEY,
    NAME VARCHAR(30),
    EMAIL VARCHAR(30),
    PASSWORD VARCHAR(30)
);

CREATE TABLE PROFILE (
    ID INTEGER PRIMARY KEY,
    NAME VARCHAR(30)
);

CREATE TABLE AUTHOR_PROFILE (
    AUTHOR INTEGER,
    PROFILE INTEGER,

    PRIMARY KEY (AUTHOR, PROFILE),
    FOREIGN KEY (AUTHOR) REFERENCES AUTHOR (ID),
    FOREIGN KEY (PROFILE) REFERENCES PROFILE (ID)
);

CREATE TABLE TOPIC (
    ID INTEGER PRIMARY KEY,
    TITLE VARCHAR(30),
    MESSAGE TEXT,
    CREATIONDATE DATE,
    STATUS VARCHAR(30),
    AUTHOR INTEGER,
    COURSE INTEGER,

    FOREIGN KEY (AUTHOR) REFERENCES AUTHOR (ID),
    FOREIGN KEY (COURSE) REFERENCES COURSE (ID)
);

CREATE TABLE RESPONSE (
    ID INTEGER PRIMARY KEY,
    MESSAGE TEXT,
    TOPIC INTEGER,
    CREATIONDATE DATE,
    AUTHOR INTEGER,
    SOLUTION TEXT,

    FOREIGN KEY (TOPIC) REFERENCES TOPIC (ID),
    FOREIGN KEY (AUTHOR) REFERENCES AUTHOR (ID)
);

COMMIT;