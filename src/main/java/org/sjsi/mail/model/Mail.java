package org.sjsi.mail.model;

public class Mail {
    private String address;
    private String content;

    public Mail(String address, String content) {
        this.address = address;
        this.setContent(content);
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
