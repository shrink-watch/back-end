# 슈링크워치 백엔드 API 가이드 (프론트엔드용)

> 모든 응답 예시는 실제 동작으로 검증된 JSON입니다.
> Base URL: `http://localhost:8080`

## 공통 사항

- 요청/응답 형식: `application/json`
- 리소스를 찾지 못하면 `404` + 아래 형식으로 응답합니다.
  ```json
  { "status": 404, "message": "상품을 찾을 수 없습니다. id=99999" }
  ```
- CORS: `localhost:3000`(React), `localhost:5173`(Vite) 허용됨.
  다른 포트를 쓰면 알려주세요 → `WebConfig.java`에 추가합니다.

---

## 1. 상품

### 1-1. 상품 통합 검색
- `GET /api/products/search?keyword={검색어}`
- 13자리 숫자면 바코드 검색, 아니면 상품명 부분일치 검색
- **응답**
```json
[
  {
    "id": 101,
    "name": "국민 만두 냉동",
    "price": 10000,
    "unit_price_text": "10g당 690원",
    "barcode": "8801234567890",
    "is_detected": true,
    "annual_damage_cost": 5000,
    "inflation_rate": 2.0,
    "categoryName": "냉동식품"
  }
]
```

### 1-2. 상품 상세 (차트·대안상품 일괄)
- `GET /api/products/{id}`
- **응답**
```json
{
  "id": 101,
  "name": "국민 만두 냉동",
  "price": 10000,
  "unit_price_text": "10g당 690원",
  "is_detected": true,
  "rating": 3.5,
  "categoryName": "냉동식품",
  "chartData": [
    { "date": "24.01", "normalPrice": 9000, "unitPrice": 620 },
    { "date": "25.06", "normalPrice": 10000, "unitPrice": 690 }
  ],
  "alternativeProducts": [
    {
      "name": "바른 만두 대용량",
      "price": 12000,
      "unit_price_text": "10g당 550원",
      "rating": 4.0,
      "coupang_url": "https://coupang.com/mock"
    }
  ]
}
```

### 1-3. 카테고리별 상품
- `GET /api/products/search/category/{categoryId}`
- 응답: 1-1과 동일한 배열 형식

### 1-4. 랭킹/큐레이션 (메인 홈)
응답은 모두 1-1과 동일한 배열 형식.

| 용도 | 경로 |
| --- | --- |
| 실질 단가 상승률 TOP 10 | `GET /api/products/search/ranking/inflation` |
| 용량감소(슈링크플레이션) TOP 10 | `GET /api/products/search/ranking/capacity` |
| 가격동결(상승률 0%) | `GET /api/products/search/ranking/solid` |
| 소비자원 적발(검증완료) 상품 | `GET /api/products/search/ranking/detected` |

---

## 2. 제보

> ⚠️ 제보는 등록 직후 `PENDING` 상태이며, **관리자 승인(APPROVED) 후에만** 공개 피드(2-1)에 노출됩니다.

### 2-1. 제보 피드 (승인된 것만, 최신순)
- `GET /api/reports`
- **응답**
```json
[
  {
    "reportId": 1,
    "nickname": "User",
    "productName": "국민 만두 냉동",
    "content": "양 줄었어요",
    "previousVolume": 420.0,
    "currentVolume": 350.0,
    "price": 10000,
    "store": "이마트",
    "imageUrl": null,
    "status": "APPROVED",
    "createdAt": "2026-06-21T17:21:56.167925"
  }
]
```
> `imageUrl`은 현재 미사용(항상 null). 추후 이미지 첨부 기능 시 활성화 예정.

### 2-2. 제보 등록
- `POST /api/reports`
- **요청 바디**
```json
{
  "productName": "국민 만두 냉동",
  "content": "양 줄었어요",
  "previousVolume": 420,
  "currentVolume": 350,
  "price": 10000,
  "store": "이마트"
}
```
- **응답 (201)**
```json
{ "success": true, "message": "소비자 제보 고발이 실시간 피드에 정상 반영되었습니다." }
```

---

## 3. 관리자 (제보 검수)

> 현재 인증 없음. 추후 관리자 인증 적용 예정.

| 용도 | 경로 | 응답 |
| --- | --- | --- |
| 승인 대기 목록 | `GET /api/admin/reports/pending` | 2-1과 동일 형식 (status=PENDING) |
| 제보 승인 | `PATCH /api/admin/reports/{reportId}/approve` | 해당 제보 1건 (status=APPROVED) |
| 제보 반려 | `PATCH /api/admin/reports/{reportId}/reject` | 해당 제보 1건 (status=REJECTED) |

---

## ❓ 프론트엔드에 확인 요청

- 위 **필드명**(`unit_price_text`, `is_detected`, `categoryName`, `coupang_url` 등)이 화면 코드와 일치하나요? 다르면 맞추겠습니다.
- 개발 서버 포트가 3000/5173이 아니면 알려주세요 (CORS 추가).
