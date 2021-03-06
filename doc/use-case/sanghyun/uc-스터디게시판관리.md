# UC - 스터디 게시판 관리(Study Board Management)

스터디 게시글을 등록, 조회, 변경, 삭제하는 유스케이스이다.

## 주 액터(Primary Actor)

스터디장, 스터디원

## 보조 액터(Secondary Actor)

## 사전 조건(Preconditions)

- 스터디장 또는 스터디원으로 로그인되있어야 한다.
- 스터디 그룹에 가입되 있어야 한다.

## 종료 조건(Postconditions)

- 스터디 게시글을 등록하였다.
- 스터디 게시글을 조회하였다.
- 스터디 게시글을 변경하였다.
- 스터디 게시글을 삭제하였다.

## 시나리오(Flow of Evnets)

### 게시글 등록하기

1. 액터는 게시글 글쓰기 버튼을 클릭한다.
2. 시스템은 게시글 등록 폼을 출력한다.
3. 액터는 제목, 내용을 입력하고 등록 버튼을 누른다.
4. 시스템은 게시글 정보를 저장한 후 '게시글 조회하기' 유스케이스의 2번으로 간다.
    - 제목, 내용이 비어 있다면,
        - 시스템은 제목, 내용을 필히 입력하도록 알린다.

### 게시글 조회하기

1. 액터는 MyStudy 페이지에서 게시판 탭을 클릭한다.
2. 시스템은 게시글 목록(번호, 제목, 작성자, 작성일, 조회수)을 출력한다.
3. 액터는 목록에서 게시글 제목을 클릭한다.
4. 시스템은 게시글 상세 정보(번호, 제목, 내용, 작성자, 작성일, 조회수)를 출력한다.
    - 게시글이 존재하지 않는다면,
        - 시스템은 게시글이 존재하지 않는다는 내용을 출력한다.

### 자신이 작성한 게시글 변경하기

1. 액터는 자신이 작성한 게시글 상세 정보 화면에서 제목, 내용을 변경한다.
2. 시스템은 게시글 정보를 변경한 후 '게시글 조회하기' 유스케이스의 4번으로 간다.
     - 제목, 내용이 비어 있다면,
        - 시스템은 제목, 내용을 필히 입력하도록 알린다.

### 자신이 작성한 게시글 삭제하기(스터디원)

1. 스터디원은 자신이 작성한 게시글 상세 정보 화면에서 삭제 버튼을 클릭한다.
2. 시스템은 해당 게시글을 삭제한 후 '게시글 조회하기' 유스케이스의 2번으로 간다.
    - 게시글이 존재하지 않는다면,
        - 시스템은 게시글이 존재하지 않는다는 내용을 출력한다.

### 게시글 삭제하기(스터디장)

1. 스터디장은 게시글의 상세 정보 화면에서 삭제 버튼을 클릭한다.
2. 시스템은 해당 게시글을 삭제한 후 '게시글 조회하기' 유스케이스의 2번으로 간다.
    - 게시글이 존재하지 않는다면,
        - 시스템은 게시글이 존재하지 않는다는 내용을 출력한다.

## UI 프로토타입

![스터디게시판관리](./images/스터디게시판.png)