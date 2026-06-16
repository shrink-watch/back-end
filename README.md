# 슈링크워치 (ShrinkWatch) - Backend

슈링크플레이션(용량 눈속임 물가상승)을 감지·공유하는 플랫폼의 백엔드입니다.

## 기술 스택

- Java 17
- Spring Boot 4.1.0 (Web MVC, Data JPA)
- MySQL

## 실행 방법

1. MySQL에 데이터베이스 생성

   ```sql
   CREATE DATABASE shrinkwatch;
   ```

2. `src/main/resources/application.yml`에서 DB 접속 정보(username/password) 확인

3. 애플리케이션 실행

   ```bash
   ./gradlew bootRun
   ```

   서버는 `http://localhost:8080`에서 실행됩니다.

> 참고: `ddl-auto: create` 설정이라 실행할 때마다 테이블이 새로 생성됩니다.

## API 목록

| 기능 | Method | Path |
| --- | --- | --- |
| 상품 통합 검색 (이름 또는 바코드) | `GET` | `/api/products/search?keyword=` |
| 상품 상세 조회 | `GET` | `/api/products/{id}` |
| 제보 피드 목록 조회 (최신순) | `GET` | `/api/reports` |
| 신규 제보 등록 | `POST` | `/api/reports` |
