# RoadLine_v2
<img src="https://user-images.githubusercontent.com/46068444/124224225-33e57e00-db40-11eb-9d22-4c9ba72f5916.png" alter="roadline_intro_img" width="60%"/>

기존에 진행했던 **로드라인** 프로젝트를 MVVM 패턴과 AAC 라이브러리를 활용해 리팩토링하고 있습니다.  
자세한 앱 설명은 아래 링크(기존 프로젝트 repo)에 있습니다!  
👉 [로드라인 v1](https://github.com/enan501/MP2019_RoadLine)


## 주요 변경 내용
#### MVVM 패턴을 적용하여, Model / View / ViewModel 계층을 분리해 개발하였습니다.
- 가독성, 보일러 플레이트 감소, 역할 분리로 인한 개발 및 유지보수의 장점을 경험했습니다.
- ViewModel 계층으로 AAC의 viewModel을 사용해 액티비티의 생명주기 변화로부터 자유롭게 데이터를 보존할 수 있었습니다.
- LiveData와 다른 AAC 라이브러리들을 활용한 Observer Pattern을 통해 뷰와 모델의 데이터를 일치시킬 수 있었습니다.
#### Model 계층을 담당하는 DBMS를 Realm -> Room으로 변경하였습니다.
- Realm과 Room을 비교하여 장단점을 간단히 정리했습니다. 👉 [Realm vs Room](https://velog.io/@enan/Realm-vs-Room)
- 변경 이유
  - LiveData 등 다른 AAC 라이브러리와 함께 사용하여 변경 사항을 자동으로 갱신시키기 용이함
  - 앱 용량 감소(약 2MB)
  - realm을 사용해 성능을 최적화시켜야 할 만큼의 대용량 데이터를 저장할 일이 없음

