CREATE KEYSPACE IF NOT EXISTS stocks WITH REPLICATION = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};