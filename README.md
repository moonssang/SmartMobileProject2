# SmartMobileProject 최종보고서 

> 위치기반 사진공유 프로젝트 (팀원 : 문영호, 문현화, 박정하, 서범규, 손유현)


**Requirements:**
  - 프로젝트 깃허브 주소 : https://github.com/youngho98/smartmobileproject
  - 최종발표 영상 : 
 

## Table of Contents

- [주제](#Topic)
  - [선정 배경 및 목적](#Topic)

- [다운로드 받기 전 필요한 부분](#groomIDE)
  - [goormIDE](#groomIDE)
  - [android](#android)
  - [KakaoMap](#KakoMap)
 
- [APP 주요 코드](#KakaoLogin)
  - [KaKaoLogin](#KakaoLogin)
  - [Background Location](#Background)
  - [KakaoMap IMG Upload](#Upload)
  - [KakaoMap IMG Download](#Download)
  - [이미지 업로드 다운로드 알고리즘](#IMGAlgorithm)
  
- [실행화면](#Launch)

- [차별화](#차별화)

- [배운점](#배운점)
 
<br> 

 ## Topic
  ### 선정 배경 및 목적
  - 프로젝트 주제를 선정하기에 앞서 일상생활에서 겪는 불편함을 생각해보았습니다. 가족이나 친구, 연인끼리 여행을 가면 주로 한 사람이 모든 사진을 찍게 되는데 이 후 모든 사진을 다시 옮겨 주어야하는 불편함이 발생합니다. 
  - 이를 해결하고자 공유 대상자의 위치 정보를 받아 사진을 자동으로 공유할 수 있는 app을 만들고자 하였습니다. 
  - 근처 몇 미터 반경 안에 자신이 등록한 커뮤니티 사용자 존재 시 사진을 자동으로 공유하는 기능을 구현하였습니다.
  
  
  
 ## 다운로드 받기 전 필요한 부분
  ### groomIDE
  
  - 어플리케이션을 실행시키기 위해서 웹 서버가 구축되어있어야 합니다. 웹서버는 groomIDE를 사용하였으며, 웹서버 주소와 php 파일명을 안드로이드 request class에 입력해줍니다.
  - groomIDE 링크 : https://ide.goorm.io/my/dashboard <br>
   
   1. 로그인을 한 후 실행 버튼을 눌러 구름 서버에 접속합니다. (교수님께 보내드린 계정으로 접속하시면 됩니다.)
    ![image](https://user-images.githubusercontent.com/80194089/121145677-28bd6c00-c87a-11eb-8111-6afbf20ea3eb.png) <br>
  
   2. 화면과 같이 화살표 버튼을 클릭하여 서버(new run.php)를 연결해줍니다. 
    ![image](https://user-images.githubusercontent.com/80194089/121147471-db41fe80-c87b-11eb-82de-b58fca48da49.png)
    
---------------------------------------------------------------------------------------------------  
  
  ### android
  - 프로젝트를 다운 받고 실행하는 과정입니다.
  
  1. 안드로이드 스튜디오 초기 실행 과정이며, Get From Version Control을 눌러줍니다.
   ![image](https://user-images.githubusercontent.com/80194089/121148656-ed706c80-c87c-11eb-8a3f-baedca6b7fce.png)

  2. url 부분에 https://github.com/youngho98/smartmobileproject.git 주소를 넣어준 후, clone을 눌러주면 프로젝트가 실행됩니다.
   ![image](https://user-images.githubusercontent.com/80194089/121148756-0416c380-c87d-11eb-9600-9e40d33fb67b.png)
   
  3. master에서 'final branch'로 check out 해줍니다.

------------------------------------------------------------------------------------------------------------------------------

  ### KakaoMap
  
  - MapActivity를 실행시키기 위해서는 libDaumMapAndroid.jar 파일의 절대경로를 복사하여 build.gradle 파일의 dependencies 부분에 넣어줍니다.
  
   1. 프로젝트 파일에서 app -> libs 폴더를 열어보면 libDaumMapAndroid.jar 파일이 있는 것을 확인할 수 있습니다.
![KakaoTalk_20210608_151112955_01](https://user-images.githubusercontent.com/79883555/121132708-7206bf00-c86c-11eb-8fd3-3b381528d43c.jpg)<br><br>
   2. libDaumMapAndroid.jar 파일을 우클릭하여 Copy -> Copy Path를 눌러줍니다.
![KakaoTalk_20210608_151112955_02](https://user-images.githubusercontent.com/79883555/121132714-73d08280-c86c-11eb-8ff6-ebbdfe3d1003.jpg)<br><br>
   3. 다음으로 Absolute Path를 눌러주면  libDaumMapAndroid.jar파일의 절대경로가 복사됩니다.
![KakaoTalk_20210608_151112955_03](https://user-images.githubusercontent.com/79883555/121132724-77fca000-c86c-11eb-8e57-7511536c875b.jpg)<br><br>
   4. 복사한 절대경로를 Gradle Scripts의 build.gradle(Module)의 dependecies안에 있는 implementation files안에 넣어줍니다.
![image](https://user-images.githubusercontent.com/80194089/121142808-5359f580-c877-11eb-866d-c555f7d14994.png)

-----------------------------------------------------------------------------------------------------------------------  
   
  ## APP 주요 코드 및 실행 과정
  
   ### KakaoLogin
   1. KaKaoLogin 주요 코드 설명
   - 로그인에 성공하게 되면 자신의 이메일 정보를 데이터베이스에 저장하고 공유대상이 설정되어 있는 지를 판단합니다. 이 때 설정된 공유대상이 있다면 맵 액티비티로 이동하고, 공유대상이 설정되어 있지 않다면 공유대상설정 액티비티로 이동하여 공유대상의 이메일을 입력하여 데이터베이스에 저장해서 공유대상 정보를 저장하고 공유대상을 식별할 때 사용됩니다.
   
   2. KakaologinActivity 코드 
   
   ```java
    String serverUrl="https://phpproject-cparr.run.goorm.io/Kakaouser.php";

        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("testresponse",response);
                String[] strarr =response.split("#");
                Log.d("strarr1",strarr[0]);
                setResultboolean(strarr[0]);
                setShared_email(strarr[1]);
                Log.d("threadstop","stop");
                Log.d("the11",getResultboolean());
                String hi = getResultboolean();
                Log.d("hihi",hi);
                boolean bool= Boolean.parseBoolean(hi);
                if(bool==true){
                    Log.d("resultfalse",getResultboolean());
                    Intent intent = new Intent(KaKaoLoginActivity.this, MapActivity.class);
                    intent.putExtra("email", email); //파싱한 값을 넘겨줌
                    intent.putExtra("shared_email", shared_email);
                    Log.d("sharedemail",shared_email);
                    startActivity(intent);
                }
                else if(bool==false){
                    Log.d("threadstart","start");
                    Intent intent = new Intent(KaKaoLoginActivity.this,ShareActivity.class);
                    Log.d("emailtest",email);
                    intent.putExtra("email", email); //파싱한 값을 넘겨줌
                    startActivity(intent);
                }
            }          
   ```
   
   3. KakaoUser.php (서버)
   - SharedUser table에 $email(=사용자 email)과 $shared_email(=공유 대상자 이메일)을 echo문으로 데이터를 전송합니다.
   
   ```php
   <?php

$connect = mysqli_connect("127.0.0.1", "root1", "1234", "Project");

$email = $_POST["email"];
$sql = "select * from Shareduser where email = '".$email."'";
$result = mysqli_query($connect, $sql);

if(mysqli_num_rows($result) > 0) {
   $row = mysqli_fetch_array($result);
   $response = "true#".$row["shared_email"];
   echo $response;
}
else if(mysqli_num_rows($result) == 0) {
   $response = "false#null";
   echo $response;   
}

include "sqldisconnect.php";
   
?>
   
   ```
   
   4. KakaoUser 데이터 저장
   - Shareduser table에 사용자 email과 shared_email (공유 대상자 이메일) 데이터를 저장합니다. <br>
    ![image](https://user-images.githubusercontent.com/80194089/121157974-f6653c00-c884-11eb-9483-54b3578c8cb6.png)

----------------------------------------------------------------------------------------------------------------------------------   
   
   ### Background
   1. Background Service 주요 코드 설명
   - 백그라운드 로케이션 기능은 백그라운드 쓰레드를 생성함으로써 앱이 종료되어도 정해진 주기에 따라 사용자의 위치정보를 추적하여 데이터베이스에 저장함으로써 사용자의 위치를 주기적으로 갱신하는 알고리즘입니다. 
   
   2. android studio : Background Service 코드
   - 서버에서 불러온 위도, 경도 값으로 사용자 위치 정보를 불러옵니다.
    
   ```java
     if (ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context1, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            Log.d("권한설정", "권한설정을 해주세요.");
                        }

                        if (LocationManager.GPS_PROVIDER != null) {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);
                        } else if (LocationManager.NETWORK_PROVIDER != null) {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1000, locationListener);
                        } else {
                            Log.d("위치정보", "위치정보를 불러올수가 없습니다......ㅠㅠㅠ");
                        }
                        long time = System.currentTimeMillis();
                        Date now = new Date(time);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:ddHH:mm:ss");
                        String nowdate = format.format(now);


                        request = url + "?longtitude="+getlongtitude()+"&latitude="+getlongtitude()+"&email="+useremail+"&time="+nowdate;
                        Log.d("request",request);
                        gethttpresponse.execute(request);

                    }
                },0);
   ```
   
   3. groomIDE 서버 : UploadBackground Loaction.php  
   - 사용자 위도, 경도, 이메일, 날짜 정보 데이터를 GET형태로 전송합니다.
 
   ```php
   <?php
	
	$connect = mysqli_connect("127.0.0.1","root1","1234","Project");

	$longtitude = $_GET["longtitude"];
	
	$latitude = $_GET["latitude"];

	$email = $_GET["email"];
	
	$Date = $_GET["time"];
    
 	$query = "insert into Backgroundlocation(email,latitude,longtitude,date) Value('$email',$latitude,$longtitude,'$Date')";
	mysqli_query($connect,$query);
	
	echo $query;
	
	include "sqldisconnect.php";
	
?>
   
   ```
-----------------------------------------------------------------------------------------------------------------------------   
   ### Upload
   1. KakoMap IMG Upload 주요 코드 설명 
   - 이 코드는 이미지가 업로드 할 때 사용되는 코드입니다. 이미지 업로드 알고리즘은 공유대상으로 지정된 사람이 근처 1km이내에 존재할 경우 당일에 찍은 모든 사진이 자동으로 업로드가 되는 방식입니다. 
   
   2.  android studio : Map Activity (IMG Upoad) 
   - server Url로 php와 android studio를 연동합니다. IMG Upload 할때 필요한 사용자 정보(email, date, imgPath)를 불러옵니다. 
   
   ```Java
   public void Upload(String imgPath,String email,String date){

        String serverUrl="https://phpproject-cparr.run.goorm.io/UploadImage.php";

        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hellosuccess100",response);
            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        smpr.addStringParam("email",email);
        smpr.addStringParam("date",date);
        smpr.addStringParam("path",imgPath);
        smpr.addFile("img", imgPath);

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        Log.d("smpr", String.valueOf(smpr));
        requestQueue.add(smpr);


    }
   ```
   
   3. groomIDE 서버 : UploadImage.php
   - 이미지 파일을 영구보관하기 위해 이미지 파일의 세부정보 받아와서 임시 저장소인 'uploads' 폴더에 이미지를 이동시킨다. 
  
  ```php
   <?php

	$connect = mysqli_connect("127.0.0.1","root1","1234","Project");
	$email= $_POST['email'];
	$date = $_POST['date']; 
	$path = $_POST['path'];
	$file= $_FILES['img'];
        $srcName= $file['name'];
        $tmpName= $file['tmp_name']; 
 	
	$query = "select * from File_Info where email = '".$email."' And file_name = '".$srcName."'";
	$result = mysqli_query($connect,$query);
	
	if(mysqli_num_rows($result)>0){
	echo "exist file";	
	}	
	else{
		
		$dstName= "uploads/".date('Ymd_his')."_".$srcName;
    	$result=move_uploaded_file($tmpName, $dstName);

		$query = "insert into File_Info(email,upload_time,file_name,file_path) Value('$email','$date','$srcName','$dstName')";

		mysqli_query($connect,$query);
		
		echo $email;
		
	}
			
?>
   ```

 4. Upload 데이터 저장

|      File_Info DB         |    Server Uploads   |     
| :-----------------------: | :----------------:  | 
| ![image](https://user-images.githubusercontent.com/80194089/121165916-9756f580-c88b-11eb-9ca2-40e7067fa544.png) | ![image](https://user-images.githubusercontent.com/80194089/121166337-f288e800-c88b-11eb-8bf1-415b6a35020c.png) |
|      업로드에 필요한 데이터를 저장한다.       |    업로드 한 이미지가 서버에 저장된다.   |




---------------------------------------------------------------------------------------------------------------------------
   ### Download
   1. KakoMap IMG Download주요 코드 설명 
   - 사용자가 올린 사진의 위치정보와 사용자가 지정한 공유대상자의 위치정보가 일정 거리 이내로 일치할 경우 사용자가 업로드한 사진은 자동으로 공유됩니다.

   2. android studio : Map Activity (IMG Download)
   - server Url로 php 서버와 안드로이드 스튜디오를 연동했습니다.
  
  ```Java
  public void downloadImage(String requestemail,String requestdate,Context mcontext){

        String serverUrl="https://phpproject-cparr.run.goorm.io/downloadrequest.php";
        String downloadUrl = "https://phpproject-cparr.run.goorm.io/";
        long time = System.currentTimeMillis();
        Date now = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd");
        String nowdate = format.format(now);

        SimpleMultiPartRequest smpr= new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("downloadsuccess",response);
                String[] strarr = response.split("%");
                for(int i = 0 ;i<strarr.length;i++){
                    String[] finalarr = strarr[i].split("#");
                    Log.d("finalarr", finalarr[1]);
                    Log.d("finalname",finalarr[0]);
                    String finalurl = downloadUrl + finalarr[1];
                    Log.d("testurl",finalurl);
                    DownloadManager downloadManager = (DownloadManager)getSystemService(mcontext.DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(finalurl));
                    File outputFile = new File("/storage/emulated/0/DCIM/"+nowdate+"/"+finalarr[0]);
                    request.setDestinationUri(Uri.fromFile(outputFile));
                    request.setTitle(finalarr[0]);
                    request.setDescription("다운로드 중..");
                    request.setNotificationVisibility(1);

                    long enqueue = downloadManager.enqueue(request);
                    Log.d("final","final");
                }
            }
        }
  
  ```
  
  3. groomIDE 서버 : downloadrequest.php
  - 사용자 이메일 데이터를 통해 업로드 된 이미지를 자동 다운로드 받을 수 있는 서버 코드이다. 
   ```php
   <?php
	$connect = mysqli_connect("127.0.0.1", "root1", "1234", "Project");

	$re_email = "ansdudgh98@naver.com";
	$input_date = $_POST["request_date"];
	
	$date = date("Y-m-d");
	
	$query = "select * from File_Info where email = '".$re_email."' and upload_time like '".$date."%'";
		
	$result = mysqli_query($connect,$query);
	$numrow = mysqli_num_rows($result);
			
	for($i=0; $i<$numrow; $i++){
		$row[$i]=mysqli_fetch_array($result); 
		echo $row[$i]["file_name"];
		echo "#";
		echo $row[$i]["file_path"];
		echo "%";
	}
   ```
   
   4. groomIDE 서버 : coordinate.php
   - 사용자가 업로드한 이미지 파일과 공유대상자의 좌표를 계산하는 코드이다.
   
| param double $lat1  | param double $lon1 | param double $lat2 | param double $lon2 |
| :-----------------:| :----------------: | :----------------: | :----------------: |
| 이미지파일 좌표 위도 | 이미지파일 좌표 경도 | 공유대상자 좌표 위도 |공유대상자 좌표 경도 |

```php
function getDistance($lat1, $lng1, $lat2, $lng2) { 
$earth_radius = 6371; 
$dLat = deg2rad($lat2 - $lat1);
$dLon = deg2rad($lng2 - $lng1); 
$a = sin($dLat/2) * sin($dLat/2) + cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * sin($dLon/2) * sin($dLon/2); 
$c = 2 * asin(sqrt($a)); 
$d = $earth_radius * $c; 
return $d: // km 거리반환
//return * 1000; // m 거리 반환
}

// 거리가 1km 이내 일 경우 사진 다운로드
if($d < 1) {
	//echo json_encode
	$jsonData = json_encode($arr);
	echo "$jsonData"
		
	//사진 다운로드
}
```
    
  ### IMGAlgorithm
  
  
  ## Launch
  |      SplashActivity |  Access  |
| :-----------------------: | :----------------:  | 
| ![image](https://user-images.githubusercontent.com/80194089/121171134-100c8080-c891-11eb-93fb-f4930eba1131.png)| ![image](https://user-images.githubusercontent.com/80194089/121171160-1995e880-c891-11eb-8f08-3a8b8708a717.png) |
|      어플리케이션 시작시 나오는 화면입니다.    |    권한 요청 사진입니다.   |

 |      KakaoLogin Page |  KakaoLogin  |
| :-----------------------: | :----------------:  | 
| ![image](https://user-images.githubusercontent.com/80194089/121171661-b9ec0d00-c891-11eb-973f-c33eae275a95.png)| ![image](https://user-images.githubusercontent.com/80194089/121171685-c2dcde80-c891-11eb-8a09-fe3012e1512c.png) |
|      카카오 로그인 시작 페이지입니다.   |  이메일과 비밀번호를 입력하여 로그인하는 페이지입니다.    |
  
  

  ## 차별화
  - 기존의 사진공유 앱들은 사진의 공유가 자동화가 되지 않는다는 문제점들을 가지고 있었다. 이러한 문제를 해결하기 위해 Background Location을 이용하는 방법에 접근하였으며, 경도와 위도 즉, 위치를 이용하여 사진 업로드 및 다운로드 자동화를 구현해 차별화를 두었다. 
  - 기존의 사진공유앱들에서 볼 수 없는 백그라운드 로케이션 알고리즘을 이용한 위치기반 이미지 업로드가 가능하며, 기존의 앱들은 사진의 업로드와 다운로드를 수동으로 해야 하는 불편함을 자동화 방식을 통해 편리하게 사진을 공유할 수 있습니다.
  
  
  ## 배운점
  - 안드로이드 스튜디오의 주요 언어인 JAVA에 대해 공부를 하며 프로젝트 진행 이전보다 JAVA에 대한 이해도와 활용능력이 증가하였다.
  - 안드로이드 스튜디오와 연동하여 데이터를 송신해주는 PHP에 대한 이해도와 활용능력이 프로젝트 진행 이전보다 증가하였으며 또한, 웹서버의 사용법도 알 수 있었다.
  - 프로젝트 진행에 있어서 미완성된 부분이 다소 존재했기에 프로젝트 진행과정을 다시 한 번 돌아보게 하는 계기가 되었다.
