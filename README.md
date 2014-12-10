The-Next-Business-Move
======================

CMPE-295B SJSU Masters project

<b> Copy all json data files to <Mongo-Installation-Dir>/bin and execute following commands to import the dataset </b> <br />
mongoimport --db yelp --collection checkin yelp\_academic\_dataset_checkin.json

<b> Question-1: Can you predict the rating of any business based on its reveiws? </b> <br />
For this feature, we have used the concepts of sentimental analysis to determine the rating of previous reviews of the businesses and then use the cumulative link model to generate the machine learning model which can predict rating of any unseen review. The rating of unseen reviews of a businesses will be used for determining the rating of any business. <br />
Solution is provided in R script "PredictBusinessRatings.R"


<b> Question-2: Can you predict ratings of facilities of any business based on its text? </b> <br />
For this feature, we are using concepts of feature-based search on reviews. We are searching and performing sanity-checks for a particular feature (e.g. Wi-Fi or Good-for-children). Then in the same review, we are determining the positivity or the negativity of the review which is used for determining the final answer of Wi-Fi availability / Good-for-children. <br /> Solution is provided in PredictWiFiRatings.R, PredictOutdoorSeatingRatings.R, PredictParkingRatings.R files which predicts the ratings for Wi-Fi, Outdoor Seating and Parking facilities respectively of any business.


<b> Question-3: Can you predict the next business to be reviewed by customer? </b> <br />
For this feature, we have used collaborative filtering approach. We have a review dataset which has corresponding review_id, business\_id and user_id. So, we have gathered similar users based on the businesses those users have reviewed. And after getting similar users, based on pre-decided threshold, we have calculated the next businesses which are likely to be reviewed. <br />
Solution is available in RecommendUser.java, StoreRecommendation.java, toCSV.java, UnresistBoolRecommend.java
