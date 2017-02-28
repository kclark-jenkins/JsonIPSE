# IpseHelper

* [Usage](#Usage)
  * [iHub](#iHub)
  * [Client Side](#Client-Side)
* [Dependencies](#Dependencies)

IpseHelper is a wrapper for OpenText's Information Hub's popular IPSE security extension.  

This extension point allows for you to pass in nearly any information you want to a servlet prior to report execution.  Once your information has been passed to the servlet you can preform your own authentication action on it and choose to allow or disallow access to the resource being requested.

IpseHelper is a library that aims to speed up this process by taking a single JSON string,  map it to a POJO, and finally set's this JSON a server side session level parameter for later use in a BIRT report should it be needed. 

# Usage

## iHub

To use the IpseHelper you need to include the IpseHelper and it's dependecies in the following location

```
%IHUB_HOME%\modules\BIRTiHub\iHub\web\birtservice\WEB-INF\lib
```

For example, my enviornment uses the default installation path

```
C:\OpenText\InformationHub
```

Once IpseHelper and it's dependecies have been installed you would write a class similar to the following sample code

```java
package org.krisbox;

import org.krisbox.json.ipse.utils.IpseHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import com.actuate.reportcast.exceptions.AuthenticationException;
import com.actuate.iportal.security.iPortalSecurityAdapter;

public class JsonIpse extends IpseHelper {
    public JsonIpse() {
        super("ipseJSON");
    }

    @Override
    public boolean authenticate(HttpServletRequest request) {
        try {
            mapJson(request);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return true;
    }
}
```


The above code always allows the resource to be served.  For something more practical you might do something like this
```java
package org.krisbox;

import org.krisbox.json.ipse.utils.IpseHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import com.actuate.reportcast.exceptions.AuthenticationException;
import com.actuate.iportal.security.iPortalSecurityAdapter;

public class JsonIpse extends IpseHelper {
    public JsonIpse() {
        super("ipseJSON");
    }

    @Override
    public boolean authenticate(HttpServletRequest request) {
        try {
            mapJson(request);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        // perform some authentication on this token
        return getIpsePropertiesPojo().getAdditionalProperties("authToken");
    }
}
```

After writing a class that extends IpseHelper similar to what's shown above you will need to make the iHub aware that it should be using your IPSE.  

First place your jar in the same location as the dependencies.

Next open web.xml located at

```
%IHUB_HOME%\modules\BIRTiHub\iHub\web\birtservice\WEB-INF

or

C:\OpenText\InformationHub\modules\BIRTiHub\iHub\web\birtservice\WEB-INF
```

Then find the following line

```
<param-name>SECURITY_ADAPTER_CLASS</param-name>
<param-value/>
```

Assuming the same package as above it would need to be modified to the following

```
<param-name>SECURITY_ADAPTER_CLASS</param-name>
<param-value>org.krisbox.JsonIpse</param-value>
```

Finally restart the iHub service.

## Client Side

## Sample JSON

The JSON I used to test the first code snippet is as follows
```json
{
	"ipseProperties": {
		"username": "Administrator",
		"password": "",
		"volume": "Default Volume",
		"volumeProfile": "Default Volume",
		"enterprise": true,
		"userHomeFolder": "/home/Administrator",
		"sessionLevelParameters": {
			"parameterNames": [
				"testParam0",
				"testParam1"
			],
			"parameterValues": []
		}
	}
}
```

## Sample HTML and JavaScript

```html
<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type='text/javascript' language='JavaScript' src='http://dummyhost.com:8700/iportal/jsapi'></script>

    <script>
        $(document).ready(function() {
            var iportal = window.location.origin + '/iportal';
            var loginJSONCookie = 'ipseJSON';
            var loginJSON = '{"ipseProperties": {"username": "Administrator","password": "","volume": "Default Volume","volumeProfile": "Default Volume","extendedCredentials": "","enterprise": true,"userHomeFolder": "/home/Administrator","sessionLevelParameters": {"parameterNames": ["testParam0", "testParam1"],"parameterValues": []}}}';

            var renderReport = function() {
                actuate.load('viewer');
                var reqOps = new actuate.RequestOptions();
                reqOps.setRepositoryType('Enterprise');
                reqOps.setVolume('Default Volume');
                reqOps.setCustomParameters({});
                actuate.initialize('http://dummyhost.com:8700/iportal/', reqOps == undefined ? null : reqOps, null, null, myInit);

                function myInit() {
                    viewer1 = new actuate.Viewer('container1');
                    viewer1.setReportDesign('/Applications/Sample Application/Report Designs/salesOrderReport.rptdesign;1');
                    var options = new actuate.viewer.UIOptions();
                    viewer1.setUIOptions(options);
                    viewer1.submit();
                }
            }

            var setCookie = function(name, value) {
                document.cookie = name + '=' + value;
            }

            var getCookieValue = function(a) {
                var b = document.cookie.match('(^|;)\\s*' + a + '\\s*=\\s*([^;]+)');
                return b ? b.pop() : '';
            }

            var buildLoginCookie = function() {
                var loginObject = {
                    ipseProperties: {
                        username: $('#username').val(),
                        password: $('#password').val(),
                        volume: $('#volume').val(),
                        volumeProfile: $('#volumeProfile').val(),
                        extendedCredentials: $('#extendedCredentials').val(),
                        enterprise: true,
                        userHomeFolder: '/home/' + $('#username').val(),
                        sessionLevelParameters: {
                            "parameterNames": ["testParam0", "testParam1"],
                            "parameterValues": []
                        }
                    }
                }

                return JSON.stringify(loginObject);
            }

            var getParameterByName = function(name, url) {
                if (!url) {
                    url = window.location.href;
                }
                name = name.replace(/[\[\]]/g, "\\$&");
                var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                    results = regex.exec(url);
                if (!results) return null;
                if (!results[2]) return '';
                return decodeURIComponent(results[2].replace(/\+/g, " "));
            }

            var forward = function(resource) {
                window.location = document.URL + '?auth=yes';
            }

            var notify = function(seconds, type, message) {
                if (seconds >= 0) {
                    if (type == 'dynamic') {
                        $('#notifications').html('Rendering report in ' + (seconds) + '...' + ' using the following cookie:<br/>');
                        formatJson(buildLoginCookie());
                    } else {
                        $('#notifications').html('Rendering report in ' + (seconds) + '...' + ' using the following cookie:<br/>');
                        formatJson(loginJSON);
                    }

                    setTimeout(function(test) {
                        if (seconds > 0) {
                            notify(seconds - 1);
                        } else {
                            $('#notifications').html('Forwarding to ihub resource...');
                            forward(iportal);
                        }
                    }, 1000);
                } else {
                    $('pre').html(message);
                }
            }

            var formatJson = function(json) {
                $("pre").text(JSON.stringify(JSON.parse(json), null, '\t'));
            }

            var syntaxHighlight = function(json) {
                if (typeof json != 'string') {
                    json = JSON.stringify(json, undefined, 2);
                }
                json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
                return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
                    var cls = 'number';
                    if (/^"/.test(match)) {
                        if (/:$/.test(match)) {
                            cls = 'key';
                        } else {
                            cls = 'string';
                        }
                    } else if (/true|false/.test(match)) {
                        cls = 'boolean';
                    } else if (/null/.test(match)) {
                        cls = 'null';
                    }
                    return '<span class="' + cls + '">' + match + '</span>';
                });
            }

            $('#login').click(function() {
                if ($('input[name=loginType]:checked').val() === 'dynamic') {
                    setCookie(loginJSONCookie, buildLoginCookie());
                    console.log(buildLoginCookie());
                    notify(5, 'dynamic');
                } else {
                    setCookie(loginJSONCookie, loginJSON);
                    console.log(loginJSON);
                    notify(5, 'static');
                }
            });

            if (getParameterByName('auth') == 'yes') {
                formatJson(getCookieValue(loginJSONCookie));
                $('#notifications').html('Attempted to render report with the following JSON');
                renderReport();
            }
        });
    </script>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                            Volume
                        </div>
                        <div class="col-sm-2">
                            <input id="volume" type="text" name="volume" value="Default Volume">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            Volume Profile
                        </div>
                        <div class="col-sm-2">
                            <input id="volumeProfile" type="text" name="volumeProfile" value="Default Volume">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            Username
                        </div>
                        <div class="col-sm-2"><input id="username" type="text" name="username" value="Administrator"></div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            Password
                        </div>
                        <div class="col-sm-2">
                            <input id="password" type="password" name="password" value="">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            Login Type
                        </div>
                        <div class="col-sm-2">
                            <input type="radio" name="loginType" value="static">Static Login <input type="radio" name="loginType" value="dynamic" checked> Dynamic Login
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            
                        </div>
                        <div class="col-sm-2"><input id="login" type="submit" value="Login"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="container">
                    <div class="row">
                        <div id="notifications" class="col-sm-4">
                            Notifications
                        </div>
					</div>
					<div class="row">
                        <pre class="col-md-4">
                        </pre>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
                <div class="container">
                    <div class="row">
                        <div id="container1" class="col-sm-1">
                        </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
```

# Dependencies

## Production
* [Android 4.1.1.4](https://mvnrepository.com/artifact/com.google.android/android/4.1.1.4)
* [Jackson Databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
* [Jackson Annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations/2.8.7)
* [Validation API 1.1.0 Final](https://mvnrepository.com/artifact/javax.validation/validation-api/1.1.0.Final)
* [Servlet API 2.5](https://mvnrepository.com/artifact/javax.servlet/servlet-api/2.5)
* [Jackson Core 2.8.7](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.8.7)

## Tests
* [JUnit 4.11](https://mvnrepository.com/artifact/junit/junit/4.11)
* [Spring 2.5.6](https://mvnrepository.com/artifact/org.springframework/spring/2.5.6)
* [Spring Core 2.5.6](https://mvnrepository.com/artifact/org.springframework/spring-core/2.5.6)
* [Spring Test 2.5.6](https://mvnrepository.com/artifact/org.springframework/spring-test/2.5)
