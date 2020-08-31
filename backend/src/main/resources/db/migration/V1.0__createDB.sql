create sequence hibernate_sequence start with 1 increment by 1;

create table movie (
       id bigint not null,
        average_stars double not null,
        director varchar(255),
        genre varchar(255),
        release_year bigint check (release_year<=2020 AND release_year>=1888),
        review_count integer not null,
        title varchar(255),
        total_stars double not null,
        primary key (id)
    );

create table review (
       id bigint not null,
        created_date timestamp,
        review_text varchar(255),
        stars integer not null check (stars<=5 AND stars>=1),
        movie_information_id bigint not null,
        review_owner_userid varchar(255),
        primary key (id)
    );

create table users (
       userid varchar(255) not null,
        email varchar(255),
        enabled boolean not null,
        hashed_password varchar(255) not null,
        name varchar(128),
        surname varchar(128),
        primary key (userid)
    );

create table users_roles (
       users_userid varchar(255) not null,
        roles varchar(255)
    );


alter table users
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

alter table review
       add constraint FKfdjdbung6o375ucjqjy80vm15
       foreign key (movie_information_id)
       references movie;

alter table review
       add constraint FKodd4xo1gx9y3h78exo3as0ces
       foreign key (review_owner_userid)
       references users;

alter table users_roles
       add constraint FKnqgxij5udu4xrsqju9dtbc8pr
       foreign key (users_userid)
       references users;