#!/bin/bash

echo "=== HỆ THỐNG QUẢN LÝ KHÁCH SẠN ==="
echo

echo "Biên dịch hệ thống..."
javac -cp "src" src/main/Main.java
if [ $? -ne 0 ]; then
    echo "❌ Lỗi biên dịch!"
    exit 1
fi

echo
echo "Chạy hệ thống..."
java -cp "src" main.Main

echo
echo "Nhấn Enter để thoát..."
read 