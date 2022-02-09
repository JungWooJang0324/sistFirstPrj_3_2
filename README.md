## 3조 로그분석 미니 프로젝트 sistFirstPrj_3_2
<br>
# 사용 언어: Java
* 제작기간: (2022.02.04~2022.02.11)
<br>
# 3조 클래스 다이어그램
<img src="https://user-images.githubusercontent.com/93374409/153107574-98923110-1017-4abd-b4f5-0315e0a750cb.png">

# 필요한 정보 
* 최다 사용 key의 이름, 횟수
* 브라우저별 요청 횟수, 비율(%)
* 서비스 성공 횟수(200), 실패(404) 횟수
* 요청이 가장 많은 시간
* 비정상적인 요청(403)이 발생한 횟수, 비율(%)
* 입력 라인에 해당하는 최다 사용 키의 이름과 횟수

# 결과물
### 로그인
<img src="https://user-images.githubusercontent.com/93374409/153108800-ace77860-be95-4684-b7d7-91b3cc21c621.PNG" width="300" height=auto/> <img src="https://user-images.githubusercontent.com/93374409/153108805-55497a82-af61-4d73-bc3f-04effb08ba93.PNG" width="300" height=auto/>

* Login 성공
    * 일반 사용자 계정 : ID: "admin", PASSWORD: "1234" 또는 ID: "administrator", PASSWORD:"12345"
    * 관리자 계정 : ID: "root", PASSWORD "1111" - 파일을 생성할 수 없습니다.
    * 로그인 성공시 "로그인 성공! ID님 환영합니다" 메세지 출력합니다.
* Login 실패
    * 실패 시 "로그인 실패!" 메세지 출력 후 로그인창 재호출 출력합니다
<br>
### 로그 다이얼로그
<img src="https://user-images.githubusercontent.com/93374409/153108929-ee1e3660-cac7-4a20-a663-b90460a6991b.PNG" width="400" height=auto/>

* Line 
    * 첫줄과 마지막줄 입력값에 대해 필요한 정보를 팝업으로 출력합니다 
    * 첫줄을 입력하지 않았을 때, 자동으로 첫줄부터 읽어옵니다.
    * 마지막줄을 입력하지 않았을 때, "마지막줄을 입력해 주세요!" 메세지를 출력합니다.  

* View
    * 로그파일을 선택하면 로그파일내에 필요한 정보를 읽어옵니다.

* Report
    * 일반사용자가 View를 선택하거나 Line을 선택해 정보를 읽어오면 그것을 C:/dev/report파일에 생성된 report_시간정보.txt파일로 저장됩니다.
    * View나 Line에서 정보를 읽어오지 못했다면 "View나 Line을 먼저 실행해주세요!" 메세지를 출력합니다.
    * 관리자 계정이라면 Report버튼의 접근권한이 없습니다.
 <br>
### 결과창 & 파일생성(Report)
<img src="https://user-images.githubusercontent.com/93374409/153109039-fd2f8d89-cacb-486c-9684-9ece4fcb9173.PNG" width="500" height=auto/> 
<img src="https://user-images.githubusercontent.com/93374409/153109042-bdac3ec4-496a-400c-bfe1-33e28095cf48.PNG" width="300" height=auto/>
