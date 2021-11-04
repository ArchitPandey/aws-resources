### Deploying Angular App to Apache httpd
On running `ng build --prod`, Angular application generates files in dist/app-name folder. These html, css and js files can be run in browser. This can be done using a server that can serve static files, like Apache httpd server.

The configuration for http is very easy and is well documented in link below:
[How-to-serve-angular-app-using-apache-webserver](https://ramya-bala221190.medium.com/how-to-serve-an-angular-7-application-on-an-apache-web-server-with-gzip-compression-23d5b72f81f)

One thing to note is that server must serve index.html when it's asked for a file that it doesn't know. This is also documented in above link. The reason for this mentioned in angular docs:

[why-fallback-to-index-when-serving-angular-app](https://angular.io/guide/deployment#fallback)

```
Angular applications are perfect candidates for serving with a simple static HTML server. You don't need a server-side engine to dynamically compose application pages because Angular does that on the client-side. If the application uses the Angular router, you must configure the server to return the application's host page (index.html) when asked for a file that it does not have. A routed application should support "deep links". A deep link is a URL that specifies a path to a component inside the application. For example, http://www.mysite.com/heroes/42 is a deep link to the hero detail page that displays the hero with id: 42. There is no issue when the user navigates to that URL from within a running client. The Angular router interprets the URL and routes to that page and hero. But clicking a link in an email, entering it in the browser address bar, or merely refreshing the browser while on the hero detail page — all of these actions are handled by the browser itself, outside the running application. The browser makes a direct request to the server for that URL, bypassing the router. A static server routinely returns index.html when it receives a request for http://www.mysite.com/. But it rejects http://www.mysite.com/heroes/42 and returns a 404 - Not Found error unless it is configured to return index.html instead.
```
