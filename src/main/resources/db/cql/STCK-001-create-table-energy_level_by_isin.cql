CREATE TABLE IF NOT EXISTS stocks.energy_level_by_isin(
    isin            TEXT,
    quote_time      TIMESTAMP,
    energy_level    DECIMAL,
    PRIMARY KEY ((isin))
);