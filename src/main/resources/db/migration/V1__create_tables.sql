-- Table `team`
CREATE TABLE team (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    team_lead_id BIGINT
);

-- Table `employee`
CREATE TABLE employee (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    team_id BIGINT,
    CONSTRAINT fk_employee_team FOREIGN KEY (team_id) REFERENCES team(id)
);

-- Add foreign key constraint for team_lead_id
ALTER TABLE team
ADD CONSTRAINT fk_team_lead FOREIGN KEY (team_lead_id) REFERENCES employee(id);