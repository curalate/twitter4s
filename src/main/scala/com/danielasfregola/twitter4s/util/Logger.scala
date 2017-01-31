package com.danielasfregola.twitter4s.util

import com.typesafe.scalalogging.{Logger => TypeSafeLogger}

trait Logger {

  @volatile protected lazy val log: TypeSafeLogger = TypeSafeLogger("twitter4s")

}
