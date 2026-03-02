CREATE TABLE player (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	team_id INTEGER NOT NULL,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	height_inches NUMERIC NOT NULL,
	weight_pounds NUMERIC NOT NULL,
	jersey_number INTEGER NOT NULL,
	position TEXT NOT NULL,
	CONSTRAINT player_team_FK FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE coach (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL
);

CREATE TABLE team (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	team_name TEXT NOT NULL,
	season TEXT NOT NULL,
	coach_id INTEGER NOT NULL,
	CONSTRAINT team_coach_FK FOREIGN KEY (coach_id) REFERENCES coach(id)
);

CREATE TABLE game (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    home_team_id INTEGER NOT NULL,
    away_team_id INTEGER NOT NULL,
    home_score INTEGER NOT NULL,
    away_score INTEGER NOT NULL,
    date TEXT NOT NULL,
    CONSTRAINT game_team_FK FOREIGN KEY (team_id) REFERENCES team(id),
    CONSTRAINT game_team_FK_1 FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE player_stats (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    player_id INTEGER NOT NULL,
    game_id INTEGER NOT NULL,
    points INTEGER NOT NULL,
    assists INTEGER NOT NULL,
    rebounds INTEGER NOT NULL,
    steals INTEGER NOT NULL,
    blocks INTEGER NOT NULL,
    CONSTRAINT player_stats_player_FK FOREIGN KEY (player_id) REFERENCES player(id),
    CONSTRAINT player_stats_game_FK FOREIGN KEY (game_id) REFERENCES game(id)
);