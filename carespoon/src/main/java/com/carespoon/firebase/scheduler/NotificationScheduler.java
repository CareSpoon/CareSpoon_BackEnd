package com.carespoon.firebase.scheduler;

import com.carespoon.firebase.enums.RequestPushMessage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static com.carespoon.firebase.enums.RequestPushMessage.*;

@Slf4j
@Service
public class NotificationScheduler {
    String fireBaseCreateScoped = "https://www.googleapis.com/auth/firebase.messaging";
    String topic = "no_record_for_meals";

    private FirebaseMessaging instance;
    @PostConstruct
    public void firebaseSetting() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new FileInputStream("/home/joyrooom15/CareSpoon_BackEnd/carespoon/build/resources/main/firebase/care-spoon-82c78-firebase-adminsdk-c2nf0-14d8922178.json"))
                .createScoped((Arrays.asList(fireBaseCreateScoped)));
        FirebaseOptions secondaryAppConfig = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(secondaryAppConfig);
        this.instance = FirebaseMessaging.getInstance(app);
    }
    @Scheduled(cron = "0 0 09 * * ?")
    public void pushMorningDietAlarm() throws FirebaseMessagingException{
        log.info("아침 식사 알림");
        pushAlarm(MORNING_DIET);
    }

    @Scheduled(cron = "0 0 13 * * ?")
    public void pushLunchDietAlarm() throws FirebaseMessagingException{
        log.info("점심 식사 알림");
        pushAlarm(LUNCH_DIET);
    }

    @Scheduled(cron = "0 0 19 * * ?")
    public void pushDinnerDietAlarm() throws FirebaseMessagingException {
        log.info("저녁 식사 알림");
        pushAlarm(DINNER_DIET);
    }

    private void pushAlarm(RequestPushMessage data) throws FirebaseMessagingException {
        Message message = getMessage(data);
        sendMessage(message);
    }

    private Message getMessage(RequestPushMessage data) {
        Notification notification = Notification.builder().setTitle(data.getTitle()).setBody(data.getBody()).build();
        Message.Builder builder = Message.builder();
        Message message = builder.setTopic(topic).setNotification(notification).build();
        return message;
    }

    public String sendMessage(Message message) throws FirebaseMessagingException {
        return this.instance.send(message);
    }

}
