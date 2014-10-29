# Load/install required libraries
install.packages("MASS")
library(MASS)

# read file with sentiment value for words
afinn_list <- read.delim(file='data/AFINN-111.txt', header = FALSE, sep = "\t")
colnames(afinn_list) <- c('word', 'score')

#load function required to calculate sentiment score of each review
source('SentimentClassification.R')

# classifiy words depending on score
vNegWords <- as.vector(afinn_list[afinn_list$score<=-4,c('word')])
negWords <- as.vector(afinn_list[afinn_list$score> -4 & afinn_list$score < 0,c('word')])
posWords <- as.vector(afinn_list[afinn_list$score> 0 & afinn_list$score < 4,c('word')])
vPosWords <- as.vector(afinn_list[afinn_list$score>=4,c('word')])

stars <- c(3,5,3,4,1,4,2,3,5,5)
results <- cbind(results, stars)
results <- as.data.frame(results)
colnames(results) <- c('Review', 'vNeg', 'neg', 'pos', 'vPos', 'stars')
results$vNeg <- as.numeric(results$vNeg)
results$neg <- as.numeric(results$neg)
results$pos <- as.numeric(results$pos)
results$vPos <- as.numeric(results$vPos)
results$stars <- as.factor(results$stars)


model <- polr(stars ~ vNeg + neg + pos + vPos, data=results, Hess = TRUE)
clmFit <- clm(stars ~ vNeg + neg +pos + vPos,link = "logit", data= results)
predict(clmFit, result, type = "prob")
review <- c("I just spent 8 days in Florida.  I have had a total of 2 drinks in the past 8 days.  This kookie tequila bar in the middle of the C terminal in Phoenix is like my mirage in the dessert.  Only when I walked in, it did not disappear.  \n\nI'm two double gin and tonics down, and I have plenty of time before my next flight.  Maybe it's the gin talking, but these are some damned fine nachos.  Damned.  Fine.  And the bartender?  Even finer...though he has me on an auto re-up system with these G&T's, so again, it could be the gin talking.\n\nI would come back here in a heart beat the next time I'm in Phoenix.  Damned.  Fine")
result <- score.classify(review, vNegWords, negWords, posWords, vPosWords)
result <- data.frame(vNeg = 1, neg=0, pos=2, vPos=0)


