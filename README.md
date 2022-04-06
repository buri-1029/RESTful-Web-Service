# RESTful-Web-Service

> **Spring Boot를 이용한 RESTful Web Service 개발** By. [인프런 강의](https://www.inflearn.com/course/spring-boot-restful-web-services/dashboard)

<br/>

### REST API 설계

|설명|REST API|HTTP Method|
|------|---|---|
|모든 사용자 조회|/users|GET|
|사용자 생성|/users|POST|
|특정 사용자 조회|/users/{id}|GET|
|사용자 삭제|/users/{id}|DELETE|
|사용자의 모든 게시글 조회|/users/{id}/posts|GET|
|사용자의 게시글 생성|/users|POST|
|사용자의 특정 게시글 조회|/users/{id}/posts/{postId}|GET|

<br/>

### HATEOAS 설정 방법

> spring hateoas 의존성 추가

```
dependencies {
    ...
  
    implementation 'org.springframework.boot:spring-boot-starter-hateoas'
    
    ...
}
```
<br/>

> 단일 Entity hateoas 적용
- EntityModel<T> 클래스 이용, Static Method로 객체를 생성하기 때문에 `EntityModel.of()`를 통해서 객체 생성
- `add()`를 통해서 link 추가  

```java
@GetMapping("/{id}")
public EntityModel<UserResponse> retrieveUser(@PathVariable Long id){
	return EntityModel.of(
	    userService.getUser(id),
	    linkTo(methodOn(UserController.class).retrieveUser(id)).withSelfRel(), // 자기자신
	    linkTo(methodOn(UserController.class).retrieveAllUsers()).withRel("all-users") // 그외
	);
}
```

```json
{
  "id": 1,
  "name": "hello",
  "createdAt": "2022-04-06",
  "password": "12345678",
  "ssn": "201111-1111111",
  "_links": {
    "self": {
      "href": "http://localhost:8081/users/1"
    },
    "all-users": {
      "href": "http://localhost:8081/users"
    }
  }
}
```
<br/>

> 복수 Entity hateoas 적용
- List에 있는 Entity를 EntityModel로 바꾸어주고 `CollectionModel.of`를 통해 값 반환

```java
@GetMapping
public List<EntityModel<UserResponse>> retrieveAllUsers() {
    return userService.getUsers()
                      .stream()
                      .map(users -> EntityModel.of(
                           users,
                           linkTo(methodOn(UserController.class)
                                    .retrieveAllUsers()).withSelfRel()
                      ))
                      .collect(Collectors.toList());

}
```

```json
[
  {
    "id": 1,
    "name": "hello",
    "createdAt": "2022-04-06",
    "password": "12345678",
    "ssn": "201111-1111111",
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8081/users"
      }
    ]
  },
  {
    "id": 2,
    "name": "hihi",
    "createdAt": "2022-04-06",
    "password": "12345678",
    "ssn": "201222-2222222",
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8081/users"
      }
    ]
  }
]
```
<br/><br/>

###### 또 다른 HATEOAS 설정 방법 (추후 알아볼 예정)

- RepresentationModelAssemblerSupport<T,D>클래스 이용
- RepresentationModelProcessor<T> 이용