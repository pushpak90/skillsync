USER
-------------------------
id (PK)
name
email
password
role (ADMIN / EMPLOYER / CANDIDATE)

SKILL
-------------------------
id (PK)
name

CANDIDATE_SKILL
-------------------------
id (PK)
user_id (FK → USER.id)
skill_id (FK → SKILL.id)
level (1 to 10)

JOB
-------------------------
id (PK)
title
description
employer_id (FK → USER.id)

JOB_SKILL
-------------------------
id (PK)
job_id (FK → JOB.id)
skill_id (FK → SKILL.id)
required_level (1 to 10)

MATCH_RESULT
-------------------------
id (PK)
job_id (FK → JOB.id)
candidate_id (FK → USER.id)
match_score (0–100)
