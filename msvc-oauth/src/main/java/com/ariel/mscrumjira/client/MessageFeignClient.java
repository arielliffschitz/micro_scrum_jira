package com.ariel.mscrumjira.client;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-message")
public interface MessageFeignClient {

	@GetMapping("/message-count")
	public Integer getUnreadMessagesCount( @RequestParam String username  ) ;
}
