create table if not exists BankAccount (
  accountNumber varchar,
  balance double,
  timeCreated integer
);

create table if not exists Transactions (
  accountNumber varchar,
  timeCreated bigint,
  amount integer,
  description varchar
);