drop table if exists tbl_user;
drop table if exists tbl_playlist;
drop table if exists tbl_song;

create table tbl_user (
    us_id int auto_increment  primary key,
    us_name varchar(100) not null,
    us_email varchar(100) unique not null,
    us_password varchar(100) null
);

create table tbl_playlist (
    pl_id int auto_increment  primary key,
    pl_name varchar(100) not null,
    pl_description varchar(1000) null,
    pl_song_id int not null
);

create table tbl_song(
    son_id int auto_increment  primary key,
    son_title varchar(100) not null,
    son_artist varchar(100) not null,
    son_album varchar(100) not null,
    son_year varchar(10) not null,
    son_gender varchar(100) not null
);

alter table tbl_playlist add foreign key (pl_song_id) references tbl_song(son_id);

insert into tbl_user(us_name,us_email,us_password) values('Andres Felipe Mera','andres@andres.com','$2a$10$hTabkNtiNB3WCTHT2Y8DuOLgbS.NXYaxjr2eAxgbMKqVXr4bPqh5O');

insert into tbl_song(son_title,son_artist,son_album,son_year,son_gender) values('Lluvia','Eddie Santiago','Sigo atrevido','1994','Salsa');
insert into tbl_song(son_title,son_artist,son_album,son_year,son_gender) values('casi te envidio','Andy Montañez','Desconocido','1999','Salsa');
insert into tbl_song(son_title,son_artist,son_album,son_year,son_gender) values('aquel viejo motel','David Pabón','Desconocido','1989','Salsa');
insert into tbl_song(son_title,son_artist,son_album,son_year,son_gender) values('DARK ARIA <LV2>','Hiroyuki Sawano','LEveL','2024','Pop');
insert into tbl_song(son_title,son_artist,son_album,son_year,son_gender) values('Youngblood','5 Seconds of Summer','Desconocido','2018','Pop Rock');

insert into tbl_playlist(pl_name,pl_description,pl_song_id) values('Salsa','Salsa clasica',1);
insert into tbl_playlist(pl_name,pl_description,pl_song_id) values('Salsa','Salsa clasica',2);
insert into tbl_playlist(pl_name,pl_description,pl_song_id) values('Salsa','Salsa clasica',3);
insert into tbl_playlist(pl_name,pl_description,pl_song_id) values('Pop rock','Pop rock',4);
insert into tbl_playlist(pl_name,pl_description,pl_song_id) values('Pop rock','Pop rock',5);
