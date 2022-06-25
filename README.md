# 트리플여행자 클럽 마일리지 서비스


 
## 👨‍💻목차

1. [개발 환경 및 배포 URL](#개발-환경-및-배포-URL)
2. [ERD 관계도](#ERD-관계도)
3. [DDL](#DDL)
4. [실행 방법](#실행-방법)
5. [API 명세](#API-명세)

</details>

## 📌개발 환경 및 배포 URL
- JDK 17
- Spring Boot 2.7
- My SQL 8.0

### 테스트 서버 URL http://52.79.165.66:8082
 
<br/>
 
## 📌ERD 관계도
![image](https://user-images.githubusercontent.com/64072136/175482986-0cd668c9-c678-482f-9587-8f2facbe72c9.png)

<br/>

## 📌DDL
```sql

CREATE TABLE review_event (
        lno BIGINT NOT NULL auto_increment,
        moddate datetime(6),
        regdate datetime(6),
        action VARCHAR(10) NOT NULL,
        bonus_check bit,
        content VARCHAR(1000),
        mileage BIGINT,
        place_id VARCHAR(50) NOT NULL,
        review_id VARCHAR(50) NOT NULL,
        type VARCHAR(10) NOT NULL,
        user_id VARCHAR(50) NOT NULL,
        PRIMARY KEY (lno)
);

CREATE INDEX review_id ON review_event (review_id);
CREATE INDEX user_id ON review_event (user_id);
CREATE INDEX review_id_bonus_check ON review_event (review_id, bonus_check);
CREATE INDEX place_id_user_id ON review_event (place_id, user_id);
CREATE INDEX bonus_check_place_id ON review_event (bonus_check, place_id);

CREATE TABLE attached_photo_ids (
       review_event_lno BIGINT NOT NULL,
       photo VARCHAR(255),
       FOREIGN KEY (review_event_lno) 
       REFERENCES review_event (lno)
);

```

<br/>

## 📌실행 방법
1. My SQL 실행 후 명령어 입력
```sql
CREATE DATABASE triple
```
2. aplication.properties 파일에서 DB 정보 입력
```java
spring.datasource.username={DB 아이디}
spring.datasource.password={DB 비밀번호}
```
3. java 다운 후 프로젝트 경로 안에서 터미널 명령어 입력
```
윈도우일 경우                        맥, 리눅스일 경우
./gradlew.bat build                 ./gradlew build

java -jar build/libs/Triple_HomeWork-0.0.1-SNAPSHOT.jar 
```
<br/>

## 📌API 명세
### 포인트 적립
- api
  - /events (post)

- headers
  - "Content-Type" : "application/json"
  
- req
  - "type": String
  - "action": String, /* "ADD", "MOD", "DELETE" */
  - "reviewId": String
  - "content": String
  - "attachedPhotoIds":String[]
  - "userId": String
  - "placeId": String
  
- res
  - "입력 완료 OR 입력 실패"
  
- fail
  - action 값이 "ADD", "MOD","DELETE" 가 아닐 경우
  - 같은 글에 리뷰가 이미 작성 되어 있을 경우
  - 요청 값이 하나라도 비어있을 경우
  
### 포인트 조회
- api
  - /mileage/:user_id (get)
  
- headers
  - "Content-Type" : "application/json"
  
- res
  - Number
