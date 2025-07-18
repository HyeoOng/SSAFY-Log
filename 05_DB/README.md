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
![erd](/uploads/baca23b189517eda05db7550ccf37e90/erd.png)<br />

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
![스크린샷_2024-10-07_022407](/uploads/26cfb6445bb0c1b7cbdb875d6e81d32a/스크린샷_2024-10-07_022407.png)

### 2. 나의 정보 페이지
로그인이 성공적으로 완료되면 나의 정보 페이지로 이동합니다.<br/>
현재 내가 가입된 스터디 목록을 확인할 수 있고, <br/>
각 스터디 이름을 클릭하면 스터디 멤버들의 현재 인증 현황을 확인할 수 있습니다.<br />
오늘 날짜에 공부한 내용을 등록한 멤버는 이름 옆 체크 표시✅가 등록됩니다.<br/>
![스크린샷_2024-10-07_022605](/uploads/cf536ed6836b9446e1e2054b546007a9/스크린샷_2024-10-07_022605.png)


### 3. 전체 게시글 페이지
전체 게시글 버튼을 클릭하면 모든 작성자가 등록한 글을 확인할 수 있습니다.<br/>
![스크린샷_2024-10-07_022710](/uploads/e3d177168fdca751b6464171d20dd85c/스크린샷_2024-10-07_022710.png)<br/>
글을 클릭하면 등록된 게시글의 자세한 내용을 확인할 수 있습니다.<br>
![스크린샷_2024-10-07_022751](/uploads/14d8b7059f37fcdd8eec6bcb2831e1aa/스크린샷_2024-10-07_022751.png)
			
				



---

## 3️⃣ Reviews

### 🐰 박다희
### 🐰 나혜원
이번 프로젝트를 통해 클라이언트 중심의 동적 화면 구성과 백엔드 데이터 흐름을 모두 경험하며, 웹 애플리케이션의 전체 구조를 실습해볼 수 있는 값진 시간이었습니다. 특히 CSR 방식으로 페이지를 구성하면서 JavaScript를 활용한 동적 렌더링과 fetch API를 통한 비동기 통신이 처음에는 낯설었지만, 데이터를 받아와 화면을 직접 업데이트하는 흐름을 체득하면서 클라이언트 개발에 대한 감각을 익힐 수 있었습니다.

백엔드에서는 JDBC를 이용한 MySQL 연동을 통해 로그인 및 게시글 CRUD 로직을 구현했는데, 이 과정에서 PreparedStatement로 보안에 신경 쓰며 쿼리를 처리하는 습관을 들일 수 있었습니다. 처음에는 SQL 구문 작성과 예외 처리에 어려움을 겪었지만, 직접 여러 테스트를 반복하면서 자연스럽게 익히게 되었습니다.

또한, MVC 아키텍처를 적용해 역할을 명확히 나누는 구조의 중요성도 실감할 수 있었습니다. 컨트롤러에서 요청을 받고, 서비스와 DAO를 통해 데이터를 처리한 뒤, 최종적으로 클라이언트에 필요한 정보만 전달하는 흐름이 반복될수록 점점 더 구조적인 사고가 가능해졌습니다.

무엇보다도, 오늘 공부한 사람을 한눈에 확인할 수 있도록 ✅ 표시로 시각화하는 기능이나, 모든 회원의 게시글을 모아보는 기능 등 사용자 입장에서 편의성과 직관성을 고려한 UI 흐름을 고민했던 점이 인상 깊었습니다. 단순히 기능 구현에 그치지 않고, 어떻게 하면 사용자에게 더 명확하게 정보를 전달할 수 있을지 계속해서 고민했던 과정이 기억에 남습니다.

아쉬운 점은 시간이 다소 부족해 일부 UI의 정교함이나 예외 처리 등을 충분히 다듬지 못한 부분이 있다는 것이지만, 전체적인 프로젝트 흐름을 주도하고 실질적인 기능 구현을 직접 해본 경험은 앞으로의 개발 역량 향상에 큰 기반이 될 것이라 확신합니다.
