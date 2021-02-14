# convert

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://travis-ci.com/vishalsonar/convert.svg?branch=main)](https://travis-ci.com/vishalsonar/convert)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=bugs)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=code_smells)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=coverage)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=ncloc)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=alert_status)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=security_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_convert&metric=sqale_index)](https://sonarcloud.io/dashboard?id=vishalsonar_convert)

Convert is a Java program to transform JSON string to XML and vise versa.
## Usage
### Convert JSON string to XML string
```java
String json = "{ \"user\": { \"name\": \"abc\", \"surname\": \"def\", \"height\": \"5.6\", \"other\": \"wxyz\" } }";
Convert convert = new Convert();
String xmlString = convert.toXml(json);
```
### Convert XML string to JSON string
```java
String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><note></note>";
Convert convert = new Convert();
String jsonString = convert.toJson(xml);
```
## License
Distributed under the MIT License. See `LICENSE` for more information.