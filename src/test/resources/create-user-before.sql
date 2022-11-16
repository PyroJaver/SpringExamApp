TRUNCATE table users;

insert into users(id, name, surname, username, password, yearofbirth, role ) values
(1, 'testName', 'testSurname', 'testUsername', '$2a$10$nB3wnvRqhP/L8110p1HzueHthVXbHfmmfnGsQwcLO6kz6gFoLO/d6', 2000, 'ROLE_ADMIN'),
(2, 'testName2', 'testSurname2', 'testUsername2', '$2a$10$XOqLJb0V3EvIACdSdR3Hful2NKAUvfMfrWlhinKw6AcNB8U4syiMi', 2001, 'ROLE_USER');

