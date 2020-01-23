package org.sjsi.model.mail;

public class Mail {
    private String address;
    private String content;

    public Mail(String address, String content) {
        this.address = address;
        this.content = content;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
