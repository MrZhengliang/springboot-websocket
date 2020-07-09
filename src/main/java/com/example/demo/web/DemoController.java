package com.example.demo.web;

import com.example.demo.websocket.SocketDemoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author demo
 */
@RestController
public class DemoController {

    @Autowired
    SocketDemoHandler socketDemoHandler;
    /**
     * @param message
     * @return
     */
    @RequestMapping(value = "/demo/notify/{message}", method = {RequestMethod.GET} )
    public String sendNotify(@PathVariable String message) {
        socketDemoHandler.sendNotifyToAll(message);
        System.out.println("api接收到信息" + message);
        return "success";
    }

    /**
     *
     * @param request
     */
    @RequestMapping(value = "/demo/hello", method = {RequestMethod.POST},produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void hello(HttpServletRequest request){
        System.out.println(request.getRemoteAddr());
    }

}
