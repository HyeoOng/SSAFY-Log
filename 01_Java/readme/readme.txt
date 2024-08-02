## SSAFIT Project
### Java 비전공반 첫 번째 PJT

<br>

## 1. Introduce
### Project Summary
> **영상 및 영상 리뷰 관리 프로그램 개발**

### Team
> **서울9반 나혜원**
> 
> **서울9반 오지원** 

### Skill & Tool
> **Java**
> 
> **STS**
> 
> **Gitlab**

<br>

## 2. Feature
#### 첫 화면
<img src="./readme/main.png" width="60%"/>

#### 기능
**영상 정보 제공**
- **등록된 모든 영상 정보 조회**
- **영상의 세부 정보 조회(번호, 제목, 운동 부위, 영상 URL)**

**영상 리뷰 관리**
- **영상 리뷰 등록**
- **영상 리뷰 삭제**

#### 영상 정보 제공
<img src="./readme/video.png" width="60%"/>

#### 영상 리뷰 관리
<img src="./readme/videoReview1.png" width="60%"/>
<img src="./readme/videoReview2.png" width="60%"/>

#### 영상 리뷰 삭제
<img src="./readme/removeReview1.png" width="60%"/>
<img src="./readme/removeReview2.png" width="60%"/>


<br>


## 3. Code

#### JSON 파일에서 데이터 로드 및 파싱

제공되는 영상 정보 데이터 파일(video.json)을 기반으로 필요한 정보를 읽어와 StringBuilder를 이용해 문자열로 결합한 후, Gson 라이브러리를 이용해 JSON 데이터를 파싱하여 객체 리스트로 저장하였습니다.

```java
Type videoListType = new TypeToken<ArrayList<Video>>() {}.getType();
private List<Video> list;

try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/video.json")));) {
	String str = null; // 한줄씩 읽어오기 위한 임시변수
	StringBuilder sb = new StringBuilder();

	while ((str = br.readLine()) != null) {
		sb.append(str);
	}

	Gson gson = new Gson();
	list = gson.fromJson(sb.toString(), videoListType);
} catch (Exception e) {

}
```

#### Singleton

클래스를 재사용할때 여러번 new하여 불필요한 객체들을 무분별하게 생성하는 것을 막기위해 싱글톤으로 영상관리 객체와 영상리뷰관리 객체를 생성하였습니다.

```java
private static VideoDaoImpl instance;
private VideoDaoImpl() {}

public static VideoDaoImpl getInstance() {
	if (instance == null) {
		instance = new VideoDaoImpl();
	}
	return instance;
}
---
private static VideoReviewDaoImpl instance;

private VideoReviewDaoImpl() {}

public static VideoReviewDaoImpl getInstance() {
	if (instance == null) {
		instance = new VideoReviewDaoImpl();
	}
	return instance;
}
```

#### 비디오 리뷰 추가 기능

특정 비디오에 리뷰를 추가할 수 있습니다. 리뷰는 비디오 번호에 따라 관리되며, 새로운 리뷰가 추가될 때마다 내부 데이터베이스에 반영됩니다. 
리뷰 번호는 자동으로 증가합니다.

```java
// In VideoReviewDaoImpl.java
@Override
public int insertReview(VideoReview videoReview) {
    int videoNo = videoReview.getVideoNo();

    // videoReviewDb에 videoNo로 하는 키가 존재하지 않을 경우
    if (!videoReviewDb.containsKey(videoNo)) {
        videoReviewDb.put(videoNo, new ArrayList<>()); // videoNo를 키로 하는 빈 배열을 할당하고
    }
    videoReviewDb.get(videoNo).add(videoReview); // 배열에 리뷰 객체를 추가
    reviewNo++;
    return reviewNo;
}
```

#### 비디오 리뷰 삭제 기능

특정 비디오에 달린 리뷰를 삭제할 수 있습니다. 
닉네임과 리뷰 번호를 기반으로 리뷰를 찾고, 삭제 후 남아있는 리뷰의 번호를 재정렬합니다. 
또한, 리뷰가 모두 삭제된 경우 해당 비디오의 리뷰 데이터도 제거됩니다.

```java
// In VideoReviewDaoImpl.java
public boolean removeReview(int videoNo, String nickName, int reviewNo) {
    List<VideoReview> videoReviewList = videoReviewDb.get(videoNo);
    int removedIdx = -1;
    boolean removed = false;

    // 리스트에 아무것도 없는 경우(리뷰가 없는 경우) => 제거하지 않고 false 반환
    if (videoReviewList == null) return false;
    
    for(int i = 0; i < videoReviewList.size(); i++) {
        if(videoReviewList.get(i).getNickName().equals(nickName) && videoReviewList.get(i).getReviewNo() == reviewNo) {
            removed = videoReviewList.remove(videoReviewList.get(i));
            removedIdx = i;
            break; // 리뷰를 찾았으면 루프를 종료
        }
    }

    // 리뷰를 찾지 못한 경우 false 반환
    if (!removed) return false;

    // 리뷰를 삭제한 후, 남아있는 리뷰의 reviewNo를 하나씩 줄여줌
    for(int i = removedIdx; i < videoReviewList.size(); i++) {
        int newReviewNo = videoReviewList.get(i).getReviewNo() - 1;
        videoReviewList.get(i).setReviewNo(newReviewNo);
    }

    // 리스트가 비어있는 경우, 맵에서 해당 키를 제거
    if (videoReviewList.isEmpty()) {
        videoReviewDb.remove(videoNo);
    }

    return removed;
}
```

비디오별 리뷰 데이터를 효율적으로 관리하기 위해, 비디오 번호를 키로 하는 맵에 리뷰 데이터를 저장합니다.
이를 통해 비디오와 리뷰 간의 관계를 명확히 하고, 리뷰 데이터를 체계적으로 관리합니다.

#### Setter

```java
public void setNickName(String nickName) {
	if (nickName == null || nickName.isEmpty()) {
		throw new IllegalArgumentException("닉네임은 null이거나 비어있을 수 없습니다.");
	}

	if (nickName.length() < 3 || nickName.length() > 20) {
		throw new IllegalArgumentException("닉네임은 최소 2자 이상, 최대 20자 이하로 작성해주세요.");
	}
	
	this.nickName = nickName;
}
```

setter에는 유효성 검사를 통해 입력받는 데이터의 값을 관리하였습니다.

<br>


## 4. Retrospective

여기에는 이번 프로젝트하면서 느낀점, 어려웠던점, 등등

#### 나혜원
~~~
짧은 프로젝트였지만 객체지향의 개념과, Java 프로그램의 기본 구조와 흐름을 이해할 수 있었던 시간이었습니다.
영상 정보와 영상의 리뷰 데이터를 저장하고 처리하는 Model(Video, VideoReview),
사용자의 요청에 맞는 비즈니스 로직을 실행하고, 데이터베이스와 통신하는 Controller(VideoDaoImpl, VideoReviewDaoImpl..),
콘솔 창에 구현한 서비스를 출력해주는 View(Ui 클래스들)을 구현하면서 MVC 패턴을 보다 더 이해할 수 있게 되었습니다.
또한 서비스에 필요한 데이터를 입력을 통해 저장하는 것이 아니라 GSON 라이브러리를 사용하여 JSON 데이터를 로드하고 파싱하는 방법을 배울 수 있었습니다.
짧은 시간동안 진행되어 다른 추가적인 기능을 못한 것이 아쉬웠지만, MVC 패턴에 맞게 클래스를 구현하면서 
객체지향 프로그램을 개발하는 것에 있어서 코드를 작성해 기능을 구현하기 전 클래스를 설계하는 설계 단계가 중요하다는 것을 깨달을 수 있었습니다. 
~~~


#### 오지원
~~~
작성해주세요~!
~~~
