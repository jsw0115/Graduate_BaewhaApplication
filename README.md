# 졸업작품_배화여대 앱 만들기

## 2022.10.05 오류 파일
* StaticsFragment.java
  *  error 1 : Cannot resolve symbol 'RankListAdapter'
  *  error 2 : Cannot resolve method 'notifyDataSetChanged()'

* SplashActivity.java
   *  error 1 : Overriding method should call 'super.onRequestPermissionsResult';
   *  error 2 : Call requires permission which may be rejected by user: code should explicitly check to see if permission is available or explicitly handle a potential 'SecurityException'

* RegisterActivity.java
   *  error 1 : Cannot resolve symbol 'Builder'
   *  error 2 : Cannot resolve method 'setMessage(java.lang.String)'
   *  error 3 : Cannot resolve method 'show' in 'AlertDialog'
   *  error 4 : Cannot resolve method 'dismiss' in 'AlertDialog'
   
* CricleImageView.java
  *  error 1 : Cannot resolve symbol 'CircleImageView'
  *  error 2 : Cannot resolve method 'CircleImageView_civ_border_width'
  *  error 3 : Cannot resolve method 'CircleImageView_civ_border_color'
  *  error 4 : Cannot resolve method 'CircleImageView_civ_border_overlay'
  *  error 5 : Cannot resolve method 'CircleImageView_civ_circle_background_color'
  
## 만들어야 될 페이지
> 사용한 sql 등 설명
* **android studio**
* **phpMyAdmin**
* **VisualStudioCode**
* **mysql** 
* **FileZila**

<!-- 기능설명 -->
> 앱의 기능
* 회원가입 (2022.10.05 RegisterActivity.java 오류)
* 로그인 > 구현 완료
* 캘린더 일정 > 구현 완료
* 공지사항 > 구현 완료
* 버스 셔틀 확인 가능 
    * 버스기사 앱도 필요 (링크 : )
    * 운행 시작 도착 버튼 만들어 확인 가능
* 학교 카페 메뉴 확인 가능 
* 사이렌 오더 사용가능
* 재학생 할인 (제휴기업) 확인 가능
* 다울(떡) 등 학교 행사 정보 확인
* 학교 급식 확인 가능 > DB에 미리 올리고 볼 수 있게끔 만들기
* 천원 밥 실시간 확인 가능 > DB에 미리 올리고 볼 수 있게끔 만들기
* 성적확인

## 로그인 창
![image](https://user-images.githubusercontent.com/87688825/194189048-f750f33d-7441-4d10-9e96-e894d5cfc2f8.png
* 아이디 비밀번호 입력 후 로그인
* 
