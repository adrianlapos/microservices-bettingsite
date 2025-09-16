package com.adrianlapos.clients.noti;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="notification-client",
url = "")
public interface NotificationClient {
}
