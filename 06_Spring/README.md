<img src="https://capsule-render.vercel.app/api?type=cylinder&height=150&color=gradient&text=SPRING%20PROJECT&fontAlignY=45&desc=SSAFY%20Hospital%20Management&descAlignY=77"/>

# 🥼 AI 의사 진단 + 병원 관리 시스템

# 1️⃣ **Introduction**

## 📌 **Project Summary**

> 1. <br> **환자와 의료진, 진료 과목을 통합적으로 관리**하는 프로그램을 제작 하였습니다.
> 2. <br> 해당 프로젝트는 **Spring 과목의 월말 평가 코드를 바탕으로 사용자 인증, 데이터 보호 등 보안 요소를 보강**하면서 비기능에 집중하는 것을 목표로 하였습니다. 회원가입과 로그인 과정에서의 비기능에 집중하였습니다.
> 3. <br> 또한 JavaScript의 일부를 Vue.js로 변경하며 Vue.js를 예습해보았습니다. 
> 4. <br>  해당 프로젝트에는 **회원 가입, 로그인/로그아웃, 의사 등록/조회, 진료 과목 등록/조회, 생성형 AI를 통한 진단 및 의사 추천** 기능이 구현되어 있습니다.

<br>


## 📌 **Team**

> **서울 9반** 나혜원
>
> **서울 9반** 우성

<br>

## 📌 **Stack**
- **Java**
- **Spring Boot** - Backend framework
- **Spring MVC** - MVC architecture
- **Spring Data JPA** - Data access layer
- **H2 Database** - In-memory database for development and testing
- **Thymeleaf** - Server-side templating engine
- **Maven** - Dependency management
---

# 2️⃣ Features

## ⭐️ 디렉토리 구조

```
src/
├── main/
│   ├── java/
│   │   └── com.ssafy.exam/
│   │       ├── config/ - 설정 파일
│   │       │   ├── LoginCounter.java - 로그인 카운터 설정 클래스
│   │       │   ├── MyBatisConfig.java - MyBatis 설정 클래스
│   │       │   └── SwaggerConfig.java - Swagger 설정 클래스
│   │       ├── controller/ - 컨트롤러 클래스
│   │       │   ├── AIDoctorController.java - AI 의사 관련 API 컨트롤러
│   │       │   ├── DoctorRestController.java - 의사 관련 REST API 컨트롤러
│   │       │   ├── MemberController.java - 사용자 관련 API 컨트롤러
│   │       │   └── SpecialtyRestController.java - 전문 분야 관련 REST API 컨트롤러
│   │       ├── model/
│   │       │   ├── dao/ - 데이터 접근 계층 (DAO)
│   │       │   │   ├── DoctorDao.java - 의사 데이터 접근 객체
│   │       │   │   ├── MemberDao.java - 사용자 데이터 접근 객체
│   │       │   │   └── SpecialtyDao.java - 전문 분야 데이터 접근 객체
│   │       │   └── dto/ - 데이터 전송 객체 (DTO)
│   │       │       ├── AiDoctor.java - AI 의사 DTO 클래스
│   │       │       ├── Doctor.java - 의사 DTO 클래스
│   │       │       ├── Member.java - 사용자 DTO 클래스
│   │       │       └── Specialty.java - 전문 분야 DTO 클래스
│   │       ├── service/ - 비즈니스 로직 계층
│   │       │   ├── DoctorService.java - 의사 관련 서비스 인터페이스
│   │       │   ├── DoctorServiceImpl.java - 의사 관련 서비스 구현 클래스
│   │       │   ├── MemberService.java - 사용자 관련 서비스 인터페이스
│   │       │   ├── MemberServiceImpl.java - 사용자 관련 서비스 구현 클래스
│   │       │   ├── SpecialtyService.java - 전문 분야 관련 서비스 인터페이스
│   │       │   └── SpecialtyServiceImpl.java - 전문 분야 관련 서비스 구현 클래스
│   │       └── util/ - 유틸리티 클래스
│   │           └── OpenCrypt.java - 암호화 관련 유틸리티 클래스
│   ├── resources/
│   │   └── application.properties - 애플리케이션 설정 파일
└── SpringAiProjectApplication.java - Spring Boot 애플리케이션 메인 클래스
```

## ⭐️ 주요 기능 
### 1. 회원가입
회원가입 페이지 입니다. <br>
사용자의 email과 PW를 입력한 후 버튼을 누르면 인증 절차를 통해 회원가입을 진행합니다<br>
비밀번호를 SHA-256 방법으로 암호화하여 DB에 저장되도록 하였습니다.<br>
![1](/uploads/aa8058c591b4ec12c6fc880f50d4de51/1.png)<br>
회원가입이 성공적으로 마치면 로그인 페이지로 이동합니다.<br />
<br>
비밀번호를 2번 입력하여 제대로 입력하였는지 확인하고 다르게 입력된 경우 알림창을 띄웁니다.<br>
![2](/uploads/a95dc96ca87ed69977915fbdf40b1274/2.png)<br>


### 2. 로그인 페이지
이메일과 비밀번호를 입력하여 로그인을 실행합니다.<br/>
로그인 실패 시 로그인 실패 횟수를 local storage에 저장하여 로그인 시도 횟수를 5회로 제한하였습니다. <br>

![3](/uploads/dd4d20923ad3eac08f97f524f283ccb8/3.png) <br>
<br>
로그인에 성공하면 내 정보 수정과 로그아웃이 가능하도록 하였습니다.
![7](/uploads/b223dcc4dafe0f1c03adb2f00256242a/7.png)<br>


### 3. AI 의사를 통한 증상 예측과 의사 추천
![4](/uploads/51dda4918b91283b4aa9345d6a1b70a9/4.png)<br/>
증상을 입력하고 진단하기 버튼을 누르면
![5](/uploads/cb8c94d63b7a317dda9953cc02332501/5.png) <br>
생성형 AI를 통해 의심되는 병명을 예측하고<br>
관련 진료과목에 해당하는 의사를 추천해줍니다. <br>
![6](/uploads/9543dbdca8047e1fb1694819c36a3dc2/6.png) <br>

---

## Reviews

### 🐻 나혜원


### 🐻 우성문
이번 프로젝트에서는 CSR 방식으로 Vue와 Spring을 사용하여 보안을 최우선으로 고려해 개발을 진행했습니다.
사용자 인증을 위해 로그인 기능을 구현하여 로그인 후에만 서비스에 접근할 수 있도록 했으며, 로그인한 사용자의 타입(환자, 직원, 관리자)에 따라 권한을 세분화하여 필요한 정보에만 접근할 수 있게 했습니다.
특히, admin 타입의 사용자에게만 의사 정보 수정 및 삭제 권한을 부여하여 민감한 데이터를 안전하게 관리할 수 있도록 했습니다. 다만, 시간 부족으로 인해 일부 디테일한 기능을 구현하지 못한 점이 아쉬웠던 부분이였고 이번에는 각 사용자 타입에 따른 접근 권한과 데이터 보호를 고려한 보안 처리를 일부만 적용했지만, 다음에는 더 깊은 지식을 바탕으로 보안 처리를 보완해보고 싶습니다.
