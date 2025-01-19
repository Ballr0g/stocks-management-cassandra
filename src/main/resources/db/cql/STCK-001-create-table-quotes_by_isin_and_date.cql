CREATE TABLE IF NOT EXISTS stocks.quotes_by_isin_and_date(
    isin            TEXT,
    period_id       TEXT, -- used to limit partition size as 'dd-mm-yyyy'
    quote_time      TIMESTAMP,
    bid             DECIMAL,
    ask             DECIMAL,
    energy_level    DECIMAL,
    PRIMARY KEY ((isin, period_id), quote_time, bid, ask)
) WITH CLUSTERING ORDER BY (quote_time DESC);