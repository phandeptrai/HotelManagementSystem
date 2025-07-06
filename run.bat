@echo off
echo === HỆ THỐNG QUẢN LÝ KHÁCH SẠN ===
echo.

echo Biên dịch hệ thống...
javac -cp "src" src/main/*.java
if %errorlevel% neq 0 (
    echo ❌ Lỗi biên dịch!
    pause
    exit /b 1
)

echo.
echo Chạy hệ thống...
java -cp "src" main.HotelSystemMain

echo.
echo Nhấn phím bất kỳ để thoát...
pause >nul 