### 프로젝트 생성 및 라이브러리
 1. 해당 프로젝트 생성은 spring boot starter(https://start.spring.io/) 에서 쉽게 프로젝트르 생성 할 수 있음.
 
 2. 프로젝트 생성 시 필요한 라이브러리는 하단과 같다.
  
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web
  - Spring Boot Starter Thymeleaf
  - Spring Boot Devtools
  - Lombok
  - H2 Database
 

---
### 요구사항
 1. 회원
 2. 주문정보
 3. 주문상품
 4. 상품
 5. 카테고리
 6. 배송정보(임베디드 타입)
 
 - 회원은 여러개의 주문 정보를 가질 수 있다.
 - 주문정보는 여러개의 주문 상품을 가질 수 있으며, 주문정보마다 하나의 배송정보를 가지고 있다.
 - 주문 상품은 각 상품의 정보(가격), 구매수량을 가지고 있다.
 - 상품 정보에는 도서, 음반, 영화 정보가 존재하며, 각각 사용하는 속성이 다르다.
 - 상품과 카테고리는 다대다 관계를 가진다.
 - 카테고리는 트리형으로 표현이 필요하기에 parent, child 각각 부모와 자식 카테고리를 연결한다.
 
---