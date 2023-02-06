# レスポンスのJSONのValueを正規表現でアサートできる

## JSON Path
* レスポンスボディとしてシナリオデータストアに"{ \"key1\": \"09012345678\", \"key2\": { \"key3\": \"test@example.com\" }, \"key4\": \"\" }"を保存する
* レスポンスのJSONの"$.key1"が"[0-9]{11}"である
* レスポンスのJSONの"$.key2.key3"が"^[0-9a-zA-Z_.+-]*@+[a-zA-Z0-9.]*"である
* レスポンスのJSONの"$.key2.key3"が".+"である
* レスポンスのJSONの"$.key4"が".*"である

## JSON Pathで指定したオブジェクトが一致しない
* レスポンスボディとしてシナリオデータストアに"{ \"key1\": \"09012345678\" }"を保存する
* レスポンスのJSONの"$.key1"が"[0-9]{12}"でない
