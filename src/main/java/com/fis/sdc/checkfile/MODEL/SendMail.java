package com.fis.sdc.checkfile.MODEL;

import java.util.List;

public class SendMail {
    private String to;
    private String cc;
    private String subject;
    private String content;
    private List<File> idfile;

    public SendMail(String to, String cc, String subject, String content, List<File> idfile) {
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.content = content;
        this.idfile = idfile;
    }

    public SendMail() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<File> getIdfile() {
        return idfile;
    }

    public void setIdfile(List<File> idfile) {
        this.idfile = idfile;
    }
}
