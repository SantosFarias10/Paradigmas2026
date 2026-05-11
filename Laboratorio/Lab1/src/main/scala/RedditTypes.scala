object RedditTypes {
  type Url = String
  type Subreddit = String
  type SubredditContent = String
  type Subscription = (Subreddit, Url)

  type postTitle = String
  type postText = String
  type postedDate = String
  type score = Int
  type postUrl = String

  type Post = (Subreddit, postTitle, postText, postedDate, score, postUrl)
}