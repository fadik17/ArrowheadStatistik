DROP database ahDB;

CREATE DATABASE IF NOT EXISTS ahDB;

USE ahDB;

CREATE TABLE IF NOT EXISTS CampaignStatus (
	id INT NOT NULL AUTO_INCREMENT,
    time LONG,
    error_code LONG,
    season LONG,
    points LONG,
    points_taken LONG,
    points_max LONG,
    status VARCHAR(50),
    introduction_order LONG,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS DefendEvent (
	id INT NOT NULL AUTO_INCREMENT,
    time LONG,
    error_code LONG,
    season LONG,
    event_id LONG,
    start_time LONG,
    end_time LONG,
    region LONG,
    enemy LONG,
    points_max LONG,
    points LONG,
    status varchar(50),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS AttackEvent (
	id INT NOT NULL auto_increment,
    time LONG,
    error_code LONG,
    season LONG,
    event_id LONG,
    start_time LONG,
    end_time LONG,
    enemy LONG,
    points_max LONG,
    points LONG,
    status varchar(50),
    players_at_start LONG,
    max_event_id LONG,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Statistics(
	id INT NOT NULL auto_increment,
    time LONG,
    error_code LONG,
    season  LONG,
    season_duration LONG,
    enemy LONG,
    players LONG,
    total_unique_players LONG,
    missions LONG,
    successful_missions LONG,
    total_mission_difficulty LONG,
    completed_planets LONG,
    defend_events LONG,
    successful_defend_events LONG,
    attack_events int,
    successful_attack_events LONG,
    deaths LONG,
    kills LONG,
    accidentals LONG, 
    shots LONG,
    hits LONG,
	PRIMARY KEY(id)
);


