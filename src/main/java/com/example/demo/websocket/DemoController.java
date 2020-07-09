package com.example.demo.websocket;

import com.modoo.common.model.MsgResult;
import com.modoo.common.util.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * @author demo
 */
@RestController
@RequestMapping(value = "/demo", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class DemoController {

    @Autowired
    SocketDemoHandler socketDemoHandler;
    /**
     * @param message
     * @return
     */
    @RequestMapping(value = "/message", method = {RequestMethod.GET})
    public MsgResult orderNotify(@PathParam("message") String message) {
        socketDemoHandler.sendNotifyToAll(message);
        return MsgResult.success();
    }

    /**
     *
     * @param request
     */
    @RequestMapping(value = "/hello")
    public void hello(@RequestBody HttpServletRequest request){
        System.out.println(IpUtils.getRemoteAddr(request));
    }

}
