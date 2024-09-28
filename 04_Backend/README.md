# 영화 정보 관리 백엔드 프로젝트 

## 팀원 구성

    서울 9반 나혜원
    서울 9반 박찬영


## 프로젝트 개요
영화 정보 관리 백엔드 프로젝트는 사용자에게 다양한 영화 정보를 제공하는 웹 애플리케이션입니다. <br>
사이트 관리자는 다양한 영화 정보를 등록, 수정, 삭제함으로서 영화 정보를 관리합니다. <br>  
이를 통해 사용자는 영화들의 목록을 한번에 보거나, 원하는 특정 영화의 상세 정보를 조회할 수 있습니다. <br>  
이 프로젝트는 Java Servlet/JSP 기술을 기반으로 MVC 패턴을 적용하여 개발하였습니다.

## 주요 기능
- 영화 정보 등록 
- 영화 정보 삭제 
- 영화 정보 수정 
- 모든 영화 정보 조회 
- 영화 정보 상세 조회
- 키워드를 통해 영화 정보 검색

## 사용 기술
- **Java**: 백엔드 개발에 사용된 언어
- **Servlets & JSP**: HTTP 요청 처리 및 동적 페이지 생성을 위한 기술
- **Tomcat**: 애플리케이션 서버로서 프로젝트 배포 및 실행

## 프로젝트 구조
```
C:.
└── main
    ├── java
    │   └── com
    │       └── ssafy
    │           └── movie
    │               ├── controller
    │               │   └── MovieController.java
    │               ├── model
    │               │   └── dto
    │               │       └── Movie.java
    │               ├── repository
    │               │   ├── MovieRepository.java
    │               │   └── MovieRepositoryImpl.java
    │               └── service
    │                   ├── MovieService.java
    │                   └── MovieServiceImpl.java
    └── webapp
        ├── index.html
        ├── META-INF
        │   └── MANIFEST.MF
        └── WEB-INF
            ├── lib
            └── movie
                ├── detail.jsp
                ├── movieList.jsp
                ├── registForm.jsp
                ├── searchForm.jsp
                └── updateForm.jsp

```
- src/main/java: Java 소스 파일들이 위치한 디렉토리 <br>
1. com.ssafy.movie.controller: MovieController.java 파일이 위치한 디렉토리 <br>
1. com.ssafy.movie.model.dto: Movie.java 파일이 위치한 디렉토리 <br>
1. com.ssafy.movie.model.repository: MovieRepository.java 및 MovieRepositoryImpl.java 파일이 위치한 디렉토리<br>
1. com.ssafy.movie.model.service: MovieService.java 및 MovieServiceImpl.java 파일이 위치한 디렉토리 <br>
- src/main/resources: 설정 파일 및 기타 리소스 파일들이 위치한 디렉토리 <br>
- src/main/webapp: JSP 페이지와 같은 웹 리소스가 위치한 디렉토리 <br>

## 구현 이미지

### 메인 및 기능화면

![image](/uploads/38c13f2d7e663923764cfaf6688792f0/image.png)
![image__1_](/uploads/252744d6a0d5e1c49a4d772da50f9cbe/image__1_.png)

## 소감
나혜원<br>
>  <br>
>   <br>
>   <br>
>   <br>
>   <br>
>   <br>

박찬영<br>
>  <br>
>  <br>
>  <br>
>  <br>
>  <br>
>  <br>
