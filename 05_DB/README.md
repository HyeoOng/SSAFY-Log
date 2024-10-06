<img src="https://capsule-render.vercel.app/api?type=cylinder&height=150&color=gradient&text=DB%20PROJECT&fontAlignY=45&desc=SSAFY%20STUDY&descAlignY=77"/>

# 📚 SSAFY STUDY(스터디 인증 웹 사이트) 구현(With DB)

# 1️⃣ **Introduction**

## 📌 **Project Summary**

> 1. <br> **혼자 공부한 내용을 기록하고 공유하는 스터디 인증 웹페이지**를 제작 하였습니다.
> 2. <br> 해당 웹페이지에는 **로그인, 내 스터디 정보 조회, 모든 게시글 조회** 기능이 구현되어 있습니다.
> 3. <br> 로그인을 하면 **"${회원이름}님의 스터디 페이지"** 라는 문구와 함께 **가입된 스터디 목록과 함께 스터디 회원의 공부 내용**을 볼 수 있습니다.
> 4. <br> 오늘 공부한 내용이 등록되었다면 이름 옆에 **체크 표시✅**가 등록이 되고, 이를 통해 **스터디를 관리**할 수 있습니다.
> 5. <br> 전체 글 보기 버튼을 통해 모든 회원이 작성한 글을 조회할 수 있습니다.
> 5. <br> 생성형 AI를 통해 더미 데이터를 생성하였습니다.

<br>


## 📌 **Team**

> **서울 9반** 나혜원
>
> **서울 9반** 박다희

<br>

## 📌 **Stack**

> **Front** :
> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black"/> 
> <br> 
> <br>
> **Back** : 
> <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white"/>
> <br>
> <br>
> **DB** : 
> <img src="https://img.shields.io/badge/MySQL-00000F?style=flat-square&logo=mysql&logoColor=white"/>
> <br>
---

# 2️⃣ Features

## ⭐️ 디렉토리 구조

```
└─main
  ├─java
  │  └─web
  │      ├─model
  │      │  ├─comment
  │      │  │      Comment.java
  │      │  │      CommentDao.java
  │      │  │      CommentService.java
  │      │  │
  │      │  ├─controller
  │      │  │      MainServlet.java
  │      │  │      PostServlet.java
  │      │  │
  │      │  ├─post
  │      │  │      Post.java
  │      │  │      PostDao.java
  │      │  │      PostDaoImpl.java
  │      │  │      PostService.java
  │      │  │      PostServiceImpl.java
  │      │  │
  │      │  └─user
  │      │          Study.java
  │      │          User.java
  │      │          UserDao.java
  │      │          UserDaoImpl.java
  │      │          UserService.java
  │      │          UserServiceImpl.java
  │      │
  │      └─util
  │              DBUtil.java
  │
  ├─resources
  └─webapp
    │  index.html
    │  index.js
    │  mypage.css
    │  mypage.html
    │  mypage.js
    │  post.html
    │  post.js
    │  signin.html
    │  signin.js
    │
    ├─META-INF
    │      MANIFEST.MF
    │
    └─WEB-INF
        │  web.xml
        │
        └─classes
```
## ⭐️ DB 설계

**Table : `user`**

| 컬럼명   | 데이터 타입  | 설명                          |
| -------- | -------------| ----------------------------- |
| `userId`     | VARCHAR(20) (PK)     | 유저 ID (NOT NULL)   |
| `pw`     | VARCHAR(300)  | 유저 PW (NOT NULL)  |
| `name`   | VARCHAR(10)  | 유저 이름  (NOT NULL)            |

**Table : `study`**

| 컬럼명   | 데이터 타입   | 설명                          |
| -------- | ------------- | ----------------------------- |
| `study`     | INT (PK)  | 스터디 ID (Auto_Increment)                    |
| `studyName`   | VARCHAR(50)   | 스터디 이름 (NOT NULL)                 |

**Table : `studymember`**

| 컬럼명     | 데이터 타입  | 설명                          |
| ---------- | -------------| ----------------------------- |
| `id`       | INT (PK)     | 멤버 index (Auto_Increment)  |
| `study`   | INT | 스터디 ID                        |

**Table : `post`**

| 컬럼명     | 데이터 타입  | 설명                          |
| ---------- | -------------| ----------------------------- |
| `postId`       | INT (PK)     | 게시글 ID (Auto_Increment)  |
| `userId`   | VARCHAR(20)  | 유저 ID                        |
| `date`       | DATE     | 게시글 등록 날짜  |
| `problemNumber`       | VARCHAR(50)      | 문제 번호  |
| `code`       | TEXT     | 코드 내용 |
| `content`       | TEXT     | 코드 설명 |

**Table : `comment`**

| 컬럼명     | 데이터 타입  | 설명                          |
| ---------- | -------------| ----------------------------- |
| `commentId`       | INT (PK)     | 댓글 ID (Auto_Increment)  |
| `postId`   | INT  | 게시글 ID                        |
| `commentUserId`       | VARCHAR(20)     | 댓글 작성 유저 ID |
| `comment`       | VARCHAR(500)      | 댓글 내용  |
| `commentDate`       | DATETIME     | 댓글 등록 날짜 |

## ⭐️ 기능 설명

### 1. Client Side Rendering (CSR) 구현
> 본 프로젝트는 **Client Side Rendering (CSR)** 방식을 사용하여 화면을 구성합니다. **CSR**은 서버에서 HTML을 완전히 렌더링하지 않고, 클라이언트 측에서 **JavaScript**를 사용해 데이터를 받아와 화면을 동적으로 그립니다.
> 
> - **구현 방식**: **`javascript`** 파일을 이용하여 **fetch API** 를 사용해 서버와 비동기 통신을 수행합니다. 클라이언트는 필요한 데이터를 JSON 형식으로 서버에서 받아오며, 이 데이터를 바탕으로 DOM을 조작하여 페이지의 특정 부분을 업데이트합니다. 


### 2. MySQL 연결
> 애플리케이션은 **JDBC**를 통해 **MySQL**과 연결되며, 회원가입, 로그인, 게시판 작성, 수정, 삭제 등의 모든 데이터 처리는 MySQL 데이터베이스에 저장되고 조회됩니다. 
> 
> - **연결 방식**: **JDBC (Java Database Connectivity)** 를 사용하여 MySQL에 접근하고, **SQL 쿼리**를 통해 데이터를 주고받습니다. **PreparedStatement**를 사용하여 보안상 안전하게 쿼리를 실행하며, 사용자가 입력한 데이터는 SQL 인젝션을 방지하기 위해 자동으로 필터링됩니다.
> - **CRUD 처리**: 
  >>- **Create**: 사용자가 새로운 글을 작성할 때, **INSERT** 쿼리를 사용하여 새로운 데이터가 데이터베이스에 저장됩니다.
  >>- **Read**: 사용자가 로그인하거나 게시글 목록을 조회할 때, **SELECT** 쿼리를 사용하여 필요한 데이터를 조회합니다.
  >>- **Update**: 사용자가 게시글을 수정할 때, **UPDATE** 쿼리를 사용하여 기존 데이터를 갱신합니다.
 >> - **Delete**: 사용자가 게시글을 삭제할 때, **DELETE** 쿼리를 사용하여 데이터를 삭제합니다.

### 3. MVC 패턴 구현
> 프로젝트는 **MVC (Model-View-Controller)** 패턴에 따라 구성되어 있으며, 각 요소가 명확히 분리되어 역할을 수행합니다:
> 
> - **Model (모델)**: 
  >>- 비즈니스 로직을 담당하며, **DAO, Service** 계층이 포함됩니다.
  >>- **DAO (Data Access Object)** 는 데이터베이스와 상호작용하며, MySQL과의 CRUD 작업을 처리합니다.
 >> - **Service**는 비즈니스 로직을 처리하며, DAO를 이용하여 데이터를 관리합니다.
> - **View (뷰)**: 
 >> - CSR 방식이 적용된 **`html`** 과 **`javascript`** 파일로 구성되어 있습니다.
  >>- 클라이언트 측에서 서버로부터 데이터를 받아와 동적으로 화면에 표시되며, DOM을 조작하여 사용자에게 필요한 정보를 제공합니다.
> - **Controller (컨트롤러)**: 
>> - **MainServlet** 과 **PostServlet** 이 클라이언트 요청이 들어오면 이를 분석하고, 함수를 호출하여, 필요한 데이터를 Model에서 받아 View에 전달합니다.

<br/>

## ⭐️ 주요 기능 화면 이미지 
### 1. 로그인 페이지
로그인할 수 있는 페이지 입니다. 유저의 ID와 PW를 입력한 후 버튼을 누르면 인증 절차를 통해 페이지에 로그인 할 수 있습니다.<br />
![image](/uploads/17bd4404e4dadcdbb87d8cd07938b289/image.png)

### 2. 나의 정보 페이지
로그인이 성공적으로 완료되면 나의 정보 페이지로 이동합니다.<br/>
현재 내가 가입된 스터디 목록을 확인할 수 있고, <br/>
각 스터디 이름을 클릭하면 스터디 멤버들의 현재 인증 현황을 확인할 수 있습니다.<br />
오늘 날짜에 공부한 내용을 등록한 멤버는 이름 옆 체크 표시✅가 등록됩니다.<br/>
![image](/uploads/a17bdf826222d60e328b7ab940396763/image.png)


### 3. 전체 게시글 페이지
전체 게시글 버튼을 클릭하면 모든 작성자가 등록한 글을 확인할 수 있습니다.<br/>
![image](/uploads/73145d67b6d79fdbcb8dc466833ddb5b/image.png)<br/>
글을 클릭하면 등록된 게시글의 자세한 내용을 확인할 수 있습니다.<br>
![image](/uploads/190e3eae98f507c7767b63b53e3bc55a/image.png)
			
				



---

## 3️⃣ Reviews

### 🐰 나혜원


### 🐰 박다희

