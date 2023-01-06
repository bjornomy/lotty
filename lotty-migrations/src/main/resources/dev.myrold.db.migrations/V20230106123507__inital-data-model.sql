-- participants can attend/create 0..* lotteries
-- one lottery can have 0..* drawings and 0..* participants, and has 1..1 schedules
-- one schedule can be valid for 0..* lotteries
-- one drawing can have 0..* prices (you can draw multiple prices in one drawing)

create table participant (
  id               bigint    not null primary key,
  oauth_identity   varchar   not null,
  participant_name varchar   not null,
  created_at       timestamp not null default current_timestamp,
  version          bigint    not null default 1
);

-- create default user
insert into participant (id, oauth_identity, participant_name) values (0, 'NONE', 'DEFAULT_USER');

create table schedule (
  id        bigint  not null primary key,
  frequency varchar not null,
  target    varchar not null,
  version   bigint  not null default 1,
  unique (frequency, target)
);

create table lottery (
  id                 bigint        not null primary key,
  lottery_name       varchar       not null,
  participation_fee  decimal (4,2) not null,
  schedule_id        bigint        not null references schedule (id),
  ending_at          timestamp     not null,
  created_by_id      bigint        not null references participant (id),
  created_at         timestamp     not null default current_timestamp,
  version            bigint        not null default 1
);

create table lottery_participant (
  lottery_id       bigint not null           references lottery (id)     on delete cascade,
  participant_id   bigint not null default 0 references participant (id) on delete set default,
  payment_received boolean not null,
  primary key (lottery_id, participant_id)
);

create table drawing (
  id         bigint    not null primary key,
  winner_id  bigint    not null default 0 references participant (id) on delete set default,
  lottery_id bigint    not null           references lottery (id)     on delete cascade,
  drawn_at   timestamp not null default current_timestamp,
  version    bigint    not null default 1
);

create table price (
  id          bigint  not null primary key,
  price_type  varchar not null,
  description varchar not null,
  drawing_id  bigint  not null references drawing (id) on delete cascade,
  version     bigint  not null default 1
);

