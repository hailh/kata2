create table if not exists BankAccount (
  accountNumber varchar,
  balance double,
  timestamp integer
);

create table if not exists Transaction (
  accountNumber varchar,
  timestamp integer,
  amount integer,
  description varchar
);