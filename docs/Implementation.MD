# Implementation of Vanity Numbers

Implementation has primarily three parts
1) Validating phone number and generating possible vanity numbers for th provided phone number
2) Input all the possible combinations of vanity numbers and get the matching vanity numbers against dictionary
3) Get top matching vanity numbers and save to dynamodb

Assumptions:
1) To get easily remember vanity numbers used dictionary with predefined words like, CALL, US, ORDER etc

What has not been covered:
1) Authorization and authentication not covered for the endpoints which are very important
2) Raised exceptions for invalid phone numbers, but not handle at frontend(Amplify)
3) Logs has not been configured, which are required
4) Some places used broad privileges instead least privileges
5) AWS amplify git hub key is not moved to KMS, right now it has place holder
6) Backend url(API gateway of lambda) hard coded in Lambda
7) Amazon connect not implemented

Challenges:
1) CORS configuration took some time to figured it out, right now allowed all the methods
2) Configuring least privileges took quite lot of time, so some places used broad privileges
3) Optimizing generate the vanity numbers, still there is scope for refactor/optimize
