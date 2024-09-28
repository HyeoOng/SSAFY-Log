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
>  우선 도메인이 제가 좋아하는 영화 주제라서 좀 더 쉽고 깊게 몰입할 수 있었습니다.<br>
>  또한, 초반 프로젝트 구조 설계와 기능 설계에 시간을 꽤 투자했었는데, 그럼에도 불구하고<br>
>  코딩 중에 추가하는 변수와 기능들이 있어서 '분석 설계를 하고 코딩하길  잘했다'라는 생각과<br>
>  '다음에는 좀 더 많은 시간을 투자해야겠다' 라는 생각이 동시에 들었습니다. <br><br>
>   그리고, 관통 PJT 관련 수업을 화면으로 볼 때까지는 피상적인 이해에 머물렀던 Servle,Jsp 관련 개념들을<br>
>  직접 코딩으로 구현하려고 노력하면서 훨씬 더 깊고 제대로 이해할 수 있었습니다.<br>
>  '백문이 불여일타'라는 말을 체감할 수 있었고, 앞으로의 공부 방향에 대해서도 알 수 있었습니다.<br><br>
> 마지막으로, 팀원이 제가 개념을 이해할 동안 인내심을 가지고 기다려주고, 제가 모르는 개념을 잘 설명해주고,<br> 프로젝트 전반적으로 리딩을 해줘서 정말 고마웠고,
> 덕분에 잘 마칠 수 있었다고 생각합니다. <br> 언젠가는 저도 프로젝트에 좀 더 기여하고, 나아가 리딩해보고 싶다는 생각을 했습니다.<br>
