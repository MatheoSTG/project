CREATE TABLE salary(
    salaryid SERIAL NOT NULL PRIMARY KEY,
    amount NUMERIC,
    time_stamp TIMESTAMPTZ,
    workerid INTEGER  REFERENCES worker ON DELETE CASCADE
);