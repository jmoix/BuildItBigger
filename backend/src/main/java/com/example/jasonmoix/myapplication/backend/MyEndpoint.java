/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.jasonmoix.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.example.JokeProvider;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.myapplication.jasonmoix.example.com",
    ownerName = "backend.myapplication.jasonmoix.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public MyBean getJoke(){
        MyBean response = new MyBean();
        response.setData(new JokeProvider().getJoke());
        return response;
    }

}
