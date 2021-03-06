create table COWSHED (
    ID_COWSHED int NOT NULL AUTO_INCREMENT,
    ADDRESS varchar(50),
    NAME_ varchar(50),
    INFO varchar(255),
    PRIMARY KEY(ID_COWSHED));
    
create table BULL(
    ID_BULL int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME_ varchar(50)
    );
    
create table MEDICINE(
    ID_MEDICINE int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME_ varchar(50));
    
create table TEAM(
    ID_GROUP int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_COWSHED int,
    NAME_ varchar(50),
    TYPE varchar(50),
    FOREIGN KEY (ID_COWSHED) REFERENCES COWSHED(ID_COWSHED));
    
create table CATTLE(
    ID_CATTLE int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME_ varchar(50),
    EARRING varchar(50),
    SEX varchar(2) CHECK (SEX in ('XX', 'XY')),
    COWSHED_NUMBER int(4),
    BIRTH_DATE date,
    JOIN_DATE date,
    LEAVE_DATE date,
    LEVA_REASON varchar(255),
    NOTES varchar(255));
    
create table TEAM_CATTLE(
    ID_GROUP int,
    ID_CATTLE int,
    FOREIGN KEY (ID_GROUP) REFERENCES TEAM(ID_GROUP),
    FOREIGN KEY (ID_CATTLE) REFERENCES CATTLE(ID_CATTLE));
    
create table INSEMINATION(
    ID_INSEMINATION int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_CATTLE int,
    INSEMINATION_DATE date,
    ID_BULL int,
    RESULT varchar(50),
    NOTES varchar(255),
    FOREIGN KEY (ID_CATTLE) REFERENCES CATTLE(ID_CATTLE),
    FOREIGN KEY (ID_BULL) REFERENCES BULL(ID_BULL));
    
create table STATS_DAILY(
    ID_DAILY int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_CATTLE int,
    MILK_AMOUNT int(4),
    MILKING_DATE date,
    MILKING_TIME time,
    FOREIGN KEY (ID_CATTLE) REFERENCES CATTLE(ID_CATTLE));
    
create table STATS_MONTHLY(
    ID_MONTHLY int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_CATTLE int,
    TEST_DATE date,
    PROTEIN_CONTENT double(4,2),
    FAT_CONTENT double(4,2),
    BACTERIA_CONTENT int(10),
    FOREIGN KEY (ID_CATTLE) REFERENCES CATTLE(ID_CATTLE));
    
create table TREATMENT(
    ID_TREATMENT int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_CATTLE int,
    START_DATE date,
    END_DATE date,
    DISEASE varchar(50),
    NOTES varchar(255),
    FOREIGN KEY (ID_CATTLE) REFERENCES CATTLE(ID_CATTLE));
    
create table MEDS_USED(
    ID_TREATMENT int NOT NULL,
    ID_MEDICINE int NOT NULL,
    DOSE varchar(255),
    PRIMARY KEY(ID_TREATMENT,ID_MEDICINE),
    FOREIGN KEY (ID_TREATMENT) REFERENCES TREATMENT(ID_TREATMENT),
    FOREIGN KEY (ID_MEDICINE) REFERENCES MEDICINE(ID_MEDICINE));
    
create table CALVING(
    ID_CALVING int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_CATTLE int,
    CALVING_DATE date,
    ID_CALF int,
    NOTES varchar(255),
    FOREIGN KEY (ID_CATTLE) REFERENCES CATTLE(ID_CATTLE),
    FOREIGN KEY (ID_CALF) REFERENCES CATTLE(ID_CATTLE));
    
    