# 自定義轉盤 411630998 蔡宇辰
## 一、專案目標
本系統旨在解決使用者面對多項選擇時的猶豫與遲疑問題，透過視覺化互動的「自定義轉盤」，幫助用戶快速做出決策。其可應用於抽籤、分組、活動抽獎、會議投票、日常選擇（如吃什麼、去哪裡）等情境。
#### 具體目標說明
| 項目     | 說明                              |
| ------ | ------------------------------- |
| 解決問題   | 使用者面對選擇障礙、難以決定時的心理負擔            |
| 替代傳統機制 | 以視覺化數位轉盤取代傳統抽籤、丟銅板等決策方式         |
| 提升互動體驗 | 使用動畫轉盤加強決策趣味性，降低選擇壓力            |
| 支援自定義  | 使用者可自訂選項數、內容、顏色與機率，使系統應用更彈性     |
| 擴展應用場景 | 可用於團體活動、會議、小遊戲、教學場景，提供分享與共用轉盤功能 |

## 第一階段：系統分析（System Analysis）

### [ ] 1. 問題定義與目標
- 解決「選擇困難」問題
- 提供可自定義的隨機決策工具（替代抽籤）

### [ ] 2. 使用者需求分析
- 使用者可以建立、命名轉盤
- 可自由增加、編輯、刪除轉盤項目
- 可按下按鈕隨機抽出結果
- 可查詢歷史紀錄

### [ ] 3. 功能需求整理
- 使用者帳號系統（登入、註冊）
- 轉盤 CRUD 功能
- 項目管理功能
- 抽選與儲存紀錄
- 歷史查詢功能（依時間與轉盤）

---

## 第二階段：系統設計（System Design）

### [ ] 1. 設計策略與技術選型
- 前端：React + TailwindCSS + Axios
- 後端：Node.js + Express + Mongoose
- 資料庫：MongoDB Atlas
- 部署：Frontend（Vercel）、Backend（Render）

### [ ] 2. 架構與介面設計
- 前端頁面結構：
  - 登入頁 /register
  - 控制台 /dashboard
  - 轉盤編輯頁 /wheel/:id
  - 歷史紀錄頁 /history
- 系統流程圖：
  - 使用者登入 → 建立轉盤 → 新增項目 → 抽選 → 儲存結果

### [ ] 3. 資料庫與 API 結構

#### 資料表（MongoDB Schemas）
- `User`：email, passwordHash, createdAt
- `Wheel`：name, userId, createdAt
- `WheelItem`：wheelId, label, color, weight
- `SpinHistory`：wheelId, resultLabel, spunAt

#### API 設計（部分範例）
- `POST /api/auth/register`：註冊
- `POST /api/auth/login`：登入
- `GET /api/wheels`：取得使用者所有轉盤
- `POST /api/wheels`：建立轉盤
- `POST /api/spin`：抽選轉盤
- `GET /api/history`：查詢歷史紀錄
