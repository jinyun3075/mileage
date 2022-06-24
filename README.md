# 트리플여행자 클럽 마일리지 서비스
## 개발 환경
- JDK 17
- Spring Boot 2.7
- My SQL 8.0

## ERD 관계도
![image](https://user-images.githubusercontent.com/64072136/175466471-b45039ce-5dfb-4426-b984-89876de76b78.png)

## 실행 방법
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

## API 명세
### 포인트 적립
- api
  - /events (post)

- headers
  - "Content-type" : "application/json"
  
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
  - "Content-type" : "application/json"
  
- res
  - Number
