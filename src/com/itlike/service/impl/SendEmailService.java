package com.itlike.service.impl;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailService {
    /**
     * @param sentToEmail 发送到的邮箱地址
     * @throws Exception
     */
    public void sendEmail(String sentToEmail,String username) throws Exception {
        Properties prop = new Properties();
        // 163邮箱设置:
        prop.setProperty("mail.host", "smtp.163com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect("smtp.163.com", "tianez_hx@163.com", "123qweasdzxc");
        // 4、创建邮件
        Message message = createSimpleMail(session,sentToEmail,username);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }
    public MimeMessage createSimpleMail(Session session,String sentToEmail,String username) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("tianez_hx@163.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sentToEmail));
        // "954028010@qq.com"));
        // 邮件的标题
        message.setSubject("蚂蚁商城注册激活");
        // 邮件的文本内容
        message.setContent(
                "<h1>购物蚂蚁商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://antshop.vipgz1.idcfengye.com/loginServlet?action=activate&username="
                        + username + "'>点击激活此账户</a></h3>",
                "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}
