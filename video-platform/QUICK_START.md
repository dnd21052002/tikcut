# 🚀 Quick Start - Video Platform

## Chạy nhanh hệ thống

### 1️⃣ Một lệnh để chạy tất cả:
```bash
./start-all.sh
```

### 2️⃣ Kiểm tra các địa chỉ:
- 🎬 **Video Editor**: http://localhost:3000
- 📺 **Video Viewer**: http://localhost:3001  
- 👨‍💼 **Admin Panel**: http://localhost:3002
- 🔧 **Backend API**: http://localhost:8080
- 📋 **Database Console**: http://localhost:8080/h2-console

### 3️⃣ Dừng hệ thống:
```bash
./stop-all.sh
```

### 4️⃣ Khởi động lại:
```bash
./restart-all.sh
```

---

## 🎯 Test nhanh

### Đăng ký user mới:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "123456",
    "email": "test@example.com",
    "firstName": "Test",
    "lastName": "User"
  }'
```

### Đăng nhập:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "123456"
  }'
```

---

## 🛠️ Debug

### Xem logs:
```bash
# Backend logs
tail -f logs/backend.log

# Frontend logs
tail -f logs/frontend-editor.log
tail -f logs/frontend-viewer.log
tail -f logs/frontend-admin.log
```

### Kiểm tra ports:
```bash
# Kiểm tra port nào đang sử dụng
lsof -i :8080  # Backend
lsof -i :3000  # Editor
lsof -i :3001  # Viewer
lsof -i :3002  # Admin
```

### Kill processes nếu cần:
```bash
# Kill tất cả Node.js processes
pkill -f "react-scripts start"

# Kill backend Java process
pkill -f "spring-boot:run"
```

---

## 📱 Sử dụng cơ bản

### Video Editor (localhost:3000):
1. Đăng ký/Đăng nhập
2. Upload video bằng drag & drop
3. Edit video với timeline
4. Preview video gốc vs edited
5. Export hoặc Publish

### Video Viewer (localhost:3001):
1. Browse video public
2. Search video
3. Xem streaming HLS
4. Like/View videos

### Admin Panel (localhost:3002):
1. Đăng nhập với admin account
2. Review video pending
3. Approve/Reject videos
4. Manage users

---

## 🔧 Cấu hình nhanh

### Database:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(để trống)*

### Default Ports:
- Backend: `8080`
- Editor: `3000`
- Viewer: `3001`
- Admin: `3002`

### Upload Directories:
```
uploads/
├── videos/      # Original videos
├── thumbnails/  # Generated thumbnails
├── hls/        # HLS streaming files
└── temp/       # Temporary files
```

---

## ⚡ Troubleshooting nhanh

### Lỗi thường gặp:

**Port already in use:**
```bash
# Kill process on port
kill -9 $(lsof -ti:3000)
```

**FFmpeg not found:**
```bash
# Ubuntu/Debian
sudo apt install ffmpeg

# macOS
brew install ffmpeg
```

**CORS errors:**
- Kiểm tra `application.properties` có đúng origins
- Restart backend after changes

**Database connection:**
- H2 console: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`

---

## 🎉 Ready to go!

Hệ thống đã sẵn sàng để:
- ✅ Upload và edit video
- ✅ Streaming HLS
- ✅ User authentication
- ✅ Admin moderation
- ✅ Multi-app architecture

**Happy coding! 🚀**