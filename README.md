# SmartMobileProject 최종보고서 

> 위치기반 사진공유 프로젝트 (팀원 : 문영호, 문현화, 박정하, 서범규, 손유현)


**Requirements:**
  - 프로젝트 깃허브 주소 : https://github.com/youngho98/smartmobileproject
  - 최종발표 영상 : 
 

## Table of Contents

- [주제](#Topic)
  - [선정 배경 및 목적](#Topic)

- [다운로드 받기 전 필요한 부분](#goormIDE)
  - [goormIDE](#goormIDE)
  - [KakaoMap](#KakoMap)
  - [android](#android)

- [APP 주요 기술](#KakaoLogin)
  - [KaKaoLogin](#KakaoLogin)
  - [KakaoMap](#KakaoMap)
  - [IMG Upload](#Upload)
  - [IMG Download](#Download)
  
- [알고리즘](#알고리즘)

- [차별화](#차별화)

- [배운점](#배운점)
 
  

  
 ## Topic
  ### 선정 배경 및 목적
  - 프로젝트 주제를 선정하기에 앞서 일상생활에서 겪는 불편함을 생각해보았습니다. 가족이나 친구, 연인끼리 여행을 가면 주로 한 사람이 모든 사진을 찍게 되는데 이 후 모든 사진을 다시 옮겨 주어야하는 불편함이 발생합니다. 
  - 이를 해결하고자 공유 대상자의 위치 정보를 받아 사진을 자동으로 공유할 수 있는 app을 만들고자 하였습니다. 
  - 근처 몇 미터 반경 안에 자신이 등록한 커뮤니티 사용자 존재 시 사진을 자동으로 공유하는 기능을 구현하였습니다.
  
  
 ## 다운로드 받기 전 필요한 부분
  ### groomIDE
  어플리케이션을 실행시키기 위해서 웹 서버가 구축되어있어야 합니다. 웹서버는 groomIDE를 사용하였으며, 웹서버 주소와 php 파일명을 안드로이드 request class에 입력해줍니다.
  
  ### KakaoMap
  
  - MapActivity를 실행시키기 위해서는 libDaumMapAndroid.jar 파일의 절대경로를 복사하여 build.gradle 파일의 dependencies 부분에 넣어줍니다.
  
   1. 프로젝트 파일에서 app -> libs 폴더를 열어보면 libDaumMapAndroid.jar 파일이 있는 것을 확인할 수 있습니다.
![KakaoTalk_20210608_151112955_01](https://user-images.githubusercontent.com/79883555/121132708-7206bf00-c86c-11eb-8fd3-3b381528d43c.jpg)<br>
   2. libDaumMapAndroid.jar 파일을 우클릭하여 Copy -> Copy Path를 눌러줍니다.
![KakaoTalk_20210608_151112955_02](https://user-images.githubusercontent.com/79883555/121132714-73d08280-c86c-11eb-8ff6-ebbdfe3d1003.jpg)<br>
   3. 다음으로 Absolute Path를 눌러주면  libDaumMapAndroid.jar파일의 절대경로가 복사됩니다.
![KakaoTalk_20210608_151112955_03](https://user-images.githubusercontent.com/79883555/121132724-77fca000-c86c-11eb-8e57-7511536c875b.jpg)<br>
   4. 복사한 절대경로를 Gradle Scripts의 build.gradle(Module)의 dependecies안에 있는 implementation files안에 넣어줍니다.
![5](https://user-images.githubusercontent.com/79883555/121133569-7a132e80-c86d-11eb-82c3-c516f42e4d9f.JPG)

  ### android
  
  ## APP 주요 기술
   ### KakaoLogin
   
   ### KakaoMap
   
   ### Upload
   
   ### Download
   
   
  ## 알고리즘
  

  ## 차별화
  
  ## 배운점
1) 안드로이드 스튜디오의 주요 언어인 JAVA에 대해 공부를 하며 프로젝트 진행 이전보다 JAVA에 대한 이해도와 활용능력이 증가하였다.
2) 안드로이드 스튜디오와 연동하여 데이터를 송신해주는 PHP에 대한 이해도와 활용능력이 프로젝트 진행 이전보다 증가하였으며 또한, 웹서버의 사용법도 알 수 있었다.
3) 프로젝트 진행에 있어서 미완성된 부분이 다소 존재했기에 프로젝트 진행과정을 다시 한 번 돌아보게 하는 계기가 되었다.
