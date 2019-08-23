create table USER_ROLE_RELATION
(
    ROLEID NUMBER not null
        constraint USER_ROLE_SYS_ROLE_ROLEID_FK
            references SYS_ROLE,
    USERID NUMBER not null
        constraint USER_ROLE_SYS_USER_USERID_FK
            references SYS_USER,
    constraint USER_ROLE_RELATION_PK
        primary key (ROLEID, USERID)
)
/

INSERT INTO CLOUD.USER_ROLE_RELATION (ROLEID, USERID) VALUES (1, 1);
INSERT INTO CLOUD.USER_ROLE_RELATION (ROLEID, USERID) VALUES (2, 1);