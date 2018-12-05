
    create table BULL (
       ID_BULL integer not null,
        NAME_ varchar(255),
        primary key (ID_BULL)
    ) engine=MyISAM

    create table CALVING (
       ID_CALVING integer not null,
        CALVING_DATE date,
        NOTES varchar(255),
        ID_CALF integer,
        ID_CATTLE integer,
        primary key (ID_CALVING)
    ) engine=MyISAM

    create table CATTLE (
       ID_CATTLE integer not null,
        BIRTH_DATE date,
        COWSHED_NUMBER integer,
        EARRING varchar(255),
        JOIN_DATE date,
        LEAVE_DATE date,
        LEVA_REASON varchar(255),
        NAME_ varchar(255),
        NOTES varchar(255),
        SEX varchar(255),
        primary key (ID_CATTLE)
    ) engine=MyISAM

    create table COWSHED (
       ID_COWSHED integer not null,
        ADDRESS varchar(255),
        INFO varchar(255),
        NAME_ varchar(255),
        primary key (ID_COWSHED)
    ) engine=MyISAM

    create table INSEMINATION (
       ID_INSEMINATION integer not null,
        INSEMINATION_DATE date,
        NOTES varchar(255),
        RESULT varchar(255),
        ID_BULL integer,
        ID_CATTLE integer,
        primary key (ID_INSEMINATION)
    ) engine=MyISAM

    create table MEDICINE (
       ID_MEDICINE integer not null,
        NAME_ varchar(255),
        primary key (ID_MEDICINE)
    ) engine=MyISAM

    create table MEDS_USED (
       ID_MEDICINE integer not null,
        ID_TREATMENT integer not null
    ) engine=MyISAM

    create table STATS_DAILY (
       ID_DAILY integer not null,
        MILK_AMOUNT integer,
        MILKING_DATE date,
        MILKING_TIME time,
        ID_CATTLE integer,
        primary key (ID_DAILY)
    ) engine=MyISAM

    create table STATS_MONTHLY (
       ID_MONTHLY integer not null,
        BACTERIA_CONTENT integer,
        FAT_CONTENT double precision,
        PROTEIN_CONTENT double precision,
        TEST_DATE date,
        ID_CATTLE integer,
        primary key (ID_MONTHLY)
    ) engine=MyISAM

    create table TEAM (
       ID_GROUP integer not null,
        NAME_ varchar(255),
        TYPE varchar(255),
        ID_COWSHED integer,
        primary key (ID_GROUP)
    ) engine=MyISAM

    create table TEAM_CATTLE (
       ID_GROUP integer not null,
        ID_CATTLE integer not null
    ) engine=MyISAM

    create table TREATMENT (
       ID_TREATMENT integer not null,
        DISEASE varchar(255),
        END_DATE date,
        NOTES varchar(255),
        START_DATE date,
        ID_CATTLE integer,
        primary key (ID_TREATMENT)
    ) engine=MyISAM

    alter table CALVING 
       add constraint FKlx00jg1o7crum0i3dpwbsatr5 
       foreign key (ID_CALF) 
       references CATTLE (ID_CATTLE)

    alter table CALVING 
       add constraint FKa0ab9x5ny55u9mgpf9qxanmsd 
       foreign key (ID_CATTLE) 
       references CATTLE (ID_CATTLE)

    alter table INSEMINATION 
       add constraint FKbpmv1fp9h3lbcfp6u0wawhamq 
       foreign key (ID_BULL) 
       references BULL (ID_BULL)

    alter table INSEMINATION 
       add constraint FKs15g8m7bwtqv6qc7t7gxqxbic 
       foreign key (ID_CATTLE) 
       references CATTLE (ID_CATTLE)

    alter table MEDS_USED 
       add constraint FK3xgjc4jmv93wi73cnoewtas1w 
       foreign key (ID_TREATMENT) 
       references TREATMENT (ID_TREATMENT)

    alter table MEDS_USED 
       add constraint FKpv0qqgiyfise7dea37gsg1fju 
       foreign key (ID_MEDICINE) 
       references MEDICINE (ID_MEDICINE)

    alter table STATS_DAILY 
       add constraint FKiw61d1e8xwx08fheecantyp6e 
       foreign key (ID_CATTLE) 
       references CATTLE (ID_CATTLE)

    alter table STATS_MONTHLY 
       add constraint FKj4yxhj9oyxtch5egnp0f60nob 
       foreign key (ID_CATTLE) 
       references CATTLE (ID_CATTLE)

    alter table TEAM 
       add constraint FKe6mwbelo5tkk3b5dhkk5q13ti 
       foreign key (ID_COWSHED) 
       references COWSHED (ID_COWSHED)

    alter table TEAM_CATTLE 
       add constraint FKmkba7265nlm3ijmt7w5x3aw1u 
       foreign key (ID_CATTLE) 
       references CATTLE (ID_CATTLE)

    alter table TEAM_CATTLE 
       add constraint FKgbtb7y6tck486rtn9flaprh7a 
       foreign key (ID_GROUP) 
       references TEAM (ID_GROUP)

    alter table TREATMENT 
       add constraint FKqqe2nkqcctxj5ou4sv2vroft5 
       foreign key (ID_CATTLE) 
       references CATTLE (ID_CATTLE)