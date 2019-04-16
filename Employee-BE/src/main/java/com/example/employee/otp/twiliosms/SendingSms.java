package com.example.employee.otp.twiliosms;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;

public class SendingSms {

    public final static String messsage_SID = "AC0ab6d7ff1535cfc6002fc2e944160f1c";
    private final static String AUTH_TOKEN = "c49f09fd9361d873b15ad33709103cdd";

    public void sendSms(String otp, String mobileNo){

        //for sending sms
        Twilio.init(messsage_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("91"+mobileNo),
                                 new PhoneNumber("+15612570677"),
                "Please don't share your 6 digit otp :"+otp).create();

        //for checking message status
        ResourceSet messages = Message.reader().read();
        for (Object message1 : messages) {
            System.out.println(message.getSid() + " : " + message.getStatus());

            // for checking sms delivery status

            ListenableFuture<ResourceSet<Message>> future = Message.reader().readAsync();
            Futures.addCallback(
                    future,
                    new FutureCallback<ResourceSet<Message>>() {
                        public void onSuccess(ResourceSet<Message> messages) {
                            for (Message message : messages) {
                                System.out.println(message.getSid() + " : " + message.getStatus());
                            }
                        }
                        public void onFailure(Throwable t) {
                            System.out.println("Failed to get message status: " + t.getMessage());
                        }
                    });

        }

    }
}
