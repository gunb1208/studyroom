# 📌 StudyRoom
>독서실 프로그램 웹 개발

</br>

## 1. 제작 기간 & 참여 인원
- 2023.07 ~ 2023.08
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 17
  - Spring Boot 2.7.14
  - Maven
  - Mybatis
  - Oracle 11
#### `Front-end`
  - JavaScript
  - JSTL
  - JQuery
  - BootStrap

</br>

## 3. ERD 설계
![StudyRoom](https://github.com/gunb1208/studyroom/assets/136543622/4da912ad-d83d-4efb-acc6-321daf05c684)


## 4. 핵심 기능
이 프로그램의 핵심 기능은 


"*등록된 회원*"이 


"*좌석을 지정*"해 


정해진 "*요금제의 금액을 결제*" 후 


해당 좌석에 "*요금제의 기간만큼 이용 등록*"을 하는 것입니다.


</br>


### 4.1 전체 흐름
![1](https://github.com/gunb1208/studyroom/assets/136543622/71d923ef-6805-46fa-887a-ab5ff7b32772)


### 4.2 사용자 요청
![2](https://github.com/gunb1208/studyroom/assets/136543622/59192db6-2a46-4a5b-967c-6271c842c974)
- **로그인 상태와 중복 등록 체크** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b587bbff4dce02e3bec4f4787151a9b6fa326319/frontend/src/components/PostInput.vue#L67)
  - 사용자가 로그인 상태인지 체크 후 비로그인시 로그인 요청 메시지를 출력합니다.
  - 사용자가 이미 1개의 좌석에 등록되어있을 경우 좌석 선택 불가 메시지를 출력합니다.

- **Contoller 호출** :pushpin: [코드 확인]()
  - 사용자가 올바른 좌석과 요금제를 선택한 후 결제하기 버튼을 누르면 연결된 결제 URL을 호출합니다.

### 4.3 Controller
![3](https://github.com/gunb1208/studyroom/assets/136543622/efcebca8-a5cb-45a5-ac0a-b6f61dc25ffe)
- **요청 처리** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b2c5e60761b6308f14eebe98ccdb1949de6c4b99/src/main/java/goQuality/integerous/controller/PostRestController.java#L55)
  - 사용자로부터 호출을 받은 Controller가 Service를 요청합니다.


### 4.4. Service
![4](https://github.com/gunb1208/studyroom/assets/136543622/dd710589-0709-4804-b776-c403a1c7f13d)
- **임시 등록** :pushpin: [코드 확인]()
  - 여러 사용자가 동시에 좌석을 선택할 경우를 대비해 임시 등록 테이블에 데이터를 입력합니다.
  - 임시 등록을 했지만 일정 시간동안 결제를 완료하지 않을 경우 스케줄러를 통해 데이터가 삭제됩니다. 

- **등록 완료** :pushpin: [코드 확인]()
  - 결제가 정상적으로 완료되었을 시 좌석이 '이용중' 상태로 변경됩니다.
  - 또한 사용자의 결제, 등록 정보가 DB에 저장됩니다.

### 4.5. Dao
![5](https://github.com/gunb1208/studyroom/assets/136543622/b9774174-db2d-4f94-a928-6875a02e3205)

</br>

## 5. 실행 화면

