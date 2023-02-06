# レスポンスのJSONの真偽値をアサートできる

## JSON Path
* レスポンスボディとしてシナリオデータストアに"{ \"key1\": true, \"key2\": [ false ] }"を保存する
* レスポンスのJSONの"$.key1"が真偽値の"true"である
* レスポンスのJSONの"$.key1"が真偽値のtrueである
* レスポンスのJSONの"$.key2[0]"が真偽値の"false"である
* レスポンスのJSONの"$.key2[0]"が真偽値のfalseである

## JSON Pathで指定したオブジェクトが一致しない
* レスポンスボディとしてシナリオデータストアに"{ \"key1\": true, \"key2\": [ false ] }"を保存する
* レスポンスのJSONの"$.key1"が真偽値の"false"でない
* レスポンスのJSONの"$.key1"が真偽値のfalseでない
* レスポンスのJSONの"$.key2[0]"が真偽値の"true"でない
* レスポンスのJSONの"$.key2[0]"が真偽値のtrueでない

## 配列内において存在（無）
* レスポンスボディとしてシナリオデータストアに"{\"key1\":[{\"id\":\"a\",\"key3\":false}, {\"id\":\"b\",\"key3\": true }, {\"id\":\"c\",\"key3\":true}]}"を保存する
* レスポンスのJSONの"$.key1"の配列の、UniqueKey"id"の値が"b"である要素の"key3"が、真偽値の"true"である
