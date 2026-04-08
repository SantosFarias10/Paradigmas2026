object RedditTypes {
  type Url = String
  type SubredditName = String
  type SubredditContent = String
  type Subscription = (SubredditName, Url)

  type postTitle = String
  type postText = String
  type postedDate = String

  type Post = (SubredditName, postTitle, postText, postedDate)
}