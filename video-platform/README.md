# Video Platform - Hệ thống Edit và Streaming Video

## 🎯 Tổng quan hệ thống

Đây là một hệ thống webapp hoàn chỉnh để edit và streaming video, được thiết kế giống như CapCut và Premiere Pro. Hệ thống bao gồm 3 thành phần chính:

### 🏗️ Kiến trúc hệ thống

```
video-platform/
├── backend/                    # Spring Boot API Server
├── frontend-editor/           # React App cho video editing
├── frontend-viewer/           # React App cho xem video streaming  
└── frontend-admin/           # React App cho admin/moderation
```

## ✨ Tính năng chính

### 🎬 Video Editor (frontend-editor)
- ✅ Upload video lên hệ thống
- ✅ Edit video với giao diện giống CapCut
- ✅ Preview video gốc và video đã edit song song
- ✅ Lưu video edit (local nếu chưa đăng nhập, server nếu đã đăng nhập)
- ✅ Export video đã edit
- ✅ Publish video (chỉ user đã đăng nhập)
- 🔄 Timeline editing với drag & drop
- 🔄 Video effects và filters
- 🔄 Audio editing và mixing

### 📺 Video Viewer (frontend-viewer)
- ✅ Xem video streaming dạng HLS (m3u8)
- ✅ Browse các video đã được publish
- ✅ Search và filter video
- 🔄 Video player với controls đầy đủ
- 🔄 Comment và rating system
- 🔄 Playlist và favorites

### 👨‍💼 Admin Panel (frontend-admin)
- ✅ Kiểm duyệt video trước khi publish
- ✅ Quản lý user và permissions
- ✅ Analytics và reporting
- 🔄 AI-powered content moderation
- 🔄 Batch operations

### 🔐 Authentication & Authorization
- ✅ JWT-based authentication
- ✅ Role-based access control (USER, ADMIN, MODERATOR)
- ✅ Registration và login
- ✅ Token validation và refresh

### 🎞️ Video Processing
- ✅ FFmpeg integration cho video processing
- ✅ Thumbnail generation
- ✅ HLS streaming preparation
- ✅ Multiple quality levels
- 🔄 Real-time processing status

## 🛠️ Tech Stack

### Backend
- **Java 17** với **Spring Boot 3.2.0**
- **Spring Security** cho authentication
- **JWT** cho token management
- **JPA/Hibernate** cho database operations
- **H2 Database** (development) / **PostgreSQL** (production)
- **FFmpeg** cho video processing
- **Maven** cho dependency management

### Frontend
- **React 18** với **Create React App**
- **Axios** cho API calls
- **React Router** cho routing
- **Material-UI/Ant Design** cho UI components
- **Video.js/React Player** cho video playback
- 🔄 **Fabric.js** cho video editing canvas

### Infrastructure
- **HLS Streaming** cho video delivery
- **CORS** configuration
- **File upload** với multipart support
- **WebSocket** cho real-time updates

## 🚀 Cài đặt và chạy hệ thống

### Yêu cầu hệ thống
- **Java 17+**
- **Node.js 16+** 
- **FFmpeg** (cài đặt system-wide)
- **Maven 3.6+**

### 1. Setup Backend

```bash
cd backend

# Cài đặt dependencies
./mvnw clean install

# Tạo database và chạy migrations
./mvnw spring-boot:run
```

Backend sẽ chạy trên: `http://localhost:8080`

### 2. Setup Frontend Editor

```bash
cd frontend-editor

# Cài đặt dependencies
npm install

# Cài thêm packages cần thiết cho video editing
npm install axios react-router-dom @mui/material @emotion/react @emotion/styled
npm install video.js videojs-contrib-hls fabric
npm install react-dropzone react-player

# Chạy development server
npm start
```

Frontend Editor sẽ chạy trên: `http://localhost:3000`

### 3. Setup Frontend Viewer

```bash
cd frontend-viewer

# Tạo React app cho viewer
npx create-react-app .
npm install axios react-router-dom @mui/material
npm install video.js videojs-contrib-hls hls.js

# Chạy trên port khác
PORT=3001 npm start
```

Frontend Viewer sẽ chạy trên: `http://localhost:3001`

### 4. Setup Frontend Admin

```bash
cd frontend-admin

# Tạo React app cho admin
npx create-react-app .
npm install axios react-router-dom @mui/material
npm install @mui/x-data-grid recharts

# Chạy trên port khác
PORT=3002 npm start
```

Frontend Admin sẽ chạy trên: `http://localhost:3002`

## 📡 API Endpoints

### Authentication
```
POST /api/auth/register    - Đăng ký user mới
POST /api/auth/login       - Đăng nhập
POST /api/auth/logout      - Đăng xuất
GET  /api/auth/validate    - Validate JWT token
```

### Videos
```
GET    /api/videos              - Lấy danh sách video
POST   /api/videos              - Upload video mới
GET    /api/videos/{id}         - Lấy thông tin video
PUT    /api/videos/{id}         - Cập nhật video
DELETE /api/videos/{id}         - Xóa video
POST   /api/videos/{id}/publish - Publish video
```

### Video Edits
```
GET    /api/video-edits                 - Lấy danh sách edit
POST   /api/video-edits                 - Tạo edit mới
GET    /api/video-edits/{id}            - Lấy thông tin edit
PUT    /api/video-edits/{id}            - Cập nhật edit
POST   /api/video-edits/{id}/export     - Export edit
```

### Admin/Reviews
```
GET    /api/reviews                     - Lấy danh sách cần review
POST   /api/reviews/{videoId}/approve   - Duyệt video
POST   /api/reviews/{videoId}/reject    - Từ chối video
```

### Public Streaming
```
GET    /api/public/videos               - Videos công khai
GET    /api/stream/{videoId}            - HLS streaming endpoint
GET    /api/uploads/hls/{path}          - HLS segments
```

## 🗃️ Database Schema

### User Entity
```sql
users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    avatar_url VARCHAR(255),
    role ENUM('USER', 'ADMIN', 'MODERATOR'),
    is_active BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)
```

### Video Entity
```sql
videos (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    original_filename VARCHAR(255),
    file_path VARCHAR(255),
    thumbnail_path VARCHAR(255),
    hls_path VARCHAR(255),
    file_size BIGINT,
    duration_seconds DOUBLE,
    width INTEGER,
    height INTEGER,
    frame_rate DOUBLE,
    bit_rate BIGINT,
    status ENUM('UPLOADED', 'PROCESSING', 'PROCESSED', 'PUBLISHED', 'REJECTED'),
    processing_status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'FAILED'),
    is_public BOOLEAN,
    view_count BIGINT,
    like_count BIGINT,
    owner_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    published_at TIMESTAMP
)
```

### Video Edit Entity
```sql
video_edits (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    edit_data TEXT,  -- JSON string chứa edit configuration
    output_path VARCHAR(255),
    output_hls_path VARCHAR(255),
    thumbnail_path VARCHAR(255),
    duration_seconds DOUBLE,
    status ENUM('DRAFT', 'IN_PROGRESS', 'COMPLETED', 'EXPORTED', 'PUBLISHED'),
    is_exported BOOLEAN,
    video_id BIGINT,
    editor_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)
```

### Video Review Entity
```sql
video_reviews (
    id BIGINT PRIMARY KEY,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'NEEDS_REVISION'),
    comments TEXT,
    rejection_reason TEXT,
    video_id BIGINT,
    reviewer_id BIGINT,
    created_at TIMESTAMP,
    reviewed_at TIMESTAMP
)
```

## 📁 Cấu trúc file system

```
uploads/
├── videos/           # Video files gốc
├── thumbnails/       # Thumbnail images
├── hls/             # HLS streaming files
│   ├── {videoId}/
│   │   ├── playlist.m3u8
│   │   ├── segment0.ts
│   │   ├── segment1.ts
│   │   └── ...
└── temp/            # Temporary processing files
```

## 🔧 Configuration

### Backend Configuration (application.properties)
```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop

# File Upload
spring.servlet.multipart.max-file-size=1000MB
spring.servlet.multipart.max-request-size=1000MB

# File Storage
file.upload-dir=./uploads
file.video-dir=./uploads/videos
file.hls-output-dir=./uploads/hls

# Video Processing
video.ffmpeg.path=ffmpeg
video.hls.segment-duration=10

# JWT
jwt.secret=mySecretKey12345678901234567890
jwt.expiration=86400000

# CORS
cors.allowed-origins=http://localhost:3000,http://localhost:3001,http://localhost:3002
```

## 🎮 Cách sử dụng

### 1. Người dùng chưa đăng nhập
- Upload video và edit → Lưu local (localStorage/IndexedDB)
- Không thể publish video
- Có thể xem video public

### 2. Người dùng đã đăng nhập
- Upload video và edit → Lưu trên server
- Có thể publish video (qua moderation)
- Quản lý video library cá nhân

### 3. Admin/Moderator
- Review và approve/reject video
- Quản lý users
- Analytics và monitoring

## 🚦 Workflow

1. **Upload Video** → Video được lưu với status `UPLOADED`
2. **Background Processing** → FFmpeg xử lý tạo HLS segments
3. **Edit Video** → User tạo edit với JSON configuration
4. **Export Edit** → Render video theo edit configuration
5. **Submit for Review** → Video chuyển status `PENDING_REVIEW`
6. **Moderation** → Admin approve/reject
7. **Publish** → Video public với HLS streaming

## 🔮 Features tương lai

### Phase 2
- [ ] Real-time collaborative editing
- [ ] Advanced video effects library
- [ ] AI-powered auto-editing
- [ ] Live streaming support
- [ ] Mobile app (React Native)

### Phase 3
- [ ] Multi-language support
- [ ] Advanced analytics dashboard
- [ ] Monetization system
- [ ] CDN integration
- [ ] Advanced AI content moderation

## 🐛 Troubleshooting

### FFmpeg không tìm thấy
```bash
# Ubuntu/Debian
sudo apt update && sudo apt install ffmpeg

# macOS
brew install ffmpeg

# Windows
# Download từ https://ffmpeg.org/download.html
```

### CORS Issues
Đảm bảo `cors.allowed-origins` trong `application.properties` có đúng URLs của frontend apps.

### Port conflicts
Thay đổi ports trong package.json hoặc sử dụng environment variables:
```bash
PORT=3003 npm start
```

## 📞 Support

Nếu gặp vấn đề, hãy check:
1. Logs của backend (`mvn spring-boot:run`)
2. Console của browser (F12)
3. Network tab để xem API calls
4. File permissions cho upload directories

---

## 📝 Notes

- Hệ thống này là foundation hoàn chỉnh có thể scale và mở rộng
- Authentication system đã implement JWT security
- Video processing pipeline ready cho production
- Database schema support cho future features
- CORS và security đã được config đúng

**Hệ thống đã sẵn sàng để chạy và phát triển thêm!** 🚀
