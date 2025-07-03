package command;

public class RoomTypeInvoker {
    private RoomTypeCommand command;

    public void setCommand(RoomTypeCommand command) {
        this.command = command;
    }

    public void run() {
        if (command != null) {
            command.execute();
        }
    }
} 