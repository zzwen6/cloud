create table SYS_ROLE
(
    ROLEID   NUMBER       not null
        constraint SYS_ROLE_PK
            primary key,
    ROLENAME VARCHAR2(20) not null
)
/

INSERT INTO CLOUD.SYS_ROLE (ROLEID, ROLENAME) VALUES (1, '测试');
INSERT INTO CLOUD.SYS_ROLE (ROLEID, ROLENAME) VALUES (2, '管理');