create table SYS_PERMESSION
(
    PID         NUMBER        not null
        constraint SYS_PERMESSION_PK
            primary key,
    RESOURCEURL VARCHAR2(100) not null
)
/

INSERT INTO CLOUD.SYS_PERMESSION (PID, RESOURCEURL) VALUES (1, '/sysUser/getUserPermissionById');
INSERT INTO CLOUD.SYS_PERMESSION (PID, RESOURCEURL) VALUES (2, '/sysUser/get');
INSERT INTO CLOUD.SYS_PERMESSION (PID, RESOURCEURL) VALUES (3, '/sysUser/remove');