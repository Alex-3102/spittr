drop table if exists `test`;

CREATE TABLE `spitter` (
  `id` int(11) PRIMARY KEY auto_increment,
  `username` varchar(20) NOT NULL UNIQUE,
  `password` varchar(20) NOT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
);

create table spittle (
  id integer identity primary key,
  spitter integer not null,
  message varchar(2000) not null,
  postedTime datetime not null,
  foreign key (spitter) references spitter(id)
);

-- insert into `test`(`id`, `name`) values (1, 'cjb3102');