ALTER TABLE LEADER_INFO ADD LI_GM INT;

ALTER TABLE LEADER_INFO MODIFY COLUMN LI_CURRENT VARCHAR(32);

ALTER TABLE DOCTOR_INFO ADD DI_DEPTTYPE INTEGER;
