-- Insert employees
INSERT INTO employee (id, name, team_id) VALUES (1, 'Jon', NULL);
INSERT INTO employee (id, name, team_id) VALUES (2, 'Jil', NULL);
INSERT INTO employee (id, name, team_id) VALUES (3, 'Ben', NULL);
INSERT INTO employee (id, name, team_id) VALUES (4, 'Hank', NULL);

-- Insert teams (initially null team_lead_id)
INSERT INTO team (id, name, team_lead_id) VALUES (1, 'Team A', NULL);
INSERT INTO team (id, name, team_lead_id) VALUES (2, 'Team B', NULL);

-- Update employees to reference teams
UPDATE employee SET team_id = 1 WHERE id IN (1, 2);
UPDATE employee SET team_id = 2 WHERE id IN (3, 4);

-- Update teams to reference team leads
UPDATE team SET team_lead_id = 1 WHERE id = 1;
UPDATE team SET team_lead_id = 4 WHERE id = 2;