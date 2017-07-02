create table companies (
    id bigint auto_increment NOT NULL,
    name varchar(30) not null UNIQUE,
    PRIMARY KEY(id)
);

create table address (
    id bigint auto_increment NOT NULL,
    province varchar(30),
    city varchar(30),
    street varchar(30),
    PRIMARY KEY(id)
);