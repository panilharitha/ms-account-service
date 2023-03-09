CREATE TABLE user (
  user_id bigint NOT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE currency (
  id bigint NOT NULL,
  create_date date DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  update_date date DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE account (
  account_id bigint NOT NULL,
  account_name varchar(255) DEFAULT NULL,
  account_number varchar(255) DEFAULT NULL,
  account_type varchar(255) DEFAULT NULL,
  balance_date date DEFAULT NULL,
  opaning_balance double DEFAULT NULL,
  currency_id bigint DEFAULT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (account_id),
  KEY fk_currency_id (currency_id),
  KEY fk_user_id (user_id),
  CONSTRAINT fk_currency_id FOREIGN KEY (currency_id) REFERENCES currency (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE transaction (
  transaction_id bigint NOT NULL,
  amount double DEFAULT NULL,
  transaction_narrative varchar(255) DEFAULT NULL,
  transaction_type varchar(255) DEFAULT NULL,
  value_date date DEFAULT NULL,
  account_id bigint NOT NULL,
  PRIMARY KEY (transaction_id),
  KEY fk_account_id (account_id),
  CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES account (account_id)
);