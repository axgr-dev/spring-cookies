package dev.axgr

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.time.temporal.ChronoUnit

@RestController
class CookieController {

  companion object {
    private val log = LoggerFactory.getLogger(CookieController::class.java)
  }

  @GetMapping("/cookie")
  fun inspect(): ResponseEntity<Void> {
    val cookie = ResponseCookie.from("theme")
      .value("dark")
      .domain("localhost")
      .maxAge(Duration.of(60, ChronoUnit.SECONDS))
      .httpOnly(true)
      .secure(true)
      .path("/")
      .build()

    return ResponseEntity.ok()
      .header("Set-Cookie", cookie.toString())
      .build()


  }

  @GetMapping("/preferences")
  fun preferences(@CookieValue("theme") theme: String?) {
    log.info("You chose the $theme theme.")
  }
}
