#!/bin/bash

echo "🚀 Starting Video Platform System..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to check if command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check required tools
echo -e "${BLUE}Checking required tools...${NC}"

if ! command_exists java; then
    echo -e "${RED}❌ Java not found. Please install Java 17+${NC}"
    exit 1
fi

if ! command_exists node; then
    echo -e "${RED}❌ Node.js not found. Please install Node.js 16+${NC}"
    exit 1
fi

if ! command_exists ffmpeg; then
    echo -e "${YELLOW}⚠️  FFmpeg not found. Video processing may not work.${NC}"
    echo -e "${YELLOW}   Install with: sudo apt install ffmpeg (Ubuntu) or brew install ffmpeg (macOS)${NC}"
fi

echo -e "${GREEN}✅ Tools check completed${NC}"

# Create necessary directories
echo -e "${BLUE}Creating upload directories...${NC}"
mkdir -p uploads/{videos,thumbnails,hls,temp}

# Function to start backend
start_backend() {
    echo -e "${BLUE}🔧 Starting Backend (Spring Boot)...${NC}"
    cd backend
    
    # Check if maven wrapper exists
    if [ -f "./mvnw" ]; then
        ./mvnw spring-boot:run > ../logs/backend.log 2>&1 &
    else
        mvn spring-boot:run > ../logs/backend.log 2>&1 &
    fi
    
    BACKEND_PID=$!
    echo $BACKEND_PID > ../pids/backend.pid
    cd ..
    echo -e "${GREEN}✅ Backend started (PID: $BACKEND_PID)${NC}"
    echo -e "   📊 API Server: http://localhost:8080"
    echo -e "   📋 H2 Console: http://localhost:8080/h2-console"
}

# Function to start frontend-editor
start_editor() {
    echo -e "${BLUE}🎬 Starting Video Editor Frontend...${NC}"
    cd frontend-editor
    
    # Install dependencies if node_modules doesn't exist
    if [ ! -d "node_modules" ]; then
        echo -e "${YELLOW}📦 Installing dependencies...${NC}"
        npm install
        npm install axios react-router-dom @mui/material @emotion/react @emotion/styled
        npm install video.js videojs-contrib-hls fabric react-dropzone react-player
    fi
    
    npm start > ../logs/frontend-editor.log 2>&1 &
    EDITOR_PID=$!
    echo $EDITOR_PID > ../pids/frontend-editor.pid
    cd ..
    echo -e "${GREEN}✅ Video Editor started (PID: $EDITOR_PID)${NC}"
    echo -e "   🎬 Editor: http://localhost:3000"
}

# Function to start frontend-viewer
start_viewer() {
    echo -e "${BLUE}📺 Starting Video Viewer Frontend...${NC}"
    cd frontend-viewer
    
    # Create if doesn't exist
    if [ ! -f "package.json" ]; then
        echo -e "${YELLOW}📦 Creating Video Viewer app...${NC}"
        npx create-react-app . --yes
        npm install axios react-router-dom @mui/material
        npm install video.js videojs-contrib-hls hls.js
    fi
    
    PORT=3001 npm start > ../logs/frontend-viewer.log 2>&1 &
    VIEWER_PID=$!
    echo $VIEWER_PID > ../pids/frontend-viewer.pid
    cd ..
    echo -e "${GREEN}✅ Video Viewer started (PID: $VIEWER_PID)${NC}"
    echo -e "   📺 Viewer: http://localhost:3001"
}

# Function to start frontend-admin
start_admin() {
    echo -e "${BLUE}👨‍💼 Starting Admin Panel Frontend...${NC}"
    cd frontend-admin
    
    # Create if doesn't exist
    if [ ! -f "package.json" ]; then
        echo -e "${YELLOW}📦 Creating Admin Panel app...${NC}"
        npx create-react-app . --yes
        npm install axios react-router-dom @mui/material
        npm install @mui/x-data-grid recharts
    fi
    
    PORT=3002 npm start > ../logs/frontend-admin.log 2>&1 &
    ADMIN_PID=$!
    echo $ADMIN_PID > ../pids/frontend-admin.pid
    cd ..
    echo -e "${GREEN}✅ Admin Panel started (PID: $ADMIN_PID)${NC}"
    echo -e "   👨‍💼 Admin: http://localhost:3002"
}

# Create directories for logs and PIDs
mkdir -p logs pids

# Start all services
echo -e "${YELLOW}🔥 Starting all services...${NC}"
echo ""

start_backend
sleep 5  # Wait for backend to start

start_editor &
start_viewer &
start_admin &

# Wait for frontends to start
sleep 10

echo ""
echo -e "${GREEN}🎉 All services started successfully!${NC}"
echo ""
echo -e "${BLUE}📍 Access Points:${NC}"
echo -e "   🔧 Backend API: http://localhost:8080"
echo -e "   🎬 Video Editor: http://localhost:3000"
echo -e "   📺 Video Viewer: http://localhost:3001"
echo -e "   👨‍💼 Admin Panel: http://localhost:3002"
echo ""
echo -e "${BLUE}📊 Monitoring:${NC}"
echo -e "   📋 H2 Database Console: http://localhost:8080/h2-console"
echo -e "   📝 Backend Logs: tail -f logs/backend.log"
echo -e "   📝 Frontend Logs: tail -f logs/frontend-*.log"
echo ""
echo -e "${YELLOW}🛑 To stop all services, run: ./stop-all.sh${NC}"
echo -e "${YELLOW}🔄 To restart all services, run: ./restart-all.sh${NC}"
echo ""

# Create stop script
cat > stop-all.sh << 'EOF'
#!/bin/bash

echo "🛑 Stopping Video Platform System..."

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

# Function to stop service by PID file
stop_service() {
    local service_name=$1
    local pid_file=$2
    
    if [ -f "$pid_file" ]; then
        local pid=$(cat "$pid_file")
        if kill -0 "$pid" 2>/dev/null; then
            echo -e "${YELLOW}Stopping $service_name (PID: $pid)...${NC}"
            kill "$pid"
            rm "$pid_file"
            echo -e "${GREEN}✅ $service_name stopped${NC}"
        else
            echo -e "${YELLOW}$service_name not running${NC}"
            rm "$pid_file"
        fi
    else
        echo -e "${YELLOW}$service_name PID file not found${NC}"
    fi
}

# Stop all services
stop_service "Backend" "pids/backend.pid"
stop_service "Video Editor" "pids/frontend-editor.pid"
stop_service "Video Viewer" "pids/frontend-viewer.pid"
stop_service "Admin Panel" "pids/frontend-admin.pid"

# Kill any remaining Node processes on our ports
echo -e "${YELLOW}Cleaning up remaining processes...${NC}"
pkill -f "react-scripts start" 2>/dev/null || true

echo -e "${GREEN}🎉 All services stopped!${NC}"
EOF

chmod +x stop-all.sh

# Create restart script
cat > restart-all.sh << 'EOF'
#!/bin/bash

echo "🔄 Restarting Video Platform System..."

# Stop all services
./stop-all.sh

# Wait a bit
sleep 3

# Start all services
./start-all.sh
EOF

chmod +x restart-all.sh

echo -e "${GREEN}📁 Helper scripts created:${NC}"
echo -e "   🛑 stop-all.sh - Stop all services"
echo -e "   🔄 restart-all.sh - Restart all services"