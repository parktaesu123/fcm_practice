package com.example.fcm_practice.domain.fcm.domain;

public class FcmMessage {
    private final boolean validateOnly = false;
    private final Message message;

    public FcmMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        private final Notification notification;
        private final String token;

        public Message(Notification notification, String token) {
            this.notification = notification;
            this.token = token;
        }
    }

    public static class Notification {
        private final String title;
        private final String body;

        public Notification(String title, String body) {
            this.title = title;
            this.body = body;
        }
    }
}
