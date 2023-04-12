package com.danielasfregola.twitter4s.entities.v2

final case class TweetUpdate(
    text: Option[String],
    media: Option[TweetMediaInfo],
    reply: Option[TweetReplyInfo]
)

case class TweetReplyInfo(
    in_reply_to_tweet_id: String
)

case class TweetMediaInfo(
    media_ids: Seq[String]
)
