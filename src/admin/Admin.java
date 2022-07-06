package admin;

public class Admin {
    private String changeCurrent, newPassword, confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getChangeCurrent() {
        return changeCurrent;
    }

    public void setChangeCurrent(String changeCurrent) {
        this.changeCurrent = changeCurrent;
    }

}