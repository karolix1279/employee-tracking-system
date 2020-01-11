package employee.tracking.system.model.dto;


public class TrackedInformationDto {

    private String computerName;

    private int countClickedButtonsKeyboard;
    private int countClickedButtonsMouse;

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public int getCountClickedButtonsKeyboard() {
        return countClickedButtonsKeyboard;
    }

    public void setCountClickedButtonsKeyboard(int countClickedButtonsKeyboard) {
        this.countClickedButtonsKeyboard = countClickedButtonsKeyboard;
    }

    public int getCountClickedButtonsMouse() {
        return countClickedButtonsMouse;
    }

    public void setCountClickedButtonsMouse(int countClickedButtonsMouse) {
        this.countClickedButtonsMouse = countClickedButtonsMouse;
    }

    @Override
    public String toString() {
        return "TrackedInformationDto{" +
                "computerName='" + computerName + '\'' +
                ", countClickedButtonsKeyboard=" + countClickedButtonsKeyboard +
                ", countClickedButtonsMouse=" + countClickedButtonsMouse +
                '}';
    }
}
