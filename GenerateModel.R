# Load the RMongo library
library(RMongo)
library(rmongodb)
source('SentimentClassification.R')

# Create a mongodb connection
con <- mongoDbConnect("yelp", "localhost", port = 27017)
con2 <- mongo.create(host = "localhost", db = "yelp")

# Query all the reviews
allReviews <- dbGetQuery(con, "reviews", '{}')
reviews <- allReviews[, "text"]

allReviews2 = data.frame(stringAsFactors = FALSE)
cursor = mongo.find(con2, "yelp.reviews")
i = 1
while(mongo.cursor.next(cursor))
{
  print(i)
  tmp = mongo.bson.to.list(mongo.cursor.value(cursor))
  #print(tmp)
  tmp.df = as.data.frame(t(unlist(tmp)), stringAsFactors = FALSE)
  allReviews2 = rbind.fill(allReviews2, tmp.df)
  i = i + 1
}

# Extracting reviews and stars out of data frame, ignoring first 
# row because it was having value <NA>
reviews2 <- allReviews2[2:nrow(allReviews2),"text"]
stars2 <- allReviews2[2:nrow(allReviews2),"stars"]
#result2 <- score.classify(reviews2, vNegWords, negWords, posWords, vPosWords)
reviews1k <- allReviews2[1:1000,c('text')]
stars1k <- allReviews2[1:1000,c('stars')]
results1k <- score.classify(reviews1k, vNegWords, negWords, posWords, vPosWords)
results1k <- cbind(results1k, stars1k)
results1k <- as.data.frame(results1k)
colnames(results1k) <- c('Review', 'vNeg', 'neg', 'pos', 'vPos', 'stars')
results1k$vNeg <- as.numeric(results1k$vNeg)
results1k$neg <- as.numeric(results1k$neg)
results1k$pos <- as.numeric(results1k$pos)
results1k$vPos <- as.numeric(results1k$vPos)
results1k$stars <- as.factor(results1k$stars)

clm1k <- clm(stars ~ vNeg + neg +pos + vPos,link = "logit", data= results1k)
testReviews <- c('Review', 'Review')
