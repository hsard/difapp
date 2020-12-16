# WAES 2-side base64 encoded data diff tool

## Api Design

#### Upload Content Endpoints
Request Type: POST \
Content Type: application/json \
Accept Type: application/json \
Url(left): localhost:8080/v1/diff/{sessionId}/left \
Url(right): localhost:8080/v1/diff/{sessionId}/right \
Request Sample:
```http request
curl --location --request POST 'localhost:8080/v1/diff/125/RIGHT' \
--header 'Content-Type: application/json' \
--data-raw '{
    "content": "abERefTY"
}'
```

#### Analyze Content Endpoint
Request Type: Get \
Accept Type: application/json \
Url: localhost:8080/v1/diff/{sessionId} \
Request Sample:
```http request
curl --location --request GET 'localhost:8080/v1/diff/125' \
--header 'Accept: application/json'
```

Response Samples
``` json
1.
{
    "resultCode": "MISMATCHING_CONTENT",
    "resultDescription": "Mismatching contents",
    "diffs": [
        {
            "start": 2,
            "offset": 2
        },
        {
            "start": 6,
            "offset": 2
        }
    ]
}

2.
{
    "resultCode": "MISMATCHING_SIZES",
    "resultDescription": "Contents are of different sizes"
}
```

#### Internalization support 
Api currently supports EN and NL locales.\
Include this header in order to get localized response messages
```
--header 'Accept-Language: nl' \
```

## Tech design

#### Content Validation
Design allows to define custom validators like SizeValidator, Encoding Validator, etc.. and create validation chain from that validators and apply to the content. Technically the solution is an implmentation of design pattern "Chain of Responsibility". 

#### Diff analyzers
In case of negative validation and proven mismatching content case, diff analyzer is applied to the content to report the actual diffs(offset:length).
Design allows to define custom analyzers like CustomAnalyzer, register them in Analyzer Factory and in future get them by key(predefined enum) and apply to content. Technically its an implementation of design pattern "Factory". 

#### Diff session storage
Considering tools like this as a short-session on-a-fly tool that are mainly used within minutes and closed, I decided not to use JPA persistence. Instead api defines its own in-memory repository with a session-expiration control.
Session expiration is configurable and can be managed from application.yml by changing the following property: diffapp.diff.session.timeoutMs=120000 (2 min). Diff sessions that are inactive for 2 mins are automatically removed from the repository.
Please note, that necessary abstraction stands behind the implementation, so you are free to extend and define your own repository, but don't forget to set diffapp.repository.inmemory=false, which stands for loading conditional bean.            

#### Testing
Controller, Validators and Analyzers functionalities are covered by tests

## Nice to haves
- authentication (Spring Security)                               
- api documentation (Swagger)
- simple UI (React or Thymeleaf)