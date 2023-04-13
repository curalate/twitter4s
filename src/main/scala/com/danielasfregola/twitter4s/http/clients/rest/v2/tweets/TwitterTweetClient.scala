package com.danielasfregola.twitter4s.http.clients.rest.v2.tweets

import com.danielasfregola.twitter4s.entities.{RatedData, ResponseData}
import com.danielasfregola.twitter4s.entities.v2.{Tweet, TweetMediaInfo, TweetReplyInfo, TweetUpdate}
import com.danielasfregola.twitter4s.http.clients.rest.RestClient
import com.danielasfregola.twitter4s.util.Configurations._

import scala.concurrent.Future

/** Implements the available requests for the v2 `tweet` resource. */
trait TwitterTweetClient {

  protected val restClient: RestClient

  private val tweetUrl = s"$apiTwitterUrl/$twitterVersionV2/tweets"

  /** Creates a tweet for the user
    * For each update attempt, the update text is compared with the authenticating user’s recent Tweets.
    * Any attempt that would result in duplication will be blocked, resulting in a `TwitterException` error.
    * Therefore, a user cannot submit the same status twice in a row.
    * For more information see
    * <a href="https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update" target="_blank">
    * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update</a>.
    *
    * @param text The text of your status update, typically up to 140 characters.
    * @param media Optional info about the media you want to include in your tweet
    * @param reply Optional info about what tweet this tweet is replying to
    * @return : The representation of the created tweet.
    */
  def createTweet(
      text: Option[String],
      media: Option[TweetMediaInfo],
      reply: Option[TweetReplyInfo]
  ): Future[RatedData[ResponseData[Tweet]]] = {
    import restClient._
    val entity = TweetUpdate(
      text = text,
      media = media,
      reply = reply
    )
    Post.asJson(s"$tweetUrl", entity).respondAsRated[ResponseData[Tweet]]
  }
}
