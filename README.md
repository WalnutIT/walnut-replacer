# WalnutReplacer [![Quality Gate Status](https://sonarqube.walnut-it.com/api/project_badges/measure?project=WalnutReplacer&metric=alert_status)](https://sonarqube.walnut-it.com/dashboard?id=WalnutReplacer)

## Introduction

The WalnutReplacer allows to replace placeholders in files. With this tool you
can avoid commits with sensitive data like usernames and passwords or replacing
placeholders in several files with one execution. 

E. g.: 

index.html

```html
<html>
	<head>
		<title>%%TITLE%%</title>
	</head>
	<body>
		<h1>%%HEADLINE%%</h1>
	</body>
</html>
```
can become to:

```html
<html>
	<head>
		<title>My Title</title>
	</head>
	<body>
		<h1>My Headline</h1>
	</body>
</html>
```	

## How to use

### Step 1: Create a WalnutReplacer Configuration File

A WalnutReplacer config file needs to have following structure:
 
	file.update=<path> 
	<key>=<value> 
	... 
	file.update=<path> 
	<key>=<value>
	...

E. g.

	file.update=/var/www/html/index.html
	%%TITLE%%=My Title
	%%HEADLINE%%=My Headline
	fileUpdate=/opt/application/config/application.config
	${app.db.url}=jdbc:postgresql://192.168.1.1:5432/appDb
	${app.db.username}=my_username
	${app.db.password}=my_password
	
The name for the configuration file and the keys can be free chosen. The only 
limitation to the configuration file is, that each block has to start with 
**`file.update=<path>`** followed by the key-value pairs for the specified file.
	
### Step 2: Run WalnutReplacer

To run the WalnutReplacer you have to execute following command:

`java -jar walnutreplacer.jar <path to config file>`


