create table SYS_USER
(
    USERID   NUMBER not null
        constraint SYS_USER_PK
            primary key,
    ACCOUNT  VARCHAR2(20),
    USERNAME VARCHAR2(20),
    PASSWORD VARCHAR2(200)
)
/

INSERT INTO CLOUD.SYS_USER (USERID, ACCOUNT, USERNAME, PASSWORD) VALUES (1, 'zzw', '听不见', '$2a$10$JV3W/SJ1dlUh0OTQmIv01OpafjoWUIbZqjvgLKt4QjZLja/zE2LFO');