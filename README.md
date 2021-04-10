# capstone-design

1. Gaegang
     - Gaegang : android app
          > 서버로 음성인식 text 보내는 거 확인함
     - Geagang_test
     - icon : 로고 및 앱 내에서 필요한 icon 모음
2. pdfReader (볼 필요 없음!!)



* 음성인식 못하는 경우 (avd 등) test 방법 (ps.줄 번호는 변경될 수 있음.)

      - RecommendActivity.kt
          > 121~127줄 (getSttString() 부분)
          > var result = findViewById<TextView>(R.id.text_stt2) 주석처리
          > var result = findViewById<TextView>(R.id.text_non0) 사용
          > return result 주석처리
          > return result.text.toString() 사용

      - activity_recommend.xml
          > 86~87줄
          > android:text="※ 가장 연관도가 높은 순으로 결과가 출력됩니다." 주석처리
          > android:text="중국 관련 온라인 강의 추천해 줘" 사용
      
      - SearchActivity.kt
          > 50~60줄 주석처리
          > 65~72줄 사용
  
     
* 음성인식 할 수 있으면 위에서 주석 반대로 ㄱㄱ



[TodoList]
- 추천 강의 목록 app에 출력되도록
- 시간표 알고리즘
- 소속학과 못듣는 강의 제외할 수 있도록
