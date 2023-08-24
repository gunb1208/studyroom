# 📌 StudyRoom
>독서실 프로그램 웹 개발

</br>

## 1. 제작 기간 & 참여 인원
- 2023.07 ~ 2023.08
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `Back-end`
  - Eclipse
  - Java 17
  - Spring Boot 2.7.14
  - Maven
  - Mybatis
  - Oracle 11g xe
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

## 5. 추가 기능


- **회원 관련**
  - 회원가입, 로그인, 회원탈퇴
- **좌석 관련**
  - 좌석 변경
- **결제/등록 관련**
  - 이용기간 연장
- **스케줄러 관련**
  - 이용기간 만료된 등록정보 삭제
- **게시판 관련**
  - 공지사항 게시판 : 비회원도 게시글을 조회할 수 있지만 관리자 권한을 가진 사용자만 게시글 작성, 수정, 삭제 가능
  - Q&A 게시판 : 비회원도 게시글을 조회할 수 있지만 게시글 작성은 회원만 가능하며 수정, 삭제는 해당 게시글의 회원이름과 세션에 저장된 회원이름이 일치해야 작동함.
- **관리자 페이지 관련**
  - 관리자 권한을 가진 사용자만 관리자 페이지에 접근 가능
  - 전체 회원 정보 조회, 회원에게 연락하기(이메일 발송), 좌석 등록과 취소, 등록정보 관리, 결제정보 조회

</br>

## 6. 핵심 기능 실행 화면


### 6.1 회원가입


- 6.1.1 약관동의
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/86bf9201-0dba-47e9-a931-9a141f5ecd3c">
</p>


- 6.1.2 회원가입
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/f6224dab-8e8c-4abe-b2af-9a26ccac8b86">
</p>


### 6.2 로그인


- 6.2.1 로그인 화면
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/161bfe84-4635-4765-9518-867ea559f8b3">
</p>


- 6.2.2 로그인 실패 화면
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/593728a3-234d-4908-8f7e-e0c99ad33824">
</p>


- 6.2.3 일반 회원 로그인시 nav bar에 회원이름 출력
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/9c0313ba-c7c9-40fc-ac4f-1675caf64afb">
</p>


- 6.2.4 관리자 로그인시 nav bar에 관리자 페이지 링크 출력
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/01d8bfa1-30cc-422b-bd66-9d5ea083e82d">
</p>


### 6.3 좌석등록


- 6.3.1 좌석등록 화면
  - 이미 등록된 좌석들은 노란색, 본인이 등록한 좌석은 파란색으로 표시
  - 고정석, 자유석 선택 가능
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/869147c0-ed37-4b73-a550-0557fe06792b">
</p>


- 6.3.2 좌석 선택 시 좌석번호 출력
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/ee35ca18-5409-469c-b7fd-828bfcaa493c">
</p>


- 6.3.3 요금제 선택
  - 결제하기 버튼을 누르는 즉시 해당 좌석의 상태가 '이용중'으로 변경됨과 동시에 임시등록 테이블에 사용자 정보 등록
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/343c8e2f-1cb9-4c81-9143-090a466e2351">
</p>


### 6.4 결제


- 6.4.1 결제 정보 출력
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/0716dca7-10bf-43d5-8355-eb4370bc0f9e">
</p>


- 6.4.2 결제 API 호출 (x를 눌러 취소해도 결제완료되도록 구현)
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/b9c131c3-8833-4257-9b11-4c00f588fc2d">
</p>


- 6.4.3 결제 완료 및 등록 완료
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/ebbd3c1c-1478-4eb6-aa8d-af176ebad526">
</p>


- 6.4.4 좌석 등록화면에서 파란색으로 표시
<p align="center">
<img src="https://github.com/gunb1208/studyroom/assets/136543622/2358a899-6938-4e6f-927b-26521ccba89a">
</p>


