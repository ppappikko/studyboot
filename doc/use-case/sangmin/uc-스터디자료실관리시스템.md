# UC001 - 스터디 자료실 관리 시스템(Cloud System)
스터디 그룹에 파일을 등록, 조회, 수정, 삭제 하는 유스케이스다.

## 주 액터(Primary Actor)

스터디에 가입한 회원

## 보조 액터(Secondary Actor)

## 사전 조건(Preconditions)

- 스터디에 가입해있어야 한다.

## 종료 조건(Postconditions)

- 자료를 조회하였다.
- 자료를 등록하였다.
- 자료를 변경하였다.
- 자료를 삭제하였다.

## 시나리오(Flow of Events)

### 자료실 조회

1. 액터는 자료실 버튼을 클릭한다.
2. 시스템은 자료실에 업로드된 목록(폴더, 파일)을 출력한다.
    - 해당 자료실에 업로드된 목록이 없다면,
        - 시스템은 업로드된 목록이 존재하지 않는다는 내용을 출력한다.
3. 3-1 액터는 목록에서 폴더를 클릭한다.
4. 3-2 시스템은 해당 폴더안에 있는 목록(폴더, 파일)을 출력한다.
    - 해당 폴더에 업로드된 목록이 없다면,
        - 시스템은 폴더에 업로드된 목록이 존재하지 않는다는 내용을 출력한다.
5. 3-3 액터는 목록에서 파일을 클릭한다.
6. 3-4 시스템은 해당 파일의 확대된 이미지를 출력하고 이동, 다운로드 버튼을 출력한다.

### 자료실 등록

1. 액터는 자료실 버튼을 클릭한다.
2. 시스템은 자료실에 업로드된 목록(폴더, 파일)을 출력한다.
    - 해당 자료실에 업로드된 목록이 없다면,
        - 시스템은 업로드된 목록이 존재하지 않는다는 내용을 출력한다.
3. 액터는 새로 만들기 버튼을 클릭한다.
4. 시스템은 액터가 만들것이 폴더인지 파일인지 선택하는 화면을 출력한다.
5. 3-1-1 액터는 폴더 버튼을 클릭한다.
6. 3-1-2 시스템은 새로 만들 폴더 이름을 정하는 폼을 출력한다.
7. 3-1-3 액터는 폴더 이름을 입력 후 만들기 버튼을 클릭한다.
8. 3-1-4 시스템은 저장 후 '자료실 조회' 유스케이스로 이동한다.
9. 3-2-1 액터는 파일 버튼을 클릭한다.
10. 3-2-2 시스템은 액터가 원하는 파일을 찾을 수 있도록 파일 탐색기 창을 출력한다.
11. 3-2-3 액터는 원하는 파일을 선택한 후 완료 버튼을 누른다.
12. 3-2-4 시스템은 저장 후 '자료실 조회' 유스케이스로 이동한다.
    - 해당 파일의 용량이 너무 크거나 업로드할 수 없는 파일이라면,
        - 시스템은 업로드 할 수 없다는 내용을 출력한다.

### 자료실 변경

1. 액터는 자료실 조회 화면에서 폴더, 파일에 대하여 이름, 파일내용을 변경한다.
2. 시스템은 자료실 정보를 변경한 후 '자료실 조회' 유스케이스로 이동한다.
    - 사용할 수 없는 이름으로 변경한다면,
        - 시스템은 포함할 수 없는 문자가 들어갔음을 알린다.

### 자료실 삭제

1. 액터는 '자료실 조회' 유스케이스 화면에서 원하는 폴더나 파일을 선택 후 삭제 버튼을 클릭한다.
2. 시스템은 액터에게 정말 이 자료(폴더, 파일)을 삭제 할 것인지 묻는 문구를 출력한다.
3. 액터는 확인 버튼을 눌러 삭제를 완료한다.
4. 시스템은 해당 자료(폴더, 파일)을 휴지통으로 임사 삭제한 후 '자료실 조회' 유스케이스로 간다.
5. 액터는 임시 삭제된 자료(폴더, 파일)을 완벽히 삭제하기 위해 휴지통 버튼을 클릭한다.
6. 시스템은 휴지통 안에 들어 있는 자료(폴더, 파일) 목록을 출력한다.
    - 휴지통 안에 아무런 자료가 들어있지 않다면,
        - 자료가 없다는 내용을 출력한다.
7. 액터는 완전하게 삭제할 자료(폴더, 파일)을 선택한 후 삭제 버튼을 클릭한다.
8. 시스템은 완전하게 선택된 자료(폴더, 파일)을 삭제한 후 '자료실 삭제' 유스케이스 6번으로 이동한다.

### 자료실 공유

1. 액터는 자료실 버튼을 클릭한다.
2. 시스템은 자료실에 업로드된 목록(폴더, 파일)을 출력한다.
    - 해당 자료실에 업로드된 목록이 없다면,
        - 시스템은 업로드된 목록이 존재하지 않는다는 내용을 출력한다.
3. 액터는 공유할 파일을 선택하여 이동하기 버튼을 클릭한다.
4. 시스템은 해당 파일을 어디로 이동할지에 대한 문구를 출력한다.
5. 액터는 공유할 파일을 이동할 주소를 입력한 뒤 확인버튼을 클릭한다.
6. 시스템은 해당 주소로 파일을 복사하고 '자료실 유스케이스' 2번으로 이동한다.
    - 입력한 주소가 올바르지 않다면,
        - 시스템은 해당 주소가 올바르지 않다는 내용을 출력한다.


## UI 프로토타입

<img src="./UI/cloud01.png" width="400"/>
<img src="./UI/cloud02.png" width="400"/>
<img src="./UI/cloud03.png" width="400"/>
<img src="./UI/cloud04.png" width="400"/>
<img src="./UI/cloud05.png" width="400"/>






 

