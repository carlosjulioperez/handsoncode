drop table if exists post;
drop table if exists topic;
  
create table topic (
   id bigint auto_increment primary key,
   name varchar(255)
);

create table post (
   id bigint auto_increment primary key,
   topic_id bigint,
   title varchar(16536),
   text varchar(16536),
   foreign key (topic_id) references topic(id)
);