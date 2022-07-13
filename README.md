# Plain Language Encoder

The Plain Language Encoder is a library for encoding data in a similar way as Base64 but using short words instead of characters. For example a file containing the following text (encoded as UTF-8)
```
The quick brown fox jumps over the lazy dog
```
will be encoded as 
```
Us it who one he do in may but oh. Or two he it on try say put get day. He it you new she be I way get oh. May any his try I new get him us try. He do if now you me I use and the. Our own he it if new you any now lot.
```

The intent is to make the encoded text look less obviously encoded, at least when examined by software with no capability of natural language understanding.

The Plain Language Encoder has two classes LanguageEncodedOutputStream and LanguageEncodedInputStream. These add functionality to (decorate) a OutputStream/InputStreamStream respectively, in a similar way to BufferedOutputStream/BufferedInputStream.

To a file encode:
```
try (InputStream is = new BufferedInputStream( new FileInputStream( sourceFile));
     OutputStream os = new LanguageEncodedOutputStream( new BufferedOutputStream( new FileOutputStream( encodedFile)))) {
  Instant start = Instant.now();
  int inputData;
  while ((inputData = is.read()) != -1) {
    os.write(inputData);
  }
}
```

To decocde the data:
```
try (InputStream is = new LanguageEncodedInputStream( new BufferedInputStream( new FileInputStream( encodedFile)));
     OutputStream os = new BufferedOutputStream( new FileOutputStream( decodedFile))) {
  Instant start = Instant.now();
  int inputData;
  while ((inputData = is.read()) != -1) {
    os.write(inputData);
  }
}
```
