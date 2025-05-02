CREATE TABLE season(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    year INTEGER NOT NULL,
    season_status season_status NOT NULL,
    alias VARCHAR(100) NOT NULL
);