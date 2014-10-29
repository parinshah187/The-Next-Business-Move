score.classify = function(reviews, vNegWords, negWords, posWords, vPosWords, .progress='none')
{
  require(plyr)
  require(stringr)
  
  final_scores <- matrix('', 0, 5)
  
  # we have a vector of sentences. plyr handles a list or a vector as an "l" for us
  # we want an array of scores back, so we use "l" + "a" + "ply" = laply
  sentimentScores <-  laply(reviews, function(review, vNegWords, negWords, posWords, vPosWords) {
    
    
    # split into words. str_split is in the stringr package
    word.list <- str_split(review, '\\s+')

    # sometimes a list() is one level of hierarchy
    words <- unlist(word.list)
    
    class(words)
    
    # compare our words to positive & negative terms
    vPos.matches <- match(words, vPosWords)
    pos.matches <- match(words, posWords)
    neg.matches <- match(words, negWords)
    vNeg.matches <- match(words, vNegWords)
    
    # match() returns the position of the matched term or NA
    posMatches = sum(!is.na(pos.matches))
    negMatches = sum(!is.na(neg.matches))
    vPosMatches = sum(!is.na(vPos.matches))
    vNegMatches = sum(!is.na(vNeg.matches))
    
    #print(posMatches)
    
    score <- c(vNegMatches, negMatches, posMatches, vPosMatches)
    newrow <- c(review, score)
    final_scores <- rbind(final_scores, newrow)
    return(final_scores)
  }, vNegWords, negWords, posWords, vPosWords, .progress=.progress )
  
  return(sentimentScores)
}