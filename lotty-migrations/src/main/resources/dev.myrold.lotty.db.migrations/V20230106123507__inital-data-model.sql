-- participants can attend/create 0..* lotteries
-- one lottery can have 0..* drawings and 0..* participants, and has 1..1 schedules
-- one schedule can be valid for 0..* lotteries
-- one drawing can have 0..* prices (you can draw multiple prices in one drawing)

create table participant (
  id               bigint    not null primary key,
  email            varchar   not null,
  participant_name varchar   not null,
  picture_url      varchar,
  open_id_identity varchar   not null,
  provider         varchar   not null,
  created_by       varchar   not null,
  created_at       timestamp not null default current_timestamp,
  modified_by      varchar   not null,
  modified_at      timestamp not null default current_timestamp,
  version          bigint    not null default 0,
  constraint uq_participant_provider_id unique (open_id_identity, provider)
);

-- we will do a lot of participant lookup by this id
create index idx_participant_provider_id on participant (open_id_identity, provider);

-- create default user
insert into participant (id, email, participant_name, open_id_identity, provider, created_by, modified_by)
values (0, 'NONE', 'DEFAULT_USER', '0', 'NONE', 'BASE_MIGRATION', 'BASE_MIGRATION');

create table schedule (
  id          bigint    not null primary key,
  frequency   varchar   not null,
  target      varchar   not null,
  created_by  varchar   not null,
  created_at  timestamp not null default current_timestamp,
  modified_by varchar   not null,
  modified_at timestamp not null default current_timestamp,
  version     bigint    not null default 0,
  unique (frequency, target)
);

create table lottery (
  id                 bigint        not null primary key,
  lottery_name       varchar       not null,
  participation_fee  decimal (8,2) not null,
  schedule_id        bigint        not null references schedule (id),
  ending_at          timestamp     not null,
  owned_by_id        bigint        not null references participant (id),
  created_by         varchar       not null,
  created_at         timestamp     not null default current_timestamp,
  modified_by        varchar       not null,
  modified_at        timestamp     not null default current_timestamp,
  version            bigint        not null default 0
);

create table lottery_participant (
  lottery_id       bigint    not null           references lottery (id)     on delete cascade,
  participant_id   bigint    not null default 0 references participant (id) on delete set default,
  payment_received boolean   not null default false,
  created_by       varchar   not null,
  created_at       timestamp not null default current_timestamp,
  modified_by      varchar   not null,
  modified_at      timestamp not null default current_timestamp,
  version          bigint    not null default 0,
  primary key (lottery_id, participant_id)
);

create table drawing (
  id          bigint    not null primary key,
  lottery_id  bigint    not null references lottery (id) on delete cascade,
  drawn_at    timestamp not null default current_timestamp,
  created_by  varchar   not null,
  created_at  timestamp not null default current_timestamp,
  modified_by varchar   not null,
  modified_at timestamp not null default current_timestamp,
  version     bigint    not null default 0
);

create table price (
  id          bigint    not null primary key,
  price_name  varchar   not null,
  description varchar   not null,
  drawing_id  bigint    not null           references drawing (id)     on delete cascade,
  winner_id   bigint    not null default 0 references participant (id) on delete set default,
  created_by  varchar   not null,
  created_at  timestamp not null default current_timestamp,
  modified_by varchar   not null,
  modified_at timestamp not null default current_timestamp,
  version     bigint    not null default 0
);

