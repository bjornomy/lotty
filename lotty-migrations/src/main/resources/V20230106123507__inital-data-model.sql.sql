-- one lottery can have 0..* drawings and 0..* participants
-- participants can attend 0..* lotteries
-- one drawing can have 0..* prices

create table participant (
  id         bigint    not null primary key,
  name       varchar   not null,
  created_at timestamp not null default current_timestamp
)

create table lottery (
  id         bigint    not null primary key,
  name       varchar   not null,
  created_by bigint    not null references participant (id)
  created_at timestamp not null default current_timestamp
);

insert into participant (id, name) values (0, 'DEFAULT_USER')

create table lottery_participant (
  lottery_id     bigint not null references lottery (id)     on delete cascade,
  participant_id bigint not null references participant (id) on delete cascade
  primary key (lottery_id, participant_id)
)

create table drawing (
  id         bigint    not null primary key,
  winner_id  bigint    not null references participant (id) on delete set 0,
  lottery_id bigint    not null references lottery (id)     on delete cascade,
  drawn_at   timestamp not null default current_timestamp
);

create table price (
  id          bigint  not null primary key,
  price_type  varchar not null,
  description varchar not null,
  drawing_id  bigint  not null references drawing (id) on delete cascade
)

