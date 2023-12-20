CREATE TABLE IF NOT EXISTS platform
(
    id          int          NOT NULL AUTO_INCREMENT COMMENT '平台id',
    name        varchar(100) not null comment '平台名称',
    description text         null comment '描述',
    master      VARCHAR(36)  NOT NULL COMMENT '平台管理人',
    created_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX CREATOR_IDX (master ASC) USING BTREE COMMENT '平台管理人'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '平台'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS user
(
    id                 VARCHAR(36)  NOT NULL COMMENT '用户id',
    account            VARCHAR(36)  NOT NULL COMMENT '账号',
    email              VARCHAR(50)  NULL COMMENT '邮箱',
    phone              VARCHAR(11)  NULL COMMENT '手机',
    nickname           VARCHAR(30)  NULL     DEFAULT NULL COMMENT '昵称',
    password           VARCHAR(128) NOT NULL COMMENT '密码',
    header             VARCHAR(255) NULL     DEFAULT NULL COMMENT '头像',
    disk_id            VARCHAR(36)  NULL     DEFAULT NULL COMMENT '磁盘id',
    status             TINYINT      NOT NULL DEFAULT 0 COMMENT '账号状态：0-未激活,1-正常,2-密码冻结,3-违规,4-注销',
    gender             TINYINT      NOT NULL DEFAULT 0 COMMENT '性别：0-未知,1-男,2-女',
    birthday           date         NULL     DEFAULT NULL COMMENT '出生日期',
    signature          VARCHAR(100) NULL     DEFAULT NULL COMMENT '个性签名',
    login_ip           VARCHAR(64)  NULL     DEFAULT NULL COMMENT '登录ip',
    last_login_time    datetime     NULL     DEFAULT NULL COMMENT '最后登陆时间',
    password_error_num TINYINT      NOT NULL DEFAULT 0 COMMENT '密码错误次数,最大为五',
    online_time        TINYINT      NOT NULL DEFAULT 3 COMMENT '登录时常,最大12',
    address            INT          NULL     DEFAULT 0 COMMENT '用户所在城市,默认是未知',
    address_text       VARCHAR(200) NULL COMMENT '用户地址中文',
    source             varchar(100) null comment '注册来源',
    source_platform    int          null comment '来源平台',
    created_at         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX ACCOUNT_IDX (account ASC) USING BTREE COMMENT '账号索引',
    UNIQUE INDEX EMAIL_IDX (email ASC) USING BTREE COMMENT '邮箱索引',
    INDEX PHONE_IDX (phone ASC) USING BTREE COMMENT '手机号索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS user_platform
(
    id          VARCHAR(36) NOT NULL COMMENT 'id',
    user_id     varchar(36) not null comment '用户id',
    platform_id int         not null comment '平台id',
    created_at  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入平台时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX PLATFORM_IDX (platform_id ASC) USING BTREE COMMENT '平台id索引',
    UNIQUE INDEX USER_IDX (user_id ASC) USING BTREE COMMENT '用户id索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户-平台'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS third_account
(
    id         VARCHAR(36)  NOT NULL COMMENT 'id',
    type       VARCHAR(30)  NOT NULL COMMENT '第三方平台：qq|weixin|dingtalk|sina|tiktok',
    open_id    VARCHAR(255) NOT NULL COMMENT 'open_id',

    nickname   VARCHAR(255) NULL COMMENT '第三方平台昵称',
    header     VARCHAR(255) NULL COMMENT '第三方平台头像',
    user_id    VARCHAR(32)  NOT NULL COMMENT '用户id',
    created_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX ACCOUNT_IDX (open_id ASC) USING BTREE COMMENT 'open_id索引',

    UNIQUE INDEX USER_IDX (user_id ASC) USING BTREE COMMENT '用户id索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '第三方平台账号'
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS user_history_header
(
    id         VARCHAR(36)  NOT NULL COMMENT '历史头像id',
    user_id    VARCHAR(36)  NOT NULL COMMENT '用户id',
    header     VARCHAR(255) NOT NULL COMMENT '头像',
    disk_id    VARCHAR(36)  NOT NULL COMMENT '磁盘id',
    created_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX USER_IDX (user_id ASC) USING BTREE COMMENT '用户id索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户历史头像表'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS role
(
    id          VARCHAR(36)  NOT NULL COMMENT '角色id',
    name        VARCHAR(50)  NOT NULL COMMENT '角色名',
    description VARCHAR(100) NOT NULL COMMENT '角色描述',
    platform_id int          not null comment '平台id',
    creator     VARCHAR(36)  NOT NULL COMMENT '创建者id',
    created_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX PLATFORM_IDX (platform_id ASC) USING BTREE COMMENT '平台id索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统角色'
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS user_role
(
    id         VARCHAR(36) NOT NULL COMMENT '用户角色中间表',
    user_id    VARCHAR(36) NOT NULL COMMENT '用户',
    role_id    VARCHAR(36) NOT NULL COMMENT '角色',
    created_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '授权时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX USER_IDX (user_id ASC) USING BTREE COMMENT '用户id索引',
    INDEX ROLE_IDX (role_id ASC) USING BTREE COMMENT '角色id索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户-角色'
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS permission_group
(
    id          varchar(36)  NOT NULL COMMENT '权限组id',
    platform_id varchar(36)  NOT NULL COMMENT '平台id',
    parent_id   varchar(36)  not null comment '上级id',
    name        varchar(50)  NOT NULL COMMENT '权限组名称',
    description varchar(100) NOT NULL COMMENT '权限组描述',
    creator     varchar(36)  NOT NULL COMMENT '创建者id',
    sort_code   int          not null default 0 comment '排序号',
    created_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX PLATFORM_IDX (platform_id ASC) USING BTREE COMMENT '平台id索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统权限组'
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS permission
(
    id          varchar(36)  NOT NULL COMMENT '权限id',
    platform_id varchar(36)  NOT NULL COMMENT '平台id',
    group_id    varchar(36)  NOT NULL COMMENT '权限组id',
    name        varchar(100) NOT NULL COMMENT '权限名称',
    code        varchar(255) NOT NULL COMMENT '权限标识',
    description varchar(100) NOT NULL COMMENT '权限描述',
    type        varchar(36)  NOT NULL COMMENT '权限类型-字典表',
    creator     varchar(36)  NOT NULL COMMENT '创建者id',
    created_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX CODE_IDX (code ASC) USING BTREE COMMENT '权限标识索引',
    INDEX GROUP_IDX (group_id ASC) USING BTREE COMMENT '权限组id索引',
    UNIQUE INDEX PLATFORM_IDX (platform_id ASC) USING BTREE COMMENT '平台id索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '系统权限'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS role_permission
(
    id            varchar(36) NOT NULL COMMENT '角色权限中间表',
    role_id       varchar(36) NOT NULL COMMENT '角色',
    permission_id varchar(36) NOT NULL COMMENT '权限',
    created_at    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '授权时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX ROLE_IDX (role_id ASC) USING BTREE COMMENT '角色id索引',
    INDEX PERMISSION_IDX (permission_id ASC) USING BTREE COMMENT '权限id索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色-权限'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS user_status_log
(
    id             VARCHAR(36)  NOT NULL COMMENT '自增id',
    user_id        VARCHAR(36)  NOT NULL COMMENT '用户',
    current_status TINYINT      NOT NULL COMMENT '当前状态',
    change_status  TINYINT      NOT NULL COMMENT '变更后状态',
    remark         VARCHAR(255) NOT NULL COMMENT '备注',
    duration       datetime     NOT NULL COMMENT '状态结束时间',
    created_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX USER (user_id ASC) USING BTREE COMMENT '用户索引'
) ENGINE = INNODB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS menu
(
    id          varchar(36)  NOT NULL COMMENT 'id',
    platform_id int          NOT NULL COMMENT '平台ID',
    name        varchar(50)  NOT NULL COMMENT '菜单名称',
    code        varchar(100) NULL     DEFAULT NULL COMMENT '菜单Code',
    icon        varchar(100) NULL     DEFAULT NULL COMMENT '菜单图标',
    view        varchar(200) NULL     DEFAULT NULL COMMENT '菜单路径',
    target      tinyint      NOT NULL DEFAULT 0 COMMENT '打开方式,1-当前标签,2-新标签',
    parent      varchar(36)  NOT NULL DEFAULT '-1' COMMENT '上级菜单',
    sort        int          NOT NULL DEFAULT 1 COMMENT '权重',
    creator     varchar(36)  NOT NULL COMMENT '创建人',
    created_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    updated_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX PLATFORM_IDX (platform_id ASC) USING BTREE COMMENT '平台id索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导航菜单'
  ROW_FORMAT = Dynamic;


CREATE TABLE IF NOT EXISTS role_menu
(
    id         varchar(36) NOT NULL COMMENT '编号',
    menu_id    varchar(36) NOT NULL COMMENT '菜单唯一标识',
    role_id    varchar(36) NOT NULL COMMENT '角色唯一标识',
    created_at datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色菜单'
  ROW_FORMAT = Dynamic;


CREATE TABLE IF NOT EXISTS system_config
(
    id          varchar(36)  NOT NULL COMMENT '配置id',
    platform_id int          NOT NULL COMMENT '平台ID',
    name        varchar(50)  NOT NULL COMMENT '系统配置名',
    code        varchar(100) NOT NULL COMMENT '缓存key',
    value       text         NOT NULL COMMENT '值',
    type        tinyint      NOT NULL COMMENT '类型\r\n0-字符串\r\n1-数组\r\n2-json对象\r\n3-数字\r\n4-布尔值\r\n5-加密',
    options     text         not null comment '配置项',
    enable      tinyint      NOT NULL DEFAULT 1 COMMENT '是否启用此配置\r\n0-不启用\r\n1-启用',
    description varchar(150) NOT NULL COMMENT '配置介绍',
    creator     varchar(36)  NOT NULL COMMENT '创建者',
    created_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX PLATFORM_IDX (platform_id ASC) USING BTREE COMMENT '平台id索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;



CREATE TABLE IF NOT EXISTS email_subject
(
    id           varchar(36)  not null comment 'id',
    platform_id  int          not null comment '平台id',
    driver       varchar(20)  NOT NULL DEFAULT 'smtp' COMMENT '邮箱驱动',
    host         varchar(64)  NOT NULL COMMENT 'host',
    port         int          NOT NULL DEFAULT 465 COMMENT '端口',
    username     varchar(100) NOT NULL COMMENT '账号',
    password     varchar(128) NOT NULL COMMENT '密码',
    encryption   varchar(20)  NOT NULL DEFAULT 'ssl' COMMENT '加密',
    address      varchar(100) NOT NULL COMMENT '邮箱地址',
    subject_name varchar(100) NOT NULL COMMENT '发件人',
    creator      varchar(36)  NOT NULL COMMENT '创建人',
    created_at   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    updated_at   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB COMMENT = '邮箱主体'
  ROW_FORMAT = Dynamic;



CREATE TABLE IF NOT EXISTS email_template
(
    id          varchar(36)   not null comment 'id',
    platform_id int           not null comment '平台id',
    name        varchar(100)  NOT NULL COMMENT '邮件模板名称',
    code        varchar(100)  NOT NULL COMMENT '模板key',
    content     text          NULL COMMENT '模板内容',
    placeholder varchar(1000) NULL     DEFAULT '{}' COMMENT '默认占位值',
    subject_id  varchar(36)   NOT NULL COMMENT '邮箱主体',
    creator     varchar(36)   NOT NULL COMMENT '模板创建者',
    created_at  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX `CREATOR_IDX` (`creator` ASC) USING BTREE COMMENT '创建者索引',
    INDEX `SUBJECT_IDX` (`subject_id` ASC) USING BTREE COMMENT '邮箱主体索引',
    INDEX `CODE_IDX` (`code` ASC) USING BTREE COMMENT '模板key'
) ENGINE = InnoDB COMMENT = '邮箱模板'
  ROW_FORMAT = Dynamic;



CREATE TABLE IF NOT EXISTS province
(
    id   int         NOT NULL COMMENT '省份编号',
    name varchar(50) NOT NULL COMMENT '省份名称',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX IDX_ID (id ASC) USING BTREE,
    INDEX IDX_NAME (name ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '省份'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS city
(
    id          int         NOT NULL COMMENT '城市编号',
    name        varchar(50) NOT NULL COMMENT '城市名称',
    province_id int         NOT NULL COMMENT '省份编号',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX IDX_ID (id ASC) USING BTREE,
    INDEX IDX_P_ID (province_id ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '城市'
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS area
(
    id          int         NOT NULL COMMENT '地区编号',
    name        varchar(50) NOT NULL COMMENT '地区名称',
    city_id     int         NOT NULL COMMENT '城市编号',
    province_id int         NOT NULL COMMENT '省份编号',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX IDX_ID (id ASC) USING BTREE,
    INDEX IDX_P_ID (province_id ASC) USING BTREE,
    INDEX IDX_C_ID (city_id ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '城区地址'
  ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS street
(
    id          int         NOT NULL COMMENT '街道编号',
    name        varchar(50) NOT NULL COMMENT '街道名称',
    area_id     int         NOT NULL COMMENT '地区编号',
    city_id     int         NOT NULL COMMENT '城市编号',
    province_id int         NOT NULL COMMENT '省份编号',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX IDX_ID (id ASC) USING BTREE,
    INDEX IDX_P_ID (province_id ASC) USING BTREE,
    INDEX IDX_C_ID (city_id ASC) USING BTREE,
    INDEX IDX_A_ID (area_id ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '街道'
  ROW_FORMAT = DYNAMIC;


CREATE TABLE IF NOT EXISTS village
(
    id          bigint       NOT NULL,
    name        varchar(200) NULL DEFAULT NULL,
    street_id   int          NULL DEFAULT NULL,
    province_id int          NULL DEFAULT NULL,
    city_id     int          NULL DEFAULT NULL,
    area_id     int          NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE,
    INDEX AREA_ID_IDX (area_id ASC) USING BTREE COMMENT '行政区id索引',
    INDEX STREET_ID_IDX (street_id ASC) USING BTREE COMMENT '街道id索引'
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;



create view address_level3
as
SELECT province.id   as id,
       province.id   AS province_id,
       province.name AS province_name,
       NULL          AS city_id,
       NULL          AS city_name,
       NULL          as area_id,
       NULL          as area_name
FROM province

UNION ALL

SELECT city.id       as id,
       province.id   AS province_id,
       province.name AS province_name,
       city.id       AS city_id,
       city.name     AS city_name,
       NULL          as area_id,
       NULL          as area_name
FROM city
         LEFT JOIN province ON city.province_id = province.id

UNION ALL

SELECT area.id       as id,
       province.id   AS province_id,
       province.name AS province_name,
       city.id       AS city_id,
       city.name     AS city_name,
       area.id       as area_id,
       area.name     as area_name
FROM area
         LEFT JOIN city ON area.city_id = city.id
         LEFT JOIN province ON city.province_id = province.id;



create view address_level4
as
SELECT province.id   as id,
       province.id   AS province_id,
       province.name AS province_name,
       NULL          AS city_id,
       NULL          AS city_name,
       NULL          as area_id,
       NULL          as area_name,
       NULL          as street_id,
       NULL          as street_name
FROM province

UNION ALL

SELECT city.id       as id,
       province.id   AS province_id,
       province.name AS province_name,
       city.id       AS city_id,
       city.name     AS city_name,
       NULL          as area_id,
       NULL          as area_name,
       NULL          as street_id,
       NULL          as street_name
FROM city
         LEFT JOIN province ON city.province_id = province.id

UNION ALL

SELECT area.id       as id,
       province.id   AS province_id,
       province.name AS province_name,
       city.id       AS city_id,
       city.name     AS city_name,
       area.id       as area_id,
       area.name     as area_name,
       NULL          as street_id,
       NULL          as street_name
FROM area
         LEFT JOIN city ON area.city_id = city.id
         LEFT JOIN province ON city.province_id = province.id
UNION ALL
SELECT street.id     as id,
       province.id   AS province_id,
       province.name AS province_name,
       city.id       AS city_id,
       city.name     AS city_name,
       area.id       as area_id,
       area.name     as area_name,
       street.id     as street_id,
       street.name   as street_name
FROM street
         LEFT JOIN area on street.area_id = area.id
         LEFT JOIN city ON area.city_id = city.id
         LEFT JOIN province ON city.province_id = province.id;