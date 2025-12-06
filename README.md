# SkillSync – Job–Candidate Skill Matching System  
A Spring Boot application that intelligently matches job requirements with candidate skills using a scoring algorithm.  
The system supports **Role-Based Access Control (RBAC)**, **JWT authentication**, **skill management**, **candidate skill profiles**, **job posting**, and **automated match score generation**.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- JWT-based login and registration  
- Role support:
  - **ADMIN** – Manage skills, view match results  
  - **EMPLOYER** – Create jobs, view candidate matches  
  - **CANDIDATE** – Add skills, view job matches  
- Secured endpoints with Spring Security + PreAuthorize  

### 📚 Skill Management
- Admin adds new skills (Java, Spring Boot, SQL, etc.)  
- Public API to view skills  

### 👤 Candidate Skill Profiles
- Candidates add their skill levels (0–10)  
- Skill matrix stored for matching algorithm  

### 💼 Job Management
- Employers create jobs with required skill levels  
- Jobs public to view  

### 🧠 Matching Algorithm  
This system uses a weighted similarity algorithm:

```
score = avg( min(candidateLevel / requiredLevel, 1) ) * 100
```

Example:

Java required 7, candidate has 8 → score capped at 100  
Spring Boot required 8, candidate has 7 → 87.5  

Final score: **93.75%**

---

## 🛠️ Tech Stack

| Layer | Technology |
|------|------------|
| Backend | Spring Boot 3.4 |
| Security | Spring Security + JWT |
| Build Tool | Gradle |
| Database | MySQL |
| ORM | Hibernate / JPA |
| Architecture | Controller → Service → Repository |
| Language | Java 21 |

---

## 📦 Project Structure

```
src/main/java/com/skillsync/
│
├── Controller/
│   ├── AuthController
│   ├── SkillController
│   ├── CandidateSkillController
│   ├── JobController
│   └── MatchController
│
├── Service/
│   ├── AuthService
│   ├── SkillService
│   ├── CandidateSkillService
│   ├── JobService
│   └── MatchService
│
├── Repository/
│   ├── UserRepository
│   ├── SkillRepository
│   ├── CandidateSkillRepository
│   ├── JobRepository
│   ├── JobSkillRepository
│   └── MatchResultRepository
│
├── Security/
│   ├── JwtAuthenticationFilter
│   ├── JwtService
│   ├── SecurityConfig
│   └── CustomUserDetailsService
│
└── Entity/
    ├── User
    ├── Skill
    ├── Job
    ├── JobSkill
    ├── CandidateSkill
    └── MatchResult
```

---

## 🌐 API Endpoints Summary

### 🔹 **Authentication**
| Method | Endpoint | Role | Description |
|--------|----------|-------|-------------|
| POST | `/api/auth/register` | Public | Register user |
| POST | `/api/auth/login` | Public | Login & receive JWT |

---

### 🔹 **Skills**
| Method | Endpoint | Role |
|--------|-----------|------|
| POST | `/api/skills` | ADMIN |
| GET | `/api/skills` | Public |

---

### 🔹 **Candidate Skills**
| Method | Endpoint | Role |
|--------|-----------|------|
| POST | `/api/candidate-skills` | CANDIDATE |
| GET | `/api/candidate-skills/{candidateId}` | CANDIDATE |

---

### 🔹 **Jobs**
| Method | Endpoint | Role |
|--------|-----------|------|
| POST | `/api/jobs` | EMPLOYER |
| GET | `/api/jobs` | Public |
| GET | `/api/jobs/{id}` | Public |

---

### 🔹 **Matching**
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| GET | `/api/match/job/{jobId}` | EMPLOYER/ADMIN | Best candidates for job |
| GET | `/api/match/candidate/{candidateId}` | CANDIDATE | Best jobs for candidate |
| GET | `/api/match/results` | ADMIN | Match history |

---

## 🧠 Algorithm Details

1. Loop through every required job skill  
2. Find candidate skill with same skillId  
3. Calculate:

```
match = min(candidateLevel / requiredLevel, 1)
```

4. Combine all averages:

```
finalScore = (sum of matches / number of skills) * 100
```

---

## 🗄️ Database ER Diagram (Text Mode)

```
Users (id, name, email, password, role)
    │
    ├──< CandidateSkills (candidate_id, skill_id, level)
    │               │
    │               └── Skills
    │
    ├──< Jobs (id, title, description, employer_id)
    │               │
    │               └──< JobSkills (job_id, skill_id, required_level)
    │
    └──< MatchResults (job_id, candidate_id, matchScore)
```

---

## ▶️ How to Run the Project

### 1. Clone the repository
```
git clone https://github.com/yourusername/skillsync.git
cd skillsync
```

### 2. Configure MySQL in `application.properties`
```
spring.datasource.url=jdbc:mysql://localhost:3306/skillsync
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
app.jwt.secret=YOUR_SECRET_KEY
app.jwt.expiration=86400000
```

### 3. Build and run
```
./gradlew bootRun
```

App starts on:
```
http://localhost:8080
```

---

## 💼 Resume-Ready Bullet Points

- Developed a **role-based skill matching platform** using Spring Boot, JWT, and MySQL.
- Built RESTful APIs with secure access using **Spring Security + PreAuthorize**.
- Implemented a **matching algorithm** to compute candidate-job compatibility scores.
- Designed normalized database schema with multiple relationships.
- Added persistence for match history through the `match_results` entity.
- Ensured clean architecture with Controller → Service → Repository pattern.
- Documented the project with a professional README and Postman Collection.

---

## 📌 Future Enhancements

- Admin analytics dashboard  
- Pagination & filtering for job listing  
- Docker support  
- Deployment on Render/AWS  
- ML-based recommendation  

---

## 👨‍💻 Author
**Pushpak Ashwin Fasate**  
Java | Spring Boot Developer  

---

# ✔ README.md Complete
