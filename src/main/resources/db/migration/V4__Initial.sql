

INSERT INTO account (account_id, account_name, account_number, account_type, balance_date, opaning_balance, currency_id, user_id)
VALUES(1, 'SGSavings726', '585309209', 'SAVINGS', '2018-11-18', 84327.51, 2, 2);
INSERT INTO account (account_id, account_name, account_number, account_type, balance_date, opaning_balance, currency_id, user_id)
VALUES(2, 'AUSavings933', '791066619', 'SAVINGS', '2018-11-18', 88005.93, 1, 2);
INSERT INTO account (account_id, account_name, account_number, account_type, balance_date, opaning_balance, currency_id, user_id)
VALUES(3, 'AUCurrent433', '321143048', 'CURRENT', '2018-11-18', 38010.62, 1, 2);
INSERT INTO account (account_id, account_name, account_number, account_type, balance_date, opaning_balance, currency_id, user_id)
VALUES(4, 'SGCurrent166', '347786244', 'CURRENT', '2018-11-18', 50664.65, 2, 2);
INSERT INTO account (account_id, account_name, account_number, account_type, balance_date, opaning_balance, currency_id, user_id)
VALUES(5, 'AUCurrent374', '680168913', 'CURRENT', '2018-11-18', 41327.28, 1, 2);
INSERT INTO account (account_id, account_name, account_number, account_type, balance_date, opaning_balance, currency_id, user_id)
VALUES(6, 'AUSavings938', '136056165', 'SAVINGS', '2018-11-18', 48928.79, 1, 3);

INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(1, 9540.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(2, 9541.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(3, 9542.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(4, 780.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(5, 2345.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(6, 4325.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(7, 321.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(8, 5432.98, 'test narrative', 'DEBIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(9, 3425.98, 'test narrative', 'CREDIT', '2012-06-12', 1);
INSERT INTO transaction (transaction_id, amount, transaction_narrative, transaction_type, value_date, account_id)
VALUES(10, 342.98, 'test narrative', 'CREDIT', '2012-06-12', 1);


