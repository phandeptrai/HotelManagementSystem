package com.hotelsystem.command.room;

import java.util.Stack;

/**
 * Invoker for managing reservation commands with undo functionality
 */
public class ReservationInvoker {
    private Stack<ReservationCommand> commandHistory = new Stack<>();
    private Stack<ReservationCommand> undoHistory = new Stack<>();
    
    public void executeCommand(ReservationCommand command) {
        command.execute();
        commandHistory.push(command);
        // Clear undo history when new command is executed
        undoHistory.clear();
    }
    
    public void undo() {
        if (!commandHistory.isEmpty()) {
            ReservationCommand command = commandHistory.pop();
            command.undo();
            undoHistory.push(command);
            System.out.println("Đã hoàn tác lệnh: " + command.getDescription());
        } else {
            System.out.println("Không có lệnh nào để hoàn tác.");
        }
    }
    
    public void redo() {
        if (!undoHistory.isEmpty()) {
            ReservationCommand command = undoHistory.pop();
            command.execute();
            commandHistory.push(command);
            System.out.println("Đã làm lại lệnh: " + command.getDescription());
        } else {
            System.out.println("Không có lệnh nào để làm lại.");
        }
    }
    
    public void showCommandHistory() {
        System.out.println("=== LỊCH SỬ LỆNH ===");
        if (commandHistory.isEmpty()) {
            System.out.println("Chưa có lệnh nào được thực hiện.");
        } else {
            for (int i = 0; i < commandHistory.size(); i++) {
                System.out.println((i + 1) + ". " + commandHistory.get(i).getDescription());
            }
        }
        System.out.println("===================");
    }
    
    public void clearHistory() {
        commandHistory.clear();
        undoHistory.clear();
        System.out.println("Đã xóa lịch sử lệnh.");
    }
    
    public int getCommandCount() {
        return commandHistory.size();
    }
} 