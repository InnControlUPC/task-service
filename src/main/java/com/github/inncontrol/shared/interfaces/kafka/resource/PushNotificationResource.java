package com.github.inncontrol.shared.interfaces.kafka.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: inncontrol-backend
 * Date: 6/4/25 @ 16:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationResource {
    private String userId;
    private String toMail;
    private String title;
    private String message;
    private String type;
    private boolean broadcast;
}
