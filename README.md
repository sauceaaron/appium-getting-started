# Appium Getting Started

## Sample App
https://github.com/saucelabs/sample-app-mobile

### Upload IOS app to sauce storage
    curl -u $SAUCE_USERNAME:$SAUCE_ACCESS_KEY -H "Content-Type: application/octet-stream" -X POST https://saucelabs.com/rest/v1/storage/$SAUCE_USERNAME/SwagLabs.app.zip?overwrite=true --data-binary @src/main/resources/SwagLabs.app.zip


### Upload Android app to sauce storage
    curl -u $SAUCE_USERNAME:$SAUCE_ACCESS_KEY \
    -H "Content-Type: application/octet-stream" \
    -X POST https://saucelabs.com/rest/v1/storage/$SAUCE_USERNAME/SwagLabs.apk?overwrite=true \
    --data-binary @src/main/resources/SwagLabs.apk

### Check files in Sauce Storage
     curl -u $SAUCE_USERNAME:$SAUCE_ACCESS_KEY \
     -X GET https://saucelabs.com/rest/v1/storage/$SAUCE_USERNAME