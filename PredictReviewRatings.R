testResults <- score.classify(testReviews, vNegWords, negWords, posWords, vPosWords)
testResults <- as.data.frame(testResults)
colnames(testResults) <- c('Review', 'vNeg', 'neg', 'pos', 'vPos')
# Ignoring the 'review' column and keeping only sentiment analysis values
testResults <- testResults[, c(2:ncol(testResults))]
testResults$vPos <- as.numeric(testResults$vPos)
testResults$pos <- as.numeric(testResults$pos)
testResults$neg <- as.numeric(testResults$neg)
testResults$vNeg <- as.numeric(testResults$vNeg)

predict(clm1k, testResults, type = "prob")